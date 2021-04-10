package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerThreadParticipant;

public class MessengerIntegrityParticipantSelectItemBindingImpl extends MessengerIntegrityParticipantSelectItemBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.participant_photo, 2);
    }

    public MessengerIntegrityParticipantSelectItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private MessengerIntegrityParticipantSelectItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[0], (SimpleDraweeView) objArr[2], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.participantItem.setTag(null);
        this.participantShortName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.participant != i) {
            return false;
        }
        setParticipant((MessengerThreadParticipant) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityParticipantSelectItemBinding
    public void setParticipant(@Nullable MessengerThreadParticipant messengerThreadParticipant) {
        this.mParticipant = messengerThreadParticipant;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.participant);
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
        MessengerThreadParticipant messengerThreadParticipant = this.mParticipant;
        String str = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (!(i == 0 || messengerThreadParticipant == null)) {
            str = messengerThreadParticipant.getName();
        }
        if (i != 0) {
            this.participantShortName.setText(str);
        }
    }
}
