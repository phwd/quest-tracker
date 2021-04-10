package defpackage;

import android.os.PowerManager;
import java.util.Objects;

/* renamed from: Wq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1383Wq0 extends Thread {
    public AbstractC1876bg F;
    public String G;
    public boolean H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public UL f9175J;
    public final /* synthetic */ C1505Yq0 K;

    public C1383Wq0(C1505Yq0 yq0, AbstractC1876bg bgVar, String str, boolean z, String str2) {
        this.K = yq0;
        yq0.e = 0;
        yq0.d = null;
        this.F = bgVar;
        this.G = str;
        this.H = z;
        this.I = str2;
    }

    public void run() {
        PowerManager.WakeLock wakeLock = null;
        try {
            wakeLock = C1505Yq0.a(this.K, this.F.f9554a);
            UL b = AbstractC1585a.b(this.F, this.G, this.H);
            this.f9175J = b;
            String str = this.I;
            Objects.requireNonNull(b);
            if (str != null) {
                String str2 = (String) b.l.put("oc_user_id", str);
            } else {
                String str3 = (String) b.l.remove("oc_user_id");
            }
        } finally {
            if (wakeLock != null && wakeLock.isHeld()) {
                wakeLock.release();
            }
        }
    }
}
