package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialListHeaderV2BindingImpl extends AnytimeTabletSocialListHeaderV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mName;
        boolean z = this.mShowAddFriend;
        long j3 = j & 6;
        int i = 0;
        if (j3 != 0) {
            boolean z2 = true;
            if (!z) {
                z2 = false;
            }
            if (j3 != 0) {
                if (z2) {
                    j2 = 16;
                } else {
                    j2 = 8;
                }
                j |= j2;
            }
            if (!z2) {
                i = 8;
            }
        }
        if ((4 & j) != 0) {
            ShellButton shellButton = this.buttonAddFriend;
            C11051qV.A00(shellButton, AnonymousClass1uW.getDrawableFromResource(shellButton, R.drawable.anytime_add_friends));
            ShellButton shellButton2 = this.buttonAddFriend;
            C11051qV.A02(shellButton2, shellButton2.getResources().getString(R.string.anytime_tablet_social_add_friend));
        }
        if ((6 & j) != 0) {
            this.buttonAddFriend.setVisibility(i);
        }
        if ((j & 5) != 0) {
            C11051qV.A02(this.headerTitle, str);
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
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding
    public void setName(@Nullable String str) {
        this.mName = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(108);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding
    public void setShowAddFriend(boolean z) {
        this.mShowAddFriend = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(103);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (108 == i) {
            setName((String) obj);
            return true;
        } else if (103 != i) {
            return false;
        } else {
            setShowAddFriend(((Boolean) obj).booleanValue());
            return true;
        }
    }

    public AnytimeTabletSocialListHeaderV2BindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public AnytimeTabletSocialListHeaderV2BindingImpl(AbstractC003408r r10, View view, Object[] objArr) {
        super(r10, view, 0, (ShellButton) objArr[2], (OCTextView) objArr[1], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.buttonAddFriend.setTag(null);
        this.headerTitle.setTag(null);
        this.socialListHeaderItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
