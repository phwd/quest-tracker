package X;

import java.util.concurrent.Callable;

/* renamed from: X.20c  reason: invalid class name and case insensitive filesystem */
public final class C137020c<T, U> extends AbstractC13251zE<U> implements AbstractC13331zP<U> {
    public final AbstractC13121yu<T> A00;
    public final AbstractC140121h<? super U, ? super T> A01;
    public final Callable<? extends U> A02;

    @Override // X.AbstractC13331zP
    public final AbstractC136820a<U> A3G() {
        return new C137620i(this.A00, this.A02, this.A01);
    }

    public C137020c(AbstractC13121yu<T> r1, Callable<? extends U> callable, AbstractC140121h<? super U, ? super T> r3) {
        this.A00 = r1;
        this.A02 = callable;
        this.A01 = r3;
    }
}
