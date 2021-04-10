package X;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0ek  reason: invalid class name and case insensitive filesystem */
public class C04350ek extends AnonymousClass03D implements AnonymousClass03V {
    public AnonymousClass03C A00;
    public WeakReference<View> A01;
    public final C04280eZ A02;
    public final Context A03;
    public final /* synthetic */ C04340ej A04;

    public C04350ek(C04340ej r3, Context context, AnonymousClass03C r5) {
        this.A04 = r3;
        this.A03 = context;
        this.A00 = r5;
        C04280eZ r1 = new C04280eZ(context);
        r1.A00 = 1;
        this.A02 = r1;
        r1.A0C(this);
    }

    @Override // X.AnonymousClass03D
    public final Menu A00() {
        return this.A02;
    }

    @Override // X.AnonymousClass03D
    public final MenuInflater A01() {
        return new AnonymousClass03J(this.A03);
    }

    @Override // X.AnonymousClass03D
    public final View A02() {
        WeakReference<View> weakReference = this.A01;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // X.AnonymousClass03D
    public final CharSequence A03() {
        return this.A04.A02.A02;
    }

    @Override // X.AnonymousClass03D
    public final CharSequence A04() {
        return this.A04.A02.A03;
    }

    @Override // X.AnonymousClass03D
    public final void A05() {
        C04340ej r3 = this.A04;
        if (r3.A01 == this) {
            if (r3.A03) {
                r3.A07 = this;
                r3.A06 = this.A00;
            } else {
                this.A00.A63(this);
            }
            this.A00 = null;
            r3.A0I(false);
            ActionBarContextView actionBarContextView = r3.A02;
            if (actionBarContextView.A00 == null) {
                actionBarContextView.removeAllViews();
                actionBarContextView.A01 = null;
                ((AbstractC001203i) actionBarContextView).A01 = null;
            }
            r3.A0B.A4s().sendAccessibilityEvent(32);
            r3.A0A.setHideOnContentScrollEnabled(r3.A0E);
            r3.A01 = null;
        }
    }

    @Override // X.AnonymousClass03D
    public final void A06() {
        if (this.A04.A01 == this) {
            C04280eZ r1 = this.A02;
            r1.A09();
            try {
                this.A00.A6N(this, r1);
            } finally {
                r1.A08();
            }
        }
    }

    @Override // X.AnonymousClass03D
    public final void A07(int i) {
        A0A(this.A04.A00.getResources().getString(i));
    }

    @Override // X.AnonymousClass03D
    public final void A08(int i) {
        A0B(this.A04.A00.getResources().getString(i));
    }

    @Override // X.AnonymousClass03D
    public final void A09(View view) {
        this.A04.A02.setCustomView(view);
        this.A01 = new WeakReference<>(view);
    }

    @Override // X.AnonymousClass03D
    public final void A0A(CharSequence charSequence) {
        this.A04.A02.setSubtitle(charSequence);
    }

    @Override // X.AnonymousClass03D
    public final void A0B(CharSequence charSequence) {
        this.A04.A02.setTitle(charSequence);
    }

    @Override // X.AnonymousClass03D
    public final boolean A0D() {
        return this.A04.A02.A04;
    }

    @Override // X.AnonymousClass03V
    public final boolean A6D(@NonNull C04280eZ r2, @NonNull MenuItem menuItem) {
        AnonymousClass03C r0 = this.A00;
        if (r0 != null) {
            return r0.A5o(this, menuItem);
        }
        return false;
    }

    @Override // X.AnonymousClass03V
    public final void A6E(@NonNull C04280eZ r2) {
        if (this.A00 != null) {
            A06();
            AnonymousClass0Mm r0 = ((AbstractC001203i) this.A04.A02).A00;
            if (r0 != null) {
                r0.A07();
            }
        }
    }

    @Override // X.AnonymousClass03D
    public final void A0C(boolean z) {
        super.A0C(z);
        this.A04.A02.setTitleOptional(z);
    }
}
