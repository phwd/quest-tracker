package defpackage;

import J.N;
import android.app.Activity;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Zs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1569Zs {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f9379a;
    public final Q31 b;
    public final AbstractC4448qj c;
    public final C5090uU0 d;
    public final C2189dU0 e;
    public final Callback f;
    public final C2528fT0 g;
    public final boolean h;
    public final long i;
    public final List j;
    public final View$OnLayoutChangeListenerC5940zU0 k;
    public C2691gP0 l;
    public final String m;
    public final HZ n;
    public final Tm1 o;
    public final AbstractC0576Jj p = new C1325Vs(this);

    public C1569Zs(Activity activity, Q31 q31, AbstractC4448qj qjVar, C5090uU0 uu0, C2189dU0 du0, C1915bt btVar, Callback callback, C2528fT0 ft0, boolean z, long j2, View$OnLayoutChangeListenerC5940zU0 zu0, HZ hz, Tm1 tm1) {
        int i2;
        int i3;
        this.f9379a = activity;
        this.b = q31;
        this.c = qjVar;
        this.d = uu0;
        this.e = du0;
        this.f = callback;
        this.g = ft0;
        this.h = z;
        this.i = j2;
        this.n = hz;
        this.o = tm1;
        ArrayList arrayList = new ArrayList();
        this.j = arrayList;
        if (N.M09VlOh_("ChromeShareScreenshot")) {
            arrayList.add(new C1386Ws(CU0.a(AbstractC5544x8.a(activity, R.drawable.f34810_resource_name_obfuscated_RES_2131231521), activity.getResources().getString(R.string.f61770_resource_name_obfuscated_RES_2131953494), new View$OnClickListenerC0715Ls(this), tm1.isInitialized() && tm1.shouldTriggerHelpUI("IPH_ShareScreenshot")), Arrays.asList(0, 2, 3, 5), Collections.emptySet(), true));
        }
        if (N.M09VlOh_("ChromeShareLongScreenshot")) {
            i3 = 1;
            i2 = 2;
            arrayList.add(new C1386Ws(CU0.a(AbstractC5544x8.a(activity, R.drawable.f33570_resource_name_obfuscated_RES_2131231397), activity.getResources().getString(R.string.f61730_resource_name_obfuscated_RES_2131953490), new View$OnClickListenerC0776Ms(this), false), Arrays.asList(0, 2, 3, 5), Collections.emptySet(), true));
        } else {
            i3 = 1;
            i2 = 2;
        }
        Integer[] numArr = new Integer[i2];
        numArr[0] = 0;
        numArr[i3] = 1;
        C1508Ys ys = new C1508Ys(this, numArr);
        Integer[] numArr2 = new Integer[i3];
        numArr2[0] = 4;
        if (N.M09VlOh_("ChromeSharingHubV15")) {
            ys.e = numArr2;
        }
        ys.f9302a = R.drawable.f29810_resource_name_obfuscated_RES_2131231021;
        ys.b = R.string.f61630_resource_name_obfuscated_RES_2131953480;
        ys.c = "SharingHubAndroid.CopyURLSelected";
        ys.d = new C0837Ns(this);
        arrayList.add(ys.a());
        if (N.M09VlOh_("ChromeSharingHubV15")) {
            C1508Ys ys2 = new C1508Ys(this, 5);
            ys2.f9302a = R.drawable.f29810_resource_name_obfuscated_RES_2131231021;
            ys2.b = R.string.f61610_resource_name_obfuscated_RES_2131953478;
            ys2.c = "SharingHubAndroid.CopyImageSelected";
            ys2.d = new C0898Os(this);
            arrayList.add(ys2.a());
            C1508Ys ys3 = new C1508Ys(this, 4);
            ys3.f9302a = R.drawable.f29810_resource_name_obfuscated_RES_2131231021;
            ys3.b = R.string.f61600_resource_name_obfuscated_RES_2131953477;
            ys3.c = "SharingHubAndroid.CopySelected";
            ys3.d = new C0959Ps(this);
            arrayList.add(ys3.a());
            C1508Ys ys4 = new C1508Ys(this, 2, 3);
            Integer[] numArr3 = {4};
            if (N.M09VlOh_("ChromeSharingHubV15")) {
                ys4.e = numArr3;
            }
            ys4.f9302a = R.drawable.f29810_resource_name_obfuscated_RES_2131231021;
            ys4.b = R.string.f61620_resource_name_obfuscated_RES_2131953479;
            ys4.c = "SharingHubAndroid.CopyTextSelected";
            ys4.d = new C1020Qs(this);
            arrayList.add(ys4.a());
        }
        C1508Ys ys5 = new C1508Ys(this, 0, 1, 5);
        ys5.f9302a = R.drawable.f34860_resource_name_obfuscated_RES_2131231526;
        ys5.b = R.string.f61250_resource_name_obfuscated_RES_2131953442;
        ys5.c = "SharingHubAndroid.SendTabToSelfSelected";
        ys5.d = new C1081Rs(this);
        arrayList.add(ys5.a());
        if (N.M09VlOh_("ChromeSharingHubV15") && N.M09VlOh_("ChromeShareHighlightsAndroid")) {
            C1508Ys ys6 = new C1508Ys(this, 3);
            ys6.f9302a = R.drawable.f33500_resource_name_obfuscated_RES_2131231390;
            ys6.b = R.string.f61640_resource_name_obfuscated_RES_2131953481;
            ys6.c = "SharingHubAndroid.LinkToTextSelected";
            ys6.d = new C1264Us(this);
            arrayList.add(ys6.a());
        }
        if (N.M09VlOh_("ChromeShareQRCode") && !((Tab) q31.get()).l().a()) {
            C1508Ys ys7 = new C1508Ys(this, 0, 1, 5);
            ys7.f9302a = R.drawable.f34730_resource_name_obfuscated_RES_2131231513;
            ys7.b = R.string.f59810_resource_name_obfuscated_RES_2131953298;
            ys7.c = "SharingHubAndroid.QRCodeSelected";
            ys7.d = new C1142Ss(this);
            arrayList.add(ys7.a());
        }
        if (N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "printing.enabled")) {
            C1508Ys ys8 = new C1508Ys(this, 0);
            ys8.f9302a = R.drawable.f34970_resource_name_obfuscated_RES_2131231537;
            ys8.b = R.string.f59450_resource_name_obfuscated_RES_2131953262;
            ys8.c = "SharingHubAndroid.PrintSelected";
            ys8.d = new C1203Ts(this);
            arrayList.add(ys8.a());
        }
        this.k = zu0;
        String h2 = ((Tab) q31.get()).isInitialized() ? ((Tab) q31.get()).getUrl().h() : "";
        if (!TextUtils.isEmpty(du0.d)) {
            h2 = du0.d;
        } else if (!btVar.d.j()) {
            h2 = btVar.d.h();
        }
        this.m = h2;
    }

    public static void b(long j2) {
        AbstractC3364kK0.j("Sharing.SharingHubAndroid.TimeToShare", System.currentTimeMillis() - j2);
    }

    public List a(Set set, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (C1386Ws ws : this.j) {
            if (!Collections.disjoint(set, ws.f9177a) && Collections.disjoint(set, ws.b)) {
                if (!z || !ws.d) {
                    arrayList.add(ws.c);
                }
            }
        }
        return arrayList;
    }
}
