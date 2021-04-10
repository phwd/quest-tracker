package com.oculus.vrshell.panelservice;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import android.view.Surface;
import java.util.Collections;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PanelService extends Service {
    public Messenger F = null;
    public C5848yw0 G = null;

    static {
        System.loadLibrary("panelappsdk");
    }

    public long a(Surface surface, Map map, Bundle bundle) {
        throw new UnsupportedOperationException("Panel applications should provide an instance.");
    }

    public void b() {
    }

    public String c(int i) {
        long nanoTime = System.nanoTime();
        C4412qV0 a2 = this.G.f11710a.a(i);
        Log.i("CppPanelService", String.format("verifyVrShellUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.G.b), Integer.valueOf(i), a2, Long.valueOf((System.nanoTime() - nanoTime) / 1000000)));
        if (!a2.c.contains("com.oculus.vrshell")) {
            return String.format("Expected connection to APK %s and got %s instead; failing authentication.", "com.oculus.vrshell", Collections.min(a2.c));
        } else if (a2.f11144a) {
            return null;
        } else {
            return String.format("Expected %s APK to be signed with trusted certificate; failing authentication.", "com.oculus.vrshell");
        }
    }

    public final native void nativeInitializePanelInstance(long j, int i);

    public IBinder onBind(Intent intent) {
        return this.F.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.F = new Messenger(new HandlerC5338vw0(this));
        this.G = new C5848yw0(this, !Build.TYPE.equals("user"));
    }

    public void onDestroy() {
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}
