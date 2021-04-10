package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0xM  reason: invalid class name and case insensitive filesystem */
public final class C08510xM {
    @GuardedBy("this")
    public Throwable A00 = null;
    public final int A01;
    public final long A02;
    public final C08300wz A03;
    public final EnumC08830xt A04;
    public final String A05;
    public volatile AbstractFutureC09480zX<?> A06;
    public volatile AnonymousClass10O A07;

    public final void A00() {
        synchronized (this) {
            this.A00 = new TimeoutException();
        }
        if (this.A06 != null) {
            this.A06.cancel(false);
        }
    }

    public final String toString() {
        return "MqttOperation{mResponseType=" + this.A04 + ", mOperationId=" + this.A01 + ", mCreationTime=" + this.A02 + '}';
    }

    public C08510xM(C08300wz r2, String str, EnumC08830xt r4, int i, long j) {
        this.A03 = r2;
        this.A05 = str;
        this.A04 = r4;
        this.A01 = i;
        this.A02 = j;
    }
}
