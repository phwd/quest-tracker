package java.util.concurrent;

public abstract class RecursiveAction extends ForkJoinTask<Void> {
    private static final long serialVersionUID = 5232453952276485070L;

    /* access modifiers changed from: protected */
    public abstract void compute();

    @Override // java.util.concurrent.ForkJoinTask
    public final Void getRawResult() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setRawResult(Void mustBeNull) {
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.ForkJoinTask
    public final boolean exec() {
        compute();
        return true;
    }
}
