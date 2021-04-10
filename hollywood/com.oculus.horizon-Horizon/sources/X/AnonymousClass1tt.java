package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1tt  reason: invalid class name */
public final class AnonymousClass1tt {
    public final AnonymousClass1u6 A00;
    @GuardedBy("mOpenTracesLock")
    public final AnonymousClass0VE<RunnableC10551tn> A01;

    public static boolean A00(@Nullable RunnableC10551tn r1) {
        if (r1 == null || !r1.A0F) {
            return false;
        }
        return true;
    }

    @Nullable
    public final RunnableC10551tn A01(int i) {
        AnonymousClass1u6 r1 = this.A00;
        r1.A00();
        try {
            return this.A01.get(i);
        } finally {
            r1.unlock();
        }
    }

    @Nullable
    public final RunnableC10551tn A02(int i) {
        AnonymousClass1u6 r2 = this.A00;
        r2.lock();
        try {
            AnonymousClass0VE<RunnableC10551tn> r1 = this.A01;
            RunnableC10551tn r0 = r1.get(i);
            if (r0 != null) {
                r1.remove(i);
            }
            return r0;
        } finally {
            r2.unlock();
        }
    }

    public final boolean A03(int i) {
        AnonymousClass1u6 r1 = this.A00;
        r1.A00();
        try {
            return A00(this.A01.get(i));
        } finally {
            r1.unlock();
        }
    }

    public AnonymousClass1tt(AnonymousClass0VH r2, AnonymousClass1u6 r3) {
        this.A01 = r2.A5b();
        this.A00 = r3;
    }
}
