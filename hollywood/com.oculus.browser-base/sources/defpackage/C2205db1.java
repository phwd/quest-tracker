package defpackage;

import java.util.Iterator;
import java.util.Objects;

/* renamed from: db1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2205db1 implements AbstractC3400kb1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f9792a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ C4766sb1 c;

    public C2205db1(C4766sb1 sb1, boolean z, boolean z2) {
        this.c = sb1;
        this.f9792a = z;
        this.b = z2;
    }

    @Override // defpackage.AbstractC3400kb1
    public void a(int i, int i2, String str, Boolean bool, boolean z, boolean z2) {
        C4766sb1 sb1 = this.c;
        if (sb1.v) {
            if (!sb1.b.c() || !this.c.h.contains(Integer.valueOf(i2))) {
                this.c.h.add(Integer.valueOf(i2));
            } else {
                return;
            }
        }
        C4596rb1 rb1 = new C4596rb1(i2, i, bool, str, Boolean.valueOf(this.f9792a));
        if (this.f9792a || ((!z2 || !this.b) && (!z || this.b))) {
            this.c.g.addLast(rb1);
        } else {
            this.c.g.addFirst(rb1);
        }
        Iterator it = this.c.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                Objects.requireNonNull((C0307Fa1) uq0.next());
            } else {
                return;
            }
        }
    }
}
