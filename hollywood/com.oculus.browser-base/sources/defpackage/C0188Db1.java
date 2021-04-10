package defpackage;

import androidx.recyclerview.widget.GridLayoutManager;

/* renamed from: Db1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0188Db1 extends OW {
    public final /* synthetic */ IK0 c;
    public final /* synthetic */ C0371Gb1 d;

    public C0188Db1(C0371Gb1 gb1, IK0 ik0) {
        this.d = gb1;
        this.c = ik0;
    }

    @Override // defpackage.OW
    public int c(int i) {
        if (this.d.e.G.T.d(i) == 4) {
            return ((GridLayoutManager) this.c).H;
        }
        return 1;
    }
}
