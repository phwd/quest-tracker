package X;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.1fI  reason: invalid class name */
public final class AnonymousClass1fI implements Iterable<AnonymousClass1fY> {
    public final List<AnonymousClass1fY> A00;

    @Override // java.lang.Iterable
    @NonNull
    public final Iterator<AnonymousClass1fY> iterator() {
        return this.A00.iterator();
    }

    public AnonymousClass1fI() {
        this(new ArrayList(2));
    }

    public AnonymousClass1fI(List<AnonymousClass1fY> list) {
        this.A00 = list;
    }
}
