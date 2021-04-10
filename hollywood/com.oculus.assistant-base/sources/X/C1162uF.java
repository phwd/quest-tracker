package X;

import com.google.common.collect.ImmutableMultiset;
import java.util.Iterator;

/* renamed from: X.uF  reason: case insensitive filesystem */
public final class C1162uF extends AbstractC0370Ug {
    public int A00;
    public Object A01;
    public final /* synthetic */ ImmutableMultiset A02;
    public final /* synthetic */ Iterator A03;

    public C1162uF(ImmutableMultiset immutableMultiset, Iterator it) {
        this.A02 = immutableMultiset;
        this.A03 = it;
    }

    public final boolean hasNext() {
        if (this.A00 > 0 || this.A03.hasNext()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.A00;
        if (i <= 0) {
            AbstractC1179ua uaVar = (AbstractC1179ua) this.A03.next();
            this.A01 = uaVar.A01();
            i = uaVar.A00();
            this.A00 = i;
        }
        this.A00 = i - 1;
        return this.A01;
    }
}
