package defpackage;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/* renamed from: KA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KA1 extends TA1 {
    public final Map G;
    public final /* synthetic */ JA1 H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KA1(JA1 ja1, Map map) {
        super(ja1, null);
        this.H = ja1;
        this.G = map;
    }

    @Override // defpackage.TA1
    public final void a() {
        AbstractC5045uB1 ub1;
        TV tv = new TV(this.H.d);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (AbstractC2129d7 d7Var : this.G.keySet()) {
            Objects.requireNonNull(d7Var);
            if (!((LA1) this.G.get(d7Var)).c) {
                arrayList.add(d7Var);
            } else {
                arrayList2.add(d7Var);
            }
        }
        int i = -1;
        int i2 = 0;
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            while (i2 < size) {
                Object obj = arrayList2.get(i2);
                i2++;
                i = tv.a(this.H.c, (AbstractC2129d7) obj);
                if (i == 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList.size();
            while (i2 < size2) {
                Object obj2 = arrayList.get(i2);
                i2++;
                i = tv.a(this.H.c, (AbstractC2129d7) obj2);
                if (i != 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            ConnectionResult connectionResult = new ConnectionResult(i, null);
            JA1 ja1 = this.H;
            C2313eB1 eb1 = ja1.f8277a;
            eb1.e.sendMessage(eb1.e.obtainMessage(1, new NA1(this, ja1, connectionResult)));
            return;
        }
        JA1 ja12 = this.H;
        if (ja12.m && (ub1 = ja12.k) != null) {
            C4752sV0 sv0 = (C4752sV0) ub1;
            sv0.d(new C3242jg(sv0));
        }
        for (AbstractC2129d7 d7Var2 : this.G.keySet()) {
            AbstractC3071ig igVar = (AbstractC3071ig) this.G.get(d7Var2);
            Objects.requireNonNull(d7Var2);
            if (tv.a(this.H.c, d7Var2) != 0) {
                JA1 ja13 = this.H;
                C2313eB1 eb12 = ja13.f8277a;
                eb12.e.sendMessage(eb12.e.obtainMessage(1, new MA1(ja13, igVar)));
            } else {
                ((BaseGmsClient) d7Var2).d(igVar);
            }
        }
    }
}
