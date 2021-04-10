package defpackage;

import android.content.res.Resources;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.components.messages.MessageContainer;

/* renamed from: Kj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0638Kj0 extends AbstractC2059ck {
    public MessageContainer F;
    public C1551Zj G;

    public C0638Kj0(MessageContainer messageContainer, C1551Zj zj) {
        this.F = messageContainer;
        this.G = zj;
        zj.Y.b(this);
    }

    public final int a() {
        if (this.G.T == 0) {
            return 0;
        }
        Resources resources = this.F.getResources();
        return (this.G.T - resources.getDimensionPixelOffset(R.dimen.f20750_resource_name_obfuscated_RES_2131165694)) - resources.getDimensionPixelOffset(R.dimen.f20830_resource_name_obfuscated_RES_2131165702);
    }

    public final void b() {
        NA na = (NA) this.F.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) na).topMargin = a();
        this.F.setLayoutParams(na);
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        b();
    }

    @Override // defpackage.AbstractC2230dk, defpackage.AbstractC2059ck
    public void k(int i, int i2) {
        b();
    }
}
