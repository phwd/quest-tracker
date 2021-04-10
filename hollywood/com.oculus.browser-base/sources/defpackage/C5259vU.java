package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.text.TextUtils;
import java.util.Iterator;
import org.chromium.base.PackageManagerUtils;

/* renamed from: vU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5259vU {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11480a;
    public static final String b;
    public static C5259vU c;
    public final Context d;
    public final C1322Vq0 e = new C1322Vq0();
    public Boolean f;
    public String g;

    static {
        String format = String.format("%s.GsaPublicContentProvider", "com.google.android.googlequicksearchbox");
        f11480a = format;
        b = String.format("content://%s/publicvalue/roti_for_chrome_enabled", format);
    }

    public C5259vU(Context context) {
        this.d = context.getApplicationContext();
    }

    public static C5259vU b(Context context) {
        if (c == null) {
            c = new C5259vU(context);
        }
        return c;
    }

    public String a() {
        try {
            return this.d.getPackageManager().getPackageInfo("com.google.android.googlequicksearchbox", 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return true;
        }
        String[] split = str.split("\\.", -1);
        String[] split2 = str2.split("\\.", -1);
        int min = Math.min(split.length, split2.length);
        for (int i = 0; i < min; i++) {
            int parseInt = Integer.parseInt(split[i]);
            int parseInt2 = Integer.parseInt(split2[i]);
            if (parseInt < parseInt2) {
                return true;
            }
            if (parseInt > parseInt2) {
                return false;
            }
        }
        if (split.length < split2.length) {
            return true;
        }
        return false;
    }

    public boolean d() {
        Boolean bool = this.f;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        this.f = bool2;
        Intent intent = new Intent("com.google.android.googlequicksearchbox.TEXT_ASSIST");
        intent.setPackage("com.google.android.googlequicksearchbox");
        boolean z = false;
        if (PackageManagerUtils.c(intent, 0).size() == 0) {
            this.f = bool2;
        } else {
            if (AbstractC4652ru0.a(this.d, "com.google.android.googlequicksearchbox") >= 300401021) {
                if (AbstractC4652ru0.a(this.d, "com.google.android.gms") >= 6577010) {
                    z = true;
                }
                if (z) {
                    this.f = Boolean.TRUE;
                }
            }
            this.f = bool2;
        }
        return this.f.booleanValue();
    }

    public boolean e(Cursor cursor) {
        if (cursor == null) {
            AbstractC1220Ua0.a("GSAState", "Failed due to cursor being null.", new Object[0]);
            return false;
        } else if (!cursor.moveToFirst()) {
            AbstractC1220Ua0.a("GSAState", "Failed due cursor being empty.", new Object[0]);
            return false;
        } else if (cursor.getType(0) == 3) {
            return Boolean.parseBoolean(cursor.getString(0));
        } else {
            AbstractC1220Ua0.a("GSAState", "Failed due cursor having unexpected datatype (expected string).", new Object[0]);
            return false;
        }
    }

    public void f(String str) {
        this.g = str;
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((C0122Ca) uq0.next()).d();
            } else {
                return;
            }
        }
    }
}
