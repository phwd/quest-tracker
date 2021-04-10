package X;

import com.oculus.horizon.logging.LoggingKeys;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1xb  reason: invalid class name */
public class AnonymousClass1xb implements AnonymousClass1xh {
    public final /* synthetic */ AnonymousClass1xh A00;
    public final /* synthetic */ C11061xY A01;
    public final /* synthetic */ String A02;

    public AnonymousClass1xb(C11061xY r1, String str, AnonymousClass1xh r3) {
        this.A01 = r1;
        this.A02 = str;
        this.A00 = r3;
    }

    @Override // X.AnonymousClass1xh
    public final void A5o(C11081xd r12) {
        C11061xY r1 = this.A01;
        AbstractC11091xe r2 = r1.A02;
        long hashCode = (long) r1.hashCode();
        String str = this.A02;
        String str2 = r12.mErrorSeverity;
        if (str2 == null) {
            str2 = LoggingKeys.REFERRER_MEDIUM;
        }
        r2.A5R("recording_failed", "RecordingControllerImpl", hashCode, str, r12, str2, "RecordingControllerImpl");
        r2.A5M(19);
        r1.A9U(true);
        r1.A04.set(false);
        this.A00.A5o(r12);
    }

    @Override // X.AnonymousClass1xh
    public final void A5p() {
        C11061xY r2 = this.A01;
        AbstractC11091xe r3 = r2.A02;
        long hashCode = (long) hashCode();
        String str = this.A02;
        C11211xt r0 = r2.A03;
        HashMap hashMap = new HashMap();
        for (AbstractC11261xz r02 : r0.A04.values()) {
            Map<String, String> A48 = r02.A48();
            if (A48 != null) {
                hashMap.putAll(A48);
            }
        }
        r3.A5S("recording_finished", "RecordingControllerImpl", hashCode, str, hashMap);
        r3.A5N(19);
        r2.A04.set(false);
        this.A00.A5p();
    }

    @Override // X.AnonymousClass1xh
    public final void A5q(long j) {
        this.A01.A02.A5S("recording_started", "RecordingControllerImpl", (long) hashCode(), this.A02, null);
        this.A00.A5q(j);
    }

    @Override // X.AnonymousClass1xh
    public final long now() {
        return this.A00.now();
    }
}
