package defpackage;

/* renamed from: L4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L4 implements Runnable {
    public final /* synthetic */ O4 F;

    public L4(O4 o4) {
        this.F = o4;
    }

    public void run() {
        if (this.F.K.isShowing()) {
            this.F.K.dismiss();
        }
    }
}
