package androidx.core.app;

import X.AnonymousClass02C;
import X.AnonymousClass03Y;
import X.AnonymousClass03d;
import X.AnonymousClass03p;
import android.app.Notification;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import com.oculus.horizon.R;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$DecoratedCustomViewStyle extends AnonymousClass03p {
    public static final int MAX_ACTION_BUTTONS = 3;

    private RemoteViews generateActionButton(AnonymousClass03d r7) {
        boolean z = false;
        if (r7.A01 == null) {
            z = true;
        }
        String packageName = this.mBuilder.A0B.getPackageName();
        int i = R.layout.notification_action;
        if (z) {
            i = R.layout.notification_action_tombstone;
        }
        RemoteViews remoteViews = new RemoteViews(packageName, i);
        remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(r7.A00(), this.mBuilder.A0B.getResources().getColor(R.color.notification_action_color_filter)));
        remoteViews.setTextViewText(R.id.action_text, r7.A02);
        if (!z) {
            remoteViews.setOnClickPendingIntent(R.id.action_container, r7.A01);
        }
        remoteViews.setContentDescription(R.id.action_container, r7.A02);
        return remoteViews;
    }

    public static List<AnonymousClass03d> getNonContextualActions(List<AnonymousClass03d> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass03d r1 : list) {
            if (!r1.A08) {
                arrayList.add(r1);
            }
        }
        return arrayList;
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass03Y r3) {
        if (Build.VERSION.SDK_INT >= 24) {
            r3.A3C().setStyle(new Notification.DecoratedCustomViewStyle());
        }
    }

    private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
        int min;
        int i = 0;
        RemoteViews applyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
        applyStandardTemplate.removeAllViews(R.id.actions);
        List<AnonymousClass03d> nonContextualActions = getNonContextualActions(this.mBuilder.A0F);
        if (!z || nonContextualActions == null || (min = Math.min(nonContextualActions.size(), 3)) <= 0) {
            i = 8;
        } else {
            int i2 = 0;
            do {
                applyStandardTemplate.addView(R.id.actions, generateActionButton(nonContextualActions.get(i2)));
                i2++;
            } while (i2 < min);
        }
        applyStandardTemplate.setViewVisibility(R.id.actions, i);
        applyStandardTemplate.setViewVisibility(R.id.action_divider, i);
        buildIntoRemoteViews(applyStandardTemplate, remoteViews);
        return applyStandardTemplate;
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeBigContentView(AnonymousClass03Y r2) {
        return null;
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeContentView(AnonymousClass03Y r2) {
        return null;
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeHeadsUpContentView(AnonymousClass03Y r2) {
        return null;
    }
}
