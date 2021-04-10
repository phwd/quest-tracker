package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0mQ  reason: invalid class name and case insensitive filesystem */
public final class C06050mQ {
    @GuardedBy("this")
    public Throwable A00 = null;
    public final int A01;
    public final long A02;
    public final AnonymousClass0ZF A03;
    public final EnumC02120Zg A04;
    public final String A05;
    public volatile AnonymousClass0XB<?> A06;
    public volatile AnonymousClass0ZN A07;

    public final void A00() {
        synchronized (this) {
            this.A00 = new TimeoutException();
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

    public C06050mQ(AnonymousClass0ZF r2, String str, EnumC02120Zg r4, int i, long j) {
        this.A03 = r2;
        this.A05 = str;
        this.A04 = r4;
        this.A01 = i;
        this.A02 = j;
    }
}
