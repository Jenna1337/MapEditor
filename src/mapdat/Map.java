package mapdat;

public class Map
{
	private int width;
	private int height;
	private Tile[] tiles;
	
	public Map(int w, int h, Tile[] tls)
	{
		setWidth(w);
		setHeight(h);
		setTiles(tls);
	}
	
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
		//TODO remake tiles
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
		//TODO remake tiles
	}
	public Tile[] getTiles()
	{
		return tiles;
	}
	public void setTiles(Tile[] tiles)
	{
		if(this.tiles.length != tiles.length)
			throw new ArrayStoreException("Length of tiles does not match length of tiles");
		this.tiles = tiles;
	}
	public void setTiles(int w, int h, Tile[] tiles)
	{
		setWidth(w);
		setHeight(h);
		setTiles(tiles);
	}
	public Tile getTile(int x, int y)
	{
		return this.tiles[x+width*y];
	}
	public void setTile(int x, int y, Tile t)
	{
		this.tiles[x+width*y] = t;
	}
}
