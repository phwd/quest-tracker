package defpackage;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/* renamed from: S31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S31 extends ActionMode {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8873a;
    public final AbstractC5696y2 b;

    public S31(Context context, AbstractC5696y2 y2Var) {
        this.f8873a = context;
        this.b = y2Var;
    }

    public void finish() {
        this.b.c();
    }

    public View getCustomView() {
        return this.b.d();
    }

    public Menu getMenu() {
        return new MenuC2569fj0(this.f8873a, (U31) this.b.e());
    }

    public MenuInflater getMenuInflater() {
        return this.b.f();
    }

    public CharSequence getSubtitle() {
        return this.b.g();
    }

    public Object getTag() {
        return this.b.F;
    }

    public CharSequence getTitle() {
        return this.b.h();
    }

    public boolean getTitleOptionalHint() {
        return this.b.G;
    }

    public void invalidate() {
        this.b.i();
    }

    public boolean isTitleOptional() {
        return this.b.j();
    }

    public void setCustomView(View view) {
        this.b.k(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.b.m(charSequence);
    }

    public void setTag(Object obj) {
        this.b.F = obj;
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.b.o(charSequence);
    }

    public void setTitleOptionalHint(boolean z) {
        this.b.p(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.b.l(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.b.n(i);
    }
}
