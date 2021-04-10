package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.fragment.R;
import java.util.ArrayList;

public final class FragmentContainerView extends FrameLayout {
    private ArrayList<View> mDisappearingFragmentChildren;
    private boolean mDrawDisappearingViewsFirst = true;
    private ArrayList<View> mTransitioningFragmentViews;

    FragmentContainerView(Context context, AttributeSet attrs, FragmentManager fm) {
        super(context, attrs);
        String name = attrs.getClassAttribute();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FragmentContainerView);
        name = name == null ? a.getString(R.styleable.FragmentContainerView_android_name) : name;
        String tag = a.getString(R.styleable.FragmentContainerView_android_tag);
        a.recycle();
        int id = getId();
        Fragment existingFragment = fm.findFragmentById(id);
        if (name != null && existingFragment == null) {
            if (id <= 0) {
                throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + name + (tag != null ? " with tag " + tag : ""));
            }
            Fragment containerFragment = fm.getFragmentFactory().instantiate(context.getClassLoader(), name);
            containerFragment.onInflate(context, attrs, (Bundle) null);
            fm.beginTransaction().setReorderingAllowed(true).add(this, containerFragment, tag).commitNowAllowingStateLoss();
        }
    }

    public void setLayoutTransition(LayoutTransition transition) {
        if (Build.VERSION.SDK_INT < 18) {
            super.setLayoutTransition(transition);
            return;
        }
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).dispatchApplyWindowInsets(new WindowInsets(insets));
        }
        return insets;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mDrawDisappearingViewsFirst && this.mDisappearingFragmentChildren != null) {
            for (int i = 0; i < this.mDisappearingFragmentChildren.size(); i++) {
                super.drawChild(canvas, this.mDisappearingFragmentChildren.get(i), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (!this.mDrawDisappearingViewsFirst || this.mDisappearingFragmentChildren == null || this.mDisappearingFragmentChildren.size() <= 0 || !this.mDisappearingFragmentChildren.contains(child)) {
            return super.drawChild(canvas, child, drawingTime);
        }
        return false;
    }

    public void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.mTransitioningFragmentViews == null) {
                this.mTransitioningFragmentViews = new ArrayList<>();
            }
            this.mTransitioningFragmentViews.add(view);
        }
        super.startViewTransition(view);
    }

    public void endViewTransition(View view) {
        if (this.mTransitioningFragmentViews != null) {
            this.mTransitioningFragmentViews.remove(view);
            if (this.mDisappearingFragmentChildren != null && this.mDisappearingFragmentChildren.remove(view)) {
                this.mDrawDisappearingViewsFirst = true;
            }
        }
        super.endViewTransition(view);
    }

    /* access modifiers changed from: package-private */
    public void setDrawDisappearingViewsLast(boolean drawDisappearingViewsFirst) {
        this.mDrawDisappearingViewsFirst = drawDisappearingViewsFirst;
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (FragmentManager.getViewFragment(child) == null) {
            throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + child + " is not associated with a Fragment.");
        }
        super.addView(child, index, params);
    }

    /* access modifiers changed from: protected */
    public boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        if (FragmentManager.getViewFragment(child) != null) {
            return super.addViewInLayout(child, index, params, preventRequestLayout);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + child + " is not associated with a Fragment.");
    }

    public void removeViewAt(int index) {
        addDisappearingFragmentView(getChildAt(index));
        super.removeViewAt(index);
    }

    public void removeViewInLayout(View view) {
        addDisappearingFragmentView(view);
        super.removeViewInLayout(view);
    }

    public void removeView(View view) {
        addDisappearingFragmentView(view);
        super.removeView(view);
    }

    public void removeViews(int start, int count) {
        for (int i = start; i < start + count; i++) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeViews(start, count);
    }

    public void removeViewsInLayout(int start, int count) {
        for (int i = start; i < start + count; i++) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeViewsInLayout(start, count);
    }

    public void removeAllViewsInLayout() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            addDisappearingFragmentView(getChildAt(i));
        }
        super.removeAllViewsInLayout();
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View child, boolean animate) {
        if (animate) {
            addDisappearingFragmentView(child);
        }
        super.removeDetachedView(child, animate);
    }

    private void addDisappearingFragmentView(View v) {
        if (v.getAnimation() != null || (this.mTransitioningFragmentViews != null && this.mTransitioningFragmentViews.contains(v))) {
            if (this.mDisappearingFragmentChildren == null) {
                this.mDisappearingFragmentChildren = new ArrayList<>();
            }
            this.mDisappearingFragmentChildren.add(v);
        }
    }
}
