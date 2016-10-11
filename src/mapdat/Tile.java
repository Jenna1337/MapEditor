package mapdat;

import java.awt.image.BufferedImage;
import datautils.ImageBase64;

public class Tile
{
	private int id;
	BufferedImage img;
	
	public Tile(int id, BufferedImage img)
	{
		this.id = id;
		this.img = img;
	}
	public Tile(int id, String data64)
	{
		this.id = id;
		this.img = ImageBase64.fromBase64(data64);
	}
	public String toString()
	{
		return "Tile("+id+", \""+getImgBase64()+"\")";
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getImgBase64()
	{
		return ImageBase64.toBase64(getImg());
	}
	public BufferedImage getImg()
	{
		return img;
	}
	public void setImg(BufferedImage img)
	{
		this.img = img;
	}
}
