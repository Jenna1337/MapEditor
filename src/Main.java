import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import datautils.ImageBase64;
import datautils.MapBase64;
import mapdat.Map;
import mapdat.Tile;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Base64.Encoder enc = Base64.getEncoder();
		String floc = "tiletest5.bmp";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(floc)));
		String data = "";
		int ch;
		while((ch=reader.read())!=-1)
			data+=new String(Character.toChars(ch));
		reader.close();
		Tile t = new Tile(0, ImageBase64.getInstance().fromBase64(enc.encodeToString(data.getBytes(StandardCharsets.ISO_8859_1))));
		System.out.println(t.getImg());
		System.out.println(t);
		
		String br = System.lineSeparator();
		String src = "2,2,2,2,2,2,2,2,2,2,"+br+
					 "3,2,0,0,0,0,0,0,0,0,"+br+
					 "3,3,2,0,0,0,0,5,0,0,"+br+
					 "3,3,3,2,0,0,0,5,0,0,"+br+
					 "3,3,3,3,2,0,0,5,0,0,"+br+
					 "3,3,3,3,3,2,0,5,0,0,"+br+
					 "0,0,0,0,0,0,2,0,0,0,"+br+
					 "0,0,0,0,0,0,0,2,0,0,"+br+
					 "0,0,0,0,0,0,0,0,2,0,"+br+
					 "0,0,0,0,0,0,0,0,0,2";
		Map m = MapBase64.getInstance().fromBase64(enc.encodeToString(src.getBytes(StandardCharsets.ISO_8859_1)));
		System.out.println(m);
		
		System.exit(0);
		
		byte[] bytes = Base64.getDecoder().decode(src);
		int[] ints = new int[bytes.length/4];
		for(int i=0;i<ints.length;++i)
			ints[i]=bytes[i*4];
		String[] hexbytes = new String[bytes.length];
		for(int i=0;i<bytes.length;++i)
			hexbytes[i]=Integer.toHexString(bytes[i]);
		System.out.println(java.util.Arrays.toString(hexbytes));
		
		
		// TODO Auto-generated method stub
	}
}
