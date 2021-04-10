package defpackage;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.oculus.browser.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* renamed from: tg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4950tg0 extends AbstractC5750yK0 {
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public final LayoutInflater f11359J;
    public final Drawable K;
    public final Drawable L;
    public final Drawable M;
    public final Drawable N;
    public C4440qg0 O;
    public final int P;
    public final Interpolator Q;
    public final /* synthetic */ DialogC5460wg0 R;

    public C4950tg0(DialogC5460wg0 wg0) {
        this.R = wg0;
        this.f11359J = LayoutInflater.from(wg0.Q);
        this.K = AbstractC4783sh0.e(wg0.Q, R.attr.f6430_resource_name_obfuscated_RES_2130969089);
        this.L = AbstractC4783sh0.e(wg0.Q, R.attr.f6520_resource_name_obfuscated_RES_2130969098);
        this.M = AbstractC4783sh0.e(wg0.Q, R.attr.f6490_resource_name_obfuscated_RES_2130969095);
        this.N = AbstractC4783sh0.e(wg0.Q, R.attr.f6480_resource_name_obfuscated_RES_2130969094);
        this.P = wg0.Q.getResources().getInteger(R.integer.f35940_resource_name_obfuscated_RES_2131492891);
        this.Q = new AccelerateDecelerateInterpolator();
        w();
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.I.size() + 1;
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        C4440qg0 qg0;
        if (i == 0) {
            qg0 = this.O;
        } else {
            qg0 = (C4440qg0) this.I.get(i - 1);
        }
        return qg0.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x012c, code lost:
        if ((r10 == null || r10.c) != false) goto L_0x0131;
     */
    @Override // defpackage.AbstractC5750yK0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j(defpackage.XK0 r9, int r10) {
        /*
        // Method dump skipped, instructions count: 478
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4950tg0.j(XK0, int):void");
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new C4098og0(this, this.f11359J.inflate(R.layout.f39360_resource_name_obfuscated_RES_2131624245, viewGroup, false));
        }
        if (i == 2) {
            return new C4269pg0(this, this.f11359J.inflate(R.layout.f39370_resource_name_obfuscated_RES_2131624246, viewGroup, false));
        }
        if (i == 3) {
            return new C4780sg0(this, this.f11359J.inflate(R.layout.f39390_resource_name_obfuscated_RES_2131624248, viewGroup, false));
        }
        if (i == 4) {
            return new C3927ng0(this, this.f11359J.inflate(R.layout.f39350_resource_name_obfuscated_RES_2131624244, viewGroup, false));
        }
        Log.w("MediaRouteCtrlDialog", "Cannot create ViewHolder because of wrong view type");
        return null;
    }

    @Override // defpackage.AbstractC5750yK0
    public void q(XK0 xk0) {
        this.R.Y.values().remove(xk0);
    }

    public void s(View view, int i) {
        C3414kg0 kg0 = new C3414kg0(this, i, view.getLayoutParams().height, view);
        kg0.setAnimationListener(new animation.Animation$AnimationListenerC3585lg0(this));
        kg0.setDuration((long) this.P);
        kg0.setInterpolator(this.Q);
        view.startAnimation(kg0);
    }

    public Drawable t(C2392eh0 eh0) {
        Uri uri = eh0.f;
        if (uri != null) {
            try {
                Drawable createFromStream = Drawable.createFromStream(this.R.Q.getContentResolver().openInputStream(uri), null);
                if (createFromStream != null) {
                    return createFromStream;
                }
            } catch (IOException e) {
                Log.w("MediaRouteCtrlDialog", "Failed to load " + uri, e);
            }
        }
        int i = eh0.m;
        if (i == 1) {
            return this.L;
        }
        if (i == 2) {
            return this.M;
        }
        if (eh0.f()) {
            return this.N;
        }
        return this.K;
    }

    public boolean u() {
        return this.R.L.c().size() > 1;
    }

    public void v() {
        this.R.P.clear();
        DialogC5460wg0 wg0 = this.R;
        List list = wg0.P;
        List list2 = wg0.N;
        ArrayList arrayList = new ArrayList();
        for (C2392eh0 eh0 : wg0.L.f9872a.b()) {
            C2222dh0 b = wg0.L.b(eh0);
            if (b != null && b.a()) {
                arrayList.add(eh0);
            }
        }
        HashSet hashSet = new HashSet(list2);
        hashSet.removeAll(arrayList);
        list.addAll(hashSet);
        this.F.b();
    }

    public void w() {
        this.I.clear();
        DialogC5460wg0 wg0 = this.R;
        this.O = new C4440qg0(this, wg0.L, 1);
        if (!wg0.M.isEmpty()) {
            for (C2392eh0 eh0 : this.R.M) {
                this.I.add(new C4440qg0(this, eh0, 3));
            }
        } else {
            this.I.add(new C4440qg0(this, this.R.L, 3));
        }
        boolean z = false;
        if (!this.R.N.isEmpty()) {
            boolean z2 = false;
            for (C2392eh0 eh02 : this.R.N) {
                if (!this.R.M.contains(eh02)) {
                    if (!z2) {
                        AbstractC0202Dg0 a2 = this.R.L.a();
                        String j = a2 != null ? a2.j() : null;
                        if (TextUtils.isEmpty(j)) {
                            j = this.R.Q.getString(R.string.f55360_resource_name_obfuscated_RES_2131952853);
                        }
                        this.I.add(new C4440qg0(this, j, 2));
                        z2 = true;
                    }
                    this.I.add(new C4440qg0(this, eh02, 3));
                }
            }
        }
        if (!this.R.O.isEmpty()) {
            for (C2392eh0 eh03 : this.R.O) {
                C2392eh0 eh04 = this.R.L;
                if (eh04 != eh03) {
                    if (!z) {
                        AbstractC0202Dg0 a3 = eh04.a();
                        String k = a3 != null ? a3.k() : null;
                        if (TextUtils.isEmpty(k)) {
                            k = this.R.Q.getString(R.string.f55370_resource_name_obfuscated_RES_2131952854);
                        }
                        this.I.add(new C4440qg0(this, k, 2));
                        z = true;
                    }
                    this.I.add(new C4440qg0(this, eh03, 4));
                }
            }
        }
        v();
    }
}
