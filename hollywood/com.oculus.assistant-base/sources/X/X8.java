package X;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class X8 extends AbstractC0943pL {
    public static int A00() {
        return 16;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if ((r2 & r1) != r1) goto L_0x00ba;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A02(X.X8 r15, android.content.Intent r16, android.content.Context r17, android.content.pm.ComponentInfo r18, java.lang.String r19) {
        /*
        // Method dump skipped, instructions count: 187
        */
        throw new UnsupportedOperationException("Method not decompiled: X.X8.A02(X.X8, android.content.Intent, android.content.Context, android.content.pm.ComponentInfo, java.lang.String):boolean");
    }

    public X8(J4 j4, JC jc, JB jb) {
        super(j4, jc, jb);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: android.content.Intent[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static Intent A01(X8 x8, Intent intent, List list, boolean z) {
        if (list.isEmpty()) {
            x8.A00.A4o("AccessibleByAnyAppIntentScope", "No matching public components.", null);
            return null;
        }
        if (z) {
            if (list.size() > 1) {
                J3.A00(intent, x8.A00, x8.A06());
                ArrayList arrayList = new ArrayList(list.size());
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ComponentInfo componentInfo = (ComponentInfo) it.next();
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
                    intent2.setPackage(componentInfo.packageName);
                    arrayList.add(intent2);
                }
                Parcelable[] parcelableArr = new Intent[(arrayList.size() - 1)];
                int i = 0;
                while (i < arrayList.size() - 1) {
                    int i2 = i + 1;
                    parcelableArr[i] = arrayList.get(i2);
                    i = i2;
                }
                Intent createChooser = Intent.createChooser((Intent) arrayList.get(0), "Choose an app to launch.");
                createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", parcelableArr);
                return createChooser;
            }
            ComponentInfo componentInfo2 = (ComponentInfo) list.get(0);
            intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
        }
        J3.A00(intent, x8.A00, x8.A06());
        return intent;
    }
}
