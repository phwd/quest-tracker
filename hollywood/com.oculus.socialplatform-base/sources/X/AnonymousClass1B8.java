package X;

import android.view.View;

/* renamed from: X.1B8  reason: invalid class name */
public class AnonymousClass1B8 implements AnonymousClass2ff {
    public final /* synthetic */ AnonymousClass1Ag A00;

    public AnonymousClass1B8(AnonymousClass1Ag r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass2ff
    public final View A3U(int i) {
        return this.A00.getChildAt(i);
    }

    @Override // X.AnonymousClass2ff
    public final int A4c() {
        AnonymousClass1Ag r0 = this.A00;
        return r0.mHeight - r0.getPaddingBottom();
    }

    @Override // X.AnonymousClass2ff
    public final int A4d() {
        return this.A00.getPaddingTop();
    }

    @Override // X.AnonymousClass2ff
    public final int A3W(View view) {
        return this.A00.getDecoratedBottom(view) + ((C05831Bi) view.getLayoutParams()).bottomMargin;
    }

    @Override // X.AnonymousClass2ff
    public final int A3Y(View view) {
        return this.A00.getDecoratedTop(view) - ((C05831Bi) view.getLayoutParams()).topMargin;
    }
}
