package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashMap;
import com.google.common.collect.CompactLinkedHashMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.To  reason: case insensitive filesystem */
public abstract class AbstractC0363To implements Iterator {
    public int A00;
    public int A01;
    public int A02;
    public final /* synthetic */ CompactHashMap A03;

    public AbstractC0363To(CompactHashMap compactHashMap) {
        int i;
        this.A03 = compactHashMap;
        this.A02 = compactHashMap.A01;
        if (!(compactHashMap instanceof CompactLinkedHashMap)) {
            i = 0;
            if (compactHashMap.isEmpty()) {
                i = -1;
            }
        } else {
            i = ((CompactLinkedHashMap) compactHashMap).A00;
        }
        this.A00 = i;
        this.A01 = -1;
    }

    public final boolean hasNext() {
        if (this.A00 >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object[] objArr;
        Object obj;
        CompactHashMap compactHashMap = this.A03;
        if (compactHashMap.A01 != this.A02) {
            throw new ConcurrentModificationException();
        } else if (hasNext()) {
            int i = this.A00;
            this.A01 = i;
            if (this instanceof u2) {
                objArr = ((u2) this).A00.A07;
            } else if (!(this instanceof u1)) {
                objArr = ((u0) this).A00.A06;
            } else {
                obj = new u3(((u1) this).A00, i);
                this.A00 = compactHashMap.A03(i);
                return obj;
            }
            obj = objArr[i];
            this.A00 = compactHashMap.A03(i);
            return obj;
        } else {
            throw new NoSuchElementException();
        }
    }

    public final void remove() {
        CompactHashMap compactHashMap = this.A03;
        if (compactHashMap.A01 == this.A02) {
            boolean z = false;
            if (this.A01 >= 0) {
                z = true;
            }
            Preconditions.checkState(z, "no calls to next() since the last call to remove()");
            this.A02++;
            int i = this.A01;
            CompactHashMap.A01(compactHashMap, compactHashMap.A06[i], (int) (compactHashMap.A05[i] >>> 32));
            int i2 = this.A00;
            int i3 = this.A01;
            if (!(compactHashMap instanceof CompactLinkedHashMap)) {
                i2--;
            } else if (i2 >= compactHashMap.size()) {
                i2 = i3;
            }
            this.A00 = i2;
            this.A01 = -1;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
