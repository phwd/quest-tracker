package java.lang;

public class InternalError extends VirtualMachineError {
    private static final long serialVersionUID = -9062593416125562365L;

    public InternalError() {
    }

    public InternalError(String str) {
        super(str);
    }

    public InternalError(String str, Throwable th) {
        super(str, th);
    }

    public InternalError(Throwable th) {
        super(th);
    }
}
