package X;

import android.util.Log;

/* renamed from: X.pN  reason: case insensitive filesystem */
public final class C0945pN implements JC {
    @Override // X.JC
    public final void A4o(String str, String str2, Throwable th) {
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
