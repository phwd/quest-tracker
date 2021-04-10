package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: dt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2256dt implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final String f9815a;
    public final Callback b;
    public final int c;
    public final C3542lO d;
    public final /* synthetic */ C2427et e;

    public C2256dt(C2427et etVar, String str, Callback callback, C2086ct ctVar) {
        this.e = etVar;
        this.f9815a = str;
        this.b = callback;
        int dimensionPixelSize = etVar.f9887a.getResources().getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
        this.c = dimensionPixelSize;
        C3542lO lOVar = new C3542lO();
        this.d = lOVar;
        if (!lOVar.c(Profile.b(), str, dimensionPixelSize, this)) {
            lOVar.b();
            Resources resources = etVar.f9887a.getResources();
            int round = Math.round(((float) dimensionPixelSize) / resources.getDisplayMetrics().density);
            float f = (float) round;
            callback.onResult(new KN0(resources, round, round, Math.round(0.125f * f), -6908266, Math.round(f * 0.625f)).c(str, false));
        }
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        this.d.b();
        if (bitmap == null) {
            Resources resources = this.e.f9887a.getResources();
            int round = Math.round(((float) this.c) / resources.getDisplayMetrics().density);
            float f = (float) round;
            bitmap = new KN0(resources, round, round, Math.round(0.125f * f), -6908266, Math.round(f * 0.625f)).b(this.f9815a);
        }
        this.b.onResult(bitmap);
    }
}
