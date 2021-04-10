package androidx.media.app;

import X.AnonymousClass2O;
import X.AnonymousClass4I;
import X.AnonymousClass4N;
import X.AnonymousClass4R;
import X.AnonymousClass4Z;
import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.widget.RemoteViews;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.oculus.userserver2.R;

public class NotificationCompat$MediaStyle extends AnonymousClass4Z {
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
        throw null;
    }

    public RemoteViews generateContentView() {
        getContentViewLayoutResource();
        throw null;
    }

    public int getContentViewLayoutResource() {
        return R.layout.notification_template_media;
    }

    private RemoteViews generateMediaActionButton(AnonymousClass4N r2) {
        throw null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public void apply(AnonymousClass4I r2) {
        throw null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public RemoteViews makeBigContentView(AnonymousClass4I r2) {
        return null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public RemoteViews makeContentView(AnonymousClass4I r2) {
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

    public NotificationCompat$MediaStyle(AnonymousClass4R r2) {
        setBuilder(r2);
    }
}
