package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.GroupThreadBlockedViewModel;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerGroupThreadContainingBlockedBindingImpl extends AnytimeTabletMessengerGroupThreadContainingBlockedBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(GroupThreadBlockedViewModel groupThreadBlockedViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 115) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 143) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 113) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i != 148) {
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

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerGroupThreadContainingBlockedBinding
    public void setViewModel(@Nullable GroupThreadBlockedViewModel groupThreadBlockedViewModel) {
        updateRegistration(0, groupThreadBlockedViewModel);
        this.mViewModel = groupThreadBlockedViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bottom_button_guideline, 4);
        sparseIntArray.put(R.id.group_thread_containing_blocked, 5);
        sparseIntArray.put(R.id.group_thread_containing_blocked_outline, 6);
        sparseIntArray.put(R.id.group_thread_containing_blocked_message_top_buffer, 7);
        sparseIntArray.put(R.id.group_thread_containing_blocked_info_icon, 8);
        sparseIntArray.put(R.id.group_thread_containing_blocked_leave_group_btn, 9);
        sparseIntArray.put(R.id.group_thread_containing_blocked_continue_btn, 10);
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
        int i = 0;
        GroupThreadBlockedViewModel groupThreadBlockedViewModel = this.mViewModel;
        String str3 = null;
        if ((63 & j) != 0) {
            if ((j & 41) == 0 || groupThreadBlockedViewModel == null) {
                str = null;
            } else {
                str = groupThreadBlockedViewModel.getGroupThreadBlockedViewManageBlockButtonText();
            }
            if ((j & 37) == 0 || groupThreadBlockedViewModel == null) {
                str2 = null;
            } else {
                str2 = groupThreadBlockedViewModel.getGroupThreadBlockedViewBodyText();
            }
            if (!((j & 35) == 0 || groupThreadBlockedViewModel == null)) {
                str3 = groupThreadBlockedViewModel.getGroupThreadBlockedViewTitleText();
            }
            if (!((j & 49) == 0 || groupThreadBlockedViewModel == null)) {
                i = groupThreadBlockedViewModel.getGroupThreadBlockedViewManageBlockButtonVisibility();
            }
        } else {
            str = null;
            str2 = null;
        }
        if ((41 & j) != 0) {
            C11051qV.A02(this.groupThreadContainingBlockedManageBlockBtn, str);
        }
        if ((49 & j) != 0) {
            this.groupThreadContainingBlockedManageBlockBtn.setVisibility(i);
        }
        if ((37 & j) != 0) {
            C11051qV.A02(this.groupThreadContainingBlockedMessageBody, str2);
        }
        if ((j & 35) != 0) {
            C11051qV.A02(this.groupThreadContainingBlockedMessageTitle, str3);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((GroupThreadBlockedViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((GroupThreadBlockedViewModel) obj);
        return true;
    }

    public AnytimeTabletMessengerGroupThreadContainingBlockedBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 11, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerGroupThreadContainingBlockedBindingImpl(AbstractC003408r r18, View view, Object[] objArr) {
        super(r18, view, 1, (Guideline) objArr[4], (View) objArr[5], (OCButton) objArr[10], (ImageView) objArr[8], (OCButton) objArr[9], (OCButton) objArr[3], (OCTextView) objArr[2], (OCTextView) objArr[1], (Space) objArr[7], (View) objArr[6], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.groupThreadContainingBlockedManageBlockBtn.setTag(null);
        this.groupThreadContainingBlockedMessageBody.setTag(null);
        this.groupThreadContainingBlockedMessageTitle.setTag(null);
        this.groupThreadContainingBlockedView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
