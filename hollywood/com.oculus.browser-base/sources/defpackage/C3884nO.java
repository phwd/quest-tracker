package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* renamed from: nO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3884nO {

    /* renamed from: a  reason: collision with root package name */
    public final Resources f10488a;
    public final KN0 b;
    public final int c;

    public C3884nO(Context context) {
        Resources resources = context.getResources();
        this.f10488a = resources;
        this.c = resources.getDimensionPixelSize(R.dimen.f20290_resource_name_obfuscated_RES_2131165648);
        this.b = AbstractC4055oO.a(resources);
    }

    public void a(String str, Callback callback) {
        X60 x60 = new X60(Profile.b());
        GURL gurl = new GURL(str);
        if (gurl.b) {
            x60.b(gurl, this.c, new C3371kO(this, str, callback));
        }
    }

    public Drawable b(String str) {
        return AbstractC4055oO.c(null, str, R.color.f11180_resource_name_obfuscated_RES_2131099808, this.b, this.f10488a, this.c);
    }
}
