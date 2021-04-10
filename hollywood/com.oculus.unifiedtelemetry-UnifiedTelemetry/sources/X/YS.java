package X;

import javax.annotation.Nullable;

public class YS implements HA {
    public final HA A00;
    @Nullable
    public final HU A01;

    @Override // X.HA
    public final void A3h() {
        this.A00.A3h();
        HU hu = this.A01;
        if (hu != null) {
            hu.A01.stopSelf(hu.A00);
        }
    }

    @Override // X.HA
    public final void A46(boolean z) {
        this.A00.A46(z);
    }

    public YS(HA ha, @Nullable HU hu) {
        this.A00 = ha;
        this.A01 = hu;
    }
}
