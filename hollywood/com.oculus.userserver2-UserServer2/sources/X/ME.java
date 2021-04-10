package X;

import java.util.concurrent.Callable;

public final class ME extends AbstractRunnableC0203ey<V> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.TrustedListenableFutureTask$TrustedFutureInterruptibleTask";
    public final Callable<V> callable;
    public final /* synthetic */ AnonymousClass0B this$0;

    public ME(AnonymousClass0B r2, Callable<V> callable2) {
        this.this$0 = r2;
        if (callable2 != null) {
            this.callable = callable2;
            return;
        }
        throw null;
    }
}
