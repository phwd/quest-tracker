package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: eF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2324eF0 extends AbstractComponentCallbacksC3550lS implements AbstractC4204pF0, AbstractC3862nF0, AbstractC4033oF0, VE {
    public RecyclerView A0;
    public boolean B0;
    public boolean C0;
    public int D0 = R.layout.f40770_resource_name_obfuscated_RES_2131624386;
    public Handler E0 = new HandlerC1812bF0(this);
    public final Runnable F0 = new RunnableC1983cF0(this);
    public final C2154dF0 y0 = new C2154dF0(this);
    public C4375qF0 z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void C0(Bundle bundle) {
        PreferenceScreen preferenceScreen = this.z0.g;
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.i(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        this.i0 = true;
        C4375qF0 qf0 = this.z0;
        qf0.h = this;
        qf0.i = this;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void E0() {
        this.i0 = true;
        C4375qF0 qf0 = this.z0;
        qf0.h = null;
        qf0.i = null;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void F0(View view, Bundle bundle) {
        PreferenceScreen preferenceScreen;
        Bundle bundle2;
        PreferenceScreen preferenceScreen2;
        if (!(bundle == null || (bundle2 = bundle.getBundle("android:preferences")) == null || (preferenceScreen2 = this.z0.g) == null)) {
            preferenceScreen2.h(bundle2);
        }
        if (this.B0 && (preferenceScreen = this.z0.g) != null) {
            this.A0.q0(new C3520lF0(preferenceScreen));
            preferenceScreen.v();
        }
        this.C0 = true;
    }

    /* JADX INFO: finally extract failed */
    public void e1(int i) {
        C4375qF0 qf0 = this.z0;
        if (qf0 != null) {
            Context x = x();
            PreferenceScreen preferenceScreen = this.z0.g;
            qf0.e = true;
            C3691mF0 mf0 = new C3691mF0(x, qf0);
            XmlResourceParser xml = x.getResources().getXml(i);
            try {
                Preference c = mf0.c(xml, preferenceScreen);
                xml.close();
                PreferenceScreen preferenceScreen2 = (PreferenceScreen) c;
                preferenceScreen2.w(qf0);
                SharedPreferences.Editor editor = qf0.d;
                if (editor != null) {
                    editor.apply();
                }
                qf0.e = false;
                j1(preferenceScreen2);
            } catch (Throwable th) {
                xml.close();
                throw th;
            }
        } else {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
    }

    public Preference f1(CharSequence charSequence) {
        PreferenceScreen preferenceScreen;
        C4375qF0 qf0 = this.z0;
        if (qf0 == null || (preferenceScreen = qf0.g) == null) {
            return null;
        }
        return preferenceScreen.b0(charSequence);
    }

    public abstract void g1(Bundle bundle, String str);

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        TypedValue typedValue = new TypedValue();
        x().getTheme().resolveAttribute(R.attr.f7080_resource_name_obfuscated_RES_2130969154, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = R.style.f69550_resource_name_obfuscated_RES_2132017528;
        }
        x().getTheme().applyStyle(i, false);
        C4375qF0 qf0 = new C4375qF0(x());
        this.z0 = qf0;
        qf0.j = this;
        Bundle bundle2 = this.K;
        g1(bundle, bundle2 != null ? bundle2.getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") : null);
    }

    public RecyclerView h1(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        RecyclerView recyclerView;
        if (x().getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(R.layout.f40790_resource_name_obfuscated_RES_2131624388, viewGroup, false);
        recyclerView2.t0(new LinearLayoutManager(x()));
        C4716sF0 sf0 = new C4716sF0(recyclerView2);
        recyclerView2.X0 = sf0;
        AbstractC1920bu1.n(recyclerView2, sf0);
        return recyclerView2;
    }

    public void i1(Drawable drawable) {
        C2154dF0 df0 = this.y0;
        Objects.requireNonNull(df0);
        if (drawable != null) {
            df0.b = drawable.getIntrinsicHeight();
        } else {
            df0.b = 0;
        }
        df0.f9763a = drawable;
        df0.d.A0.S();
    }

    public void j1(PreferenceScreen preferenceScreen) {
        boolean z;
        C4375qF0 qf0 = this.z0;
        PreferenceScreen preferenceScreen2 = qf0.g;
        if (preferenceScreen != preferenceScreen2) {
            if (preferenceScreen2 != null) {
                preferenceScreen2.A();
            }
            qf0.g = preferenceScreen;
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.B0 = true;
            if (this.C0 && !this.E0.hasMessages(1)) {
                this.E0.obtainMessage(1).sendToTarget();
            }
        }
    }

    @Override // defpackage.AbstractC3862nF0
    public void k(Preference preference) {
        x();
        u();
        if (G().J("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof ListPreference) {
                String str = preference.Q;
                C4868t90 t90 = new C4868t90();
                Bundle bundle = new Bundle(1);
                bundle.putString("key", str);
                t90.U0(bundle);
                t90.b1(this, 0);
                t90.k1(G(), "androidx.preference.PreferenceFragment.DIALOG");
                return;
            }
            StringBuilder i = AbstractC2531fV.i("Cannot display dialog for an unknown Preference type: ");
            i.append(preference.getClass().getSimpleName());
            i.append(". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
            throw new IllegalArgumentException(i.toString());
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TypedArray obtainStyledAttributes = x().obtainStyledAttributes(null, FJ0.p0, R.attr.f7020_resource_name_obfuscated_RES_2130969148, 0);
        this.D0 = obtainStyledAttributes.getResourceId(0, this.D0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(2, -1);
        boolean z = obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.recycle();
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(x());
        View inflate = cloneInContext.inflate(this.D0, viewGroup, false);
        View findViewById = inflate.findViewById(16908351);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup2 = (ViewGroup) findViewById;
            RecyclerView h1 = h1(cloneInContext, viewGroup2);
            if (h1 != null) {
                this.A0 = h1;
                h1.g(this.y0);
                i1(drawable);
                if (dimensionPixelSize != -1) {
                    C2154dF0 df0 = this.y0;
                    df0.b = dimensionPixelSize;
                    df0.d.A0.S();
                }
                this.y0.c = z;
                if (this.A0.getParent() == null) {
                    viewGroup2.addView(this.A0);
                }
                this.E0.post(this.F0);
                return inflate;
            }
            throw new RuntimeException("Could not create RecyclerView");
        }
        throw new IllegalStateException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void n0() {
        this.E0.removeCallbacks(this.F0);
        this.E0.removeMessages(1);
        if (this.B0) {
            this.A0.q0(null);
            PreferenceScreen preferenceScreen = this.z0.g;
            if (preferenceScreen != null) {
                preferenceScreen.A();
            }
        }
        this.A0 = null;
        this.i0 = true;
    }

    @Override // defpackage.AbstractC4204pF0
    public boolean q(Preference preference) {
        if (preference.S == null) {
            return false;
        }
        x();
        u();
        Log.w("PreferenceFragment", "onPreferenceStartFragment is not implemented in the parent activity - attempting to use a fallback implementation. You should implement this method so that you can configure the new fragment that will be displayed, and set a transition between the fragments.");
        KS G = G();
        Bundle j = preference.j();
        AbstractComponentCallbacksC3550lS a2 = G.P().a(O0().getClassLoader(), preference.S);
        a2.U0(j);
        a2.b1(this, 0);
        C0317Fe fe = new C0317Fe(G);
        fe.q(((View) this.k0.getParent()).getId(), a2);
        if (fe.h) {
            fe.g = true;
            fe.i = null;
            fe.e();
            return true;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }
}
