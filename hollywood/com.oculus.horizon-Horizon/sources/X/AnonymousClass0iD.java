package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0iD  reason: invalid class name */
public abstract class AnonymousClass0iD implements AbstractC02610as {
    public final AnonymousClass0b1 A00;
    public final C02620au A01;
    public final C02660b0 A02;

    public abstract boolean A08(Context context, PackageInfo packageInfo);

    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2V(Intent intent, Context context) {
        return A2W(intent, context, null);
    }

    public static final Integer A02(AnonymousClass0iD r1) {
        Integer num;
        C02620au r12 = r1.A01;
        synchronized (r12) {
            num = r12.A00;
        }
        return num;
    }

    public static String A03(@Nullable Intent intent) {
        if (intent == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("intent(");
        sb.append("action = ");
        sb.append(intent.getAction());
        sb.append(", data= ");
        sb.append(intent.getData());
        sb.append(", type= ");
        sb.append(intent.getType());
        if (intent.getComponent() != null) {
            sb.append(", component = ");
            sb.append(intent.getComponent());
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            sb.append(", extras = [");
            for (String str : extras.keySet()) {
                sb.append(str);
                sb.append(" = ");
                sb.append(extras.get(str));
                sb.append(", ");
            }
            sb.append("]");
        }
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass0iD(C02620au r1, AnonymousClass0b1 r2, C02660b0 r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    public static List<ActivityInfo> A04(Intent intent, Context context, int i) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, i);
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

    public static List<ServiceInfo> A05(Intent intent, Context context, int i) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, i);
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

    public static boolean A06(Intent intent, Context context) {
        if (intent.getComponent() == null || !intent.getComponent().getPackageName().equals(context.getPackageName())) {
            return false;
        }
        return true;
    }

    public final boolean A07() {
        if (A02(this) == AnonymousClass007.A00) {
            return true;
        }
        return false;
    }
}
