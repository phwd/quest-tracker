package defpackage;

import java.util.HashSet;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: Zg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1542Zg implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public final C3929nh f9358a;
    public final HashSet b = new HashSet();
    public boolean c;
    public C3245jh d;
    public final C1481Yg e;

    public C1542Zg() {
        C1481Yg yg = new C1481Yg(this);
        this.e = yg;
        this.c = false;
        this.f9358a = new C3929nh(yg);
    }

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        Object obj = ThreadUtils.f10596a;
        if (this.b.isEmpty()) {
            C3929nh nhVar = this.f9358a;
            if (!nhVar.e && ContextUtils.getApplicationContext().registerReceiver(nhVar.c, nhVar.b) != null) {
                nhVar.e = true;
            }
            if (!nhVar.e) {
                AbstractC1220Ua0.a("BattMonitorFactory", "BatteryStatusManager failed to start.", new Object[0]);
            }
        }
        C1699ah ahVar = new C1699ah(this);
        if (this.c) {
            ahVar.f0(this.d);
        }
        this.b.add(ahVar);
        return ahVar;
    }
}
