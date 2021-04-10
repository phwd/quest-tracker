package X;

import android.util.Log;
import javax.annotation.Nullable;

/* renamed from: X.0kj  reason: invalid class name and case insensitive filesystem */
public final class C03000kj implements AbstractC02660jW {
    @Override // X.AbstractC02660jW
    public final void report(String str) {
        Log.e("Security-LocalReporter", str);
    }

    @Override // X.AbstractC02660jW
    public final void report(String str, String str2, @Nullable Throwable th) {
        StringBuilder sb = new StringBuilder("category=");
        sb.append(str);
        sb.append(", message=");
        sb.append(str2);
        if (th != null) {
            sb.append(", cause=");
            sb.append(th.toString());
        }
        report(sb.toString());
    }
}
