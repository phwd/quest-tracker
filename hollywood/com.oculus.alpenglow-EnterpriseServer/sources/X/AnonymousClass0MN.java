package X;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment$2;
import androidx.fragment.app.FragmentActivity;
import java.util.HashMap;
import java.util.UUID;

/* renamed from: X.0MN  reason: invalid class name */
public final class AnonymousClass0MN implements AbstractC01030Da, AbstractC01160Dt, AbstractC03380cC, ComponentCallbacks, View.OnCreateContextMenuListener {
    public static final Object A0f = new Object();
    public static final String __redex_internal_original_name = "androidx.fragment.app.Fragment";
    public C03530cb<AbstractC01030Da> A00 = new C03530cb<>();
    public Runnable A01 = new AnonymousClass0CK(this);
    public int A02;
    public int A03;
    public int A04;
    public int A05 = -1;
    public int A06;
    public Bundle A07;
    public Bundle A08;
    public SparseArray<Parcelable> A09;
    public LayoutInflater A0A;
    public ViewGroup A0B;
    public AnonymousClass0CM A0C;
    public AnonymousClass0MN A0D;
    public AnonymousClass0MN A0E;
    public AbstractC03650cs<?> A0F;
    @NonNull
    public AnonymousClass0Cj A0G = new C03610cn();
    public AnonymousClass0Cj A0H;
    @Nullable
    public C03570cj A0I;
    public AnonymousClass0DX A0J = AnonymousClass0DX.RESUMED;
    public C03540cc A0K = new C03540cc(this);
    public AnonymousClass0GK A0L = new AnonymousClass0GK(this);
    public Boolean A0M = null;
    public String A0N;
    public String A0O = null;
    @NonNull
    public String A0P = UUID.randomUUID().toString();
    public boolean A0Q;
    public boolean A0R;
    public boolean A0S;
    public boolean A0T;
    public boolean A0U;
    public boolean A0V;
    public boolean A0W;
    public boolean A0X;
    public boolean A0Y;
    public boolean A0Z = true;
    public boolean A0a;
    public boolean A0b;
    public boolean A0c;
    public boolean A0d;
    public boolean A0e = true;

    @CallSuper
    @UiThread
    public final void A02() {
        this.A0R = true;
        AbstractC03650cs<?> r0 = this.A0F;
        if (r0 != null && r0.A00 != null) {
            this.A0R = false;
            this.A0R = true;
        }
    }

    @CallSuper
    public final void onConfigurationChanged(@NonNull Configuration configuration) {
        this.A0R = true;
    }

    @CallSuper
    @MainThread
    public final void onLowMemory() {
        this.A0R = true;
    }

    public static AnonymousClass0CM A00(AnonymousClass0MN r1) {
        AnonymousClass0CM r0 = r1.A0C;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass0CM r02 = new AnonymousClass0CM();
        r1.A0C = r02;
        return r02;
    }

    @NonNull
    public final AnonymousClass0Cj A01() {
        AnonymousClass0Cj r0 = this.A0H;
        if (r0 != null) {
            return r0;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final void A03() {
        this.A0G.A0L();
        this.A0a = true;
        C03570cj r0 = new C03570cj();
        this.A0I = r0;
        if (r0.A00 != null) {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
        this.A0I = null;
    }

    @NonNull
    public final void A04() {
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public final void A05(int i) {
        if (this.A0C != null || i != 0) {
            A00(this).A00 = i;
        }
    }

    public final void A06(@Nullable Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable(FragmentActivity.FRAGMENTS_TAG)) != null) {
            this.A0G.A0O(parcelable);
            AnonymousClass0Cj r1 = this.A0G;
            r1.A0G = false;
            r1.A0H = false;
            AnonymousClass0Cj.A05(r1, 1);
        }
    }

    @Override // X.AbstractC01030Da
    @NonNull
    public final AnonymousClass0DY getLifecycle() {
        return this.A0K;
    }

    @Override // X.AbstractC03380cC
    @NonNull
    public final AnonymousClass0GJ getSavedStateRegistry() {
        return this.A0L.A00;
    }

    @Override // X.AbstractC01160Dt
    @NonNull
    public final C01150Ds getViewModelStore() {
        AnonymousClass0Cj r0 = this.A0H;
        if (r0 != null) {
            HashMap<String, C01150Ds> hashMap = r0.A06.A03;
            C01150Ds r1 = hashMap.get(this.A0P);
            if (r1 != null) {
                return r1;
            }
            C01150Ds r12 = new C01150Ds();
            hashMap.put(this.A0P, r12);
            return r12;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @MainThread
    public final void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        FragmentActivity fragmentActivity;
        AbstractC03650cs<?> r0 = this.A0F;
        if (r0 == null || (fragmentActivity = (FragmentActivity) r0.A00) == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
        }
        fragmentActivity.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @NonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.A0P);
        sb.append(")");
        int i = this.A04;
        if (i != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(i));
        }
        String str = this.A0N;
        if (str != null) {
            sb.append(" ");
            sb.append(str);
        }
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass0MN() {
        this.A0K.A06(new Fragment$2(this));
    }
}
