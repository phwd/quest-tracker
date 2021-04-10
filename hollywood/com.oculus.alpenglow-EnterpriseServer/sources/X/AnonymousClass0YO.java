package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0YO  reason: invalid class name */
public abstract class AnonymousClass0YO<T> extends AnonymousClass0u6<T> {
    public Integer A00 = AnonymousClass007.A01;
    @NullableDecl
    public T A01;

    public abstract T A00();

    @CanIgnoreReturnValue
    public final boolean hasNext() {
        Integer num = this.A00;
        Integer num2 = AnonymousClass007.A0D;
        boolean z = false;
        if (num != num2) {
            z = true;
        }
        Preconditions.checkState(z);
        switch (num.intValue()) {
            case 0:
                return true;
            case 1:
            default:
                this.A00 = num2;
                this.A01 = A00();
                if (this.A00 == AnonymousClass007.A0C) {
                    return false;
                }
                this.A00 = AnonymousClass007.A00;
                return true;
            case 2:
                return false;
        }
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final T next() {
        if (hasNext()) {
            this.A00 = AnonymousClass007.A01;
            T t = this.A01;
            this.A01 = null;
            return t;
        }
        throw new NoSuchElementException();
    }
}
