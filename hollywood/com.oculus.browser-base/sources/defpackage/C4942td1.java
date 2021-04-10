package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: td1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4942td1 extends WK implements Qr1 {
    public static long F;
    public int G;
    public long H;
    public long I;

    /* renamed from: J  reason: collision with root package name */
    public long f11354J;
    public int K;

    public C4942td1(Tab tab, int i) {
        this.H = -1;
        this.I = -1;
        this.f11354J = -1;
        this.K = 0;
        this.f11354J = System.currentTimeMillis();
        this.G = i;
        if (i == 0) {
            W(1);
        } else if (i == 1 || i == 2 || i == 3) {
            W(2);
        }
        tab.A(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        if (i != 1) {
            W(2);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        long j = this.I;
        if (j != -1 && this.H >= j) {
            V(-1, -1, i);
        }
        this.I = -1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        long j = this.I;
        if (j != -1 && this.H >= j) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            V(elapsedRealtime - this.I, elapsedRealtime - this.H, 0);
        }
        this.I = -1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void M(Tab tab) {
        if (this.K == 1) {
            this.G = 0;
        } else {
            this.G = 1;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void N(Tab tab) {
        this.I = SystemClock.elapsedRealtime();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        long j = C5383wB.q(tab).S;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = this.H;
        if (j2 != -1 && i == 3) {
            AbstractC3364kK0.d("Tab.SwitchedToForegroundAge", (int) (elapsedRealtime - j2));
        }
        long j3 = F + 1;
        F = j3;
        int i2 = (j3 > 1 ? 1 : (j3 == 1 ? 0 : -1));
        int i3 = 0;
        boolean z = i2 == 0;
        int i4 = this.G;
        boolean z2 = i4 == 3 && this.H == -1;
        if (this.I != -1 || z2) {
            if (this.H == -1) {
                if (z) {
                    i3 = 6;
                } else if (i4 == 2) {
                    i3 = 7;
                } else if (i4 == 3) {
                    i3 = 8;
                }
            }
            i3 = 1;
        }
        if (i == 3) {
            AbstractC3364kK0.g("Tab.StatusWhenSwitchedBackToForeground", i3, 9);
        }
        if (this.H == -1 && j > 0) {
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (z) {
                AbstractC3364kK0.d("Tabs.ForegroundTabAgeAtStartup", (int) (currentTimeMillis / 60000));
            } else if (i == 3) {
                AbstractC3364kK0.d("Tab.AgeUponRestoreFromColdStart", (int) (currentTimeMillis / 60000));
            }
        }
        this.H = elapsedRealtime;
        W(1);
    }

    public final void V(long j, long j2, int i) {
        if (i == 0) {
            AbstractC3364kK0.g("Tab.RestoreResult", 1, 3);
            AbstractC3364kK0.d("Tab.RestoreTime", (int) j);
            AbstractC3364kK0.d("Tab.PerceivedRestoreTime", (int) j2);
        } else if (i == -803 || i == -137 || i == -106) {
            AbstractC3364kK0.g("Tab.RestoreResult", 2, 3);
        } else {
            AbstractC3364kK0.g("Tab.RestoreResult", 0, 3);
        }
    }

    public final void W(int i) {
        if (this.K != i) {
            long currentTimeMillis = System.currentTimeMillis();
            int i2 = this.K;
            if (i2 == 0) {
                AbstractC3364kK0.g("Tabs.StateTransfer.Target_Initial", i, 4);
            } else if (i2 == 1) {
                AbstractC3364kK0.g("Tabs.StateTransfer.Target_Active", i, 4);
            } else if (i2 == 2) {
                AbstractC3364kK0.g("Tabs.StateTransfer.Target_Inactive", i, 4);
            }
            this.f11354J = currentTimeMillis;
            this.K = i;
        }
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        if (this.I != -1) {
            this.I = -1;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        W(4);
        tab.I(this);
    }
}
