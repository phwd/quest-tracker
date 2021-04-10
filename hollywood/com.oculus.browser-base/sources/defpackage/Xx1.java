package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Base64;
import java.util.Map;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.ShortcutHelper;

/* renamed from: Xx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Xx1 {

    /* renamed from: a  reason: collision with root package name */
    public static Wx1 f9245a = new Wx1();
    public final String b;
    public final SharedPreferences c;

    public Xx1(String str) {
        this.b = str;
        Context applicationContext = ContextUtils.getApplicationContext();
        this.c = applicationContext.getSharedPreferences("webapp_" + str, 0);
    }

    public void a() {
        String c2 = c();
        if (c2 != null) {
            this.c.edit().remove("pending_update_file_path").apply();
            PostTask.b(C3070if1.b, new Vx1(c2), 0);
        }
    }

    public long b() {
        return this.c.getLong("last_used", 0);
    }

    public String c() {
        return this.c.getString("pending_update_file_path", null);
    }

    public String d() {
        return this.c.getString("webapk_package_name", null);
    }

    public final boolean e() {
        String d = d();
        return d != null && !d.startsWith("org.chromium.webapk");
    }

    public void f(boolean z) {
        if (!e()) {
            this.c.edit().putBoolean("should_force_update", z).apply();
        }
    }

    public void g(AbstractC3767mk mkVar) {
        boolean z;
        long j;
        C1761ay1 ay1 = new C1761ay1(mkVar);
        SharedPreferences.Editor edit = this.c.edit();
        boolean z2 = true;
        if (this.c.getString("url", "").equals("")) {
            edit.putString("url", ay1.f());
            z = true;
        } else {
            z = false;
        }
        if (this.c.getString("scope", "").equals("")) {
            edit.putString("scope", ay1.c().c);
            z = true;
        }
        if (this.c.getInt("version", 0) != 3) {
            edit.putInt("version", 3);
            if (!TextUtils.isEmpty(ay1.g())) {
                edit.putString("webapk_package_name", ay1.g());
                edit.putString("webapk_manifest_url", ay1.d());
                edit.putInt("webapk_version_code", ay1.h());
                try {
                    j = ContextUtils.getApplicationContext().getPackageManager().getPackageInfo(ay1.g(), 0).firstInstallTime;
                } catch (PackageManager.NameNotFoundException unused) {
                    j = 0;
                }
                edit.putLong("webapk_install_timestamp", j);
            } else {
                edit.putString("name", ay1.c().e);
                edit.putString("short_name", ay1.c().f);
                Zx1 zx1 = ay1.c().d;
                String str = zx1.f9389a;
                if (str == null) {
                    if (zx1.b == null) {
                        Bitmap bitmap = null;
                        if (str != null && zx1.e) {
                            Map map = ShortcutHelper.f10603a;
                            if (!TextUtils.isEmpty(str)) {
                                byte[] decode = Base64.decode(str, 0);
                                bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                            }
                        } else if (!(zx1.c == null || zx1.d == 0)) {
                            try {
                                BitmapDrawable bitmapDrawable = (BitmapDrawable) AbstractC3153j7.c(ContextUtils.getApplicationContext().getPackageManager().getResourcesForApplication(zx1.c), zx1.d);
                                if (bitmapDrawable != null) {
                                    bitmap = bitmapDrawable.getBitmap();
                                }
                            } catch (Exception unused2) {
                            }
                        }
                        zx1.b = bitmap;
                    }
                    zx1.f9389a = ShortcutHelper.b(zx1.b);
                }
                edit.putString("icon", zx1.f9389a);
                edit.putInt("display_mode", ay1.a());
                edit.putInt("orientation", ay1.c().h);
                edit.putLong("theme_color", ay1.e());
                Integer num = ay1.c().j;
                edit.putLong("background_color", num != null ? (long) num.intValue() : 2147483648L);
                edit.putBoolean("is_icon_generated", ay1.c().k);
                edit.putBoolean("is_icon_adaptive", ay1.c().l);
                edit.putInt("source", ay1.c().i);
            }
        } else {
            z2 = z;
        }
        if (z2) {
            edit.apply();
        }
    }
}
