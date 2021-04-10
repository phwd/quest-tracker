package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import java.util.HashSet;

public class NotificationsListScrollListener extends RecyclerView.OnScrollListener {
    private static String TAG = NotificationsListScrollListener.class.getSimpleName();
    private static HashSet<Long> mSeenPositions = new HashSet<>();
    private PersistedNotificationListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private NotificationsViewModel mViewModel;

    public NotificationsListScrollListener(NotificationsViewModel notificationsViewModel) {
        this.mViewModel = notificationsViewModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (this.mRecyclerView == null) {
            this.mRecyclerView = recyclerView;
        }
        if (this.mLayoutManager == null) {
            this.mLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
        }
        if (this.mAdapter == null) {
            this.mAdapter = (PersistedNotificationListAdapter) recyclerView.getAdapter();
        }
        this.mAdapter.dismissActiveDropdown();
        int findFirstCompletelyVisibleItemPosition = this.mLayoutManager.findFirstCompletelyVisibleItemPosition();
        int findLastCompletelyVisibleItemPosition = this.mLayoutManager.findLastCompletelyVisibleItemPosition();
        if (this.mAdapter.getItemInPosition(findFirstCompletelyVisibleItemPosition) != null && !itemsHaveBeenLogged(findFirstCompletelyVisibleItemPosition, findLastCompletelyVisibleItemPosition)) {
            while (findFirstCompletelyVisibleItemPosition <= findLastCompletelyVisibleItemPosition) {
                mSeenPositions.add(Long.valueOf(this.mAdapter.getItemInPosition(findFirstCompletelyVisibleItemPosition).getId()));
                IBaseVRNotification itemInPosition = this.mAdapter.getItemInPosition(findFirstCompletelyVisibleItemPosition);
                AbstractVRNotification.NotificationSeenState notificationSeenState = AbstractVRNotification.NotificationSeenState.SEEN_AND_READ;
                if (itemInPosition.getSeenState().getValue() < AbstractVRNotification.NotificationSeenState.SEEN_AND_READ.getValue()) {
                    itemInPosition.setSeenState(notificationSeenState);
                    this.mViewModel.syncSeenState(itemInPosition, notificationSeenState, true, null);
                }
                findFirstCompletelyVisibleItemPosition++;
            }
        }
    }

    public static HashSet<Long> getSeenNotifications() {
        return mSeenPositions;
    }

    public static void addItemAsSeen(long j) {
        mSeenPositions.add(Long.valueOf(j));
    }

    private boolean itemsHaveBeenLogged(int i, int i2) {
        return mSeenPositions.contains(Long.valueOf(this.mAdapter.getItemInPosition(i2).getId())) && mSeenPositions.contains(Long.valueOf(this.mAdapter.getItemInPosition(i).getId()));
    }
}
