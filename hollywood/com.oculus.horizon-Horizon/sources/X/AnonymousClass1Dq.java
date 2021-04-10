package X;

import java.util.Calendar;

/* renamed from: X.1Dq  reason: invalid class name */
public final class AnonymousClass1Dq {
    public final int A00;
    public final long A01;
    public final Integer A02;
    public final String A03;

    public final boolean A00() {
        String str;
        if (this.A00 < 0 || (str = this.A03) == null || str.isEmpty()) {
            return false;
        }
        return true;
    }

    public AnonymousClass1Dq(int i, String str, long j) {
        this.A00 = i;
        this.A03 = str;
        this.A01 = Calendar.getInstance().getTimeInMillis() + (j * 1000);
        this.A02 = AnonymousClass007.A00;
    }

    public AnonymousClass1Dq(int i, String str, long j, String str2) {
        Integer num;
        this.A00 = i;
        this.A03 = str;
        this.A01 = j;
        if (str2.equals("ENCRYPTION_WITH_TAGGING")) {
            num = AnonymousClass007.A00;
        } else if (str2.equals("PLAINTEXT_WITH_TAGGING")) {
            num = AnonymousClass007.A01;
        } else {
            throw new IllegalArgumentException(str2);
        }
        this.A02 = num;
    }

    public AnonymousClass1Dq(AnonymousClass1Dq r3, Integer num) {
        this.A00 = r3.A00;
        this.A03 = r3.A03;
        this.A01 = r3.A01;
        this.A02 = num;
    }
}
