package X;

import com.facebook.papaya.IPapayaService;
import java.util.Set;

public final class H5 {
    public final IPapayaService A00;
    public final H6 A01;
    public final /* synthetic */ H7 A02;

    public H5(H7 h7, IPapayaService iPapayaService, H6 h6) {
        this.A02 = h7;
        this.A00 = iPapayaService;
        this.A01 = h6;
    }

    public final void A00() {
        H7 h7 = this.A02;
        Set set = h7.A02;
        synchronized (set) {
            H6 h6 = this.A01;
            if (set.remove(h6)) {
                h7.A00.unbindService(h6);
            }
        }
    }
}
