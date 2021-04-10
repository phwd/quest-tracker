package X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import com.oculus.socialplatform.R;
import java.lang.reflect.Field;

/* renamed from: X.1F5  reason: invalid class name */
public class AnonymousClass1F5 extends ListView {
    public int A00;
    public int A01 = 0;
    public int A02 = 0;
    public int A03 = 0;
    public int A04 = 0;
    public AnonymousClass1F6 A05;
    public Field A06;
    public boolean A07;
    public boolean A08;
    public AnonymousClass1F7 A09;
    public AnonymousClass0vS A0A;
    public boolean A0B;
    public final Rect A0C = new Rect();

    public final void onDetachedFromWindow() {
        this.A05 = null;
        super.onDetachedFromWindow();
    }

    private void setSelectorEnabled(boolean z) {
        AnonymousClass1F7 r0 = this.A09;
        if (r0 != null) {
            r0.A00 = z;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        if (r13 != 3) goto L_0x0012;
     */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A01(android.view.MotionEvent r24, int r25) {
        /*
        // Method dump skipped, instructions count: 352
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1F5.A01(android.view.MotionEvent, int):boolean");
    }

    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.A0C;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    public final void drawableStateChanged() {
        if (this.A05 == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            Drawable selector = getSelector();
            if (selector != null && this.A07 && isPressed()) {
                selector.setState(getDrawableState());
            }
        }
    }

    public final boolean hasFocus() {
        if (this.A0B || super.hasFocus()) {
            return true;
        }
        return false;
    }

    public final boolean hasWindowFocus() {
        if (this.A0B || super.hasWindowFocus()) {
            return true;
        }
        return false;
    }

    public final boolean isFocused() {
        if (this.A0B || super.isFocused()) {
            return true;
        }
        return false;
    }

    public final boolean isInTouchMode() {
        if ((!this.A0B || !this.A08) && !super.isInTouchMode()) {
            return false;
        }
        return true;
    }

    public boolean onHoverEvent(@NonNull MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.A05 == null) {
            AnonymousClass1F6 r1 = new AnonymousClass1F6(this);
            this.A05 = r1;
            r1.A00.post(r1);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                Drawable selector = getSelector();
                if (selector != null && this.A07 && isPressed()) {
                    selector.setState(getDrawableState());
                    return onHoverEvent;
                }
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        AnonymousClass1F7 r0;
        if (drawable != null) {
            r0 = new AnonymousClass1F7(drawable);
        } else {
            r0 = null;
        }
        this.A09 = r0;
        super.setSelector(r0);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.A02 = rect.left;
        this.A04 = rect.top;
        this.A03 = rect.right;
        this.A01 = rect.bottom;
    }

    public AnonymousClass1F5(@NonNull Context context, boolean z) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.A0B = z;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.A06 = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public final int A00(int i, int i2) {
        int makeMeasureSpec;
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        int i3 = listPaddingTop + listPaddingBottom;
        if (adapter != null) {
            if (dividerHeight <= 0 || divider == null) {
                dividerHeight = 0;
            }
            int count = adapter.getCount();
            View view = null;
            int i4 = 0;
            for (int i5 = 0; i5 < count; i5++) {
                int itemViewType = adapter.getItemViewType(i5);
                if (itemViewType != i4) {
                    view = null;
                    i4 = itemViewType;
                }
                view = adapter.getView(i5, view, this);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = generateDefaultLayoutParams();
                    view.setLayoutParams(layoutParams);
                }
                int i6 = layoutParams.height;
                if (i6 > 0) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
                } else {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                }
                view.measure(i, makeMeasureSpec);
                view.forceLayout();
                if (i5 > 0) {
                    i3 += dividerHeight;
                }
                i3 += view.getMeasuredHeight();
                if (i3 >= i2) {
                    return i2;
                }
            }
        }
        return i3;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.A00 = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        AnonymousClass1F6 r2 = this.A05;
        if (r2 != null) {
            AnonymousClass1F5 r1 = r2.A00;
            r1.A05 = null;
            r1.removeCallbacks(r2);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z) {
        this.A08 = z;
    }
}
