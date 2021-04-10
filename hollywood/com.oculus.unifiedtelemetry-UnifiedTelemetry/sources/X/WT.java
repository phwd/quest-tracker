package X;

import java.io.IOException;
import java.util.Map;

public final class WT extends AbstractC0202Vi {
    public final Object A00;

    @Override // X.AbstractC0202Vi
    public final void A00(Object obj) throws IOException, q0 {
        ((Map) obj).put(this.A00, this.A01);
    }

    public WT(AbstractC0202Vi vi, Object obj, Object obj2) {
        super(vi, obj);
        this.A00 = obj2;
    }
}
