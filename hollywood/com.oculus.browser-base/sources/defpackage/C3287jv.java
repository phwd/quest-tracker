package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.browserservices.verification.OriginVerifier;

/* renamed from: jv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3287jv {

    /* renamed from: a  reason: collision with root package name */
    public final C4819st0 f10247a = new C4819st0();
    public final Map b = new HashMap();
    public final SparseBooleanArray c = new SparseBooleanArray();

    public C3287jv() {
        C2173dM0.b();
    }

    public final synchronized void a(CustomTabsSessionToken customTabsSessionToken) {
        C3116iv ivVar = (C3116iv) this.b.get(customTabsSessionToken);
        if (ivVar != null) {
            this.b.remove(customTabsSessionToken);
            KE0 ke0 = ivVar.e;
            if (ke0 != null) {
                Context applicationContext = ContextUtils.getApplicationContext();
                AbstractC3222jZ jZVar = ke0.c;
                boolean z = true;
                if (jZVar != null) {
                    if (jZVar == null) {
                        z = false;
                    }
                    if (z) {
                        applicationContext.unbindService(ke0);
                        ke0.c = null;
                    }
                }
            }
            OriginVerifier originVerifier = ivVar.g;
            if (originVerifier != null) {
                originVerifier.b();
            }
            C4194pC pCVar = ivVar.c;
            if (pCVar != null) {
                pCVar.f11056a.b(customTabsSessionToken);
                Objects.requireNonNull(pCVar.f11056a);
                C1622aC c2 = AbstractApplicationC3785mq.g().c();
                if (c2.c) {
                    Bitmap bitmap = (Bitmap) ((QY0) c2.b.get()).f8768a.remove(customTabsSessionToken);
                }
            }
            this.c.delete(ivVar.f10172a);
        }
    }

    public synchronized C5216vC b(CustomTabsSessionToken customTabsSessionToken) {
        if (customTabsSessionToken != null) {
            if (this.b.containsKey(customTabsSessionToken)) {
                return ((C3116iv) this.b.get(customTabsSessionToken)).b;
            }
        }
        return null;
    }

    public synchronized String c(CustomTabsSessionToken customTabsSessionToken) {
        String str;
        C3116iv ivVar = (C3116iv) this.b.get(customTabsSessionToken);
        if (ivVar == null) {
            str = null;
        } else {
            str = ivVar.j;
        }
        return str;
    }

    public synchronized C2512fL0 d(CustomTabsSessionToken customTabsSessionToken) {
        C2512fL0 fl0;
        String c2 = c(customTabsSessionToken);
        ComponentName componentName = S20.f8870a;
        if (TextUtils.isEmpty(c2)) {
            fl0 = null;
        } else {
            fl0 = new C2512fL0(new Uri.Builder().scheme("android-app").authority(c2).build().toString(), 1);
        }
        return fl0;
    }

    public synchronized boolean e(CustomTabsSessionToken customTabsSessionToken, C4649rt0 rt0) {
        boolean z;
        String c2 = c(customTabsSessionToken);
        String c3 = OriginVerifier.c(c2);
        z = true;
        if (!OriginVerifier.e(c2, rt0, 1)) {
            AL0 al0 = new AL0(c2, c3, rt0, 1);
            if (!((HashSet) Hs1.a()).contains(al0.toString())) {
                z = false;
            }
        }
        return z;
    }

    public final synchronized boolean f(CustomTabsSessionToken customTabsSessionToken, int i, C4649rt0 rt0, boolean z) {
        C3116iv ivVar = (C3116iv) this.b.get(customTabsSessionToken);
        if (ivVar != null) {
            if (!TextUtils.isEmpty(ivVar.j)) {
                C2775gv gvVar = new C2775gv(this, rt0, customTabsSessionToken, i, z, ivVar);
                C4819st0 st0 = this.f10247a;
                String str = ivVar.j;
                C3938nk nkVar = new C3938nk();
                Objects.requireNonNull(st0);
                ivVar.g = new OriginVerifier(str, i, null, null, nkVar);
                PostTask.c(Zo1.f9374a, new RunnableC2946hv(ivVar, gvVar, rt0));
                if (i == 2) {
                    try {
                        if (C4677s20.i0(ivVar.j, new Vo1(rt0.toString()), new C3627lu0())) {
                            ivVar.f.add(rt0);
                        }
                    } catch (URISyntaxException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
