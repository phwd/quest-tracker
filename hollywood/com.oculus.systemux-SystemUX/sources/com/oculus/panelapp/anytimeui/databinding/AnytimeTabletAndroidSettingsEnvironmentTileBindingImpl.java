package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.library.model.App;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryAppTileFooter;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsEnvironmentTile;

public class AnytimeTabletAndroidSettingsEnvironmentTileBindingImpl extends AnytimeTabletAndroidSettingsEnvironmentTileBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.image_view, 10);
        sViewsWithIds.put(R.id.button, 11);
    }

    public AnytimeTabletAndroidSettingsEnvironmentTileBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletAndroidSettingsEnvironmentTileBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[1], (OCButton) objArr[11], (SettingsEnvironmentTile) objArr[0], (ImageView) objArr[3], (OCTextView) objArr[4], (LibraryAppTileFooter) objArr[5], (View) objArr[2], (SimpleDraweeView) objArr[10], (View) objArr[9], (ProgressBar) objArr[8], (OCTextView) objArr[7], (OCTextView) objArr[6]);
        this.mDirtyFlags = -1;
        this.attentionBadge.setTag(null);
        this.cardView.setTag(null);
        this.ctaIcon.setTag(null);
        this.ctaText.setTag(null);
        this.footerBackground.setTag(null);
        this.hoverOverlayBackground.setTag(null);
        this.outline.setTag(null);
        this.progressBar.setTag(null);
        this.subtitle.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (BR.environment == i) {
            setEnvironment((App) obj);
        } else if (BR.isHovered == i) {
            setIsHovered(((Boolean) obj).booleanValue());
        } else if (BR.imageAvailable != i) {
            return false;
        } else {
            setImageAvailable(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsEnvironmentTileBinding
    public void setEnvironment(@Nullable App app) {
        this.mEnvironment = app;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.environment);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsEnvironmentTileBinding
    public void setIsHovered(boolean z) {
        this.mIsHovered = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.isHovered);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsEnvironmentTileBinding
    public void setImageAvailable(boolean z) {
        this.mImageAvailable = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.imageAvailable);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0226  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x014c  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 594
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsEnvironmentTileBindingImpl.executeBindings():void");
    }
}
