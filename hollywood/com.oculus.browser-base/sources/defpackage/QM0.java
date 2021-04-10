package defpackage;

/* renamed from: QM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QM0 extends C4284pl0 {
    public QM0(SM0 sm0, C4935tb0 tb0) {
        super(tb0);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // defpackage.C4284pl0
    public long getItemId(int i) {
        if (((C4765sb0) this.F.G.get(i)).f11283a == 2 || ((C4765sb0) this.F.G.get(i)).f11283a == 3) {
            return (long) ((C4765sb0) this.F.G.get(i)).b.f(YM0.b);
        }
        return -1;
    }

    public boolean isEnabled(int i) {
        return ((C4765sb0) this.F.G.get(i)).f11283a == 2 || ((C4765sb0) this.F.G.get(i)).f11283a == 3;
    }
}
