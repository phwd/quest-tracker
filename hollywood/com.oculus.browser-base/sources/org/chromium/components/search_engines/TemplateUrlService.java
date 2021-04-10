package org.chromium.components.search_engines;

import J.N;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TemplateUrlService {

    /* renamed from: a  reason: collision with root package name */
    public final C1322Vq0 f10887a = new C1322Vq0();
    public final C1322Vq0 b = new C1322Vq0();
    public long c;

    public TemplateUrlService(long j) {
        this.c = j;
    }

    public static void addTemplateUrlToList(List list, TemplateUrl templateUrl) {
        list.add(templateUrl);
    }

    public static TemplateUrlService create(long j) {
        return new TemplateUrlService(j);
    }

    public TemplateUrl a() {
        if (!g()) {
            return null;
        }
        return (TemplateUrl) N.MxujzkW4(this.c, this);
    }

    public String b(String str) {
        return N.MjOvYRBS(this.c, this, str);
    }

    public String c(GURL gurl) {
        return N.MfK2IDmL(this.c, this, gurl);
    }

    public final void clearNativePtr() {
        this.c = 0;
    }

    public GURL d(String str) {
        return (GURL) N.MA0BGHUQ(this.c, this, str);
    }

    public boolean e() {
        return N.MWMFuBEz(this.c, this);
    }

    public boolean f() {
        return N.MELaF8Vs(this.c, this);
    }

    public boolean g() {
        Object obj = ThreadUtils.f10596a;
        return N.M4Z0aoFH(this.c, this);
    }

    public void h() {
        Object obj = ThreadUtils.f10596a;
        N.MVKcMDBb(this.c, this);
    }

    public void i(AbstractC0322Ff1 ff1) {
        Object obj = ThreadUtils.f10596a;
        this.f10887a.b(ff1);
        if (g()) {
            PostTask.b(Zo1.f9374a, new RunnableC0200Df1(this, ff1), 0);
        }
    }

    public void j(Runnable runnable) {
        if (g()) {
            runnable.run();
            return;
        }
        i(new C0261Ef1(this, runnable));
        h();
    }

    public void k(AbstractC0322Ff1 ff1) {
        Object obj = ThreadUtils.f10596a;
        this.f10887a.c(ff1);
    }

    public final void onTemplateURLServiceChanged() {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0383Gf1) uq0.next()).N();
            } else {
                return;
            }
        }
    }

    public final void templateUrlServiceLoaded() {
        Object obj = ThreadUtils.f10596a;
        Iterator it = this.f10887a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0322Ff1) uq0.next()).f();
            } else {
                return;
            }
        }
    }
}
