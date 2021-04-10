package com.oculus.panelapp.anytimeui.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCHighlight;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull;
import com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarSimple;
import com.oculus.panelapp.anytimeui.v2.bar.BarView;
import com.oculus.panelapp.anytimeui.v2.bar.BarViewModel;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusView;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;
import com.oculus.panelapp.social.SocialViewModel;

public class AnytimeBarViewV2BindingImpl extends AnytimeBarViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(22);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"anytime_bar_navigation_store_button_v2", "anytime_bar_navigation_button_v2", "anytime_bar_navigation_button_v2", "anytime_bar_navigation_button_v2", "anytime_bar_navigation_button_v2", "anytime_bar_navigation_button_v2", "anytime_bar_navigation_button_v2", "anytime_bar_navigation_store_button_v2", "anytime_bar_active_app_button_v2"}, new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17}, new int[]{R.layout.anytime_bar_navigation_store_button_v2, R.layout.anytime_bar_navigation_button_v2, R.layout.anytime_bar_navigation_button_v2, R.layout.anytime_bar_navigation_button_v2, R.layout.anytime_bar_navigation_button_v2, R.layout.anytime_bar_navigation_button_v2, R.layout.anytime_bar_navigation_button_v2, R.layout.anytime_bar_navigation_store_button_v2, R.layout.anytime_bar_active_app_button_v2});
        sViewsWithIds.put(R.id.status_bar_v2, 18);
        sViewsWithIds.put(R.id.navigation_button_profile, 19);
        sViewsWithIds.put(R.id.profile_image_view, 20);
        sViewsWithIds.put(R.id.profile_online_presence_dot_enterprise, 21);
    }

    public AnytimeBarViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 22, sIncludes, sViewsWithIds));
    }

    private AnytimeBarViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 12, (ActiveCallBarFull) objArr[8], (ActiveCallBarSimple) objArr[7], (OCButton) objArr[6], (BarView) objArr[0], (View) objArr[1], (OCHighlight) objArr[3], (AnytimeBarActiveAppButtonV2Binding) objArr[17], (AnytimeBarNavigationButtonV2Binding) objArr[10], (AnytimeBarNavigationButtonV2Binding) objArr[12], (AnytimeBarNavigationButtonV2Binding) objArr[13], (OCButton) objArr[19], (AnytimeBarNavigationButtonV2Binding) objArr[15], (AnytimeBarNavigationButtonV2Binding) objArr[14], (AnytimeBarNavigationButtonV2Binding) objArr[11], (AnytimeBarNavigationStoreButtonV2Binding) objArr[9], (AnytimeBarNavigationStoreButtonV2Binding) objArr[16], (SimpleDraweeView) objArr[20], (View) objArr[2], (View) objArr[21], (View) objArr[5], (StatusView) objArr[18], (View) objArr[4]);
        this.mDirtyFlags = -1;
        this.activeCallBarFull.setTag(null);
        this.activeCallBarSimple.setTag(null);
        this.activeCallBarSimpleExpandedTapTarget.setTag(null);
        this.barView.setTag(null);
        this.leftEdge.setTag(null);
        this.libraryHighlight.setTag(null);
        this.profileOnlinePresenceDot.setTag(null);
        this.rightEdge.setTag(null);
        this.storeDivider.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 262144;
        }
        this.navigationButtonStoreLeft.invalidateAll();
        this.navigationButtonLibrary.invalidateAll();
        this.navigationButtonSocial.invalidateAll();
        this.navigationButtonMessenger.invalidateAll();
        this.navigationButtonNotifications.invalidateAll();
        this.navigationButtonSharing.invalidateAll();
        this.navigationButtonSettings.invalidateAll();
        this.navigationButtonStoreRight.invalidateAll();
        this.navigationButtonDestinationUi.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.navigationButtonLibrary.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.navigationButtonSocial.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.navigationButtonMessenger.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r4.navigationButtonNotifications.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r4.navigationButtonSharing.hasPendingBindings() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r4.navigationButtonSettings.hasPendingBindings() == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
        if (r4.navigationButtonStoreRight.hasPendingBindings() == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
        if (r4.navigationButtonDestinationUi.hasPendingBindings() == false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005e, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.navigationButtonStoreLeft.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0060 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0060 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0060 }
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationStoreButtonV2Binding r0 = r4.navigationButtonStoreLeft
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding r0 = r4.navigationButtonLibrary
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding r0 = r4.navigationButtonSocial
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding r0 = r4.navigationButtonMessenger
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r1
        L_0x0031:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding r0 = r4.navigationButtonNotifications
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x003a
            return r1
        L_0x003a:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding r0 = r4.navigationButtonSharing
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0043
            return r1
        L_0x0043:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding r0 = r4.navigationButtonSettings
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x004c
            return r1
        L_0x004c:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationStoreButtonV2Binding r0 = r4.navigationButtonStoreRight
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0055
            return r1
        L_0x0055:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveAppButtonV2Binding r0 = r4.navigationButtonDestinationUi
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x005e
            return r1
        L_0x005e:
            r0 = 0
            return r0
        L_0x0060:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.socialViewModel == i) {
            setSocialViewModel((SocialViewModel) obj);
        } else if (BR.destinationUIViewModel == i) {
            setDestinationUIViewModel((DestinationUIViewModel) obj);
        } else if (BR.barViewModel != i) {
            return false;
        } else {
            setBarViewModel((BarViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2Binding
    public void setSocialViewModel(@Nullable SocialViewModel socialViewModel) {
        updateRegistration(2, socialViewModel);
        this.mSocialViewModel = socialViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.socialViewModel);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2Binding
    public void setDestinationUIViewModel(@Nullable DestinationUIViewModel destinationUIViewModel) {
        updateRegistration(4, destinationUIViewModel);
        this.mDestinationUIViewModel = destinationUIViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.destinationUIViewModel);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2Binding
    public void setBarViewModel(@Nullable BarViewModel barViewModel) {
        updateRegistration(11, barViewModel);
        this.mBarViewModel = barViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.barViewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonStoreLeft.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonLibrary.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonSocial.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonMessenger.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonNotifications.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonSharing.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonSettings.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonStoreRight.setLifecycleOwner(lifecycleOwner);
        this.navigationButtonDestinationUi.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeNavigationButtonSocial((AnytimeBarNavigationButtonV2Binding) obj, i2);
            case 1:
                return onChangeNavigationButtonStoreRight((AnytimeBarNavigationStoreButtonV2Binding) obj, i2);
            case 2:
                return onChangeSocialViewModel((SocialViewModel) obj, i2);
            case 3:
                return onChangeNavigationButtonStoreLeft((AnytimeBarNavigationStoreButtonV2Binding) obj, i2);
            case 4:
                return onChangeDestinationUIViewModel((DestinationUIViewModel) obj, i2);
            case 5:
                return onChangeNavigationButtonSettings((AnytimeBarNavigationButtonV2Binding) obj, i2);
            case 6:
                return onChangeNavigationButtonNotifications((AnytimeBarNavigationButtonV2Binding) obj, i2);
            case 7:
                return onChangeNavigationButtonMessenger((AnytimeBarNavigationButtonV2Binding) obj, i2);
            case 8:
                return onChangeNavigationButtonDestinationUi((AnytimeBarActiveAppButtonV2Binding) obj, i2);
            case 9:
                return onChangeNavigationButtonLibrary((AnytimeBarNavigationButtonV2Binding) obj, i2);
            case 10:
                return onChangeNavigationButtonSharing((AnytimeBarNavigationButtonV2Binding) obj, i2);
            case 11:
                return onChangeBarViewModel((BarViewModel) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeNavigationButtonSocial(AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeNavigationButtonStoreRight(AnytimeBarNavigationStoreButtonV2Binding anytimeBarNavigationStoreButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeSocialViewModel(SocialViewModel socialViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.barLowerSectionVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        } else if (i == BR.activeCallBarSimpleVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i != BR.activeCallBarFullVisible) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
    }

    private boolean onChangeNavigationButtonStoreLeft(AnytimeBarNavigationStoreButtonV2Binding anytimeBarNavigationStoreButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeDestinationUIViewModel(DestinationUIViewModel destinationUIViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i != BR.isPaused) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        }
    }

    private boolean onChangeNavigationButtonSettings(AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeNavigationButtonNotifications(AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeNavigationButtonMessenger(AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeNavigationButtonDestinationUi(AnytimeBarActiveAppButtonV2Binding anytimeBarActiveAppButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeNavigationButtonLibrary(AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeNavigationButtonSharing(AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeBarViewModel(BarViewModel barViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.onlinePresenceDotVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        } else if (i != BR.highlightLibrary) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0240  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0263  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0285  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x02a1  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0309  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0458  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x014b  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 1168
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2BindingImpl.executeBindings():void");
    }
}
