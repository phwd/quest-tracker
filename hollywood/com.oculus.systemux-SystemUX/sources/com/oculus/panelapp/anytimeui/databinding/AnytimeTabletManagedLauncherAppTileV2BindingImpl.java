package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletManagedLauncherAppTileV2BindingImpl extends AnytimeTabletManagedLauncherAppTileV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final CardView mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.app_tile_button, 9);
    }

    public AnytimeTabletManagedLauncherAppTileV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletManagedLauncherAppTileV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ShellButton) objArr[9], (ImageView) objArr[4], (OCTextView) objArr[5], (View) objArr[6], (View) objArr[3], (ImageView) objArr[1], (SimpleDraweeView) objArr[2], (View) objArr[8], (OCTextView) objArr[7]);
        this.mDirtyFlags = -1;
        this.ctaIcon.setTag(null);
        this.ctaText.setTag(null);
        this.footerBackground.setTag(null);
        this.hoverOverlayBackground.setTag(null);
        this.icon.setTag(null);
        this.imageView.setTag(null);
        this.mboundView0 = (CardView) objArr[0];
        this.mboundView0.setTag(null);
        this.outline.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (BR.item == i) {
            setItem((ManagedLauncherItem) obj);
        } else if (BR.displayName == i) {
            setDisplayName((String) obj);
        } else if (BR.isHovered == i) {
            setIsHovered(((Boolean) obj).booleanValue());
        } else if (BR.imageAvailable != i) {
            return false;
        } else {
            setImageAvailable(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherAppTileV2Binding
    public void setItem(@Nullable ManagedLauncherItem managedLauncherItem) {
        this.mItem = managedLauncherItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherAppTileV2Binding
    public void setDisplayName(@Nullable String str) {
        this.mDisplayName = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.displayName);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherAppTileV2Binding
    public void setIsHovered(boolean z) {
        this.mIsHovered = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.isHovered);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherAppTileV2Binding
    public void setImageAvailable(boolean z) {
        this.mImageAvailable = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.imageAvailable);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 279
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherAppTileV2BindingImpl.executeBindings():void");
    }
}
