package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.XMABubbleViewModel;
import com.oculus.socialplatform.R;

public class AnytimeTabletMessengerXmaItemV2BindingImpl extends AnytimeTabletMessengerXmaItemV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeXmaBubbleViewModel(XMABubbleViewModel xMABubbleViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 140) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 122) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 119) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 136) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i != 126) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 32;
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
            this.mDirtyFlags = 64;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerXmaItemV2Binding
    public void setXmaBubbleViewModel(@Nullable XMABubbleViewModel xMABubbleViewModel) {
        updateRegistration(0, xMABubbleViewModel);
        this.mXmaBubbleViewModel = xMABubbleViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(117);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.xma_item, 5);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        String str;
        String str2;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        XMABubbleViewModel xMABubbleViewModel = this.mXmaBubbleViewModel;
        int i2 = 0;
        String str3 = null;
        if ((127 & j) != 0) {
            if ((j & 67) == 0 || xMABubbleViewModel == null) {
                i = 0;
            } else {
                i = xMABubbleViewModel.getShouldShowDisplayText();
            }
            if ((j & 97) == 0 || xMABubbleViewModel == null) {
                str2 = null;
            } else {
                str2 = xMABubbleViewModel.mAttachmentSubtitle;
            }
            if ((j & 69) == 0 || xMABubbleViewModel == null) {
                str = null;
            } else {
                str = xMABubbleViewModel.mMessageDisplayText;
            }
            if (!((j & 73) == 0 || xMABubbleViewModel == null)) {
                i2 = xMABubbleViewModel.getShouldShowFavicon();
            }
            if (!((j & 81) == 0 || xMABubbleViewModel == null)) {
                str3 = xMABubbleViewModel.mAttachmentTitle;
            }
        } else {
            str = null;
            str2 = null;
            i = 0;
        }
        if ((j & 73) != 0) {
            this.favicon.setVisibility(i2);
        }
        if ((j & 67) != 0) {
            this.messageText.setVisibility(i);
        }
        if ((69 & j) != 0) {
            C11051qV.A02(this.messageText, str);
        }
        if ((97 & j) != 0) {
            C11051qV.A02(this.subtitleText, str2);
        }
        if ((j & 81) != 0) {
            C11051qV.A02(this.titleText, str3);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeXmaBubbleViewModel((XMABubbleViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (117 != i) {
            return false;
        }
        setXmaBubbleViewModel((XMABubbleViewModel) obj);
        return true;
    }

    public AnytimeTabletMessengerXmaItemV2BindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 6, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerXmaItemV2BindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 1, (ImageView) objArr[2], (OCTextView) objArr[1], (ConstraintLayout) objArr[0], (OCTextView) objArr[4], (OCTextView) objArr[3], (ConstraintLayout) objArr[5]);
        this.mDirtyFlags = -1;
        this.favicon.setTag(null);
        this.messageText.setTag(null);
        this.messageWithXma.setTag(null);
        this.subtitleText.setTag(null);
        this.titleText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
