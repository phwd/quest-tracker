package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.databinding.BaseObservable;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class AbstractVRNotification extends BaseObservable implements IBaseVRNotification {
    public static final String NOTIF_FBID_FIELD = "oculus_notification_fbid";
    public static final String NOTIF_NDID_FIELD = "oculus_notification_ndid";
    public static final String NOTIF_TYPE_FIELD = "oculus_notification_type";
    public static final String NOTIF_XTYPE_FIELD = "oculus_notification_xnotiftype";
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected NotificationSeenState mSeenState;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public void setSeenState(NotificationSeenState notificationSeenState) {
        if (getSeenState().getValue() < notificationSeenState.getValue()) {
            this.mSeenState = notificationSeenState;
            this.mHandler.post(new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$AbstractVRNotification$IlSdP5SVVI2eIyY9ZlseJXOwy94 */

                public final void run() {
                    AbstractVRNotification.this.lambda$setSeenState$97$AbstractVRNotification();
                }
            });
        }
    }

    public /* synthetic */ void lambda$setSeenState$97$AbstractVRNotification() {
        notifyPropertyChanged(BR.seenState);
    }

    public static String postedTimeToTimeAgo(Context context, long j) {
        return postedTimeToTimeAgo(context, j, true);
    }

    public static String postedTimeToTimeAgo(Context context, long j, boolean z) {
        long currentTimeMillis = (System.currentTimeMillis() / ((long) (z ? 1000 : 1))) - j;
        long j2 = (long) (z ? 60 : 60000);
        long j3 = 60 * j2;
        long j4 = 24 * j3;
        long j5 = 7 * j4;
        long j6 = 30 * j4;
        long j7 = 365 * j4;
        int i = (currentTimeMillis > j2 ? 1 : (currentTimeMillis == j2 ? 0 : -1));
        if (i < 0) {
            return context.getString(R.string.anytime_tablet_notifications_arrival_now);
        }
        if (i >= 0 && currentTimeMillis < j3) {
            return String.format("%sm", Long.valueOf(currentTimeMillis / j2));
        } else if (currentTimeMillis >= j3 && currentTimeMillis < j4) {
            return String.format("%sh", Long.valueOf(currentTimeMillis / j3));
        } else if (currentTimeMillis >= j4 && currentTimeMillis < j4 * 2) {
            return context.getString(R.string.anytime_tablet_notifications_arrival_yesterday);
        } else {
            if (currentTimeMillis >= 2 * j4 && currentTimeMillis < j5) {
                return String.format("%sd", Integer.valueOf((int) Math.floor((double) (currentTimeMillis / j4))));
            } else if (currentTimeMillis >= j5 && currentTimeMillis < j6) {
                return String.format("%sw ", Integer.valueOf((int) Math.floor((double) (currentTimeMillis / j5))));
            } else if (currentTimeMillis >= j6 && currentTimeMillis < j7) {
                return String.format("%sm", Integer.valueOf((int) Math.floor((double) (currentTimeMillis / j6))));
            } else if (currentTimeMillis < j7) {
                return "";
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d',' yyyy", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getDefault());
                return String.format("%s", simpleDateFormat.format(new Date(1000 * j)));
            }
        }
    }

    public static RoundedBitmapDrawable getRoundDrawableFromBitmap(Context context, Bitmap bitmap) {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        create.setCircular(true);
        create.setAntiAlias(true);
        return create;
    }

    public enum NotificationSeenState {
        UNSEEN_AND_UNREAD(0),
        SEEN_BUT_UNREAD(1),
        SEEN_AND_READ(2),
        CLICKED(3),
        DISMISSED(4);
        
        private int mValue;

        private NotificationSeenState(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }
}
