package defpackage;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import androidx.mediarouter.app.MediaRouteActionProvider;
import androidx.mediarouter.app.MediaRouteButton;
import java.util.ArrayList;
import java.util.List;

/* renamed from: Ym  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1496Ym {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f9296a = new NF1("CastButtonFactory");
    public static final List b = new ArrayList();
    public static final List c = new ArrayList();

    public static void a(Context context, MenuItem menuItem) {
        H2 h2;
        SE0.e("Must be called from the main thread.");
        if (menuItem instanceof Y31) {
            h2 = ((Y31) menuItem).a();
        } else {
            Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
            h2 = null;
        }
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) h2;
        if (mediaRouteActionProvider != null) {
            C1557Zm e = C1557Zm.e(context);
            if (e != null) {
                C0629Kg0 a2 = e.a();
                if (a2 == null) {
                    throw new IllegalArgumentException("selector must not be null");
                } else if (!mediaRouteActionProvider.e.equals(a2)) {
                    if (!mediaRouteActionProvider.e.c()) {
                        mediaRouteActionProvider.c.j(mediaRouteActionProvider.d);
                    }
                    if (!a2.c()) {
                        mediaRouteActionProvider.c.a(a2, mediaRouteActionProvider.d, 0);
                    }
                    mediaRouteActionProvider.e = a2;
                    mediaRouteActionProvider.i();
                    MediaRouteButton mediaRouteButton = mediaRouteActionProvider.g;
                    if (mediaRouteButton != null) {
                        mediaRouteButton.e(a2);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
