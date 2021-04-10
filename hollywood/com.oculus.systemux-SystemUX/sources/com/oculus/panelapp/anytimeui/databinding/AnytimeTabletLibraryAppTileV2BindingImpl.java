package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.library.model.App;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryAppTileFooter;

public class AnytimeTabletLibraryAppTileV2BindingImpl extends AnytimeTabletLibraryAppTileV2Binding {
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
        sViewsWithIds.put(R.id.icon, 14);
        sViewsWithIds.put(R.id.image_view, 15);
        sViewsWithIds.put(R.id.button, 16);
    }

    public AnytimeTabletLibraryAppTileV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletLibraryAppTileV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[3], (View) objArr[4], (OCButton) objArr[16], (CardView) objArr[0], (OCButton) objArr[11], (ImageView) objArr[6], (OCTextView) objArr[7], (LibraryAppTileFooter) objArr[8], (View) objArr[5], (ImageView) objArr[14], (ImageView) objArr[2], (SimpleDraweeView) objArr[15], (View) objArr[13], (ProgressBar) objArr[12], (OCTextView) objArr[10], (OCTextView) objArr[1], (OCTextView) objArr[9]);
        this.mDirtyFlags = -1;
        this.attentionBadge.setTag(null);
        this.blockingBackground.setTag(null);
        this.cardView.setTag(null);
        this.contextMenuButton.setTag(null);
        this.ctaIcon.setTag(null);
        this.ctaText.setTag(null);
        this.footerBackground.setTag(null);
        this.hoverOverlayBackground.setTag(null);
        this.imageBadge.setTag(null);
        this.outline.setTag(null);
        this.progressBar.setTag(null);
        this.subtitle.setTag(null);
        this.textBadge.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
        if (BR.isTrackingEnabled == i) {
            setIsTrackingEnabled(((Boolean) obj).booleanValue());
        } else if (BR.isPrototype == i) {
            setIsPrototype(((Boolean) obj).booleanValue());
        } else if (BR.isInternetConnected == i) {
            setIsInternetConnected(((Boolean) obj).booleanValue());
        } else if (BR.isContextMenuButtonHovered == i) {
            setIsContextMenuButtonHovered(((Boolean) obj).booleanValue());
        } else if (BR.position == i) {
            setPosition(((Integer) obj).intValue());
        } else if (BR.app == i) {
            setApp((App) obj);
        } else if (BR.isHovered == i) {
            setIsHovered(((Boolean) obj).booleanValue());
        } else if (BR.iconDrawable == i) {
            setIconDrawable((Drawable) obj);
        } else if (BR.panelApp == i) {
            setPanelApp((AnytimeUIPanelAppBase) obj);
        } else if (BR.imageAvailable == i) {
            setImageAvailable(((Boolean) obj).booleanValue());
        } else if (BR.hasContextMenu != i) {
            return false;
        } else {
            setHasContextMenu(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setIsTrackingEnabled(boolean z) {
        this.mIsTrackingEnabled = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isTrackingEnabled);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setIsPrototype(boolean z) {
        this.mIsPrototype = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.isPrototype);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setIsInternetConnected(boolean z) {
        this.mIsInternetConnected = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.isInternetConnected);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setIsContextMenuButtonHovered(boolean z) {
        this.mIsContextMenuButtonHovered = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.isContextMenuButtonHovered);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setPosition(int i) {
        this.mPosition = i;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setApp(@Nullable App app) {
        this.mApp = app;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.app);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setIsHovered(boolean z) {
        this.mIsHovered = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.isHovered);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setIconDrawable(@Nullable Drawable drawable) {
        this.mIconDrawable = drawable;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setPanelApp(@Nullable AnytimeUIPanelAppBase anytimeUIPanelAppBase) {
        this.mPanelApp = anytimeUIPanelAppBase;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.panelApp);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setImageAvailable(boolean z) {
        this.mImageAvailable = z;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.imageAvailable);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding
    public void setHasContextMenu(boolean z) {
        this.mHasContextMenu = z;
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        notifyPropertyChanged(BR.hasContextMenu);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x026f  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x02ca  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0305  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x033a  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x0352  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0367  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x03a1  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x03ac  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x03bb  */
    /* JADX WARNING: Removed duplicated region for block: B:230:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b8  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 966
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2BindingImpl.executeBindings():void");
    }
}
