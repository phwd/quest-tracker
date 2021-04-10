package defpackage;

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
import com.oculus.browser.R;
import java.lang.reflect.Field;

/* renamed from: bJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1823bJ extends ListView {
    public final Rect F = new Rect();
    public int G = 0;
    public int H = 0;
    public int I = 0;

    /* renamed from: J  reason: collision with root package name */
    public int f9532J = 0;
    public int K;
    public Field L;
    public ZI M;
    public boolean N;
    public boolean O;
    public boolean P;
    public View$OnTouchListenerC5038u90 Q;
    public RunnableC1643aJ R;

    public C1823bJ(Context context, boolean z) {
        super(context, null, R.attr.f4030_resource_name_obfuscated_RES_2130968849);
        this.O = z;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.L = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            AbstractC0754Mh1.f8495a.b(e);
        }
    }

    public int a(int i, int i2, int i3) {
        int i4;
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i5 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        View view = null;
        while (i6 < count) {
            int itemViewType = adapter.getItemViewType(i6);
            if (itemViewType != i7) {
                view = null;
                i7 = itemViewType;
            }
            view = adapter.getView(i6, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i9 = layoutParams.height;
            if (i9 > 0) {
                i4 = View.MeasureSpec.makeMeasureSpec(i9, 1073741824);
            } else {
                i4 = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, i4);
            view.forceLayout();
            if (i6 > 0) {
                i5 += dividerHeight;
            }
            i5 += view.getMeasuredHeight();
            if (i5 >= i2) {
                return (i3 < 0 || i6 <= i3 || i8 <= 0 || i5 == i2) ? i2 : i8;
            }
            if (i3 >= 0 && i6 >= i3) {
                i8 = i5;
            }
            i6++;
        }
        return i5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(android.view.MotionEvent r17, int r18) {
        /*
        // Method dump skipped, instructions count: 365
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1823bJ.b(android.view.MotionEvent, int):boolean");
    }

    public final void c() {
        Drawable selector = getSelector();
        if (selector != null && this.P && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    public void dispatchDraw(Canvas canvas) {
        Drawable selector;
        if (!this.F.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.F);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    public void drawableStateChanged() {
        if (this.R == null) {
            super.drawableStateChanged();
            ZI zi = this.M;
            if (zi != null) {
                zi.G = true;
            }
            c();
        }
    }

    public boolean hasFocus() {
        return this.O || super.hasFocus();
    }

    public boolean hasWindowFocus() {
        return this.O || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.O || super.isFocused();
    }

    public boolean isInTouchMode() {
        return (this.O && this.N) || super.isInTouchMode();
    }

    public void onDetachedFromWindow() {
        this.R = null;
        super.onDetachedFromWindow();
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.R == null) {
            RunnableC1643aJ aJVar = new RunnableC1643aJ(this);
            this.R = aJVar;
            post(aJVar);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                c();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.K = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        RunnableC1643aJ aJVar = this.R;
        if (aJVar != null) {
            C1823bJ bJVar = aJVar.F;
            bJVar.R = null;
            bJVar.removeCallbacks(aJVar);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        ZI zi = drawable != null ? new ZI(drawable) : null;
        this.M = zi;
        super.setSelector(zi);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.G = rect.left;
        this.H = rect.top;
        this.I = rect.right;
        this.f9532J = rect.bottom;
    }
}
