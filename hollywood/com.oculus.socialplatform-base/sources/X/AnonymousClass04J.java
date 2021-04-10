package X;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.04J  reason: invalid class name */
public final class AnonymousClass04J implements Iterable<Intent> {
    public final Context A00;
    public final ArrayList<Intent> A01 = new ArrayList<>();

    @Override // java.lang.Iterable
    @Deprecated
    public final Iterator<Intent> iterator() {
        return this.A01.iterator();
    }

    public AnonymousClass04J(Context context) {
        this.A00 = context;
    }
}
