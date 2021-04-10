package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qx  reason: invalid class name and case insensitive filesystem */
public final class C10091qx implements AnonymousClass1t0 {
    public final List<AnonymousClass1t0> A00;

    @Override // X.AnonymousClass1qE
    public final void A6T(AnonymousClass1qU r7, String str, String str2) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6T(r7, str, str2);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6V(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6V(producerContext, str, map);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6X(ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6X(producerContext, str, th, map);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6Z(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6Z(producerContext, str, map);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A6b(AnonymousClass1qU r7, String str) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6b(r7, str);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onProducerStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6j(AnonymousClass1qU r7) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6j(r7);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6l(AnonymousClass1qU r7, Throwable th) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6l(r7, th);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onRequestFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6n(AnonymousClass1qU r7) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6n(r7);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onRequestStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1t0
    public final void A6p(AnonymousClass1qU r7) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6p(r7);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1qE
    public final void A77(AnonymousClass1qU r7, String str, boolean z) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A77(r7, str, z);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1qE
    public final boolean A8K(AnonymousClass1qU r6, String str) {
        List<AnonymousClass1t0> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).A8K(r6, str)) {
                return true;
            }
        }
        return false;
    }

    public C10091qx(Set<AnonymousClass1t0> set) {
        this.A00 = new ArrayList(set.size());
        for (AnonymousClass1t0 r1 : set) {
            if (r1 != null) {
                this.A00.add(r1);
            }
        }
    }
}
