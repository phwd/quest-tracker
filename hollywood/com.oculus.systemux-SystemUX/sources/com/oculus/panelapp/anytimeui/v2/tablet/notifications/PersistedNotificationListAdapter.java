package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.notifications.NotificationHelper;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCDropdown;
import com.oculus.ocui.OCSideNavItem;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsListItemV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsConstraintLayout;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.PersistedNotificationListAdapter;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NativeNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NotificationDatasetProxy;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.PersistedNotification;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.util.NotificationsActionsUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.json.JSONException;
import org.json.JSONObject;

public class PersistedNotificationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements INotificationProxyListener {
    private static final String EXTRA_DEEPLINK = "deeplink";
    private static final String EXTRA_LAUNCH_PARAMS = "launch_params";
    private static final String EXTRA_NOTIFICATION_ID = "notification_id";
    private static final String EXTRA_NOTIFICATION_NDID = "notification_ndid";
    private static final String EXTRA_NOTIFICATION_TYPE = "notification_type";
    private static final String EXTRA_PACKAGE = "package";
    private static final String FEED_DESTINATION_ACTION = "com.oculus.horizon.AUI_NOTIFICATION_DESTINATION_LAUNCH";
    public static final int NATIVE_NOTIF_VIEWTYPE = 1;
    public static final int PERSISTED_NOTIF_VIEWTYPE = 2;
    private static String TAG = "PersistedNotificationListAdapter";
    private OCDropdown<NotificationsMoreButtonItem> mActiveDropdown;
    private NotificationsType mCurrentCategory;
    private volatile List<IBaseVRNotification> mCurrentCategoryItems;
    private RecyclerView.ViewHolder mHolder;
    private volatile ConcurrentHashMap<NotificationsType, List<IBaseVRNotification>> mItems;
    private NotificationDatasetProxy mNotificationProxy = NotificationDatasetProxy.getInstance(this.mPanelApp.getContext(), null, null);
    private NotificationsType mNotificationType;
    private NotificationsView mNotificationsView;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private TabletNavUtil.TabletNavSelectListener mTabletNavSelectListener;

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private OCButton[] mActionButtons;
        private AnytimeTabletNotificationsListItemV2Binding mBinding;
        private Context mContext;

