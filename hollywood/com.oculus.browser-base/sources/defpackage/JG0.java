package defpackage;

/* renamed from: JG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JG0 implements Runnable {
    public JG0(OG0 og0) {
    }

    public void run() {
        int i = AbstractC3983nz.f10523a.getInt("android_restore_status", 0);
        if (i != 5) {
            AbstractC3364kK0.g("Android.RestoreResult", i, 5);
            AbstractC3983nz.f10523a.edit().putInt("android_restore_status", 5).apply();
        }
    }
}
