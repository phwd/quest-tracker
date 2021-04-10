package X;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class Op {
    public Context A00;
    public final List<Context> A01 = new ArrayList();
    public final List<BX> A02 = new ArrayList();

    public final Context A00() {
        List<Context> list = this.A01;
        if (list.isEmpty()) {
            return this.A00;
        }
        return list.get(list.size() - 1);
    }

    public Op(Context context) {
        this.A00 = context;
    }
}
