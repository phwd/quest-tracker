package defpackage;

import android.os.RemoteException;
import java.util.concurrent.ExecutionException;

/* renamed from: Wn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Wn1 implements Runnable {
    public final AbstractC2073co1 F;
    public final C4649rt0 G;
    public final AbstractFutureC5208v90 H;

    public Wn1(AbstractC2073co1 co1, C4649rt0 rt0, AbstractFutureC5208v90 v90) {
        this.F = co1;
        this.G = rt0;
        this.H = v90;
    }

    public void run() {
        AbstractC2073co1 co1 = this.F;
        try {
            co1.a(this.G, (C3268jo1) this.H.get());
        } catch (RemoteException | InterruptedException e) {
            AbstractC1220Ua0.f("TWAClient", "Failed to execute TWA command.", e);
        } catch (SecurityException | ExecutionException e2) {
            AbstractC1220Ua0.f("TWAClient", "Failed to connect to TWA to execute command", e2);
            co1.b();
        }
    }
}
