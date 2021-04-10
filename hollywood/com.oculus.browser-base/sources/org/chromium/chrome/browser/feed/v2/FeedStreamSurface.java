package org.chromium.chrome.browser.feed.v2;

import J.N;
import android.app.Activity;
import android.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FeedStreamSurface extends AbstractC4567rO implements AbstractC1600a41 {

    /* renamed from: a  reason: collision with root package name */
    public static PG0 f10671a;
    public static boolean b;
    public static HashSet c;
    public final long d = N.MoVwmkuF(this);
    public final C5757yO e;
    public final AbstractC1780b41 f;
    public RecyclerView g;
    public final AbstractC3391kY h;
    public BO i;
    public final NO j = new NO(this, null);
    public final C1322Vq0 k = new C1322Vq0();
    public final MO l = new MO(this, null);
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public final int q = N.Merjco_l();
    public boolean r;
    public boolean s;

    public FeedStreamSurface(Activity activity, boolean z, View$OnClickListenerC5098uY0 uy0, AbstractC0095Bm0 bm0, AbstractC4448qj qjVar, C2535fX fXVar, boolean z2, OO oo) {
        C5757yO yOVar = new C5757yO(this, this);
        this.e = yOVar;
        this.s = z2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(activity, z ? R.style.f68450_resource_name_obfuscated_RES_2132017418 : R.style.f68730_resource_name_obfuscated_RES_2132017446);
        PG0 e2 = e();
        if (e2 != null) {
            this.f = e2.b(new JO(this, contextThemeWrapper, z));
        } else {
            this.f = null;
        }
        AbstractC1780b41 b41 = this.f;
        if (b41 != null) {
            this.h = b41.b();
        } else {
            this.h = new C0461Hm0(contextThemeWrapper);
        }
        AbstractC3391kY kYVar = this.h;
        if (kYVar != null) {
            C0461Hm0 hm0 = (C0461Hm0) kYVar;
            hm0.f8180J = yOVar;
            RecyclerView recyclerView = new RecyclerView(hm0.I, null);
            hm0.K = recyclerView;
            recyclerView.q0(hm0);
            hm0.K.t0(new LinearLayoutManager(hm0.I));
            ((C5757yO) hm0.f8180J).b.add(hm0);
            hm0.F.e(0, hm0.f8180J.a());
            RecyclerView recyclerView2 = hm0.K;
            this.g = recyclerView2;
            this.i = new BO(recyclerView2, yOVar, new PO(this, null));
        } else {
            this.g = null;
        }
        if (c == null) {
            c = new HashSet();
        }
        c.add(this);
    }

    public static PG0 e() {
        if (f10671a == null) {
            AppHooks appHooks = AppHooks.get();
            BundleUtils.c(AO.a(ContextUtils.getApplicationContext()), "feedv2");
            f10671a = appHooks.e();
        }
        return f10671a;
    }

    public final /* synthetic */ void a() {
        this.r = false;
    }

    public final void b() {
        Objects.requireNonNull((C0461Hm0) this.h);
        int a2 = this.e.a();
        int i2 = this.p;
        int i3 = a2 - i2;
        if (i3 > 0) {
            this.e.d(i2, i3);
            this.l.a();
        }
        this.j.a();
        BO bo = this.i;
        bo.H.clear();
        bo.I = false;
        if (this.f != null) {
            N.MWrRh44q(this.d, this);
        }
        this.m = false;
    }

    public final void c(ArrayList arrayList) {
        int i2;
        int i3;
        int i4;
        HashSet hashSet = new HashSet();
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        while (i6 < arrayList.size()) {
            hashSet.add(((AbstractC5417wO) arrayList.get(i6)).f11544a);
            i6++;
            z = true;
        }
        HashMap hashMap = new HashMap();
        for (int i7 = 0; i7 < this.e.a(); i7++) {
            AbstractC5417wO c2 = this.e.c(i7);
            hashMap.put(c2.f11544a, c2);
        }
        for (int a2 = this.e.a() - 1; a2 >= 0; a2--) {
            String str = this.e.c(a2).f11544a;
            if (!hashSet.contains(str)) {
                this.e.d(a2, 1);
                hashMap.remove(str);
                z = true;
            }
        }
        while (i5 < arrayList.size()) {
            AbstractC5417wO wOVar = (AbstractC5417wO) arrayList.get(i5);
            if (hashMap.containsKey(wOVar.f11544a)) {
                C5757yO yOVar = this.e;
                int b2 = yOVar.b(wOVar.f11544a);
                if (b2 < i5) {
                    i4 = -1;
                    i2 = b2;
                    i3 = i5;
                } else {
                    if (b2 > i5) {
                        i3 = b2;
                        i4 = 1;
                        i2 = i5;
                    }
                    i5++;
                }
                Collections.rotate(yOVar.f11677a.subList(i2, i3 + 1), i4);
                Iterator it = yOVar.b.iterator();
                while (it.hasNext()) {
                    ((C0461Hm0) it.next()).F.c(b2, i5);
                }
                i5++;
            } else {
                int i8 = i5 + 1;
                while (i8 < arrayList.size() && !hashMap.containsKey(((AbstractC5417wO) arrayList.get(i8)).f11544a)) {
                    i8++;
                }
                C5757yO yOVar2 = this.e;
                List subList = arrayList.subList(i5, i8);
                yOVar2.f11677a.addAll(i5, subList);
                Iterator it2 = yOVar2.b.iterator();
                while (it2.hasNext()) {
                    ((C0461Hm0) it2.next()).F.e(i5, subList.size());
                }
                i5 = i8;
            }
            z = true;
        }
        if (z) {
            this.l.a();
        }
    }

    public final void d() {
        boolean z = b && this.n && this.o;
        if (z != this.m) {
            if (z) {
                this.m = true;
                if (this.f != null) {
                    N.MLxw_owz(this.d, this);
                }
                Objects.requireNonNull((C0461Hm0) this.h);
                return;
            }
            b();
        }
    }

    public void onStreamUpdated(byte[] bArr) {
        C3373kP kPVar;
        Object obj;
        C4057oP oPVar;
        C3886nP nPVar;
        if (this.m) {
            try {
                C3715mP mPVar = (C3715mP) AbstractC2360eV.k(C3715mP.e, bArr);
                for (C3202jP jPVar : mPVar.h) {
                    AbstractC3391kY kYVar = this.h;
                    jPVar.g.l();
                    Objects.requireNonNull((C0461Hm0) kYVar);
                }
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < this.p; i2++) {
                    arrayList.add(this.e.c(i2));
                }
                for (C3544lP lPVar : mPVar.g) {
                    int i3 = lPVar.g;
                    if (i3 == 1) {
                        if (i3 == 1) {
                            kPVar = (C3373kP) lPVar.h;
                        } else {
                            C3373kP kPVar2 = C3373kP.e;
                            kPVar = C3373kP.e;
                        }
                        String str = kPVar.i;
                        int i4 = kPVar.g;
                        if (i4 == 1) {
                            if (i4 == 1) {
                                nPVar = (C3886nP) kPVar.h;
                            } else {
                                nPVar = C3886nP.e;
                            }
                            obj = new C5247vO(str, nPVar.g.l());
                        } else if (!(i4 == 4)) {
                            if (i4 == 3) {
                                oPVar = (C4057oP) kPVar.h;
                            } else {
                                C4057oP oPVar2 = C4057oP.e;
                                oPVar = C4057oP.e;
                            }
                            if (oPVar.m() == 3) {
                                obj = new C5587xO(str, (int) R.layout.f39910_resource_name_obfuscated_RES_2131624300);
                            } else {
                                obj = new C5587xO(str, (int) R.layout.f39920_resource_name_obfuscated_RES_2131624301);
                            }
                        } else if (this.s) {
                            obj = null;
                        } else {
                            obj = new C5587xO(str, (int) R.layout.f38540_resource_name_obfuscated_RES_2131624163);
                        }
                        if (obj != null) {
                            arrayList.add(obj);
                        }
                    } else {
                        int b2 = this.e.b(i3 == 2 ? (String) lPVar.h : "");
                        if (b2 != -1) {
                            arrayList.add(this.e.c(b2));
                        }
                    }
                }
                c(arrayList);
            } catch (L30 e2) {
                AbstractC1220Ua0.g("FeedStreamSurface", "Unable to parse StreamUpdate proto data", e2);
            }
        }
    }

    public void removeDataStoreEntry(String str) {
        AbstractC1780b41 b41 = this.f;
        if (b41 != null) {
            b41.c(str);
        }
    }

    public void replaceDataStoreEntry(String str, byte[] bArr) {
        AbstractC1780b41 b41 = this.f;
        if (b41 != null) {
            b41.a(str, bArr);
        }
    }
}
