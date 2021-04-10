package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.redex.annotations.ImmutableGetter;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0bf  reason: invalid class name and case insensitive filesystem */
public final class C03140bf<T> implements AnonymousClass0P8<T> {
    @Nullable
    public final T A00;

    @Override // X.AnonymousClass0P8
    @ImmutableGetter
    @Nullable
    public final T A4R() {
        return this.A00;
    }

    /* JADX WARN: Incorrect args count in method signature: (TT;Lcom/facebook/fbservice/results/DataFreshnessResult;JLcom/facebook/graphservice/interfaces/Summary;)V */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public C03140bf(@Nullable Object obj) {
        this.A00 = obj;
    }
}
