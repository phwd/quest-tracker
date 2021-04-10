package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.components.permissions.PermissionUtil;
import org.chromium.components.permissions.nfc.NfcSystemLevelSetting;

/* renamed from: SB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SB0 {

    /* renamed from: a  reason: collision with root package name */
    public final List f8882a = new ArrayList();
    public final String b;
    public final boolean c;
    public final Context d;
    public final AbstractC2809h6 e;
    public final R51 f;
    public final Callback g;
    public final TB0 h;

    public SB0(Context context, AbstractC2809h6 h6Var, String str, boolean z, R51 r51, Callback callback, TB0 tb0) {
        this.d = context;
        this.b = str;
        this.c = z;
        this.f = r51;
        this.e = h6Var;
        this.g = callback;
        this.h = tb0;
    }

    public C0357Fv0 a() {
        boolean z;
        String str;
        String str2;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f8882a.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            RB0 rb0 = (RB0) it.next();
            C0418Gv0 gv0 = new C0418Gv0();
            gv0.d = AbstractC1154Sy.d(rb0.b);
            String str3 = null;
            if (rb0.c == 1) {
                C1159Ta0 a2 = C1159Ta0.a();
                if (rb0.b == 5 && !a2.e()) {
                    gv0.f = R.string.f56940_resource_name_obfuscated_RES_2131953011;
                    gv0.h = new PB0(this, a2.b(), null);
                } else if (rb0.b == 52 && !NfcSystemLevelSetting.isNfcAccessPossible()) {
                    gv0.f = R.string.f56950_resource_name_obfuscated_RES_2131953012;
                } else if (rb0.b != 52 || NfcSystemLevelSetting.isNfcSystemLevelSettingEnabled()) {
                    if (rb0.b == 6 && !new C0650Kp0(this.d).a() && N.ManEQDnV("AppNotificationStatusMessaging")) {
                        gv0.f = R.string.f56960_resource_name_obfuscated_RES_2131953013;
                        Intent intent = new Intent();
                        String packageName = ContextUtils.getApplicationContext().getPackageName();
                        if (Build.VERSION.SDK_INT >= 26) {
                            C2641g7.c(intent, packageName);
                        } else {
                            intent.setAction("android.settings.ACTION_APP_NOTIFICATION_SETTINGS");
                            intent.putExtra("app_package", packageName);
                            intent.putExtra("app_uid", ContextUtils.getApplicationContext().getApplicationInfo().uid);
                        }
                        gv0.h = new PB0(this, intent, null);
                    } else {
                        String[] androidPermissionsForContentSetting = PermissionUtil.getAndroidPermissionsForContentSetting(rb0.b);
                        if (androidPermissionsForContentSetting != null) {
                            int i = 0;
                            while (true) {
                                if (i >= androidPermissionsForContentSetting.length) {
                                    break;
                                } else if (!this.e.hasPermission(androidPermissionsForContentSetting[i])) {
                                    z2 = false;
                                    break;
                                } else {
                                    i++;
                                }
                            }
                        }
                        z2 = true;
                        if (!z2) {
                            int i2 = rb0.b;
                            if (i2 == 58) {
                                gv0.f = R.string.f56930_resource_name_obfuscated_RES_2131953010;
                            } else {
                                gv0.f = R.string.f56960_resource_name_obfuscated_RES_2131953013;
                            }
                            gv0.h = new PB0(this, null, PermissionUtil.getAndroidPermissionsForContentSetting(i2));
                        }
                    }
                } else {
                    gv0.f = R.string.f56960_resource_name_obfuscated_RES_2131953013;
                    gv0.h = new PB0(this, NfcSystemLevelSetting.a(), null);
                }
                if (gv0.f != 0) {
                    gv0.d = R.drawable.f29210_resource_name_obfuscated_RES_2131230961;
                    gv0.e = R.color.f11230_resource_name_obfuscated_RES_2131099813;
                }
            }
            if (rb0.b == 26) {
                gv0.g = R.string.f57140_resource_name_obfuscated_RES_2131953031;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            SpannableString spannableString = new SpannableString(rb0.f8814a);
            spannableString.setSpan(new TextAppearanceSpan(this.d, R.style.f72100_resource_name_obfuscated_RES_2132017783), 0, spannableString.length(), 17);
            gv0.f8120a = spannableString;
            spannableStringBuilder.append((CharSequence) spannableString);
            spannableStringBuilder.append((CharSequence) " – ");
            C4649rt0 b2 = C4649rt0.b(this.b);
            if (b2 != null) {
                TB0 tb0 = this.h;
                int i3 = rb0.b;
                Objects.requireNonNull((C0411Gs) tb0);
                if (i3 == 6) {
                    C2756go1 go1 = C2585fo1.a().f9955a;
                    str3 = go1.f10023a.getString(go1.b(b2), null);
                }
            }
            if (str3 != null) {
                str = String.format(this.d.getString(R.string.f64720_resource_name_obfuscated_RES_2131953789), str3);
            } else {
                int i4 = rb0.c;
                if (i4 == 1) {
                    gv0.b = true;
                    str2 = this.d.getString(R.string.f57150_resource_name_obfuscated_RES_2131953032);
                } else if (i4 != 2) {
                    str2 = "";
                } else {
                    gv0.b = false;
                    str2 = this.d.getString(R.string.f57160_resource_name_obfuscated_RES_2131953033);
                }
                if (N.Mno5HIHV(this.h.f8944a, rb0.b, this.b)) {
                    str = rb0.c == 1 ? this.d.getString(R.string.f57050_resource_name_obfuscated_RES_2131953022) : this.d.getString(R.string.f57060_resource_name_obfuscated_RES_2131953023);
                } else {
                    str = str2;
                }
            }
            spannableStringBuilder.append((CharSequence) str);
            gv0.c = spannableStringBuilder;
            arrayList.add(gv0);
        }
        C0357Fv0 fv0 = new C0357Fv0();
        if (!arrayList.isEmpty() && this.c) {
            z = true;
        }
        fv0.f8051a = z;
        fv0.b = arrayList;
        return fv0;
    }
}
