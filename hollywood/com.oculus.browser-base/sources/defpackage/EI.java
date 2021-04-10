package defpackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: EI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EI {

    /* renamed from: a  reason: collision with root package name */
    public final List f7952a = new ArrayList();
    public final C1322Vq0 b = new C1322Vq0();
    public PU0 c;

    public EI(CI ci) {
        PU0 pu0 = NU0.f8549a;
        this.c = pu0;
        pu0.f8694a.a("PendingDownloadNotifications");
        if (AbstractC3983nz.f10523a.contains("PendingDownloadNotifications")) {
            PU0 pu02 = this.c;
            Set set = DownloadManagerService.F;
            Iterator it = new HashSet(pu02.j("PendingDownloadNotifications")).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (BI.b(str).b > 0) {
                    this.f7952a.add(BI.b(str));
                }
            }
        }
    }

    public void a(BI bi, boolean z) {
        Iterator it = this.f7952a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BI bi2 = (BI) it.next();
            if (bi2.g.equals(bi.g)) {
                if (!bi2.equals(bi)) {
                    it.remove();
                } else {
                    return;
                }
            }
        }
        this.f7952a.add(bi);
        d(z);
        Iterator it2 = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it2;
            if (uq0.hasNext()) {
                ((AbstractC4212pI) uq0.next()).f(bi.g);
            } else {
                return;
            }
        }
    }

    public BI b(C0788My my) {
        for (int i = 0; i < this.f7952a.size(); i++) {
            if (((BI) this.f7952a.get(i)).g.equals(my)) {
                return (BI) this.f7952a.get(i);
            }
        }
        return null;
    }

    public void c(C0788My my) {
        boolean z;
        Iterator it = this.f7952a.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((BI) it.next()).g.equals(my)) {
                    it.remove();
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            d(false);
        }
    }

    public final void d(boolean z) {
        String str;
        String str2;
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.f7952a.size(); i++) {
            BI bi = (BI) this.f7952a.get(i);
            Objects.requireNonNull(bi);
            String str3 = "1";
            StringBuilder i2 = AbstractC2531fV.i(AbstractC2531fV.h(AbstractC2531fV.i(AbstractC2531fV.h(AbstractC2531fV.i(AbstractC2531fV.h(AbstractC2531fV.i("6," + bi.b + ","), bi.g.f8514a, ",")), bi.g.b, ",")), bi.c ? str3 : "0", ","));
            if (bi.d) {
                str = str3;
            } else {
                str = "0";
            }
            StringBuilder i3 = AbstractC2531fV.i(AbstractC2531fV.h(i2, str, ","));
            if (bi.f) {
                str2 = str3;
            } else {
                str2 = "0";
            }
            StringBuilder i4 = AbstractC2531fV.i(AbstractC2531fV.h(i3, str2, ","));
            if (!bi.h) {
                str3 = "0";
            }
            StringBuilder i5 = AbstractC2531fV.i(AbstractC2531fV.h(i4, str3, ","));
            i5.append(bi.e);
            hashSet.add(i5.toString());
        }
        DownloadManagerService.J(this.c, "PendingDownloadNotifications", hashSet, z);
    }
}
