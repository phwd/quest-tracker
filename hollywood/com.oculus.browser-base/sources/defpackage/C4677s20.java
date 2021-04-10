package defpackage;

import J.N;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: s20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4677s20 implements AbstractC1944c20 {
    public final C2115d20 F;
    public final C3627lu0 G;
    public final N20 H;

    public C4677s20(C2115d20 d20, C3627lu0 lu0, N20 n20) {
        this.F = d20;
        this.G = lu0;
        this.H = n20;
    }

    public static JSONArray h0(String str, C3627lu0 lu0) {
        Bundle bundle;
        Objects.requireNonNull(lu0);
        ApplicationInfo applicationInfo = ContextUtils.getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
            return new JSONArray();
        }
        int i = bundle.getInt("asset_statements");
        if (i == 0) {
            return new JSONArray();
        }
        try {
            try {
                return new JSONArray(ContextUtils.getApplicationContext().getPackageManager().getResourcesForApplication(applicationInfo).getString(i));
            } catch (JSONException unused) {
                StringBuilder k = AbstractC2531fV.k("Android package ", str, " has JSON syntax error in asset statements resource (0x");
                k.append(Integer.toHexString(i));
                k.append(").");
                AbstractC1220Ua0.f("InstalledAppProvider", k.toString(), new Object[0]);
                return new JSONArray();
            }
        } catch (Resources.NotFoundException unused2) {
            StringBuilder k2 = AbstractC2531fV.k("Android package ", str, " missing asset statements resource (0x");
            k2.append(Integer.toHexString(i));
            k2.append(").");
            AbstractC1220Ua0.f("InstalledAppProvider", k2.toString(), new Object[0]);
            return new JSONArray();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0082 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0083 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean i0(java.lang.String r5, org.chromium.url.GURL r6, defpackage.C3627lu0 r7) {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4677s20.i0(java.lang.String, org.chromium.url.GURL, lu0):boolean");
    }

    @Override // defpackage.AbstractC1944c20
    public void V(C5922zL0[] zl0Arr, Cq1 cq1, C5527x20 x20) {
        GURL gurl;
        boolean z;
        C4677s20 s20 = this;
        String i = s20.F.f9741a.i();
        if (i == null) {
            gurl = GURL.emptyGURL();
        } else {
            gurl = new GURL(i);
        }
        int min = Math.min(zl0Arr.length, 3);
        C4506r20 r20 = new C4506r20(min, new C2456f20(s20, x20));
        int i2 = 0;
        while (i2 < min) {
            C5922zL0 zl0 = zl0Arr[i2];
            if (s20.j0(zl0)) {
                PostTask.b(C3070if1.b, new RunnableC3139j20(this, r20, i2, zl0, gurl), 0);
            } else {
                if (zl0.d.equals("play") && zl0.f != null) {
                    z = !s20.j0(zl0);
                } else {
                    z = false;
                }
                if (z) {
                    PostTask.b(C3070if1.b, new RunnableC3310k20(this, r20, i2, zl0, gurl), 0);
                } else if (s20.k0(zl0) && zl0.e.equals(cq1.d)) {
                    PostTask.b(C3070if1.b, new RunnableC3481l20(s20, r20, i2, zl0), 0);
                } else if (s20.k0(zl0)) {
                    N.MnDEbWkz(Profile.b(), zl0.e, cq1.d, new C2798h20(this, r20, i2, zl0, s20.f0(zl0.e)));
                } else {
                    r20.a(null, i2, 0);
                    i2++;
                    s20 = this;
                }
            }
            i2++;
            s20 = this;
        }
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }

    public final int f0(String str) {
        boolean a2 = this.F.f9741a.a();
        if (C2602fu0.F == null) {
            C2602fu0.F = new C2602fu0();
        }
        C2602fu0 fu0 = C2602fu0.F;
        byte[] bArr = a2 ? fu0.H : fu0.G;
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            byte[] b = AbstractC3153j7.b(str);
            try {
                instance.init(new SecretKeySpec(bArr, "HmacSHA256"));
                byte[] doFinal = instance.doFinal(b);
                return (((short) ((doFinal[1] & 255) | ((doFinal[0] & 255) << 8))) & 1023) / 100;
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final void g0(C4506r20 r20, int i, C5922zL0 zl0) {
        String str;
        int f0 = f0(zl0.e);
        String str2 = zl0.e;
        List<PackageInfo> installedPackages = ContextUtils.getApplicationContext().getPackageManager().getInstalledPackages(192);
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= installedPackages.size()) {
                str = null;
                break;
            }
            PackageInfo packageInfo = installedPackages.get(i2);
            if (AbstractC2612fx1.f(packageInfo, packageInfo.packageName) && TextUtils.equals(packageInfo.applicationInfo.metaData.getString("org.chromium.webapk.shell_apk.webManifestUrl"), str2)) {
                str = packageInfo.packageName;
                break;
            }
            i2++;
        }
        if (str != null) {
            z = true;
        }
        if (!z) {
            PostTask.b(Zo1.f9374a, new RunnableC4336q20(r20, i, f0), 0);
            return;
        }
        PostTask.b(Zo1.f9374a, new RunnableC2627g20(r20, zl0, i, f0), 0);
    }

    public final boolean j0(C5922zL0 zl0) {
        String str;
        if (!zl0.d.equals("play") || (str = zl0.f) == null) {
            return false;
        }
        if ("instantapp".equals(str) || "instantapp:holdback".equals(zl0.f)) {
            return true;
        }
        return false;
    }

    public final boolean k0(C5922zL0 zl0) {
        if (zl0.d.equals("webapp") && zl0.e != null) {
            return true;
        }
        return false;
    }

    public final void l0(C5922zL0 zl0) {
        try {
            C3627lu0 lu0 = this.G;
            String str = zl0.f;
            Objects.requireNonNull(lu0);
            zl0.g = ContextUtils.getApplicationContext().getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
