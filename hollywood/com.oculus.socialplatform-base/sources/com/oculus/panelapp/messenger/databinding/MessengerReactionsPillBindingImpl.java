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
import com.oculus.panelapp.messenger.views.MessengerReactionsPillViewModel;

public class MessengerReactionsPillBindingImpl extends MessengerReactionsPillBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    private boolean onChangeViewModel(MessengerReactionsPillViewModel messengerReactionsPillViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 159) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 124) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 162) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 149) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 163) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 112) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i != 128) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 128;
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
            this.mDirtyFlags = 256;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.MessengerReactionsPillBinding
    public void setViewModel(@Nullable MessengerReactionsPillViewModel messengerReactionsPillViewModel) {
        updateRegistration(0, messengerReactionsPillViewModel);
        this.mViewModel = messengerReactionsPillViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        int i;
        int i2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MessengerReactionsPillViewModel messengerReactionsPillViewModel = this.mViewModel;
        int i3 = 0;
        String str = null;
        if ((511 & j) != 0) {
            if ((j & 265) == 0 || messengerReactionsPillViewModel == null) {
                i = 0;
            } else {
                i = messengerReactionsPillViewModel.getSecondReactionVisibility();
            }
            if ((j & 273) == 0 || messengerReactionsPillViewModel == null) {
                drawable3 = null;
            } else {
                drawable3 = messengerReactionsPillViewModel.getThirdReaction();
            }
            if ((j & 385) == 0 || messengerReactionsPillViewModel == null) {
                i2 = 0;
            } else {
                i2 = messengerReactionsPillViewModel.getReactionTotalCountVisibility();
            }
            if ((j & 261) == 0 || messengerReactionsPillViewModel == null) {
                drawable2 = null;
            } else {
                drawable2 = messengerReactionsPillViewModel.getSecondReaction();
            }
            if ((j & 259) == 0 || messengerReactionsPillViewModel == null) {
                drawable = null;
            } else {
                drawable = messengerReactionsPillViewModel.getFirstReaction();
            }
            if (!((j & 289) == 0 || messengerReactionsPillViewModel == null)) {
                i3 = messengerReactionsPillViewModel.getThirdReactionVisibility();
            }
            if (!((j & 321) == 0 || messengerReactionsPillViewModel == null)) {
                str = String.valueOf(messengerReactionsPillViewModel.mReactionsTotalCount);
            }
        } else {
            drawable = null;
            drawable2 = null;
            drawable3 = null;
            i = 0;
            i2 = 0;
        }
        if ((j & 259) != 0) {
            this.firstReaction.setImageDrawable(drawable);
        }
        if ((j & 261) != 0) {
            this.secondReaction.setImageDrawable(drawable2);
        }
        if ((j & 265) != 0) {
            this.secondReaction.setVisibility(i);
        }
        if ((j & 273) != 0) {
            this.thirdReaction.setImageDrawable(drawable3);
        }
        if ((j & 289) != 0) {
            this.thirdReaction.setVisibility(i3);
        }
        if ((321 & j) != 0) {
            C11051qV.A02(this.totalCount, str);
        }
        if ((j & 385) != 0) {
            this.totalCount.setVisibility(i2);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((MessengerReactionsPillViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((MessengerReactionsPillViewModel) obj);
        return true;
    }

    public MessengerReactionsPillBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 5, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public MessengerReactionsPillBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 1, (ImageView) objArr[1], (LinearLayout) objArr[0], (ImageView) objArr[2], (ImageView) objArr[3], (OCTextView) objArr[4]);
        this.mDirtyFlags = -1;
        this.firstReaction.setTag(null);
        this.reactionsPill.setTag(null);
        this.secondReaction.setTag(null);
        this.thirdReaction.setTag(null);
        this.totalCount.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
