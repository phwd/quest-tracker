package defpackage;

import android.app.Notification;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.widget.RemoteViews;

/* renamed from: Ep0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0284Ep0 extends AbstractC0345Fp0 {
    public int[] b = null;
    public MediaSessionCompat$Token c;

    @Override // defpackage.AbstractC0345Fp0
    public void b(C0406Gp0 gp0) {
        Notification.Builder builder = gp0.f8113a;
        Notification.MediaStyle mediaStyle = new Notification.MediaStyle();
        int[] iArr = this.b;
        if (iArr != null) {
            mediaStyle.setShowActionsInCompactView(iArr);
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = this.c;
        if (mediaSessionCompat$Token != null) {
            mediaStyle.setMediaSession((MediaSession.Token) mediaSessionCompat$Token.G);
        }
        builder.setStyle(mediaStyle);
    }

    @Override // defpackage.AbstractC0345Fp0
    public RemoteViews d(C0406Gp0 gp0) {
        return null;
    }

    @Override // defpackage.AbstractC0345Fp0
    public RemoteViews e(C0406Gp0 gp0) {
        return null;
    }
}
