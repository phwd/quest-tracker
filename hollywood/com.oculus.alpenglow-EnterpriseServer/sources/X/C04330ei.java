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
/* renamed from: X.0ei  reason: invalid class name and case insensitive filesystem */
public final class C04330ei extends AnonymousClass03D implements AnonymousClass03V {
    public Context A00;
    public AnonymousClass03C A01;
    public C04280eZ A02;
    public ActionBarContextView A03;
    public WeakReference<View> A04;
    public boolean A05;

    @Override // X.AnonymousClass03D
    public final Menu A00() {
        return this.A02;
    }

    @Override // X.AnonymousClass03D
    public final MenuInflater A01() {
        return new AnonymousClass03J(this.A03.getContext());
    }

    @Override // X.AnonymousClass03D
    public final View A02() {
        WeakReference<View> weakReference = this.A04;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // X.AnonymousClass03D
    public final CharSequence A03() {
        return this.A03.A02;
    }

    @Override // X.AnonymousClass03D
    public final CharSequence A04() {
        return this.A03.A03;
    }

    @Override // X.AnonymousClass03D
    public final void A05() {
        if (!this.A05) {
            this.A05 = true;
            this.A03.sendAccessibilityEvent(32);
            this.A01.A63(this);
        }
    }

    @Override // X.AnonymousClass03D
    public final void A06() {
        this.A01.A6N(this, this.A02);
    }

    @Override // X.AnonymousClass03D
    public final void A07(int i) {
        A0A(this.A00.getString(i));
    }

    @Override // X.AnonymousClass03D
    public final void A08(int i) {
        A0B(this.A00.getString(i));
    }

    @Override // X.AnonymousClass03D
    public final void A09(View view) {
        WeakReference<View> weakReference;
        this.A03.setCustomView(view);
        if (view != null) {
            weakReference = new WeakReference<>(view);
        } else {
            weakReference = null;
        }
        this.A04 = weakReference;
    }

    @Override // X.AnonymousClass03D
    public final void A0A(CharSequence charSequence) {
        this.A03.setSubtitle(charSequence);
    }

    @Override // X.AnonymousClass03D
    public final void A0B(CharSequence charSequence) {
        this.A03.setTitle(charSequence);
    }

    @Override // X.AnonymousClass03D
    public final boolean A0D() {
        return this.A03.A04;
    }

    @Override // X.AnonymousClass03V
    public final boolean A6D(@NonNull C04280eZ r2, @NonNull MenuItem menuItem) {
        return this.A01.A5o(this, menuItem);
    }

    public C04330ei(Context context, ActionBarContextView actionBarContextView, AnonymousClass03C r5) {
        this.A00 = context;
        this.A03 = actionBarContextView;
        this.A01 = r5;
        C04280eZ r1 = new C04280eZ(actionBarContextView.getContext());
        r1.A00 = 1;
        this.A02 = r1;
        r1.A0C(this);
    }

    @Override // X.AnonymousClass03D
    public final void A0C(boolean z) {
        super.A0C(z);
        this.A03.setTitleOptional(z);
    }

    @Override // X.AnonymousClass03V
    public final void A6E(@NonNull C04280eZ r2) {
        A06();
        AnonymousClass0Mm r0 = ((AbstractC001203i) this.A03).A00;
        if (r0 != null) {
            r0.A07();
        }
    }
}
