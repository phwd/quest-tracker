package X;

import java.util.Set;

/* renamed from: X.0Xr  reason: invalid class name and case insensitive filesystem */
public class C02310Xr extends AbstractC07450tb<E> {
    public final /* synthetic */ Set A00;
    public final /* synthetic */ Set A01;

    public C02310Xr(Set set, Set set2) {
        this.A00 = set;
        this.A01 = set2;
    }

    @Override // X.AbstractC07450tb
    /* renamed from: A00 */
    public final AnonymousClass0u6<E> iterator() {
        return new AnonymousClass0Il(this);
    }

    public final boolean contains(Object obj) {
        if (this.A00.contains(obj) || this.A01.contains(obj)) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        if (!this.A00.isEmpty() || !this.A01.isEmpty()) {
            return false;
        }
        return true;
    }

    public final int size() {
        Set set = this.A00;
        int size = set.size();
        for (Object obj : this.A01) {
            if (!set.contains(obj)) {
                size++;
            }
        }
        return size;
    }
}
