package X;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;

public class UE implements Iterator<Map.Entry<K, V>>, AnonymousClass2g<K, V> {
    public AnonymousClass2f<K, V> A00;
    public boolean A01 = true;
    public final /* synthetic */ AnonymousClass2h A02;

    public UE(AnonymousClass2h r2) {
        this.A02 = r2;
    }

    @Override // X.AnonymousClass2g
    public final void A3o(@NonNull AnonymousClass2f<K, V> r3) {
        AnonymousClass2f<K, V> r0 = this.A00;
        if (r3 == r0) {
            AnonymousClass2f<K, V> r1 = r0.A01;
            this.A00 = r1;
            boolean z = false;
            if (r1 == null) {
                z = true;
            }
            this.A01 = z;
        }
    }

    public final boolean hasNext() {
        AnonymousClass2f<K, V> r0;
        if (this.A01) {
            r0 = this.A02.A02;
        } else {
            AnonymousClass2f<K, V> r02 = this.A00;
            if (r02 == null) {
                return false;
            }
            r0 = r02.A00;
        }
        if (r0 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        AnonymousClass2f<K, V> r0;
        if (this.A01) {
            this.A01 = false;
            r0 = this.A02.A02;
        } else {
            AnonymousClass2f<K, V> r02 = this.A00;
            if (r02 != null) {
                r0 = r02.A00;
            } else {
                r0 = null;
            }
        }
        this.A00 = r0;
        return r0;
    }
}
