package org.chromium.chrome.browser.sync;

import J.N;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ProfileSyncService {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10770a = {6, 2, 4, 3, 39, 11};
    public static ProfileSyncService b;
    public static boolean c;
    public final List d = new CopyOnWriteArrayList();
    public long e;
    public int f;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class GetAllNodesCallback {
    }

    public ProfileSyncService() {
        Object obj = ThreadUtils.f10596a;
        this.e = N.MulmQvSr(this);
    }

    public static ProfileSyncService b() {
        Object obj = ThreadUtils.f10596a;
        if (!c) {
            ProfileSyncService profileSyncService = new ProfileSyncService();
            b = profileSyncService;
            if (profileSyncService.e == 0) {
                b = null;
            }
            c = true;
        }
        return b;
    }

    public static void onGetAllNodesResult(GetAllNodesCallback getAllNodesCallback, String str) {
        Objects.requireNonNull(getAllNodesCallback);
    }

    public static Set p(int[] iArr) {
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        return hashSet;
    }

    public void a(AbstractC3526lH0 lh0) {
        Object obj = ThreadUtils.f10596a;
        this.d.add(lh0);
    }

    public Set c() {
        return p(N.Mn8VB9Cv(this.e, this));
    }

    public int d() {
        int MmYvMKiO = N.MmYvMKiO(this.e, this);
        if (MmYvMKiO >= 0 && MmYvMKiO < 14) {
            return MmYvMKiO;
        }
        throw new IllegalArgumentException(AbstractC2531fV.w("No state for code: ", MmYvMKiO));
    }

    public int e() {
        int MguEMeJF = N.MguEMeJF(this.e, this);
        if (MguEMeJF >= 0 && MguEMeJF <= 4) {
            return MguEMeJF;
        }
        throw new IllegalArgumentException();
    }

    public C3355kH0 f() {
        return new C3355kH0(this, null);
    }

    public boolean g() {
        return N.MwXg19e5(this.e, this);
    }

    public boolean h() {
        return N.Mga07EF4(this.e, this);
    }

    public boolean i() {
        return N.MaQzF2Pg(this.e, this);
    }

    public boolean j() {
        return N.M7c35BFF(this.e, this);
    }

    public boolean k() {
        return N.MZ9BV1ZG(this.e, this);
    }

    public boolean l() {
        return N.M4TQ0K8e(this.e, this);
    }

    public boolean m() {
        return N.MB8QsrpS(this.e, this);
    }

    public boolean n() {
        return N.MbDTiQl_(this.e, this);
    }

    public boolean o() {
        return N.MKkxCXyK(this.e, this);
    }

    public void q(AbstractC3526lH0 lh0) {
        Object obj = ThreadUtils.f10596a;
        this.d.remove(lh0);
    }

    public void r(boolean z, Set set) {
        int[] iArr;
        long j = this.e;
        if (z) {
            iArr = f10770a;
        } else {
            int[] iArr2 = new int[set.size()];
            Iterator it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr2[i] = ((Integer) it.next()).intValue();
                i++;
            }
            iArr = iArr2;
        }
        N.MRx3HxkB(j, this, z, iArr);
    }

    public void syncStateChanged() {
        for (AbstractC3526lH0 lh0 : this.d) {
            lh0.m();
        }
    }
}
