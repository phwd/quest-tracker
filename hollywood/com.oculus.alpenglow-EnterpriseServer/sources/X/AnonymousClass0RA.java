package X;

import android.content.Context;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.0RA  reason: invalid class name */
public final class AnonymousClass0RA {
    public Context A00;
    public final List<Context> A01 = new ArrayList();
    public final List<AnonymousClass0Lf> A02 = new ArrayList();

    public final Context A00() {
        List<Context> list = this.A01;
        if (list.isEmpty()) {
            return this.A00;
        }
        return list.get(list.size() - 1);
    }

    public final void A01() {
        List<AnonymousClass0Lf> list = this.A02;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
    }

    public AnonymousClass0RA(Context context) {
        this.A00 = context;
    }
}
