package X;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: X.1SC  reason: invalid class name */
public final class AnonymousClass1SC {
    public boolean A00;
    public final List<AnonymousClass1h5> A01 = new ArrayList();
    public final Set<AnonymousClass1h5> A02 = Collections.newSetFromMap(new WeakHashMap());

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{numRequests=");
        sb.append(this.A02.size());
        sb.append(", isPaused=");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }
}
