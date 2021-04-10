package androidx.core.app;

import X.AnonymousClass2O;
import X.AnonymousClass4I;
import X.AnonymousClass4N;
import X.AnonymousClass4Z;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$DecoratedCustomViewStyle extends AnonymousClass4Z {
    public static final int MAX_ACTION_BUTTONS = 3;

    public static List<AnonymousClass4N> getNonContextualActions(List<AnonymousClass4N> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass4N r1 : list) {
            if (!r1.A06) {
                arrayList.add(r1);
            }
        }
        return arrayList;
    }

    private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
        throw null;
    }

    private RemoteViews generateActionButton(AnonymousClass4N r2) {
        throw null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass4I r2) {
        throw null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeBigContentView(AnonymousClass4I r2) {
        return null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeContentView(AnonymousClass4I r2) {
        return null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeHeadsUpContentView(AnonymousClass4I r2) {
        return null;
    }
}
