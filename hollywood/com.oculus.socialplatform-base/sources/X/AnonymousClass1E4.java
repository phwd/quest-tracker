package X;

import android.view.View;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1E4  reason: invalid class name */
public final class AnonymousClass1E4 {
    public float A00;
    public float A01;
    public int A02;
    public int A03 = Integer.MAX_VALUE;
    public int A04 = Integer.MIN_VALUE;
    public List<Integer> A05 = new ArrayList();
    public int A06 = Integer.MIN_VALUE;
    public int A07;
    public int A08;
    public int A09;
    public int A0A;
    public int A0B;
    public int A0C;
    public int A0D;
    public int A0E;
    public int A0F = Integer.MAX_VALUE;

    public final void A00(View view, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        this.A03 = Math.min(this.A03, (view.getLeft() - flexItem.A4I()) - i);
        this.A0F = Math.min(this.A0F, (view.getTop() - flexItem.A4K()) - i2);
        this.A04 = Math.max(this.A04, view.getRight() + flexItem.A4J() + i3);
        this.A06 = Math.max(this.A06, view.getBottom() + flexItem.A4H() + i4);
    }
}
