package androidx.media.app;

import X.AnonymousClass02D;
import X.AnonymousClass06v;
import android.app.Notification;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.R;

public class NotificationCompat$DecoratedMediaCustomViewStyle extends NotificationCompat$MediaStyle {
    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getBigContentViewLayoutResource(int i) {
        if (i <= 3) {
            return R.layout.notification_template_big_media_narrow_custom;
        }
        return R.layout.notification_template_big_media_custom;
    }

    private void setBackgroundColor(RemoteViews remoteViews) {
        remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", this.mBuilder.A03.getResources().getColor(R.color.notification_material_background_media_default_color));
    }

    @Override // X.AnonymousClass07C, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public void apply(AnonymousClass06v r3) {
        Notification.Builder A3A = r3.A3A();
        Notification.DecoratedMediaCustomViewStyle decoratedMediaCustomViewStyle = new Notification.DecoratedMediaCustomViewStyle();
        fillInMediaStyle(decoratedMediaCustomViewStyle);
        A3A.setStyle(decoratedMediaCustomViewStyle);
    }

    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getContentViewLayoutResource() {
        return super.getContentViewLayoutResource();
    }

    @Override // X.AnonymousClass07C, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public RemoteViews makeBigContentView(AnonymousClass06v r2) {
        return null;
    }

    @Override // X.AnonymousClass07C, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public RemoteViews makeContentView(AnonymousClass06v r2) {
        return null;
    }

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public RemoteViews makeHeadsUpContentView(AnonymousClass06v r2) {
        return null;
    }
}
