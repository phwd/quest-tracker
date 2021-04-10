package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class X3 extends AbstractC0943pL {
    public X3(J4 j4, JC jc, JB jb) {
        super(j4, jc, jb);
    }

    public static Intent A00(X3 x3, Intent intent, Context context, List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ComponentInfo componentInfo = (ComponentInfo) it.next();
            ApplicationInfo applicationInfo = componentInfo.applicationInfo;
            if (applicationInfo != null) {
                String str = applicationInfo.packageName;
                if (!str.equals(context.getPackageName())) {
                    if (x3.A06()) {
                        x3.A00.A4o("InternalIntentScope", AnonymousClass08.A04("Detected different package name component but fail open: ", str), null);
                    }
                }
                arrayList.add(componentInfo);
            }
        }
        if (arrayList.isEmpty()) {
            x3.A00.A4o("InternalIntentScope", "No matching internal components", null);
            return null;
        }
        ComponentInfo componentInfo2 = (ComponentInfo) arrayList.get(0);
        intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
        return intent;
    }
}
