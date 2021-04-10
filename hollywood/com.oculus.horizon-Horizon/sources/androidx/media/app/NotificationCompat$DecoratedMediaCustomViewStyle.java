package androidx.media.app;

import X.AnonymousClass02C;
import X.AnonymousClass03Y;
import X.AnonymousClass03h;
import android.app.Notification;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import com.oculus.horizon.R;

public class NotificationCompat$DecoratedMediaCustomViewStyle extends NotificationCompat$MediaStyle {
    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getBigContentViewLayoutResource(int i) {
        if (i <= 3) {
            return R.layout.notification_template_big_media_narrow_custom;
        }
        return R.layout.notification_template_big_media_custom;
    }

    private void setBackgroundColor(RemoteViews remoteViews) {
        AnonymousClass03h r0 = this.mBuilder;
        int i = r0.A05;
        if (i == 0) {
            i = r0.A0B.getResources().getColor(R.color.notification_material_background_media_default_color);
        }
        remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", i);
    }

    @Override // X.AnonymousClass03p, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public void apply(AnonymousClass03Y r3) {
        if (Build.VERSION.SDK_INT >= 24) {
            Notification.Builder A3C = r3.A3C();
            Notification.DecoratedMediaCustomViewStyle decoratedMediaCustomViewStyle = new Notification.DecoratedMediaCustomViewStyle();
            fillInMediaStyle(decoratedMediaCustomViewStyle);
            A3C.setStyle(decoratedMediaCustomViewStyle);
            return;
        }
        super.apply(r3);
    }

    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getContentViewLayoutResource() {
        return super.getContentViewLayoutResource();
    }

    @Override // X.AnonymousClass03p, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeBigContentView(AnonymousClass03Y r2) {
        return null;
    }

    @Override // X.AnonymousClass03p, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeContentView(AnonymousClass03Y r2) {
        return null;
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public RemoteViews makeHeadsUpContentView(AnonymousClass03Y r2) {
        return null;
    }
}
