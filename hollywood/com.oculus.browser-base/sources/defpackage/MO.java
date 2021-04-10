package defpackage;

import android.os.Handler;
import java.util.Iterator;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: MO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MO {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8473a;
    public final /* synthetic */ FeedStreamSurface b;

    public MO(FeedStreamSurface feedStreamSurface, IO io) {
        this.b = feedStreamSurface;
    }

    public void a() {
        if (!this.f8473a) {
            this.f8473a = true;
            new Handler().post(new KO(this));
        }
    }

    public final void b() {
        EW0 ew0 = this.b.g.y0;
        if (ew0 != null && ew0.h()) {
            this.b.g.y0.i(this);
            return;
        }
        this.f8473a = false;
        Iterator it = this.b.k.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((L21) uq0.next()).a();
            } else {
                return;
            }
        }
    }
}
