package X;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: X.0nU  reason: invalid class name */
public class AnonymousClass0nU extends AnonymousClass0WG {
    @Override // X.AnonymousClass0WG
    @TargetApi(11)
    public final SharedPreferences A00(Context context, String str, boolean z) {
        int i = 0;
        if (z) {
            i = 4;
        }
        return context.getSharedPreferences(str, i);
    }
}
