package mapdat;

import java.awt.image.BufferedImage;

public class Tile
{
	private int id;
	BufferedImage img;
	
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
	public void setId(int id)
	{
		this.id = id;
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
