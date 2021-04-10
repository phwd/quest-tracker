package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.ocui.OCTextView;
import java.util.Optional;

public class AnytimeTabletMessengerMessageReactionsItemBindingImpl extends AnytimeTabletMessengerMessageReactionsItemBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final LinearLayout mboundView0;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MessengerReaction messengerReaction = this.mReaction;
        long j2 = j & 3;
        String str2 = null;
        if (j2 == 0 || messengerReaction == null) {
            str = null;
        } else {
            str = messengerReaction.mReaction;
            Optional ofNullable = Optional.ofNullable(messengerReaction.mActorName);
            if (ofNullable != null) {
                str2 = (String) ofNullable.orElse("unknown");
            }
        }
        if (j2 != 0) {
            C11051qV.A02(this.reactionListEmoji, str);
            C11051qV.A02(this.reactionListUserName, str2);
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

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsItemBinding
    public void setReaction(@Nullable MessengerReaction messengerReaction) {
        this.mReaction = messengerReaction;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(153);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (153 != i) {
            return false;
        }
        setReaction((MessengerReaction) obj);
        return true;
    }

    public AnytimeTabletMessengerMessageReactionsItemBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public AnytimeTabletMessengerMessageReactionsItemBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (OCTextView) objArr[1], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.reactionListEmoji.setTag(null);
        this.reactionListUserName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
