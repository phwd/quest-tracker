package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter;
import com.oculus.socialplatform.R;

public class SocialCreateVrInviteListItemBindingImpl extends SocialCreateVrInviteListItemBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(CreateVrInviteListAdapter.ListItem listItem, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 182) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 67) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 80) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i != 203) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
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
            this.mDirtyFlags = 32;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteListItemBinding
    public void setViewModel(@Nullable CreateVrInviteListAdapter.ListItem listItem) {
        updateRegistration(0, listItem);
        this.mViewModel = listItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.create_vr_invite_list_item_button, 4);
        sparseIntArray.put(R.id.left_icon, 5);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        String str;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        CreateVrInviteListAdapter.ListItem listItem = this.mViewModel;
        int i = 0;
        Drawable drawable = null;
        if ((63 & j) != 0) {
            if ((j & 41) == 0 || listItem == null) {
                str = null;
            } else {
                str = listItem.mSubtitle;
            }
            if ((j & 37) == 0 || listItem == null) {
                str2 = null;
            } else {
                str2 = listItem.mTitle;
            }
            if (!((j & 49) == 0 || listItem == null)) {
                i = listItem.getSubtitleVisibility();
            }
            if (!((j & 35) == 0 || listItem == null)) {
                drawable = listItem.getRightGlyph();
            }
        } else {
            str = null;
            str2 = null;
        }
        if ((35 & j) != 0) {
            this.rightGlyph.setImageDrawable(drawable);
        }
        if ((41 & j) != 0) {
            C11051qV.A02(this.subtitle, str);
        }
        if ((49 & j) != 0) {
            this.subtitle.setVisibility(i);
        }
        if ((j & 37) != 0) {
            C11051qV.A02(this.title, str2);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((CreateVrInviteListAdapter.ListItem) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((CreateVrInviteListAdapter.ListItem) obj);
        return true;
    }

    public SocialCreateVrInviteListItemBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 6, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public SocialCreateVrInviteListItemBindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 1, (ConstraintLayout) objArr[0], (OCButton) objArr[4], (ImageView) objArr[5], (ImageView) objArr[1], (OCTextView) objArr[3], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.createVrInviteListItem.setTag(null);
        this.rightGlyph.setTag(null);
        this.subtitle.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
