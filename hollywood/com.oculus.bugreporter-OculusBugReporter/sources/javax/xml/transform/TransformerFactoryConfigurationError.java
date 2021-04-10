package javax.xml.transform;

public class TransformerFactoryConfigurationError extends Error {
    private Exception exception;

    public TransformerFactoryConfigurationError() {
        this.exception = null;
    }

    public TransformerFactoryConfigurationError(String msg) {
        super(msg);
        this.exception = null;
    }

    public TransformerFactoryConfigurationError(Exception e) {
        super(e.toString());
        this.exception = e;
    }

    public TransformerFactoryConfigurationError(Exception e, String msg) {
        super(msg);
        this.exception = e;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        Exception exc;
        String message = super.getMessage();
        if (message != null || (exc = this.exception) == null) {
            return message;
        }
        return exc.getMessage();
    }

    public Exception getException() {
        return this.exception;
    }
}
