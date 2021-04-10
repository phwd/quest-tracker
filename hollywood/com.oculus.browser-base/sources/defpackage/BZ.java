package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;

/* renamed from: BZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BZ {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CZ f7745a;

    public BZ(CZ cz) {
        this.f7745a = cz;
    }

    public void a(boolean z, WebContents webContents) {
        Objects.requireNonNull(this.f7745a);
        PU0 pu0 = NU0.f8549a;
        pu0.c("Chrome.ImageDescriptions.JustOnceCount");
        Objects.requireNonNull(this.f7745a);
        pu0.m("Chrome.ImageDescriptions.DontAskAgain", z);
        N.MlWjE4_y(webContents);
    }

    public void b(boolean z, Profile profile) {
        N.Mf2ABpoH(CZ.a(this.f7745a, profile).f10883a, "settings.a11y.enable_accessibility_image_labels_only_on_wifi", z);
    }
}