        private NotificationViewHolder(AnytimeTabletNotificationsListItemV2Binding anytimeTabletNotificationsListItemV2Binding, Context context) {
            super(anytimeTabletNotificationsListItemV2Binding.notificationListItem);
            this.mBinding = anytimeTabletNotificationsListItemV2Binding;
            this.mContext = context;
            this.mActionButtons = new OCButton[]{this.mBinding.primaryActionButton.button, this.mBinding.secondaryActionButton.button};
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void bindModel(NativeNotification nativeNotification) {
            Log.v(PersistedNotificationListAdapter.TAG, String.format("Binding for %s", nativeNotification));
            this.mBinding.setNotification(nativeNotification);
            Notification notification = nativeNotification.getRaw().getNotification();
            setVisualUpdates(nativeNotification);
            if (NotificationsActionsUtil.getNotificationIsDownloadInProgress(notification) || NativeNotification.isFBUploadInProgressNotification(nativeNotification)) {
                this.mBinding.notificationDescription.setVisibility(8);
                this.mBinding.notificationProgressBar.setProgressRatio(NotificationsActionsUtil.getNotificationProgress(notification));
                this.mBinding.notificationProgressBar.setVisibility(0);
            } else {
                this.mBinding.notificationDescription.setVisibility(0);
                this.mBinding.notificationProgressBar.setVisibility(8);
            }
            if (NotificationsActionsUtil.getNotificationIcon(notification) == null) {
                this.mBinding.notificationIcon.setVisibility(8);
            } else {
                this.mBinding.notificationIcon.setImageDrawable((RoundedBitmapDrawable) nativeNotification.getPrimaryIcon(this.mContext));
                this.mBinding.notificationIcon.setClipToOutline(true);
            }
            setActionButtons(nativeNotification);
            setMoreButton(nativeNotification);
            setContainerAction(nativeNotification);
        }

        private void setActionButtons(NativeNotification nativeNotification) {
            ClickEventButtonId clickEventButtonId;
            int i;
            for (OCButton oCButton : this.mActionButtons) {
                oCButton.setVisibility(8);
            }
            Notification.Action[] actionArr = nativeNotification.getRaw().getNotification().actions;
            if (actionArr == null) {
                Parcelable primaryAction = nativeNotification.getPrimaryAction();
                int i2 = R.drawable.ic_info_button;
                ClickEventButtonId clickEventButtonId2 = ClickEventButtonId.AUIV2_NOTIFICATIONS_ITEM;
                if (primaryAction != null) {
                    OCButton[] oCButtonArr = this.mActionButtons;
                    for (OCButton oCButton2 : oCButtonArr) {
                        if (oCButton2.getVisibility() != 0) {
                            oCButton2.setVisibility(0);
                            oCButton2.setBackgroundResource(i2);
                            oCButton2.setOnClickListener(new View.OnClickListener(primaryAction, nativeNotification, clickEventButtonId2) {
                                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$NotificationViewHolder$PPohGd5onFT11zRitmeMXSWaHAA */
                                private final /* synthetic */ Parcelable f$1;
                                private final /* synthetic */ NativeNotification f$2;
                                private final /* synthetic */ ClickEventButtonId f$3;

                                {
                                    this.f$1 = r2;
                                    this.f$2 = r3;
                                    this.f$3 = r4;
                                }

                                public final void onClick(View view) {
                                    PersistedNotificationListAdapter.NotificationViewHolder.this.lambda$setActionButtons$184$PersistedNotificationListAdapter$NotificationViewHolder(this.f$1, this.f$2, this.f$3, view);
                                }
                            });
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
            if (actionArr != null) {
                for (Notification.Action action : actionArr) {
                    ClickEventButtonId clickEventButtonId3 = ClickEventButtonId.AUIV2_NOTIFICATIONS_ITEM;
                    if (NotificationsActionsUtil.isAcceptActionItem(action)) {
                        i = R.drawable.ic_check_alt_button;
                        clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_ACCEPT_ACTION;
                    } else if (NotificationsActionsUtil.isSeeMoreActionItem(action)) {
                        i = R.drawable.ic_info_button;
                        clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_SEE_MORE_ACTION;
                    } else if (NotificationsActionsUtil.isCancelDownloadActionItem(action)) {
                        i = R.drawable.ic_notifications_stop_download;
                        clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_CANCEL_DOWNLOAD_ACTION;
                    } else if (NotificationsActionsUtil.isLaunchContentActionItem(action)) {
                        i = R.drawable.ic_game_launcher_button;
                        clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_LAUNCH_CONTENT_ACTION;
                    } else if (NotificationsActionsUtil.isRetryDownloadActionItem(action)) {
                        i = R.drawable.ic_reset_button;
                        clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_RETRY_ACTION;
                    } else if (NotificationsActionsUtil.isInviteActionItem(action) || NotificationsActionsUtil.isAcceptPartyInviteActionItem(action)) {
                        i = R.drawable.ic_party_add_button;
                        clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_ACCEPT_INVITE_ACTION;
                    } else if (NotificationsActionsUtil.isLaunchCamerarollActionItem(action)) {
                        i = R.drawable.ic_share_button;
                        clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_LAUNCH_CAMERA_ROLL_ACTION;
                    } else {
                        clickEventButtonId = clickEventButtonId3;
                        i = 0;
                    }
                    if (i != 0) {
                        OCButton[] oCButtonArr2 = this.mActionButtons;
                        int length = oCButtonArr2.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length) {
                                break;
                            }
                            OCButton oCButton3 = oCButtonArr2[i3];
                            if (oCButton3.getVisibility() != 0) {
                                oCButton3.setVisibility(0);
                                oCButton3.setBackgroundResource(i);
                                oCButton3.setOnClickListener(new View.OnClickListener(action, nativeNotification, clickEventButtonId) {
                                    /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$NotificationViewHolder$tFLhIMdVFUekrTsh0lgYYjWhFU */
                                    private final /* synthetic */ Notification.Action f$1;
                                    private final /* synthetic */ NativeNotification f$2;
                                    private final /* synthetic */ ClickEventButtonId f$3;

                                    {
                                        this.f$1 = r2;
                                        this.f$2 = r3;
                                        this.f$3 = r4;
                                    }

                                    public final void onClick(View view) {
                                        PersistedNotificationListAdapter.NotificationViewHolder.this.lambda$setActionButtons$185$PersistedNotificationListAdapter$NotificationViewHolder(this.f$1, this.f$2, this.f$3, view);
                                    }
                                });
                                break;
                            }
                            i3++;
                        }
                    }
                }
            }
        }

        public /* synthetic */ void lambda$setActionButtons$184$PersistedNotificationListAdapter$NotificationViewHolder(Parcelable parcelable, NativeNotification nativeNotification, ClickEventButtonId clickEventButtonId, View view) {
            this.mBinding.notificationListItem.setSelected(true);
            if (parcelable instanceof PendingIntent) {
                try {
                    ((PendingIntent) parcelable).send();
                } catch (PendingIntent.CanceledException e) {
                    Log.e(PersistedNotificationListAdapter.TAG, "Could not send intent from action", e);
                }
            } else {
                try {
                    this.mContext.sendBroadcast((Intent) parcelable);
                } catch (Exception e2) {
                    Log.e(PersistedNotificationListAdapter.TAG, "Failure sending broadcast for intent in notification", e2);
                }
            }
            PersistedNotificationListAdapter.this.clickNotification(nativeNotification);
            PersistedNotificationListAdapter.this.mPanelApp.logButtonClick(clickEventButtonId);
        }

        public /* synthetic */ void lambda$setActionButtons$185$PersistedNotificationListAdapter$NotificationViewHolder(Notification.Action action, NativeNotification nativeNotification, ClickEventButtonId clickEventButtonId, View view) {
            NotificationsActionsUtil.invokeNotificationAction(action.actionIntent);
            PersistedNotificationListAdapter.this.clickNotification(nativeNotification);
            if (NotificationsActionsUtil.dismissAfterClick(action)) {
                PersistedNotificationListAdapter.this.mNotificationProxy.publishRemoval(nativeNotification);
            }
            PersistedNotificationListAdapter.this.mPanelApp.logButtonClick(clickEventButtonId);
            PersistedNotificationListAdapter persistedNotificationListAdapter = PersistedNotificationListAdapter.this;
            persistedNotificationListAdapter.notifyItemChanged(persistedNotificationListAdapter.mCurrentCategoryItems.indexOf(nativeNotification));
        }

        private void setMoreButton(NativeNotification nativeNotification) {
            ArrayList arrayList = new ArrayList(Arrays.asList(NotificationsMoreButtonItem.REMOVE));
            OCDropdown oCDropdown = new OCDropdown(this.mContext);
            oCDropdown.setWidth((int) this.mContext.getResources().getDimension(R.dimen.anytime_tablet_notifications_more_dropdown_width));
            oCDropdown.setItems(arrayList);
            oCDropdown.setTitleMap(NotificationsMoreButtonItem.getMap());
            oCDropdown.setOnItemClick(new Function(nativeNotification) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$NotificationViewHolder$zK69WELMLRhE5WakPf3vvQfO_r8 */
                private final /* synthetic */ NativeNotification f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PersistedNotificationListAdapter.NotificationViewHolder.this.lambda$setMoreButton$186$PersistedNotificationListAdapter$NotificationViewHolder(this.f$1, obj);
                }
            });
            oCDropdown.setEventHandler(PersistedNotificationListAdapter.this.mPanelApp);
            this.mBinding.moreButton.setVisibility(0);
            this.mBinding.moreButton.setOnClickListener(new View.OnClickListener(oCDropdown) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$NotificationViewHolder$ISgh0KPitdWeWKDovTSj2eBF9TM */
                private final /* synthetic */ OCDropdown f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PersistedNotificationListAdapter.NotificationViewHolder.this.lambda$setMoreButton$187$PersistedNotificationListAdapter$NotificationViewHolder(this.f$1, view);
                }
            });
        }

        public /* synthetic */ Object lambda$setMoreButton$186$PersistedNotificationListAdapter$NotificationViewHolder(NativeNotification nativeNotification, Object obj) {
            if (!obj.equals(NotificationsMoreButtonItem.REMOVE)) {
                return null;
            }
            PersistedNotificationListAdapter.this.mNotificationProxy.publishRemoval(nativeNotification);
            PersistedNotificationListAdapter.this.mPanelApp.logNotificationEvent(NotificationsHelper.NOTIF_FEED_DISMISS, nativeNotification);
            return null;
        }

        public /* synthetic */ void lambda$setMoreButton$187$PersistedNotificationListAdapter$NotificationViewHolder(OCDropdown oCDropdown, View view) {
            PersistedNotificationListAdapter.this.mActiveDropdown = oCDropdown;
            oCDropdown.toggle(view);
        }

        private void setContainerAction(NativeNotification nativeNotification) {
            this.mBinding.container.setOnClickListener(new View.OnClickListener(nativeNotification) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$NotificationViewHolder$od2GFW_drctl8oRtlAhiBbtd7bI */
                private final /* synthetic */ NativeNotification f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PersistedNotificationListAdapter.NotificationViewHolder.this.lambda$setContainerAction$188$PersistedNotificationListAdapter$NotificationViewHolder(this.f$1, view);
                }
            });
        }

        public /* synthetic */ void lambda$setContainerAction$188$PersistedNotificationListAdapter$NotificationViewHolder(NativeNotification nativeNotification, View view) {
            PersistedNotificationListAdapter.this.mPanelApp.onButtonClick();
            this.mBinding.notificationListItem.setSelected(true);
            NotificationsActionsUtil.invokeNotificationAction((PendingIntent) nativeNotification.getPrimaryAction());
            PersistedNotificationListAdapter.this.clickNotification(nativeNotification);
            PersistedNotificationListAdapter persistedNotificationListAdapter = PersistedNotificationListAdapter.this;
            persistedNotificationListAdapter.notifyItemChanged(persistedNotificationListAdapter.mCurrentCategoryItems.indexOf(nativeNotification));
        }

        private void setVisualUpdates(IBaseVRNotification iBaseVRNotification) {
            NotificationsConstraintLayout notificationsConstraintLayout = this.mBinding.notificationListItem;
            OCTextView oCTextView = this.mBinding.notificationDescription;
            Boolean valueOf = Boolean.valueOf(iBaseVRNotification.getSeenState() == AbstractVRNotification.NotificationSeenState.CLICKED);
            Drawable drawable = this.mContext.getDrawable(R.drawable.anytime_tablet_notifications_list_item_image_background_v2);
            if (valueOf.booleanValue()) {
                drawable = this.mContext.getDrawable(R.drawable.anytime_tablet_notifications_list_item_clicked_background);
            }
            notificationsConstraintLayout.setBackground(drawable);
            notificationsConstraintLayout.setSelected(valueOf.booleanValue());
            oCTextView.setSelected(false);
            notificationsConstraintLayout.addHoverStateListener(new NotificationsConstraintLayout.IHoverStateChangedListener(notificationsConstraintLayout, oCTextView) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$NotificationViewHolder$YEyqpFnOo8ymTEcPZgU3Y4kxUN4 */
                private final /* synthetic */ NotificationsConstraintLayout f$1;
                private final /* synthetic */ TextView f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsConstraintLayout.IHoverStateChangedListener
                public final boolean onHoverChangedListener(NotificationsConstraintLayout.HoverState hoverState) {
                    return PersistedNotificationListAdapter.NotificationViewHolder.this.lambda$setVisualUpdates$189$PersistedNotificationListAdapter$NotificationViewHolder(this.f$1, this.f$2, hoverState);
                }
            });
            this.mBinding.notificationTimestamp.setText(AbstractVRNotification.postedTimeToTimeAgo(this.mContext, iBaseVRNotification.getPostedTimeSeconds()));
            this.mBinding.notificationTimestamp.setVisibility(0);
        }

        public /* synthetic */ boolean lambda$setVisualUpdates$189$PersistedNotificationListAdapter$NotificationViewHolder(NotificationsConstraintLayout notificationsConstraintLayout, TextView textView, NotificationsConstraintLayout.HoverState hoverState) {
            int i = AnonymousClass3.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$NotificationsConstraintLayout$HoverState[hoverState.ordinal()];
            if (i == 1 || i == 2) {
                if (!notificationsConstraintLayout.isHovered()) {
                    PersistedNotificationListAdapter.this.mPanelApp.onButtonEnter();
                    notificationsConstraintLayout.setHovered(true);
                }
                if (!textView.isSelected()) {
                    textView.setSelected(true);
                }
                return false;
            } else if (i != 3) {
                return false;
            } else {
                notificationsConstraintLayout.setHovered(false);
                textView.setSelected(false);
                return true;
            }
        }
    }

    public class PersistedNotificationViewHolder extends RecyclerView.ViewHolder {
        private OCButton[] mActionButtons;
        private AnytimeTabletNotificationsListItemV2Binding mBinding;
        private Context mContext;

        private PersistedNotificationViewHolder(AnytimeTabletNotificationsListItemV2Binding anytimeTabletNotificationsListItemV2Binding, Context context) {
            super(anytimeTabletNotificationsListItemV2Binding.notificationListItem);
            this.mBinding = anytimeTabletNotificationsListItemV2Binding;
            this.mContext = context;
            this.mActionButtons = new OCButton[]{this.mBinding.primaryActionButton.button, this.mBinding.secondaryActionButton.button};
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void bindModel(PersistedNotification persistedNotification) {
            this.mBinding.setNotification(persistedNotification);
            setVisualUpdates(persistedNotification);
            this.mBinding.notificationDescription.setVisibility(0);
            this.mBinding.notificationProgressBar.setVisibility(8);
            Drawable primaryIcon = persistedNotification.getPrimaryIcon(this.mContext);
            if (primaryIcon == null) {
                this.mBinding.notificationIcon.setVisibility(8);
            } else {
                this.mBinding.notificationIcon.setImageDrawable(primaryIcon);
                this.mBinding.notificationIcon.setClipToOutline(true);
            }
            setActionButtons(persistedNotification);
            setMoreButton(persistedNotification);
            setContainerAction(persistedNotification);
        }

        private void launchDestination(PersistedNotification persistedNotification) throws JSONException, PendingIntent.CanceledException {
            JSONObject launchDestination = persistedNotification.getLaunchDestination();
            if (launchDestination != null) {
                String string = launchDestination.getString("package");
                String string2 = launchDestination.has("launch_params") ? launchDestination.getString("launch_params") : "";
                Intent intent = new Intent(PersistedNotificationListAdapter.FEED_DESTINATION_ACTION);
                intent.setPackage("com.oculus.horizon");
                intent.putExtra("package", string);
                intent.putExtra("launch_params", string2);
                intent.putExtra("deeplink", persistedNotification.getDeeplinkUri());
                intent.putExtra(PersistedNotificationListAdapter.EXTRA_NOTIFICATION_ID, persistedNotification.getFBID());
                intent.putExtra(PersistedNotificationListAdapter.EXTRA_NOTIFICATION_NDID, persistedNotification.getNDID());
                intent.putExtra(PersistedNotificationListAdapter.EXTRA_NOTIFICATION_TYPE, persistedNotification.getNotificationType());
                PersistedNotificationListAdapter.this.mPanelApp.getContext().getApplicationContext().sendBroadcast(intent);
                Log.v(PersistedNotificationListAdapter.TAG, "Launching the destination");
            }
        }

        private void setActionButtons(PersistedNotification persistedNotification) {
            for (OCButton oCButton : this.mActionButtons) {
                oCButton.setVisibility(8);
            }
            Intent intent = (Intent) persistedNotification.getPrimaryAction();
            int i = R.drawable.ic_info_button;
            ClickEventButtonId clickEventButtonId = ClickEventButtonId.AUIV2_NOTIFICATIONS_ITEM;
            OCButton[] oCButtonArr = this.mActionButtons;
            for (OCButton oCButton2 : oCButtonArr) {
                if (oCButton2.getVisibility() != 0) {
                    oCButton2.setVisibility(0);
                    oCButton2.setBackgroundResource(i);
                    oCButton2.setOnClickListener(new View.OnClickListener(persistedNotification, intent) {
                        /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$PersistedNotificationViewHolder$tMWWGJS5aAqeyzVYDzkNsUMkO0 */
                        private final /* synthetic */ PersistedNotification f$1;
                        private final /* synthetic */ Intent f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void onClick(View view) {
                            PersistedNotificationListAdapter.PersistedNotificationViewHolder.this.lambda$setActionButtons$190$PersistedNotificationListAdapter$PersistedNotificationViewHolder(this.f$1, this.f$2, view);
                        }
                    });
                    return;
                }
            }
        }

        public /* synthetic */ void lambda$setActionButtons$190$PersistedNotificationListAdapter$PersistedNotificationViewHolder(PersistedNotification persistedNotification, Intent intent, View view) {
            this.mBinding.notificationListItem.setSelected(true);
            if (persistedNotification.getLaunchDestination() == null || !PersistedNotificationListAdapter.this.mPanelApp.isGKEnabled(Gatekeeper.NOTIFICATIONS_LAUNCH_DESTINATIONS)) {
                this.mContext.sendBroadcast(intent);
            } else {
                try {
                    launchDestination(persistedNotification);
                } catch (Exception e) {
                    Log.e(PersistedNotificationListAdapter.TAG, "Error in launching destination from notification", e);
                    this.mContext.sendBroadcast(intent);
                }
            }
            PersistedNotificationListAdapter.this.clickNotification(persistedNotification);
            PersistedNotificationListAdapter persistedNotificationListAdapter = PersistedNotificationListAdapter.this;
            persistedNotificationListAdapter.notifyItemChanged(persistedNotificationListAdapter.mCurrentCategoryItems.indexOf(persistedNotification));
        }

        private void setMoreButton(PersistedNotification persistedNotification) {
            ArrayList arrayList = new ArrayList(Arrays.asList(NotificationsMoreButtonItem.REMOVE));
            OCDropdown oCDropdown = new OCDropdown(this.mContext);
            oCDropdown.setWidth((int) this.mContext.getResources().getDimension(R.dimen.anytime_tablet_notifications_more_dropdown_width));
            oCDropdown.setItems(arrayList);
            oCDropdown.setTitleMap(NotificationsMoreButtonItem.getMap());
            oCDropdown.setOnItemClick(new Function(persistedNotification) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$PersistedNotificationViewHolder$Iu4E3JaLEPH0YAxW_D8pDlQeI4 */
                private final /* synthetic */ PersistedNotification f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PersistedNotificationListAdapter.PersistedNotificationViewHolder.this.lambda$setMoreButton$191$PersistedNotificationListAdapter$PersistedNotificationViewHolder(this.f$1, obj);
                }
            });
            oCDropdown.setEventHandler(PersistedNotificationListAdapter.this.mPanelApp);
            this.mBinding.moreButton.setVisibility(0);
            this.mBinding.moreButton.setOnClickListener(new View.OnClickListener(oCDropdown) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$PersistedNotificationViewHolder$XrPkhL9YG9Jb7BGubpxbS9XWaI */
                private final /* synthetic */ OCDropdown f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PersistedNotificationListAdapter.PersistedNotificationViewHolder.this.lambda$setMoreButton$192$PersistedNotificationListAdapter$PersistedNotificationViewHolder(this.f$1, view);
                }
            });
        }

        public /* synthetic */ Object lambda$setMoreButton$191$PersistedNotificationListAdapter$PersistedNotificationViewHolder(PersistedNotification persistedNotification, Object obj) {
            if (!obj.equals(NotificationsMoreButtonItem.REMOVE)) {
                return null;
            }
            PersistedNotificationListAdapter.this.mNotificationProxy.publishRemoval(persistedNotification);
            PersistedNotificationListAdapter.this.mPanelApp.logNotificationEvent(NotificationsHelper.NOTIF_FEED_DISMISS, persistedNotification);
            return null;
        }

        public /* synthetic */ void lambda$setMoreButton$192$PersistedNotificationListAdapter$PersistedNotificationViewHolder(OCDropdown oCDropdown, View view) {
            PersistedNotificationListAdapter.this.mActiveDropdown = oCDropdown;
            oCDropdown.toggle(view);
        }

        private void setContainerAction(PersistedNotification persistedNotification) {
            this.mBinding.container.setOnClickListener(new View.OnClickListener(persistedNotification) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$PersistedNotificationViewHolder$YcJfYcRq_kHYoFqne2MyrJOWBg */
                private final /* synthetic */ PersistedNotification f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PersistedNotificationListAdapter.PersistedNotificationViewHolder.this.lambda$setContainerAction$193$PersistedNotificationListAdapter$PersistedNotificationViewHolder(this.f$1, view);
                }
            });
        }

        public /* synthetic */ void lambda$setContainerAction$193$PersistedNotificationListAdapter$PersistedNotificationViewHolder(PersistedNotification persistedNotification, View view) {
            PersistedNotificationListAdapter.this.mPanelApp.onButtonClick();
            this.mBinding.notificationListItem.setSelected(true);
            Intent intent = (Intent) persistedNotification.getPrimaryAction();
            if (intent != null) {
                if (persistedNotification.getLaunchDestination() == null || !PersistedNotificationListAdapter.this.mPanelApp.isGKEnabled(Gatekeeper.NOTIFICATIONS_LAUNCH_DESTINATIONS)) {
                    this.mContext.sendBroadcast(intent);
                } else {
                    try {
                        launchDestination(persistedNotification);
                    } catch (Exception e) {
                        Log.e(PersistedNotificationListAdapter.TAG, "Error in launching destination from notification", e);
                        this.mContext.sendBroadcast(intent);
                    }
                }
                PersistedNotificationListAdapter.this.clickNotification(persistedNotification);
                PersistedNotificationListAdapter persistedNotificationListAdapter = PersistedNotificationListAdapter.this;
                persistedNotificationListAdapter.notifyItemChanged(persistedNotificationListAdapter.mCurrentCategoryItems.indexOf(persistedNotification));
            }
        }

        private void setVisualUpdates(IBaseVRNotification iBaseVRNotification) {
            NotificationsConstraintLayout notificationsConstraintLayout = this.mBinding.notificationListItem;
            OCTextView oCTextView = this.mBinding.notificationDescription;
            Boolean valueOf = Boolean.valueOf(iBaseVRNotification.getSeenState() == AbstractVRNotification.NotificationSeenState.CLICKED);
            Drawable drawable = this.mContext.getDrawable(R.drawable.anytime_tablet_notifications_list_item_image_background_v2);
            if (valueOf.booleanValue()) {
                drawable = this.mContext.getDrawable(R.drawable.anytime_tablet_notifications_list_item_clicked_background);
            }
            notificationsConstraintLayout.setBackground(drawable);
            notificationsConstraintLayout.setSelected(valueOf.booleanValue());
            oCTextView.setSelected(false);
            notificationsConstraintLayout.addHoverStateListener(new NotificationsConstraintLayout.IHoverStateChangedListener(notificationsConstraintLayout, oCTextView) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$PersistedNotificationViewHolder$hvV0c0zJVgorN_BoJsdvHpeFp8c */
                private final /* synthetic */ NotificationsConstraintLayout f$1;
                private final /* synthetic */ TextView f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsConstraintLayout.IHoverStateChangedListener
                public final boolean onHoverChangedListener(NotificationsConstraintLayout.HoverState hoverState) {
                    return PersistedNotificationListAdapter.PersistedNotificationViewHolder.this.lambda$setVisualUpdates$194$PersistedNotificationListAdapter$PersistedNotificationViewHolder(this.f$1, this.f$2, hoverState);
                }
            });
            this.mBinding.notificationTimestamp.setText(AbstractVRNotification.postedTimeToTimeAgo(this.mContext, iBaseVRNotification.getPostedTimeSeconds()));
            this.mBinding.notificationTimestamp.setVisibility(0);
        }

        public /* synthetic */ boolean lambda$setVisualUpdates$194$PersistedNotificationListAdapter$PersistedNotificationViewHolder(NotificationsConstraintLayout notificationsConstraintLayout, TextView textView, NotificationsConstraintLayout.HoverState hoverState) {
            int i = AnonymousClass3.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$NotificationsConstraintLayout$HoverState[hoverState.ordinal()];
            if (i == 1 || i == 2) {
                if (!notificationsConstraintLayout.isHovered()) {
                    PersistedNotificationListAdapter.this.mPanelApp.onButtonEnter();
                    notificationsConstraintLayout.setHovered(true);
                }
                if (!textView.isSelected()) {
                    textView.setSelected(true);
                }
                return false;
            } else if (i != 3) {
                return false;
            } else {
                notificationsConstraintLayout.setHovered(false);
                textView.setSelected(false);
                return true;
            }
        }
    }

    public PersistedNotificationListAdapter(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, NotificationsView notificationsView) {
        List<String> list;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mNotificationsView = notificationsView;
        TabletNavUtil tabletNavUtil = this.mPanelApp.getTabletNavUtil();
        this.mNotificationType = NotificationsType.mapFromUri(tabletNavUtil.getSelectedNavItem(TabletNav.NOTIFICATIONS).getUri());
        int i = 0;
        if (DeviceConfigHelper.getBoolean(this.mPanelApp.getContext(), Gatekeeper.AUI_V2_MESSENGER)) {
            list = Arrays.asList(NativeNotification.MESSENGER_NOTIFICATION_TYPE, NativeNotification.OCULUS_CHATS_NOTIFICATION_TYPE);
        } else {
            list = Arrays.asList(NativeNotification.MESSENGER_NOTIFICATION_TYPE);
        }
        this.mItems = this.mNotificationProxy.listen(this, list);
        this.mCurrentCategory = this.mNotificationType;
        this.mCurrentCategoryItems = this.mItems.get(this.mNotificationType);
        this.mNotificationsView.setNullStateVisibility(!this.mCurrentCategoryItems.isEmpty() ? 8 : i);
        this.mTabletNavSelectListener = new TabletNavUtil.TabletNavSelectListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$A15hUgbgOS_lTbxLKmVqRJDIFcw */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.TabletNavSelectListener
            public final void onSelect(OCSideNavItem oCSideNavItem) {
                PersistedNotificationListAdapter.this.lambda$new$195$PersistedNotificationListAdapter(oCSideNavItem);
            }
        };
        tabletNavUtil.addSelectListener(TabletNav.NOTIFICATIONS, this.mTabletNavSelectListener);
    }

    public /* synthetic */ void lambda$new$195$PersistedNotificationListAdapter(OCSideNavItem oCSideNavItem) {
        this.mNotificationType = NotificationsType.mapFromUri(oCSideNavItem.getUri());
        setCategoryList(this.mNotificationType);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AnytimeTabletNotificationsListItemV2Binding inflate = AnytimeTabletNotificationsListItemV2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        inflate.primaryActionButton.button.setEventHandler(this.mPanelApp);
        inflate.secondaryActionButton.button.setEventHandler(this.mPanelApp);
        inflate.dismissButtonOriginal.button.setEventHandler(this.mPanelApp);
        inflate.moreButton.setEventHandler(this.mPanelApp);
        this.mHolder = null;
        if (i == 1) {
            this.mHolder = new NotificationViewHolder(inflate, viewGroup.getContext());
        } else if (i == 2) {
            this.mHolder = new PersistedNotificationViewHolder(inflate, viewGroup.getContext());
        }
        return this.mHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (this.mCurrentCategoryItems.get(i) instanceof NativeNotification) {
            ((NotificationViewHolder) viewHolder).bindModel((NativeNotification) this.mCurrentCategoryItems.get(i));
        }
        if (this.mCurrentCategoryItems.get(i) instanceof PersistedNotification) {
            ((PersistedNotificationViewHolder) viewHolder).bindModel((PersistedNotification) this.mCurrentCategoryItems.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        try {
            return this.mCurrentCategoryItems.size();
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mCurrentCategoryItems.get(i) instanceof NativeNotification ? 1 : 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        NotificationsView notificationsView = this.mNotificationsView;
        NotificationsView.setViewHasBeenOpened(true);
        this.mNotificationsView.handleTopVisibleItemsSeenState();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        removeListener();
        this.mPanelApp.getTabletNavUtil().removeSelectListener(this.mTabletNavSelectListener);
        NotificationsView notificationsView = this.mNotificationsView;
        NotificationsView.setViewHasBeenOpened(false);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationAdded(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
        if (iBaseVRNotification instanceof NativeNotification) {
            handleSeenStateObservation((NativeNotification) iBaseVRNotification);
        }
        addNotification(iBaseVRNotification);
        if (!this.mNotificationsView.isAttachedToWindow() || this.mNotificationsView.getVisibility() != 0) {
            setCategoryList(NotificationsType.ALL);
            return;
        }
        if (this.mCurrentCategory.equals(iBaseVRNotification.getCategory()) || this.mCurrentCategory.equals(NotificationsType.ALL)) {
            int indexOf = this.mCurrentCategoryItems.indexOf(iBaseVRNotification);
            Log.v(TAG, String.format("Adding %s at index %s", iBaseVRNotification, Integer.valueOf(indexOf)));
            notifyItemInserted(this.mCurrentCategoryItems.indexOf(iBaseVRNotification));
            this.mNotificationsView.scrollToTop();
        }
        handleEmptyList();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationRemoved(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
        int indexOf = this.mCurrentCategoryItems.indexOf(iBaseVRNotification);
        removeNotification(iBaseVRNotification);
        if (indexOf >= 0) {
            Log.v(TAG, String.format("Removing an item %s at index %s", iBaseVRNotification, Integer.valueOf(indexOf)));
            notifyItemRemoved(indexOf);
        } else {
            notifyDataSetChanged();
        }
        handleEmptyList();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public synchronized void onNotificationUpdated(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
        if (!this.mCurrentCategoryItems.isEmpty()) {
            update(iBaseVRNotification);
            int indexOf = this.mCurrentCategoryItems.indexOf(iBaseVRNotification);
            Log.v(TAG, String.format("Updating item %s at index %s", iBaseVRNotification, Integer.valueOf(indexOf)));
            if (indexOf != -1) {
                notifyItemChanged(indexOf);
            }
        }
    }

    @Nullable
    public IBaseVRNotification getItemInPosition(int i) {
        if (this.mCurrentCategoryItems == null || this.mCurrentCategoryItems.isEmpty()) {
            return null;
        }
        return this.mCurrentCategoryItems.get(i);
    }

    public void dismissActiveDropdown() {
        OCDropdown<NotificationsMoreButtonItem> oCDropdown = this.mActiveDropdown;
        if (oCDropdown != null && oCDropdown.isShowing()) {
            this.mActiveDropdown.dismiss();
        }
    }

    public NotificationsType getCurrentCategory() {
        return this.mCurrentCategory;
    }

    public int getCurrentSideNavCategory() {
        return getSideNavForCategory(this.mCurrentCategory);
    }

    public OCDropdown getMoreButtonDropdown() {
        return this.mActiveDropdown;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clickNotification(IBaseVRNotification iBaseVRNotification) {
        iBaseVRNotification.setSeenState(AbstractVRNotification.NotificationSeenState.CLICKED);
        this.mPanelApp.acquireNotifsViewModel().syncSeenState(iBaseVRNotification, AbstractVRNotification.NotificationSeenState.CLICKED, true, null);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_NOTIFICATIONS_ITEM);
    }

    private synchronized void addNotification(IBaseVRNotification iBaseVRNotification) {
        this.mItems.compute(coerceType(iBaseVRNotification.getCategory()), new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$g4JwncysshFyWeVcmcsY46oCb8 */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                NotificationsType notificationsType = (NotificationsType) obj;
                return ((List) obj2).add(0, IBaseVRNotification.this);
            }
        });
        this.mItems.compute(NotificationsType.ALL, new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$EHBAR7OCOKFSKdhxTfDLP7nwmQ */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                NotificationsType notificationsType = (NotificationsType) obj;
                return ((List) obj2).add(0, IBaseVRNotification.this);
            }
        });
    }

    private synchronized void removeNotification(IBaseVRNotification iBaseVRNotification) {
        this.mItems.compute(coerceType(iBaseVRNotification.getCategory()), new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$u4oP_jn12RoPIZmoJF4Qh17npk */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                NotificationsType notificationsType = (NotificationsType) obj;
                return ((List) obj2).remove(IBaseVRNotification.this);
            }
        });
        this.mItems.compute(NotificationsType.ALL, new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$cSGfjBxau0EtEZI2uOf5oswnAJY */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                NotificationsType notificationsType = (NotificationsType) obj;
                return ((List) obj2).remove(IBaseVRNotification.this);
            }
        });
    }

    private synchronized void update(IBaseVRNotification iBaseVRNotification) {
        this.mItems.compute(NotificationsType.ALL, new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$OlQ8SqZzUakvBjZzIeCyJcw4TYI */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return PersistedNotificationListAdapter.lambda$update$200(IBaseVRNotification.this, (NotificationsType) obj, (List) obj2);
            }
        });
        this.mItems.compute(coerceType(iBaseVRNotification.getCategory()), new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.$$Lambda$PersistedNotificationListAdapter$17W39V4rCeHRyLnLkpj7LfRbl00 */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return PersistedNotificationListAdapter.lambda$update$201(IBaseVRNotification.this, (NotificationsType) obj, (List) obj2);
            }
        });
    }

    static /* synthetic */ List lambda$update$200(IBaseVRNotification iBaseVRNotification, NotificationsType notificationsType, List list) {
        int indexOf = list.indexOf(iBaseVRNotification);
        if (indexOf >= 0) {
            list.set(indexOf, iBaseVRNotification);
        }
        return list;
    }

    static /* synthetic */ List lambda$update$201(IBaseVRNotification iBaseVRNotification, NotificationsType notificationsType, List list) {
        int indexOf = list.indexOf(iBaseVRNotification);
        if (indexOf >= 0) {
            list.set(indexOf, iBaseVRNotification);
        }
        return list;
    }

    private static NotificationsType coerceType(NotificationsType notificationsType) {
        if (notificationsType == NotificationsType.DOWNLOADS || notificationsType == NotificationsType.DOWNLOAD_IN_PROGRESS) {
            return NotificationsType.DOWNLOADS;
        }
        return notificationsType;
    }

    private void handleEmptyList() {
        if (this.mCurrentCategoryItems.isEmpty()) {
            this.mNotificationsView.setNullStateVisibility(0);
        } else {
            this.mNotificationsView.setNullStateVisibility(4);
        }
    }

    private void handleSeenStateObservation(final NativeNotification nativeNotification) {
        final AnonymousClass1 r0 = new Observable.OnPropertyChangedCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.PersistedNotificationListAdapter.AnonymousClass1 */

            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                if (i == BR.seenState) {
                    NotificationsViewModel acquireNotifsViewModel = PersistedNotificationListAdapter.this.mPanelApp.acquireNotifsViewModel();
                    NativeNotification nativeNotification = nativeNotification;
                    acquireNotifsViewModel.syncSeenState(nativeNotification, nativeNotification.getSeenState(), false, null);
                    for (IBaseVRNotification iBaseVRNotification : PersistedNotificationListAdapter.this.mCurrentCategoryItems) {
                        if (nativeNotification == iBaseVRNotification) {
                            PersistedNotificationListAdapter persistedNotificationListAdapter = PersistedNotificationListAdapter.this;
                            persistedNotificationListAdapter.notifyItemChanged(persistedNotificationListAdapter.mCurrentCategoryItems.indexOf(iBaseVRNotification));
                            return;
                        }
                    }
                }
            }
        };
        nativeNotification.addOnPropertyChangedCallback(r0);
        Executors.newScheduledThreadPool(1).schedule(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.PersistedNotificationListAdapter.AnonymousClass2 */

            public void run() {
                nativeNotification.removeOnPropertyChangedCallback(r0);
            }
        }, NotificationHelper.getNotificationDisplayDurationMs(nativeNotification.getRaw()) + 1000, TimeUnit.MILLISECONDS);
    }

    private void removeListener() {
        NotificationDatasetProxy notificationDatasetProxy = this.mNotificationProxy;
        if (notificationDatasetProxy != null) {
            notificationDatasetProxy.removeListener(this);
            this.mNotificationProxy = null;
        }
    }

    public void setCategoryList(NotificationsType notificationsType) {
        this.mCurrentCategory = notificationsType;
        this.mCurrentCategoryItems = this.mItems.get(notificationsType);
        notifyDataSetChanged();
        this.mNotificationsView.setNullStateVisibility(this.mCurrentCategoryItems.isEmpty() ? 0 : 8);
        this.mNotificationsView.navigateToCategory(getCurrentSideNavCategory());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.notifications.PersistedNotificationListAdapter$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$NotificationsConstraintLayout$HoverState = new int[NotificationsConstraintLayout.HoverState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(2:19|20)|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(2:19|20)|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007f */
        static {
            /*
            // Method dump skipped, instructions count: 138
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.notifications.PersistedNotificationListAdapter.AnonymousClass3.<clinit>():void");
        }
    }

    private static int getSideNavForCategory(NotificationsType notificationsType) {
        switch (notificationsType) {
            case ALL:
                return R.id.side_nav_notifications_all;
            case APPS:
                return R.id.side_nav_notifications_apps;
            case DEVICE:
                return R.id.side_nav_notifications_device;
            case DOWNLOADS:
                return R.id.side_nav_notifications_downloads;
            case PHONE:
                return R.id.side_nav_notifications_phone;
            case SOCIAL:
                return R.id.side_nav_notifications_social;
            case STORE:
                return R.id.side_nav_notifications_store;
            case SYSTEM:
                return R.id.side_nav_notifications_system;
            default:
                return R.id.side_nav_notifications_all;
        }
    }
}
