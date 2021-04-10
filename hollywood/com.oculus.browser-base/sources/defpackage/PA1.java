package defpackage;

import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* renamed from: PA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PA1 extends TA1 {
    public final ArrayList G;
    public final /* synthetic */ JA1 H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PA1(JA1 ja1, ArrayList arrayList) {
        super(ja1, null);
        this.H = ja1;
        this.G = arrayList;
    }

    @Override // defpackage.TA1
    public final void a() {
        Set set;
        JA1 ja1 = this.H;
        VA1 va1 = ja1.f8277a.m;
        if (ja1.r == null) {
            set = Collections.emptySet();
        } else {
            HashSet hashSet = new HashSet(ja1.r.b);
            Map map = ja1.r.d;
            for (C2470f7 f7Var : map.keySet()) {
                if (!ja1.f8277a.g.containsKey(f7Var.a())) {
                    C5859z.a(map.get(f7Var));
                    Objects.requireNonNull(null);
                    throw null;
                }
            }
            set = hashSet;
        }
        va1.p = set;
        ArrayList arrayList = this.G;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            JA1 ja12 = this.H;
            ((BaseGmsClient) ((AbstractC2129d7) obj)).j(ja12.o, ja12.f8277a.m.p);
        }
    }
}
