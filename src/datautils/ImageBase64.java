package datautils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class ImageBase64
{
	private static final String formatName = "PNG";
	
	private ImageBase64()
	{
	}
	
	/**
	 * @param data64 - the base 64 data.
	 * @return The image represented by the data.
	 * @throws IOException if the data does not represent a valid image file.
	 */
	public static BufferedImage fromBase64(String data64) throws IOException
	{
		byte[] data = Base64.getDecoder().decode(data64);
		File tmp = File.createTempFile("img64_" + data64.hashCode(), "tmp");
		RandomAccessFile raf = new RandomAccessFile(tmp, "rws");
		raf.write(data);
		raf.close();
		return ImageIO.read(tmp);
	}
	public static String toBase64(BufferedImage buffimg)
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageOutputStream imgos = ImageIO.createImageOutputStream(baos);
			boolean success = ImageIO.write(buffimg, formatName, imgos);
			if(!success)
				throw new IOException("Failed to get image data.");
			return Base64.getEncoder().encodeToString(baos.toByteArray());
		}
		catch(IOException e)
		{
			throw new InternalError(e);
		}
	}
}
