package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* renamed from: xe0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5624xe0 {

    /* renamed from: a  reason: collision with root package name */
    public Service f11621a;
    public AbstractC4944te0 b;
    public SparseArray c;
    public AbstractC3615lq0 d;
    public C0074Be0 e;
    public C0571Jh0 f;
    public C5454we0 g;
    public final AbstractC5803yh0 h = new C4774se0(this);

    public C5624xe0(AbstractC4944te0 te0) {
        this.b = te0;
        SparseArray sparseArray = new SparseArray();
        this.c = sparseArray;
        sparseArray.put(0, new C5114ue0(R.drawable.f32500_resource_name_obfuscated_RES_2131231290, R.string.f45710_resource_name_obfuscated_RES_2131951888, "org.chromium.components.browser_ui.media.ACTION_PLAY"));
        this.c.put(1, new C5114ue0(R.drawable.f32390_resource_name_obfuscated_RES_2131231279, R.string.f45690_resource_name_obfuscated_RES_2131951886, "org.chromium.components.browser_ui.media.ACTION_PAUSE"));
        this.c.put(7, new C5114ue0(R.drawable.f32730_resource_name_obfuscated_RES_2131231313, R.string.f45820_resource_name_obfuscated_RES_2131951899, "org.chromium.components.browser_ui.media.ACTION_STOP"));
        this.c.put(2, new C5114ue0(R.drawable.f32720_resource_name_obfuscated_RES_2131231312, R.string.f45740_resource_name_obfuscated_RES_2131951891, "org.chromium.components.browser_ui.media.ACTION_PREVIOUS_TRACK"));
        this.c.put(3, new C5114ue0(R.drawable.f32710_resource_name_obfuscated_RES_2131231311, R.string.f45600_resource_name_obfuscated_RES_2131951877, "org.chromium.components.browser_ui.media.ACTION_NEXT_TRACK"));
        this.c.put(5, new C5114ue0(R.drawable.f30200_resource_name_obfuscated_RES_2131231060, R.string.f45790_resource_name_obfuscated_RES_2131951896, "org.chromium.components.browser_ui.media.ACTION_SEEK_FORWARD"));
        this.c.put(4, new C5114ue0(R.drawable.f30210_resource_name_obfuscated_RES_2131231061, R.string.f45780_resource_name_obfuscated_RES_2131951895, "MediaNotificationmanager.ListenerService.SEEK_BACKWARD"));
        this.g = new C5454we0(this);
    }

    public static boolean d(Service service, C3444kq0 kq0) {
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        int i = kq0.b.c;
        Notification notification = kq0.f10306a;
        if (notification == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            service.startForeground(i, notification, 0);
            return true;
        }
        service.startForeground(i, notification);
        return true;
    }

    public static boolean g(C0074Be0 be0, C0074Be0 be02) {
        Set set = be02.n;
        if ((set != null && set.isEmpty()) || be02.equals(be0)) {
            return true;
        }
        if (!be02.c || be0 == null || be02.e == be0.e) {
            return false;
        }
        return true;
    }

    public void a(int i) {
        C0571Jh0 jh0;
        C0074Be0 be0 = this.e;
        if (be0 != null && be0.e == i && be0.a() && !this.e.c && (jh0 = this.f) != null) {
            jh0.d(true);
        }
    }

    public void b() {
        C5454we0 we0 = this.g;
        we0.b.removeCallbacks(we0.c);
        we0.d = null;
        we0.c = null;
        if (this.e != null) {
            C0650Kp0 kp0 = new C0650Kp0(ContextUtils.getApplicationContext());
            kp0.e.cancel(null, this.e.k);
            C0571Jh0 jh0 = this.f;
            if (jh0 != null) {
                jh0.e(null, null);
                this.f.d(false);
                this.f.b.a();
                this.f = null;
            }
            h();
            this.e = null;
            this.d = null;
        }
    }

    public final PendingIntent c(String str) {
        return PendingIntent.getService(ContextUtils.getApplicationContext(), 0, ((C1323Vr) this.b).a().setAction(str), 268435456);
    }

    public void e(int i) {
        C0074Be0 be0 = this.e;
        if (be0 != null) {
            be0.m.d(i);
        }
    }

    public void f(int i) {
        C0074Be0 be0 = this.e;
        if (be0 != null && !be0.c) {
            be0.m.a(i);
        }
    }

    public void h() {
        Service service = this.f11621a;
        if (service != null) {
            try {
                service.stopForeground(1);
            } catch (NullPointerException e2) {
                AbstractC1220Ua0.a("ForegroundService", "Failed to stop foreground service, ", e2);
            }
            this.f11621a.stopSelf();
        }
    }

    public void i() {
        MediaMetadataCompat mediaMetadataCompat;
        long j;
        long j2;
        float f2;
        if (this.e.a()) {
            if (this.f == null) {
                Context applicationContext = ContextUtils.getApplicationContext();
                Objects.requireNonNull((C1323Vr) this.b);
                C0571Jh0 jh0 = new C0571Jh0(applicationContext, ContextUtils.getApplicationContext().getString(R.string.f46950_resource_name_obfuscated_RES_2131952012), null, null);
                jh0.e(this.h, null);
                jh0.d(true);
                this.f = jh0;
            }
            a(this.e.e);
            AbstractC4944te0 te0 = this.b;
            C0571Jh0 jh02 = this.f;
            Objects.requireNonNull((C1323Vr) te0);
            C3246jh0.e(ContextUtils.getApplicationContext()).l(jh02);
            C0571Jh0 jh03 = this.f;
            C4604re0 re0 = new C4604re0();
            C0074Be0 be0 = this.e;
            if (be0.f) {
                mediaMetadataCompat = re0.a();
            } else {
                re0.d("android.media.metadata.TITLE", be0.b.f11009a);
                re0.d("android.media.metadata.ARTIST", this.e.d);
                if (!TextUtils.isEmpty(this.e.b.b)) {
                    re0.d("android.media.metadata.ARTIST", this.e.b.b);
                }
                if (!TextUtils.isEmpty(this.e.b.c)) {
                    re0.d("android.media.metadata.ALBUM", this.e.b.c);
                }
                Bitmap bitmap = this.e.j;
                if (bitmap != null) {
                    re0.b("android.media.metadata.ALBUM_ART", bitmap);
                }
                MediaPosition mediaPosition = this.e.o;
                if (mediaPosition != null) {
                    re0.c("android.media.metadata.DURATION", mediaPosition.f11010a.longValue());
                }
                mediaMetadataCompat = re0.a();
            }
            jh03.b.k(mediaMetadataCompat);
            C0571Jh0 jh04 = this.f;
            ArrayList arrayList = new ArrayList();
            long j3 = 6;
            Set set = this.e.n;
            if (set != null) {
                if (set.contains(2)) {
                    j3 = 22;
                }
                if (set.contains(3)) {
                    j3 |= 32;
                }
                if (set.contains(5)) {
                    j3 |= 64;
                }
                if (set.contains(4)) {
                    j3 |= 8;
                }
                if (set.contains(8)) {
                    j3 |= 256;
                }
            }
            C0074Be0 be02 = this.e;
            int i = be02.c ? 2 : 3;
            MediaPosition mediaPosition2 = be02.o;
            if (mediaPosition2 != null) {
                j2 = mediaPosition2.b.longValue();
                f2 = this.e.o.c.floatValue();
                j = this.e.o.d.longValue();
            } else {
                j2 = -1;
                f2 = 1.0f;
                j = SystemClock.elapsedRealtime();
            }
            jh04.b.g(new PlaybackStateCompat(i, j2, 0, f2, j3, 0, null, j, arrayList, -1, null));
        }
    }

    public void j(boolean z, boolean z2) {
        Notification notification;
        Service service = this.f11621a;
        if (service != null) {
            if (this.e != null) {
                i();
                k();
                C3444kq0 b2 = this.d.b();
                boolean z3 = z && d(this.f11621a, b2);
                C0074Be0 be0 = this.e;
                if (((be0.f7746a & 4) != 0) && be0.c) {
                    try {
                        this.f11621a.stopForeground(2);
                    } catch (NullPointerException e2) {
                        AbstractC1220Ua0.a("ForegroundService", "Failed to stop foreground service, ", e2);
                    }
                    NotificationManager notificationManager = (NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification");
                    if (b2 == null || (notification = b2.f10306a) == null) {
                        AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
                    } else {
                        C0832Np0 np0 = b2.b;
                        notificationManager.notify(np0.b, np0.c, notification);
                    }
                } else if (!z3) {
                    Service service2 = this.f11621a;
                    int i = be0.k;
                    Notification notification2 = b2.f10306a;
                    if (notification2 != null) {
                        if (Build.VERSION.SDK_INT >= 29) {
                            service2.startForeground(i, notification2, 0);
                        } else {
                            service2.startForeground(i, notification2);
                        }
                    }
                }
                if (z2) {
                    Objects.requireNonNull((C1323Vr) this.b);
                    AbstractC3102iq0.f10166a.b(6, b2.f10306a);
                }
            } else if (z) {
                d(service, AbstractC3786mq0.b(true, "media", null, new C0832Np0(6, null, ((C1323Vr) this.b).b)).b());
                try {
                    this.f11621a.stopForeground(1);
                } catch (NullPointerException e3) {
                    AbstractC1220Ua0.a("ForegroundService", "Failed to stop foreground service, ", e3);
                }
            }
        }
    }

    public void k() {
        int[] iArr;
        String str;
        AbstractC3615lq0 b2 = AbstractC3786mq0.b(true, "media", null, new C0832Np0(6, null, ((C1323Vr) this.b).b));
        this.d = b2;
        C0074Be0 be0 = this.e;
        if (be0.f) {
            b2.H(ContextUtils.getApplicationContext().getResources().getString(R.string.f54420_resource_name_obfuscated_RES_2131952759));
            b2.g(ContextUtils.getApplicationContext().getResources().getString(R.string.f56180_resource_name_obfuscated_RES_2131952935));
        } else {
            b2.H(be0.b.f11009a);
            MediaMetadata mediaMetadata = this.e.b;
            String str2 = mediaMetadata.b;
            String str3 = "";
            if (str2 == null) {
                str2 = str3;
            }
            String str4 = mediaMetadata.c;
            if (str4 != null) {
                str3 = str4;
            }
            if (str2.isEmpty() || str3.isEmpty()) {
                str = AbstractC2531fV.f(str2, str3);
            } else {
                str = AbstractC2531fV.g(str2, " - ", str3);
            }
            b2.F(str);
            b2.g(this.e.d);
        }
        if (!this.e.a()) {
            b2.t(null);
        } else {
            C0074Be0 be02 = this.e;
            Bitmap bitmap = be02.h;
            if (bitmap != null && !be02.f) {
                b2.t(bitmap);
            }
        }
        HashSet hashSet = new HashSet();
        boolean z = false;
        if (this.e.a()) {
            Set set = this.e.n;
            if (set != null) {
                hashSet.addAll(set);
            }
            if (this.e.c) {
                hashSet.remove(1);
                hashSet.add(0);
            } else {
                hashSet.remove(0);
                hashSet.add(1);
            }
        }
        if (((this.e.f7746a & 2) != 0 ? 1 : null) != null) {
            hashSet.add(7);
        } else {
            hashSet.remove(7);
        }
        int[] iArr2 = {2, 4, 0, 1, 5, 3, 7};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            int i2 = iArr2[i];
            if (hashSet.contains(Integer.valueOf(i2))) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C5114ue0 ue0 = (C5114ue0) this.c.get(((Integer) it.next()).intValue());
            b2.l(ue0.f11425a, ContextUtils.getApplicationContext().getResources().getString(ue0.b), c(ue0.c));
        }
        if (this.e.a()) {
            C0571Jh0 jh0 = this.f;
            if (arrayList.size() <= 3) {
                iArr = new int[arrayList.size()];
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    iArr[i3] = i3;
                }
            } else if (arrayList.contains(7)) {
                ArrayList arrayList2 = new ArrayList();
                if (arrayList.contains(0)) {
                    arrayList2.add(Integer.valueOf(arrayList.indexOf(0)));
                }
                arrayList2.add(Integer.valueOf(arrayList.indexOf(7)));
                iArr = AbstractC0417Gv.b(arrayList2);
            } else {
                int[] iArr3 = new int[3];
                if (!arrayList.contains(2) || !arrayList.contains(3)) {
                    iArr3[0] = arrayList.indexOf(4);
                    if (arrayList.contains(0)) {
                        iArr3[1] = arrayList.indexOf(0);
                    } else {
                        iArr3[1] = arrayList.indexOf(1);
                    }
                    iArr3[2] = arrayList.indexOf(5);
                } else {
                    iArr3[0] = arrayList.indexOf(2);
                    if (arrayList.contains(0)) {
                        iArr3[1] = arrayList.indexOf(0);
                    } else {
                        iArr3[1] = arrayList.indexOf(1);
                    }
                    iArr3[2] = arrayList.indexOf(3);
                }
                iArr = iArr3;
            }
            b2.k(jh0, iArr);
        }
        this.d.j(false).f(0);
        this.d.A(this.e.g);
        this.d.x(false);
        this.d.q(true);
        this.d.r(((C1079Rr) C1323Vr.f9110a.get(((C1323Vr) this.b).b)).b);
        this.d.v(true);
        C0074Be0 be03 = this.e;
        if ((be03.f7746a & 4) != 0) {
            z = true;
        }
        if (z) {
            this.d.u(!be03.c);
            this.d.K(c("org.chromium.components.browser_ui.media.ACTION_SWIPE"));
        }
        if (this.e.l != null) {
            AbstractC3615lq0 lq0 = this.d;
            Context applicationContext = ContextUtils.getApplicationContext();
            C0074Be0 be04 = this.e;
            lq0.I(PendingIntent.getActivity(applicationContext, be04.e, be04.l, 134217728));
        }
        this.d.d(1 ^ (this.e.f ? 1 : 0));
    }
}
