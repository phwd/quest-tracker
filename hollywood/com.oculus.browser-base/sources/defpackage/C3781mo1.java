package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: mo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3781mo1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10447a;
    public final Map b = new HashMap();

    public C3781mo1(Context context) {
        this.f10447a = context.getApplicationContext();
    }

    public final Intent a(Context context, Uri uri, Set set, boolean z) {
        if (set == null || set.size() == 0) {
            return null;
        }
        Intent intent = new Intent();
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        String str = null;
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
            String str2 = resolveInfo.activityInfo.packageName;
            Iterator it = set.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((C1707aj1) it.next()).a(str2, context.getPackageManager())) {
                        str = str2;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (str == null) {
            if (z) {
                Log.w("TWAConnectionPool", "No TWA candidates for " + uri + " have been registered.");
            }
            return null;
        }
        Intent intent2 = new Intent();
        intent2.setPackage(str);
        intent2.setAction("android.support.customtabs.trusted.TRUSTED_WEB_ACTIVITY_SERVICE");
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent2, 131072);
        if (resolveService == null) {
            if (z) {
                Log.w("TWAConnectionPool", "Could not find TWAService for " + str);
            }
            return null;
        }
        if (z) {
            StringBuilder i = AbstractC2531fV.i("Found ");
            i.append(resolveService.serviceInfo.name);
            i.append(" to handle request for ");
            i.append(uri);
            Log.i("TWAConnectionPool", i.toString());
        }
        Intent intent3 = new Intent();
        intent3.setComponent(new ComponentName(str, resolveService.serviceInfo.name));
        return intent3;
    }
}
