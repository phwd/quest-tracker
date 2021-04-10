package defpackage;

import java.util.List;

/* renamed from: dd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2211dd1 implements D91 {

    /* renamed from: a  reason: collision with root package name */
    public final C3919nd1 f9795a;

    public C2211dd1(C3919nd1 nd1) {
        this.f9795a = nd1;
    }

    @Override // defpackage.D91
    public void a(int i) {
        C3919nd1 nd1 = this.f9795a;
        List i2 = nd1.i(i);
        if (i2.size() == 0) {
            i2 = null;
        }
        nd1.p.e(i2);
        AbstractC3535lK0.a("TabGridDialog.ExpandedFromSwitcher");
    }
}
