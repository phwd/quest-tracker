package defpackage;

import J.N;
import org.chromium.components.embedder_support.delegate.ColorChooserAndroid;

/* renamed from: Hv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0478Hv implements AbstractC0656Ks0 {
    public final /* synthetic */ ColorChooserAndroid F;

    public C0478Hv(ColorChooserAndroid colorChooserAndroid) {
        this.F = colorChooserAndroid;
    }

    @Override // defpackage.AbstractC0656Ks0
    public void a(int i) {
        this.F.f10841a.dismiss();
        ColorChooserAndroid colorChooserAndroid = this.F;
        N.M2zph6xH(colorChooserAndroid.b, colorChooserAndroid, i);
    }
}
