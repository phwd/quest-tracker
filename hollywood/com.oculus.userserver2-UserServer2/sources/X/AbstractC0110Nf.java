package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.Nf  reason: case insensitive filesystem */
public abstract class AbstractC0110Nf<T> extends TW<T> {
    public Integer A00 = AnonymousClass07.A01;
    @NullableDecl
    public T A01;

    @CanIgnoreReturnValue
    public final boolean hasNext() {
        E e;
        Integer num = this.A00;
        Integer num2 = AnonymousClass07.A03;
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
                if (!(this instanceof AnonymousClass9M)) {
                    AnonymousClass9O r2 = (AnonymousClass9O) this;
                    Iterator<? extends E> it = r2.A00;
                    if (it.hasNext()) {
                        e = (T) it.next();
                    } else {
                        while (true) {
                            Iterator<? extends E> it2 = r2.A01;
                            if (it2.hasNext()) {
                                e = (T) it2.next();
                                if (!r2.A02.A01.contains(e)) {
                                }
                            } else {
                                ((AbstractC0110Nf) r2).A00 = AnonymousClass07.A02;
                                e = (T) null;
                            }
                        }
                    }
                } else {
                    AnonymousClass9M r22 = (AnonymousClass9M) this;
                    while (true) {
                        Iterator<E> it3 = r22.A00;
                        if (it3.hasNext()) {
                            e = it3.next();
                            if (r22.A01.A01.contains(e)) {
                            }
                        } else {
                            ((AbstractC0110Nf) r22).A00 = AnonymousClass07.A02;
                            e = (T) null;
                        }
                    }
                }
                this.A01 = (T) e;
                if (this.A00 == AnonymousClass07.A02) {
                    return false;
                }
                this.A00 = AnonymousClass07.A00;
                return true;
            case 2:
                return false;
        }
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final T next() {
        if (hasNext()) {
            this.A00 = AnonymousClass07.A01;
            T t = this.A01;
            this.A01 = null;
            return t;
        }
        throw new NoSuchElementException();
    }
}
