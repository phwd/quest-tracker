package X;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.BiPredicate;

/* renamed from: X.20g  reason: invalid class name and case insensitive filesystem */
public final class C137420g<T, K> extends AbstractC137320f<T, T> {
    public K A00;
    public boolean A01;
    public final AbstractC13031yl<? super T, K> A02;
    public final BiPredicate<? super K, ? super K> A03;

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026 A[Catch:{ all -> 0x002d }, RETURN] */
    @Override // X.AnonymousClass1yM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onNext(T r3) {
        /*
            r2 = this;
            boolean r0 = r2.A03
            if (r0 != 0) goto L_0x003f
            int r0 = r2.A01
            if (r0 != 0) goto L_0x003a
            X.1yl<? super T, K> r0 = r2.A02     // Catch:{ all -> 0x002d }
            java.lang.Object r1 = r0.apply(r3)     // Catch:{ all -> 0x002d }
            boolean r0 = r2.A01     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x0027
            K r0 = r2.A00     // Catch:{ all -> 0x002d }
            if (r0 == r1) goto L_0x0021
            if (r0 == 0) goto L_0x001f
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            r0 = 0
            goto L_0x0022
        L_0x0021:
            r0 = 1
        L_0x0022:
            r2.A00 = r1     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x003a
            return
        L_0x0027:
            r0 = 1
            r2.A01 = r0     // Catch:{ all -> 0x002d }
            r2.A00 = r1     // Catch:{ all -> 0x002d }
            goto L_0x003a
        L_0x002d:
            r1 = move-exception
            X.C12261xA.A00(r1)
            X.1xB r0 = r2.A00
            r0.dispose()
            r2.onError(r1)
            return
        L_0x003a:
            X.1yM<? super R> r0 = r2.A04
            r0.onNext(r3)
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C137420g.onNext(java.lang.Object):void");
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() throws Exception {
        T poll;
        K apply;
        while (true) {
            poll = super.A02.poll();
            if (poll != null) {
                apply = this.A02.apply(poll);
                if (this.A01) {
                    K k = this.A00;
                    if (k != apply && (k == null || !k.equals(apply))) {
                        break;
                    }
                    this.A00 = apply;
                } else {
                    this.A01 = true;
                    break;
                }
            } else {
                return null;
            }
        }
        this.A00 = apply;
        return poll;
    }

    public C137420g(AnonymousClass1yM<? super T> r1, AbstractC13031yl<? super T, K> r2, BiPredicate<? super K, ? super K> biPredicate) {
        super(r1);
        this.A02 = r2;
        this.A03 = biPredicate;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        return A00(i);
    }
}
