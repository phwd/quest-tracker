package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.common.api.Status;
import com.oculus.os.Version;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: ML0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ML0 implements AbstractC1313Vm {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8471a;
    public final Handler b;
    public final MF1 c;
    public final IL0 d;
    public final C1069Rm e;
    public final C0684Le0 f;
    public YV g;
    public final List h = new CopyOnWriteArrayList();
    public final List i = new CopyOnWriteArrayList();
    public final Map j;

    static {
        String str = MF1.e;
    }

    public ML0(MF1 mf1, C1069Rm rm) {
        new ConcurrentHashMap();
        this.j = new ConcurrentHashMap();
        this.f8471a = new Object();
        this.b = new HandlerC2841hG1(Looper.getMainLooper());
        IL0 il0 = new IL0(this);
        this.d = il0;
        this.e = rm;
        this.c = mf1;
        mf1.i = new C4555rI1(this);
        mf1.c = il0;
        this.f = new C0684Le0(this);
    }

    public static DB0 s(int i2, String str) {
        JL0 jl0 = new JL0();
        jl0.f(new C1810bE1(new Status(1, i2, null, null)));
        return jl0;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // defpackage.AbstractC1313Vm
    public void a(CastDevice castDevice, String str, String str2) {
        char c2;
        int i2;
        MediaStatus mediaStatus;
        int[] l;
        char c3;
        MF1 mf1 = this.c;
        NF1 nf1 = mf1.f10060a;
        Object[] objArr = {str2};
        if (nf1.d()) {
            nf1.c("message received: %s", objArr);
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String string = jSONObject.getString("type");
            long optLong = jSONObject.optLong("requestId", -1);
            switch (string.hashCode()) {
                case -1830647528:
                    if (string.equals("LOAD_CANCELLED")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1790231854:
                    if (string.equals("QUEUE_ITEMS")) {
                        c2 = 7;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1125000185:
                    if (string.equals("INVALID_REQUEST")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -262628938:
                    if (string.equals("LOAD_FAILED")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 154411710:
                    if (string.equals("QUEUE_CHANGE")) {
                        c2 = 6;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 431600379:
                    if (string.equals("INVALID_PLAYER_STATE")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 823510221:
                    if (string.equals("MEDIA_STATUS")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 2107149050:
                    if (string.equals("QUEUE_ITEM_IDS")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                    JSONArray jSONArray = jSONObject.getJSONArray("status");
                    if (jSONArray.length() > 0) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                        boolean a2 = mf1.j.a(optLong);
                        int i3 = ((!mf1.o.f() || mf1.o.a(optLong)) && (!mf1.p.f() || mf1.p.a(optLong))) ? 0 : 1;
                        if (a2 || (mediaStatus = mf1.g) == null) {
                            MediaStatus mediaStatus2 = new MediaStatus(null, 0, 0, 0.0d, 0, 0, 0, 0, 0.0d, false, null, 0, 0, null, 0, null, false, null, null);
                            mediaStatus2.B(jSONObject2, 0);
                            mf1.g = mediaStatus2;
                            mf1.f = SystemClock.elapsedRealtime();
                            i2 = 127;
                        } else {
                            i2 = mediaStatus.B(jSONObject2, i3);
                        }
                        if ((i2 & 1) != 0) {
                            mf1.f = SystemClock.elapsedRealtime();
                            mf1.i();
                        }
                        if ((i2 & 2) != 0) {
                            mf1.f = SystemClock.elapsedRealtime();
                            mf1.i();
                        }
                        if ((i2 & 128) != 0) {
                            mf1.f = SystemClock.elapsedRealtime();
                        }
                        if ((i2 & 4) != 0) {
                            mf1.f();
                        }
                        if ((i2 & 8) != 0) {
                            mf1.h();
                        }
                        if ((i2 & 16) != 0) {
                            mf1.g();
                        }
                        if ((i2 & 32) != 0) {
                            mf1.f = SystemClock.elapsedRealtime();
                            C4555rI1 ri1 = mf1.i;
                            if (ri1 != null) {
                                for (C2490fD1 fd1 : ri1.f11197a.h) {
                                    fd1.a(false);
                                }
                                for (GL0 gl0 : ri1.f11197a.i) {
                                    Objects.requireNonNull(gl0);
                                }
                            }
                        }
                        if ((i2 & 64) != 0) {
                            mf1.f = SystemClock.elapsedRealtime();
                            mf1.i();
                        }
                    } else {
                        mf1.g = null;
                        mf1.i();
                        mf1.f();
                        mf1.h();
                        mf1.g();
                    }
                    for (QF1 qf1 : mf1.d) {
                        qf1.e(optLong, 0, null);
                    }
                    return;
                case 1:
                    NF1 nf12 = mf1.f10060a;
                    Log.w(nf12.f8536a, nf12.c("received unexpected error: Invalid Player State.", new Object[0]));
                    JSONObject optJSONObject = jSONObject.optJSONObject("customData");
                    for (QF1 qf12 : mf1.d) {
                        qf12.e(optLong, 2100, optJSONObject);
                    }
                    return;
                case 2:
                    mf1.j.e(optLong, 2100, jSONObject.optJSONObject("customData"));
                    return;
                case 3:
                    mf1.j.e(optLong, 2101, jSONObject.optJSONObject("customData"));
                    return;
                case 4:
                    NF1 nf13 = mf1.f10060a;
                    Log.w(nf13.f8536a, nf13.c("received unexpected error: Invalid Request.", new Object[0]));
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("customData");
                    for (QF1 qf13 : mf1.d) {
                        qf13.e(optLong, 2100, optJSONObject2);
                    }
                    return;
                case 5:
                    mf1.x.e(optLong, 0, null);
                    if (!(mf1.i == null || (l = MF1.l(jSONObject.getJSONArray("itemIds"))) == null)) {
                        mf1.i.a(l);
                        return;
                    }
                    return;
                case 6:
                    mf1.z.e(optLong, 0, null);
                    if (mf1.i != null) {
                        String string2 = jSONObject.getString("changeType");
                        int[] l2 = MF1.l(jSONObject.getJSONArray("itemIds"));
                        int optInt = jSONObject.optInt("insertBefore", 0);
                        if (l2 != null) {
                            switch (string2.hashCode()) {
                                case -2130463047:
                                    if (string2.equals("INSERT")) {
                                        c3 = 0;
                                        break;
                                    }
                                    c3 = 65535;
                                    break;
                                case -1881281404:
                                    if (string2.equals("REMOVE")) {
                                        c3 = 2;
                                        break;
                                    }
                                    c3 = 65535;
                                    break;
                                case -1785516855:
                                    if (string2.equals("UPDATE")) {
                                        c3 = 3;
                                        break;
                                    }
                                    c3 = 65535;
                                    break;
                                case 1122976047:
                                    if (string2.equals("ITEMS_CHANGE")) {
                                        c3 = 1;
                                        break;
                                    }
                                    c3 = 65535;
                                    break;
                                case 1395699694:
                                    if (string2.equals("NO_CHANGE")) {
                                        c3 = 4;
                                        break;
                                    }
                                    c3 = 65535;
                                    break;
                                default:
                                    c3 = 65535;
                                    break;
                            }
                            if (c3 == 0) {
                                for (GL0 gl02 : mf1.i.f11197a.i) {
                                    gl02.d(l2, optInt);
                                }
                                return;
                            } else if (c3 == 1) {
                                for (GL0 gl03 : mf1.i.f11197a.i) {
                                    gl03.e(l2);
                                }
                                return;
                            } else if (c3 == 2) {
                                for (GL0 gl04 : mf1.i.f11197a.i) {
                                    gl04.g(l2);
                                }
                                return;
                            } else if (c3 == 3) {
                                mf1.i.a(l2);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    mf1.y.e(optLong, 0, null);
                    if (mf1.i != null) {
                        JSONArray jSONArray2 = jSONObject.getJSONArray("items");
                        MediaQueueItem[] mediaQueueItemArr = new MediaQueueItem[jSONArray2.length()];
                        for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                            MediaQueueItem mediaQueueItem = new MediaQueueItem(jSONArray2.getJSONObject(i4));
                            if (mediaQueueItem.F == null) {
                                throw new IllegalArgumentException("media cannot be null.");
                            } else if (!Double.isNaN(mediaQueueItem.I) && mediaQueueItem.I < 0.0d) {
                                throw new IllegalArgumentException("startTime cannot be negative or NaN.");
                            } else if (Double.isNaN(mediaQueueItem.f9644J)) {
                                throw new IllegalArgumentException("playbackDuration cannot be NaN.");
                            } else if (Double.isNaN(mediaQueueItem.K) || mediaQueueItem.K < 0.0d) {
                                throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
                            } else {
                                mediaQueueItemArr[i4] = mediaQueueItem;
                            }
                        }
                        for (GL0 gl05 : mf1.i.f11197a.i) {
                            gl05.f(mediaQueueItemArr);
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (JSONException e2) {
            NF1 nf14 = mf1.f10060a;
            Log.w(nf14.f8536a, nf14.c("Message is malformed (%s); ignoring: %s", e2.getMessage(), str2));
        }
    }

    public long b() {
        long c2;
        synchronized (this.f8471a) {
            SE0.e("Must be called from the main thread.");
            c2 = this.c.c();
        }
        return c2;
    }

    public MediaInfo c() {
        MediaInfo d2;
        synchronized (this.f8471a) {
            SE0.e("Must be called from the main thread.");
            d2 = this.c.d();
        }
        return d2;
    }

    public MediaStatus d() {
        MediaStatus mediaStatus;
        synchronized (this.f8471a) {
            SE0.e("Must be called from the main thread.");
            mediaStatus = this.c.g;
        }
        return mediaStatus;
    }

    public int e() {
        int i2;
        synchronized (this.f8471a) {
            SE0.e("Must be called from the main thread.");
            MediaStatus d2 = d();
            i2 = d2 != null ? d2.f9645J : 1;
        }
        return i2;
    }

    public long f() {
        long e2;
        synchronized (this.f8471a) {
            SE0.e("Must be called from the main thread.");
            e2 = this.c.e();
        }
        return e2;
    }

    public boolean g() {
        SE0.e("Must be called from the main thread.");
        return h() || l() || k() || j();
    }

    public boolean h() {
        SE0.e("Must be called from the main thread.");
        MediaStatus d2 = d();
        return d2 != null && d2.f9645J == 4;
    }

    public boolean i() {
        SE0.e("Must be called from the main thread.");
        MediaInfo c2 = c();
        return c2 != null && c2.G == 2;
    }

    public boolean j() {
        SE0.e("Must be called from the main thread.");
        MediaStatus d2 = d();
        return (d2 == null || d2.Q == 0) ? false : true;
    }

    public boolean k() {
        int i2;
        SE0.e("Must be called from the main thread.");
        MediaStatus d2 = d();
        if (d2 != null) {
            if (d2.f9645J == 3) {
                return true;
            }
            if (i()) {
                synchronized (this.f8471a) {
                    SE0.e("Must be called from the main thread.");
                    MediaStatus d3 = d();
                    i2 = d3 != null ? d3.K : 0;
                }
                if (i2 == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean l() {
        SE0.e("Must be called from the main thread.");
        MediaStatus d2 = d();
        return d2 != null && d2.f9645J == 2;
    }

    public boolean m() {
        SE0.e("Must be called from the main thread.");
        MediaStatus d2 = d();
        return d2 != null && d2.W;
    }

    public DB0 n() {
        SE0.e("Must be called from the main thread.");
        if (!w()) {
            return s(17, null);
        }
        C5731yD1 yd1 = new C5731yD1(this, this.g, null);
        t(yd1);
        return yd1;
    }

    public DB0 o() {
        SE0.e("Must be called from the main thread.");
        if (!w()) {
            return s(17, null);
        }
        FD1 fd1 = new FD1(this, this.g, null);
        t(fd1);
        return fd1;
    }

    public DB0 p() {
        SE0.e("Must be called from the main thread.");
        if (!w()) {
            return s(17, null);
        }
        C5746yI1 yi1 = new C5746yI1(this, this.g);
        t(yi1);
        return yi1;
    }

    @Deprecated
    public DB0 q(long j2) {
        C4953th0 th0 = new C4953th0(j2, 0, false, null, null);
        SE0.e("Must be called from the main thread.");
        if (!w()) {
            return s(17, null);
        }
        OD1 od1 = new OD1(this, this.g, th0);
        t(od1);
        return od1;
    }

    public void r() {
        SE0.e("Must be called from the main thread.");
        int e2 = e();
        if (e2 == 4 || e2 == 2) {
            n();
        } else {
            o();
        }
    }

    public final KL0 t(KL0 kl0) {
        try {
            this.g.f(kl0);
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (Throwable unused) {
            kl0.f(new C2664gE1(new Status(2100)));
        }
        return kl0;
    }

    public final void u(YV yv) {
        YV yv2 = this.g;
        if (yv2 != yv) {
            if (yv2 != null) {
                this.c.m();
                try {
                    C1069Rm rm = this.e;
                    YV yv3 = this.g;
                    SE0.e("Must be called from the main thread.");
                    String str = this.c.b;
                    Objects.requireNonNull(rm);
                    try {
                        ((C3350kF1) yv3.g(KF1.f8354a)).z(str);
                    } catch (RemoteException unused) {
                        throw new IOException("service error");
                    }
                } catch (IOException unused2) {
                }
                this.d.f8220a = null;
                this.b.removeCallbacksAndMessages(null);
            }
            this.g = yv;
            if (yv != null) {
                this.d.f8220a = yv;
            }
        }
    }

    public final void v() {
        YV yv = this.g;
        if (yv != null) {
            C1069Rm rm = this.e;
            SE0.e("Must be called from the main thread.");
            String str = this.c.b;
            Objects.requireNonNull(rm);
            try {
                ((C3350kF1) yv.g(KF1.f8354a)).A(str, this);
            } catch (RemoteException unused) {
                throw new IOException("service error");
            }
        }
    }

    public final boolean w() {
        return this.g != null;
    }
}
