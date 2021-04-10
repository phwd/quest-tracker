package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;

public class AnytimeBarStatusViewV2BindingImpl extends AnytimeBarStatusViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(14);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(5, new String[]{"anytime_bar_status_battery_dots_view_v2"}, new int[]{8}, new int[]{R.layout.anytime_bar_status_battery_dots_view_v2});
        sIncludes.setIncludes(6, new String[]{"anytime_bar_status_battery_dots_view_v2"}, new int[]{9}, new int[]{R.layout.anytime_bar_status_battery_dots_view_v2});
        sIncludes.setIncludes(7, new String[]{"anytime_bar_status_battery_dots_view_v2"}, new int[]{10}, new int[]{R.layout.anytime_bar_status_battery_dots_view_v2});
        sViewsWithIds.put(R.id.icon_battery_left_controller, 11);
        sViewsWithIds.put(R.id.icon_battery_headset, 12);
        sViewsWithIds.put(R.id.icon_battery_right_controller, 13);
    }

    public AnytimeBarStatusViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 14, sIncludes, sViewsWithIds));
    }

    private AnytimeBarStatusViewV2BindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr, Object[] objArr) {
        super(dataBindingComponent, viewArr[0], 4, (AnytimeBarStatusBatteryDotsViewV2Binding) objArr[9], (AnytimeBarStatusBatteryDotsViewV2Binding) objArr[8], (AnytimeBarStatusBatteryDotsViewV2Binding) objArr[10], (LinearLayout) objArr[6], (LinearLayout) objArr[5], (LinearLayout) objArr[7], (LinearLayout) objArr[0], (ImageView) objArr[12], (ImageView) objArr[11], (ImageView) objArr[13], (OCTextView) objArr[1], (OCButton) objArr[4], (OCButton) objArr[2], (ImageView) objArr[3]);
        this.mDirtyFlags = -1;
        this.batteryHeadset.setTag(null);
        this.batteryLeftController.setTag(null);
        this.batteryRightController.setTag(null);
        this.clockTooltipWrapper.setTag(null);
        this.statusClockTimeText.setTag(null);
        this.statusPartyIcon.setTag(null);
        this.statusWifiButton.setTag(null);
        this.statusWifiIcon.setTag(null);
        setRootTag(viewArr);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
        }
        this.batteryDotsLeftController.invalidateAll();
        this.batteryDotsHeadset.invalidateAll();
        this.batteryDotsRightController.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.batteryDotsHeadset.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.batteryDotsRightController.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.batteryDotsLeftController.hasPendingBindings() == false) goto L_0x0016;
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
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusBatteryDotsViewV2Binding r0 = r4.batteryDotsLeftController
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusBatteryDotsViewV2Binding r0 = r4.batteryDotsHeadset
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusBatteryDotsViewV2Binding r0 = r4.batteryDotsRightController
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
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusViewV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.partyButtonVisible == i) {
            setPartyButtonVisible(((Boolean) obj).booleanValue());
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((StatusViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusViewV2Binding
    public void setPartyButtonVisible(boolean z) {
        this.mPartyButtonVisible = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.partyButtonVisible);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusViewV2Binding
    public void setViewModel(@Nullable StatusViewModel statusViewModel) {
        updateRegistration(3, statusViewModel);
        this.mViewModel = statusViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.batteryDotsLeftController.setLifecycleOwner(lifecycleOwner);
        this.batteryDotsHeadset.setLifecycleOwner(lifecycleOwner);
        this.batteryDotsRightController.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeBatteryDotsLeftController((AnytimeBarStatusBatteryDotsViewV2Binding) obj, i2);
        }
        if (i == 1) {
            return onChangeBatteryDotsRightController((AnytimeBarStatusBatteryDotsViewV2Binding) obj, i2);
        }
        if (i == 2) {
            return onChangeBatteryDotsHeadset((AnytimeBarStatusBatteryDotsViewV2Binding) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeViewModel((StatusViewModel) obj, i2);
    }

    private boolean onChangeBatteryDotsLeftController(AnytimeBarStatusBatteryDotsViewV2Binding anytimeBarStatusBatteryDotsViewV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeBatteryDotsRightController(AnytimeBarStatusBatteryDotsViewV2Binding anytimeBarStatusBatteryDotsViewV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeBatteryDotsHeadset(AnytimeBarStatusBatteryDotsViewV2Binding anytimeBarStatusBatteryDotsViewV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModel(StatusViewModel statusViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i != BR.time) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z = this.mPartyButtonVisible;
        String str = null;
        StatusViewModel statusViewModel = this.mViewModel;
        int i2 = ((j & 80) > 0 ? 1 : ((j & 80) == 0 ? 0 : -1));
        int i3 = 0;
        if (i2 != 0) {
            if (i2 != 0) {
                if (z) {
                    j3 = j | 256;
                    j2 = 1024;
                } else {
                    j3 = j | 128;
                    j2 = 512;
                }
                j = j3 | j2;
            }
            i = z ? 8 : 0;
            if (!z) {
                i3 = 8;
            }
        } else {
            i = 0;
        }
        int i4 = ((104 & j) > 0 ? 1 : ((104 & j) == 0 ? 0 : -1));
        if (!(i4 == 0 || statusViewModel == null)) {
            str = statusViewModel.getTime();
        }
        if (i4 != 0) {
            TextViewBindingAdapter.setText(this.statusClockTimeText, str);
        }
        if ((j & 80) != 0) {
            this.statusPartyIcon.setVisibility(i3);
            this.statusWifiButton.setVisibility(i);
            this.statusWifiIcon.setVisibility(i);
        }
        executeBindingsOn(this.batteryDotsLeftController);
        executeBindingsOn(this.batteryDotsHeadset);
        executeBindingsOn(this.batteryDotsRightController);
    }
}
