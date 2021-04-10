package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.mediarouter.app.MediaRouteButton;
import java.util.ArrayList;
import java.util.List;

/* renamed from: cf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2045cf0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9622a;
    public boolean b = true;
    public List c;

    public C2045cf0(Context context) {
        this.f9622a = context;
        this.c = new ArrayList();
    }

    public void onReceive(Context context, Intent intent) {
        boolean z;
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && this.b != (!intent.getBooleanExtra("noConnectivity", false))) {
            this.b = z;
            for (MediaRouteButton mediaRouteButton : this.c) {
                mediaRouteButton.c();
            }
        }
    }
}
