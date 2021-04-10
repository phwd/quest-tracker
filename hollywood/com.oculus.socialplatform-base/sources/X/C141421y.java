package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.21y  reason: invalid class name and case insensitive filesystem */
public final class C141421y {
    public final AnonymousClass22D A00;
    public final long A01;
    public final long A02;
    @Nullable
    public final EnumC142522n A03;

    public final String toString() {
        StringBuilder sb = new StringBuilder("MqttChannelState{mConnectionState=");
        sb.append(this.A00);
        sb.append(", mDisconnectionReason=");
        sb.append(this.A03);
        sb.append(", mLastConnectionMs=");
        sb.append(this.A01);
        sb.append(", mLastDisconnectMs=");
        sb.append(this.A02);
        sb.append('}');
        return sb.toString();
    }

    public C141421y(AnonymousClass22D r1, @Nullable EnumC142522n r2, long j, long j2) {
        this.A00 = r1;
        this.A03 = r2;
        this.A01 = j;
        this.A02 = j2;
    }
}
