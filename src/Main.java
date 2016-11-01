import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Comparator;
import mapdat.Tile;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		File tmp = File.createTempFile("tile", "dat");
		Tile tile = new Tile(5, new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR));
		tile.getImg().setRGB(0, 0, 0);
		byte[] img = tile.toImageBytes();
		FileOutputStream fout = new FileOutputStream(tmp);
		fout.write(img);
		fout.close();
		FileInputStream fin = new FileInputStream(tmp);
		BufferedImage result = Tile.readImageString(fin);
		Tile tile2 = new Tile(5, result);
		System.out.println(tile.getImg() +"\n"+tile2.getImg());
		Comparator<BufferedImage> imgcomp = new Comparator<BufferedImage>()
		{
			public int compare(BufferedImage o1, BufferedImage o2)
			{
				if((o1.getHeight() == o2.getHeight())
				&&(o1.getWidth() == o2.getWidth())
				&&(o1.getType() == o2.getType()))
				{
					for(int x=0; x<o1.getWidth(); ++x)
						for(int y=0; y<o1.getHeight(); ++y)
							if(o1.getRGB(x, y) != o2.getRGB(x, y))
								return 1;
					return 0;
				}
				return -1;
			}
		};
		System.out.println(imgcomp.compare(tile.getImg(), tile2.getImg())==0);
	}
}
