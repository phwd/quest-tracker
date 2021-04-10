package X;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Set;

/* renamed from: X.0hx  reason: invalid class name and case insensitive filesystem */
public final class C04920hx {
    @VisibleForTesting
    public static C02830at A00 = new C02830at();

    public static void A00(Intent intent, AbstractC04970iB r8, boolean z) {
        Object[] objArr;
        String str;
        if (intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            Set<String> keySet = extras.keySet();
            ArrayList<String> arrayList = new ArrayList();
            for (String str2 : keySet) {
                Object obj = extras.get(str2);
                if (obj != null && AnonymousClass0GP.class.getClassLoader().equals(obj.getClass().getClassLoader())) {
                    arrayList.add(str2);
                }
            }
            for (String str3 : arrayList) {
                if (!z) {
                    intent.removeExtra(str3);
                    objArr = new Object[]{str3, extras.get(str3)};
                    str = "Removed an internal class in a different-key intent: %s => %s";
                } else {
                    objArr = new Object[]{str3, extras.get(str3)};
                    str = "Found an internal class in a different-key intent but not removing: %s => %s";
                }
                r8.A7Q("ExternalIntentSanitization", String.format(str, objArr), null);
            }
        }
    }
}
