package defpackage;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.WindowManager;
import com.oculus.browser.R;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;

/* renamed from: jN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3198jN {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1652aN f10201a;

    public C3198jN(AbstractC1652aN aNVar) {
        this.f10201a = aNVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0061, code lost:
        if (r2.packageName.equals(r8) == false) goto L_0x001d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x000c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList c(java.util.List r7, java.lang.String r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 147
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3198jN.c(java.util.List, java.lang.String, boolean):java.util.ArrayList");
    }

    public static boolean e(Intent intent) {
        if (intent == null || intent.getData() == null) {
            return false;
        }
        String lastPathSegment = intent.getData().getLastPathSegment();
        if ((lastPathSegment == null || !lastPathSegment.endsWith(".pdf")) && !"application/pdf".equals(intent.getType())) {
            return false;
        }
        return true;
    }

    public static boolean h(String str, String str2, String str3, AbstractC1652aN aNVar, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        Objects.requireNonNull(aNVar);
        boolean z5 = !((C2003cN) aNVar).f() || z;
        if (str2 != null) {
            Pattern pattern = AbstractC5154ur1.f11440a;
            z3 = N.MR0YZiDd(str2);
        } else {
            z3 = false;
        }
        if (str3 != null) {
            Pattern pattern2 = AbstractC5154ur1.f11440a;
            z4 = N.MR0YZiDd(str3);
        } else {
            z4 = false;
        }
        if (!z3 && !z4) {
            return false;
        }
        if (!z3) {
            str2 = str3;
        }
        if (z5) {
            C2003cN cNVar = (C2003cN) aNVar;
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            String packageName = ContextUtils.getApplicationContext().getPackageName();
            intent.putExtra("com.android.browser.application_id", packageName);
            if (z2) {
                intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
            }
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setClassName(packageName, Lr.class.getName());
            intent.addFlags(268435456);
            S20.a(intent);
            p(intent, false, cNVar);
            if (z) {
                cNVar.g();
            }
            return false;
        }
        LoadUrlParams loadUrlParams = new LoadUrlParams(str2, 6);
        if (!TextUtils.isEmpty(str)) {
            loadUrlParams.d = new C2512fL0(str, 0);
        }
        C2003cN cNVar2 = (C2003cN) aNVar;
        if (cNVar2.m()) {
            cNVar2.b.c(loadUrlParams);
        }
        return true;
    }

    public static void j(Intent intent) {
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("org.chromium.chrome.browser.eenp");
        if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
            AbstractC3535lK0.a("MobileExternalNavigationDispatched");
        }
    }

    public static boolean k(Intent intent, boolean z) {
        boolean z2;
        Context applicationContext = ContextUtils.getApplicationContext();
        ResolveInfo d = PackageManagerUtils.d(intent, 0);
        if (d == null) {
            return false;
        }
        String packageName = applicationContext.getPackageName();
        if (d.match == 0) {
            List c = PackageManagerUtils.c(intent, 65536);
            if (c == null || c.isEmpty()) {
                return false;
            }
            Iterator it = c.iterator();
            boolean z3 = false;
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                ResolveInfo resolveInfo = (ResolveInfo) it.next();
                String str = resolveInfo.activityInfo.packageName;
                if (!packageName.equals(str)) {
                    if ("com.google.android.apps.docs".equals(str) && e(intent)) {
                        intent.setClassName(str, resolveInfo.activityInfo.name);
                        intent.putExtra("android.intent.extra.REFERRER", new Uri.Builder().scheme("android-app").authority(packageName).build());
                        z2 = true;
                        break;
                    }
                } else {
                    z3 = true;
                }
            }
            if (!z3 || z || z2) {
                return true;
            }
            return false;
        } else if (z || !packageName.equals(d.activityInfo.packageName)) {
            return true;
        } else {
            return false;
        }
    }

    public static void p(Intent intent, boolean z, AbstractC1652aN aNVar) {
        if (intent != null) {
            try {
                if (e(intent)) {
                    k(intent, true);
                }
            } catch (RuntimeException e) {
                U20.b(e, intent);
            }
        }
        if (z) {
            ((C2003cN) aNVar).h(intent);
        } else {
            Context a2 = ContextUtils.a(((C2003cN) aNVar).j());
            if (a2 == null) {
                a2 = ContextUtils.getApplicationContext();
                intent.addFlags(268435456);
            }
            a2.startActivity(intent);
        }
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("org.chromium.chrome.browser.eenp");
        if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
            AbstractC3535lK0.a("MobileExternalNavigationDispatched");
        }
        aNVar.a(intent);
    }

    public boolean a(String str) {
        if (str.startsWith("wtai://wp/mc;")) {
            return true;
        }
        try {
            Intent parseUri = Intent.parseUri(str, 1);
            if (parseUri.getPackage() != null) {
                return true;
            }
            List i = i(parseUri);
            return i != null && !i.isEmpty();
        } catch (URISyntaxException e) {
            AbstractC1220Ua0.f("UrlHandler", "Bad URI %s", str, e);
            return false;
        }
    }

    public final String b() {
        NavigationController f;
        int m;
        NavigationEntry l;
        if (!((C2003cN) this.f10201a).m() || ((C2003cN) this.f10201a).k() == null || (m = (f = ((C2003cN) this.f10201a).k().f()).m()) == -1 || (l = f.l(m)) == null) {
            return null;
        }
        return l.b.h();
    }

    public final boolean d(List list, C3540lN lNVar) {
        return false;
    }

    public boolean f() {
        String b = b();
        if (b == null) {
            return false;
        }
        return N.M$l72hrq(b);
    }

    public final boolean g(List list, Intent intent) {
        Objects.requireNonNull(this.f10201a);
        ArrayList c = c(list, null, true);
        if (c.size() == 1) {
            String str = (String) c.get(0);
            byte[] bArr = AbstractC3797mu.f10456a;
            byte[] bArr2 = AbstractC3797mu.b;
            if (AbstractC2612fx1.f9968a == null) {
                AbstractC2612fx1.f9968a = bArr;
            }
            if (AbstractC2612fx1.b == null) {
                AbstractC2612fx1.b = bArr2;
            }
            if (AbstractC2612fx1.b(ContextUtils.getApplicationContext(), str)) {
                Intent intent2 = new Intent(intent);
                intent2.setPackage((String) c.get(0));
                try {
                    p(intent2, false, this.f10201a);
                    return true;
                } catch (ActivityNotFoundException unused) {
                }
            }
        }
        return false;
    }

    public final List i(Intent intent) {
        return PackageManagerUtils.c(intent, 64);
    }

    public final void l(Intent intent) {
        intent.setFlags(intent.getFlags() & 1007171600);
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setComponent(null);
        Intent selector = intent.getSelector();
        if (selector != null) {
            selector.addCategory("android.intent.category.BROWSABLE");
            selector.setComponent(null);
        }
    }

    public final C3028iN m(String str, String str2, C3540lN lNVar) {
        Intent intent = new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("market").authority("details").appendQueryParameter("id", str).appendQueryParameter("referrer", Uri.decode(str2)).build());
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setPackage("com.android.vending");
        intent.addFlags(268435456);
        String str3 = lNVar.c;
        if (str3 != null) {
            intent.putExtra("android.intent.extra.REFERRER", Uri.parse(str3));
        }
        List i = i(intent);
        boolean z = false;
        if (!(i != null && !i.isEmpty())) {
            return C3028iN.c();
        }
        if (lNVar.b) {
            try {
                z = r(intent, lNVar.c, null, lNVar.l, false);
            } catch (WindowManager.BadTokenException unused) {
            }
            if (!z) {
                return C3028iN.c();
            }
            return C3028iN.a(1);
        }
        p(intent, false, this.f10201a);
        return C3028iN.b();
    }

    public final boolean n(C3540lN lNVar) {
        if ((lNVar.d & 255) == 3) {
            return true;
        }
        if ((lNVar.f && !this.f10201a.e()) || lNVar.i) {
            return true;
        }
        return (lNVar.d & 16777216) != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0249, code lost:
        if ((r2.g == 2) != false) goto L_0x024b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x02ed, code lost:
        if (r6 == null) goto L_0x0313;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0324, code lost:
        if (r6 == null) goto L_0x0331;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00af, code lost:
        if (r6 != false) goto L_0x00b3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x0318  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x031e  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0336  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x033c  */
    /* JADX WARNING: Removed duplicated region for block: B:417:0x06a2  */
    /* JADX WARNING: Removed duplicated region for block: B:419:0x06ab  */
    /* JADX WARNING: Removed duplicated region for block: B:451:0x077b  */
    /* JADX WARNING: Removed duplicated region for block: B:452:0x077f  */
    /* JADX WARNING: Removed duplicated region for block: B:454:0x0784  */
    /* JADX WARNING: Removed duplicated region for block: B:461:0x07a9 A[SYNTHETIC, Splitter:B:461:0x07a9] */
    /* JADX WARNING: Removed duplicated region for block: B:468:0x07ca  */
    /* JADX WARNING: Removed duplicated region for block: B:471:0x07e6  */
    /* JADX WARNING: Removed duplicated region for block: B:487:0x0843  */
    /* JADX WARNING: Removed duplicated region for block: B:515:0x08c9  */
    /* JADX WARNING: Removed duplicated region for block: B:517:0x08d3  */
    /* JADX WARNING: Removed duplicated region for block: B:530:0x08f5  */
    /* JADX WARNING: Removed duplicated region for block: B:531:0x08fa  */
    /* JADX WARNING: Removed duplicated region for block: B:534:0x090f  */
    /* JADX WARNING: Removed duplicated region for block: B:545:0x092e  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0165  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public defpackage.C3028iN o(defpackage.C3540lN r37) {
        /*
        // Method dump skipped, instructions count: 2782
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3198jN.o(lN):iN");
    }

    public final boolean q(Intent intent, boolean z) {
        StrictMode.ThreadPolicy allowThreadDiskWrites;
        int c = this.f10201a.c(intent, z);
        boolean z2 = true;
        if (c == 0) {
            return true;
        }
        if (c == 1 || c != 2) {
            return false;
        }
        allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        if (intent != null) {
            try {
                if (e(intent)) {
                    k(intent, true);
                }
            } catch (SecurityException unused) {
            } catch (RuntimeException e) {
                U20.b(e, intent);
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                throw th;
            }
        }
        if (z) {
            ((C2003cN) this.f10201a).h(intent);
        } else {
            Activity a2 = ContextUtils.a(((C2003cN) this.f10201a).j());
            z2 = a2 != null ? a2.startActivityIfNeeded(intent, -1) : false;
        }
        if (z2) {
            j(intent);
        }
        StrictMode.setThreadPolicy(allowThreadDiskWrites);
        return z2;
        StrictMode.setThreadPolicy(allowThreadDiskWrites);
        return false;
    }

    public boolean r(Intent intent, String str, String str2, boolean z, boolean z2) {
        if (!((C2003cN) this.f10201a).m()) {
            return false;
        }
        Context j = ((C2003cN) this.f10201a).j();
        if (ContextUtils.a(j) == null) {
            return false;
        }
        C2246dp1 dp1 = new C2246dp1(j, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        dp1.g(R.string.f52160_resource_name_obfuscated_RES_2131952533);
        dp1.c(R.string.f52150_resource_name_obfuscated_RES_2131952532);
        dp1.e(R.string.f52130_resource_name_obfuscated_RES_2131952530, new DialogInterface$OnClickListenerC2857hN(this, intent, z2, z, str, str2));
        dp1.d(R.string.f52140_resource_name_obfuscated_RES_2131952531, new DialogInterface$OnClickListenerC2686gN(this, str, str2, intent, z));
        dp1.f9828a.l = new DialogInterface$OnCancelListenerC2515fN(this, str, str2, intent, z);
        dp1.i();
        return true;
    }
}
