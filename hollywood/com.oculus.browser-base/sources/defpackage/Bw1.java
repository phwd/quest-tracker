package defpackage;

import J.N;
import android.content.Context;
import android.net.Uri;
import android.view.ContextThemeWrapper;
import android.view.InflateException;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Bw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Bw1 {

    /* renamed from: a  reason: collision with root package name */
    public static Bw1 f7774a;
    public final Set b = new HashSet();
    public final Map c = new HashMap();
    public int d;
    public ViewGroup e;
    public WebContents f;
    public long g;
    public Aw1 h;
    public boolean i;

    public static Bw1 a() {
        Object obj = ThreadUtils.f10596a;
        if (f7774a == null) {
            f7774a = new Bw1();
        }
        return f7774a;
    }

    public void b(Context context, int i2, int i3) {
        ViewGroup viewGroup;
        Object obj = ThreadUtils.f10596a;
        if (this.e == null || this.d != i2) {
            try {
                TraceEvent j0 = TraceEvent.j0("WarmupManager.inflateViewHierarchy");
                try {
                    ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, ChromeActivity.X0());
                    viewGroup = (ViewGroup) AbstractC2471f70.a(contextThemeWrapper, R.layout.f39200_resource_name_obfuscated_RES_2131624229, new FrameLayout(contextThemeWrapper));
                    if (i2 != -1) {
                        ViewStub viewStub = (ViewStub) viewGroup.findViewById(R.id.control_container_stub);
                        viewStub.setLayoutResource(i2);
                        viewStub.inflate();
                    }
                    DA da = (DA) viewGroup.findViewById(R.id.control_container);
                    if (!(i3 == -1 || da == null)) {
                        ((ToolbarControlContainer) da).d(i3);
                    }
                    if (j0 != null) {
                        j0.close();
                    }
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } catch (InflateException e2) {
                AbstractC1220Ua0.a("WarmupManager", "Inflation exception.", e2);
                viewGroup = null;
            }
            this.e = viewGroup;
            this.d = i2;
            return;
        }
        return;
        throw th;
    }

    public void c(Profile profile, String str) {
        Object obj = ThreadUtils.f10596a;
        Uri parse = Uri.parse(str);
        if (parse != null) {
            String scheme = parse.normalizeScheme().getScheme();
            if (!"http".equals(scheme) && !"https".equals(scheme)) {
                return;
            }
            if (this.b.contains(str)) {
                this.c.put(str, profile);
            } else {
                N.Mw6Ub3GC(profile, str);
            }
        }
    }

    public final void d(int i2) {
        if (this.i) {
            AbstractC3364kK0.g("CustomTabs.SpareWebContents.Status2", i2, 5);
        }
    }

    public WebContents e(boolean z, boolean z2, boolean z3) {
        WebContents webContents;
        Object obj = ThreadUtils.f10596a;
        if (z || (webContents = this.f) == null) {
            return null;
        }
        this.f = null;
        webContents.Q(this.h);
        this.h = null;
        if (!z2) {
            webContents.O();
        }
        d(this.i == z3 ? 1 : 4);
        return webContents;
    }
}
