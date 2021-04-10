package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C10221le;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public class ListItemBindingImpl extends ListItemBinding {
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
        sparseIntArray.put(R.id.list_item_button, 1);
        sparseIntArray.put(R.id.list_item_left_single_icon_container, 2);
        sparseIntArray.put(R.id.list_item_left_single_image, 3);
        sparseIntArray.put(R.id.list_item_left_single_icon, 4);
        sparseIntArray.put(R.id.list_item_left_multi_image_2, 5);
        sparseIntArray.put(R.id.list_item_left_multi_image_1_outline, 6);
        sparseIntArray.put(R.id.list_item_left_multi_image_1, 7);
        sparseIntArray.put(R.id.list_item_right_glyph, 8);
        sparseIntArray.put(R.id.list_item_title, 9);
        sparseIntArray.put(R.id.list_item_subtitle_icon_spacer, 10);
        sparseIntArray.put(R.id.list_item_subtitle_icon, 11);
        sparseIntArray.put(R.id.list_item_subtitle, 12);
        sparseIntArray.put(R.id.list_item_alt_subtitle_icon, 13);
        sparseIntArray.put(R.id.list_item_alt_subtitle, 14);
        sparseIntArray.put(R.id.list_item_bottom_barrier, 15);
        sparseIntArray.put(R.id.list_item_title_barrier, 16);
        sparseIntArray.put(R.id.list_item_subtitle_icon_barrier, 17);
    }

    public ListItemBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 18, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public ListItemBindingImpl(AbstractC003408r r41, View view, Object[] objArr) {
        super(r41, view, 0, (ConstraintLayout) objArr[0], (OCTextView) objArr[14], (C10221le) objArr[13], (Barrier) objArr[15], (OCButton) objArr[1], (C10221le) objArr[7], (View) objArr[6], (C10221le) objArr[5], (C10221le) objArr[4], (View) objArr[2], (C10221le) objArr[3], (C10221le) objArr[8], (OCTextView) objArr[12], (C10221le) objArr[11], (Barrier) objArr[17], (View) objArr[10], (OCTextView) objArr[9], (Barrier) objArr[16]);
        this.mDirtyFlags = -1;
        this.listItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
