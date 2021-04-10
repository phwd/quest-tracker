package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Deprecated
/* renamed from: D20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D20 {

    /* renamed from: a  reason: collision with root package name */
    public static final RC1 f7857a = FC1.b().a();
    public static Map b = new C4931ta();
    public static final long c = TimeUnit.DAYS.toMillis(7);
    public static C4881tD1 d;
    public static YC1 e;
    public static String f;
    public Context g;
    public String h = "";

    public D20(Context context, String str) {
        this.g = context.getApplicationContext();
        this.h = str;
    }

    public static synchronized D20 b(Context context, Bundle bundle) {
        D20 d20;
        synchronized (D20.class) {
            String string = bundle.getString("subtype");
            if (string == null) {
                string = "";
            }
            Context applicationContext = context.getApplicationContext();
            if (d == null) {
                String packageName = applicationContext.getPackageName();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 73);
                sb.append("Instance ID SDK is deprecated, ");
                sb.append(packageName);
                sb.append(" should update to use Firebase Instance ID");
                Log.w("InstanceID", sb.toString());
                d = new C4881tD1(applicationContext);
                e = new YC1(applicationContext);
            }
            f = Integer.toString(f(applicationContext));
            d20 = (D20) b.get(string);
            if (d20 == null) {
                d20 = new D20(applicationContext, string);
                b.put(string, d20);
            }
        }
        return d20;
    }

    public static String d(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("InstanceID", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    public static int f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return 0;
        }
    }

    @Deprecated
    public void a() {
        e("*", "*", null);
        C4881tD1 td1 = d;
        String str = this.h;
        synchronized (td1) {
            td1.d.remove(str);
        }
        File k = WH1.k(td1.b, str);
        if (k.exists()) {
            k.delete();
        }
        String concat = String.valueOf(str).concat("|");
        synchronized (td1) {
            SharedPreferences.Editor edit = td1.f11331a.edit();
            for (String str2 : td1.f11331a.getAll().keySet()) {
                if (str2.startsWith(concat)) {
                    edit.remove(str2);
                }
            }
            edit.commit();
        }
    }

    public final KeyPair c() {
        return d.d(this.h).f9531a;
    }

    public final void e(String str, String str2, Bundle bundle) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            C4881tD1 td1 = d;
            String str3 = this.h;
            synchronized (td1) {
                SharedPreferences.Editor edit = td1.f11331a.edit();
                edit.remove(C4881tD1.a(str3, str, str2));
                edit.remove(C4881tD1.b(str3, str, str2));
                edit.commit();
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("sender", str);
            if (str2 != null) {
                bundle2.putString("scope", str2);
            }
            bundle2.putString("subscription", str);
            bundle2.putString("delete", "1");
            bundle2.putString("X-delete", "1");
            bundle2.putString("subtype", "".equals(this.h) ? str : this.h);
            if (!"".equals(this.h)) {
                str = this.h;
            }
            bundle2.putString("X-subtype", str);
            YC1.g(e.a(bundle2, c()));
            return;
        }
        throw new IOException("MAIN_THREAD");
    }
}
