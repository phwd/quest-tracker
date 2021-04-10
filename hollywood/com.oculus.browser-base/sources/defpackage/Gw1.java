package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

/* renamed from: Gw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Gw1 implements Yw1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f8123a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Hw1 c;

    public Gw1(Iw1 iw1, Context context, String str, Hw1 hw1) {
        this.f8123a = context;
        this.b = str;
        this.c = hw1;
    }

    @Override // defpackage.Yw1
    public void a(IBinder iBinder) {
        Object obj;
        Bundle bundle;
        String packageName = this.f8123a.getPackageName();
        String str = null;
        if (iBinder == null) {
            try {
                bundle = this.f8123a.getPackageManager().getApplicationInfo(this.b, 128).metaData;
            } catch (PackageManager.NameNotFoundException unused) {
                bundle = null;
            }
            if (bundle != null && bundle.getInt("org.chromium.webapk.shell_apk.shellApkVersion") < 6) {
                str = bundle.getString("org.chromium.webapk.shell_apk.runtimeHost");
            }
            this.c.a(TextUtils.equals(str, packageName), str);
            return;
        }
        int i = MY.f8481a;
        IInterface queryLocalInterface = iBinder.queryLocalInterface("org.chromium.webapk.lib.common.identity_service.IIdentityService");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof NY)) {
            obj = new LY(iBinder);
        } else {
            obj = (NY) queryLocalInterface;
        }
        try {
            str = ((LY) obj).c();
        } catch (RemoteException unused2) {
            Log.w("WebApkIdentityService", "Failed to get runtime host from the Identity service.");
        }
        this.c.a(TextUtils.equals(str, packageName), str);
    }
}
