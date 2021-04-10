package X;

import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.0wE  reason: invalid class name and case insensitive filesystem */
public final class C07920wE extends Pair<String, String> {
    public static final C07920wE A01 = new C07920wE("", "", Long.MAX_VALUE);
    public final long A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C07920wE(String str, String str2, long j) {
        super(str == null ? "" : str, str2 == null ? "" : str2);
        this.A00 = j;
    }

    public final String toString() {
        return "MqttDeviceIdAndSecret{id=" + ((String) this.first) + "secret=" + ((String) this.second) + "mTimestamp=" + this.A00 + '}';
    }
}
