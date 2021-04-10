package X;

import java.io.IOException;

public class YZ implements HK {
    public final /* synthetic */ HB A00;

    public YZ(HB hb) {
        this.A00 = hb;
    }

    @Override // X.HK
    public final void A3j(IOException iOException) {
        HB hb = this.A00;
        hb.sendMessage(hb.obtainMessage(5, iOException));
    }

    @Override // X.HK
    public final void A43() {
        HB hb = this.A00;
        hb.sendMessage(hb.obtainMessage(2));
    }
}
