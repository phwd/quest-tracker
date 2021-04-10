package X;

import com.facebook.acra.AppComponentStats;
import java.util.HashMap;

/* renamed from: X.p1  reason: case insensitive filesystem */
public final class C0926p1 implements IV {
    public C0928p3 A00;
    public final HashMap A01 = new HashMap();
    public final /* synthetic */ Object A02;
    public final /* synthetic */ C0935pA A03;

    @Override // X.IV
    public final void A5N(long j, long j2, int i, String str, Ie ie, Ix ix) {
        int intValue;
        if (i <= 7) {
            HashMap hashMap = this.A01;
            Number number = (Number) hashMap.get(str);
            if (number == null) {
                hashMap.put(str, 1);
                intValue = 1;
            } else {
                intValue = number.intValue() + 1;
                hashMap.put(str, Integer.valueOf(intValue));
            }
            if (intValue <= 20) {
                C0935pA pAVar = this.A03;
                C0846jq jqVar = (C0846jq) this.A02;
                C0847jr A002 = jqVar.A01.A00();
                C0846jq.A00(jqVar, A002);
                pAVar.A01(A002, "timeSinceStart", j);
                if (intValue == 20) {
                    str = AnonymousClass08.A04(str, "_duplicated");
                }
                C0847jr.A01(A002, AppComponentStats.ATTRIBUTE_NAME, str);
                if (ie != null) {
                    C0847jr A05 = A002.A05("data");
                    C0928p3 p3Var = this.A00;
                    if (p3Var == null) {
                        p3Var = new C0928p3(pAVar);
                        this.A00 = p3Var;
                    }
                    p3Var.A00 = A05;
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < ie.A00) {
                        String[] strArr = ie.A02;
                        p3Var.A5X(strArr[i3], strArr[i3 + 1], ie.A01[i2]);
                        i2++;
                        i3 += 2;
                    }
                    C0928p3 p3Var2 = this.A00;
                    p3Var2.A00 = null;
                    p3Var2.A01.clear();
                }
            }
        }
    }

    public C0926p1(C0935pA pAVar, Object obj) {
        this.A03 = pAVar;
        this.A02 = obj;
    }
}
