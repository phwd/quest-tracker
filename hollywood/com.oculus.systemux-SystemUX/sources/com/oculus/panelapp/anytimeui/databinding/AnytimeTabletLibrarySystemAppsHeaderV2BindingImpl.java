package com.oculus.panelapp.anytimeui.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.flexbox.FlexboxLayout;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;

public class AnytimeTabletLibrarySystemAppsHeaderV2BindingImpl extends AnytimeTabletLibrarySystemAppsHeaderV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(6);
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final FlexboxLayout mboundView0;

    static {
        sIncludes.setIncludes(0, new String[]{"anytime_tablet_library_system_app_button_v2", "anytime_tablet_library_system_app_button_v2", "anytime_tablet_library_system_app_button_v2", "anytime_tablet_library_system_app_button_v2", "anytime_tablet_library_system_app_button_v2"}, new int[]{1, 2, 3, 4, 5}, new int[]{R.layout.anytime_tablet_library_system_app_button_v2, R.layout.anytime_tablet_library_system_app_button_v2, R.layout.anytime_tablet_library_system_app_button_v2, R.layout.anytime_tablet_library_system_app_button_v2, R.layout.anytime_tablet_library_system_app_button_v2});
    }

    public AnytimeTabletLibrarySystemAppsHeaderV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletLibrarySystemAppsHeaderV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, (AnytimeTabletLibrarySystemAppButtonV2Binding) objArr[3], (AnytimeTabletLibrarySystemAppButtonV2Binding) objArr[1], (AnytimeTabletLibrarySystemAppButtonV2Binding) objArr[4], (AnytimeTabletLibrarySystemAppButtonV2Binding) objArr[2], (AnytimeTabletLibrarySystemAppButtonV2Binding) objArr[5]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (FlexboxLayout) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        this.libraryHomeButton.invalidateAll();
        this.libraryStoreButton.invalidateAll();
        this.libraryBrowserButton.invalidateAll();
        this.librarySocialButton.invalidateAll();
        this.libraryTvButton.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.libraryStoreButton.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.libraryBrowserButton.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.librarySocialButton.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r4.libraryTvButton.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.libraryHomeButton.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x003c }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding r0 = r4.libraryHomeButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding r0 = r4.libraryStoreButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding r0 = r4.libraryBrowserButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding r0 = r4.librarySocialButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r1
        L_0x0031:
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppButtonV2Binding r0 = r4.libraryTvButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x003a
            return r1
        L_0x003a:
            r0 = 0
            return r0
        L_0x003c:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppsHeaderV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.browserApp == i) {
            setBrowserApp((App) obj);
        } else if (BR.tvApp == i) {
            setTvApp((App) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((LibraryViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppsHeaderV2Binding
    public void setBrowserApp(@Nullable App app) {
        this.mBrowserApp = app;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.browserApp);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppsHeaderV2Binding
    public void setTvApp(@Nullable App app) {
        this.mTvApp = app;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.tvApp);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppsHeaderV2Binding
    public void setViewModel(@Nullable LibraryViewModel libraryViewModel) {
        updateRegistration(5, libraryViewModel);
        this.mViewModel = libraryViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.libraryHomeButton.setLifecycleOwner(lifecycleOwner);
        this.libraryStoreButton.setLifecycleOwner(lifecycleOwner);
        this.libraryBrowserButton.setLifecycleOwner(lifecycleOwner);
        this.librarySocialButton.setLifecycleOwner(lifecycleOwner);
        this.libraryTvButton.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeLibrarySocialButton((AnytimeTabletLibrarySystemAppButtonV2Binding) obj, i2);
        }
        if (i == 1) {
            return onChangeLibraryBrowserButton((AnytimeTabletLibrarySystemAppButtonV2Binding) obj, i2);
        }
        if (i == 2) {
            return onChangeLibraryStoreButton((AnytimeTabletLibrarySystemAppButtonV2Binding) obj, i2);
        }
        if (i == 3) {
            return onChangeLibraryHomeButton((AnytimeTabletLibrarySystemAppButtonV2Binding) obj, i2);
        }
        if (i == 4) {
            return onChangeLibraryTvButton((AnytimeTabletLibrarySystemAppButtonV2Binding) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeViewModel((LibraryViewModel) obj, i2);
    }

    private boolean onChangeLibrarySocialButton(AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLibraryBrowserButton(AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeLibraryStoreButton(AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeLibraryHomeButton(AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeLibraryTvButton(AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModel(LibraryViewModel libraryViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.highlightHome) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == BR.headerColoredSystemAppsEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i != BR.eventsEntryEnabled) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0220  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 580
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppsHeaderV2BindingImpl.executeBindings():void");
    }
}
