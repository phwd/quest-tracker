package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public class OctooltipBindingImpl extends OctooltipBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tooltip_caret_left, 1);
        sparseIntArray.put(R.id.tooltip_caret_up, 2);
        sparseIntArray.put(R.id.tooltip_caret_right, 3);
        sparseIntArray.put(R.id.tooltip_caret_down, 4);
        sparseIntArray.put(R.id.tooltip_body, 5);
        sparseIntArray.put(R.id.tooltip_icon, 6);
        sparseIntArray.put(R.id.tooltip_text, 7);
        sparseIntArray.put(R.id.dismiss_cta_icon, 8);
    }

    public OctooltipBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 9, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public OctooltipBindingImpl(AbstractC003408r r16, View view, Object[] objArr) {
        super(r16, view, 0, (OCButton) objArr[8], (View) objArr[5], (ImageView) objArr[4], (ImageView) objArr[1], (ImageView) objArr[3], (ImageView) objArr[2], (ConstraintLayout) objArr[0], (ImageView) objArr[6], (OCTextView) objArr[7]);
        this.mDirtyFlags = -1;
        this.tooltipContainer.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
