package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.components.page_info.PageInfoController;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SingleWebsiteSettings extends SiteSettingsPreferenceFragment implements XE0, YE0 {
    public static final int[] H0 = {0, 5, 10, 9, 6, 2, 4, 26, 22, 13, 16, 31, 14, 54, 52, 44, 57, 58, 40, 33};
    public static final String[] I0 = {"site_heading", "site_title", "site_usage", "site_permissions", "clear_data"};
    public boolean J0;
    public HX0 K0;
    public final OX0 L0 = new OX0();
    public C3469ky1 M0;
    public int N0;
    public int O0;
    public Integer P0;
    public Map Q0;
    public Dialog R0;
    public int S0;
    public final Runnable T0 = new RunnableC5776yX0(this);

    public static C3469ky1 A1(C3640ly1 ly1, Collection collection) {
        String str;
        V90 v90;
        String d = ly1.d();
        String host = Uri.parse(d).getHost();
        C3469ky1 ky1 = new C3469ky1(ly1, null);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            C3469ky1 ky12 = (C3469ky1) it.next();
            if (ky1.f(26) == null && ky12.f(26) != null && ky12.b(ky1) == 0) {
                ky1.m(26, ky12.f(26));
            }
            for (OB0 ob0 : ky12.I.values()) {
                if (ky1.h(ob0.I) == null) {
                    if (d.equals(ob0.H) && (d.equals(ob0.b()) || "*".equals(ob0.b()))) {
                        ky1.I.put(Integer.valueOf(ob0.I), ob0);
                    }
                }
            }
            if (ky1.f10318J == null && (v90 = ky12.f10318J) != null && d.equals(v90.F)) {
                ky1.f10318J = ky12.f10318J;
            }
            Iterator it2 = new ArrayList(ky12.K).iterator();
            while (it2.hasNext()) {
                I21 i21 = (I21) it2.next();
                if (host.equals(i21.F)) {
                    ky1.K.add(i21);
                }
            }
            Iterator it3 = ((ArrayList) ky12.d()).iterator();
            while (it3.hasNext()) {
                C5316vp vpVar = (C5316vp) it3.next();
                if (d.equals(vpVar.G) && ((str = vpVar.H) == null || str.equals("*"))) {
                    ky1.L.add(vpVar);
                }
            }
            if (host.equals(ky12.F.H)) {
                for (C1032Qy qy : ky12.H.values()) {
                    int i = qy.F;
                    if (i != 26 && ky1.f(i) == null) {
                        ky1.m(i, qy);
                    }
                }
            }
        }
        return ky1;
    }

    public static Bundle k1(String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("org.chromium.chrome.preferences.site_address", C3640ly1.b(C4649rt0.c(str).toString()));
        return bundle;
    }

    public static String p1(int i) {
        if (i == 0) {
            return "cookies_permission_list";
        }
        if (i == 2) {
            return "javascript_permission_list";
        }
        if (i == 4) {
            return "popup_permission_list";
        }
        if (i == 5) {
            return "location_access_list";
        }
        if (i == 6) {
            return "push_notifications_list";
        }
        if (i == 9) {
            return "microphone_permission_list";
        }
        if (i == 10) {
            return "camera_permission_list";
        }
        if (i == 13) {
            return "automatic_downloads_permission_list";
        }
        if (i == 14) {
            return "midi_sysex_permission_list";
        }
        if (i == 57) {
            return "vr_permission_list";
        }
        if (i == 58) {
            return "ar_permission_list";
        }
        switch (i) {
            case 2:
                return "javascript_permission_list";
            case Version.VERSION_16:
                return "protected_media_identifier_permission_list";
            case Version.VERSION_22:
                return "background_sync_permission_list";
            case Version.VERSION_26:
                return "ads_permission_list";
            case Version.VERSION_31:
                return "sound_permission_list";
            case 33:
                return "sensors_permission_list";
            case 40:
                return "idle_detection_permission_list";
            case 44:
                return "bluetooth_scanning_permission_list";
            case 52:
                return "nfc_permission_list";
            case 54:
                return "clipboard_permission_list";
            default:
                return null;
        }
    }

    public static boolean s1() {
        return N.ManEQDnV("ActionableContentSettings");
    }

    public final void B1(CharSequence charSequence) {
        Preference f1 = f1(charSequence);
        if (f1 != null) {
            PreferenceScreen preferenceScreen = this.z0.g;
            preferenceScreen.g0(f1);
            preferenceScreen.u();
        }
    }

    public final void C1(Preference preference, Integer num) {
        int n1 = n1(preference.Q);
        int g = AbstractC1154Sy.g(n1);
        if (g != 0) {
            preference.U(g);
        }
        if (!preference.r()) {
            Drawable m1 = m1(n1, num, false);
            if (preference.P != m1) {
                preference.P = m1;
                preference.O = 0;
                preference.s();
                return;
            }
            return;
        }
        QX0 d = QX0.d(this.G0.b, n1);
        if (!(d == null || num == null || num.intValue() == 2)) {
            if (!(d.h() && d.g(u()))) {
                Drawable j = d.j(x());
                if (preference.P != j) {
                    preference.P = j;
                    preference.O = 0;
                    preference.s();
                }
                preference.K(false);
                preference.X = false;
                int i = this.S0 + 1;
                this.S0 = i;
                preference.P(i);
                this.z0.g.a0(preference);
            }
        }
        Drawable m12 = m1(n1, num, true);
        if (preference.P != m12) {
            preference.P = m12;
            preference.O = 0;
            preference.s();
        }
        preference.X = false;
        int i2 = this.S0 + 1;
        this.S0 = i2;
        preference.P(i2);
        this.z0.g.a0(preference);
    }

    public final boolean D1(Preference preference, int i, int i2, Integer num) {
        Object obj;
        String str;
        C4649rt0 b = C4649rt0.b(this.M0.F.d());
        if (b == null) {
            return false;
        }
        Objects.requireNonNull(this.G0);
        if (i2 == 6) {
            C2756go1 go1 = C2585fo1.a().f9955a;
            obj = go1.f10023a.getString(go1.b(b), null);
        } else {
            obj = null;
        }
        if (obj == null) {
            return false;
        }
        Objects.requireNonNull(this.G0);
        if (i2 == 6) {
            C2756go1 go12 = C2585fo1.a().f9955a;
            str = go12.f10023a.getString(go12.c(b), null);
        } else {
            str = null;
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 26 || i2 != 6) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + str));
        } else {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", str);
        }
        String P = P(R.string.f64720_resource_name_obfuscated_RES_2131953789, obj);
        ChromeImageViewPreference chromeImageViewPreference = new ChromeImageViewPreference(preference.F);
        chromeImageViewPreference.O(preference.Q);
        C1(chromeImageViewPreference, num);
        chromeImageViewPreference.T(P);
        chromeImageViewPreference.b0(R.drawable.f34520_resource_name_obfuscated_RES_2131231492, i, null);
        if (chromeImageViewPreference.y0) {
            chromeImageViewPreference.y0 = false;
            chromeImageViewPreference.a0();
        }
        chromeImageViewPreference.K = new AX0(this, intent);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void E1(androidx.preference.Preference r9, java.lang.Integer r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 149
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings.E1(androidx.preference.Preference, java.lang.Integer, boolean):void");
    }

    public final boolean F1(int i) {
        BrowserContextHandle browserContextHandle = this.G0.b;
        Integer e = this.M0.e(browserContextHandle, QX0.c(i));
        if (e == null || e.intValue() == 2) {
            return false;
        }
        return QX0.f(browserContextHandle, i).q(x());
    }

    public final void G1(Preference preference, int i) {
        preference.T(o1(i));
        if (preference instanceof ListPreference) {
            ((ListPreference) preference).z0 = new String[]{O(R.string.f65500_resource_name_obfuscated_RES_2131953867), O(R.string.f65530_resource_name_obfuscated_RES_2131953870)};
        }
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        int i;
        Integer num;
        if (this.k0 == null) {
            return true;
        }
        BrowserContextHandle browserContextHandle = this.G0.b;
        int n1 = n1(preference.Q);
        if (n1 == -1) {
            return false;
        }
        if (obj instanceof Boolean) {
            i = ((Boolean) obj).booleanValue() ? 1 : 2;
        } else if (obj instanceof String) {
            String str = (String) obj;
            int i2 = 0;
            while (true) {
                if (i2 >= 6) {
                    num = null;
                    break;
                } else if (AbstractC0971Py.f8725a[i2].equals(str)) {
                    num = Integer.valueOf(i2);
                    break;
                } else {
                    i2++;
                }
            }
            i = num.intValue();
        } else {
            i = ((Integer) obj).intValue();
        }
        this.M0.l(browserContextHandle, n1, i);
        if (t1(n1)) {
            preference.T(o1(i));
        } else {
            preference.T(O(AbstractC1154Sy.a(i)));
        }
        Drawable m1 = m1(n1, Integer.valueOf(i), true);
        if (preference.P != m1) {
            preference.P = m1;
            preference.O = 0;
            preference.s();
        }
        HX0 hx0 = this.K0;
        if (hx0 != null) {
            C6015zv0 zv0 = (C6015zv0) hx0;
            ((PageInfoController) zv0.F).k(16);
            PageInfoController pageInfoController = (PageInfoController) zv0.F;
            pageInfoController.U.f8882a.clear();
            long j = pageInfoController.f10868J;
            if (j != 0) {
                N.MDd48bYq(j, pageInfoController);
            }
        }
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        u().setTitle(x().getString(R.string.f59280_resource_name_obfuscated_RES_2131953245));
        if (this.G0 == null) {
            C0317Fe fe = new C0317Fe(G());
            fe.p(this);
            fe.e();
        } else {
            Serializable serializable = this.K.getSerializable("org.chromium.chrome.preferences.site");
            Serializable serializable2 = this.K.getSerializable("org.chromium.chrome.preferences.site_address");
            if (serializable != null && serializable2 == null) {
                this.M0 = (C3469ky1) serializable;
                l1();
            } else if (serializable2 != null && serializable == null) {
                new Dy1(this.G0.b, false).b(new IX0(this, (C3640ly1) serializable2));
            }
            i1(null);
            this.A0.s0(null);
        }
        this.i0 = true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void c0(int i, int i2, Intent intent) {
        C3469ky1 ky1;
        if (this.z0.g != null && (ky1 = this.M0) != null && i == 1) {
            int intValue = ky1.e(this.G0.b, 6).intValue();
            Preference f1 = f1(p1(6));
            if (f1 != null) {
                a(f1, Integer.valueOf(intValue));
            }
            if (this.P0.intValue() == 1 && intValue != 1) {
                N.M$1c3w6w(this.G0.b, this.M0.F.d(), intValue);
                this.P0 = null;
            }
        }
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        boolean z = this.J0;
        int i = z ? R.string.f57180_resource_name_obfuscated_RES_2131953035 : R.string.f64700_resource_name_obfuscated_RES_2131953787;
        int i2 = z ? R.string.f57190_resource_name_obfuscated_RES_2131953036 : R.string.f64710_resource_name_obfuscated_RES_2131953788;
        int i3 = z ? R.string.f60290_resource_name_obfuscated_RES_2131953346 : i;
        C2290e4 e4Var = new C2290e4(x(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(i);
        e4Var.c(i2);
        e4Var.e(i3, new DX0(this));
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new EX0(this));
        this.R0 = e4Var.i();
        return true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
    }

    @Override // defpackage.AbstractC2324eF0, defpackage.AbstractC3862nF0
    public void k(Preference preference) {
        if (preference instanceof ClearWebsiteStorage) {
            C5946zX0 zx0 = new C5946zX0(this);
            ClearWebsiteStorageDialog clearWebsiteStorageDialog = new ClearWebsiteStorageDialog();
            ClearWebsiteStorageDialog.U0 = zx0;
            Bundle bundle = new Bundle(1);
            bundle.putString("key", preference.Q);
            clearWebsiteStorageDialog.U0(bundle);
            clearWebsiteStorageDialog.b1(this, 0);
            clearWebsiteStorageDialog.k1(this.W, "ClearWebsiteStorageDialog");
            return;
        }
        super.k(preference);
    }

    public final void l1() {
        QX0 qx0;
        char c;
        Preference preference;
        String str;
        PreferenceScreen preferenceScreen = this.z0.g;
        if (preferenceScreen != null) {
            preferenceScreen.e0();
        }
        AbstractC2870hT0.a(this, R.xml.f76390_resource_name_obfuscated_RES_2132213795);
        f1("site_title").V(this.M0.i());
        this.S0 = f1("site_permissions").L;
        int[] iArr = H0;
        int length = iArr.length;
        int i = 0;
        while (true) {
            int i2 = 2;
            qx0 = null;
            c = 1;
            if (i >= length) {
                break;
            }
            int i3 = iArr[i];
            if (s1()) {
                preference = new ChromeSwitchPreference(this.z0.f11127a);
            } else {
                preference = new ListPreference(this.z0.f11127a, null);
            }
            preference.O(p1(i3));
            if (i3 == 26) {
                BrowserContextHandle browserContextHandle = this.G0.b;
                if (!QX0.a()) {
                    E1(preference, null, false);
                } else {
                    boolean Mq9o4NGp = N.Mq9o4NGp(browserContextHandle, this.M0.F.d());
                    Integer e = this.M0.e(browserContextHandle, 26);
                    if (e != null || Mq9o4NGp) {
                        if (e == null) {
                            e = Integer.valueOf(N.MJSt3Ocq(browserContextHandle, 26) ? 1 : 2);
                        }
                        E1(preference, e, false);
                        if (preference instanceof ListPreference) {
                            ListPreference listPreference = (ListPreference) preference;
                            listPreference.z0 = new String[]{O(R.string.f65490_resource_name_obfuscated_RES_2131953866), O(R.string.f65480_resource_name_obfuscated_RES_2131953865)};
                            if (e.intValue() == 1) {
                                c = 0;
                            }
                            CharSequence[] charSequenceArr = listPreference.A0;
                            if (charSequenceArr != null) {
                                listPreference.c0(charSequenceArr[c].toString());
                            }
                        }
                    } else {
                        E1(preference, null, false);
                    }
                }
            } else if (i3 == 31) {
                BrowserContextHandle browserContextHandle2 = this.G0.b;
                Integer e2 = this.M0.e(browserContextHandle2, 31);
                if (e2 == null) {
                    if (N.MJSt3Ocq(browserContextHandle2, 31)) {
                        i2 = 1;
                    }
                    e2 = Integer.valueOf(i2);
                }
                E1(preference, e2, false);
            } else if (i3 == 2) {
                BrowserContextHandle browserContextHandle3 = this.G0.b;
                int e3 = this.M0.e(browserContextHandle3, 2);
                if (e3 == null && !N.MJSt3Ocq(browserContextHandle3, 2)) {
                    e3 = 2;
                }
                E1(preference, e3, false);
            } else if (i3 == 5) {
                Integer e4 = this.M0.e(this.G0.b, 5);
                if (!D1(preference, R.string.f64680_resource_name_obfuscated_RES_2131953785, 5, e4)) {
                    E1(preference, e4, u1(5));
                    if (t1(5) && e4 != null) {
                        G1(preference, e4.intValue());
                    }
                }
            } else if (i3 == 6) {
                boolean u1 = u1(i3);
                Integer e5 = this.M0.e(this.G0.b, 6);
                if (!D1(preference, R.string.f64690_resource_name_obfuscated_RES_2131953786, 6, e5)) {
                    if (Build.VERSION.SDK_INT < 26) {
                        E1(preference, e5, u1);
                        if (t1(6) && e5 != null) {
                            G1(preference, e5.intValue());
                        }
                    } else if (e5 != null && (e5.intValue() == 1 || e5.intValue() == 2)) {
                        if (t1(6)) {
                            str = o1(e5.intValue());
                        } else if (u1) {
                            str = O(R.string.f47640_resource_name_obfuscated_RES_2131952081);
                        } else {
                            str = O(AbstractC1154Sy.a(e5.intValue()));
                        }
                        ChromeImageViewPreference chromeImageViewPreference = new ChromeImageViewPreference(preference.F);
                        chromeImageViewPreference.O(preference.Q);
                        C1(chromeImageViewPreference, e5);
                        chromeImageViewPreference.T(str);
                        chromeImageViewPreference.Z = e5;
                        chromeImageViewPreference.K = new BX0(this, preference);
                    }
                }
            } else {
                E1(preference, this.M0.e(this.G0.b, i3), u1(i3));
            }
            i++;
        }
        Preference f1 = f1("reset_site_button");
        f1.U(this.J0 ? R.string.f57180_resource_name_obfuscated_RES_2131953035 : R.string.f64700_resource_name_obfuscated_RES_2131953787);
        f1.P(this.S0 + 1);
        f1.K = this;
        if (N.M9l6T3Dg(this.G0.b, this.M0.F.d())) {
            f1.K(false);
        }
        ClearWebsiteStorage clearWebsiteStorage = (ClearWebsiteStorage) f1("clear_data");
        long j = this.M0.j();
        if (j > 0) {
            Objects.requireNonNull(this.G0);
            boolean contains = ((HashSet) AbstractC2957hy1.f10115a.a()).contains(this.M0.F.d());
            Context context = clearWebsiteStorage.F;
            clearWebsiteStorage.V(String.format(context.getString(R.string.f56830_resource_name_obfuscated_RES_2131953000), Formatter.formatShortFileSize(context, j)));
            clearWebsiteStorage.B0 = this.M0.i();
            clearWebsiteStorage.C0 = contains;
            if (N.M9l6T3Dg(this.G0.b, this.M0.F.d())) {
                clearWebsiteStorage.K(false);
            }
        } else {
            this.z0.g.f0(clearWebsiteStorage);
        }
        PreferenceScreen preferenceScreen2 = this.z0.g;
        Iterator it = ((ArrayList) this.M0.d()).iterator();
        while (it.hasNext()) {
            C5316vp vpVar = (C5316vp) it.next();
            ChromeImageViewPreference chromeImageViewPreference2 = new ChromeImageViewPreference(this.z0.f11127a);
            chromeImageViewPreference2.O("chooser_permission_list");
            Drawable b = AbstractC2870hT0.b(x(), AbstractC1154Sy.d(vpVar.F));
            if (chromeImageViewPreference2.P != b) {
                chromeImageViewPreference2.P = b;
                chromeImageViewPreference2.O = 0;
                chromeImageViewPreference2.s();
            }
            chromeImageViewPreference2.V(vpVar.I);
            chromeImageViewPreference2.b0(R.drawable.f29850_resource_name_obfuscated_RES_2131231025, R.string.f65580_resource_name_obfuscated_RES_2131953875, new CX0(this, vpVar, preferenceScreen2, chromeImageViewPreference2));
            GX0 gx0 = new GX0(this, this.G0.a(), vpVar);
            chromeImageViewPreference2.t0 = gx0;
            AbstractC1865bc0.b(gx0, chromeImageViewPreference2);
            if (vpVar.K) {
                this.O0++;
            } else {
                this.N0++;
            }
            int i4 = this.S0 + 1;
            this.S0 = i4;
            chromeImageViewPreference2.P(i4);
            preferenceScreen2.a0(chromeImageViewPreference2);
        }
        PreferenceScreen preferenceScreen3 = this.z0.g;
        BrowserContextHandle browserContextHandle4 = this.G0.b;
        if (F1(9)) {
            qx0 = QX0.f(browserContextHandle4, 9);
        } else if (F1(6)) {
            qx0 = QX0.f(browserContextHandle4, 6);
        } else if (F1(12)) {
            qx0 = QX0.f(browserContextHandle4, 12);
        } else if (F1(14)) {
            qx0 = QX0.f(browserContextHandle4, 14);
        } else if (F1(13)) {
            qx0 = QX0.f(browserContextHandle4, 13);
        } else if (F1(2)) {
            qx0 = QX0.f(browserContextHandle4, 2);
        }
        if (qx0 == null) {
            B1("os_permissions_warning");
            B1("os_permissions_warning_extra");
            B1("os_permissions_warning_divider");
        } else {
            Preference f12 = f1("os_permissions_warning");
            Preference f13 = f1("os_permissions_warning_extra");
            qx0.b(f12, f13, x(), false, this.G0.f9887a.getString(R.string.f46950_resource_name_obfuscated_RES_2131952012));
            if (f12.M == null) {
                preferenceScreen3.g0(f12);
                preferenceScreen3.u();
            } else if (f13.M == null) {
                preferenceScreen3.g0(f13);
                preferenceScreen3.u();
            }
        }
        if (!QX0.a() || !N.Mq9o4NGp(this.G0.b, this.M0.F.d()) || f1(p1(26)) == null) {
            c = 0;
        }
        if (c == 0) {
            B1("intrusive_ads_info");
            B1("intrusive_ads_info_divider");
        }
        if (!r1()) {
            B1("site_usage");
        }
        if (!q1()) {
            B1("site_permissions");
        }
        if (this.J0) {
            for (String str2 : I0) {
                B1(str2);
            }
            return;
        }
        B1("page_description");
    }

    public final Drawable m1(int i, Integer num, boolean z) {
        Drawable drawable;
        if (z) {
            drawable = AbstractC2870hT0.b(x(), AbstractC1154Sy.d(i));
        } else {
            drawable = AbstractC1154Sy.c(i, I());
        }
        if (!s1() || num == null || num.intValue() != 2) {
            return drawable;
        }
        Resources I = I();
        ColorFilter colorFilter = drawable.getColorFilter();
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int width = canvas.getWidth();
        drawable.setBounds(0, 0, width, width);
        drawable.draw(canvas);
        float f = (float) width;
        float f2 = 0.08f * f;
        float f3 = f * 0.15f;
        float f4 = (f2 / 2.0f) * 0.7071f;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        float f5 = 0.5f * f4;
        paint.setStrokeWidth(1.5f * f2);
        float f6 = f - f3;
        canvas.drawLine(f3 + f5, f3 - f5, f6 + f5, f6 - f5, paint);
        paint.setColor(-16777216);
        paint.setXfermode(null);
        paint.setStrokeWidth(f2);
        canvas.drawLine(f3 - f4, f3 + f4, f6 - f4, f6 + f4, paint);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(I, createBitmap);
        bitmapDrawable.setColorFilter(colorFilter);
        return bitmapDrawable;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void n0() {
        super.n0();
        Dialog dialog = this.R0;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public int n1(String str) {
        if (this.Q0 == null) {
            this.Q0 = new HashMap();
            for (int i = 0; i < 69; i++) {
                String p1 = p1(i);
                if (p1 != null) {
                    this.Q0.put(p1, Integer.valueOf(i));
                }
            }
        }
        Integer num = (Integer) this.Q0.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public final String o1(int i) {
        if (i == 1) {
            return O(R.string.f65510_resource_name_obfuscated_RES_2131953868);
        }
        return O(R.string.f65540_resource_name_obfuscated_RES_2131953871);
    }

    public final boolean q1() {
        if (this.N0 > 0 || this.O0 > 0) {
            return true;
        }
        PreferenceScreen preferenceScreen = this.z0.g;
        for (int i = 0; i < preferenceScreen.d0(); i++) {
            if (n1(preferenceScreen.c0(i).Q) != -1) {
                return true;
            }
        }
        return false;
    }

    public final boolean r1() {
        return f1("clear_data") != null;
    }

    public final boolean t1(int i) {
        return N.Mno5HIHV(this.G0.b, i, this.M0.F.d());
    }

    public final boolean u1(int i) {
        return ((OB0) this.M0.I.get(Integer.valueOf(i))) != null && ((OB0) this.M0.I.get(Integer.valueOf(i))).F;
    }

    public final void v1() {
        if (this.J0) {
            this.L0.b(this.G0.b, this.M0);
        } else if (u() != null) {
            for (int i = 0; i < 69; i++) {
                String p1 = p1(i);
                if (p1 != null) {
                    B1(p1);
                }
            }
            boolean z = this.M0.j() == 0 && this.O0 == 0;
            this.L0.b(this.G0.b, this.M0);
            this.L0.a(this.G0.b, this.M0, this.T0);
            AbstractC3364kK0.g("SingleWebsitePreferences.NavigatedFromToReset", this.K.getInt("org.chromium.chrome.preferences.navigation_source", 0), 3);
            if (z) {
                u().finish();
            }
        }
        HX0 hx0 = this.K0;
        if (hx0 != null) {
            C6015zv0 zv0 = (C6015zv0) hx0;
            ((PageInfoController) zv0.F).k(15);
            PageInfoController pageInfoController = (PageInfoController) zv0.F;
            pageInfoController.U.f8882a.clear();
            long j = pageInfoController.f10868J;
            if (j != 0) {
                N.MDd48bYq(j, pageInfoController);
            }
            ((PageInfoController) zv0.F).c();
        }
    }

    public final /* synthetic */ void w1() {
        this.R0 = null;
    }

    public final void x1(C5316vp vpVar, PreferenceScreen preferenceScreen, ChromeImageViewPreference chromeImageViewPreference) {
        vpVar.a(this.G0.b);
        preferenceScreen.g0(chromeImageViewPreference);
        preferenceScreen.u();
        this.N0--;
        if (!q1()) {
            B1("site_permissions");
        }
    }

    public final boolean y1(Preference preference) {
        if (u1(6)) {
            this.M0.l(this.G0.b, 6, 2);
        }
        C2427et etVar = this.G0;
        String d = this.M0.F.d();
        Objects.requireNonNull(etVar);
        String a2 = LX0.f8421a.a(d);
        Context context = preference.F;
        this.P0 = this.M0.e(this.G0.b, 6);
        Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.CHANNEL_ID", a2);
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        d1(intent, 1);
        return true;
    }

    public final /* synthetic */ boolean z1(Intent intent) {
        c1(intent);
        return true;
    }
}
