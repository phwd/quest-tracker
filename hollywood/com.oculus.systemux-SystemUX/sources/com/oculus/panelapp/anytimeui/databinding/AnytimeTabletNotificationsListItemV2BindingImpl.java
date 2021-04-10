package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsConstraintLayout;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.vrshell.panels.views.ShellProgressBarView;

public class AnytimeTabletNotificationsListItemV2BindingImpl extends AnytimeTabletNotificationsListItemV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(11);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"anytime_tablet_notifications_action_button_v2", "anytime_tablet_notifications_action_button_v2", "anytime_tablet_notifications_action_button_v2"}, new int[]{3, 4, 5}, new int[]{R.layout.anytime_tablet_notifications_action_button_v2, R.layout.anytime_tablet_notifications_action_button_v2, R.layout.anytime_tablet_notifications_action_button_v2});
        sViewsWithIds.put(R.id.container, 6);
        sViewsWithIds.put(R.id.notification_icon, 7);
        sViewsWithIds.put(R.id.notification_timestamp, 8);
        sViewsWithIds.put(R.id.notification_progress_bar, 9);
        sViewsWithIds.put(R.id.more_button, 10);
    }

    public AnytimeTabletNotificationsListItemV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletNotificationsListItemV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (ConstraintLayout) objArr[6], (AnytimeTabletNotificationsActionButtonV2Binding) objArr[5], (OCButton) objArr[10], (OCTextView) objArr[2], (ImageView) objArr[7], (NotificationsConstraintLayout) objArr[0], (ShellProgressBarView) objArr[9], (OCTextView) objArr[8], (OCTextView) objArr[1], (AnytimeTabletNotificationsActionButtonV2Binding) objArr[3], (AnytimeTabletNotificationsActionButtonV2Binding) objArr[4]);
        this.mDirtyFlags = -1;
        this.notificationDescription.setTag(null);
        this.notificationListItem.setTag(null);
        this.notificationTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        this.primaryActionButton.invalidateAll();
        this.secondaryActionButton.invalidateAll();
        this.dismissButtonOriginal.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.secondaryActionButton.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.dismissButtonOriginal.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.primaryActionButton.hasPendingBindings() == false) goto L_0x0016;
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
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsActionButtonV2Binding r0 = r4.primaryActionButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsActionButtonV2Binding r0 = r4.secondaryActionButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsActionButtonV2Binding r0 = r4.dismissButtonOriginal
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
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsListItemV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.notification != i) {
            return false;
        }
        setNotification((IBaseVRNotification) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsListItemV2Binding
    public void setNotification(@Nullable IBaseVRNotification iBaseVRNotification) {
        this.mNotification = iBaseVRNotification;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.notification);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.primaryActionButton.setLifecycleOwner(lifecycleOwner);
        this.secondaryActionButton.setLifecycleOwner(lifecycleOwner);
        this.dismissButtonOriginal.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeSecondaryActionButton((AnytimeTabletNotificationsActionButtonV2Binding) obj, i2);
        }
        if (i == 1) {
            return onChangeDismissButtonOriginal((AnytimeTabletNotificationsActionButtonV2Binding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangePrimaryActionButton((AnytimeTabletNotificationsActionButtonV2Binding) obj, i2);
    }

    private boolean onChangeSecondaryActionButton(AnytimeTabletNotificationsActionButtonV2Binding anytimeTabletNotificationsActionButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDismissButtonOriginal(AnytimeTabletNotificationsActionButtonV2Binding anytimeTabletNotificationsActionButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangePrimaryActionButton(AnytimeTabletNotificationsActionButtonV2Binding anytimeTabletNotificationsActionButtonV2Binding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.text.Spanned] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r8 = this;
            monitor-enter(r8)
            long r0 = r8.mDirtyFlags     // Catch:{ all -> 0x0047 }
            r2 = 0
            r8.mDirtyFlags = r2     // Catch:{ all -> 0x0047 }
            monitor-exit(r8)     // Catch:{ all -> 0x0047 }
            com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification r4 = r8.mNotification
            r5 = 24
            long r0 = r0 & r5
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 0
            if (r0 == 0) goto L_0x002a
            if (r4 == 0) goto L_0x001d
            java.lang.String r1 = r4.getTitle()
            java.lang.String r2 = r4.getLongText()
            goto L_0x001e
        L_0x001d:
            r2 = r1
        L_0x001e:
            android.text.Spanned r1 = android.text.Html.fromHtml(r1)
            android.text.Spanned r2 = android.text.Html.fromHtml(r2)
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x002b
        L_0x002a:
            r2 = r1
        L_0x002b:
            if (r0 == 0) goto L_0x0037
            com.oculus.ocui.OCTextView r0 = r8.notificationDescription
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r1)
            com.oculus.ocui.OCTextView r0 = r8.notificationTitle
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0037:
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsActionButtonV2Binding r0 = r8.primaryActionButton
            executeBindingsOn(r0)
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsActionButtonV2Binding r0 = r8.secondaryActionButton
            executeBindingsOn(r0)
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsActionButtonV2Binding r0 = r8.dismissButtonOriginal
            executeBindingsOn(r0)
            return
        L_0x0047:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsListItemV2BindingImpl.executeBindings():void");
    }
}
