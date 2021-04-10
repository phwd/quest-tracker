package defpackage;

import android.os.PowerManager;
import android.util.Log;

/* renamed from: Xq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1444Xq0 extends Thread {
    public final /* synthetic */ C1505Yq0 F;

    public C1444Xq0(C1505Yq0 yq0) {
        this.F = yq0;
    }

    public void run() {
        PowerManager.WakeLock wakeLock = null;
        try {
            C1505Yq0 yq0 = this.F;
            wakeLock = C1505Yq0.a(yq0, yq0.f9300a.F.f9554a);
            while (this.F.f9300a.isAlive()) {
                Log.i("OculusBrowserACRAClient", "ACRAInitWorker is still alive, sleeping for 2 seconds to let it finish.");
                Thread.sleep(2000);
            }
            C1505Yq0 yq02 = this.F;
            yq02.e = yq02.f9300a.f9175J.d();
            if (wakeLock == null || !wakeLock.isHeld()) {
                return;
            }
        } catch (Exception e) {
            Log.e("OculusBrowserACRAClient", "Failed to send crash reports", e);
            if (0 == 0 || !wakeLock.isHeld()) {
                return;
            }
        } catch (Throwable th) {
            if (0 != 0 && wakeLock.isHeld()) {
                wakeLock.release();
            }
            throw th;
        }
        wakeLock.release();
    }
}
