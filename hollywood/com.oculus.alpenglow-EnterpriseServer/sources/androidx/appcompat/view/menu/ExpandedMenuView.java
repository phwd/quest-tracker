package androidx.appcompat.view.menu;

import X.AbstractC000803d;
import X.AnonymousClass02D;
import X.AnonymousClass03W;
import X.AnonymousClass05Y;
import X.C04250eW;
import X.C04280eZ;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
public final class ExpandedMenuView extends ListView implements AnonymousClass03W, AbstractC000803d, AdapterView.OnItemClickListener {
    public static final int[] A01 = {16842964, 16843049};
    public C04280eZ A00;

    @Override // X.AnonymousClass03W
    public final boolean A5L(C04250eW r4) {
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
        A5L((C04250eW) getAdapter().getItem(i));
    }

    @Override // X.AbstractC000803d
    public final void A5F(C04280eZ r1) {
        this.A00 = r1;
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        AnonymousClass05Y A002 = AnonymousClass05Y.A00(context, attributeSet, A01, i, 0);
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
