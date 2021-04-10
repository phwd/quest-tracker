package defpackage;

import android.content.Context;
import android.net.Uri;
import java.io.File;
import org.chromium.base.ContextUtils;

/* renamed from: XP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XP {
    public Uri a(File file) {
        Context applicationContext = ContextUtils.getApplicationContext();
        return WP.b(applicationContext, applicationContext.getPackageName() + ".FileProvider", file);
    }
}
