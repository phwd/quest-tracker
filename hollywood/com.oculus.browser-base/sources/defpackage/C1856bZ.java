package defpackage;

import android.widget.PopupWindow;
import org.chromium.components.infobars.InfoBar;

/* renamed from: bZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1856bZ implements PopupWindow.OnDismissListener, AbstractC2282e10, AbstractC3478l10 {
    public final WY F;
    public AbstractC1676aZ G;

    public C1856bZ(WY wy) {
        this.F = wy;
    }

    @Override // defpackage.AbstractC3478l10
    public void a(boolean z) {
    }

    @Override // defpackage.AbstractC2282e10
    public void b(int i) {
    }

    @Override // defpackage.AbstractC3478l10
    public void c(C3649m10 m10, float f) {
    }

    @Override // defpackage.AbstractC2282e10
    public void d(C10 c10) {
        if (c10 != null) {
            InfoBar infoBar = (InfoBar) c10;
        }
        AbstractC1676aZ aZVar = this.G;
        if (aZVar != null && aZVar.f9436a != null) {
            aZVar.b.c();
        }
    }

    public void onDismiss() {
        AbstractC1676aZ aZVar = this.G;
        if (aZVar != null) {
            this.F.b.dismissed(aZVar.c);
            this.G = null;
        }
    }
}
