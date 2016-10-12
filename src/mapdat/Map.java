package mapdat;

public class Map
{
	private int width;
	private int height;
	/**
	 * usage:&ensp;{@code tiles[y][x]}</br>
	 * Note: the coordinate point (0,0) is in the top-left corner.
	 */
	private Tile[][] tiles;
	
	public Map(int w, int h, Tile[][] tls)
	{
		setWidth(w);
		setHeight(h);
		setTiles(tls);
	}
	
	public Map(int[][] dat)
	{
		setWidth(dat[0].length);
		setHeight(dat.length);
	}
	
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
		// TODO crop tiles
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
		// TODO crop tiles
	}
	public Tile[][] getTiles()
	{
		return tiles;
	}
	public void setTiles(Tile[][] tiles)
	{
		if((this.tiles.length != tiles.length)
				|| (tiles[0].length != tiles[0].length))
			throw new ArrayStoreException(
					"Length of tiles does not match length of tiles");
		this.tiles = tiles;
	}
	public Tile getTile(int x, int y)
	{
		return this.tiles[y][x];
	}
	public void setTile(int x, int y, Tile t)
	{
		this.tiles[y][x] = t;
	}
}
