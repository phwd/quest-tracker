package X;

import android.view.View;

/* renamed from: X.1B7  reason: invalid class name */
public class AnonymousClass1B7 implements AnonymousClass2ff {
    public final /* synthetic */ AnonymousClass1Ag A00;

    public AnonymousClass1B7(AnonymousClass1Ag r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass2ff
    public final View A3U(int i) {
        return this.A00.getChildAt(i);
    }

    @Override // X.AnonymousClass2ff
    public final int A4c() {
        AnonymousClass1Ag r0 = this.A00;
        return r0.mWidth - r0.getPaddingRight();
    }

    @Override // X.AnonymousClass2ff
    public final int A4d() {
        return this.A00.getPaddingLeft();
    }

    @Override // X.AnonymousClass2ff
    public final int A3W(View view) {
        return this.A00.getDecoratedRight(view) + ((C05831Bi) view.getLayoutParams()).rightMargin;
    }

    @Override // X.AnonymousClass2ff
    public final int A3Y(View view) {
        return this.A00.getDecoratedLeft(view) - ((C05831Bi) view.getLayoutParams()).leftMargin;
    }
}
