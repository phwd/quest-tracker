package defpackage;

import androidx.recyclerview.widget.GridLayoutManager;

/* renamed from: k91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3332k91 extends OW {
    public final /* synthetic */ GridLayoutManager c;
    public final /* synthetic */ I91 d;

    public C3332k91(I91 i91, GridLayoutManager gridLayoutManager) {
        this.d = i91;
        this.c = gridLayoutManager;
    }

    @Override // defpackage.OW
    public int c(int i) {
        int i2 = ((C4765sb0) this.d.g.G.get(i)).f11283a;
        if (i2 == 3 || i2 == 6) {
            return this.c.H;
        }
        return 1;
    }
}
