package com.oculus.panelapp.bugreporter.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCCheckbox;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCScrollView;
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.bugreporter.BR;
import com.oculus.panelapp.bugreporter.DescriptionView;
import com.oculus.panelapp.bugreporter.DescriptionViewModel;
import com.oculus.panelapp.bugreporter.R;
import com.oculus.panelapp.bugreporter.common.TextInputView;

public class BugReportDescriptionViewBindingImpl extends BugReportDescriptionViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private InverseBindingListener checkboxandroidCheckedAttrChanged;
    private InverseBindingListener descriptionandroidTextAttrChanged;
    private long mDirtyFlags;
    @NonNull
    private final DescriptionView mboundView0;

    static {
        sViewsWithIds.put(R.id.title, 8);
        sViewsWithIds.put(R.id.scrollview, 9);
        sViewsWithIds.put(R.id.category_label, 10);
        sViewsWithIds.put(R.id.category_selector, 11);
        sViewsWithIds.put(R.id.description_label, 12);
        sViewsWithIds.put(R.id.support_text, 13);
        sViewsWithIds.put(R.id.cancel_button, 14);
    }

    public BugReportDescriptionViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }

    private BugReportDescriptionViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCButton) objArr[14], (OCTextView) objArr[10], (OCSelect) objArr[11], (OCCheckbox) objArr[4], (OCTextView) objArr[5], (OCButton) objArr[7], (TextInputView) objArr[1], (OCTextView) objArr[2], (OCTextView) objArr[12], (ImageView) objArr[6], (ImageView) objArr[3], (OCScrollView) objArr[9], (OCLink) objArr[13], (OCTextView) objArr[8]);
        this.checkboxandroidCheckedAttrChanged = new InverseBindingListener() {
            /* class com.oculus.panelapp.bugreporter.databinding.BugReportDescriptionViewBindingImpl.AnonymousClass1 */

            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = BugReportDescriptionViewBindingImpl.this.checkbox.isChecked();
                DescriptionViewModel descriptionViewModel = BugReportDescriptionViewBindingImpl.this.mViewModel;
                if (descriptionViewModel != null) {
                    descriptionViewModel.setIncludeScreenshot(isChecked);
                }
            }
        };
        this.descriptionandroidTextAttrChanged = new InverseBindingListener() {
            /* class com.oculus.panelapp.bugreporter.databinding.BugReportDescriptionViewBindingImpl.AnonymousClass2 */

            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(BugReportDescriptionViewBindingImpl.this.description);
                DescriptionViewModel descriptionViewModel = BugReportDescriptionViewBindingImpl.this.mViewModel;
                if (descriptionViewModel != null) {
                    descriptionViewModel.setDescriptionText(textString);
                }
            }
        };
        this.mDirtyFlags = -1;
        this.checkbox.setTag(null);
        this.checkboxLabel.setTag(null);
        this.continueButton.setTag(null);
        this.description.setTag(null);
        this.descriptionHint.setTag(null);
        this.mboundView0 = (DescriptionView) objArr[0];
        this.mboundView0.setTag(null);
        this.preselectedPhoto.setTag(null);
        this.screenshot.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
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
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((DescriptionViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.bugreporter.databinding.BugReportDescriptionViewBinding
    public void setViewModel(@Nullable DescriptionViewModel descriptionViewModel) {
        updateRegistration(0, descriptionViewModel);
        this.mViewModel = descriptionViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((DescriptionViewModel) obj, i2);
    }

    private boolean onChangeViewModel(DescriptionViewModel descriptionViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.descriptionText) {
            synchronized (this) {
                this.mDirtyFlags |= 66;
            }
            return true;
        } else if (i == BR.screenshot) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.hasPreselectedPhoto) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == BR.includeScreenshot) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.continueButtonText) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.continueButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i != BR.bugCategory) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 368
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.bugreporter.databinding.BugReportDescriptionViewBindingImpl.executeBindings():void");
    }
}
