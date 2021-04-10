package X;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0GD  reason: invalid class name */
public abstract class AnonymousClass0GD {
    @GuardedBy("BatchLockState.this")
    public int A00;
    @GuardedBy("this")
    @Nullable
    public Object A01;
    public final Object A02;
    public final /* synthetic */ AnonymousClass0GE A03;

    public void A00() {
    }

    public abstract void A02();

    public abstract void A03();

    public final synchronized void A04(Object obj) {
        if (this.A01 != obj) {
            throw new IllegalMonitorStateException("Lock is not held by the provided owner");
        }
    }

    public final synchronized void A05(Object obj) {
        A04(obj);
        try {
            A03();
        } finally {
            this.A01 = null;
            notifyAll();
        }
    }

    public abstract boolean A06();

    public abstract boolean A07();

    public final synchronized String toString() {
        String str;
        StringBuilder sb;
        Object obj = this.A01;
        if (obj != null) {
            str = obj.toString();
        } else {
            str = "null";
        }
        sb = new StringBuilder();
        sb.append("[key=");
        sb.append(this.A02);
        sb.append(",refCount=");
        sb.append(this.A00);
        sb.append(",lockOwner=");
        sb.append(str);
        sb.append(",isDeleted=");
        sb.append(A07());
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass0GD(AnonymousClass0GE r1, Object obj) {
        this.A03 = r1;
        this.A02 = obj;
    }

    public final void A01() {
        AnonymousClass0GE r2 = this.A03;
        synchronized (r2) {
            int i = this.A00 - 1;
            this.A00 = i;
            if (i < 0) {
                throw new IllegalStateException("Unbalance calls to acquire/release");
            } else if (i == 0) {
                A00();
                r2.A00.remove(this.A02);
            }
        }
    }
}
