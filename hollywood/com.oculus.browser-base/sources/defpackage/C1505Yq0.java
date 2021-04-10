package defpackage;

import android.util.Log;
import com.oculus.browser.Preferences;
import com.oculus.browser.SystemSettings;
import com.oculus.os.SettingsManager;
import java.lang.Thread;
import java.util.Date;

/* renamed from: Yq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1505Yq0 implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public C1383Wq0 f9300a;
    public C1444Xq0 b;
    public Thread.UncaughtExceptionHandler c = Thread.getDefaultUncaughtExceptionHandler();
    public Date d;
    public int e;

    public C1505Yq0(AbstractC1876bg bgVar, String str, boolean z, String str2) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        C1383Wq0 wq0 = new C1383Wq0(this, bgVar, str, z, str2);
        this.f9300a = wq0;
        wq0.start();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001d A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.os.PowerManager.WakeLock a(defpackage.C1505Yq0 r4, android.content.Context r5) {
        /*
            java.util.Objects.requireNonNull(r4)
            java.lang.String r4 = "android.permission.WAKE_LOCK"
            android.content.pm.PackageManager r0 = r5.getPackageManager()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x000e
            goto L_0x001a
        L_0x000e:
            java.lang.String r3 = r5.getPackageName()     // Catch:{ RuntimeException -> 0x001a }
            int r4 = r0.checkPermission(r4, r3)     // Catch:{ RuntimeException -> 0x001a }
            if (r4 != 0) goto L_0x001a
            r4 = r1
            goto L_0x001b
        L_0x001a:
            r4 = r2
        L_0x001b:
            if (r4 != 0) goto L_0x001f
            r4 = 0
            goto L_0x0035
        L_0x001f:
            java.lang.String r4 = "power"
            java.lang.Object r4 = r5.getSystemService(r4)
            android.os.PowerManager r4 = (android.os.PowerManager) r4
            java.lang.String r5 = "ACRA:wakelock"
            android.os.PowerManager$WakeLock r4 = r4.newWakeLock(r1, r5)
            r4.setReferenceCounted(r2)
            r0 = 1000(0x3e8, double:4.94E-321)
            r4.acquire(r0)
        L_0x0035:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1505Yq0.a(Yq0, android.content.Context):android.os.PowerManager$WakeLock");
    }

    public void b() {
        C1444Xq0 xq0 = this.b;
        boolean z = true;
        if (xq0 != null && xq0.isAlive()) {
            Log.e("OculusBrowserACRAClient", "Already trying to send crash reports.");
            return;
        }
        if (Preferences.getInstance().getBoolean("telemetryOptedOut", false) || !SystemSettings.getBoolean(SettingsManager.CRASH_REPORTS_ENABLED, true)) {
            z = false;
        }
        if (!z) {
            Log.i("OculusBrowserACRAClient", "Not uploading crash reports. Telemetry disabled.");
            return;
        }
        this.e = 0;
        this.d = new Date();
        C1444Xq0 xq02 = new C1444Xq0(this);
        this.b = xq02;
        xq02.start();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        try {
            if (this.f9300a.isAlive()) {
                Thread.sleep(2000);
            }
            if (this.f9300a.isAlive()) {
                Log.e("OculusBrowserACRAClient", "acra worker thread is still alive, we haven't init our crash reporting logic so we can't handle this exception.");
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.c;
                if (uncaughtExceptionHandler2 != null) {
                    uncaughtExceptionHandler2.uncaughtException(thread, th);
                    return;
                }
                return;
            }
            this.f9300a.f9175J.u(th);
            uncaughtExceptionHandler = this.c;
            if (uncaughtExceptionHandler == null) {
                return;
            }
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } catch (Exception e2) {
            Log.e("OculusBrowserACRAClient", "Failed to handle uncaught exception", e2);
            uncaughtExceptionHandler = this.c;
            if (uncaughtExceptionHandler == null) {
            }
        } catch (Throwable th2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler3 = this.c;
            if (uncaughtExceptionHandler3 != null) {
                uncaughtExceptionHandler3.uncaughtException(thread, th);
            }
            throw th2;
        }
    }
}
