package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.notifications.ActionInfo;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.chrome.browser.notifications.NotificationSystemStatusUtil;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: Pp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0954Pp0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final NotificationPlatformBridge f8715a;
    public final String b;
    public final int c;
    public final String d;
    public final String e;
    public final String f;
    public final boolean g;
    public final boolean h;
    public final String i;
    public final String j;
    public final Bitmap k;
    public final Bitmap l;
    public final Bitmap m;
    public final int[] n;
    public final long o;
    public final boolean p;
    public final boolean q;
    public final ActionInfo[] r;

    public C0954Pp0(NotificationPlatformBridge notificationPlatformBridge, String str, int i2, String str2, String str3, String str4, boolean z, boolean z2, String str5, String str6, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int[] iArr, long j2, boolean z3, boolean z4, ActionInfo[] actionInfoArr) {
        this.f8715a = notificationPlatformBridge;
        this.b = str;
        this.c = i2;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = z;
        this.h = z2;
        this.i = str5;
        this.j = str6;
        this.k = bitmap;
        this.l = bitmap2;
        this.m = bitmap3;
        this.n = iArr;
        this.o = j2;
        this.p = z3;
        this.q = z4;
        this.r = actionInfoArr;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        int i2;
        ZZ0 zz0;
        C5232vH0 vh0;
        C5232vH0 vh02;
        Bitmap bitmap;
        NotificationPlatformBridge notificationPlatformBridge = this.f8715a;
        String str = this.b;
        int i3 = this.c;
        String str2 = this.d;
        String str3 = this.e;
        String str4 = this.f;
        boolean z = this.g;
        boolean z2 = this.h;
        String str5 = this.i;
        String str6 = this.j;
        Bitmap bitmap2 = this.k;
        Bitmap bitmap3 = this.l;
        Bitmap bitmap4 = this.m;
        int[] iArr = this.n;
        long j2 = this.o;
        boolean z3 = this.p;
        boolean z4 = this.q;
        ActionInfo[] actionInfoArr = this.r;
        String str7 = (String) obj;
        N.MlTGi82B(notificationPlatformBridge.c, notificationPlatformBridge, str, str7);
        AbstractC3364kK0.g("Notifications.AppNotificationStatus", NotificationSystemStatusUtil.getAppNotificationStatus(), 4);
        Context applicationContext = ContextUtils.getApplicationContext();
        CB0 g2 = notificationPlatformBridge.g(applicationContext, "org.chromium.chrome.browser.notifications.CLICK_NOTIFICATION", str, i3, str2, str3, str4, z, str7, -1);
        CB0 g3 = notificationPlatformBridge.g(applicationContext, "org.chromium.chrome.browser.notifications.CLOSE_NOTIFICATION", str, i3, str2, str3, str4, z, str7, -1);
        int i4 = 0;
        int i5 = 1;
        Bitmap bitmap5 = bitmap2;
        boolean z5 = bitmap5 != null;
        boolean z6 = !str7.isEmpty();
        Context context = applicationContext;
        ZZ0 zz02 = new ZZ0(context);
        zz02.d = AbstractC5827yp0.i(str5);
        zz02.e = AbstractC5827yp0.i(str6);
        zz02.i = bitmap5;
        zz02.u = bitmap3;
        zz02.j = R.drawable.f29770_resource_name_obfuscated_RES_2131231017;
        zz02.l(bitmap4);
        zz02.k(bitmap4);
        zz02.m = g2;
        zz02.n = g3;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str5);
        spannableStringBuilder.append((CharSequence) "\n");
        spannableStringBuilder.append((CharSequence) str6);
        spannableStringBuilder.setSpan(new StyleSpan(1), 0, str5.length(), 18);
        zz02.h = AbstractC5827yp0.i(spannableStringBuilder);
        zz02.s = j2;
        zz02.t = z3;
        zz02.f = AbstractC5827yp0.i(N.MR6Af3ZS(str2, 1));
        if (Build.VERSION.SDK_INT >= 26 && !z6) {
            zz02.g = LX0.f8421a.a(str2);
        }
        int i6 = 0;
        while (i6 < actionInfoArr.length) {
            CB0 g4 = notificationPlatformBridge.g(context, "org.chromium.chrome.browser.notifications.CLICK_NOTIFICATION", str, i3, str2, str3, str4, z, str7, i6);
            ActionInfo actionInfo = actionInfoArr[i6];
            if (z5) {
                bitmap = null;
            } else {
                bitmap = actionInfo.b;
            }
            if (actionInfo.c == i5) {
                zz02.b(bitmap, actionInfo.f10701a, g4.f7793a, 1, actionInfo.d);
            } else {
                zz02.b(bitmap, actionInfo.f10701a, g4.f7793a, 0, null);
            }
            i6++;
            zz02 = zz02;
            i5 = i5;
            context = context;
            bitmap5 = bitmap5;
            str2 = str2;
            i4 = i4;
        }
        int[] iArr2 = !z2 ? NotificationPlatformBridge.f10702a : iArr;
        int length = iArr2.length;
        if (z4) {
            zz0 = zz02;
            i2 = i4;
        } else {
            i2 = (length > 0 || !z2) ? -3 : -1;
            zz0 = zz02;
        }
        zz0.q = i2;
        int length2 = iArr2.length + i5;
        long[] jArr = new long[length2];
        int i7 = i4;
        while (i7 < iArr2.length) {
            int i8 = i7 + 1;
            jArr[i8] = (long) iArr2[i7];
            i7 = i8;
        }
        zz0.r = Arrays.copyOf(jArr, length2);
        if (!str7.isEmpty()) {
            if (Qw1.f8796a == null) {
                Qw1.f8796a = new Qw1();
            }
            Qw1 qw1 = Qw1.f8796a;
            qw1.b.a(ContextUtils.getApplicationContext(), str7, new Nw1(qw1, zz0, str7, str, -1));
        } else if (notificationPlatformBridge.e().d(Uri.parse(str3))) {
            C2414eo1 e2 = notificationPlatformBridge.e();
            Uri parse = Uri.parse(str3);
            C3273jq0 jq0 = AbstractC3102iq0.f10166a;
            Objects.requireNonNull(e2);
            e2.b(parse, new Un1(e2, ContextUtils.getApplicationContext().getResources().getString(R.string.f56050_resource_name_obfuscated_RES_2131952922), zz0, str, -1, jq0));
        } else {
            C3444kq0 a2 = notificationPlatformBridge.a(zz0, str, str2, actionInfoArr, bitmap5);
            if (!C2078cq0.b()) {
                vh0 = C5232vH0.c(Boolean.FALSE);
            } else {
                BrowserStartupControllerImpl browserStartupControllerImpl = (BrowserStartupControllerImpl) AbstractC4280pk.a();
                if (browserStartupControllerImpl.f()) {
                    vh02 = C5232vH0.c(null);
                } else {
                    C5232vH0 vh03 = new C5232vH0();
                    browserStartupControllerImpl.a(new C1907bq0(vh03));
                    vh02 = vh03;
                }
                C1564Zp0 zp0 = new C1564Zp0();
                C5232vH0 vh04 = new C5232vH0();
                vh02.h(new C4381qH0(zp0, vh04));
                vh02.a(new C4551rH0(vh04));
                vh0 = vh04.f(new C1736aq0(a2));
            }
            vh0.g(new C1076Rp0(notificationPlatformBridge, a2));
        }
    }
}
