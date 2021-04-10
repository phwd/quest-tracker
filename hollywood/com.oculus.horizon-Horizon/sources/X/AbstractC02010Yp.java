package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.0Yp  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02010Yp {
    public BroadcastReceiver A00;
    public C05990mI A01;
    public final Context A02;
    public final AnonymousClass0nN A03;
    public final C01890Xx A04;
    public final Integer A05;
    public final String A06;

    public void A03(String str, Intent intent) {
    }

    public void A04(String str, String str2, EnumC01870Xu r3) {
    }

    public abstract boolean A05(AbstractC02020Yq v);

    public final C05990mI A01() {
        String str;
        C05990mI r2 = this.A01;
        if (r2 != null) {
            return r2;
        }
        Context context = this.A02;
        String str2 = this.A06;
        if (1 - this.A05.intValue() != 0) {
            str = "FBNS_LITE";
        } else {
            str = "FBNS";
        }
        C05990mI r22 = new C05990mI(context, AnonymousClass006.A00(str2, '_', str), this.A03);
        this.A01 = r22;
        return r22;
    }

    public AbstractC02010Yp(Context context, C01890Xx r2, AnonymousClass0nN r3, String str, Integer num) {
        this.A02 = context;
        this.A04 = r2;
        this.A03 = r3;
        this.A06 = str;
        this.A05 = num;
    }

    public long A02(String str, String str2, boolean z) {
        return A01().A00(str);
    }
}
