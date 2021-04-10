package X;

import android.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.22y  reason: invalid class name and case insensitive filesystem */
public final class C143622y extends Pair<String, String> {
    public static final C143622y A01 = new C143622y("", "", RecyclerView.FOREVER_NS);
    public final long A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C143622y(String str, String str2, long j) {
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
