package X;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1rj  reason: invalid class name and case insensitive filesystem */
public final class C11291rj extends ActionMode {
    public final Context A00;
    public final AbstractC11301rk A01;

    public final void finish() {
        this.A01.A01();
    }

    public final View getCustomView() {
        WeakReference<View> weakReference;
        AbstractC11301rk r1 = this.A01;
        if (!(r1 instanceof AnonymousClass1rQ)) {
            weakReference = ((AnonymousClass1rN) r1).A01;
        } else {
            weakReference = ((AnonymousClass1rQ) r1).A04;
        }
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final Menu getMenu() {
        return new AnonymousClass1rg(this.A00, (AnonymousClass05X) this.A01.A00());
    }

    public final MenuInflater getMenuInflater() {
        Context context;
        AbstractC11301rk r1 = this.A01;
        if (!(r1 instanceof AnonymousClass1rQ)) {
            context = ((AnonymousClass1rN) r1).A02;
        } else {
            context = ((AnonymousClass1rQ) r1).A03.getContext();
        }
        return new C11571sK(context);
    }

    public final CharSequence getSubtitle() {
        ActionBarContextView actionBarContextView;
        AbstractC11301rk r1 = this.A01;
        if (!(r1 instanceof AnonymousClass1rQ)) {
            actionBarContextView = ((AnonymousClass1rN) r1).A04.A09;
        } else {
            actionBarContextView = ((AnonymousClass1rQ) r1).A03;
        }
        return actionBarContextView.A02;
    }

    public final Object getTag() {
        return this.A01.A00;
    }

    public final CharSequence getTitle() {
        ActionBarContextView actionBarContextView;
        AbstractC11301rk r1 = this.A01;
        if (!(r1 instanceof AnonymousClass1rQ)) {
            actionBarContextView = ((AnonymousClass1rN) r1).A04.A09;
        } else {
            actionBarContextView = ((AnonymousClass1rQ) r1).A03;
        }
        return actionBarContextView.A03;
    }

    public final boolean getTitleOptionalHint() {
        return this.A01.A01;
    }

    public final void invalidate() {
        this.A01.A02();
    }

    public final boolean isTitleOptional() {
        ActionBarContextView actionBarContextView;
        AbstractC11301rk r1 = this.A01;
        if (r1 instanceof AnonymousClass1rQ) {
            actionBarContextView = ((AnonymousClass1rQ) r1).A03;
        } else if (!(r1 instanceof AnonymousClass1rN)) {
            return false;
        } else {
            actionBarContextView = ((AnonymousClass1rN) r1).A04.A09;
        }
        return actionBarContextView.A04;
    }

    public final void setCustomView(View view) {
        WeakReference<View> weakReference;
        AbstractC11301rk r1 = this.A01;
        if (!(r1 instanceof AnonymousClass1rQ)) {
            AnonymousClass1rN r12 = (AnonymousClass1rN) r1;
            r12.A04.A09.setCustomView(view);
            r12.A01 = new WeakReference<>(view);
            return;
        }
        AnonymousClass1rQ r13 = (AnonymousClass1rQ) r1;
        r13.A03.setCustomView(view);
        if (view != null) {
            weakReference = new WeakReference<>(view);
        } else {
            weakReference = null;
        }
        r13.A04 = weakReference;
    }

    public final void setTag(Object obj) {
        this.A01.A00 = obj;
    }

    public final void setTitleOptionalHint(boolean z) {
        this.A01.A05(z);
    }

    public C11291rj(Context context, AbstractC11301rk r2) {
        this.A00 = context;
        this.A01 = r2;
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i) {
        AbstractC11301rk r1 = this.A01;
        if (!(r1 instanceof AnonymousClass1rQ)) {
            AnonymousClass1rN r12 = (AnonymousClass1rN) r1;
            r12.A03(r12.A04.A01.getResources().getString(i));
            return;
        }
        AnonymousClass1rQ r13 = (AnonymousClass1rQ) r1;
        r13.A03(r13.A00.getString(i));
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.A01.A03(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i) {
        AbstractC11301rk r1 = this.A01;
        if (!(r1 instanceof AnonymousClass1rQ)) {
            AnonymousClass1rN r12 = (AnonymousClass1rN) r1;
            r12.A04(r12.A04.A01.getResources().getString(i));
            return;
        }
        AnonymousClass1rQ r13 = (AnonymousClass1rQ) r1;
        r13.A04(r13.A00.getString(i));
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.A01.A04(charSequence);
    }
}
