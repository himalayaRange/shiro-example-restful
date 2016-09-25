package cn.com.zygx.backFramework.exception;

public class ResourceNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = -278891748737843914L;

	public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
