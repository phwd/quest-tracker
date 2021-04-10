package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Pair;
import java.util.Iterator;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: LT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class LT0 extends IT0 {
    public static ComponentName e() {
        PU0 pu0 = NU0.f8549a;
        String i = pu0.i("last_shared_package_name", null);
        String i2 = pu0.i("last_shared_class_name", null);
        if (i == null || i2 == null) {
            return null;
        }
        return new ComponentName(i, i2);
    }

    public static Intent f(Uri uri) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(524288);
        intent.setType("image/jpeg");
        intent.addFlags(1);
        intent.putExtra("android.intent.extra.STREAM", uri);
        return intent;
    }

    public static Pair g(Intent intent) {
        boolean z;
        CharSequence charSequence;
        Drawable drawable;
        Throwable th;
        ComponentName e = e();
        boolean z2 = true;
        if (e != null) {
            intent.setPackage(e.getPackageName());
            Iterator it = PackageManagerUtils.c(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityInfo activityInfo = ((ResolveInfo) it.next()).activityInfo;
                if (e.equals(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name))) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        CharSequence charSequence2 = null;
        if (z) {
            PackageManager packageManager = ContextUtils.getApplicationContext().getPackageManager();
            try {
                P21 f0 = P21.f0();
                try {
                    drawable = packageManager.getActivityIcon(e);
                    try {
                        charSequence = packageManager.getActivityInfo(e, 0).loadLabel(packageManager);
                        AbstractC3100ip1.f10165a.a("Android.IsLastSharedAppInfoRetrieved", z2);
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            f0.close();
                        } catch (Throwable th3) {
                            try {
                                AbstractC0754Mh1.f8495a.a(th, th3);
                            } catch (PackageManager.NameNotFoundException unused) {
                                charSequence = charSequence2;
                                charSequence2 = drawable;
                                z2 = false;
                                AbstractC3100ip1.f10165a.a("Android.IsLastSharedAppInfoRetrieved", z2);
                                return new Pair(charSequence2, charSequence);
                            }
                        }
                        throw th;
                    }
                    try {
                        f0.close();
                        charSequence2 = drawable;
                    } catch (PackageManager.NameNotFoundException unused2) {
                        charSequence2 = charSequence;
                        charSequence = charSequence2;
                        charSequence2 = drawable;
                        z2 = false;
                        AbstractC3100ip1.f10165a.a("Android.IsLastSharedAppInfoRetrieved", z2);
                        return new Pair(charSequence2, charSequence);
                    }
                } catch (Throwable th4) {
                    th = th4;
                    drawable = null;
                    f0.close();
                    throw th;
                }
            } catch (PackageManager.NameNotFoundException unused3) {
                charSequence = null;
                z2 = false;
                AbstractC3100ip1.f10165a.a("Android.IsLastSharedAppInfoRetrieved", z2);
                return new Pair(charSequence2, charSequence);
            }
        } else {
            charSequence = null;
        }
        return new Pair(charSequence2, charSequence);
    }

    public static void h(WindowAndroid windowAndroid, ComponentName componentName, Uri uri) {
        Intent f = f(uri);
        if (componentName == null) {
            Object obj = KT0.f8367a;
            KT0.b(windowAndroid, f, new JT0(null));
            return;
        }
        f.setComponent(componentName);
        IT0.a(windowAndroid, f, null);
    }

    public static void i(C2189dU0 du0) {
        ComponentName e = e();
        if (e != null) {
            Intent c = IT0.c(du0);
            c.addFlags(50331648);
            c.setComponent(e);
            IT0.a(du0.f9785a, c, null);
        }
    }

    public static void j(C2189dU0 du0, boolean z) {
        if (z) {
            du0.i = new JT0(du0.i);
        }
        Object obj = KT0.f8367a;
        KT0.b(du0.f9785a, IT0.c(du0), du0.i);
    }
}
