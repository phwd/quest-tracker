package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class X2 extends AbstractC0943pL {
    public final C0200Jn A00;
    public final String A01 = "FamilyIntentScope";
    public final boolean A02;

    public static Intent A00(X2 x2, Intent intent, Context context, String str, List list) {
        boolean z;
        C0195Ji ji;
        if (x2.A02) {
            C0199Jm.A02(intent, context, str, ((AbstractC0943pL) x2).A00);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ComponentInfo componentInfo = (ComponentInfo) it.next();
            ApplicationInfo applicationInfo = componentInfo.applicationInfo;
            String str2 = applicationInfo.packageName;
            C0200Jn jn = x2.A00;
            if (!TextUtils.isEmpty(str2) && jn.A01.isEmpty()) {
                Iterator it2 = jn.A00.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (((Set) it2.next()).contains(str2)) {
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                try {
                    C0194Jh A002 = C0200Jn.A00(applicationInfo.uid, context);
                    boolean contains = C0193Jg.A10.contains(C0198Jl.A03(C0198Jl.A01(C0198Jl.A00(context, context.getPackageName()))));
                    if (A002 != null && (ji = A002.A01) != null) {
                        Iterator it3 = jn.A01.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                if (C0200Jn.A02(ji, (C0195Ji) it3.next(), contains)) {
                                    break;
                                }
                            } else {
                                Map map = jn.A00;
                                Iterator it4 = map.keySet().iterator();
                                while (true) {
                                    if (!it4.hasNext()) {
                                        break;
                                    }
                                    C0195Ji ji2 = (C0195Ji) it4.next();
                                    if (C0200Jn.A02(ji, ji2, contains)) {
                                        for (Object obj : A002.A04) {
                                            if (((Set) map.get(ji2)).contains(obj)) {
                                                break;
                                            }
                                        }
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    if (x2.A06()) {
                        ((AbstractC0943pL) x2).A00.A4o(x2.A01, AnonymousClass08.A04(str2, " is not an app matching the targeted app filter, but fail-open."), null);
                    }
                } catch (SecurityException e) {
                    ((AbstractC0943pL) x2).A00.A4o(x2.A01, AnonymousClass08.A04("Unexpected exception in checking trusted app for ", str2), e);
                    if (AbstractC0943pL.A03(x2) == AnonymousClass09.A0J) {
                    }
                }
            } else if (x2.A06()) {
                ((AbstractC0943pL) x2).A00.A4o(x2.A01, AnonymousClass08.A04(str2, " is not an app matching the targeted app filter, but fail-open."), null);
            }
            arrayList.add(componentInfo);
        }
        if (arrayList.isEmpty()) {
            ((AbstractC0943pL) x2).A00.A4o(x2.A01, "No matching packages available.", null);
            return null;
        }
        ComponentInfo componentInfo2 = (ComponentInfo) arrayList.get(0);
        if (arrayList.size() > 1) {
            Iterator it5 = arrayList.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    break;
                }
                ComponentInfo componentInfo3 = (ComponentInfo) it5.next();
                try {
                    String str3 = componentInfo3.packageName;
                    ApplicationInfo applicationInfo2 = C0198Jl.A00(context, context.getPackageName()).applicationInfo;
                    if (applicationInfo2 != null) {
                        ApplicationInfo applicationInfo3 = C0198Jl.A00(context, str3).applicationInfo;
                        if (applicationInfo3 != null) {
                            int i = applicationInfo2.uid;
                            int i2 = applicationInfo3.uid;
                            try {
                                int checkSignatures = context.getPackageManager().checkSignatures(i, i2);
                                if (!(i == i2 || checkSignatures == 0)) {
                                    componentInfo2 = componentInfo3;
                                    break;
                                }
                            } catch (RuntimeException e2) {
                                throw new SecurityException(e2);
                            }
                        } else {
                            throw new C0201Jo(str3);
                        }
                    } else {
                        throw new C0201Jo(context.getPackageName());
                    }
                } catch (SecurityException e3) {
                    ((AbstractC0943pL) x2).A00.A4o(x2.A01, AnonymousClass08.A04("Error verifying the signature for ", componentInfo3.packageName), e3);
                }
            }
        }
        intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
        return intent;
    }

    public X2(J4 j4, JC jc, JB jb, C0200Jn jn) {
        super(j4, jc, jb);
        this.A00 = jn;
        this.A02 = true;
    }
}
