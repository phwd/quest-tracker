package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCEmptyLayout;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSideNav;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsView;

public class AnytimeTabletNotificationsViewV2BindingImpl extends AnytimeTabletNotificationsViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final NotificationsView mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.side_nav, 2);
        sViewsWithIds.put(R.id.null_state, 3);
        sViewsWithIds.put(R.id.notification_list, 4);
    }

    public AnytimeTabletNotificationsViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletNotificationsViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCRecyclerView) objArr[4], (OCTextView) objArr[1], (OCEmptyLayout) objArr[3], (OCSideNav) objArr[2]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (NotificationsView) objArr[0];
        this.mboundView0.setTag(null);
        this.notificationListHeader.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.sectionHeader != i) {
            return false;
        }
        setSectionHeader((String) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsViewV2Binding
    public void setSectionHeader(@Nullable String str) {
        this.mSectionHeader = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.sectionHeader);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mSectionHeader;
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.notificationListHeader, str);
        }
    }
}
