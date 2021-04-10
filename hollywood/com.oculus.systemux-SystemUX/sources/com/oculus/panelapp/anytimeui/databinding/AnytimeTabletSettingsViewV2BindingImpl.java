package com.oculus.panelapp.anytimeui.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCNotchedSlider;
import com.oculus.ocui.OCTileButton;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ToggleButton;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsView;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.tablet.R;
import com.oculus.tablet.databinding.OsigSeekbarBinding;

public class AnytimeTabletSettingsViewV2BindingImpl extends AnytimeTabletSettingsViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(14);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final SettingsView mboundView0;

    static {
        sIncludes.setIncludes(0, new String[]{"osig_seekbar", "osig_seekbar"}, new int[]{9, 10}, new int[]{R.layout.osig_seekbar, R.layout.osig_seekbar});
        sViewsWithIds.put(com.oculus.panelapp.anytimeui.R.id.button_night_shift, 11);
        sViewsWithIds.put(com.oculus.panelapp.anytimeui.R.id.button_reset_view, 12);
        sViewsWithIds.put(com.oculus.panelapp.anytimeui.R.id.button_microphone, 13);
    }

    public AnytimeTabletSettingsViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSettingsViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, (ToggleButton) objArr[6], (ToggleButton) objArr[8], (ToggleButton) objArr[5], (OCTileButton) objArr[4], (OCTileButton) objArr[3], (ToggleButton) objArr[7], (ToggleButton) objArr[13], (ToggleButton) objArr[11], (ToggleButton) objArr[12], (OCTileButton) objArr[2], (OsigSeekbarBinding) objArr[10], (OsigSeekbarBinding) objArr[9], (OCNotchedSlider) objArr[1]);
        this.mDirtyFlags = -1;
        this.buttonActivateAssistant.setTag(null);
        this.buttonBugReport.setTag(null);
        this.buttonDoNotDisturb.setTag(null);
        this.buttonEnableLink.setTag(null);
        this.buttonGuardian.setTag(null);
        this.buttonHands.setTag(null);
        this.buttonWifi.setTag(null);
        this.mboundView0 = (SettingsView) objArr[0];
        this.mboundView0.setTag(null);
        this.sliderRealityTuner.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32768;
        }
        this.seekbarVolume.invalidateAll();
        this.seekbarBrightness.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.seekbarBrightness.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.seekbarVolume.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            com.oculus.tablet.databinding.OsigSeekbarBinding r0 = r4.seekbarVolume
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.tablet.databinding.OsigSeekbarBinding r0 = r4.seekbarBrightness
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsViewV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.settingsViewModel == i) {
            setSettingsViewModel((SettingsViewModel) obj);
        } else if (BR.statusViewModel != i) {
            return false;
        } else {
            setStatusViewModel((StatusViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsViewV2Binding
    public void setSettingsViewModel(@Nullable SettingsViewModel settingsViewModel) {
        updateRegistration(1, settingsViewModel);
        this.mSettingsViewModel = settingsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.settingsViewModel);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsViewV2Binding
    public void setStatusViewModel(@Nullable StatusViewModel statusViewModel) {
        updateRegistration(2, statusViewModel);
        this.mStatusViewModel = statusViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.statusViewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.seekbarVolume.setLifecycleOwner(lifecycleOwner);
        this.seekbarBrightness.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeSeekbarBrightness((OsigSeekbarBinding) obj, i2);
        }
        if (i == 1) {
            return onChangeSettingsViewModel((SettingsViewModel) obj, i2);
        }
        if (i == 2) {
            return onChangeStatusViewModel((StatusViewModel) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeSeekbarVolume((OsigSeekbarBinding) obj, i2);
    }

    private boolean onChangeSeekbarBrightness(OsigSeekbarBinding osigSeekbarBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeSettingsViewModel(SettingsViewModel settingsViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.realityTunerValue) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.realityTunerSliderVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.volumeIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == BR.brightness) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == BR.brightnessSeekbarVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == BR.brightnessIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == BR.guardianIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == BR.guardianCTA) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i != BR.handsButtonVisible) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        }
    }

    private boolean onChangeStatusViewModel(StatusViewModel statusViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.wifiButtonIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i != BR.wifiButtonCTA) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
    }

    private boolean onChangeSeekbarVolume(OsigSeekbarBinding osigSeekbarBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0177 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0186 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0153  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 749
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsViewV2BindingImpl.executeBindings():void");
    }
}
