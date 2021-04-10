package defpackage;

import J.N;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.content_public.browser.WebContents;

/* renamed from: od  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4088od {
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Intent r4) {
        /*
            java.lang.String r0 = "AutofillAssistantChromeEntry"
            boolean r0 = J.N.M09VlOh_(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0048
            java.lang.String r0 = "AutofillAssistant"
            boolean r0 = J.N.M09VlOh_(r0)
            if (r0 == 0) goto L_0x0044
            jd r0 = defpackage.C3404kd.f()
            android.os.Bundle r4 = r4.getExtras()
            r0.a(r4)
            kd r4 = r0.f10219a
            java.lang.String r0 = "ENABLED"
            boolean r0 = r4.d(r0)
            if (r0 == 0) goto L_0x003f
            java.util.Map r0 = r4.f10291a
            java.lang.String r3 = "START_IMMEDIATELY"
            java.lang.Object r0 = r0.get(r3)
            if (r0 != 0) goto L_0x0032
            goto L_0x003f
        L_0x0032:
            boolean r0 = r4.d(r3)
            if (r0 != 0) goto L_0x003d
            boolean r4 = r4.c()
            goto L_0x0040
        L_0x003d:
            r4 = r1
            goto L_0x0040
        L_0x003f:
            r4 = r2
        L_0x0040:
            if (r4 == 0) goto L_0x0044
            r4 = r1
            goto L_0x0045
        L_0x0044:
            r4 = r2
        L_0x0045:
            if (r4 == 0) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r1 = r2
        L_0x0049:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC4088od.a(android.content.Intent):boolean");
    }

    public static void b(ChromeActivity chromeActivity, C3404kd kdVar) {
        N.MT4iKtWs("AutofillAssistantTriggered", "Enabled");
        String sb = kdVar.c.toString();
        if (!sb.isEmpty()) {
            for (String str : sb.split(",")) {
                N.MT4iKtWs("AutofillAssistantExperimentsTrial", str);
            }
        }
        AbstractC3364kK0.g("Android.AutofillAssistant.DropOutReason", 0, 28);
        C3575ld ldVar = new C3575ld(kdVar, chromeActivity);
        if (chromeActivity.K0() == null || chromeActivity.K0().l() == null) {
            C1595a3 a3Var = chromeActivity.W0;
            C3917nd ndVar = new C3917nd(chromeActivity, ldVar);
            a3Var.F.b(ndVar);
            ndVar.b(a3Var.H);
            return;
        }
        ldVar.onResult(chromeActivity.K0());
    }

    public static void c(ChromeActivity chromeActivity, C3404kd kdVar, AbstractC4600rd rdVar) {
        AbstractC4448qj a2 = AbstractC5978zj.a(chromeActivity.b0);
        AbstractC2400ek M0 = chromeActivity.M0();
        CompositorViewHolder compositorViewHolder = chromeActivity.I0;
        WebContents R0 = chromeActivity.R0();
        C2971i3 i3Var = chromeActivity.b0;
        L2 l2 = (L2) i3Var.G;
        C2712ga gaVar = i3Var.O;
        C1595a3 a3Var = chromeActivity.W0;
        String str = kdVar.d;
        HashMap hashMap = new HashMap();
        for (String str2 : kdVar.f10291a.keySet()) {
            if (!"ENABLED".equals(str2) && !"CALLER_ACCOUNT".equals(str2)) {
                try {
                    hashMap.put(str2, URLDecoder.decode(kdVar.f10291a.get(str2).toString(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalStateException("Encoding not available.", e);
                }
            }
        }
        String sb = kdVar.c.toString();
        String e2 = kdVar.e("CALLER_ACCOUNT");
        String e3 = kdVar.e("USER_EMAIL");
        if (TextUtils.isEmpty(e3)) {
            Iterator it = kdVar.b.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    e3 = null;
                    break;
                }
                String str3 = (String) it.next();
                if (str3.endsWith("ACCOUNT_NAME")) {
                    e3 = kdVar.b.get(str3).toString();
                    break;
                }
            }
        }
        rdVar.a(a2, M0, compositorViewHolder, chromeActivity, R0, l2, gaVar, a3Var, false, str, hashMap, sb, e2, e3, kdVar.e("ORIGINAL_DEEPLINK"));
    }
}
