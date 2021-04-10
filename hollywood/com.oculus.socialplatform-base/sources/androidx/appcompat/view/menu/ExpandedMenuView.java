package androidx.appcompat.view.menu;

import X.AbstractC11921ta;
import X.AnonymousClass02C;
import X.AnonymousClass1td;
import X.C10901qA;
import X.C11581sN;
import X.C11601sP;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public final class ExpandedMenuView extends ListView implements AbstractC11921ta, AnonymousClass1td, AdapterView.OnItemClickListener {
    public static final int[] A01 = {16842964, 16843049};
    public C11581sN A00;

    @Override // X.AbstractC11921ta
    public final boolean A5p(C11601sP r4) {
        return this.A00.A0K(r4, null, 0);
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        A5p((C11601sP) getAdapter().getItem(i));
    }

    @Override // X.AnonymousClass1td
    public final void A5h(C11581sN r1) {
        this.A00 = r1;
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        C10901qA A002 = C10901qA.A00(context, attributeSet, A01, i, 0);
        TypedArray typedArray = A002.A02;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(A002.A02(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(A002.A02(1));
        }
        A002.A04();
    }
}
