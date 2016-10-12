package datautils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import mapdat.Map;

/**
 * 
 * @author jonah.sloan
 * @see BaseDen.Decoder
 * @see BaseDen.Encoder
 */
public class MapBase64 implements Base64Converter<Map>
{
	private MapBase64()
	{
	}
	
	private static MapBase64 instance = new MapBase64();
	
	public static MapBase64 getInstance()
	{
		return instance;
	}
	
	public Map fromBase64(String src64) throws Base64Exception
	{
		String raw = new String(Base64.getDecoder().decode(src64),
				StandardCharsets.ISO_8859_1);
		String[] rows = raw.split("\\v+");
		int[][] dat = new int[rows[0].length()][rows.length];
		int h = dat[0].length;
		;
		
		for(int y = 0; y < h; ++y)
		{
			String[] cols = rows[y].split(",\\s*");
			for(int x = 0; x < cols.length; ++x)
				dat[y][x] = Integer.parseInt(cols[x]);
		}
		
		return new Map(dat);
	}
	public String toBase64(Map m)
	{
		int[][] dat = new int[m.getHeight()][m.getWidth()];
		int h = m.getHeight(), w = m.getWidth();
		for(int y = 0; y < h; ++y)
			for(int x = 0; x < w; ++x)
				dat[y][x] = m.getTile(x, y).getId();
		String raw = "";
		for(int y = 0; y < h; ++y)
		{
			for(int x = 0; x < w; ++x)
			{
				raw += m.getTile(x, y).getId();
				if(y < h - 1 && x < w - 1)// check if last item
					raw += ",";
			}
			raw += System.lineSeparator();
		}
		return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.ISO_8859_1));
	}
}
