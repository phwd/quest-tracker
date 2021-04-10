package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* renamed from: ex1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2441ex1 extends AbstractC4798sm0 {
    public Xx1 f;
    public boolean g;

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        P21 f0 = P21.f0();
        try {
            WebappRegistry.d();
            f0.close();
            WebappRegistry webappRegistry = AbstractC2957hy1.f10115a;
            Objects.requireNonNull(webappRegistry);
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : webappRegistry.b.entrySet()) {
                Xx1 xx1 = (Xx1) entry.getValue();
                if (!TextUtils.isEmpty(xx1.c()) && AbstractC4652ru0.b(ContextUtils.getApplicationContext(), xx1.d())) {
                    arrayList.add((String) entry.getKey());
                }
            }
            Iterator it = arrayList.iterator();
            boolean z = true;
            if (it.hasNext()) {
                Xx1 c = AbstractC2957hy1.f10115a.c((String) it.next());
                String str = c.b;
                Iterator it2 = ((ArrayList) ApplicationStatus.d()).iterator();
                while (it2.hasNext()) {
                    Activity activity = (Activity) it2.next();
                }
                this.f = c;
                if (arrayList.size() <= 1) {
                    z = false;
                }
                this.g = z;
                return 0;
            } else if (arrayList.isEmpty()) {
                return 2;
            } else {
                return 1;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        Xx1 xx1 = this.f;
        RunnableC2270dx1 dx1 = new RunnableC2270dx1(this, ne);
        AbstractC1220Ua0.d("WebApkUpdateManager", "Update now", new Object[0]);
        C2100cx1 cx1 = new C2100cx1(xx1, dx1);
        AbstractC3364kK0.g("WebApk.Update.RequestSent", 3, 4);
        N.MEqkTChv(xx1.c(), cx1);
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return true;
    }
}
