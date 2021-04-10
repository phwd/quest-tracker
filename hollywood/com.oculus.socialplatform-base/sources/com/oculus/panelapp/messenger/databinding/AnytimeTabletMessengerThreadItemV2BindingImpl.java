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
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.MessengerFacepile;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerThreadItemV2BindingImpl extends AnytimeTabletMessengerThreadItemV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        String str;
        boolean z;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MessengerThread messengerThread = this.mThread;
        long j3 = j & 3;
        String str2 = null;
        int i = 0;
        if (j3 != 0) {
            if (messengerThread != null) {
                z = messengerThread.isUnread();
                str2 = messengerThread.getSnippet();
                str = messengerThread.getThreadName();
            } else {
                str = null;
                z = false;
            }
            if (j3 != 0) {
                if (z) {
                    j2 = 8;
                } else {
                    j2 = 4;
                }
                j |= j2;
            }
            if (!z) {
                i = 8;
            }
        } else {
            str = null;
        }
        if ((j & 3) != 0) {
            C11051qV.A02(this.threadSnippet, str2);
            C11051qV.A02(this.threadTitle, str);
            this.unreadIndicator.setVisibility(i);
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

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.thread_facepile, 4);
        sparseIntArray.put(R.id.thread_info, 5);
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerThreadItemV2Binding
    public void setThread(@Nullable MessengerThread messengerThread) {
        this.mThread = messengerThread;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(155);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (155 != i) {
            return false;
        }
        setThread((MessengerThread) obj);
        return true;
    }

    public AnytimeTabletMessengerThreadItemV2BindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 6, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerThreadItemV2BindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 0, (MessengerFacepile) objArr[4], (LinearLayout) objArr[5], (LinearLayout) objArr[0], (OCTextView) objArr[2], (OCTextView) objArr[1], (View) objArr[3]);
        this.mDirtyFlags = -1;
        this.threadItem.setTag(null);
        this.threadSnippet.setTag(null);
        this.threadTitle.setTag(null);
        this.unreadIndicator.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
