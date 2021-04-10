package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1ks  reason: invalid class name and case insensitive filesystem */
public final class C10131ks implements AnonymousClass1m8 {
    public final List<AnonymousClass1m8> A00;

    @Override // X.AnonymousClass1l6
    public final void A7W(C10161kv r7, String str, String str2) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7W(r7, str, str2);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7Y(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7Y(producerContext, str, map);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7a(ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7a(producerContext, str, th, map);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7c(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7c(producerContext, str, map);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A7e(C10161kv r7, String str) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7e(r7, str);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onProducerStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7n(C10161kv r7) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7n(r7);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7q(C10161kv r7, Throwable th) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7q(r7, th);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onRequestFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7s(C10161kv r7) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7s(r7);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onRequestStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1m8
    public final void A7v(C10161kv r7) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7v(r7);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1l6
    public final void A8F(C10161kv r7, String str, boolean z) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A8F(r7, str, z);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener2", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1l6
    public final boolean A9I(C10161kv r6, String str) {
        List<AnonymousClass1m8> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).A9I(r6, str)) {
                return true;
            }
        }
        return false;
    }

    public C10131ks(Set<AnonymousClass1m8> set) {
        this.A00 = new ArrayList(set.size());
        for (AnonymousClass1m8 r1 : set) {
            if (r1 != null) {
                this.A00.add(r1);
            }
        }
    }
}
