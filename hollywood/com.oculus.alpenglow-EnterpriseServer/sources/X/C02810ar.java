package X;

import android.util.Log;
import javax.annotation.Nullable;

/* renamed from: X.0ar  reason: invalid class name and case insensitive filesystem */
public final class C02810ar implements AbstractC04970iB {
    @Override // X.AbstractC04970iB
    public final void A7P(String str) {
        Log.e("Security-LocalReporter", str);
    }

    @Override // X.AbstractC04970iB
    public final void A7Q(String str, String str2, @Nullable Throwable th) {
        StringBuilder sb = new StringBuilder("category=");
        sb.append(str);
        sb.append(", message=");
        sb.append(str2);
        if (th != null) {
            sb.append(", cause=");
            sb.append(th.toString());
        }
        A7P(sb.toString());
    }
}
