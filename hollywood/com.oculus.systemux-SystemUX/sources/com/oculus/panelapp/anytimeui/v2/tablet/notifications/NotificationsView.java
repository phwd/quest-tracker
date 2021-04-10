package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSideNavItem;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.Locale;

public class NotificationsView extends BaseView {
    private static final String PHONE_NOTIFICATIONS_PAIRING_DIALOG_IMAGE = "apk://com.oculus.vrshell.home/assets/ui/settings/phone_notifications_setup_instructions_512x288.ktx";
    private static final float PHONE_NOTIFICATIONS_PAIRING_DIALOG_IMAGE_ASPECT_RATIO = 1.7777778f;
    private static final String TAG = LoggingUtil.tag(NotificationsView.class);
    private static boolean mHasBeenOpened = false;
    private AnytimeTabletNotificationsViewV2Binding mBinding;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private TabletNavUtil.TabletNavSelectListener mTabletNavSelectListener;

    public NotificationsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing NotificationsView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing NotificationsView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = (AnytimeTabletNotificationsViewV2Binding) viewDataBinding;
        initializeNavigation();
        initializeCombinedNotificationsList();
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.NOTIFICATIONS_TABLET);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying NotificationsView");
        this.mPanelApp.getTabletNavUtil().removeSelectListener(this.mTabletNavSelectListener);
        this.mBinding.notificationList.setAdapter(null);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing NotificationsView");
        if (mHasBeenOpened) {
            try {
                PersistedNotificationListAdapter persistedNotificationListAdapter = (PersistedNotificationListAdapter) this.mBinding.notificationList.getAdapter();
                navigateToCategory(persistedNotificationListAdapter.getCurrentSideNavCategory());
                scrollToTop((LinearLayoutManager) this.mBinding.notificationList.getLayoutManager(), persistedNotificationListAdapter);
                handleTopVisibleItemsSeenState();
            } catch (Exception e) {
                Log.e(TAG, "Encountered exception in updating notification seen state", e);
            }
        }
    }

    public void scrollToTop() {
        scrollToTop((LinearLayoutManager) this.mBinding.notificationList.getLayoutManager(), (PersistedNotificationListAdapter) this.mBinding.notificationList.getAdapter());
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding NotificationsView");
        PersistedNotificationListAdapter persistedNotificationListAdapter = (PersistedNotificationListAdapter) this.mBinding.notificationList.getAdapter();
        if (persistedNotificationListAdapter.getMoreButtonDropdown() != null) {
            persistedNotificationListAdapter.getMoreButtonDropdown().dismiss();
        }
    }

    private void initializeNavigation() {
        Log.d(TAG, "Initializing notifications side nav");
        this.mPanelApp.getSideNavUtil().initializeSideNav(TabletNav.NOTIFICATIONS, this.mBinding.sideNav);
        TabletNavUtil tabletNavUtil = this.mPanelApp.getTabletNavUtil();
        this.mBinding.setSectionHeader(tabletNavUtil.getSelectedNavItem(TabletNav.NOTIFICATIONS).getTitle());
        this.mTabletNavSelectListener = new TabletNavUtil.TabletNavSelectListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$NotificationsView$U0BD8cCVkH80NH9IJLyeVXnZje4 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.TabletNavSelectListener
            public final void onSelect(OCSideNavItem oCSideNavItem) {
                NotificationsView.this.lambda$initializeNavigation$202$NotificationsView(oCSideNavItem);
            }
        };
        tabletNavUtil.addSelectListener(TabletNav.NOTIFICATIONS, this.mTabletNavSelectListener);
        Log.d(TAG, "Initialized notifications side nav");
    }

    public /* synthetic */ void lambda$initializeNavigation$202$NotificationsView(OCSideNavItem oCSideNavItem) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.valueOf("AUIV2_NOTIFICATIONS_" + oCSideNavItem.getUri().toUpperCase(Locale.US) + "_SECTION"));
        this.mBinding.setSectionHeader(oCSideNavItem.getTitle());
        this.mBinding.sideNav.setSelectedItem(oCSideNavItem);
    }

    private void initializeCombinedNotificationsList() {
        this.mBinding.notificationList.setHasFixedSize(true);
        PersistedNotificationListAdapter persistedNotificationListAdapter = new PersistedNotificationListAdapter(this.mPanelApp, this);
        this.mBinding.notificationList.setAdapter(persistedNotificationListAdapter);
        this.mBinding.notificationList.setItemViewCacheSize(10);
        int i = 0;
        this.mBinding.notificationList.getRecycledViewPool().setMaxRecycledViews(1, 0);
        this.mBinding.notificationList.getRecycledViewPool().setMaxRecycledViews(2, 0);
        this.mBinding.notificationList.addOnScrollListener(new NotificationsListScrollListener(this.mPanelApp.acquireNotifsViewModel()));
        this.mBinding.nullState.getOCButton().setEventHandler(this.mPanelApp);
        this.mBinding.nullState.getOCButton().setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$NotificationsView$iA2DC1oi1YFQhfxAR1ZsKL11ihk */

            public final void onClick(View view) {
                NotificationsView.this.lambda$initializeCombinedNotificationsList$203$NotificationsView(view);
            }
        });
        if (persistedNotificationListAdapter.getItemCount() > 0) {
            i = 8;
        }
        setNullStateVisibility(i);
        Log.d(TAG, "Initialized notifications RecyclerView");
    }

    public /* synthetic */ void lambda$initializeCombinedNotificationsList$203$NotificationsView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_NOTIFICATIONS_PHONE_EMPTY_CTA);
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("notifications_phone_set_up", getResources().getString(R.string.notifications_phone_set_up_title), getResources().getString(R.string.notifications_phone_set_up_description));
        dialogDefinitionCustom.setHeroImage(new DialogHeroImage(PHONE_NOTIFICATIONS_PAIRING_DIALOG_IMAGE, PHONE_NOTIFICATIONS_PAIRING_DIALOG_IMAGE_ASPECT_RATIO, null));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("close", getResources().getString(R.string.notifications_phone_set_up_action)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("close"));
        this.mPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    public void setNullStateVisibility(int i) {
        int i2;
        PersistedNotificationListAdapter persistedNotificationListAdapter = (PersistedNotificationListAdapter) this.mBinding.notificationList.getAdapter();
        int i3 = 8;
        if (persistedNotificationListAdapter != null) {
            int i4 = R.drawable.notifications_empty_state;
            int i5 = R.string.anytime_tablet_notifications_null_state_header;
            if (persistedNotificationListAdapter.getCurrentCategory() == NotificationsType.PHONE) {
                i4 = R.drawable.phone_notifications_empty_state;
                if (!new SettingsManager().getBoolean(SettingsManager.PHONE_NOTIFICATION_ENABLED, false)) {
                    i5 = R.string.anytime_tablet_notifications_phone_setup_null_state_header;
                    i2 = 0;
                    this.mBinding.nullState.setSplash(getResources().getDrawable(i4));
                    this.mBinding.nullState.setHeader(getResources().getString(i5));
                    this.mBinding.nullState.getOCButton().setVisibility(i2);
                }
            }
            i2 = 8;
            this.mBinding.nullState.setSplash(getResources().getDrawable(i4));
            this.mBinding.nullState.setHeader(getResources().getString(i5));
            this.mBinding.nullState.getOCButton().setVisibility(i2);
        }
        this.mBinding.nullState.setVisibility(i);
        OCRecyclerView oCRecyclerView = this.mBinding.notificationList;
        if (i != 0) {
            i3 = 0;
        }
        oCRecyclerView.setVisibility(i3);
    }

    public void handleTopVisibleItemsSeenState() {
        PersistedNotificationListAdapter persistedNotificationListAdapter = (PersistedNotificationListAdapter) this.mBinding.notificationList.getAdapter();
        int itemCount = persistedNotificationListAdapter.getItemCount();
        int min = Math.min(itemCount - 1, 2);
        if (persistedNotificationListAdapter != null && itemCount > 0) {
            for (int i = 0; i <= min; i++) {
                IBaseVRNotification itemInPosition = persistedNotificationListAdapter.getItemInPosition(i);
                if (!NotificationsListScrollListener.getSeenNotifications().contains(Long.valueOf(itemInPosition.getId()))) {
                    AbstractVRNotification.NotificationSeenState notificationSeenState = AbstractVRNotification.NotificationSeenState.SEEN_AND_READ;
                    NotificationsListScrollListener.addItemAsSeen(itemInPosition.getId());
                    itemInPosition.setSeenState(notificationSeenState);
                    this.mPanelApp.acquireNotifsViewModel().syncSeenState(itemInPosition, notificationSeenState, true, null);
                }
            }
        }
    }

    public void navigateToCategory(int i) {
        this.mBinding.setSectionHeader(this.mBinding.sideNav.setSelectedItemByID(i).getTitle());
    }

    private static void scrollToTop(LinearLayoutManager linearLayoutManager, PersistedNotificationListAdapter persistedNotificationListAdapter) {
        if (linearLayoutManager != null && persistedNotificationListAdapter.getItemCount() > 0) {
            linearLayoutManager.scrollToPosition(0);
        }
    }

    public static void setViewHasBeenOpened(boolean z) {
        mHasBeenOpened = z;
    }
}
