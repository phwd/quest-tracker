package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Zu  reason: invalid class name and case insensitive filesystem */
public final class C02220Zu {
    public final List<String> A00;

    public final String toString() {
        return TextUtils.join(",", this.A00.toArray());
    }

    public C02220Zu(List<String> list) {
        this.A00 = Collections.unmodifiableList(new ArrayList(list));
    }
}
