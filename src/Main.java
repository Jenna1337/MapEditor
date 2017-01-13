import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Comparator;
import game.CollisionType;
import mapdat.Tile;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		File tmp = File.createTempFile("tile", "dat");
		Tile tile = new Tile(5, CollisionType.TUNNEL_VERTICAL, 
				new BufferedImage(1, 5, BufferedImage.TYPE_4BYTE_ABGR));
		byte[] img = tile.toBytes();
		FileOutputStream fout = new FileOutputStream(tmp);
		fout.write(img);
		fout.close();
		FileInputStream fin = new FileInputStream(tmp);
		Tile tile2 = new Tile(fin);
		System.out.println(tile + "\n" + tile2);
		Comparator<BufferedImage> imgcomp = new Comparator<BufferedImage>()
		{
			Comparator<ColorModel> colormodelcomp = new Comparator<ColorModel>()
			{
				public int compare(ColorModel o1, ColorModel o2)
				{
					System.out.println(o1.hashCode() + " " + o2.hashCode());
					if(o1.hashCode() == o2.hashCode()
							|| o1.toString().equals(o2.toString()))
						return 0;
					return 1;
				}
			};
			
			public int compare(BufferedImage o1, BufferedImage o2)
			{
				System.out.println(o1.hashCode() + " " + o2.hashCode());
				if((o1.getHeight() == o2.getHeight())
						&& (o1.getWidth() == o2.getWidth())
						&& (o1.getType() == o2.getType())
						&& (colormodelcomp.compare(o1.getColorModel(),
								o2.getColorModel()) == 0))
				{
					for(int x = 0; x < o1.getWidth(); ++x)
						for(int y = 0; y < o1.getHeight(); ++y)
							if(o1.getRGB(x, y) != o2.getRGB(x, y))
								return 1;
					return 0;
				}
				return -1;
			}
		};
		System.out.println(imgcomp.compare(tile.getImage(), tile2.getImage()) == 0);
	}
}
