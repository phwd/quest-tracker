package androidx.constraintlayout.widget;

import X.AnonymousClass2a3;
import android.content.Context;
import android.util.AttributeSet;

public class Group extends AnonymousClass2a3 {
    @Override // X.AnonymousClass2a3
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        A05();
    }

    public void setElevation(float f) {
        super.setElevation(f);
        A05();
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        A05();
    }

    @Override // X.AnonymousClass2a3
    public final void A06(AttributeSet attributeSet) {
        super.A06(attributeSet);
    }

    public Group(Context context) {
        super(context);
    }

    public Group(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Group(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
