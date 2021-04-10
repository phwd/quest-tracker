package X;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public final class J3 {
    public static C0944pM A00 = new C0944pM();

    public static void A00(Intent intent, JC jc, boolean z) {
        Object[] objArr;
        String str;
        if (intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            Set<String> keySet = extras.keySet();
            ArrayList arrayList = new ArrayList();
            for (String str2 : keySet) {
                Object obj = extras.get(str2);
                if (obj != null && AnonymousClass2T.class.getClassLoader().equals(obj.getClass().getClassLoader())) {
                    arrayList.add(str2);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (!z) {
                    intent.removeExtra(str3);
                    objArr = new Object[]{str3, extras.get(str3)};
                    str = "Removed an internal class in a different-key intent: %s => %s";
                } else {
                    objArr = new Object[]{str3, extras.get(str3)};
                    str = "Found an internal class in a different-key intent but not removing: %s => %s";
                }
                jc.A4o("ExternalIntentSanitization", String.format(str, objArr), null);
            }
        }
    }
}
