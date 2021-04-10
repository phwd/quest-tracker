package defpackage;

import android.app.Activity;
import android.content.Context;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: et  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2427et {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9887a;
    public final BrowserContextHandle b;
    public AbstractC1528Zb0 c;

    public C2427et(Context context, BrowserContextHandle browserContextHandle) {
        this.f9887a = context;
        this.b = browserContextHandle;
    }

    public AbstractC1528Zb0 a() {
        if (this.c == null) {
            this.c = new C2086ct(this);
        }
        return this.c;
    }

    public void b(Activity activity) {
        C2535fX.a().b(activity, activity.getString(R.string.f52640_resource_name_obfuscated_RES_2131952581), Profile.b(), null);
    }
}
