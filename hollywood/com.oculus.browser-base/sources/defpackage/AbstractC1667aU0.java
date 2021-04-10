package defpackage;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;

/* renamed from: aU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1667aU0 {
    public static File a(String str, String str2, boolean z, String str3) {
        File file;
        if (str2.isEmpty()) {
            file = c();
        } else {
            file = new File(str2);
        }
        if (!file.exists() && !file.mkdir()) {
            return null;
        }
        if (z) {
            return File.createTempFile(str, str3, file);
        }
        File file2 = new File(str2, AbstractC2531fV.f(str, str3));
        int i = 0;
        while (file2.exists()) {
            StringBuilder i2 = AbstractC2531fV.i(str);
            i++;
            i2.append(String.format(Locale.getDefault(), " (%d)", Integer.valueOf(i)));
            i2.append(str3);
            file2 = new File(str2, i2.toString());
        }
        file2.createNewFile();
        return file2;
    }

    public static void b(byte[] bArr, String str, Callback callback) {
        if (bArr.length == 0) {
            AbstractC1220Ua0.f("share", "Share failed -- Received image contains no data.", new Object[0]);
            return;
        }
        d(String.valueOf(System.currentTimeMillis()), new NT0(), new ST0(callback), new OT0(bArr), true, str);
    }

    public static File c() {
        return new File(AbstractC2417ep1.d(ContextUtils.getApplicationContext()), "screenshot");
    }

    public static void d(String str, YT0 yt0, ZT0 zt0, XT0 xt0, boolean z, String str2) {
        UT0 ut0 = new UT0(str, yt0, z, str2, xt0, zt0);
        Executor executor = AbstractC2032cb.f9616a;
        ut0.f();
        ((ExecutorC1463Ya) executor).execute(ut0.e);
    }
}
