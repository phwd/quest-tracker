package X;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.oculus.alpenglow.R;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.util.ArrayList;

/* renamed from: X.0CY  reason: invalid class name */
public final class AnonymousClass0CY extends FrameLayout {
    public boolean A00 = true;
    public ArrayList<View> A01;
    public ArrayList<View> A02;

    @NonNull
    @RequiresApi(HttpEngine.MAX_FOLLOW_UPS)
    public final WindowInsets onApplyWindowInsets(@NonNull WindowInsets windowInsets) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).dispatchApplyWindowInsets(new WindowInsets(windowInsets));
        }
        return windowInsets;
    }

    public final void removeViews(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            A00(getChildAt(i3));
        }
        super.removeViews(i, i2);
    }

    public final void removeViewsInLayout(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            A00(getChildAt(i3));
        }
        super.removeViewsInLayout(i, i2);
    }

    public final void dispatchDraw(@NonNull Canvas canvas) {
        if (this.A00 && this.A01 != null) {
            int i = 0;
            while (true) {
                ArrayList<View> arrayList = this.A01;
                if (i >= arrayList.size()) {
                    break;
                }
                super.drawChild(canvas, arrayList.get(i), getDrawingTime());
                i++;
            }
        }
        super.dispatchDraw(canvas);
    }

    public final boolean drawChild(@NonNull Canvas canvas, @NonNull View view, long j) {
        ArrayList<View> arrayList;
        if (!this.A00 || (arrayList = this.A01) == null || arrayList.size() <= 0 || !arrayList.contains(view)) {
            return super.drawChild(canvas, view, j);
        }
        return false;
    }

    public final void endViewTransition(@NonNull View view) {
        ArrayList<View> arrayList = this.A02;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList<View> arrayList2 = this.A01;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.A00 = true;
            }
        }
        super.endViewTransition(view);
    }

    public final void removeDetachedView(@NonNull View view, boolean z) {
        if (z) {
            A00(view);
        }
        super.removeDetachedView(view, z);
    }

    public void setLayoutTransition(@Nullable LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    public AnonymousClass0CY(@NonNull Context context, @NonNull AttributeSet attributeSet, @NonNull AnonymousClass0Cj r8) {
        super(context, attributeSet);
        String str;
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass18O.A01);
        classAttribute = classAttribute == null ? obtainStyledAttributes.getString(0) : classAttribute;
        String string = obtainStyledAttributes.getString(1);
        obtainStyledAttributes.recycle();
        int id = getId();
        AnonymousClass0MN A0F = r8.A0F(id);
        if (classAttribute != null && A0F == null) {
            if (id <= 0) {
                if (string != null) {
                    str = AnonymousClass006.A05(" with tag ", string);
                } else {
                    str = "";
                }
                throw new IllegalStateException(AnonymousClass006.A07("FragmentContainerView must have an android:id to add Fragment ", classAttribute, str));
            }
            AnonymousClass0MN A012 = r8.A0I().A01(context.getClassLoader(), classAttribute);
            A012.A02();
            C03670cv r1 = new C03670cv(r8);
            r1.A0E = true;
            A012.A0B = this;
            r1.A01(getId(), A012, string, 1);
            r1.A00();
        }
    }

    private void A00(@NonNull View view) {
        ArrayList<View> arrayList;
        if (view.getAnimation() != null || ((arrayList = this.A02) != null && arrayList.contains(view))) {
            ArrayList<View> arrayList2 = this.A01;
            if (arrayList2 == null) {
                arrayList2 = new ArrayList<>();
                this.A01 = arrayList2;
            }
            arrayList2.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(@NonNull View view, int i, @Nullable ViewGroup.LayoutParams layoutParams) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (!(tag instanceof AnonymousClass0MN) || tag == null) {
            throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
        }
        super.addView(view, i, layoutParams);
    }

    public final boolean addViewInLayout(@NonNull View view, int i, @Nullable ViewGroup.LayoutParams layoutParams, boolean z) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if ((tag instanceof AnonymousClass0MN) && tag != null) {
            return super.addViewInLayout(view, i, layoutParams, z);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    public final void removeAllViewsInLayout() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (childCount >= 0) {
                A00(getChildAt(childCount));
            } else {
                super.removeAllViewsInLayout();
                return;
            }
        }
    }

    public final void removeView(@NonNull View view) {
        A00(view);
        super.removeView(view);
    }

    public final void removeViewAt(int i) {
        A00(getChildAt(i));
        super.removeViewAt(i);
    }

    public final void removeViewInLayout(@NonNull View view) {
        A00(view);
        super.removeViewInLayout(view);
    }

    public final void startViewTransition(@NonNull View view) {
        if (view.getParent() == this) {
            ArrayList<View> arrayList = this.A02;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.A02 = arrayList;
            }
            arrayList.add(view);
        }
        super.startViewTransition(view);
    }

    public void setDrawDisappearingViewsLast(boolean z) {
        this.A00 = z;
    }
}
