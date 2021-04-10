package org.chromium.chrome.browser.suggestions.mostvisited;

import J.N;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MostVisitedSitesBridge implements AbstractC0824Nl0 {

    /* renamed from: a  reason: collision with root package name */
    public long f10768a;
    public AbstractC0763Ml0 b;

    public MostVisitedSitesBridge(Profile profile) {
        this.f10768a = N.M8pqI3Tk(this, profile);
    }

    public final void onIconMadeAvailable(GURL gurl) {
        if (this.f10768a != 0) {
            C1425Xh1 xh1 = (C1425Xh1) this.b;
            Objects.requireNonNull(xh1);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < xh1.g.size(); i++) {
                for (C0815Nh1 nh1 : (List) xh1.g.valueAt(i)) {
                    if (nh1.f8567a.b.equals(gurl)) {
                        arrayList.add(nh1);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C4105oi1 oi1 = xh1.d;
                SX0 sx0 = ((C0815Nh1) it.next()).f8567a;
                oi1.b.a(sx0.b, oi1.f, new C1242Uh1(xh1, sx0, false, null));
            }
        }
    }

    public final void onURLsAvailable(String[] strArr, GURL[] gurlArr, int[] iArr, String[] strArr2, int[] iArr2, int[] iArr3, long[] jArr) {
        C0815Nh1 nh1;
        String[] strArr3 = strArr;
        if (this.f10768a != 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(strArr3.length);
            int i = 0;
            while (i < strArr3.length) {
                arrayList2.add(new SX0(strArr3[i], gurlArr[i], strArr2[i], iArr2[i], iArr3[i], iArr[i], new Date(jArr[i])));
                i++;
                strArr3 = strArr;
            }
            arrayList.addAll(arrayList2);
            C1425Xh1 xh1 = (C1425Xh1) this.b;
            Objects.requireNonNull(xh1);
            xh1.h = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                SX0 sx0 = (SX0) it.next();
                xh1.h.add(sx0);
                if (sx0.f == 1) {
                    sx0.b.equals(null);
                    sx0.b.equals(null);
                    if (sx0.e == 7 && !xh1.j) {
                        xh1.j = true;
                        N.Mr5wBIg_(Profile.b(), 0);
                    }
                }
            }
            if (xh1.i) {
                Objects.requireNonNull(xh1.f9228a);
            }
            boolean z = !xh1.i;
            xh1.i = true;
            List list = (List) xh1.g.get(1);
            int size = list == null ? 0 : list.size();
            SparseArray sparseArray = new SparseArray();
            sparseArray.put(1, new ArrayList());
            boolean z2 = z;
            for (int i2 = 0; i2 < xh1.h.size(); i2++) {
                SX0 sx02 = (SX0) xh1.h.get(i2);
                C0815Nh1 b2 = xh1.b(sx02);
                if (b2 == null) {
                    b2 = new C0815Nh1(sx02, i2);
                    z2 = true;
                }
                List list2 = (List) sparseArray.get(sx02.f);
                if (list2 == null) {
                    list2 = new ArrayList();
                    sparseArray.append(sx02.f, list2);
                }
                GURL gurl = sx02.b;
                Iterator it2 = list2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        nh1 = null;
                        break;
                    }
                    nh1 = (C0815Nh1) it2.next();
                    if (nh1.f8567a.b.equals(gurl)) {
                        break;
                    }
                }
                if (nh1 == null) {
                    list2.add(b2);
                }
            }
            xh1.g = sparseArray;
            xh1.h = null;
            List list3 = (List) sparseArray.get(1);
            boolean z3 = z || (list3 == null ? 0 : list3.size()) != size;
            if (z2 || z3) {
                xh1.f.b(false);
                if (z3) {
                    xh1.c.b();
                }
                if (xh1.c()) {
                    xh1.a(2);
                }
                xh1.c.d();
                if (z) {
                    xh1.d(1);
                }
            }
        }
    }
}
