package exceptions;

public class SqlServerException extends Exception {
    private String source;
	
	public SqlServerException(String message, String source) {
		super(message);
		this.source = source;
	}

	public String getSource() {
		return source;
	}

}
