package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.ocui.OCTextView;

public class AnytimeTabletMessengerAdminMessageItemV2BindingImpl extends AnytimeTabletMessengerAdminMessageItemV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MessengerMessage messengerMessage = this.mMessage;
        String str = null;
        long j2 = j & 3;
        if (!(j2 == 0 || messengerMessage == null)) {
            str = messengerMessage.getText();
        }
        if (j2 != 0) {
            C11051qV.A02(this.adminText, str);
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

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerAdminMessageItemV2Binding
    public void setMessage(@Nullable MessengerMessage messengerMessage) {
        this.mMessage = messengerMessage;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(156);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (156 != i) {
            return false;
        }
        setMessage((MessengerMessage) obj);
        return true;
    }

    public AnytimeTabletMessengerAdminMessageItemV2BindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 1, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public AnytimeTabletMessengerAdminMessageItemV2BindingImpl(AbstractC003408r r3, View view, Object[] objArr) {
        super(r3, view, 0, (OCTextView) objArr[0]);
        this.mDirtyFlags = -1;
        this.adminText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
