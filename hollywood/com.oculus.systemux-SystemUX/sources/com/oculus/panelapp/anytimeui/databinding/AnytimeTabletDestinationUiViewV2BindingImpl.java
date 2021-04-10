package com.oculus.panelapp.anytimeui.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.systrace.Systrace;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIView;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel;
import com.oculus.tablet.R;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletDestinationUiViewV2BindingImpl extends AnytimeTabletDestinationUiViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(20);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final DestinationUIView mboundView0;

    static {
        sIncludes.setIncludes(0, new String[]{"osig_button_borderless", "osig_button_borderless", "osig_button_borderless"}, new int[]{13, 14, 15}, new int[]{R.layout.osig_button_borderless, R.layout.osig_button_borderless, R.layout.osig_button_borderless});
        sIncludes.setIncludes(7, new String[]{"osig_button_borderless"}, new int[]{10}, new int[]{R.layout.osig_button_borderless});
        sIncludes.setIncludes(8, new String[]{"osig_button_borderless"}, new int[]{11}, new int[]{R.layout.osig_button_borderless});
        sIncludes.setIncludes(9, new String[]{"osig_button_borderless"}, new int[]{12}, new int[]{R.layout.osig_button_borderless});
        sViewsWithIds.put(com.oculus.panelapp.anytimeui.R.id.app_detail_row, 16);
        sViewsWithIds.put(com.oculus.panelapp.anytimeui.R.id.resume_button, 17);
        sViewsWithIds.put(com.oculus.panelapp.anytimeui.R.id.left_guideline, 18);
        sViewsWithIds.put(com.oculus.panelapp.anytimeui.R.id.right_guideline, 19);
    }

    public AnytimeTabletDestinationUiViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletDestinationUiViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 8, (ConstraintLayout) objArr[16], (SimpleDraweeView) objArr[1], (ImageView) objArr[2], (OCTextView) objArr[4], (OsigButtonBorderlessBinding) objArr[13], (OsigButtonBorderlessBinding) objArr[14], (Guideline) objArr[18], (OsigButtonBorderlessBinding) objArr[12], (LinearLayout) objArr[9], (OCTextView) objArr[3], (ShellButton) objArr[5], (OsigButtonBorderlessBinding) objArr[15], (ShellButton) objArr[6], (ShellButton) objArr[17], (Guideline) objArr[19], (OsigButtonBorderlessBinding) objArr[10], (LinearLayout) objArr[7], (OsigButtonBorderlessBinding) objArr[11], (LinearLayout) objArr[8]);
        this.mDirtyFlags = -1;
        this.appImage.setTag(null);
        this.appScreenshot.setTag(null);
        this.appTitle.setTag(null);
        this.livestreamButtonContainer.setTag(null);
        this.mboundView0 = (DestinationUIView) objArr[0];
        this.mboundView0.setTag(null);
        this.parentAppTitle.setTag(null);
        this.quitButton.setTag(null);
        this.restartButton.setTag(null);
        this.screenrecordingButtonContainer.setTag(null);
        this.screenshotButtonContainer.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = Systrace.TRACE_TAG_COMPONENTS;
        }
        this.screenrecordingButton.invalidateAll();
        this.screenshotButton.invalidateAll();
        this.livestreamButton.invalidateAll();
        this.castingButton.invalidateAll();
        this.inviteButton.invalidateAll();
        this.reportButton.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.screenshotButton.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.livestreamButton.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.castingButton.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r4.inviteButton.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r4.reportButton.hasPendingBindings() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.screenrecordingButton.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0045 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0045 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0045 }
            com.oculus.tablet.databinding.OsigButtonBorderlessBinding r0 = r4.screenrecordingButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.tablet.databinding.OsigButtonBorderlessBinding r0 = r4.screenshotButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.tablet.databinding.OsigButtonBorderlessBinding r0 = r4.livestreamButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            com.oculus.tablet.databinding.OsigButtonBorderlessBinding r0 = r4.castingButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r1
        L_0x0031:
            com.oculus.tablet.databinding.OsigButtonBorderlessBinding r0 = r4.inviteButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x003a
            return r1
        L_0x003a:
            com.oculus.tablet.databinding.OsigButtonBorderlessBinding r0 = r4.reportButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0043
            return r1
        L_0x0043:
            r0 = 0
            return r0
        L_0x0045:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDestinationUiViewV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.sharingViewModel == i) {
            setSharingViewModel((SharingViewModel) obj);
        } else if (BR.destinationUIViewModel != i) {
            return false;
        } else {
            setDestinationUIViewModel((DestinationUIViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDestinationUiViewV2Binding
    public void setSharingViewModel(@Nullable SharingViewModel sharingViewModel) {
        updateRegistration(0, sharingViewModel);
        this.mSharingViewModel = sharingViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.sharingViewModel);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDestinationUiViewV2Binding
    public void setDestinationUIViewModel(@Nullable DestinationUIViewModel destinationUIViewModel) {
        updateRegistration(2, destinationUIViewModel);
        this.mDestinationUIViewModel = destinationUIViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.destinationUIViewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.screenrecordingButton.setLifecycleOwner(lifecycleOwner);
        this.screenshotButton.setLifecycleOwner(lifecycleOwner);
        this.livestreamButton.setLifecycleOwner(lifecycleOwner);
        this.castingButton.setLifecycleOwner(lifecycleOwner);
        this.inviteButton.setLifecycleOwner(lifecycleOwner);
        this.reportButton.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeSharingViewModel((SharingViewModel) obj, i2);
            case 1:
                return onChangeScreenrecordingButton((OsigButtonBorderlessBinding) obj, i2);
            case 2:
                return onChangeDestinationUIViewModel((DestinationUIViewModel) obj, i2);
            case 3:
                return onChangeInviteButton((OsigButtonBorderlessBinding) obj, i2);
            case 4:
                return onChangeCastingButton((OsigButtonBorderlessBinding) obj, i2);
            case 5:
                return onChangeLivestreamButton((OsigButtonBorderlessBinding) obj, i2);
            case 6:
                return onChangeScreenshotButton((OsigButtonBorderlessBinding) obj, i2);
            case 7:
                return onChangeReportButton((OsigButtonBorderlessBinding) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeSharingViewModel(SharingViewModel sharingViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.videoRecordingButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == BR.recordingIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == BR.recordingText) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == BR.captureAllowed) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.liveStreamAllowed) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        } else if (i == BR.liveStreamText) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == BR.castingText) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i != BR.inviteEnabled) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        }
    }

    private boolean onChangeScreenrecordingButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDestinationUIViewModel(DestinationUIViewModel destinationUIViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.heroImage) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        } else if (i == BR.gameScreenshot) {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        } else if (i == BR.isScreenshotVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 262144;
            }
            return true;
        } else if (i == BR.parentAppName) {
            synchronized (this) {
                this.mDirtyFlags |= 524288;
            }
            return true;
        } else if (i == BR.appName) {
            synchronized (this) {
                this.mDirtyFlags |= 1048576;
            }
            return true;
        } else if (i != BR.quitButtonText) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2097152;
            }
            return true;
        }
    }

    private boolean onChangeInviteButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeCastingButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeLivestreamButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeScreenshotButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeReportButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0237  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 1146
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDestinationUiViewV2BindingImpl.executeBindings():void");
    }
}
