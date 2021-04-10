package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nonnull;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0oc  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03820oc<T> implements AnonymousClass0MB<T> {
    public void onCancellation(@Nonnull AnonymousClass0M8<T> r1) {
    }

    public abstract void onFailureImpl(@Nonnull AnonymousClass0M8<T> v);

    public abstract void onNewResultImpl(@Nonnull AnonymousClass0M8<T> v);

    @Override // X.AnonymousClass0MB
    public void onProgressUpdate(@Nonnull AnonymousClass0M8<T> r1) {
    }

    @Override // X.AnonymousClass0MB
    public void onFailure(@Nonnull AnonymousClass0M8<T> r2) {
        try {
            onFailureImpl(r2);
        } finally {
            r2.A29();
        }
    }

    @Override // X.AnonymousClass0MB
    public void onNewResult(@Nonnull AnonymousClass0M8<T> r3) {
        boolean A5y = r3.A5y();
        try {
            onNewResultImpl(r3);
        } finally {
            if (A5y) {
                r3.A29();
            }
        }
    }
}
