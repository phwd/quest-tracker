package X;

import android.content.Context;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* renamed from: X.0em  reason: invalid class name and case insensitive filesystem */
public final class C04370em extends AnonymousClass02W {
    public boolean A00;
    public Window.Callback A01;
    public AbstractC002604c A02;
    public boolean A03;
    public ArrayList<ActionBar.OnMenuVisibilityListener> A04 = new ArrayList<>();
    public boolean A05;
    public final AbstractC004505b A06 = new C04400eq(this);
    public final Runnable A07 = new AnonymousClass032(this);

    @Override // X.AnonymousClass02W
    public final void A03() {
        this.A02.A4s().removeCallbacks(this.A07);
    }

    @Override // X.AnonymousClass02W
    public final boolean A04() {
        return this.A02.A54();
    }

    @Override // X.AnonymousClass02W
    public final boolean A05() {
        ViewGroup A4s = this.A02.A4s();
        Runnable runnable = this.A07;
        A4s.removeCallbacks(runnable);
        this.A02.A4s().postOnAnimation(runnable);
        return true;
    }

    @Override // X.AnonymousClass02W
    public final boolean A06() {
        return this.A02.A8S();
    }

    @Override // X.AnonymousClass02W
    public final int A08() {
        return this.A02.A3S();
    }

    @Override // X.AnonymousClass02W
    public final Context A09() {
        return this.A02.A3F();
    }

    @Override // X.AnonymousClass02W
    public final void A0C(CharSequence charSequence) {
        this.A02.setWindowTitle(charSequence);
    }

    @Override // X.AnonymousClass02W
    public final void A0D(boolean z) {
        if (z != this.A05) {
            this.A05 = z;
            ArrayList<ActionBar.OnMenuVisibilityListener> arrayList = this.A04;
            if (0 < arrayList.size()) {
                arrayList.get(0);
                throw null;
            }
        }
    }

    @Override // X.AnonymousClass02W
    public final boolean A0G() {
        AbstractC002604c r1 = this.A02;
        if (!r1.A50()) {
            return false;
        }
        r1.A1j();
        return true;
    }

    @Override // X.AnonymousClass02W
    public final boolean A0H(int i, KeyEvent keyEvent) {
        int i2;
        if (!this.A03) {
            this.A02.A80(new C04390ep(this), new AnonymousClass0eo(this));
            this.A03 = true;
        }
        Menu A42 = this.A02.A42();
        if (A42 == null) {
            return false;
        }
        if (keyEvent != null) {
            i2 = keyEvent.getDeviceId();
        } else {
            i2 = -1;
        }
        int keyboardType = KeyCharacterMap.load(i2).getKeyboardType();
        boolean z = true;
        if (keyboardType == 1) {
            z = false;
        }
        A42.setQwertyMode(z);
        return A42.performShortcut(i, keyEvent, 0);
    }

    public C04370em(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        C04030dq r1 = new C04030dq(toolbar, false);
        this.A02 = r1;
        C04380en r0 = new C04380en(this, callback);
        this.A01 = r0;
        r1.setWindowCallback(r0);
        toolbar.A0C = this.A06;
        r1.setWindowTitle(charSequence);
    }

    @Override // X.AnonymousClass02W
    public final boolean A07(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            A06();
        }
        return true;
    }
}
