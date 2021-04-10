package defpackage;

import java.util.regex.Pattern;

/* renamed from: AJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AJ0 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7666a = false;
    public boolean b = false;

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.f7666a;
    }

    @Override // defpackage.AbstractC5856yz
    public void f(AbstractC0486Hz hz) {
        ((C4017oA) hz).d(3, Boolean.valueOf(this.b));
    }

    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2) {
            boolean z3 = this.f7666a;
            boolean z4 = this.b;
            Pattern pattern = AA.f7657a;
            AbstractC3364kK0.g("Search.ContextualSearchQuickAnswerSeen", z ? z3 ? z4 ? 0 : 2 : 4 : z3 ? z4 ? 1 : 3 : 5, 6);
        }
    }
}
