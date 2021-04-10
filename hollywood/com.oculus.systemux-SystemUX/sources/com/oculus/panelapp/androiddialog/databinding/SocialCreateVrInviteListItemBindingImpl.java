package com.oculus.panelapp.androiddialog.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteListAdapter;

public class SocialCreateVrInviteListItemBindingImpl extends SocialCreateVrInviteListItemBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.create_vr_invite_list_item_button, 4);
        sViewsWithIds.put(R.id.left_icon, 5);
    }

    public SocialCreateVrInviteListItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private SocialCreateVrInviteListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[0], (OCButton) objArr[4], (ImageView) objArr[5], (ImageView) objArr[1], (OCTextView) objArr[3], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.createVrInviteListItem.setTag(null);
        this.rightGlyph.setTag(null);
        this.subtitle.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((CreateVrInviteListAdapter.ListItem) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteListItemBinding
    public void setViewModel(@Nullable CreateVrInviteListAdapter.ListItem listItem) {
        updateRegistration(0, listItem);
        this.mViewModel = listItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((CreateVrInviteListAdapter.ListItem) obj, i2);
    }

    private boolean onChangeViewModel(CreateVrInviteListAdapter.ListItem listItem, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.rightGlyph) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.title) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.subtitle) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i != BR.subtitleVisibility) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        String str2;
        Drawable drawable;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        CreateVrInviteListAdapter.ListItem listItem = this.mViewModel;
        int i = 0;
        Drawable drawable2 = null;
        if ((63 & j) != 0) {
            str2 = ((j & 41) == 0 || listItem == null) ? null : listItem.getSubtitle();
            String title = ((j & 37) == 0 || listItem == null) ? null : listItem.getTitle();
            if (!((j & 49) == 0 || listItem == null)) {
                i = listItem.getSubtitleVisibility();
            }
            if (!((j & 35) == 0 || listItem == null)) {
                drawable2 = listItem.getRightGlyph();
            }
            drawable = drawable2;
            str = title;
        } else {
            drawable = null;
            str2 = null;
            str = null;
        }
        if ((35 & j) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.rightGlyph, drawable);
        }
        if ((41 & j) != 0) {
            TextViewBindingAdapter.setText(this.subtitle, str2);
        }
        if ((j & 49) != 0) {
            this.subtitle.setVisibility(i);
        }
        if ((j & 37) != 0) {
            TextViewBindingAdapter.setText(this.title, str);
        }
    }
}
