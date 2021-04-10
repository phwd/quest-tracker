package com.oculus.messengervr.common.utils;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC12881yV;
import X.AbstractC13031yl;
import X.AbstractC13241zD;
import X.AbstractC13251zE;
import X.AnonymousClass1xU;
import X.AnonymousClass1z9;
import X.AnonymousClass21D;
import X.AnonymousClass21F;
import X.C12781yJ;
import X.C13041ym;
import X.C13261zF;
import X.C13401zX;
import X.C13471ze;
import X.C13621zt;
import X.C13661zx;
import X.C137220e;
import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MutationObservableUtil {
    public static <T, V> AbstractC13251zE<T> wrapMutationSingle(AbstractC13251zE<V> r5, AnonymousClass1xU r6, Function<V, AbstractC13251zE<T>> function) {
        AtomicReference atomicReference = new AtomicReference();
        C13471ze r2 = new C13471ze(new C13401zX(r5, new AbstractC13031yl(function) {
            /* class com.oculus.messengervr.common.utils.$$Lambda$sdQ6RZZ0qWIJcE2ZiDarchoTi02 */
            public final /* synthetic */ Function f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return this.f$0.apply(obj);
            }
        }).A02(), new AbstractC12881yV(atomicReference, r6) {
            /* class com.oculus.messengervr.common.utils.$$Lambda$MutationObservableUtil$GNv_Q76mnBunNgeMo3aPhPnVkuE2 */
            public final /* synthetic */ AtomicReference f$0;
            public final /* synthetic */ AnonymousClass1xU f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // X.AbstractC12881yV
            public final void run() {
                MutationObservableUtil.lambda$wrapMutationSingle$0(this.f$0, this.f$1);
            }
        });
        AtomicReference atomicReference2 = new AtomicReference();
        AnonymousClass21D r3 = new AnonymousClass21D(new AnonymousClass21F(atomicReference2), r2, atomicReference2);
        C13261zF r22 = new C13261zF(new C13041ym(r3));
        r22.A05(C137220e.A04, C137220e.A06);
        r3.A0K(new AbstractC12851yS(atomicReference, r6) {
            /* class com.oculus.messengervr.common.utils.$$Lambda$MutationObservableUtil$bo_bumwbq3sARIGPONvCNqD8roA2 */
            public final /* synthetic */ AtomicReference f$0;
            public final /* synthetic */ AnonymousClass1xU f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                MutationObservableUtil.lambda$wrapMutationSingle$1(this.f$0, this.f$1, (AbstractC12271xB) obj);
            }
        });
        return r22;
    }

    public static <T> AbstractC13241zD wrapMutationSingleToCompletable(AbstractC13251zE<T> r5, AnonymousClass1xU r6, Function<T, AbstractC13241zD> function) {
        AtomicReference atomicReference = new AtomicReference();
        C13471ze r3 = new C13471ze(new AnonymousClass1z9(r5, new AbstractC13031yl(function) {
            /* class com.oculus.messengervr.common.utils.$$Lambda$J0BmC0xSFFDVjVKe8GxGleojzys2 */
            public final /* synthetic */ Function f$0;

            {
                this.f$0 = r1;
            }

            @Override // X.AbstractC13031yl
            public final Object apply(Object obj) {
                return this.f$0.apply(obj);
            }
        }).A01(), new AbstractC12881yV(atomicReference, r6) {
            /* class com.oculus.messengervr.common.utils.$$Lambda$MutationObservableUtil$1qvFHereBItBdxYL4u979rM9zHA2 */
            public final /* synthetic */ AtomicReference f$0;
            public final /* synthetic */ AnonymousClass1xU f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // X.AbstractC12881yV
            public final void run() {
                MutationObservableUtil.lambda$wrapMutationSingleToCompletable$2(this.f$0, this.f$1);
            }
        });
        AtomicReference atomicReference2 = new AtomicReference();
        AnonymousClass21D r2 = new AnonymousClass21D(new AnonymousClass21F(atomicReference2), r3, atomicReference2);
        C13621zt r1 = new C13621zt(new C13661zx(r2));
        r1.AAZ(new C12781yJ());
        r2.A0K(new AbstractC12851yS(atomicReference, r6) {
            /* class com.oculus.messengervr.common.utils.$$Lambda$MutationObservableUtil$mTd4ClrsDQqCYN5WJgSAbaZE2c2 */
            public final /* synthetic */ AtomicReference f$0;
            public final /* synthetic */ AnonymousClass1xU f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                MutationObservableUtil.lambda$wrapMutationSingleToCompletable$3(this.f$0, this.f$1, (AbstractC12271xB) obj);
            }
        });
        return r1;
    }

    public static /* synthetic */ void lambda$wrapMutationSingle$0(AtomicReference atomicReference, AnonymousClass1xU r1) throws Exception {
        AbstractC12271xB r0 = (AbstractC12271xB) atomicReference.get();
        if (r0 != null) {
            r1.A2Z(r0);
        }
    }

    public static /* synthetic */ void lambda$wrapMutationSingle$1(AtomicReference atomicReference, AnonymousClass1xU r1, AbstractC12271xB r2) throws Exception {
        atomicReference.set(r2);
        r1.A1D(r2);
    }

    public static /* synthetic */ void lambda$wrapMutationSingleToCompletable$2(AtomicReference atomicReference, AnonymousClass1xU r1) throws Exception {
        AbstractC12271xB r0 = (AbstractC12271xB) atomicReference.get();
        if (r0 != null) {
            r1.A2Z(r0);
        }
    }

    public static /* synthetic */ void lambda$wrapMutationSingleToCompletable$3(AtomicReference atomicReference, AnonymousClass1xU r1, AbstractC12271xB r2) throws Exception {
        atomicReference.set(r2);
        r1.A1D(r2);
    }
}
