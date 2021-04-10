package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* renamed from: tD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4881tD1 {

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f11331a;
    public Context b;
    public final WH1 c;
    public final Map d = new C4931ta();

    public C4881tD1(Context context) {
        WH1 wh1 = new WH1();
        this.b = context;
        this.f11331a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.c = wh1;
        Context context2 = this.b;
        Object obj = K2.f8337a;
        File file = new File(context2.getNoBackupFilesDir(), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !this.f11331a.getAll().isEmpty()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    L20.b(this.b, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    String valueOf = String.valueOf(e.getMessage());
                    if (valueOf.length() != 0) {
                        "Error creating file in no backup dir: ".concat(valueOf);
                    } else {
                        new String("Error creating file in no backup dir: ");
                    }
                }
            }
        }
    }

    public static String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + String.valueOf(str2).length() + String.valueOf(str).length() + 4);
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    public static String b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + String.valueOf(str2).length() + String.valueOf(str).length() + 14);
        sb.append(str);
        sb.append("|T-timestamp|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    public static String c(String str, String str2) {
        StringBuilder sb = new StringBuilder(str2.length() + String.valueOf(str).length() + 3);
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    public final synchronized C1822bI1 d(String str) {
        C1822bI1 bi1;
        C1822bI1 bi12 = (C1822bI1) this.d.get(str);
        if (bi12 != null) {
            return bi12;
        }
        try {
            WH1 wh1 = this.c;
            Context context = this.b;
            bi1 = wh1.h(context, str);
            if (bi1 == null) {
                bi1 = wh1.f(context, str);
            }
        } catch (C2847hI1 unused) {
            Log.w("InstanceID/Store", "Stored data is corrupt, generating new identity");
            L20.b(this.b, this);
            bi1 = this.c.f(this.b, str);
        }
        this.d.put(str, bi1);
        return bi1;
    }
}
