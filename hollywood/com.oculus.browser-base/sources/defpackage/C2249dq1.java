package defpackage;

import J.N;
import android.content.res.Resources;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;

/* renamed from: dq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2249dq1 {

    /* renamed from: a  reason: collision with root package name */
    public static C2249dq1 f9811a;
    public static Object b = new Object();
    public final C1322Vq0 c = new C1322Vq0();
    public final Callback d = new Xp1(this);
    public C5321vq1 e;
    public C2079cq1 f = new C2079cq1();
    public boolean g;

    public static C2249dq1 a() {
        C2249dq1 dq1;
        synchronized (b) {
            if (f9811a == null) {
                f9811a = new C2249dq1();
            }
            dq1 = f9811a;
        }
        return dq1;
    }

    public static PrefService b() {
        return Wr1.a(Profile.b());
    }

    public final void c() {
        boolean g2 = AbstractC1575Zv.e().g("force-show-update-menu-badge");
        Resources resources = ContextUtils.getApplicationContext().getResources();
        C2079cq1 cq1 = new C2079cq1();
        this.f = cq1;
        C5321vq1 vq1 = this.e;
        switch (vq1.f11503a) {
            case 1:
                if (g2 || (!TextUtils.equals(N.Ma80fvz5(b().f10883a, "omaha.latest_version_when_clicked_upate_menu_item"), this.e.d))) {
                    C2079cq1 cq12 = this.f;
                    C1737aq1 aq1 = new C1737aq1();
                    cq12.b = aq1;
                    aq1.f9494a = R.string.f46250_resource_name_obfuscated_RES_2131951942;
                    aq1.b = R.drawable.f28230_resource_name_obfuscated_RES_2131230863;
                    aq1.c = R.drawable.f28240_resource_name_obfuscated_RES_2131230864;
                }
                C2079cq1 cq13 = this.f;
                C1908bq1 bq1 = new C1908bq1();
                cq13.f9724a = bq1;
                bq1.f9564a = R.string.f54900_resource_name_obfuscated_RES_2131952807;
                bq1.b = R.color.f11510_resource_name_obfuscated_RES_2131099841;
                bq1.d = R.drawable.f28220_resource_name_obfuscated_RES_2131230862;
                bq1.f = true;
                bq1.c = Sp1.a("custom_summary");
                if (TextUtils.isEmpty(this.f.f9724a.c)) {
                    this.f.f9724a.c = resources.getString(R.string.f54910_resource_name_obfuscated_RES_2131952808);
                    return;
                }
                return;
            case 2:
                String str = vq1.d;
                if ((g2 | (str == null)) || (!TextUtils.equals(AbstractC0456Hk.f8178a.f, str))) {
                    C2079cq1 cq14 = this.f;
                    C1737aq1 aq12 = new C1737aq1();
                    cq14.b = aq12;
                    aq12.f9494a = R.string.f46240_resource_name_obfuscated_RES_2131951941;
                    aq12.b = R.drawable.f30070_resource_name_obfuscated_RES_2131231047;
                    aq12.c = R.drawable.f30100_resource_name_obfuscated_RES_2131231050;
                }
                C2079cq1 cq15 = this.f;
                C1908bq1 bq12 = new C1908bq1();
                cq15.f9724a = bq12;
                bq12.f9564a = R.string.f54920_resource_name_obfuscated_RES_2131952809;
                bq12.b = R.color.f11450_resource_name_obfuscated_RES_2131099835;
                bq12.c = resources.getString(R.string.f54930_resource_name_obfuscated_RES_2131952810);
                C1908bq1 bq13 = this.f.f9724a;
                bq13.d = R.drawable.f30050_resource_name_obfuscated_RES_2131231045;
                bq13.f = false;
                return;
            case 3:
                if (g2 || (!TextUtils.equals(N.Ma80fvz5(b().f10883a, "omaha.latest_version_when_clicked_upate_menu_item"), this.e.d))) {
                    C2079cq1 cq16 = this.f;
                    C1737aq1 aq13 = new C1737aq1();
                    cq16.b = aq13;
                    aq13.f9494a = R.string.f46250_resource_name_obfuscated_RES_2131951942;
                    aq13.b = R.drawable.f28230_resource_name_obfuscated_RES_2131230863;
                    aq13.c = R.drawable.f28240_resource_name_obfuscated_RES_2131230864;
                }
                C2079cq1 cq17 = this.f;
                C1908bq1 bq14 = new C1908bq1();
                cq17.f9724a = bq14;
                bq14.f9564a = R.string.f54900_resource_name_obfuscated_RES_2131952807;
                bq14.b = R.color.f11460_resource_name_obfuscated_RES_2131099836;
                bq14.c = Sp1.a("custom_summary");
                if (TextUtils.isEmpty(this.f.f9724a.c)) {
                    this.f.f9724a.c = resources.getString(R.string.f54910_resource_name_obfuscated_RES_2131952808);
                }
                C1908bq1 bq15 = this.f.f9724a;
                bq15.d = R.drawable.f30680_resource_name_obfuscated_RES_2131231108;
                bq15.e = R.color.f11230_resource_name_obfuscated_RES_2131099813;
                bq15.f = true;
                return;
            case 4:
                C1908bq1 bq16 = new C1908bq1();
                cq1.f9724a = bq16;
                bq16.f9564a = R.string.f54600_resource_name_obfuscated_RES_2131952777;
                bq16.b = R.color.f11660_resource_name_obfuscated_RES_2131099856;
                return;
            case 5:
                C1908bq1 bq17 = new C1908bq1();
                cq1.f9724a = bq17;
                bq17.f9564a = R.string.f54620_resource_name_obfuscated_RES_2131952779;
                bq17.b = R.color.f11460_resource_name_obfuscated_RES_2131099836;
                bq17.c = resources.getString(R.string.f54630_resource_name_obfuscated_RES_2131952780);
                C1908bq1 bq18 = this.f.f9724a;
                bq18.d = R.drawable.f33230_resource_name_obfuscated_RES_2131231363;
                bq18.e = R.color.f11230_resource_name_obfuscated_RES_2131099813;
                bq18.f = true;
                return;
            case 6:
                C1908bq1 bq19 = new C1908bq1();
                cq1.f9724a = bq19;
                bq19.f9564a = R.string.f54610_resource_name_obfuscated_RES_2131952778;
                bq19.b = R.color.f11460_resource_name_obfuscated_RES_2131099836;
                bq19.c = resources.getString(R.string.f63810_resource_name_obfuscated_RES_2131953698);
                C1908bq1 bq110 = this.f.f9724a;
                bq110.d = R.drawable.f30680_resource_name_obfuscated_RES_2131231108;
                bq110.e = R.color.f11230_resource_name_obfuscated_RES_2131099813;
                bq110.f = true;
                return;
            default:
                return;
        }
    }

    public final void d(int i) {
        AbstractC3364kK0.g("GoogleUpdate.MenuItem.ActionTakenOnMenuOpen", i, 3);
    }

    public final void e() {
        if (N.MzIXnlkD(b().f10883a, "omaha.clicked_update_menu_item")) {
            int i = 1;
            if (this.e.f11503a != 1) {
                i = 0;
            }
            AbstractC3364kK0.g("GoogleUpdate.MenuItem.ActionTakenAfterItemClicked", i, 2);
            N.Mf2ABpoH(b().f10883a, "omaha.clicked_update_menu_item", false);
        }
    }

    public void f(Runnable runnable) {
        if (this.c.b(runnable)) {
            if (this.e != null) {
                PostTask.b(Zo1.f9374a, new Yp1(this, runnable), 0);
            } else {
                AbstractC4981tq1.f11374a.a(this.d);
            }
        }
    }
}
