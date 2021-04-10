package androidx.media.app;

import X.AbstractC000803d;
import X.AnonymousClass02C;
import X.AnonymousClass03i;
import X.AnonymousClass03m;
import X.AnonymousClass03u;
import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.widget.RemoteViews;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

public class NotificationCompat$MediaStyle extends AnonymousClass03u {
    public static final int MAX_MEDIA_BUTTONS = 5;
    public static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
    public int[] mActionsToShowInCompact = null;
    public PendingIntent mCancelButtonIntent;
    public final boolean mShowCancelButton;
    public MediaSessionCompat$Token mToken;

    public int getBigContentViewLayoutResource(int i) {
        if (i <= 3) {
            return R.layout.notification_template_big_media_narrow;
        }
        return R.layout.notification_template_big_media;
    }

    private RemoteViews generateMediaActionButton(AnonymousClass03i r5) {
        boolean z = false;
        if (r5.A01 == null) {
            z = true;
        }
        RemoteViews remoteViews = new RemoteViews(this.mBuilder.A08.getPackageName(), (int) R.layout.notification_media_action);
        remoteViews.setImageViewResource(R.id.action0, r5.A00);
        if (!z) {
            remoteViews.setOnClickPendingIntent(R.id.action0, r5.A01);
        }
        remoteViews.setContentDescription(R.id.action0, r5.A02);
        return remoteViews;
    }

    public static MediaSessionCompat$Token getMediaSession(Notification notification) {
        Parcelable parcelable;
        Bundle bundle = notification.extras;
        if (bundle == null || (parcelable = bundle.getParcelable("android.mediaSession")) == null) {
            return null;
        }
        if (parcelable instanceof MediaSession.Token) {
            return new MediaSessionCompat$Token(parcelable, null);
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }

    @RequiresApi(21)
    public Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle mediaStyle) {
        int[] iArr = this.mActionsToShowInCompact;
        if (iArr != null) {
            mediaStyle.setShowActionsInCompactView(iArr);
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = this.mToken;
        if (mediaSessionCompat$Token != null) {
            mediaStyle.setMediaSession((MediaSession.Token) mediaSessionCompat$Token.A00);
        }
        return mediaStyle;
    }

    public RemoteViews generateBigContentView() {
        int min = Math.min(this.mBuilder.A0D.size(), 5);
        RemoteViews applyStandardTemplate = applyStandardTemplate(false, getBigContentViewLayoutResource(min), false);
        applyStandardTemplate.removeAllViews(R.id.media_actions);
        if (min > 0) {
            int i = 0;
            do {
                applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.mBuilder.A0D.get(i)));
                i++;
            } while (i < min);
        }
        if (this.mShowCancelButton) {
            applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
            applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.A08.getResources().getInteger(R.integer.cancel_button_image_alpha));
            applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
            return applyStandardTemplate;
        }
        applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
        return applyStandardTemplate;
    }

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public void apply(AbstractC000803d r3) {
        Notification.Builder A3T = r3.A3T();
        Notification.MediaStyle mediaStyle = new Notification.MediaStyle();
        fillInMediaStyle(mediaStyle);
        A3T.setStyle(mediaStyle);
    }

    public RemoteViews generateContentView() {
        int min;
        RemoteViews applyStandardTemplate = applyStandardTemplate(false, getContentViewLayoutResource(), true);
        int size = this.mBuilder.A0D.size();
        int[] iArr = this.mActionsToShowInCompact;
        if (iArr == null) {
            min = 0;
        } else {
            min = Math.min(iArr.length, 3);
        }
        applyStandardTemplate.removeAllViews(R.id.media_actions);
        if (min > 0) {
            int i = 0;
            while (i < size) {
                applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.mBuilder.A0D.get(this.mActionsToShowInCompact[i])));
                i++;
                if (i >= min) {
                }
            }
            throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(i), Integer.valueOf(size - 1)));
        }
        if (this.mShowCancelButton) {
            applyStandardTemplate.setViewVisibility(R.id.end_padder, 8);
            applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
            applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
            applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.A08.getResources().getInteger(R.integer.cancel_button_image_alpha));
            return applyStandardTemplate;
        }
        applyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
        applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
        return applyStandardTemplate;
    }

    public int getContentViewLayoutResource() {
        return R.layout.notification_template_media;
    }

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeBigContentView(AbstractC000803d r2) {
        return null;
    }

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeContentView(AbstractC000803d r2) {
        return null;
    }

    public NotificationCompat$MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
        this.mCancelButtonIntent = pendingIntent;
        return this;
    }

    public NotificationCompat$MediaStyle setMediaSession(MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.mToken = mediaSessionCompat$Token;
        return this;
    }

    public NotificationCompat$MediaStyle setShowActionsInCompactView(int... iArr) {
        this.mActionsToShowInCompact = iArr;
        return this;
    }

    public NotificationCompat$MediaStyle setShowCancelButton(boolean z) {
        return this;
    }

    public NotificationCompat$MediaStyle() {
    }

    public NotificationCompat$MediaStyle(AnonymousClass03m r2) {
        setBuilder(r2);
    }
}
