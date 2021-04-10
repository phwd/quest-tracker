package X;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.view.ViewGroup;

/* renamed from: X.0eJ  reason: invalid class name and case insensitive filesystem */
public class C04200eJ extends C003604n {
    @ViewDebug.ExportedProperty
    public int A00;
    @ViewDebug.ExportedProperty
    public int A01;
    @ViewDebug.ExportedProperty
    public boolean A02;
    public boolean A03;
    @ViewDebug.ExportedProperty
    public boolean A04;
    @ViewDebug.ExportedProperty
    public boolean A05;

    public C04200eJ() {
        super(-2);
        this.A04 = false;
    }

    public C04200eJ(C04200eJ r2) {
        super(r2);
        this.A04 = r2.A04;
    }

    public C04200eJ(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C04200eJ(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }
}
