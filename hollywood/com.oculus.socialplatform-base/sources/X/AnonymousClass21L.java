package X;

import java.util.ArrayList;

/* renamed from: X.21L  reason: invalid class name */
public final class AnonymousClass21L<T> extends ArrayList<Object> implements AnonymousClass21R<T> {
    public static final long serialVersionUID = 7063189396499112664L;
    public volatile int size;

    public AnonymousClass21L() {
        super(16);
    }

    @Override // X.AnonymousClass21R
    public final void A2C() {
        add(EnumC139220y.complete());
        this.size++;
    }

    @Override // X.AnonymousClass21R
    public final void A2t(Throwable th) {
        add(EnumC139220y.error(th));
        this.size++;
    }

    @Override // X.AnonymousClass21R
    public final void A6Y(T t) {
        add(t);
        this.size++;
    }

    @Override // X.AnonymousClass21R
    public final void A9F(AnonymousClass21P<T> r6) {
        int i;
        if (r6.getAndIncrement() == 0) {
            AnonymousClass1yM<? super T> r4 = r6.child;
            int i2 = 1;
            while (!r6.cancelled) {
                int i3 = this.size;
                Number number = (Number) r6.index;
                if (number != null) {
                    i = number.intValue();
                } else {
                    i = 0;
                }
                while (i < i3) {
                    if (!EnumC139220y.accept(get(i), r4) && !r6.cancelled) {
                        i++;
                    } else {
                        return;
                    }
                }
                r6.index = Integer.valueOf(i);
                i2 = r6.addAndGet(-i2);
                if (i2 == 0) {
                    return;
                }
            }
        }
    }
}
