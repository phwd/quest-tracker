package com.google.android.gms.cast.framework.media;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaNotificationService extends Service {
    public static final NF1 F = new NF1("MediaNotificationService");
    public List G = new ArrayList();
    public C0318Fe0 H;
    public AbstractC0257Ee0 I;

    /* renamed from: J  reason: collision with root package name */
    public C1557Zm f9652J;
    public final BroadcastReceiver K = new QG1(this);

    public final void a() {
        if (this.H != null) {
            AbstractC0257Ee0 ee0 = this.I;
            new C0223Dp0(this, "cast_media_notification").g(null);
            throw null;
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        C1557Zm c = C1557Zm.c(this);
        this.f9652J = c;
        Objects.requireNonNull(c);
        SE0.e("Must be called from the main thread.");
        throw null;
    }

    public void onDestroy() {
        ((NotificationManager) getSystemService("notification")).cancel(1);
        Objects.requireNonNull(this.f9652J);
        SE0.e("Must be called from the main thread.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a7, code lost:
        if ((r1 != null && r2 == r1.b && r3 == r1.c && defpackage.GF1.a(r7, r1.d) && defpackage.GF1.a(r8, r1.e) && r13 == r1.f && r12 == r1.g) == false) goto L_0x00a9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r18, int r19, int r20) {
        /*
        // Method dump skipped, instructions count: 207
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.MediaNotificationService.onStartCommand(android.content.Intent, int, int):int");
    }
}
