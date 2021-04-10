package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorViewModel;
import com.oculus.socialplatform.R;

public class ErrorViewBindingImpl extends ErrorViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final ErrorView mboundView0;

    private boolean onChangeErrorViewModel(ErrorViewModel errorViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 67) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != 156) {
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
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ErrorViewModel errorViewModel = this.mErrorViewModel;
        String str2 = null;
        if ((15 & j) != 0) {
            if ((j & 13) == 0 || errorViewModel == null) {
                str = null;
            } else {
                str = errorViewModel.mMessage;
            }
            if (!((j & 11) == 0 || errorViewModel == null)) {
                str2 = errorViewModel.mTitle;
            }
        } else {
            str = null;
        }
        if ((13 & j) != 0) {
            C11051qV.A02(this.errorViewMessage, str);
        }
        if ((j & 11) != 0) {
            C11051qV.A02(this.errorViewTitle, str2);
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

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.ErrorViewBinding
    public void setErrorViewModel(@Nullable ErrorViewModel errorViewModel) {
        updateRegistration(0, errorViewModel);
        this.mErrorViewModel = errorViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(221);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.error_view_secondary_button, 3);
        sparseIntArray.put(R.id.error_view_primary_secondary_button, 4);
        sparseIntArray.put(R.id.error_view_primary_button, 5);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeErrorViewModel((ErrorViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (221 != i) {
            return false;
        }
        setErrorViewModel((ErrorViewModel) obj);
        return true;
    }

    public ErrorViewBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 6, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public ErrorViewBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 1, (OCTextView) objArr[2], (OCButton) objArr[5], (OCButton) objArr[4], (OCButton) objArr[3], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.errorViewMessage.setTag(null);
        this.errorViewTitle.setTag(null);
        ErrorView errorView = (ErrorView) objArr[0];
        this.mboundView0 = errorView;
        errorView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
