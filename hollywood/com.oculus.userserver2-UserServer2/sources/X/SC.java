package X;

import android.util.Log;
import javax.annotation.Nullable;

public final class SC implements AbstractC0201ew {
    @Override // X.AbstractC0201ew
    public final void A3L(String str) {
        Log.e("Security-LocalReporter", str);
    }

    @Override // X.AbstractC0201ew
    public final void A3M(String str, String str2, @Nullable Throwable th) {
        StringBuilder sb = new StringBuilder("category=");
        sb.append(str);
        sb.append(", message=");
        sb.append(str2);
        if (th != null) {
            sb.append(", cause=");
            sb.append(th.toString());
        }
        A3L(sb.toString());
    }
}
