package androidx.fragment.app;

import X.AN;
import X.AO;
import X.AP;
import X.AR;
import X.AbstractC00279a;
import X.AbstractC0047Ak;
import X.AbstractC0286a9;
import X.AnonymousClass9B;
import X.AnonymousClass9D;
import X.Bz;
import X.C0;
import X.C0046Aj;
import X.C0279a0;
import X.C0283a4;
import X.Zg;
import X.Zv;
import X.Zw;
import X.Zx;
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
import java.util.HashMap;
import java.util.UUID;

public final class Fragment implements AR, AbstractC0047Ak, Zg, ComponentCallbacks, View.OnCreateContextMenuListener {
    public static final Object A0f = new Object();
    public Zv<AR> A00 = new Zv<>();
    public Runnable A01 = new AnonymousClass9B(this);
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
    public AnonymousClass9D A0C;
    public Fragment A0D;
    public Fragment A0E;
    public AbstractC0286a9<?> A0F;
    @NonNull
    public AbstractC00279a A0G = new C0283a4();
    public AbstractC00279a A0H;
    @Nullable
    public C0279a0 A0I;
    public AO A0J = AO.RESUMED;
    public Zw A0K = new Zw(this);
    public C0 A0L = new C0(this);
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
        AbstractC0286a9<?> a9Var = this.A0F;
        if (a9Var != null && a9Var.A00 != null) {
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

    public static AnonymousClass9D A00(Fragment fragment) {
        AnonymousClass9D r0 = fragment.A0C;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass9D r02 = new AnonymousClass9D();
        fragment.A0C = r02;
        return r02;
    }

    @NonNull
    public final AbstractC00279a A01() {
        AbstractC00279a r0 = this.A0H;
        if (r0 != null) {
            return r0;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not associated with a fragment manager.");
        throw new IllegalStateException(sb.toString());
    }

    public final void A03() {
        this.A0G.A0L();
        this.A0a = true;
        C0279a0 a0Var = new C0279a0();
        this.A0I = a0Var;
        if (a0Var.A00 != null) {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
        this.A0I = null;
    }

    @NonNull
    public final void A04() {
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not return a View from onCreateView() or this was called before onCreateView().");
        throw new IllegalStateException(sb.toString());
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
            AbstractC00279a r1 = this.A0G;
            r1.A0G = false;
            r1.A0H = false;
            AbstractC00279a.A05(r1, 1);
        }
    }

    @Override // X.Zg
    @NonNull
    public final Bz getSavedStateRegistry() {
        return this.A0L.A00;
    }

    @Override // X.AbstractC0047Ak
    @NonNull
    public final C0046Aj getViewModelStore() {
        AbstractC00279a r0 = this.A0H;
        if (r0 != null) {
            HashMap<String, C0046Aj> hashMap = r0.A06.A03;
            C0046Aj aj = hashMap.get(this.A0P);
            if (aj != null) {
                return aj;
            }
            C0046Aj aj2 = new C0046Aj();
            hashMap.put(this.A0P, aj2);
            return aj2;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @MainThread
    public final void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        FragmentActivity fragmentActivity;
        AbstractC0286a9<?> a9Var = this.A0F;
        if (a9Var == null || (fragmentActivity = (FragmentActivity) a9Var.A00) == null) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(this);
            sb.append(" not attached to an activity.");
            throw new IllegalStateException(sb.toString());
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

    public Fragment() {
        this.A0K.A06(new Zx() {
            /* class androidx.fragment.app.Fragment.AnonymousClass2 */

            @Override // X.Zx
            public final void A42(@NonNull AR ar, @NonNull AN an) {
            }
        });
    }

    @Override // X.AR
    @NonNull
    public final AP getLifecycle() {
        return this.A0K;
    }
}
