package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1kt  reason: invalid class name and case insensitive filesystem */
public final class C10141kt implements AnonymousClass1mI {
    public final List<AnonymousClass1mI> A00;

    @Override // X.AnonymousClass1lE
    public final void A7X(String str, String str2, String str3) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7X(str, str2, str3);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1lE
    public final void A7Z(String str, String str2, @Nullable Map<String, String> map) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7Z(str, str2, map);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1lE
    public final void A7b(String str, String str2, Throwable th, @Nullable Map<String, String> map) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7b(str, str2, th, map);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1lE
    public final void A7d(String str, String str2, @Nullable Map<String, String> map) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7d(str, str2, map);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1lE
    public final void A7f(String str, String str2) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7f(str, str2);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onProducerStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1mI
    public final void A7o(String str) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7o(str);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    @Override // X.AnonymousClass1mI
    public final void A7r(AnonymousClass1kA r7, String str, Throwable th, boolean z) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7r(r7, str, th, z);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onRequestFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1mI
    public final void A7t(AnonymousClass1kA r7, Object obj, String str, boolean z) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7t(r7, obj, str, z);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onRequestStart", e);
            }
        }
    }

    @Override // X.AnonymousClass1mI
    public final void A7w(AnonymousClass1kA r7, String str, boolean z) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A7w(r7, str, z);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1lE
    public final void A8G(String str, String str2, boolean z) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.get(i).A8G(str, str2, z);
            } catch (Exception e) {
                AnonymousClass0J5.A04("ForwardingRequestListener", "InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // X.AnonymousClass1lE
    public final boolean A9J(String str) {
        List<AnonymousClass1mI> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).A9J(str)) {
                return true;
            }
        }
        return false;
    }

    public C10141kt(Set<AnonymousClass1mI> set) {
        this.A00 = new ArrayList(set.size());
        for (AnonymousClass1mI r1 : set) {
            if (r1 != null) {
                this.A00.add(r1);
            }
        }
    }

    public C10141kt(AnonymousClass1mI... r5) {
        int length = r5.length;
        this.A00 = new ArrayList(length);
        for (AnonymousClass1mI r1 : r5) {
            if (r1 != null) {
                this.A00.add(r1);
            }
        }
    }
}
