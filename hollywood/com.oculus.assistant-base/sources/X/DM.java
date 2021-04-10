package X;

import android.os.Looper;
import java.util.ArrayList;

public final class DM {
    public ArrayList A00 = new ArrayList();
    public boolean A01;
    public final Looper A02;
    public final DL A03;

    public DM(Looper looper) {
        if (looper != null) {
            this.A03 = new DL(this, looper);
            this.A02 = looper;
            return;
        }
        this.A03 = null;
        this.A02 = null;
    }
}
