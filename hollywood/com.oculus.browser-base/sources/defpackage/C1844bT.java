package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.fragment.app.FragmentState;
import java.util.Objects;

/* renamed from: bT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1844bT {

    /* renamed from: a  reason: collision with root package name */
    public final BS f9542a;
    public final AbstractComponentCallbacksC3550lS b;
    public int c = -1;

    public C1844bT(BS bs, AbstractComponentCallbacksC3550lS lSVar) {
        this.f9542a = bs;
        this.b = lSVar;
    }

    public void a(ClassLoader classLoader) {
        Bundle bundle = this.b.H;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            AbstractComponentCallbacksC3550lS lSVar = this.b;
            lSVar.I = lSVar.H.getSparseParcelableArray("android:view_state");
            AbstractComponentCallbacksC3550lS lSVar2 = this.b;
            lSVar2.M = lSVar2.H.getString("android:target_state");
            AbstractComponentCallbacksC3550lS lSVar3 = this.b;
            if (lSVar3.M != null) {
                lSVar3.N = lSVar3.H.getInt("android:target_req_state", 0);
            }
            AbstractComponentCallbacksC3550lS lSVar4 = this.b;
            Objects.requireNonNull(lSVar4);
            lSVar4.m0 = lSVar4.H.getBoolean("android:user_visible_hint", true);
            AbstractComponentCallbacksC3550lS lSVar5 = this.b;
            if (!lSVar5.m0) {
                lSVar5.l0 = true;
            }
        }
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        AbstractComponentCallbacksC3550lS lSVar = this.b;
        lSVar.C0(bundle);
        lSVar.x0.b(bundle);
        Parcelable k0 = lSVar.Y.k0();
        if (k0 != null) {
            bundle.putParcelable("android:support:fragments", k0);
        }
        this.f9542a.j(this.b, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.b.k0 != null) {
            c();
        }
        if (this.b.I != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.b.I);
        }
        if (!this.b.m0) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.b.m0);
        }
        return bundle;
    }

    public void c() {
        if (this.b.k0 != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.b.k0.saveHierarchyState(sparseArray);
            if (sparseArray.size() > 0) {
                this.b.I = sparseArray;
            }
        }
    }

    public C1844bT(BS bs, ClassLoader classLoader, AbstractC5765yS ySVar, FragmentState fragmentState) {
        this.f9542a = bs;
        AbstractComponentCallbacksC3550lS a2 = ySVar.a(classLoader, fragmentState.F);
        this.b = a2;
        Bundle bundle = fragmentState.O;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        a2.U0(fragmentState.O);
        a2.f10345J = fragmentState.G;
        a2.R = fragmentState.H;
        a2.T = true;
        a2.a0 = fragmentState.I;
        a2.b0 = fragmentState.f9472J;
        a2.c0 = fragmentState.K;
        a2.f0 = fragmentState.L;
        a2.Q = fragmentState.M;
        a2.e0 = fragmentState.N;
        a2.d0 = fragmentState.P;
        a2.t0 = EnumC3328k80.values()[fragmentState.Q];
        Bundle bundle2 = fragmentState.R;
        if (bundle2 != null) {
            a2.H = bundle2;
        } else {
            a2.H = new Bundle();
        }
        if (KS.R(2)) {
            AbstractC2531fV.p("Instantiated fragment ", a2);
        }
    }

    public C1844bT(BS bs, AbstractComponentCallbacksC3550lS lSVar, FragmentState fragmentState) {
        this.f9542a = bs;
        this.b = lSVar;
        lSVar.I = null;
        lSVar.V = 0;
        lSVar.S = false;
        lSVar.P = false;
        AbstractComponentCallbacksC3550lS lSVar2 = lSVar.L;
        lSVar.M = lSVar2 != null ? lSVar2.f10345J : null;
        lSVar.L = null;
        Bundle bundle = fragmentState.R;
        if (bundle != null) {
            lSVar.H = bundle;
        } else {
            lSVar.H = new Bundle();
        }
    }
}
