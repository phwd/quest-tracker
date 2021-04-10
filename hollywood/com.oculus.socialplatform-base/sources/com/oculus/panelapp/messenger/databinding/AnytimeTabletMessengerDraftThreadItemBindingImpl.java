package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerDraftThreadItemBindingImpl extends AnytimeTabletMessengerDraftThreadItemBinding {
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
        sparseIntArray.put(R.id.facepile_left_icon, 1);
        sparseIntArray.put(R.id.draft_text, 2);
        sparseIntArray.put(R.id.close_indicator, 3);
    }

    public AnytimeTabletMessengerDraftThreadItemBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 4, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerDraftThreadItemBindingImpl(AbstractC003408r r11, View view, Object[] objArr) {
        super(r11, view, 0, (ImageView) objArr[3], (OCTextView) objArr[2], (ImageView) objArr[1], (LinearLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.threadItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
