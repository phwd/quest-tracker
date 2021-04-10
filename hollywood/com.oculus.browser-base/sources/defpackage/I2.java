package defpackage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import org.chromium.base.ContextUtils;

/* renamed from: I2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I2 implements AbstractC2809h6 {
    public Handler F = new Handler();
    public SparseArray G = new SparseArray();
    public int H;
    public WeakReference I;

    public I2(WeakReference weakReference) {
        this.I = weakReference;
    }

    @Override // defpackage.AbstractC2809h6
    public final boolean B(int i, String[] strArr, int[] iArr) {
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (iArr[i2] == 0) {
                edit.remove(b(strArr[i2]));
            } else {
                edit.putBoolean(b(strArr[i2]), true);
            }
        }
        edit.apply();
        HB0 hb0 = (HB0) this.G.get(i);
        this.G.delete(i);
        if (hb0 == null) {
            return false;
        }
        hb0.b(strArr, iArr);
        return true;
    }

    @Override // defpackage.AbstractC2809h6
    public final boolean E(String str) {
        Activity activity = (Activity) this.I.get();
        if (activity == null) {
            return false;
        }
        return activity.getPackageManager().isPermissionRevokedByPolicy(str, activity.getPackageName());
    }

    public final void a(String str) {
        String b = b(str);
        SharedPreferences sharedPreferences = AbstractC3983nz.f10523a;
        if (sharedPreferences.contains(b)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(b);
            edit.apply();
        }
    }

    public final String b(String str) {
        StringBuilder i = AbstractC2531fV.i("HasRequestedAndroidPermission::");
        i.append(c(str));
        return i.toString();
    }

    public final String c(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            return str;
        }
        try {
            PermissionInfo permissionInfo = ContextUtils.getApplicationContext().getPackageManager().getPermissionInfo(str, 128);
            return !TextUtils.isEmpty(permissionInfo.group) ? permissionInfo.group : str;
        } catch (PackageManager.NameNotFoundException unused) {
            return str;
        }
    }

    @Override // defpackage.AbstractC2809h6
    public final boolean canRequestPermission(String str) {
        boolean z;
        if (hasPermission(str)) {
            return true;
        }
        if (E(str)) {
            return false;
        }
        Activity activity = (Activity) this.I.get();
        if (activity == null) {
            z = false;
        } else {
            z = activity.shouldShowRequestPermissionRationale(str);
        }
        if (!z) {
            return true ^ AbstractC3983nz.f10523a.getBoolean(b(str), false);
        }
        a(str);
        return true;
    }

    public final boolean d(String[] strArr, HB0 hb0) {
        boolean z;
        int i = this.H;
        int i2 = i + 1000;
        this.H = (i + 1) % 100;
        this.G.put(i2, hb0);
        Activity activity = (Activity) this.I.get();
        if (activity == null) {
            z = false;
        } else {
            activity.requestPermissions(strArr, i2);
            z = true;
        }
        if (z) {
            return true;
        }
        this.G.delete(i2);
        return false;
    }

    @Override // defpackage.AbstractC2809h6
    public final boolean hasPermission(String str) {
        boolean z = AbstractC3153j7.a(ContextUtils.getApplicationContext(), str, Process.myPid(), Process.myUid()) == 0;
        if (z) {
            a(str);
        }
        return z;
    }

    @Override // defpackage.AbstractC2809h6
    public final void i(String[] strArr, HB0 hb0) {
        if (!d(strArr, hb0)) {
            this.F.post(new RunnableC2980i6(this, strArr, hb0));
        }
    }
}
