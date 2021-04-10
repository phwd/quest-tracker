package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public class OccontextMenuItemBindingImpl extends OccontextMenuItemBinding {
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
        sparseIntArray.put(R.id.item_icon_container, 1);
        sparseIntArray.put(R.id.item_icon, 2);
        sparseIntArray.put(R.id.item_text_container, 3);
        sparseIntArray.put(R.id.item_title, 4);
        sparseIntArray.put(R.id.item_subtitle, 5);
        sparseIntArray.put(R.id.item_right_icon, 6);
    }

    public OccontextMenuItemBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 7, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public OccontextMenuItemBindingImpl(AbstractC003408r r14, View view, Object[] objArr) {
        super(r14, view, 0, (ConstraintLayout) objArr[0], (ImageView) objArr[2], (FrameLayout) objArr[1], (ImageView) objArr[6], (OCTextView) objArr[5], (ConstraintLayout) objArr[3], (OCTextView) objArr[4]);
        this.mDirtyFlags = -1;
        this.contextMenuItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
