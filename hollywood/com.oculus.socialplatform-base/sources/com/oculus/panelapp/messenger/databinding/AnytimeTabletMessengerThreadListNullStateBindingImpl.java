package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.MessengerViewModel;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerThreadListNullStateBindingImpl extends AnytimeTabletMessengerThreadListNullStateBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeMessengerViewModel(MessengerViewModel messengerViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != 157) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
        MessengerViewModel messengerViewModel = this.mMessengerViewModel;
        int i = 0;
        long j2 = j & 7;
        if (!(j2 == 0 || messengerViewModel == null)) {
            i = messengerViewModel.getLeftbarViewVisibility();
        }
        if (j2 != 0) {
            this.threadListNullStateView.setVisibility(i);
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

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerThreadListNullStateBinding
    public void setMessengerViewModel(@Nullable MessengerViewModel messengerViewModel) {
        updateRegistration(0, messengerViewModel);
        this.mMessengerViewModel = messengerViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(132);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.thread_list_null_state_title, 1);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeMessengerViewModel((MessengerViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (132 != i) {
            return false;
        }
        setMessengerViewModel((MessengerViewModel) obj);
        return true;
    }

    public AnytimeTabletMessengerThreadListNullStateBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 2, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerThreadListNullStateBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 1, (OCTextView) objArr[1], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.threadListNullStateView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
