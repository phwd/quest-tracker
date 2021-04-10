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
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.views.MessengerReactionsPill;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerMessageItemContainerV2BindingImpl extends AnytimeTabletMessengerMessageItemContainerV2Binding {
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
        boolean z = this.mShouldShowSenderInfo;
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
                i = 4;
            }
        }
        if ((j & 3) != 0) {
            this.profilePicThem.setVisibility(i);
            this.profilePicThemButton.setVisibility(i);
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
        sparseIntArray.put(R.id.reactions_pill_wrapper, 3);
        sparseIntArray.put(R.id.reactions_pill, 4);
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding
    public void setShouldShowSenderInfo(boolean z) {
        this.mShouldShowSenderInfo = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(137);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (137 != i) {
            return false;
        }
        setShouldShowSenderInfo(((Boolean) obj).booleanValue());
        return true;
    }

    public AnytimeTabletMessengerMessageItemContainerV2BindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 5, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerMessageItemContainerV2BindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 0, (ConstraintLayout) objArr[0], (ImageView) objArr[1], (OCButton) objArr[2], (MessengerReactionsPill) objArr[4], (LinearLayout) objArr[3]);
        this.mDirtyFlags = -1;
        this.messageItem.setTag(null);
        this.profilePicThem.setTag(null);
        this.profilePicThemButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
