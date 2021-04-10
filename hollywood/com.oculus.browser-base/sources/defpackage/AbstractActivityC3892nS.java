package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: nS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractActivityC3892nS extends AbstractActivityC3119iw implements J2 {
    public final C5595xS K;
    public final C4865t80 L = new C4865t80(this);
    public boolean M;
    public boolean N;
    public boolean O = true;
    public boolean P;
    public boolean Q;
    public int R;
    public MY0 S;

    public AbstractActivityC3892nS() {
        C3721mS mSVar = new C3721mS(this);
        TE0.b(mSVar, "callbacks == null");
        this.K = new C5595xS(mSVar);
    }

    public static void X(int i) {
        if ((i & -65536) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    public static boolean Z(KS ks, EnumC3328k80 k80) {
        AbstractActivityC3892nS nSVar;
        EnumC3328k80 k802 = EnumC3328k80.STARTED;
        boolean z = false;
        for (AbstractComponentCallbacksC3550lS lSVar : ks.c.g()) {
            if (lSVar != null) {
                C3721mS mSVar = lSVar.X;
                if (mSVar == null) {
                    nSVar = null;
                } else {
                    nSVar = mSVar.f10420J;
                }
                if (nSVar != null) {
                    z |= Z(lSVar.w(), k80);
                }
                ET et = lSVar.v0;
                if (et != null) {
                    if (((C4865t80) et.Q()).b.compareTo(k802) >= 0) {
                        lSVar.v0.F.g(k80);
                        z = true;
                    }
                }
                if (lSVar.u0.b.compareTo(k802) >= 0) {
                    lSVar.u0.g(k80);
                    z = true;
                }
            }
        }
        return z;
    }

    public final int W(AbstractComponentCallbacksC3550lS lSVar) {
        if (this.S.i() < 65534) {
            while (true) {
                MY0 my0 = this.S;
                int i = this.R;
                if (my0.G) {
                    my0.c();
                }
                if (AbstractC0179Cy.a(my0.H, my0.f8482J, i) >= 0) {
                    this.R = (this.R + 1) % 65534;
                } else {
                    int i2 = this.R;
                    this.S.g(i2, lSVar.f10345J);
                    this.R = (this.R + 1) % 65534;
                    return i2;
                }
            }
        } else {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
    }

    public KS Y() {
        return this.K.f11609a.I;
    }

    public void a0() {
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.M);
        printWriter.print(" mResumed=");
        printWriter.print(this.N);
        printWriter.print(" mStopped=");
        printWriter.print(this.O);
        if (getApplication() != null) {
            J90.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.K.f11609a.I.z(str, fileDescriptor, printWriter, strArr);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.K.a();
        int i3 = i >> 16;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String str = (String) this.S.d(i4);
            this.S.h(i4);
            if (str == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            AbstractComponentCallbacksC3550lS K2 = this.K.f11609a.I.K(str);
            if (K2 == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
                return;
            }
            K2.c0(i & 65535, i2, intent);
            return;
        }
        Object obj = K2.f8337a;
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.K.a();
        this.K.f11609a.I.l(configuration);
    }

    @Override // defpackage.AbstractActivityC3119iw
    public void onCreate(Bundle bundle) {
        C3721mS mSVar = this.K.f11609a;
        mSVar.I.c(mSVar, mSVar, null);
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            C3721mS mSVar2 = this.K.f11609a;
            if (mSVar2 instanceof AbstractC4823su1) {
                mSVar2.I.j0(parcelable);
                if (bundle.containsKey("android:support:next_request_index")) {
                    this.R = bundle.getInt("android:support:next_request_index");
                    int[] intArray = bundle.getIntArray("android:support:request_indicies");
                    String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                    if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                        Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                    } else {
                        this.S = new MY0(intArray.length);
                        for (int i = 0; i < intArray.length; i++) {
                            this.S.g(intArray[i], stringArray[i]);
                        }
                    }
                }
            } else {
                throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
            }
        }
        if (this.S == null) {
            this.S = new MY0(10);
            this.R = 0;
        }
        super.onCreate(bundle);
        this.L.e(EnumC3157j80.ON_CREATE);
        this.K.f11609a.I.n();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu);
        C5595xS xSVar = this.K;
        return onCreatePanelMenu | xSVar.f11609a.I.o(menu, getMenuInflater());
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View onCreateView = this.K.f11609a.I.f.onCreateView(view, str, context, attributeSet);
        return onCreateView == null ? super.onCreateView(view, str, context, attributeSet) : onCreateView;
    }

    public void onDestroy() {
        super.onDestroy();
        this.K.f11609a.I.p();
        this.L.e(EnumC3157j80.ON_DESTROY);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.K.f11609a.I.q();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.K.f11609a.I.s(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.K.f11609a.I.m(menuItem);
    }

    public void onMultiWindowModeChanged(boolean z) {
        this.K.f11609a.I.r(z);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.K.a();
    }

    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.K.f11609a.I.t(menu);
        }
        super.onPanelClosed(i, menu);
    }

    public void onPause() {
        super.onPause();
        this.N = false;
        this.K.f11609a.I.x(3);
        this.L.e(EnumC3157j80.ON_PAUSE);
    }

    public void onPictureInPictureModeChanged(boolean z) {
        this.K.f11609a.I.v(z);
    }

    public void onPostResume() {
        super.onPostResume();
        this.L.e(EnumC3157j80.ON_RESUME);
        KS ks = this.K.f11609a.I;
        ks.u = false;
        ks.v = false;
        ks.x(4);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i == 0) {
            return super.onPreparePanel(0, view, menu) | this.K.f11609a.I.w(menu);
        }
        return super.onPreparePanel(i, view, menu);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.K.a();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String str = (String) this.S.d(i3);
            this.S.h(i3);
            if (str == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            AbstractComponentCallbacksC3550lS K2 = this.K.f11609a.I.K(str);
            if (K2 == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
                return;
            }
            K2.A0();
        }
    }

    public void onResume() {
        super.onResume();
        this.N = true;
        this.K.a();
        this.K.f11609a.I.D(true);
    }

    @Override // defpackage.AbstractActivityC3119iw
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        do {
        } while (Z(Y(), EnumC3328k80.CREATED));
        this.L.e(EnumC3157j80.ON_STOP);
        Parcelable k0 = this.K.f11609a.I.k0();
        if (k0 != null) {
            bundle.putParcelable("android:support:fragments", k0);
        }
        if (this.S.i() > 0) {
            bundle.putInt("android:support:next_request_index", this.R);
            int[] iArr = new int[this.S.i()];
            String[] strArr = new String[this.S.i()];
            for (int i = 0; i < this.S.i(); i++) {
                iArr[i] = this.S.f(i);
                strArr[i] = (String) this.S.j(i);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    public void onStart() {
        super.onStart();
        this.O = false;
        if (!this.M) {
            this.M = true;
            KS ks = this.K.f11609a.I;
            ks.u = false;
            ks.v = false;
            ks.x(2);
        }
        this.K.a();
        this.K.f11609a.I.D(true);
        this.L.e(EnumC3157j80.ON_START);
        KS ks2 = this.K.f11609a.I;
        ks2.u = false;
        ks2.v = false;
        ks2.x(3);
    }

    public void onStateNotSaved() {
        this.K.a();
    }

    public void onStop() {
        super.onStop();
        this.O = true;
        do {
        } while (Z(Y(), EnumC3328k80.CREATED));
        KS ks = this.K.f11609a.I;
        ks.v = true;
        ks.x(2);
        this.L.e(EnumC3157j80.ON_STOP);
    }

    public void startActivityForResult(Intent intent, int i) {
        if (!this.Q && i != -1) {
            X(i);
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        if (!this.P && i != -1) {
            X(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        if (!this.Q && i != -1) {
            X(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (!this.P && i != -1) {
            X(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View onCreateView = this.K.f11609a.I.f.onCreateView(null, str, context, attributeSet);
        return onCreateView == null ? super.onCreateView(str, context, attributeSet) : onCreateView;
    }
}
