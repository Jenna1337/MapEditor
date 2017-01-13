package mapdat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import game.CollisionType;
import game.TileType;

public class Tile extends TileType
{
	private static final String format = "PNG";
	
	public Tile(int id, CollisionType collision, String imagepath)
	{
		super(id, collision, imagepath);
	}
	public Tile(InputStream stream) throws IOException
	{
		super(stream);
	}
	public Tile(int id, CollisionType ctype,
			BufferedImage bufferedImage)
	{
		super(id, ctype, saveTempImage(bufferedImage));
	}
	private static String saveTempImage(BufferedImage bufferedImage)
	{
		try
		{
			File tempFile = File.createTempFile(System.currentTimeMillis()+"_IMG"+bufferedImage.hashCode(), format);
			boolean success = ImageIO.write(bufferedImage, format, tempFile);
			if(!success)
				throw new IOException("No appropriate writer found for \"" +
						format + "\"");
			return tempFile.getAbsolutePath();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	public byte[] toBytes() throws IOException
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ImageIO.write(getImage(), format, bytes);
		String data = bytes.toString();
		bytes.reset();
		bytes.write(toByta(getId()), 0, 4);
		bytes.write(toByta(data.length()), 0, 4);
		bytes.write(data.getBytes());
		return bytes.toByteArray();
		//TODO collision
	}
	private static byte[] toByta(int data) {
		return new byte[] {
				(byte)((data >> 24) & 0xff),
				(byte)((data >> 16) & 0xff),
				(byte)((data >> 8) & 0xff),
				(byte)((data >> 0) & 0xff),
		};
	}
}