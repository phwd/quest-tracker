package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.library.model.App;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCHighlight;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;

public class AnytimeTabletLibrarySystemAppButtonV2BindingImpl extends AnytimeTabletLibrarySystemAppButtonV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.button, 6);
    }

    public AnytimeTabletLibrarySystemAppButtonV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletLibrarySystemAppButtonV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCButton) objArr[6], (OCHighlight) objArr[2], (ImageView) objArr[1], (ProgressBar) objArr[3], (OCTextView) objArr[4], (View) objArr[5]);
        this.mDirtyFlags = -1;
        this.highlight.setTag(null);
        this.iconImage.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.progressBar.setTag(null);
        this.textView.setTag(null);
        this.updateDot.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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
        if (BR.systemApp == i) {
            setSystemApp((App) obj);
        } else if (BR.highlight == i) {
            setHighlight(((Boolean) obj).booleanValue());
        } else if (BR.text == i) {
            setText((String) obj);
        } else if (BR.coloredBackground == i) {
            setColoredBackground((Drawable) obj);
        } else if (BR.isHovered == i) {
            setIsHovered(((Boolean) obj).booleanValue());
        } else if (BR.icon != i) {
            return false;
        } else {
            setIcon((Drawable) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding
    public void setSystemApp(@Nullable App app) {
        this.mSystemApp = app;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.systemApp);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding
    public void setHighlight(boolean z) {
        this.mHighlight = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.highlight);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding
    public void setText(@Nullable String str) {
        this.mText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.text);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding
    public void setColoredBackground(@Nullable Drawable drawable) {
        this.mColoredBackground = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.coloredBackground);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding
    public void setIsHovered(boolean z) {
        this.mIsHovered = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.isHovered);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.icon);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:132:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x013a  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 463
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2BindingImpl.executeBindings():void");
    }
}
