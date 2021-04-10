package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.SeekBarBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCSlider;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;

public class SettingsSliderActionViewBindingImpl extends SettingsSliderActionViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public SettingsSliderActionViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 5, sIncludes, sViewsWithIds));
    }

    private SettingsSliderActionViewBindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr, Object[] objArr) {
        super(dataBindingComponent, viewArr[0], 1, (ImageView) objArr[4], (OCTextView) objArr[3], (ImageView) objArr[0], (OCTextView) objArr[1], (OCSlider) objArr[2]);
        this.mDirtyFlags = -1;
        this.maxIcon.setTag(null);
        this.maxLabel.setTag(null);
        this.minIcon.setTag(null);
        this.minLabel.setTag(null);
        this.slider.setTag(null);
        setRootTag(viewArr);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (BR.sliderAction != i) {
            return false;
        }
        setSliderAction((SettingsSliderActionType) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.SettingsSliderActionViewBinding
    public void setSliderAction(@Nullable SettingsSliderActionType settingsSliderActionType) {
        updateRegistration(0, settingsSliderActionType);
        this.mSliderAction = settingsSliderActionType;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.sliderAction);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeSliderAction((SettingsSliderActionType) obj, i2);
    }

    private boolean onChangeSliderAction(SettingsSliderActionType settingsSliderActionType, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != BR.progress) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        Drawable drawable;
        Drawable drawable2;
        int i5;
        String str;
        int i6;
        int i7;
        String str2;
        String str3;
        String str4;
        int i8;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SettingsSliderActionType settingsSliderActionType = this.mSliderAction;
        int i9 = ((7 & j) > 0 ? 1 : ((7 & j) == 0 ? 0 : -1));
        int i10 = 0;
        if (i9 != 0) {
            if ((j & 5) == 0 || settingsSliderActionType == null) {
                drawable2 = null;
                drawable = null;
                str4 = null;
                str3 = null;
                i6 = 0;
                i5 = 0;
                i4 = 0;
                i3 = 0;
                i8 = 0;
            } else {
                i6 = settingsSliderActionType.getMaxIconVisibility();
                i5 = settingsSliderActionType.getMinIconVisibility();
                drawable2 = settingsSliderActionType.getMaxIcon();
                drawable = settingsSliderActionType.getMinIcon();
                i4 = settingsSliderActionType.getMaxLabelVisibility();
                i3 = settingsSliderActionType.getMinLabelVisibility();
                i8 = settingsSliderActionType.getMaxProgress();
                str4 = settingsSliderActionType.getMaxLabel();
                str3 = settingsSliderActionType.getMinLabel();
            }
            if (settingsSliderActionType != null) {
                i10 = settingsSliderActionType.getProgress();
            }
            i = i10;
            str2 = str4;
            str = str3;
            i2 = i9;
            i7 = i8;
        } else {
            i2 = i9;
            str2 = null;
            drawable2 = null;
            drawable = null;
            i7 = 0;
            i5 = 0;
            i4 = 0;
            i3 = 0;
            i = 0;
            i6 = 0;
            str = null;
        }
        if ((j & 5) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.maxIcon, drawable2);
            this.maxIcon.setVisibility(i6);
            TextViewBindingAdapter.setText(this.maxLabel, str2);
            this.maxLabel.setVisibility(i4);
            ImageViewBindingAdapter.setImageDrawable(this.minIcon, drawable);
            this.minIcon.setVisibility(i5);
            TextViewBindingAdapter.setText(this.minLabel, str);
            this.minLabel.setVisibility(i3);
            this.slider.setMax(i7);
        }
        if (i2 != 0) {
            SeekBarBindingAdapter.setProgress(this.slider, i);
        }
    }
}
