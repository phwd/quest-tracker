package X;

import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;

public final class hN implements IP {
    public final PowerManager A00;
    public volatile boolean A01 = false;

    @Override // X.IP
    public final String A2m() {
        return "cpu_stats";
    }

    @Override // X.IP
    public final long A2n() {
        return 256;
    }

    @Override // X.IP
    public final void A1d(RunnableC0929p4 p4Var, Object obj, Object obj2) {
        A5 a5 = (A5) obj;
        A5 a52 = (A5) obj2;
        if (a5 != null && a52 != null) {
            Ic.A00(p4Var.A2f(), "start_pri", Integer.valueOf(a5.A00));
            Ic.A00(p4Var.A2f(), "stop_pri", Integer.valueOf(a52.A00));
            Ic.A00(p4Var.A2f(), "ps_cpu_ms", String.valueOf(a52.A02 - a5.A02));
            if (a5.A01 == a52.A01) {
                Ic.A00(p4Var.A2f(), "th_cpu_ms", Long.valueOf(a52.A03 - a5.A03));
            }
            Ic.A00(p4Var.A2f(), "low_power_state", a5.A04);
        }
    }

    @Override // X.IP
    public final boolean A3S(C0915oq oqVar) {
        if (!(oqVar instanceof C0650du)) {
            return false;
        }
        return A4.A02;
    }

    @Override // X.IP
    public final Object A56() {
        String str;
        A5 a5 = new A5();
        a5.A01 = Process.myTid();
        a5.A02 = Process.getElapsedCpuTime();
        a5.A03 = SystemClock.currentThreadTimeMillis();
        a5.A00 = Process.getThreadPriority(a5.A01);
        a5.A04 = "unknown";
        PowerManager powerManager = this.A00;
        if (powerManager != null && !this.A01) {
            try {
                if (powerManager.isPowerSaveMode()) {
                    str = "true";
                } else {
                    str = "false";
                }
                a5.A04 = str;
                return a5;
            } catch (SecurityException unused) {
                this.A01 = true;
            }
        }
        return a5;
    }

    @Override // X.IP
    public final Object A58() {
        A5 a5 = new A5();
        int myTid = Process.myTid();
        a5.A01 = myTid;
        a5.A02 = 0;
        a5.A03 = 0;
        a5.A00 = Process.getThreadPriority(myTid);
        a5.A04 = "unknown";
        return a5;
    }

    public hN(PowerManager powerManager) {
        this.A00 = powerManager;
    }

    @Override // X.IP
    public final Class A2t() {
        return A5.class;
    }

    @Override // X.IP
    public final Class A2w() {
        return A2t();
    }
}
