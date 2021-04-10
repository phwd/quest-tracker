package X;

import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.0Y3  reason: invalid class name */
public final class AnonymousClass0Y3 extends Pair<String, String> {
    public static final AnonymousClass0Y3 A01 = new AnonymousClass0Y3("", "", Long.MAX_VALUE);
    public final long A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Y3(String str, String str2, long j) {
        super(str == null ? "" : str, str2 == null ? "" : str2);
        this.A00 = j;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MqttDeviceIdAndSecret{id=");
        sb.append((String) this.first);
        sb.append("secret=");
        sb.append((String) this.second);
        sb.append("mTimestamp=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }
}
