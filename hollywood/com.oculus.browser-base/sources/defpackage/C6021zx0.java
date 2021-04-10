package defpackage;

import android.graphics.drawable.Drawable;
import java.util.Objects;
import org.chromium.chrome.browser.keyboard_accessory.sheet_tabs.PasswordAccessoryInfoView;

/* renamed from: zx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C6021zx0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0117Bx0 f11783a;
    public final PasswordAccessoryInfoView b;
    public final C3319k50 c;

    public C6021zx0(C0117Bx0 bx0, PasswordAccessoryInfoView passwordAccessoryInfoView, C3319k50 k50) {
        this.f11783a = bx0;
        this.b = passwordAccessoryInfoView;
        this.c = k50;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0117Bx0 bx0 = this.f11783a;
        PasswordAccessoryInfoView passwordAccessoryInfoView = this.b;
        C3319k50 k50 = this.c;
        Drawable drawable = (Drawable) obj;
        Objects.requireNonNull(bx0);
        if (k50.f10260a.equals(bx0.Z)) {
            passwordAccessoryInfoView.a(drawable);
        }
    }
}
