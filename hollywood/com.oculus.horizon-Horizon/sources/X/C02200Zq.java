package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Zq  reason: invalid class name and case insensitive filesystem */
public final class C02200Zq {
    public final List<SubscribeTopic> A00;

    public final String toString() {
        return TextUtils.join(",", this.A00.toArray());
    }

    public C02200Zq(List<SubscribeTopic> list) {
        this.A00 = Collections.unmodifiableList(new ArrayList(list));
    }
}
