package defpackage;

import android.util.Log;

/* renamed from: JN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class JN extends AbstractC4712sE {
    public final RE1 b = new RE1();
    public final TE1 c;
    public final Object d = new Object();
    public boolean e = true;

    public JN(TE1 te1, AbstractC3341kC1 kc1) {
        this.c = te1;
    }

    @Override // defpackage.AbstractC4712sE
    public final void a() {
        super.a();
        synchronized (this.d) {
            if (this.e) {
                this.c.d();
                this.e = false;
            }
        }
    }

    public final void finalize() {
        try {
            synchronized (this.d) {
                if (this.e) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    a();
                }
            }
        } finally {
            super.finalize();
        }
    }
}
