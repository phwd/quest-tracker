package X;

/* renamed from: X.1cW  reason: invalid class name */
public final class AnonymousClass1cW<K, V> extends C05700wg<K, V> {
    public int A00;

    @Override // X.C000502v
    public final V A06(int i) {
        this.A00 = 0;
        return (V) super.A06(i);
    }

    @Override // X.C000502v
    public final V A07(int i, V v) {
        this.A00 = 0;
        return (V) super.A07(i, v);
    }

    @Override // X.C000502v
    public final void A09(C000502v<? extends K, ? extends V> r2) {
        this.A00 = 0;
        super.A09(r2);
    }

    @Override // X.C000502v
    public final void clear() {
        this.A00 = 0;
        super.clear();
    }

    @Override // X.C000502v, java.util.Map
    public final V put(K k, V v) {
        this.A00 = 0;
        return (V) super.put(k, v);
    }

    @Override // X.C000502v
    public final int hashCode() {
        int i = this.A00;
        if (i != 0) {
            return i;
        }
        int hashCode = super.hashCode();
        this.A00 = hashCode;
        return hashCode;
    }
}
