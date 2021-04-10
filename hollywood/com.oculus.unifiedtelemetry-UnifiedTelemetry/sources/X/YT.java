package X;

import android.os.PowerManager;

public class YT implements HA {
    public final HL A00;
    public final /* synthetic */ HT A01;

    public YT(HT ht, HL hl) {
        this.A01 = ht;
        this.A00 = hl;
    }

    @Override // X.HA
    public final void A3h() {
        PowerManager.WakeLock wakeLock = this.A01.A00;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    @Override // X.HA
    public final void A46(boolean z) {
        HT ht;
        HQ hq;
        if (z && (hq = (ht = this.A01).A07) != null) {
            this.A00.A04(ht.A02, hq.A02, ht.A06, hq.A01, hq.A00);
        }
    }
}
