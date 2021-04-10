package a.b.a;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;

/* compiled from: chromium-webapk7.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9400a;
    public final NotificationManager b;

    static {
        new HashSet();
    }

    public a(Context context) {
        this.f9400a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }
}
