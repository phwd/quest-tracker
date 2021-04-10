package defpackage;

import J.N;
import android.content.Context;
import com.oculus.browser.Preferences;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: WF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WF0 {

    /* renamed from: a  reason: collision with root package name */
    public static WF0 f9136a;
    public final Context b;
    public final PU0 c = NU0.f8549a;

    public WF0(Context context) {
        this.b = context;
    }

    public static WF0 a() {
        if (f9136a == null) {
            f9136a = new WF0(ContextUtils.getApplicationContext());
        }
        return f9136a;
    }

    public boolean b() {
        return !Preferences.getInstance().getBoolean("telemetryOptedOut", false);
    }

    public void c() {
        BrowserStartupControllerImpl browserStartupControllerImpl = (BrowserStartupControllerImpl) AbstractC4280pk.a();
        Objects.requireNonNull(browserStartupControllerImpl);
        Object obj = ThreadUtils.f10596a;
        if ((browserStartupControllerImpl.l || browserStartupControllerImpl.h) && browserStartupControllerImpl.i) {
            N.MmqfIJ4g(b());
        }
    }
}
