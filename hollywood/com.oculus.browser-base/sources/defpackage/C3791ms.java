package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import java.util.Collections;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.notifications.NotificationIntentInterceptor$Receiver;

/* renamed from: ms  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3791ms implements AbstractC3615lq0 {

    /* renamed from: a  reason: collision with root package name */
    public final Notification.Builder f10452a;
    public final Context b;
    public final C0832Np0 c;

    public C3791ms(Context context, String str, C0340Fn fn, C0832Np0 np0) {
        this.b = context;
        Notification.Builder builder = new Notification.Builder(context);
        this.f10452a = builder;
        if (Build.VERSION.SDK_INT >= 26) {
            if (str != null) {
                fn.b(Collections.emptyList(), Collections.singletonList(str), true);
            }
            builder.setChannelId(str);
        }
        this.c = np0;
        if (np0 != null) {
            builder.setDeleteIntent(AbstractC0528Ip0.a(2, 0, np0, null));
        }
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 A(int i) {
        this.f10452a.setSmallIcon(i);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 B(CB0 cb0) {
        this.f10452a.setContentIntent(AbstractC0528Ip0.a(0, 0, this.c, cb0));
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 C(CharSequence charSequence) {
        this.f10452a.setTicker(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 D(int i) {
        this.f10452a.setColor(i);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 E(long[] jArr) {
        this.f10452a.setVibrate(jArr);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 F(CharSequence charSequence) {
        this.f10452a.setContentText(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 G(int i) {
        this.f10452a.setDefaults(i);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 H(CharSequence charSequence) {
        this.f10452a.setContentTitle(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 I(PendingIntent pendingIntent) {
        this.f10452a.setContentIntent(pendingIntent);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 J(Notification.BigPictureStyle bigPictureStyle) {
        this.f10452a.setStyle(bigPictureStyle);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 K(PendingIntent pendingIntent) {
        this.f10452a.setDeleteIntent(pendingIntent);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 L(int i, int i2, boolean z) {
        this.f10452a.setProgress(i, i2, z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 a(Notification.BigTextStyle bigTextStyle) {
        this.f10452a.setStyle(bigTextStyle);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public C3444kq0 b() {
        return new C3444kq0(c(), this.c);
    }

    @Override // defpackage.AbstractC3615lq0
    public Notification c() {
        return this.f10452a.build();
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 d(int i) {
        this.f10452a.setVisibility(i);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 e(int i, CharSequence charSequence, CB0 cb0, int i2) {
        l(i, charSequence, AbstractC0528Ip0.a(1, i2, this.c, cb0));
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 f(long j) {
        this.f10452a.setWhen(j);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 g(CharSequence charSequence) {
        this.f10452a.setSubText(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 h(boolean z) {
        this.f10452a.setOnlyAlertOnce(z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 i(Notification.Action action, int i, int i2) {
        C0832Np0 np0 = this.c;
        PendingIntent pendingIntent = action.actionIntent;
        int i3 = 0;
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent = new Intent(applicationContext, NotificationIntentInterceptor$Receiver.class);
        intent.setAction("notifications.NotificationIntentInterceptor.INTENT_ACTION");
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_PENDING_INTENT", pendingIntent);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_INTENT_TYPE", 1);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_NOTIFICATION_TYPE", np0.f8577a);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_CREATE_TIME", System.currentTimeMillis());
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_ACTION_TYPE", i2);
        intent.addFlags(268435456);
        int i4 = ((((np0.f8577a * 31) + 1) * 31) + i2) * 31;
        String str = np0.b;
        if (str != null) {
            i3 = str.hashCode();
        }
        action.actionIntent = PendingIntent.getBroadcast(applicationContext, ((i4 + i3) * 31) + np0.c, intent, i);
        this.f10452a.addAction(action);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 j(boolean z) {
        this.f10452a.setShowWhen(z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 k(C0571Jh0 jh0, int[] iArr) {
        Notification.MediaStyle mediaStyle = new Notification.MediaStyle();
        mediaStyle.setMediaSession(((MediaSession) jh0.b.h()).getSessionToken());
        mediaStyle.setShowActionsInCompactView(iArr);
        this.f10452a.setStyle(mediaStyle);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 l(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this.f10452a.addAction(new Notification.Action.Builder(Icon.createWithResource(this.b, i), charSequence, pendingIntent).build());
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 m(String str) {
        this.f10452a.setCategory(str);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 n(Notification notification) {
        this.f10452a.setPublicVersion(notification);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 o(int i) {
        if (Build.VERSION.SDK_INT < 26) {
            this.f10452a.setPriority(i);
        }
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 p(Icon icon) {
        this.f10452a.setSmallIcon(icon);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 q(boolean z) {
        this.f10452a.setLocalOnly(z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 r(String str) {
        this.f10452a.setGroup(str);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 s(Bundle bundle) {
        this.f10452a.addExtras(bundle);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 t(Bitmap bitmap) {
        this.f10452a.setLargeIcon(bitmap);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 u(boolean z) {
        this.f10452a.setOngoing(z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 v(boolean z) {
        this.f10452a.setGroupSummary(z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 w(CB0 cb0) {
        this.f10452a.setDeleteIntent(AbstractC0528Ip0.a(2, 0, this.c, cb0));
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 x(boolean z) {
        this.f10452a.setAutoCancel(z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 y(Notification.Action action) {
        this.f10452a.addAction(action);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public C3444kq0 z(String str) {
        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
        bigTextStyle.setBuilder(this.f10452a);
        bigTextStyle.bigText(str);
        return new C3444kq0(bigTextStyle.build(), this.c);
    }
}
