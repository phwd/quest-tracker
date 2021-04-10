package X;

import com.oculus.horizon.fbconnect.FBConnectLogger;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@NotThreadSafe
@ThreadSafe
/* renamed from: X.1pz  reason: invalid class name and case insensitive filesystem */
public interface AbstractC09911pz<T, INFO> {
    public static final Map<String, Object> A01 = AnonymousClass0KP.A00("component_tag", "drawee");
    public static final Map<String, Object> A02 = AnonymousClass0KP.A01(FBConnectLogger.EXTRA_ORIGIN, "memory_bitmap", "origin_sub", "shortcut");
    public final AnonymousClass1sA A00;

    final default String toString() {
        AnonymousClass0KS A002 = AnonymousClass0KT.A00(this);
        String valueOf = String.valueOf(false);
        AnonymousClass0KS.A00(A002, "isAttached", valueOf);
        AnonymousClass0KS.A00(A002, "isRequestSubmitted", valueOf);
        AnonymousClass0KS.A00(A002, "hasFetchFailed", valueOf);
        AnonymousClass0KS.A00(A002, "fetchedImage", String.valueOf(System.identityHashCode(null)));
        AnonymousClass0KS.A00(A002, "events", this.A00.toString());
        return A002.toString();
    }
}
