package androidx.media.app;

import X.AbstractC000803d;
import X.AnonymousClass02C;
import X.AnonymousClass03m;
import android.app.Notification;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

public class NotificationCompat$DecoratedMediaCustomViewStyle extends NotificationCompat$MediaStyle {
    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getBigContentViewLayoutResource(int i) {
        if (i <= 3) {
            return R.layout.notification_template_big_media_narrow_custom;
        }
        return R.layout.notification_template_big_media_custom;
    }

    private void setBackgroundColor(RemoteViews remoteViews) {
        AnonymousClass03m r0 = this.mBuilder;
        int i = r0.A04;
        if (i == 0) {
            i = r0.A08.getResources().getColor(R.color.notification_material_background_media_default_color);
        }
        remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", i);
    }

    @Override // X.AnonymousClass03u, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public void apply(AbstractC000803d r3) {
        Notification.Builder A3T = r3.A3T();
        Notification.DecoratedMediaCustomViewStyle decoratedMediaCustomViewStyle = new Notification.DecoratedMediaCustomViewStyle();
        fillInMediaStyle(decoratedMediaCustomViewStyle);
        A3T.setStyle(decoratedMediaCustomViewStyle);
    }

    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getContentViewLayoutResource() {
        return super.getContentViewLayoutResource();
    }

    @Override // X.AnonymousClass03u, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeBigContentView(AbstractC000803d r2) {
        return null;
    }

    @Override // X.AnonymousClass03u, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeContentView(AbstractC000803d r2) {
        return null;
    }

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeHeadsUpContentView(AbstractC000803d r2) {
        return null;
    }
}
