package X;

import android.util.Log;
import javax.annotation.Nullable;

public final class XS implements AbstractC0382ge {
    @Override // X.AbstractC0382ge
    public final void A4i(String str, String str2, @Nullable Throwable th) {
        StringBuilder sb = new StringBuilder("category=");
        sb.append(str);
        sb.append(", message=");
        sb.append(str2);
        if (th != null) {
            sb.append(", cause=");
            sb.append(th.toString());
        }
        Log.e("Security-LocalReporter", sb.toString());
    }
}
