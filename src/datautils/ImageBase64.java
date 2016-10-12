package datautils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class ImageBase64 implements Base64Converter<BufferedImage>
{
	// Use the most compressed and lossless format.
	private static final String formatName = "PNG";
	
	private ImageBase64()
	{
	}
	
	private static ImageBase64 instance = new ImageBase64();
	
	public static ImageBase64 getInstance()
	{
		return instance;
	}
	
	/**
	 * @param data64 - the base 64 data.
	 * @return The image represented by the data.
	 * @throws Base64Exception if the data does not represent a valid image
	 *             file.
	 */
	public BufferedImage fromBase64(String data64) throws Base64Exception
	{
		try
		{
			byte[] data = Base64.getDecoder().decode(data64);
			File tmp = File.createTempFile("img64_" + data64.hashCode(), "tmp");
			RandomAccessFile raf = new RandomAccessFile(tmp, "rws");
			raf.write(data);
			raf.close();
			return ImageIO.read(tmp);
		}
		catch(IOException e)
		{
			throw new Base64Exception(e);
		}
	}
	public String toBase64(BufferedImage buffimg)
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
