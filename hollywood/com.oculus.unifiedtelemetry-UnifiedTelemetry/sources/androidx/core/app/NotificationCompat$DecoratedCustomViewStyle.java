package androidx.core.app;

import X.AnonymousClass2C;
import X.AnonymousClass3Y;
import X.AnonymousClass3d;
import X.AnonymousClass3p;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$DecoratedCustomViewStyle extends AnonymousClass3p {
    public static final int MAX_ACTION_BUTTONS = 3;

    public static List<AnonymousClass3d> getNonContextualActions(List<AnonymousClass3d> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass3d r1 : list) {
            if (!r1.A06) {
                arrayList.add(r1);
            }
        }
        return arrayList;
    }

    private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
        throw null;
    }

    private RemoteViews generateActionButton(AnonymousClass3d r2) {
        throw null;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass3Y r2) {
        throw null;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeBigContentView(AnonymousClass3Y r2) {
        return null;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeContentView(AnonymousClass3Y r2) {
        return null;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeHeadsUpContentView(AnonymousClass3Y r2) {
        return null;
    }
}
