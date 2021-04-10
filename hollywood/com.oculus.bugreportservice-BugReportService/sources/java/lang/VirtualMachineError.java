package java.lang;

public abstract class VirtualMachineError extends Error {
    private static final long serialVersionUID = 4161983926571568670L;

    public VirtualMachineError() {
    }

    public VirtualMachineError(String str) {
        super(str);
    }

    public VirtualMachineError(String str, Throwable th) {
        super(str, th);
    }

    public VirtualMachineError(Throwable th) {
        super(th);
    }
}
