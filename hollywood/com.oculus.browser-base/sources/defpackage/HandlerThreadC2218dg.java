package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* renamed from: dg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerThreadC2218dg extends HandlerThread implements Handler.Callback {
    public HandlerThreadC2218dg() {
        super("GifDecoder");
    }

    public boolean handleMessage(Message message) {
        RunnableC2388eg egVar = (RunnableC2388eg) message.obj;
        if (!(egVar == null || egVar.R == null)) {
            int i = message.what;
            if (i == 10) {
                do {
                    try {
                        RunnableC2388eg.a(egVar);
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        egVar.C0 = true;
                    }
                    if (egVar.C0) {
                        int i2 = egVar.t0;
                        if (i2 == 0) {
                            egVar.V = true;
                        } else if (i2 > 1) {
                            int i3 = egVar.D0;
                            if (i3 != 0) {
                                int i4 = egVar.E0 + 1;
                                egVar.E0 = i4;
                                if (i4 >= i3) {
                                    egVar.W = true;
                                }
                            }
                            egVar.M = egVar.K.e;
                            egVar.r0 = false;
                            egVar.t0 = 0;
                            egVar.k0 = 0;
                        } else {
                            egVar.W = true;
                        }
                    }
                    if (!egVar.C0 || egVar.V) {
                        Handler handler = egVar.A0;
                        handler.sendMessage(handler.obtainMessage(11, egVar.x0, 0));
                    }
                } while (!egVar.W);
                Handler handler2 = egVar.A0;
                handler2.sendMessage(handler2.obtainMessage(11, egVar.x0, 0));
            } else if (i != 12) {
                return false;
            } else {
                egVar.M = egVar.K.e;
                egVar.r0 = false;
                egVar.t0 = 0;
                egVar.k0 = 0;
                return true;
            }
        }
        return true;
    }
}
