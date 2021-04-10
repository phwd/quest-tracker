package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.pL  reason: case insensitive filesystem */
public abstract class AbstractC0943pL {
    public final JC A00;
    public final J4 A01;
    public final JB A02;

    public static final Integer A03(AbstractC0943pL pLVar) {
        Integer num;
        J4 j4 = pLVar.A01;
        synchronized (j4) {
            num = j4.A00;
        }
        return num;
    }

    public AbstractC0943pL(J4 j4, JC jc, JB jb) {
        this.A01 = j4;
        this.A00 = jc;
        this.A02 = jb;
    }

    public static List A04(Intent intent, Context context) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65600);
        if (queryIntentActivities == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(1);
        Iterator<T> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = ((ResolveInfo) it.next()).activityInfo;
            if (!(activityInfo == null || activityInfo.applicationInfo == null)) {
                arrayList.add(activityInfo);
            }
        }
        return arrayList;
    }

    public static List A05(Intent intent, Context context) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 65600);
        if (queryIntentServices == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(1);
        Iterator<T> it = queryIntentServices.iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = ((ResolveInfo) it.next()).serviceInfo;
            if (!(serviceInfo == null || serviceInfo.applicationInfo == null)) {
                arrayList.add(serviceInfo);
            }
        }
        return arrayList;
    }

    public final boolean A06() {
        if (A03(this) == AnonymousClass09.A00) {
            return true;
        }
        return false;
    }
}
