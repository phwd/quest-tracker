package com.oculus.panelapp.anytimeui.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.BindingUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherView;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem;
import java.util.List;

public class AnytimeTabletManagedLauncherViewV2BindingImpl extends AnytimeTabletManagedLauncherViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ManagedLauncherView mboundView0;

    static {
        sViewsWithIds.put(R.id.managed_launcher_tablet_title, 3);
        sViewsWithIds.put(R.id.back_to_top_button_height_layout, 4);
        sViewsWithIds.put(R.id.back_to_top_icon, 5);
        sViewsWithIds.put(R.id.back_to_top_text, 6);
        sViewsWithIds.put(R.id.back_to_top_button, 7);
    }

    public AnytimeTabletManagedLauncherViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletManagedLauncherViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCButton) objArr[7], (LinearLayout) objArr[4], (ImageView) objArr[5], (OCTextView) objArr[6], (OCRecyclerView) objArr[2], (OCTextView) objArr[1], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.managedLauncherContent.setTag(null);
        this.managedLauncherTabletNoApps.setTag(null);
        this.mboundView0 = (ManagedLauncherView) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        setViewModel((ManagedLauncherViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherViewV2Binding
    public void setViewModel(@Nullable ManagedLauncherViewModel managedLauncherViewModel) {
        updateRegistration(0, managedLauncherViewModel);
        this.mViewModel = managedLauncherViewModel;
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
        return onChangeViewModel((ManagedLauncherViewModel) obj, i2);
    }

    private boolean onChangeViewModel(ManagedLauncherViewModel managedLauncherViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.launcherActions) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.items) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.unknownSources) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.isHandTrackingEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.ingestedApps) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != BR.showUnknownSources) {
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ManagedLauncherViewModel managedLauncherViewModel = this.mViewModel;
        List<ManagedLauncherItem> list = null;
        int i3 = 0;
        if ((255 & j) != 0) {
            if (!((133 & j) == 0 || managedLauncherViewModel == null)) {
                managedLauncherViewModel.getIsHandTrackingEnabled();
            }
            if (!((193 & j) == 0 || managedLauncherViewModel == null)) {
                managedLauncherViewModel.getShowUnknownSources();
            }
            if (!((145 & j) == 0 || managedLauncherViewModel == null)) {
                managedLauncherViewModel.getIngestedApps();
            }
            if (!((161 & j) == 0 || managedLauncherViewModel == null)) {
                managedLauncherViewModel.getUnknownSources();
            }
            int i4 = ((j & 131) > 0 ? 1 : ((j & 131) == 0 ? 0 : -1));
            if (i4 != 0) {
                if (managedLauncherViewModel != null) {
                    list = managedLauncherViewModel.getItems();
                }
                if (list != null) {
                    i2 = list.size();
                } else {
                    i2 = 0;
                }
                boolean z = true;
                boolean z2 = i2 == 0;
                if (i2 <= 0) {
                    z = false;
                }
                if (i4 != 0) {
                    j |= z2 ? PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : 1024;
                }
                if ((j & 131) != 0) {
                    j |= z ? 512 : 256;
                }
                int i5 = 8;
                i = z2 ? 0 : 8;
                if (z) {
                    i5 = 0;
                }
                i3 = i5;
            } else {
                i = 0;
            }
            if (!((137 & j) == 0 || managedLauncherViewModel == null)) {
                managedLauncherViewModel.getLauncherActions();
            }
        } else {
            i = 0;
        }
        if ((j & 131) != 0) {
            this.managedLauncherContent.setVisibility(i3);
            BindingUtils.updateItems(this.managedLauncherContent, list);
            this.managedLauncherTabletNoApps.setVisibility(i);
        }
    }
}
