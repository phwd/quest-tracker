package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22b  reason: invalid class name */
public final class AnonymousClass22b {
    @GuardedBy("this")
    public Throwable A00 = null;
    public final int A01;
    public final long A02;
    public final AnonymousClass22J A03;
    public final EnumC142622o A04;
    public final String A05;
    public volatile AnonymousClass1XI<?> A06;
    public volatile VrMsysMqttClientCallbacks.AnonymousClass1.AnonymousClass1 A07;

    public final void A00() {
        synchronized (this) {
            this.A00 = new TimeoutException();
        }
        if (this.A07 != null) {
            this.A07.onPubAckTimeout(this.A01);
        }
        if (this.A06 != null) {
            this.A06.cancel(false);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MqttOperation{mResponseType=");
        sb.append(this.A04);
        sb.append(", mOperationId=");
        sb.append(this.A01);
        sb.append(", mCreationTime=");
        sb.append(this.A02);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass22b(AnonymousClass22J r2, String str, EnumC142622o r4, int i, long j) {
        this.A03 = r2;
        this.A05 = str;
        this.A04 = r4;
        this.A01 = i;
        this.A02 = j;
    }
}
