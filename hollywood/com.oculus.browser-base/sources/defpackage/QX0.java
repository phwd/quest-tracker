package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Process;
import android.text.style.ForegroundColorSpan;
import androidx.preference.Preference;
import com.oculus.browser.R;
import com.oculus.os.Version;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: QX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QX0 {

    /* renamed from: a  reason: collision with root package name */
    public final BrowserContextHandle f8767a;
    public int b;
    public String c;

    public QX0(BrowserContextHandle browserContextHandle, int i, String str) {
        this.f8767a = browserContextHandle;
        this.b = i;
        this.c = str;
    }

    public static boolean a() {
        return N.MK7GTxrW("SubresourceFilter");
    }

    public static int c(int i) {
        switch (i) {
            case 1:
                return 26;
            case 2:
                return 58;
            case 3:
                return 13;
            case 4:
                return 22;
            case 5:
                return 44;
            case 6:
                return 10;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                return 54;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                return 0;
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                return 5;
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                return 40;
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                return 2;
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                return 9;
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                return 52;
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                return 6;
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                return 4;
            case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                return 16;
            case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                return 33;
            case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                return 31;
            case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                return 37;
            case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                return 21;
            case Version.VERSION_21 /*{ENCODED_INT: 21}*/:
                return 57;
            default:
                return -1;
        }
    }

    public static QX0 d(BrowserContextHandle browserContextHandle, int i) {
        for (int i2 = 0; i2 < 23; i2++) {
            if (c(i2) == i) {
                return f(browserContextHandle, i2);
            }
        }
        return null;
    }

    public static QX0 e(BrowserContextHandle browserContextHandle, String str) {
        for (int i = 0; i < 23; i++) {
            if (p(i).equals(str)) {
                return f(browserContextHandle, i);
            }
        }
        return null;
    }

    public static QX0 f(BrowserContextHandle browserContextHandle, int i) {
        if (i == 9) {
            return new C0428Ha0(browserContextHandle);
        }
        if (i == 13) {
            return new C4293po0(browserContextHandle);
        }
        if (i == 14) {
            return new C5997zp0(browserContextHandle);
        }
        String str = "android.permission.CAMERA";
        if (i != 6) {
            if (i == 12) {
                str = "android.permission.RECORD_AUDIO";
            } else if (i != 2) {
                str = "";
            }
        }
        return new QX0(browserContextHandle, i, str);
    }

    public static String p(int i) {
        switch (i) {
            case 0:
                return "all_sites";
            case 1:
                return "ads";
            case 2:
                return "augmented_reality";
            case 3:
                return "automatic_downloads";
            case 4:
                return "background_sync";
            case 5:
                return "bluetooth_scanning";
            case 6:
                return "camera";
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                return "clipboard";
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                return "cookies";
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                return "device_location";
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                return "idle_detection";
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                return "javascript";
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                return "microphone";
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                return "nfc";
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                return "notifications";
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                return "popups";
            case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                return "protected_content";
            case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                return "sensors";
            case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                return "sound";
            case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                return "usb";
            case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                return "bluetooth";
            case Version.VERSION_21 /*{ENCODED_INT: 21}*/:
                return "virtual_reality";
            case Version.VERSION_22 /*{ENCODED_INT: 22}*/:
                return "use_storage";
            default:
                return "";
        }
    }

    public void b(Preference preference, Preference preference2, Context context, boolean z, String str) {
        Intent intent;
        Drawable j;
        if (g(context)) {
            intent = null;
        } else {
            intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(new Uri.Builder().scheme("package").opaquePart(context.getPackageName()).build());
        }
        Intent k = k(context);
        boolean z2 = !z;
        int i = i();
        int i2 = i == 5 ? R.string.f46830_resource_name_obfuscated_RES_2131952000 : i == 9 ? R.string.f46840_resource_name_obfuscated_RES_2131952001 : i == 10 ? R.string.f46780_resource_name_obfuscated_RES_2131951995 : i == 58 ? R.string.f46770_resource_name_obfuscated_RES_2131951994 : i == 6 ? R.string.f46870_resource_name_obfuscated_RES_2131952004 : R.string.f46880_resource_name_obfuscated_RES_2131952005;
        Resources resources = context.getResources();
        if (z2) {
            i2 = R.string.f46890_resource_name_obfuscated_RES_2131952006;
        }
        String string = resources.getString(i2, str);
        String l = l(context);
        String m = m(context);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800));
        if (intent != null) {
            preference.V(FY0.a(string, new EY0("<link>", "</link>", foregroundColorSpan)));
            preference.R = intent;
            if (!z && preference.P != (j = j(context))) {
                preference.P = j;
                preference.O = 0;
                preference.s();
            }
        }
        if (!s()) {
            preference2.V(m);
            Drawable j2 = j(context);
            if (preference2.P != j2) {
                preference2.P = j2;
                preference2.O = 0;
                preference2.s();
            }
        } else if (k != null) {
            preference2.V(FY0.a(l, new EY0("<link>", "</link>", foregroundColorSpan)));
            preference2.R = k;
            if (z) {
                return;
            }
            if (intent == null) {
                Drawable j3 = j(context);
                if (preference2.P != j3) {
                    preference2.P = j3;
                    preference2.O = 0;
                    preference2.s();
                    return;
                }
                return;
            }
            ColorDrawable colorDrawable = new ColorDrawable(0);
            if (preference2.P != colorDrawable) {
                preference2.P = colorDrawable;
                preference2.O = 0;
                preference2.s();
            }
        }
    }

    public boolean g(Context context) {
        if (!this.c.isEmpty() && AbstractC3153j7.a(context, this.c, Process.myPid(), Process.myUid()) != 0) {
            return false;
        }
        return true;
    }

    public boolean h() {
        return true;
    }

    public int i() {
        return c(this.b);
    }

    public Drawable j(Context context) {
        Drawable c2 = AbstractC3153j7.c(context.getResources(), R.drawable.f29210_resource_name_obfuscated_RES_2131230961);
        c2.mutate();
        c2.setColorFilter(context.getResources().getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800), PorterDuff.Mode.SRC_IN);
        return c2;
    }

    public Intent k(Context context) {
        return null;
    }

    public String l(Context context) {
        return null;
    }

    public String m(Context context) {
        return null;
    }

    public boolean n() {
        if (r(3) || r(4) || r(11) || r(15)) {
            return N.MnAiqOhu(this.f8767a, i());
        }
        if (r(8) || r(9) || r(6) || r(12)) {
            return !N.MB23OvTV(this.f8767a, i());
        }
        return false;
    }

    public boolean o() {
        if (r(8) || r(9) || r(6) || r(12)) {
            return N.M1hZJgqW(this.f8767a, i());
        }
        return false;
    }

    public boolean q(Context context) {
        return !g(context) || !h();
    }

    public boolean r(int i) {
        return i == this.b;
    }

    public boolean s() {
        return true;
    }
}
