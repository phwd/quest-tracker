package defpackage;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.UUID;

/* renamed from: lS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractComponentCallbacksC3550lS implements ComponentCallbacks, View.OnCreateContextMenuListener, AbstractC4524r80, AbstractC4823su1, JO0 {
    public static final Object F = new Object();
    public int G = -1;
    public Bundle H;
    public SparseArray I;

    /* renamed from: J  reason: collision with root package name */
    public String f10345J = UUID.randomUUID().toString();
    public Bundle K;
    public AbstractComponentCallbacksC3550lS L;
    public String M = null;
    public int N;
    public Boolean O = null;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public boolean U;
    public int V;
    public KS W;
    public C3721mS X;
    public KS Y = new KS();
    public AbstractComponentCallbacksC3550lS Z;
    public int a0;
    public int b0;
    public String c0;
    public boolean d0;
    public boolean e0;
    public boolean f0;
    public boolean g0;
    public boolean h0 = true;
    public boolean i0;
    public ViewGroup j0;
    public View k0;
    public boolean l0;
    public boolean m0 = true;
    public C3038iS n0;
    public boolean o0;
    public boolean p0;
    public float q0;
    public LayoutInflater r0;
    public boolean s0;
    public EnumC3328k80 t0 = EnumC3328k80.RESUMED;
    public C4865t80 u0;
    public ET v0;
    public C3603lm0 w0 = new C3603lm0();
    public IO0 x0;

    public AbstractComponentCallbacksC3550lS() {
        S();
    }

    @Deprecated
    public static AbstractComponentCallbacksC3550lS T(Context context, String str, Bundle bundle) {
        try {
            AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) AbstractC5765yS.c(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(lSVar.getClass().getClassLoader());
                lSVar.U0(bundle);
            }
            return lSVar;
        } catch (InstantiationException e) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }

    public void A0() {
    }

    public Object B() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return null;
        }
        Objects.requireNonNull(iSVar);
        return null;
    }

    public void B0() {
        this.i0 = true;
    }

    public void C() {
        C3038iS iSVar = this.n0;
        if (iSVar != null) {
            Objects.requireNonNull(iSVar);
        }
    }

    public void C0(Bundle bundle) {
    }

    public void D0() {
        this.i0 = true;
    }

    @Deprecated
    public LayoutInflater E() {
        C3721mS mSVar = this.X;
        if (mSVar != null) {
            LayoutInflater cloneInContext = mSVar.f10420J.getLayoutInflater().cloneInContext(mSVar.f10420J);
            cloneInContext.setFactory2(this.Y.f);
            return cloneInContext;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    public void E0() {
        this.i0 = true;
    }

    public int F() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return 0;
        }
        return iSVar.d;
    }

    public void F0(View view, Bundle bundle) {
    }

    public final KS G() {
        KS ks = this.W;
        if (ks != null) {
            return ks;
        }
        throw new IllegalStateException(AbstractC2531fV.d("Fragment ", this, " not associated with a fragment manager."));
    }

    public void G0() {
        this.i0 = true;
    }

    public Object H() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return null;
        }
        Object obj = iSVar.g;
        if (obj != F) {
            return obj;
        }
        B();
        return null;
    }

    public boolean H0(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.d0) {
            return false;
        }
        if (this.g0 && this.h0) {
            z = true;
            k0(menu, menuInflater);
        }
        return z | this.Y.o(menu, menuInflater);
    }

    public final Resources I() {
        return P0().getResources();
    }

    public void I0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.Y.b0();
        boolean z = true;
        this.U = true;
        this.v0 = new ET();
        View l02 = l0(layoutInflater, viewGroup, bundle);
        this.k0 = l02;
        if (l02 != null) {
            ET et = this.v0;
            if (et.F == null) {
                et.F = new C4865t80(et);
            }
            this.w0.a(this.v0);
            return;
        }
        if (this.v0.F == null) {
            z = false;
        }
        if (!z) {
            this.v0 = null;
            return;
        }
        throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
    }

    public Object J() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return null;
        }
        Object obj = iSVar.f;
        if (obj != F) {
            return obj;
        }
        y();
        return null;
    }

    public LayoutInflater J0(Bundle bundle) {
        LayoutInflater p02 = p0(bundle);
        this.r0 = p02;
        return p02;
    }

    public Object K() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return null;
        }
        Objects.requireNonNull(iSVar);
        return null;
    }

    public void K0() {
        onLowMemory();
        this.Y.q();
    }

    @Override // defpackage.AbstractC4823su1
    public C4653ru1 L() {
        KS ks = this.W;
        if (ks != null) {
            NS ns = ks.C;
            C4653ru1 ru1 = (C4653ru1) ns.e.get(this.f10345J);
            if (ru1 != null) {
                return ru1;
            }
            C4653ru1 ru12 = new C4653ru1();
            ns.e.put(this.f10345J, ru12);
            return ru12;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public void L0(boolean z) {
        t0();
        this.Y.r(z);
    }

    public Object M() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return null;
        }
        Object obj = iSVar.h;
        if (obj != F) {
            return obj;
        }
        K();
        return null;
    }

    public void M0(boolean z) {
        x0();
        this.Y.v(z);
    }

    public int N() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return 0;
        }
        return iSVar.c;
    }

    public boolean N0(Menu menu) {
        boolean z = false;
        if (this.d0) {
            return false;
        }
        if (this.g0 && this.h0) {
            z = true;
            y0(menu);
        }
        return z | this.Y.w(menu);
    }

    public final String O(int i) {
        return I().getString(i);
    }

    public Activity O0() {
        Activity u = u();
        if (u != null) {
            return u;
        }
        throw new IllegalStateException(AbstractC2531fV.d("Fragment ", this, " not attached to an activity."));
    }

    public final String P(int i, Object... objArr) {
        return I().getString(i, objArr);
    }

    public final Context P0() {
        Context x = x();
        if (x != null) {
            return x;
        }
        throw new IllegalStateException(AbstractC2531fV.d("Fragment ", this, " not attached to a context."));
    }

    @Override // defpackage.AbstractC4524r80
    public AbstractC3499l80 Q() {
        return this.u0;
    }

    public final View Q0() {
        View view = this.k0;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException(AbstractC2531fV.d("Fragment ", this, " did not return a View from onCreateView() or this was called before onCreateView()."));
    }

    public final AbstractComponentCallbacksC3550lS R() {
        String str;
        AbstractComponentCallbacksC3550lS lSVar = this.L;
        if (lSVar != null) {
            return lSVar;
        }
        KS ks = this.W;
        if (ks == null || (str = this.M) == null) {
            return null;
        }
        return ks.H(str);
    }

    public void R0(Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            this.Y.j0(parcelable);
            this.Y.n();
        }
    }

    public final void S() {
        this.u0 = new C4865t80(this);
        this.x0 = new IO0(this);
        this.u0.a(new C2696gS(this));
    }

    public void S0(View view) {
        t().f10139a = view;
    }

    public void T0(Animator animator) {
        t().b = animator;
    }

    public final boolean U() {
        return this.X != null && this.P;
    }

    public void U0(Bundle bundle) {
        KS ks = this.W;
        if (ks == null || !ks.U()) {
            this.K = bundle;
            return;
        }
        throw new IllegalStateException("Fragment already added and state has been saved");
    }

    public boolean V() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return false;
        }
        return iSVar.j;
    }

    public void V0(boolean z) {
        if (this.g0 != z) {
            this.g0 = z;
            if (U() && !this.d0) {
                this.X.e();
            }
        }
    }

    public final boolean W() {
        return this.V > 0;
    }

    public void W0(boolean z) {
        t().j = z;
    }

    public final boolean X() {
        boolean z;
        if (this.h0) {
            if (this.W == null) {
                return true;
            }
            AbstractComponentCallbacksC3550lS lSVar = this.Z;
            if (lSVar == null) {
                z = true;
            } else {
                z = lSVar.X();
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public void X0(boolean z) {
        if (this.h0 != z) {
            this.h0 = z;
            if (this.g0 && U() && !this.d0) {
                this.X.e();
            }
        }
    }

    public final boolean Y() {
        AbstractComponentCallbacksC3550lS lSVar = this.Z;
        return lSVar != null && (lSVar.Q || lSVar.Y());
    }

    public void Y0(int i) {
        if (this.n0 != null || i != 0) {
            t().d = i;
        }
    }

    public final boolean Z() {
        return this.G >= 4;
    }

    public void Z0(JS js) {
        t();
        JS js2 = this.n0.i;
        if (js != js2) {
            if (js != null && js2 != null) {
                throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
            } else if (js != null) {
                js.c++;
            }
        }
    }

    public final boolean a0() {
        View view;
        return U() && !this.d0 && (view = this.k0) != null && view.getWindowToken() != null && this.k0.getVisibility() == 0;
    }

    public void a1(int i) {
        t().c = i;
    }

    public void b0(Bundle bundle) {
        this.i0 = true;
    }

    public void b1(AbstractComponentCallbacksC3550lS lSVar, int i) {
        KS ks = this.W;
        KS ks2 = lSVar.W;
        if (ks == null || ks2 == null || ks == ks2) {
            for (AbstractComponentCallbacksC3550lS lSVar2 = lSVar; lSVar2 != null; lSVar2 = lSVar2.R()) {
                if (lSVar2 == this) {
                    throw new IllegalArgumentException("Setting " + lSVar + " as the target of " + this + " would create a target cycle");
                }
            }
            if (this.W == null || lSVar.W == null) {
                this.M = null;
                this.L = lSVar;
            } else {
                this.M = lSVar.f10345J;
                this.L = null;
            }
            this.N = i;
            return;
        }
        throw new IllegalArgumentException(AbstractC2531fV.d("Fragment ", lSVar, " must share the same FragmentManager to be set as a target fragment"));
    }

    public void c0(int i, int i2, Intent intent) {
    }

    public void c1(Intent intent) {
        C3721mS mSVar = this.X;
        if (mSVar != null) {
            mSVar.d(this, intent, -1, null);
            return;
        }
        throw new IllegalStateException(AbstractC2531fV.d("Fragment ", this, " not attached to Activity"));
    }

    @Deprecated
    public void d0() {
        this.i0 = true;
    }

    public void d1(Intent intent, int i) {
        C3721mS mSVar = this.X;
        if (mSVar != null) {
            mSVar.d(this, intent, i, null);
            return;
        }
        throw new IllegalStateException(AbstractC2531fV.d("Fragment ", this, " not attached to Activity"));
    }

    public void e0(Context context) {
        Activity activity;
        this.i0 = true;
        C3721mS mSVar = this.X;
        if (mSVar == null) {
            activity = null;
        } else {
            activity = mSVar.F;
        }
        if (activity != null) {
            this.i0 = false;
            d0();
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void f0() {
    }

    @Override // defpackage.JO0
    public final HO0 g() {
        return this.x0.b;
    }

    public boolean g0() {
        return false;
    }

    public void h0(Bundle bundle) {
        Parcelable parcelable;
        boolean z = true;
        this.i0 = true;
        if (!(bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null)) {
            this.Y.j0(parcelable);
            this.Y.n();
        }
        KS ks = this.Y;
        if (ks.m < 1) {
            z = false;
        }
        if (!z) {
            ks.n();
        }
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public Animation i0() {
        return null;
    }

    public Animator j0() {
        return null;
    }

    public void k0(Menu menu, MenuInflater menuInflater) {
    }

    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void m0() {
        this.i0 = true;
    }

    public void n0() {
        this.i0 = true;
    }

    public void o0() {
        this.i0 = true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.i0 = true;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        O0().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onLowMemory() {
        this.i0 = true;
    }

    public LayoutInflater p0(Bundle bundle) {
        return E();
    }

    public void q0() {
    }

    @Deprecated
    public void r0() {
        this.i0 = true;
    }

    public void s(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.a0));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.b0));
        printWriter.print(" mTag=");
        printWriter.println(this.c0);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.G);
        printWriter.print(" mWho=");
        printWriter.print(this.f10345J);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.V);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.P);
        printWriter.print(" mRemoving=");
        printWriter.print(this.Q);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.R);
        printWriter.print(" mInLayout=");
        printWriter.println(this.S);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.d0);
        printWriter.print(" mDetached=");
        printWriter.print(this.e0);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.h0);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.g0);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.f0);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.m0);
        if (this.W != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.W);
        }
        if (this.X != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.X);
        }
        if (this.Z != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.Z);
        }
        if (this.K != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.K);
        }
        if (this.H != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.H);
        }
        if (this.I != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.I);
        }
        AbstractComponentCallbacksC3550lS R2 = R();
        if (R2 != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(R2);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.N);
        }
        if (F() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(F());
        }
        if (this.j0 != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.j0);
        }
        if (this.k0 != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.k0);
        }
        if (v() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(v());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(N());
        }
        if (x() != null) {
            J90.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.Y + ":");
        this.Y.z(AbstractC2531fV.f(str, "  "), fileDescriptor, printWriter, strArr);
    }

    public void s0(AttributeSet attributeSet, Bundle bundle) {
        Activity activity;
        this.i0 = true;
        C3721mS mSVar = this.X;
        if (mSVar == null) {
            activity = null;
        } else {
            activity = mSVar.F;
        }
        if (activity != null) {
            this.i0 = false;
            r0();
        }
    }

    public final C3038iS t() {
        if (this.n0 == null) {
            this.n0 = new C3038iS();
        }
        return this.n0;
    }

    public void t0() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.f10345J);
        sb.append(")");
        if (this.a0 != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.a0));
        }
        if (this.c0 != null) {
            sb.append(" ");
            sb.append(this.c0);
        }
        sb.append('}');
        return sb.toString();
    }

    public Activity u() {
        for (Context x = x(); x instanceof ContextWrapper; x = ((ContextWrapper) x).getBaseContext()) {
            if (x instanceof Activity) {
                return (Activity) x;
            }
        }
        return null;
    }

    public boolean u0(MenuItem menuItem) {
        return false;
    }

    public View v() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return null;
        }
        return iSVar.f10139a;
    }

    public void v0() {
    }

    public final KS w() {
        if (this.X != null) {
            return this.Y;
        }
        throw new IllegalStateException(AbstractC2531fV.d("Fragment ", this, " has not been attached yet."));
    }

    public void w0() {
        this.i0 = true;
    }

    public Context x() {
        C3721mS mSVar = this.X;
        if (mSVar == null) {
            return null;
        }
        return mSVar.G;
    }

    public void x0() {
    }

    public Object y() {
        C3038iS iSVar = this.n0;
        if (iSVar == null) {
            return null;
        }
        Objects.requireNonNull(iSVar);
        return null;
    }

    public void y0(Menu menu) {
    }

    public void z() {
        C3038iS iSVar = this.n0;
        if (iSVar != null) {
            Objects.requireNonNull(iSVar);
        }
    }

    public void z0() {
    }
}
