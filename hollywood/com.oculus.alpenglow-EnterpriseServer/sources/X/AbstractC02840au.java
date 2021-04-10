package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0au  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02840au implements AbstractC04930hy {
    public final AbstractC04970iB A00;
    public final AnonymousClass0i0 A01;
    public final C04960iA A02;

    public abstract boolean A06(Context context, PackageInfo packageInfo);

    @Override // X.AbstractC04930hy
    @Nullable
    public final Intent A2M(Intent intent, Context context) {
        return A2N(intent, context, null);
    }

    public static final Integer A01(AbstractC02840au r1) {
        Integer num;
        AnonymousClass0i0 r12 = r1.A01;
        synchronized (r12) {
            num = r12.A00;
        }
        return num;
    }

    public static String A02(@Nullable Intent intent) {
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

    public AbstractC02840au(AnonymousClass0i0 r1, AbstractC04970iB r2, C04960iA r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    public static List<ServiceInfo> A03(Intent intent, Context context, int i) {
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

    public static boolean A04(Intent intent, Context context) {
        if (intent.getComponent() == null || !intent.getComponent().getPackageName().equals(context.getPackageName())) {
            return false;
        }
        return true;
    }

    public final boolean A05() {
        if (A01(this) == AnonymousClass007.A00) {
            return true;
        }
        return false;
    }
}
