package mapdat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import javax.imageio.ImageIO;

public class Tile
{
	private static final String format = "PNG";
	private int id;
	private BufferedImage img;
	
	public Tile(int id, BufferedImage img)
	{
		this.id = id;
		this.img = img;
	}
	public String toString()
	{
		return "Tile("+id+", \""+getImg()+"\")";
	}
	public int getId()
	{
		return id;
	}
	public BufferedImage getImg()
	{
		return img;
	}
	
	public byte[] toImageBytes() throws IOException
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ImageIO.write(img, format, bytes);
		String data = bytes.toString();
		bytes.reset();
		bytes.write(toByta(data.length()), 0, 4);
		bytes.write(data.getBytes());
		return bytes.toByteArray();
	}
	public static BufferedImage readImageString(InputStream stream) throws IOException
	{
		byte[] nbba = new byte[4];
		if(stream.read(nbba, 0, 4) != 4)
			throw new IOException("Failed to get image size!");
		int numbytes = toInt(nbba);
		byte[] bytes = new byte[numbytes];
		int bytesread = stream.read(bytes);
		if(bytesread != numbytes)
			throw new IOException("Failed to read correct amount of bytes!");
		File tmp = File.createTempFile("img"+bytes.hashCode(), "."+format);
		RandomAccessFile raf = new RandomAccessFile(tmp, "rws");
		raf.write(bytes);
		raf.close();
		BufferedImage img = ImageIO.read(tmp);
		return img;
	}
	public static byte[] toByta(int data) {
		return new byte[] {
				(byte)((data >> 24) & 0xff),
				(byte)((data >> 16) & 0xff),
				(byte)((data >> 8) & 0xff),
				(byte)((data >> 0) & 0xff),
		};
	}
	public static int toInt(byte[] data) {
		if (data == null || data.length != 4) return 0x0;
		// ----------
		return (int)( // NOTE: type cast not necessary for int
				(0xff & data[0]) << 24  |
				(0xff & data[1]) << 16  |
				(0xff & data[2]) << 8   |
				(0xff & data[3]) << 0
				);
	}
}