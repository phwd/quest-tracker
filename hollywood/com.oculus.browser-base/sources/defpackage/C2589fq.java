package defpackage;

import J.N;
import android.app.Activity;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: fq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2589fq implements AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public final C2760gq f9959a;

    public C2589fq(C2760gq gqVar) {
        this.f9959a = gqVar;
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        AbstractC0124Ca1 P;
        C2760gq gqVar = this.f9959a;
        Objects.requireNonNull(gqVar);
        if (i == 3) {
            if (gqVar.e) {
                UmaUtils.c = SystemClock.uptimeMillis();
                try {
                    TraceEvent.Y("ProfileManagerUtils.commitPendingWritesForAllProfiles", null);
                    N.MPpDwRXN();
                    TraceEvent.f0("ProfileManagerUtils.commitPendingWritesForAllProfiles");
                    int i2 = 0;
                    gqVar.e = false;
                    NE0 ne0 = gqVar.b;
                    ME0 me0 = ne0.c;
                    if (me0.G == 1) {
                        me0.G = 2;
                        me0.F.removeCallbacks(me0);
                    }
                    if (ne0.f8534a.getAndSet(false)) {
                        ContextUtils.getApplicationContext().unregisterReceiver(ne0);
                    }
                    S20.c = null;
                    S20.e = null;
                    Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
                    while (it.hasNext()) {
                        Activity activity = (Activity) it.next();
                        if (activity instanceof ChromeActivity) {
                            ChromeActivity chromeActivity = (ChromeActivity) activity;
                            if (chromeActivity.D0 && (P = chromeActivity.P()) != null) {
                                i2 += ((AbstractC0246Ea1) P).p();
                            }
                        }
                    }
                    AbstractC3364kK0.d("Tab.TotalTabCount.BeforeLeavingApp", i2);
                } catch (Throwable th) {
                    TraceEvent.f0("ProfileManagerUtils.commitPendingWritesForAllProfiles");
                    throw th;
                }
            }
        } else if (i == 4 && ApplicationStatus.f()) {
            PartnerBrowserCustomizations c = PartnerBrowserCustomizations.c();
            c.f.clear();
            c.g = null;
            PartnerBrowserCustomizations.f10729a = null;
            AbstractC2032cb.b.execute(new MT0());
        }
    }
}
