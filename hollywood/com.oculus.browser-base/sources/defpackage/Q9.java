package defpackage;

import J.N;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import java.nio.ByteBuffer;
import org.chromium.content.browser.AppWebMessagePort;

/* renamed from: Q9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q9 extends Handler implements AbstractC3255jk0 {
    public final FE0 F;

    public Q9(Looper looper, FE0 fe0) {
        super(looper);
        this.F = fe0;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        byte[] bArr;
        try {
            C1719an1 d = C1719an1.d(gj0.a().b());
            int length = d.e.length;
            AppWebMessagePort[] appWebMessagePortArr = new AppWebMessagePort[length];
            for (int i = 0; i < length; i++) {
                appWebMessagePortArr[i] = new AppWebMessagePort(new R9(d.e[i]));
            }
            P9 p9 = new P9(null);
            C4442qh qhVar = d.d.d;
            if (qhVar.f11638a == 0) {
                bArr = qhVar.b;
            } else {
                C4612rh rhVar = qhVar.c;
                ByteBuffer B = rhVar.d.B(0, (long) rhVar.e, FU0.c);
                byte[] bArr2 = new byte[rhVar.e];
                B.get(bArr2);
                rhVar.d.w(B);
                bArr = bArr2;
            }
            p9.f8673a = bArr;
            p9.b = appWebMessagePortArr;
            sendMessage(obtainMessage(1, p9));
            return true;
        } catch (C4200pE e) {
            AbstractC1220Ua0.f("AppWebMessagePort", "Error deserializing message", e);
            return false;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, defpackage.AbstractC3255jk0
    public void close() {
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            String MWL_OG7s = N.MWL_OG7s(((P9) message.obj).f8673a);
            if (MWL_OG7s == null) {
                AbstractC1220Ua0.f("AppWebMessagePort", "Undecodable message received, dropping message", new Object[0]);
                return;
            }
            KE0 ke0 = this.F.f8003a.b;
            if (ke0.c != null) {
                synchronized (ke0.f8352a) {
                    try {
                        ke0.c.P(ke0.b, MWL_OG7s, null);
                    } catch (RemoteException unused) {
                        return;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("undefined message");
    }
}
