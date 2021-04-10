package androidx.fragment.app;

import X.AV;
import X.AX;
import X.AbstractC0041Bq;
import X.AbstractC0144Tp;
import X.Au;
import X.Bs;
import X.C0141Tk;
import X.CB;
import X.CC;
import X.Ds;
import X.Dt;
import X.EnumC0039Bo;
import X.EnumC0040Bp;
import X.TM;
import X.Tb;
import X.Tc;
import X.Td;
import X.Tg;
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

public final class Fragment implements Bs, CC, TM, ComponentCallbacks, View.OnCreateContextMenuListener {
    public static final Object A0f = new Object();
    public Tb<Bs> A00 = new Tb<>();
    public Runnable A01 = new AV(this);
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
    public AX A0C;
    public Fragment A0D;
    public Fragment A0E;
    public AbstractC0144Tp<?> A0F;
    @NonNull
    public Au A0G = new C0141Tk();
    public Au A0H;
    @Nullable
    public Tg A0I;
    public EnumC0040Bp A0J = EnumC0040Bp.RESUMED;
    public Tc A0K = new Tc(this);
    public Dt A0L = new Dt(this);
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
        AbstractC0144Tp<?> tp = this.A0F;
        if (tp != null && tp.A00 != null) {
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

    public static AX A00(Fragment fragment) {
        AX ax = fragment.A0C;
        if (ax != null) {
            return ax;
        }
        AX ax2 = new AX();
        fragment.A0C = ax2;
        return ax2;
    }

    @NonNull
    public final Au A01() {
        Au au = this.A0H;
        if (au != null) {
            return au;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not associated with a fragment manager.");
        throw new IllegalStateException(sb.toString());
    }

    public final void A03() {
        this.A0G.A0L();
        this.A0a = true;
        Tg tg = new Tg();
        this.A0I = tg;
        if (tg.A00 != null) {
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
            Au au = this.A0G;
            au.A0G = false;
            au.A0H = false;
            Au.A05(au, 1);
        }
    }

    @Override // X.TM
    @NonNull
    public final Ds getSavedStateRegistry() {
        return this.A0L.A00;
    }

    @Override // X.CC
    @NonNull
    public final CB getViewModelStore() {
        Au au = this.A0H;
        if (au != null) {
            HashMap<String, CB> hashMap = au.A06.A03;
            CB cb = hashMap.get(this.A0P);
            if (cb != null) {
                return cb;
            }
            CB cb2 = new CB();
            hashMap.put(this.A0P, cb2);
            return cb2;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @MainThread
    public final void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        FragmentActivity fragmentActivity;
        AbstractC0144Tp<?> tp = this.A0F;
        if (tp == null || (fragmentActivity = (FragmentActivity) tp.A00) == null) {
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
        this.A0K.A05(new Td() {
            /* class androidx.fragment.app.Fragment.AnonymousClass2 */

            @Override // X.Td
            public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
            }
        });
    }

    @Override // X.Bs
    @NonNull
    public final AbstractC0041Bq getLifecycle() {
        return this.A0K;
    }
}
