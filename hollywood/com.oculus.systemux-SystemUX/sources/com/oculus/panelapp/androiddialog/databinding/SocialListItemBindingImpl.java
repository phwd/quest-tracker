package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;

public class SocialListItemBindingImpl extends SocialListItemBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

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
        sViewsWithIds.put(R.id.social_list_item_button, 1);
        sViewsWithIds.put(R.id.social_list_item_left_single_icon_container, 2);
        sViewsWithIds.put(R.id.social_list_item_left_single_image, 3);
        sViewsWithIds.put(R.id.social_list_item_left_single_icon, 4);
        sViewsWithIds.put(R.id.social_list_item_left_multi_image_2, 5);
        sViewsWithIds.put(R.id.social_list_item_left_multi_image_1_outline, 6);
        sViewsWithIds.put(R.id.social_list_item_left_multi_image_1, 7);
        sViewsWithIds.put(R.id.social_list_item_right_glyph, 8);
        sViewsWithIds.put(R.id.social_list_item_title, 9);
        sViewsWithIds.put(R.id.social_list_item_subtitle_icon_spacer, 10);
        sViewsWithIds.put(R.id.social_list_item_subtitle_icon, 11);
        sViewsWithIds.put(R.id.social_list_item_subtitle, 12);
        sViewsWithIds.put(R.id.social_list_item_alt_subtitle_icon, 13);
        sViewsWithIds.put(R.id.social_list_item_alt_subtitle, 14);
        sViewsWithIds.put(R.id.social_list_item_bottom_barrier, 15);
        sViewsWithIds.put(R.id.social_list_item_title_barrier, 16);
        sViewsWithIds.put(R.id.social_list_item_subtitle_icon_barrier, 17);
    }

    public SocialListItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 18, sIncludes, sViewsWithIds));
    }

    private SocialListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (OCTextView) objArr[14], (ImageView) objArr[13], (Barrier) objArr[15], (OCButton) objArr[1], (ImageView) objArr[7], (View) objArr[6], (ImageView) objArr[5], (ImageView) objArr[4], (View) objArr[2], (ImageView) objArr[3], (ImageView) objArr[8], (OCTextView) objArr[12], (ImageView) objArr[11], (Barrier) objArr[17], (View) objArr[10], (OCTextView) objArr[9], (Barrier) objArr[16]);
        this.mDirtyFlags = -1;
        this.socialListItem.setTag(null);
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
