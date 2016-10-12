package datautils;

import java.io.IOException;

/**
 * Signals that invalid base 64 data was encountered.
 */
public class Base64Exception extends IOException
{
	/**
	 * Constructs a {@code Base64Exception} with {@code null} as its error
	 * detail message.
	 */
	public Base64Exception()
	{
		super();
	}
	/**
	 * Constructs a {@code Base64Exception} with the specified cause and a
	 * detail message of {@code (cause==null ? null : cause.toString())} (which
	 * typically contains the class and detail message of {@code cause}). This
	 * constructor is useful for IO exceptions that are little more than
	 * wrappers for other throwables.
	 * 
	 * @param cause The cause (which is saved for later retrieval by the
	 *            {@code getCause()} method). (A null value is permitted, and
	 *            indicates that the cause is nonexistent or unknown.)
	 */
	public Base64Exception(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Constructs an {@code Base64Exception} with the specified detail message.
	 * 
	 * @param message The detail message (which is saved for later retrieval by
	 *            the {@link #getMessage()} method)
	 */
	public Base64Exception(String message)
	{
		super(message);
	}
	/**
	 * Constructs an {@code Base64Exception} with the specified detail message
	 * and cause.</br>
	 * </br>
	 * Note that the detail message associated with {@code cause} is <i>not</i>
	 * automatically incorporated into this exception's detail message.
	 * 
	 * @param message The detail message (which is saved for later retrieval by
	 *            the {@link #getMessage()} method)
	 * @param cause The cause (which is saved for later retrieval by the
	 *            {@code getCause()} method). (A null value is permitted, and
	 *            indicates that the cause is nonexistent or unknown.)
	 */
	public Base64Exception(String message, Throwable cause)
	{
		super(message, cause);
	}
}
