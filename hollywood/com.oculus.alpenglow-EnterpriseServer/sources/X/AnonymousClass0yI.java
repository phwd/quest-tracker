package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yI  reason: invalid class name */
public final class AnonymousClass0yI {
    public final List<String> A00;

    public final String toString() {
        return TextUtils.join(",", this.A00.toArray());
    }

    public AnonymousClass0yI(List<String> list) {
        this.A00 = Collections.unmodifiableList(new ArrayList(list));
    }
}
