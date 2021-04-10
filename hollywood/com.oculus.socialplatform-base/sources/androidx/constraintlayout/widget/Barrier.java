package androidx.constraintlayout.widget;

import X.AnonymousClass2a3;
import X.AnonymousClass2aI;
import X.C15012al;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class Barrier extends AnonymousClass2a3 {
    public int A00;
    public C15012al A01;

    public int getMargin() {
        return this.A01.A01;
    }

    public int getType() {
        return this.A00;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.A01.A02 = z;
    }

    public void setMargin(int i) {
        this.A01.A01 = i;
    }

    @Override // X.AnonymousClass2a3
    public final void A06(AttributeSet attributeSet) {
        super.A06(attributeSet);
        this.A01 = new C15012al();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, AnonymousClass2aI.A01);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 15) {
                    this.A00 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == 14) {
                    this.A01.A02 = obtainStyledAttributes.getBoolean(index, true);
                } else if (index == 16) {
                    this.A01.A01 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.A02 = this.A01;
        A04();
    }

    public void setDpMargin(int i) {
        this.A01.A01 = (int) ((((float) i) * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setType(int i) {
        this.A00 = i;
    }

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }
}
