package com.oculus.common.quickpromotion.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.quickpromotion.R;
import com.oculus.common.quickpromotion.tooltip.QPTooltipView;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;

public class QpTooltipViewBindingImpl extends QpTooltipViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final QPTooltipView mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(R.id.tooltip_caret_left, 1);
        sViewsWithIds.put(R.id.tooltip_caret_up, 2);
        sViewsWithIds.put(R.id.tooltip_caret_right, 3);
        sViewsWithIds.put(R.id.tooltip_caret_down, 4);
        sViewsWithIds.put(R.id.tooltip_main, 5);
        sViewsWithIds.put(R.id.tooltip_icon, 6);
        sViewsWithIds.put(R.id.tooltip_text, 7);
        sViewsWithIds.put(R.id.dismiss_cta_icon, 8);
    }

    public QpTooltipViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    private QpTooltipViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCButton) objArr[8], (ImageView) objArr[4], (ImageView) objArr[1], (ImageView) objArr[3], (ImageView) objArr[2], (ImageView) objArr[6], (View) objArr[5], (OCTextView) objArr[7]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (QPTooltipView) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }
}
