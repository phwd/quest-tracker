package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.features.start_surface.FeedLoadingLayout;

/* renamed from: zO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5927zO {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11742a;
    public final ViewGroup b;
    public FeedLoadingLayout c;

    public C5927zO(Activity activity, ViewGroup viewGroup, boolean z) {
        this.b = viewGroup;
        this.f11742a = new ContextThemeWrapper(activity, z ? R.style.f68450_resource_name_obfuscated_RES_2132017418 : R.style.f68730_resource_name_obfuscated_RES_2132017446);
    }
}
