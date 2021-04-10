package androidx.viewpager2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.fragment.app.Fragment$SavedState;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ViewPager2 extends ViewGroup {
    public static final C3985nz1 F;
    public final Rect G = new Rect();
    public final Rect H = new Rect();
    public C3803mw I = new C3803mw(3);

    /* renamed from: J  reason: collision with root package name */
    public int f9488J;
    public boolean K = false;
    public AK0 L = new Hu1(this);
    public LinearLayoutManager M;
    public int N = -1;
    public Parcelable O;
    public RecyclerView P;
    public Tu1 Q;
    public QP0 R;
    public C3803mw S;
    public C1654aO T;
    public C0662Kv0 U;
    public boolean V = true;
    public int W = -1;
    public Lu1 a0 = new Su1(this);

    static {
        AbstractC2789gz1 gz1;
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            gz1 = new C2618fz1();
        } else if (i >= 29) {
            gz1 = new C2447ez1();
        } else {
            gz1 = new C2276dz1();
        }
        F = gz1.a();
    }

    /* JADX INFO: finally extract failed */
    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Uu1 uu1 = new Uu1(this, context);
        this.P = uu1;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        uu1.setId(View.generateViewId());
        this.P.setDescendantFocusability(131072);
        Nu1 nu1 = new Nu1(this, context);
        this.M = nu1;
        this.P.t0(nu1);
        RecyclerView recyclerView = this.P;
        recyclerView.G0 = ViewConfiguration.get(recyclerView.getContext()).getScaledPagingTouchSlop();
        int[] iArr = FJ0.S0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        AbstractC1920bu1.m(this, context, iArr, attributeSet, obtainStyledAttributes, 0, 0);
        try {
            this.M.D1(obtainStyledAttributes.getInt(0, 0));
            ((Su1) this.a0).e();
            obtainStyledAttributes.recycle();
            this.P.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.P.h(new Ku1(this));
            QP0 qp0 = new QP0(this);
            this.R = qp0;
            this.T = new C1654aO(this, qp0, this.P);
            Tu1 tu1 = new Tu1(this);
            this.Q = tu1;
            tu1.a(this.P);
            this.P.i(this.R);
            C3803mw mwVar = new C3803mw(3);
            this.S = mwVar;
            this.R.f8759a = mwVar;
            Iu1 iu1 = new Iu1(this);
            Ju1 ju1 = new Ju1(this);
            mwVar.f10461a.add(iu1);
            this.S.f10461a.add(ju1);
            this.a0.a(this.S, this.P);
            C3803mw mwVar2 = this.S;
            mwVar2.f10461a.add(this.I);
            C0662Kv0 kv0 = new C0662Kv0(this.M);
            this.U = kv0;
            this.S.f10461a.add(kv0);
            RecyclerView recyclerView2 = this.P;
            attachViewToParent(recyclerView2, 0, recyclerView2.getLayoutParams());
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public boolean a() {
        return this.M.K() == 1;
    }

    public final void b() {
        AbstractC5750yK0 yk0;
        AbstractComponentCallbacksC3550lS lSVar;
        if (!(this.N == -1 || (yk0 = this.P.T) == null)) {
            Parcelable parcelable = this.O;
            if (parcelable != null) {
                if (yk0 instanceof AbstractC1664aT) {
                    AbstractC1664aT aTVar = (AbstractC1664aT) yk0;
                    if (!aTVar.L.g() || !aTVar.K.g()) {
                        throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
                    }
                    Bundle bundle = (Bundle) parcelable;
                    if (bundle.getClassLoader() == null) {
                        bundle.setClassLoader(aTVar.getClass().getClassLoader());
                    }
                    for (String str : bundle.keySet()) {
                        if (AbstractC1664aT.v(str, "f#")) {
                            long parseLong = Long.parseLong(str.substring(2));
                            KS ks = aTVar.f9431J;
                            Objects.requireNonNull(ks);
                            String string = bundle.getString(str);
                            if (string == null) {
                                lSVar = null;
                            } else {
                                lSVar = ks.c.e(string);
                                if (lSVar == null) {
                                    ks.s0(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
                                }
                            }
                            aTVar.K.i(parseLong, lSVar);
                        } else if (AbstractC1664aT.v(str, "s#")) {
                            long parseLong2 = Long.parseLong(str.substring(2));
                            Fragment$SavedState fragment$SavedState = (Fragment$SavedState) bundle.getParcelable(str);
                            if (aTVar.t(parseLong2)) {
                                aTVar.L.i(parseLong2, fragment$SavedState);
                            }
                        } else {
                            throw new IllegalArgumentException(AbstractC2531fV.f("Unexpected key in savedState: ", str));
                        }
                    }
                    if (!aTVar.K.g()) {
                        aTVar.Q = true;
                        aTVar.P = true;
                        aTVar.u();
                        Handler handler = new Handler(Looper.getMainLooper());
                        SS ss = new SS(aTVar);
                        aTVar.I.a(new TS(aTVar, handler, ss));
                        handler.postDelayed(ss, 10000);
                    }
                }
                this.O = null;
            }
            int max = Math.max(0, Math.min(this.N, yk0.b() - 1));
            this.f9488J = max;
            this.N = -1;
            this.P.p0(max);
            this.a0.b();
        }
    }

    public void c(int i, boolean z) {
        Ou1 ou1;
        AbstractC5750yK0 yk0 = this.P.T;
        boolean z2 = false;
        if (yk0 == null) {
            if (this.N != -1) {
                this.N = Math.max(i, 0);
            }
        } else if (yk0.b() > 0) {
            int min = Math.min(Math.max(i, 0), yk0.b() - 1);
            int i2 = this.f9488J;
            if (min == i2) {
                if (this.R.f == 0) {
                    return;
                }
            }
            if (min != i2 || !z) {
                double d = (double) i2;
                this.f9488J = min;
                ((Su1) this.a0).e();
                QP0 qp0 = this.R;
                if (!(qp0.f == 0)) {
                    qp0.f();
                    PP0 pp0 = qp0.g;
                    d = ((double) pp0.f8689a) + ((double) pp0.b);
                }
                QP0 qp02 = this.R;
                qp02.e = z ? 2 : 3;
                qp02.m = false;
                if (qp02.i != min) {
                    z2 = true;
                }
                qp02.i = min;
                qp02.d(2);
                if (z2 && (ou1 = qp02.f8759a) != null) {
                    ou1.c(min);
                }
                if (!z) {
                    this.P.p0(min);
                    return;
                }
                double d2 = (double) min;
                if (Math.abs(d2 - d) > 3.0d) {
                    this.P.p0(d2 > d ? min - 3 : min + 3);
                    RecyclerView recyclerView = this.P;
                    recyclerView.post(new Wu1(min, recyclerView));
                    return;
                }
                this.P.w0(min);
            }
        }
    }

    public boolean canScrollHorizontally(int i) {
        return this.P.canScrollHorizontally(i);
    }

    public boolean canScrollVertically(int i) {
        return this.P.canScrollVertically(i);
    }

    public void d() {
        Tu1 tu1 = this.Q;
        if (tu1 != null) {
            View d = tu1.d(this.M);
            if (d != null) {
                int R2 = this.M.R(d);
                if (R2 != this.f9488J && this.R.f == 0) {
                    this.S.c(R2);
                }
                this.K = false;
                return;
            }
            return;
        }
        throw new IllegalStateException("Design assumption violated.");
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i = ((SavedState) parcelable).F;
            sparseArray.put(this.P.getId(), sparseArray.get(i));
            sparseArray.remove(i);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        b();
    }

    public CharSequence getAccessibilityClassName() {
        Lu1 lu1 = this.a0;
        Objects.requireNonNull(lu1);
        if (!(lu1 instanceof Su1)) {
            return super.getAccessibilityClassName();
        }
        Objects.requireNonNull(this.a0);
        return "androidx.viewpager.widget.ViewPager";
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        if (onApplyWindowInsets.isConsumed()) {
            return onApplyWindowInsets;
        }
        int childCount = this.P.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.P.getChildAt(i).dispatchApplyWindowInsets(new WindowInsets(onApplyWindowInsets));
        }
        C3985nz1 nz1 = F;
        if (nz1.g() != null) {
            return nz1.g();
        }
        return windowInsets.consumeSystemWindowInsets().consumeStableInsets();
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        int i2;
        int b;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        Su1 su1 = (Su1) this.a0;
        ViewPager2 viewPager2 = su1.d;
        AbstractC5750yK0 yk0 = viewPager2.P.T;
        if (yk0 == null) {
            i2 = 0;
            i = 0;
        } else if (viewPager2.M.r == 1) {
            i2 = yk0.b();
            i = 1;
        } else {
            i = yk0.b();
            i2 = 1;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) B.a(i2, i, false, 0).f7712a);
        AbstractC5750yK0 yk02 = su1.d.P.T;
        if (yk02 != null && (b = yk02.b()) != 0) {
            ViewPager2 viewPager22 = su1.d;
            if (viewPager22.V) {
                if (viewPager22.f9488J > 0) {
                    accessibilityNodeInfo.addAction(8192);
                }
                if (su1.d.f9488J < b - 1) {
                    accessibilityNodeInfo.addAction(4096);
                }
                accessibilityNodeInfo.setScrollable(true);
            }
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = this.P.getMeasuredWidth();
        int measuredHeight = this.P.getMeasuredHeight();
        this.G.left = getPaddingLeft();
        this.G.right = (i3 - i) - getPaddingRight();
        this.G.top = getPaddingTop();
        this.G.bottom = (i4 - i2) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.G, this.H);
        RecyclerView recyclerView = this.P;
        Rect rect = this.H;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.K) {
            d();
        }
    }

    public void onMeasure(int i, int i2) {
        measureChild(this.P, i, i2);
        int measuredWidth = this.P.getMeasuredWidth();
        int measuredHeight = this.P.getMeasuredHeight();
        int measuredState = this.P.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingTop = getPaddingTop();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i, measuredState), ViewGroup.resolveSizeAndState(Math.max(getPaddingBottom() + paddingTop + measuredHeight, getSuggestedMinimumHeight()), i2, measuredState << 16));
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.N = savedState.G;
        this.O = savedState.H;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.F = this.P.getId();
        int i = this.N;
        if (i == -1) {
            i = this.f9488J;
        }
        savedState.G = i;
        Parcelable parcelable = this.O;
        if (parcelable != null) {
            savedState.H = parcelable;
        } else {
            AbstractC5750yK0 yk0 = this.P.T;
            if (yk0 instanceof AbstractC1664aT) {
                AbstractC1664aT aTVar = (AbstractC1664aT) yk0;
                Objects.requireNonNull(aTVar);
                Bundle bundle = new Bundle(aTVar.L.k() + aTVar.K.k());
                for (int i2 = 0; i2 < aTVar.K.k(); i2++) {
                    long h = aTVar.K.h(i2);
                    AbstractComponentCallbacksC3550lS lSVar = (AbstractComponentCallbacksC3550lS) aTVar.K.e(h);
                    if (lSVar != null && lSVar.U()) {
                        String str = "f#" + h;
                        KS ks = aTVar.f9431J;
                        Objects.requireNonNull(ks);
                        if (lSVar.W == ks) {
                            bundle.putString(str, lSVar.f10345J);
                        } else {
                            ks.s0(new IllegalStateException(AbstractC2531fV.d("Fragment ", lSVar, " is not currently in the FragmentManager")));
                            throw null;
                        }
                    }
                }
                for (int i3 = 0; i3 < aTVar.L.k(); i3++) {
                    long h2 = aTVar.L.h(i3);
                    if (aTVar.t(h2)) {
                        bundle.putParcelable("s#" + h2, (Parcelable) aTVar.L.e(h2));
                    }
                }
                savedState.H = bundle;
            }
        }
        return savedState;
    }

    public void onViewAdded(View view) {
        throw new IllegalStateException(ViewPager2.class.getSimpleName() + " does not support direct child views");
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        int i2;
        Objects.requireNonNull((Su1) this.a0);
        boolean z = false;
        if (!(i == 8192 || i == 4096)) {
            return super.performAccessibilityAction(i, bundle);
        }
        Su1 su1 = (Su1) this.a0;
        Objects.requireNonNull(su1);
        if (i == 8192 || i == 4096) {
            z = true;
        }
        if (z) {
            if (i == 8192) {
                i2 = su1.d.f9488J - 1;
            } else {
                i2 = su1.d.f9488J + 1;
            }
            su1.d(i2);
            return true;
        }
        throw new IllegalStateException();
    }

    public void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        ((Su1) this.a0).e();
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new Vu1();
        public int F;
        public int G;
        public Parcelable H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.F = parcel.readInt();
            this.G = parcel.readInt();
            this.H = parcel.readParcelable(classLoader);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.F);
            parcel.writeInt(this.G);
            parcel.writeParcelable(this.H, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
