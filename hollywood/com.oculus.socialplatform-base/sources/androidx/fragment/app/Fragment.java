package androidx.fragment.app;

import X.AbstractC00480Al;
import X.AbstractC05180ub;
import X.AbstractC05230uw;
import X.AbstractC05340vL;
import X.AnonymousClass09C;
import X.AnonymousClass09E;
import X.AnonymousClass09b;
import X.AnonymousClass0AO;
import X.AnonymousClass0AP;
import X.AnonymousClass0AQ;
import X.AnonymousClass0AS;
import X.AnonymousClass0C4;
import X.AnonymousClass0C5;
import X.AnonymousClass0uu;
import X.AnonymousClass0uv;
import X.AnonymousClass0v5;
import X.C00470Ak;
import X.C05300vD;
import android.content.ComponentCallbacks;
import android.content.Context;
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
import java.util.HashMap;
import java.util.UUID;
import org.apache.commons.cli.HelpFormatter;

public class Fragment implements AnonymousClass0AS, AbstractC00480Al, AbstractC05180ub, ComponentCallbacks, View.OnCreateContextMenuListener {
    public static final Object A0f = new Object();
    public AnonymousClass0uu<AnonymousClass0AS> A00 = new AnonymousClass0uu<>();
    public Runnable A01 = new AnonymousClass09C(this);
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
    public AnonymousClass09E A0C;
    public Fragment A0D;
    public Fragment A0E;
    public AbstractC05340vL<?> A0F;
    @NonNull
    public AnonymousClass09b A0G = new C05300vD();
    public AnonymousClass09b A0H;
    @Nullable
    public AnonymousClass0v5 A0I;
    public AnonymousClass0AP A0J = AnonymousClass0AP.RESUMED;
    public AnonymousClass0uv A0K = new AnonymousClass0uv(this);
    public AnonymousClass0C5 A0L = new AnonymousClass0C5(this);
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
    public final void A05() {
        this.A0R = true;
        AbstractC05340vL<?> r0 = this.A0F;
        if (r0 != null && r0.A00 != null) {
            this.A0R = false;
            this.A0R = true;
        }
    }

    @CallSuper
    @MainThread
    public void A0A() {
        this.A0R = true;
    }

    @CallSuper
    @MainThread
    public void A0B() {
        this.A0R = true;
    }

    @CallSuper
    @MainThread
    public void A0C() {
        this.A0R = true;
    }

    @CallSuper
    @MainThread
    public void A0D() {
        this.A0R = true;
    }

    @CallSuper
    @MainThread
    public void A0E(@NonNull Context context) {
        this.A0R = true;
        AbstractC05340vL<?> r0 = this.A0F;
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

    public static AnonymousClass09E A00(Fragment fragment) {
        AnonymousClass09E r0 = fragment.A0C;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass09E r02 = new AnonymousClass09E();
        fragment.A0C = r02;
        return r02;
    }

    @Nullable
    public final Context A02() {
        AbstractC05340vL<?> r0 = this.A0F;
        if (r0 == null) {
            return null;
        }
        return r0.A01;
    }

    @NonNull
    public final AnonymousClass09b A03() {
        if (this.A0F != null) {
            return this.A0G;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" has not been attached yet.");
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    public final AnonymousClass09b A04() {
        AnonymousClass09b r0 = this.A0H;
        if (r0 != null) {
            return r0;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not associated with a fragment manager.");
        throw new IllegalStateException(sb.toString());
    }

    public final void A06() {
        this.A0G.A0M();
        this.A0a = true;
        AnonymousClass0v5 r0 = new AnonymousClass0v5();
        this.A0I = r0;
        if (r0.A00 != null) {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
        this.A0I = null;
    }

    @NonNull
    public final void A07() {
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not return a View from onCreateView() or this was called before onCreateView().");
        throw new IllegalStateException(sb.toString());
    }

    public final void A08(int i) {
        if (this.A0C != null || i != 0) {
            A00(this).A00 = i;
        }
    }

    public final void A09(@Nullable Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable(FragmentActivity.FRAGMENTS_TAG)) != null) {
            this.A0G.A0P(parcelable);
            AnonymousClass09b r1 = this.A0G;
            r1.A0G = false;
            r1.A0H = false;
            AnonymousClass09b.A05(r1, 1);
        }
    }

    @Override // X.AbstractC05180ub
    @NonNull
    public final AnonymousClass0C4 getSavedStateRegistry() {
        return this.A0L.A00;
    }

    @Override // X.AbstractC00480Al
    @NonNull
    public final C00470Ak getViewModelStore() {
        AnonymousClass09b r0 = this.A0H;
        if (r0 != null) {
            HashMap<String, C00470Ak> hashMap = r0.A06.A03;
            C00470Ak r1 = hashMap.get(this.A0P);
            if (r1 != null) {
                return r1;
            }
            C00470Ak r12 = new C00470Ak();
            hashMap.put(this.A0P, r12);
            return r12;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @MainThread
    public final void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        FragmentActivity fragmentActivity;
        AbstractC05340vL<?> r0 = this.A0F;
        if (r0 == null || (fragmentActivity = (FragmentActivity) r0.A00) == null) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(this);
            sb.append(" not attached to an activity.");
            throw new IllegalStateException(sb.toString());
        }
        fragmentActivity.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @NonNull
    public String toString() {
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
            sb.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
            sb.append(str);
        }
        sb.append('}');
        return sb.toString();
    }

    public Fragment() {
        this.A0K.A06(new AbstractC05230uw() {
            /* class androidx.fragment.app.Fragment.AnonymousClass2 */

            @Override // X.AbstractC05230uw
            public final void A87(@NonNull AnonymousClass0AS r1, @NonNull AnonymousClass0AO r2) {
            }
        });
    }

    @Override // X.AnonymousClass0AS
    @NonNull
    public final AnonymousClass0AQ getLifecycle() {
        return this.A0K;
    }
}
