package com.oculus.panelapp.social.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.panelapp.social.R;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialListHeaderV2BindingImpl extends AnytimeTabletSocialListHeaderV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public AnytimeTabletSocialListHeaderV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialListHeaderV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ShellButton) objArr[2], (OCTextView) objArr[1], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.buttonAddFriend.setTag(null);
        this.headerTitle.setTag(null);
        this.socialListHeaderItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.name == i) {
            setName((String) obj);
        } else if (BR.showAddFriend != i) {
            return false;
        } else {
            setShowAddFriend(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding
    public void setName(@Nullable String str) {
        this.mName = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.name);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2Binding
    public void setShowAddFriend(boolean z) {
        this.mShowAddFriend = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.showAddFriend);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mName;
        boolean z = this.mShowAddFriend;
        int i = ((j & 6) > 0 ? 1 : ((j & 6) == 0 ? 0 : -1));
        int i2 = 0;
        if (i != 0) {
            boolean z2 = true;
            if (!z) {
                z2 = false;
            }
            if (i != 0) {
                j |= z2 ? 16 : 8;
            }
            if (!z2) {
                i2 = 8;
            }
        }
        if ((4 & j) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.buttonAddFriend, getDrawableFromResource(this.buttonAddFriend, R.drawable.anytime_add_friends));
            TextViewBindingAdapter.setText(this.buttonAddFriend, this.buttonAddFriend.getResources().getString(R.string.anytime_tablet_social_add_friend));
        }
        if ((j & 6) != 0) {
            this.buttonAddFriend.setVisibility(i2);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.headerTitle, str);
        }
    }
}
