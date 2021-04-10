package defpackage;

import J.N;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: NO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NO {

    /* renamed from: a  reason: collision with root package name */
    public SP0 f8544a;
    public boolean b;
    public int c;
    public final /* synthetic */ FeedStreamSurface d;

    public NO(FeedStreamSurface feedStreamSurface, IO io) {
        this.d = feedStreamSurface;
    }

    public final void a() {
        int i = this.c;
        if (i != 0) {
            FeedStreamSurface feedStreamSurface = this.d;
            N.M5Y_rKUe(feedStreamSurface.d, feedStreamSurface, i);
            this.c = 0;
        }
        this.b = false;
    }
}
