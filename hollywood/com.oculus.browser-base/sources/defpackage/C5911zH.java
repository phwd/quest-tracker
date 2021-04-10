package defpackage;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.download.DownloadForegroundService;
import org.chromium.chrome.browser.download.DownloadNotificationServiceObserver;

/* renamed from: zH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5911zH {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f11737a = new Handler();
    public final Runnable b = new RunnableC5401wH(this);
    public boolean c;
    public int d = -1;
    public boolean e;
    public boolean f;
    public C5231vH g;
    public final Map h = new HashMap();
    public final ServiceConnection i = new ServiceConnectionC5571xH(this);

    public final void a() {
        Iterator it = this.h.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!b(((C5741yH) entry.getValue()).c) && ((C5741yH) entry.getValue()).f11673a != this.d) {
                it.remove();
            }
        }
    }

    public final boolean b(int i2) {
        return i2 == 0;
    }

    public void c(boolean z) {
        C5741yH yHVar;
        Iterator it = this.h.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                yHVar = null;
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (!b(((C5741yH) entry.getValue()).c)) {
                if (!it.hasNext()) {
                    yHVar = (C5741yH) entry.getValue();
                    break;
                }
            } else {
                yHVar = (C5741yH) entry.getValue();
                break;
            }
        }
        if (yHVar != null) {
            if (!this.e) {
                if (!b(yHVar.c)) {
                    a();
                    return;
                }
                Context context = yHVar.d;
                AbstractC1220Ua0.f("DownloadFg", "startAndBindService", new Object[0]);
                this.e = true;
                this.f = false;
                Intent intent = new Intent(context, DownloadForegroundService.class);
                Context applicationContext = ContextUtils.getApplicationContext();
                Object obj = K2.f8337a;
                if (Build.VERSION.SDK_INT >= 26) {
                    applicationContext.startForegroundService(intent);
                } else {
                    applicationContext.startService(intent);
                }
                context.bindService(new Intent(context, DownloadForegroundService.class), this.i, 1);
            } else if (this.g != null) {
                if (z) {
                    StringBuilder i2 = AbstractC2531fV.i("Starting service with type ");
                    i2.append(yHVar.c);
                    AbstractC1220Ua0.f("DownloadFg", i2.toString(), new Object[0]);
                    d(yHVar);
                    this.f11737a.removeCallbacks(this.b);
                    this.f11737a.postDelayed(this.b, 200);
                    this.c = true;
                }
                if (b(yHVar.c)) {
                    if (this.h.get(Integer.valueOf(this.d)) == null || !b(((C5741yH) this.h.get(Integer.valueOf(this.d))).c)) {
                        d(yHVar);
                    }
                    a();
                } else if (!this.c) {
                    int i3 = yHVar.c;
                    AbstractC1220Ua0.f("DownloadFg", AbstractC2531fV.w("stopAndUnbindService status: ", i3), new Object[0]);
                    Objects.requireNonNull(this.g);
                    this.e = false;
                    int i4 = i3 == 3 ? 0 : 1;
                    C5741yH yHVar2 = (C5741yH) this.h.get(Integer.valueOf(this.d));
                    int i5 = this.d;
                    C5231vH vHVar = this.g;
                    Objects.requireNonNull(vHVar);
                    AbstractC1220Ua0.f("DownloadFg", "stopDownloadForegroundService status: " + i4 + ", id: " + i5, new Object[0]);
                    AbstractC5404wI.b(2);
                    AbstractC5404wI.c(0);
                    if (i4 == 0) {
                        vHVar.i(1);
                    } else {
                        vHVar.i(2);
                    }
                    vHVar.f9437a.stopSelf();
                    ContextUtils.getApplicationContext().unbindService(this.i);
                    Object obj2 = ThreadUtils.f10596a;
                    Set a2 = AH.a();
                    String name = DownloadNotificationServiceObserver.class.getName();
                    if (a2.contains(name)) {
                        HashSet hashSet = new HashSet(a2);
                        hashSet.remove(name);
                        if (hashSet.size() == 0) {
                            NU0.f8549a.l("ForegroundServiceObservers");
                        } else {
                            NU0.f8549a.q("ForegroundServiceObservers", hashSet);
                        }
                    }
                    this.g = null;
                    this.d = -1;
                    a();
                } else {
                    AbstractC1220Ua0.f("DownloadFg", "Delaying call to stopAndUnbindService.", new Object[0]);
                }
            }
        }
    }

    public void d(C5741yH yHVar) {
        StringBuilder i2 = AbstractC2531fV.i("startOrUpdateForegroundService id: ");
        i2.append(yHVar.f11673a);
        AbstractC1220Ua0.f("DownloadFg", i2.toString(), new Object[0]);
        int i3 = yHVar.f11673a;
        Notification notification = yHVar.b;
        Notification notification2 = null;
        if (Build.VERSION.SDK_INT >= 26 && notification == null && !this.f) {
            notification = AbstractC3786mq0.b(true, "downloads", null, new C0832Np0(0, null, i3)).c();
        }
        if (this.g != null && i3 != -1 && notification != null) {
            C5741yH yHVar2 = (C5741yH) this.h.get(Integer.valueOf(this.d));
            if (yHVar2 != null) {
                notification2 = yHVar2.b;
            }
            boolean z = yHVar2 != null && yHVar2.c == 3;
            C5231vH vHVar = this.g;
            int i4 = this.d;
            Objects.requireNonNull(vHVar);
            AbstractC1220Ua0.f("DownloadFg", "startOrUpdateForegroundService new: " + i3 + ", old: " + i4 + ", kill old: " + z, new Object[0]);
            if (i4 == -1 && notification2 == null) {
                vHVar.h(i3, notification);
            } else {
                vHVar.i(z ? 1 : 2);
                vHVar.h(i3, notification);
            }
            if (i4 == -1) {
                AbstractC5404wI.b(0);
            } else if (i4 != i3) {
                AbstractC5404wI.b(1);
            }
            this.f = true;
            this.d = i3;
        }
    }

    public void e(Context context, int i2, int i3, Notification notification) {
        if (i2 != 0) {
            AbstractC1220Ua0.f("DownloadFg", "updateDownloadStatus status: " + i2 + ", id: " + i3, new Object[0]);
        }
        this.h.put(Integer.valueOf(i3), new C5741yH(i3, notification, i2, context));
        c(false);
    }
}
