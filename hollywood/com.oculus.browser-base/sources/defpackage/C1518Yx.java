package defpackage;

import J.N;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.SystemClock;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: Yx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1518Yx implements AbstractC5481wn0 {
    public AbstractC1457Xx F;
    public C1396Wx G;
    public final String H;
    public int I = 0;

    /* renamed from: J  reason: collision with root package name */
    public int f9307J = 0;
    public String K;
    public int L = 0;
    public int M;
    public Handler N;
    public Runnable O;

    public C1518Yx(AbstractC1457Xx xx, String str) {
        this.F = xx;
        this.H = str;
        this.G = new C1396Wx(this);
        this.N = new Handler();
        NetworkChangeNotifier.a(this);
        a(NetworkChangeNotifier.f11004a.getCurrentConnectionType());
    }

    @Override // defpackage.AbstractC5481wn0
    public void a(int i) {
        boolean z = this.I != i;
        this.I = i;
        StringBuilder i2 = AbstractC2531fV.i("onConnectionTypeChanged ");
        i2.append(this.I);
        AbstractC1220Ua0.d("OfflineIndicator", i2.toString(), new Object[0]);
        if (this.I == 6) {
            f(1);
            g();
        } else if (z || this.L == 0) {
            g();
            c();
        }
    }

    public void b() {
        Objects.requireNonNull(this.G);
        boolean z = this.L == 2;
        C1274Ux ux = new C1274Ux(this, z ? "https://www.google.com/generate_204" : "http://connectivitycheck.gstatic.com/generate_204", 5000, new C1213Tx(this));
        Executor executor = AbstractC2032cb.f9616a;
        ux.f();
        ((ExecutorC1463Ya) executor).execute(ux.e);
    }

    public final void c() {
        ConnectivityManager connectivityManager;
        int i = 1;
        this.L = 1;
        this.M = 0;
        SystemClock.elapsedRealtime();
        Objects.requireNonNull(this.G);
        if (!N.M09VlOh_("OfflineIndicatorAlwaysHttpProbe") && (connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")) != null) {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            if (allNetworks.length != 0) {
                int length = allNetworks.length;
                int i2 = 0;
                boolean z = false;
                while (true) {
                    if (i2 < length) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(allNetworks[i2]);
                        if (networkCapabilities != null) {
                            StringBuilder i3 = AbstractC2531fV.i("Reported by system: ");
                            i3.append(networkCapabilities.toString());
                            AbstractC1220Ua0.d("OfflineIndicator", i3.toString(), new Object[0]);
                            if (networkCapabilities.hasCapability(16) && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(13)) {
                                i = 4;
                                break;
                            } else if (networkCapabilities.hasCapability(17)) {
                                z = true;
                            }
                        }
                        i2++;
                    } else {
                        i = z ? 3 : 2;
                    }
                }
            }
        } else {
            i = 0;
        }
        if (i != 0) {
            f(i);
            d();
            return;
        }
        if (this.K == null) {
            this.K = N.M5LbL2nl();
        }
        this.L = 2;
        b();
    }

    public final void d() {
        StringBuilder i = AbstractC2531fV.i("processConnectivityCheckResult mConnectionState=");
        i.append(this.f9307J);
        i.append(" mConnectivityCheckingStage=");
        i.append(this.L);
        AbstractC1220Ua0.d("OfflineIndicator", i.toString(), new Object[0]);
        if (this.f9307J == 4) {
            g();
            return;
        }
        int i2 = this.L;
        if (i2 == 1) {
            e();
        } else if (i2 == 2) {
            this.L = 3;
            b();
        } else if (i2 == 3) {
            this.L = 2;
            e();
        }
    }

    public final void e() {
        AbstractC1220Ua0.d("OfflineIndicator", "scheduleNextConnectivityCheck", new Object[0]);
        int i = this.M;
        if (i == 0) {
            this.M = 5000;
        } else {
            this.M = i * 2;
        }
        if (this.M >= 120000) {
            this.M = 120000;
            AbstractC1220Ua0.d("OfflineIndicator", "No more retry after exceeding 120000ms", new Object[0]);
            if (this.f9307J == 0) {
                f(2);
                return;
            }
            return;
        }
        StringBuilder i2 = AbstractC2531fV.i("Retry after ");
        i2.append(this.M);
        i2.append("ms");
        AbstractC1220Ua0.d("OfflineIndicator", i2.toString(), new Object[0]);
        RunnableC1335Vx vx = new RunnableC1335Vx(this);
        this.O = vx;
        this.N.postDelayed(vx, (long) this.M);
    }

    public void f(int i) {
        AbstractC1220Ua0.d("OfflineIndicator", AbstractC2531fV.w("setConnectionState connectionState=", i), new Object[0]);
        if (this.f9307J != i) {
            this.f9307J = i;
            AbstractC1457Xx xx = this.F;
            if (xx != null) {
                xx.b(i);
            }
        }
    }

    public final void g() {
        if (this.L != 0) {
            Runnable runnable = this.O;
            if (runnable != null) {
                this.N.removeCallbacks(runnable);
                this.O = null;
            }
            this.L = 0;
        }
    }
}
