import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import mapdat.Tile;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		String floc = "tiletest5.bmp";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(floc)));
		String data = "";
		int ch;
		while((ch=reader.read())!=-1)
			data+=new String(Character.toChars(ch));
		reader.close();
		Tile t = new Tile(0, Base64.getEncoder().encodeToString(data.getBytes()));
		System.out.println(t.getImg());
		System.out.println(t);
		
		System.exit(0);
		
		String src = "AQEAAAIAAAACAAAAAgAAAAIAAAACAAAAAgAAAAIAAAACAAAAAgAAAAMAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADAAAAAwAAAAIAAAAAAAAAAAAAAAAAAAAAAAAABQAAAAAAAAAAAAAAAwAAAAMAAAADAAAAAgAAAAAAAAAAAAAAAAAAAAUAAAAAAAAAAAAAAAMAAAADAAAAAwAAAAMAAAACAAAAAAAAAAAAAAAFAAAAAAAAAAAAAAADAAAAAwAAAAMAAAADAAAAAwAAAAIAAAAAAAAABQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgAAAA==";
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
