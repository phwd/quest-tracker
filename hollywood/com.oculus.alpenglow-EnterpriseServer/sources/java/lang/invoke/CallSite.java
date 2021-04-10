package java.lang.invoke;

public abstract class CallSite {
    public abstract MethodHandle dynamicInvoker();

    public abstract MethodHandle getTarget();

    public abstract void setTarget(MethodHandle methodHandle);

    public MethodType type() {
        return null;
    }
}
