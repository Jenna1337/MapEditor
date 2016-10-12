package datautils;

public interface Base64Converter<T>
{
	public abstract String toBase64(T obj);
	public abstract T fromBase64(String s) throws Base64Exception;
}
