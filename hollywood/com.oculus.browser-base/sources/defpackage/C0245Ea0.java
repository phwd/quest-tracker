package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;

/* renamed from: Ea0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0245Ea0 extends AbstractC2406em {
    public final /* synthetic */ boolean G;
    public final /* synthetic */ LocationBarTablet H;

    public C0245Ea0(LocationBarTablet locationBarTablet, boolean z) {
        this.H = locationBarTablet;
        this.G = z;
    }

    @Override // defpackage.AbstractC2406em
    public void a(Animator animator) {
        this.H.q(false);
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        LocationBarTablet locationBarTablet = this.H;
        boolean z = this.G;
        locationBarTablet.b(z, z);
    }
}
