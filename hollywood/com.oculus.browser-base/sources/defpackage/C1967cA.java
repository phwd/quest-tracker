package defpackage;

import J.N;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/* renamed from: cA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1967cA {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9588a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public boolean n;
    public int o;
    public boolean p;
    public int q;
    public boolean r;
    public long s;
    public long t;
    public long u;
    public long v;
    public long w;
    public C0197De1 x;
    public AbstractC0486Hz y;
    public boolean z;

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public void a() {
        Map map;
        Integer num;
        AbstractC0486Hz hz = this.y;
        if (hz != null && this.j && this.z) {
            ((C4017oA) hz).d(1, Boolean.valueOf(this.b));
            boolean z2 = this.b;
            int i2 = ((C4017oA) this.y).e;
            Pattern pattern = AA.f7657a;
            if (i2 == 3) {
                AbstractC3364kK0.g("Search.ContextualSearch.Ranker.NotSuppressed.ResultsSeen", !z2, 2);
            } else if (i2 == 2) {
                AbstractC3364kK0.g("Search.ContextualSearch.Ranker.WouldSuppress.ResultsSeen", !z2 ? 1 : 0, 2);
            }
            ((C4017oA) this.y).d(4, Boolean.valueOf(this.l));
            if (this.n) {
                ((C4017oA) this.y).d(2, Boolean.valueOf(this.p));
            }
            C0197De1 de1 = this.x;
            if (de1 != null) {
                AbstractC0486Hz hz2 = this.y;
                for (AbstractC5856yz yzVar : de1.f7902a) {
                    yzVar.f(hz2);
                }
            }
            C4017oA oAVar = (C4017oA) this.y;
            if (oAVar.a()) {
                if (!(oAVar.c == null || (map = oAVar.f) == null || map.isEmpty())) {
                    for (Map.Entry entry : oAVar.f.entrySet()) {
                        oAVar.c(((Integer) entry.getKey()).intValue(), entry.getValue());
                    }
                    Pattern pattern2 = AA.f7657a;
                    AbstractC3100ip1.f10165a.a("Search.ContextualSearch.Ranker.Recorded", true);
                    long j2 = oAVar.h;
                    if (j2 != 0) {
                        AbstractC0303Ez ez = oAVar.g;
                        Map map2 = oAVar.f;
                        Objects.requireNonNull((C0425Gz) ez);
                        int i3 = 0;
                        for (Map.Entry entry2 : map2.entrySet()) {
                            if (((Boolean) entry2.getValue()).booleanValue()) {
                                int intValue = ((Integer) entry2.getKey()).intValue();
                                if (intValue == 1) {
                                    num = 1;
                                } else if (intValue == 2) {
                                    num = 2;
                                } else if (intValue == 3) {
                                    num = 4;
                                } else if (intValue != 4) {
                                    num = null;
                                } else {
                                    num = 8;
                                }
                                i3 |= num.intValue();
                            }
                        }
                        PU0 pu0 = NU0.f8549a;
                        pu0.n("contextual_search_previous_interaction_encoded_outcomes", i3);
                        pu0.o("contextual_search_previous_interaction_event_id", j2);
                        pu0.o("contextual_search_previous_interaction_timestamp", System.currentTimeMillis());
                        oAVar.h = 0;
                    }
                }
                N.Mocie1e2(oAVar.f10536a, oAVar);
            }
            oAVar.b = false;
            oAVar.d = false;
            oAVar.f = null;
            oAVar.c = null;
            oAVar.e = 0;
            this.y = null;
        }
    }
}
