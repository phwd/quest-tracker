package androidx.core.app;

import X.AnonymousClass02D;
import X.AnonymousClass06v;
import X.AnonymousClass070;
import X.AnonymousClass07C;
import android.app.Notification;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.R;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$DecoratedCustomViewStyle extends AnonymousClass07C {
    public static final int MAX_ACTION_BUTTONS = 3;

    private RemoteViews generateActionButton(AnonymousClass070 r7) {
        boolean z = false;
        if (r7.A01 == null) {
            z = true;
        }
        String packageName = this.mBuilder.A03.getPackageName();
        int i = R.layout.notification_action;
        if (z) {
            i = R.layout.notification_action_tombstone;
        }
        RemoteViews remoteViews = new RemoteViews(packageName, i);
        remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(r7.A00(), this.mBuilder.A03.getResources().getColor(R.color.notification_action_color_filter)));
        remoteViews.setTextViewText(R.id.action_text, r7.A02);
        if (!z) {
            remoteViews.setOnClickPendingIntent(R.id.action_container, r7.A01);
        }
        remoteViews.setContentDescription(R.id.action_container, r7.A02);
        return remoteViews;
    }

    public static List<AnonymousClass070> getNonContextualActions(List<AnonymousClass070> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass070 r1 : list) {
            if (!r1.A08) {
                arrayList.add(r1);
            }
        }
        return arrayList;
    }

    private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
        int min;
        int i = 0;
        RemoteViews applyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
        applyStandardTemplate.removeAllViews(R.id.actions);
        List<AnonymousClass070> nonContextualActions = getNonContextualActions(this.mBuilder.A08);
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

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass06v r3) {
        r3.A3A().setStyle(new Notification.DecoratedCustomViewStyle());
    }

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeBigContentView(AnonymousClass06v r2) {
        return null;
    }

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeContentView(AnonymousClass06v r2) {
        return null;
    }

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeHeadsUpContentView(AnonymousClass06v r2) {
        return null;
    }
}
