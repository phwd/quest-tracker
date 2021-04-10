package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;

public class MessengerFacepileBindingImpl extends MessengerFacepileBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z = this.mIsFacepileThreadIcon;
        long j3 = j & 3;
        int i = 0;
        if (j3 != 0) {
            if (j3 != 0) {
                if (z) {
                    j2 = 8;
                } else {
                    j2 = 4;
                }
                j |= j2;
            }
            if (!z) {
                i = 8;
            }
        }
        if ((j & 3) != 0) {
            this.facepileLeftIconBorder.setVisibility(i);
            this.facepileRightIcon.setVisibility(i);
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
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.facepile_left_icon, 3);
    }

    @Override // com.oculus.panelapp.messenger.databinding.MessengerFacepileBinding
    public void setIsFacepileThreadIcon(boolean z) {
        this.mIsFacepileThreadIcon = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(120);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (120 != i) {
            return false;
        }
        setIsFacepileThreadIcon(((Boolean) obj).booleanValue());
        return true;
    }

    public MessengerFacepileBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 4, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public MessengerFacepileBindingImpl(AbstractC003408r r11, View view, Object[] objArr) {
        super(r11, view, 0, (ImageView) objArr[3], (View) objArr[2], (ImageView) objArr[1], (FrameLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.facepileLeftIconBorder.setTag(null);
        this.facepileRightIcon.setTag(null);
        this.messengerFacepileThreadIcon.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
