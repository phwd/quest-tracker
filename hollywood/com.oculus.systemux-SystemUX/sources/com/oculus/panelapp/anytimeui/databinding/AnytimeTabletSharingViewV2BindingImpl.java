package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.common.quickpromotion.databinding.QpTooltipViewBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCTileButton;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.BindingUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingView;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSharingViewV2BindingImpl extends AnytimeTabletSharingViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(16);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final SharingView mboundView0;

    static {
        sIncludes.setIncludes(0, new String[]{"anytime_tablet_loading_dots_v2", "qp_tooltip_view", "qp_tooltip_view"}, new int[]{8, 9, 10}, new int[]{R.layout.anytime_tablet_loading_dots_v2, com.oculus.common.quickpromotion.R.layout.qp_tooltip_view, com.oculus.common.quickpromotion.R.layout.qp_tooltip_view});
        sViewsWithIds.put(R.id.title, 11);
        sViewsWithIds.put(R.id.recent_title, 12);
        sViewsWithIds.put(R.id.recents_null_state, 13);
        sViewsWithIds.put(R.id.recent_capture_tiles_row, 14);
        sViewsWithIds.put(R.id.camerarollButton, 15);
    }

    public AnytimeTabletSharingViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSharingViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, (QpTooltipViewBinding) objArr[9], (ShellButton) objArr[15], (OCTileButton) objArr[7], (QpTooltipViewBinding) objArr[10], (OCTileButton) objArr[6], (LinearLayout) objArr[5], (AnytimeTabletLoadingDotsV2Binding) objArr[8], (LinearLayout) objArr[14], (OCTextView) objArr[12], (FrameLayout) objArr[13], (OCTileButton) objArr[2], (LinearLayout) objArr[1], (OCTileButton) objArr[4], (LinearLayout) objArr[3], (OCTextView) objArr[11]);
        this.mDirtyFlags = -1;
        this.castingButton.setTag(null);
        this.livestreamButton.setTag(null);
        this.livestreamButtonContainer.setTag(null);
        this.mboundView0 = (SharingView) objArr[0];
        this.mboundView0.setTag(null);
        this.screenrecordingButton.setTag(null);
        this.screenrecordingButtonContainer.setTag(null);
        this.screenshotButton.setTag(null);
        this.screenshotButtonContainer.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        this.loadingDots.invalidateAll();
        this.cameraRollQpTooltip.invalidateAll();
        this.castingQpTooltip.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.cameraRollQpTooltip.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.castingQpTooltip.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.loadingDots.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x002a }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x002a }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x002a }
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLoadingDotsV2Binding r0 = r4.loadingDots
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.common.quickpromotion.databinding.QpTooltipViewBinding r0 = r4.cameraRollQpTooltip
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.common.quickpromotion.databinding.QpTooltipViewBinding r0 = r4.castingQpTooltip
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            r0 = 0
            return r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSharingViewV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((SharingViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSharingViewV2Binding
    public void setViewModel(@Nullable SharingViewModel sharingViewModel) {
        updateRegistration(3, sharingViewModel);
        this.mViewModel = sharingViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.loadingDots.setLifecycleOwner(lifecycleOwner);
        this.cameraRollQpTooltip.setLifecycleOwner(lifecycleOwner);
        this.castingQpTooltip.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeLoadingDots((AnytimeTabletLoadingDotsV2Binding) obj, i2);
        }
        if (i == 1) {
            return onChangeCastingQpTooltip((QpTooltipViewBinding) obj, i2);
        }
        if (i == 2) {
            return onChangeCameraRollQpTooltip((QpTooltipViewBinding) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeViewModel((SharingViewModel) obj, i2);
    }

    private boolean onChangeLoadingDots(AnytimeTabletLoadingDotsV2Binding anytimeTabletLoadingDotsV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeCastingQpTooltip(QpTooltipViewBinding qpTooltipViewBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeCameraRollQpTooltip(QpTooltipViewBinding qpTooltipViewBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModel(SharingViewModel sharingViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == BR.videoRecordingButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.recordingIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.recordingText) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == BR.captureAllowed) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == BR.liveStreamAllowed) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == BR.liveStreamText) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i != BR.castingText) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        String str;
        Drawable drawable;
        boolean z2;
        String str2;
        float f;
        int i;
        boolean z3;
        String str3;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        float f2;
        int i8;
        boolean z4;
        int i9;
        boolean z5;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        float f3 = 0.0f;
        SharingViewModel sharingViewModel = this.mViewModel;
        String str4 = null;
        int i10 = 0;
        if ((4088 & j) != 0) {
            Drawable recordingIcon = ((j & 2088) == 0 || sharingViewModel == null) ? null : sharingViewModel.getRecordingIcon();
            boolean videoRecordingButtonEnabled = ((j & 2072) == 0 || sharingViewModel == null) ? false : sharingViewModel.getVideoRecordingButtonEnabled();
            String castingText = ((j & 3080) == 0 || sharingViewModel == null) ? null : sharingViewModel.getCastingText();
            String liveStreamText = ((j & 2568) == 0 || sharingViewModel == null) ? null : sharingViewModel.getLiveStreamText();
            boolean liveStreamAllowed = ((j & 2312) == 0 || sharingViewModel == null) ? false : sharingViewModel.getLiveStreamAllowed();
            boolean captureAllowed = ((j & 2184) == 0 || sharingViewModel == null) ? false : sharingViewModel.getCaptureAllowed();
            int i11 = ((j & 2056) > 0 ? 1 : ((j & 2056) == 0 ? 0 : -1));
            if (i11 != 0) {
                if (sharingViewModel != null) {
                    z5 = sharingViewModel.isLivestreamButtonVisible();
                    i9 = sharingViewModel.getButtonStartMargin();
                    z4 = sharingViewModel.isCastingButtonVisible();
                    i8 = sharingViewModel.getButtonAlignment();
                    f2 = sharingViewModel.getButtonHorizontalBias();
                } else {
                    f2 = 0.0f;
                    z5 = false;
                    i9 = 0;
                    z4 = false;
                    i8 = 0;
                }
                if (i11 != 0) {
                    j |= z5 ? 8192 : 4096;
                }
                if ((j & 2056) != 0) {
                    j |= z4 ? 32768 : 16384;
                }
                int i12 = 8;
                int i13 = z5 ? 0 : 8;
                if (z4) {
                    i12 = 0;
                }
                i5 = i9;
                i10 = i8;
                i6 = i12;
                i7 = i13;
                f3 = f2;
            } else {
                i7 = 0;
                i6 = 0;
                i5 = 0;
            }
            if (!((j & 2120) == 0 || sharingViewModel == null)) {
                str4 = sharingViewModel.getRecordingText();
            }
            f = f3;
            drawable = recordingIcon;
            str = str4;
            i = i10;
            z2 = videoRecordingButtonEnabled;
            str3 = castingText;
            str2 = liveStreamText;
            z3 = liveStreamAllowed;
            z = captureAllowed;
            i2 = i7;
            i4 = i6;
            i3 = i5;
        } else {
            f = 0.0f;
            str3 = null;
            str2 = null;
            drawable = null;
            str = null;
            i4 = 0;
            i3 = 0;
            i2 = 0;
            z3 = false;
            i = 0;
            z2 = false;
            z = false;
        }
        if ((j & 2056) != 0) {
            this.castingButton.setVisibility(i4);
            BindingUtils.setMarginStart(this.castingButton, i3);
            this.livestreamButtonContainer.setVisibility(i2);
            BindingUtils.setHorizontalChainStyle(this.screenrecordingButtonContainer, i);
            BindingUtils.setHorizontalBias(this.screenrecordingButtonContainer, f);
            BindingUtils.setMarginStart(this.screenshotButtonContainer, i3);
        }
        if ((j & 3080) != 0) {
            this.castingButton.setSubtitle(str3);
        }
        if ((j & 2312) != 0) {
            this.livestreamButton.setEnabled(z3);
        }
        if ((2568 & j) != 0) {
            this.livestreamButton.setSubtitle(str2);
        }
        if ((j & 2072) != 0) {
            this.screenrecordingButton.setEnabled(z2);
        }
        if ((j & 2088) != 0) {
            this.screenrecordingButton.setTitleIcon(drawable);
        }
        if ((2120 & j) != 0) {
            this.screenrecordingButton.setSubtitle(str);
        }
        if ((j & 2184) != 0) {
            this.screenshotButton.setEnabled(z);
        }
        executeBindingsOn(this.loadingDots);
        executeBindingsOn(this.cameraRollQpTooltip);
        executeBindingsOn(this.castingQpTooltip);
    }
}
