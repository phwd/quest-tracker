package X;

import java.util.Set;

public class MU extends AbstractC0126Rr<E> {
    public final /* synthetic */ Set A00;
    public final /* synthetic */ Set A01;

    public MU(Set set, Set set2) {
        this.A01 = set;
        this.A00 = set2;
    }

    public final boolean contains(Object obj) {
        if (this.A01.contains(obj) || this.A00.contains(obj)) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        if (!this.A01.isEmpty() || !this.A00.isEmpty()) {
            return false;
        }
        return true;
    }

    public final int size() {
        Set set = this.A01;
        int size = set.size();
        for (Object obj : this.A00) {
            if (!set.contains(obj)) {
                size++;
            }
        }
        return size;
    }
}
