package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;

/* renamed from: rG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4547rG extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11193a = {16843284};
    public Drawable b;
    public int c;
    public final Rect d = new Rect();

    public C4547rG(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f11193a);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        this.b = drawable;
        if (drawable == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        obtainStyledAttributes.recycle();
        if (i == 0 || i == 1) {
            this.c = i;
            return;
        }
        throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        Drawable drawable = this.b;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.c == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override // defpackage.EK0
    public void h(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
        int i;
        int i2;
        int i3;
        int i4;
        if (recyclerView.U != null && this.b != null) {
            int i5 = 0;
            if (this.c == 1) {
                canvas.save();
                if (recyclerView.getClipToPadding()) {
                    i3 = recyclerView.getPaddingLeft();
                    i4 = recyclerView.getWidth() - recyclerView.getPaddingRight();
                    canvas.clipRect(i3, recyclerView.getPaddingTop(), i4, recyclerView.getHeight() - recyclerView.getPaddingBottom());
                } else {
                    i4 = recyclerView.getWidth();
                    i3 = 0;
                }
                int childCount = recyclerView.getChildCount();
                while (i5 < childCount) {
                    View childAt = recyclerView.getChildAt(i5);
                    RecyclerView.N(childAt, this.d);
                    int round = Math.round(childAt.getTranslationY()) + this.d.bottom;
                    this.b.setBounds(i3, round - this.b.getIntrinsicHeight(), i4, round);
                    this.b.draw(canvas);
                    i5++;
                }
                canvas.restore();
                return;
            }
            canvas.save();
            if (recyclerView.getClipToPadding()) {
                i = recyclerView.getPaddingTop();
                i2 = recyclerView.getHeight() - recyclerView.getPaddingBottom();
                canvas.clipRect(recyclerView.getPaddingLeft(), i, recyclerView.getWidth() - recyclerView.getPaddingRight(), i2);
            } else {
                i2 = recyclerView.getHeight();
                i = 0;
            }
            int childCount2 = recyclerView.getChildCount();
            while (i5 < childCount2) {
                View childAt2 = recyclerView.getChildAt(i5);
                IK0 ik0 = recyclerView.U;
                Rect rect = this.d;
                Objects.requireNonNull(ik0);
                RecyclerView.N(childAt2, rect);
                int round2 = Math.round(childAt2.getTranslationX()) + this.d.right;
                this.b.setBounds(round2 - this.b.getIntrinsicWidth(), i, round2, i2);
                this.b.draw(canvas);
                i5++;
            }
            canvas.restore();
        }
    }
}
