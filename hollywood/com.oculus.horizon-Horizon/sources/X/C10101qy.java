package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qy  reason: invalid class name and case insensitive filesystem */
public final class C10101qy implements AnonymousClass1tF {
    public final List<AnonymousClass1tF> A00;

    @Override // X.AnonymousClass1s1
    public final void A6U(String str, String str2, String str3) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6U(str, str2, str3);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1s1
    public final void A6W(String str, String str2, @Nullable Map<String, String> map) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6W(str, str2, map);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1s1
    public final void A6Y(String str, String str2, Throwable th, @Nullable Map<String, String> map) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6Y(str, str2, th, map);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1s1
    public final void A6a(String str, String str2, @Nullable Map<String, String> map) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6a(str, str2, map);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1s1
    public final void A6c(String str, String str2) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6c(str, str2);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onProducerStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1tF
    public final void A6k(String str) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6k(str);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1tF
    public final void A6m(C09811pd r7, String str, Throwable th, boolean z) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6m(r7, str, th, z);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onRequestFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1tF
    public final void A6o(C09811pd r7, Object obj, String str, boolean z) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6o(r7, obj, str, z);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onRequestStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1tF
    public final void A6q(C09811pd r7, String str, boolean z) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A6q(r7, str, z);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1s1
    public final void A78(String str, String str2, boolean z) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A78(str, str2, z);
            } catch (Exception e) {
                C01080Kb.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1s1
    public final boolean A8L(String str) {
        List<AnonymousClass1tF> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).A8L(str)) {
                return true;
            }
        }
        return false;
    }

    public C10101qy(Set<AnonymousClass1tF> set) {
        this.A00 = new ArrayList(set.size());
        for (AnonymousClass1tF r1 : set) {
            if (r1 != null) {
                this.A00.add(r1);
            }
        }
    }

    public C10101qy(AnonymousClass1tF... r5) {
        int length = r5.length;
        this.A00 = new ArrayList(length);
        for (AnonymousClass1tF r1 : r5) {
            if (r1 != null) {
                this.A00.add(r1);
            }
        }
    }
}
