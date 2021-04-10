package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCTextView;

public class AnytimeTabletMessengerMessageReactionsSummaryItemBindingImpl extends AnytimeTabletMessengerMessageReactionsSummaryItemBinding {
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mReactionUsername;
        Drawable drawable = this.mReactionEmoji;
        long j2 = 5 & j;
        if ((j & 6) != 0) {
            this.reactionEmoji.setImageDrawable(drawable);
        }
        if (j2 != 0) {
            C11051qV.A02(this.reactionUsername, str);
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

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsSummaryItemBinding
    public void setReactionEmoji(@Nullable Drawable drawable) {
        this.mReactionEmoji = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(166);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsSummaryItemBinding
    public void setReactionUsername(@Nullable String str) {
        this.mReactionUsername = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(146);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (146 == i) {
            setReactionUsername((String) obj);
            return true;
        } else if (166 != i) {
            return false;
        } else {
            setReactionEmoji((Drawable) obj);
            return true;
        }
    }

    public AnytimeTabletMessengerMessageReactionsSummaryItemBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public AnytimeTabletMessengerMessageReactionsSummaryItemBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (ImageView) objArr[1], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.reactionEmoji.setTag(null);
        this.reactionUsername.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
