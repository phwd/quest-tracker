package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerLeaveChatConfirmationBindingImpl extends AnytimeTabletMessengerLeaveChatConfirmationBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
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
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.leave_chat_confirmation_header, 1);
        sparseIntArray.put(R.id.leave_chat_confirmation_body, 2);
        sparseIntArray.put(R.id.bottom_button_guideline, 3);
        sparseIntArray.put(R.id.cancel_leave_chat_btn, 4);
        sparseIntArray.put(R.id.confirm_leave_chat_btn, 5);
    }

    public AnytimeTabletMessengerLeaveChatConfirmationBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 6, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerLeaveChatConfirmationBindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 0, (Guideline) objArr[3], (OCButton) objArr[4], (OCButton) objArr[5], (ConstraintLayout) objArr[0], (OCTextView) objArr[2], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.leaveChatConfirmation.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
