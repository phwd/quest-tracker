package X;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.Fy  reason: case insensitive filesystem */
public abstract class AbstractC0081Fy {
    @GuardedBy("BatchLockState.this")
    public int A00;
    @GuardedBy("this")
    @Nullable
    public Object A01;
    public final Object A02;
    public final /* synthetic */ Fz A03;

    public static synchronized void A00(AbstractC0081Fy fy, Object obj) {
        synchronized (fy) {
            if (fy.A01 == obj) {
                throw new IllegalStateException("Trying to re-enter the lock");
            }
        }
    }

    public abstract String A01();

    public void A02() {
    }

    public abstract void A04();

    public abstract void A05();

    public abstract void A06();

    public final synchronized void A07(Object obj) {
        if (this.A01 != obj) {
            throw new IllegalMonitorStateException("Lock is not held by the provided owner");
        }
    }

    public final synchronized void A08(Object obj) {
        A07(obj);
        try {
            A06();
        } finally {
            this.A01 = null;
            notifyAll();
        }
    }

    public abstract boolean A09();

    public abstract boolean A0A();

    public final synchronized boolean A0B(Object obj) {
        boolean z;
        A00(this, obj);
        if (A09() || this.A01 != null) {
            z = false;
        } else {
            z = A0A();
            if (z) {
                this.A01 = obj;
            }
        }
        return z;
    }

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
        sb.append(A09());
        sb.append("]");
        return sb.toString();
    }

    public AbstractC0081Fy(Fz fz, Object obj) {
        this.A03 = fz;
        this.A02 = obj;
    }

    public final void A03() {
        Fz fz = this.A03;
        synchronized (fz) {
            int i = this.A00 - 1;
            this.A00 = i;
            if (i < 0) {
                throw new IllegalStateException("Unbalance calls to acquire/release");
            } else if (i == 0) {
                A02();
                fz.A00.remove(this.A02);
            }
        }
    }
}
