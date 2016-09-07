package datautils;

import java.util.Base64;
import mapdat.Map;
import mapdat.Tile;

/**
 * 
 * @author jonah.sloan
 * @see BaseDen.Decoder
 * @see BaseDen.Encoder
 */
public class MapBase64
{
	/**
	 * 
	 * @author jonah.sloan
	 * @see Encoder
	 */
	public static class Decoder
	{
		private static final Base64.Decoder Dec64 = Base64.getDecoder();
		
		public static Map decode(byte[] src64)
		{
			byte[] raw = Dec64.decode(src64);
			if(raw.length%Integer.BYTES != 0)
				throw new IllegalArgumentException("Not a valid Base64 map");
			
			int w=0;
			w |= raw[0]; w<<=8;
			w |= raw[1]; w<<=8;
			w |= raw[2]; w<<=8;
			w |= raw[3];
			
			int h=0;
			w |= raw[4]; w<<=8;
			w |= raw[5]; w<<=8;
			w |= raw[6]; w<<=8;
			w |= raw[7];
			
			int[] dat = new int[w*h];
			
			for(int i=0;i<dat.length;++i)
			{
				int val=0;
				val |= raw[i*4+ 8]; val<<=8;
				val |= raw[i*4+ 9]; val<<=8;
				val |= raw[i*4+10]; val<<=8;
				val |= raw[i*4+11];
				dat[i] = val;
			}
			
			//TODO get Tile[]
			
			return new Map(w,h,tls);
		}
	}
	/**
	 * 
	 * @author jonah.sloan
	 * @see Decoder
	 */
	public static class Encoder
	{
		private static final Base64.Encoder Enc64 = Base64.getEncoder();
		
		public static byte[] encode(Map m)
		{
			int[] dat = new int[2+m.getHeight()*m.getWidth()];
			
			dat[0]=m.getWidth();
			dat[1]=m.getHeight();
			
			Tile[] tls = m.getTiles();
			for(int i=0; i<tls.length; ++i)
				dat[i+2]=tls[i].getId();
			
			byte[] raw = new byte[Integer.BYTES*dat.length];
			for(int i=0;i<dat.length;++i)
			{
				int tid=dat[i];
				raw[i*4  ] = (byte)tid;
				raw[i*4+1] = (byte)(tid>>=8);
				raw[i*4+2] = (byte)(tid>>=8);
				raw[i*4+3] = (byte)(tid>>=8);
			}
			return Enc64.encode(raw);
		}
	}
	
	private static final Decoder decoder = new Decoder();
	private static final Encoder encoder = new Encoder();
	
	public static Decoder getDecoder()
	{
		return decoder;
	}
	public static Encoder getEncoder()
	{
		return encoder;
	}
}
