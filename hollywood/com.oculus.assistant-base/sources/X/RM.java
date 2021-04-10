package X;

import java.util.ArrayList;

public abstract class RM {
    public Object A00;
    public boolean A01 = false;
    public final /* synthetic */ RO A02;

    public final void A00() {
        synchronized (this) {
            this.A00 = null;
        }
        ArrayList arrayList = this.A02.A0G;
        synchronized (arrayList) {
            arrayList.remove(this);
        }
    }

    public RM(RO ro, Object obj) {
        this.A02 = ro;
        this.A00 = obj;
    }
}
