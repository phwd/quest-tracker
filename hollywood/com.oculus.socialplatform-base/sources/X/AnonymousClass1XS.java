package X;

/* renamed from: X.1XS  reason: invalid class name */
public final class AnonymousClass1XS extends AnonymousClass1vG<Runnable> {
    public static final long serialVersionUID = -8219729196779211169L;

    public final String toString() {
        StringBuilder sb = new StringBuilder("RunnableDisposable(disposed=");
        boolean z = false;
        if (get() == null) {
            z = true;
        }
        sb.append(z);
        sb.append(", ");
        sb.append(get());
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass1XS(Runnable runnable) {
        super(runnable);
    }
}
