package X;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.03G  reason: invalid class name */
public final class AnonymousClass03G extends ActionMode {
    public final Context A00;
    public final AnonymousClass03D A01;

    public final void finish() {
        this.A01.A05();
    }

    public final View getCustomView() {
        return this.A01.A02();
    }

    public final Menu getMenu() {
        return new AnonymousClass0eQ(this.A00, (AbstractMenuC007608p) this.A01.A00());
    }

    public final MenuInflater getMenuInflater() {
        return this.A01.A01();
    }

    public final CharSequence getSubtitle() {
        return this.A01.A03();
    }

    public final Object getTag() {
        return this.A01.A00;
    }

    public final CharSequence getTitle() {
        return this.A01.A04();
    }

    public final boolean getTitleOptionalHint() {
        return this.A01.A01;
    }

    public final void invalidate() {
        this.A01.A06();
    }

    public final boolean isTitleOptional() {
        return this.A01.A0D();
    }

    public final void setCustomView(View view) {
        this.A01.A09(view);
    }

    public final void setTag(Object obj) {
        this.A01.A00 = obj;
    }

    public final void setTitleOptionalHint(boolean z) {
        this.A01.A0C(z);
    }

    public AnonymousClass03G(Context context, AnonymousClass03D r2) {
        this.A00 = context;
        this.A01 = r2;
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i) {
        this.A01.A07(i);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.A01.A0A(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i) {
        this.A01.A08(i);
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.A01.A0B(charSequence);
    }
}
