package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.flexbox.FlexboxLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.PeopleQuickMessagePopupViewModel;
import com.oculus.socialplatform.R;

public class PeopleQuickMessagePopupBindingImpl extends PeopleQuickMessagePopupBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(PeopleQuickMessagePopupViewModel peopleQuickMessagePopupViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 23) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != 74) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
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
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.people.databinding.PeopleQuickMessagePopupBinding
    public void setViewModel(@Nullable PeopleQuickMessagePopupViewModel peopleQuickMessagePopupViewModel) {
        updateRegistration(0, peopleQuickMessagePopupViewModel);
        this.mViewModel = peopleQuickMessagePopupViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.context_menu_title, 10);
        sparseIntArray.put(R.id.quick_message_wrapper, 11);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0132, code lost:
        if (r8 != false) goto L_0x0030;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 335
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.databinding.PeopleQuickMessagePopupBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((PeopleQuickMessagePopupViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((PeopleQuickMessagePopupViewModel) obj);
        return true;
    }

    public PeopleQuickMessagePopupBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 12, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleQuickMessagePopupBindingImpl(AbstractC003408r r19, View view, Object[] objArr) {
        super(r19, view, 1, (ConstraintLayout) objArr[0], (OCTextView) objArr[10], (OCButton) objArr[5], (OCSpinner) objArr[8], (View) objArr[7], (OCTextView) objArr[9], (OCButton) objArr[1], (OCButton) objArr[2], (OCButton) objArr[3], (OCButton) objArr[4], (FlexboxLayout) objArr[11], (OCTextView) objArr[6]);
        this.mDirtyFlags = -1;
        this.container.setTag(null);
        this.ctaButton.setTag(null);
        this.ctaLoadingSpinner.setTag(null);
        this.loadingContainer.setTag(null);
        this.loadingText.setTag(null);
        this.quickMessage1.setTag(null);
        this.quickMessage2.setTag(null);
        this.quickMessage3.setTag(null);
        this.quickMessage4.setTag(null);
        this.successText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
