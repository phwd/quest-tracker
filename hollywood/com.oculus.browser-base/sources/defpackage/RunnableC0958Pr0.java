package defpackage;

import android.content.Context;
import android.net.Uri;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: Pr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0958Pr0 implements Runnable {
    public final String F;
    public final String G;
    public final Callback H;

    public RunnableC0958Pr0(String str, String str2, Callback callback) {
        this.F = str;
        this.G = str2;
        this.H = callback;
    }

    public void run() {
        Uri uri;
        String str = this.F;
        String str2 = this.G;
        Callback callback = this.H;
        File file = new File(str);
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            uri = WP.b(applicationContext, applicationContext.getPackageName() + ".FileProvider", file);
        } catch (Exception unused) {
            uri = Uri.parse(str2);
        }
        PostTask.b(Zo1.f9374a, callback.a(uri), 0);
    }
}
