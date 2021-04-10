package X;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.tn  reason: case insensitive filesystem */
public abstract class AbstractC1147tn extends AbstractC0370Ug {
    public Integer A00 = AnonymousClass09.A01;
    public Object A01;

    public final boolean hasNext() {
        Object obj;
        Integer num = this.A00;
        Integer num2 = AnonymousClass09.A0J;
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
                C8 c8 = (C8) this;
                while (true) {
                    Iterator it = c8.A00;
                    if (it.hasNext()) {
                        obj = it.next();
                        if (c8.A01.A01.contains(obj)) {
                        }
                    } else {
                        ((AbstractC1147tn) c8).A00 = AnonymousClass09.A0C;
                        obj = null;
                    }
                }
                this.A01 = obj;
                if (this.A00 == AnonymousClass09.A0C) {
                    return false;
                }
                this.A00 = AnonymousClass09.A00;
                return true;
            case 2:
                return false;
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            this.A00 = AnonymousClass09.A01;
            Object obj = this.A01;
            this.A01 = null;
            return obj;
        }
        throw new NoSuchElementException();
    }
}
