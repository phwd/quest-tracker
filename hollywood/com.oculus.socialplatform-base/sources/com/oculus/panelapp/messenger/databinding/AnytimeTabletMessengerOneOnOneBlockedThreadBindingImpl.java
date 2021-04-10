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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.OneOnOneThreadBlockedViewModel;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerOneOnOneBlockedThreadBindingImpl extends AnytimeTabletMessengerOneOnOneBlockedThreadBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(OneOnOneThreadBlockedViewModel oneOnOneThreadBlockedViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 70) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != 121) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = null;
        OneOnOneThreadBlockedViewModel oneOnOneThreadBlockedViewModel = this.mViewModel;
        int i = 0;
        if ((15 & j) != 0) {
            if (!((j & 11) == 0 || oneOnOneThreadBlockedViewModel == null)) {
                str = oneOnOneThreadBlockedViewModel.getTitleText();
            }
            if (!((j & 13) == 0 || oneOnOneThreadBlockedViewModel == null)) {
                i = oneOnOneThreadBlockedViewModel.getReportButtonVisibility();
            }
        }
        if ((j & 13) != 0) {
            this.oneOnOneBlockedThreadReportBtn.setVisibility(i);
        }
        if ((j & 11) != 0) {
            C11051qV.A02(this.oneOnOneThreadBlockedMessageTitle, str);
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
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerOneOnOneBlockedThreadBinding
    public void setViewModel(@Nullable OneOnOneThreadBlockedViewModel oneOnOneThreadBlockedViewModel) {
        updateRegistration(0, oneOnOneThreadBlockedViewModel);
        this.mViewModel = oneOnOneThreadBlockedViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.one_on_one_thread_blocked_message_outline, 3);
        sparseIntArray.put(R.id.one_on_one_thread_blocked_message_top_buffer, 4);
        sparseIntArray.put(R.id.one_on_one_thread_blocked_info_icon, 5);
        sparseIntArray.put(R.id.one_on_one_thread_blocked_message_body, 6);
        sparseIntArray.put(R.id.one_on_one_blocked_thread_unblock_btn, 7);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((OneOnOneThreadBlockedViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((OneOnOneThreadBlockedViewModel) obj);
        return true;
    }

    public AnytimeTabletMessengerOneOnOneBlockedThreadBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 8, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerOneOnOneBlockedThreadBindingImpl(AbstractC003408r r15, View view, Object[] objArr) {
        super(r15, view, 1, (OCButton) objArr[2], (OCButton) objArr[7], (ConstraintLayout) objArr[0], (ImageView) objArr[5], (OCTextView) objArr[6], (View) objArr[3], (OCTextView) objArr[1], (Space) objArr[4]);
        this.mDirtyFlags = -1;
        this.oneOnOneBlockedThreadReportBtn.setTag(null);
        this.oneOnOneBlockedThreadView.setTag(null);
        this.oneOnOneThreadBlockedMessageTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
