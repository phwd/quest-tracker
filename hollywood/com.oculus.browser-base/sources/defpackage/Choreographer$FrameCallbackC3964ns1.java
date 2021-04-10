package defpackage;

import J.N;
import android.view.Choreographer;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.components.viz.service.frame_sinks.ExternalBeginFrameSourceAndroid;

/* renamed from: ns1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Choreographer$FrameCallbackC3964ns1 implements Choreographer.FrameCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4135os1 f10516a;

    public Choreographer$FrameCallbackC3964ns1(C4135os1 os1) {
        this.f10516a = os1;
    }

    public void doFrame(long j) {
        TraceEvent.Y("VSync", null);
        C4135os1 os1 = this.f10516a;
        if (os1.e && os1.b) {
            long j2 = os1.d;
            os1.d = j2 + ((long) (((float) ((j - os1.i) - j2)) * 0.1f));
        }
        os1.i = j;
        Objects.requireNonNull(os1);
        System.nanoTime();
        Objects.requireNonNull(os1);
        ThreadLocal threadLocal = C4135os1.f11033a;
        threadLocal.set(Boolean.TRUE);
        os1.f = false;
        try {
            ZM zm = os1.c;
            if (zm != null) {
                long j3 = j / 1000;
                ExternalBeginFrameSourceAndroid externalBeginFrameSourceAndroid = zm.f9340a;
                if (externalBeginFrameSourceAndroid.b) {
                    N.Mhc_M_H$(externalBeginFrameSourceAndroid.f10903a, externalBeginFrameSourceAndroid, j3, externalBeginFrameSourceAndroid.c.d / 1000);
                    zm.f9340a.c.a();
                }
            }
            threadLocal.set(Boolean.FALSE);
            TraceEvent.f0("VSync");
        } catch (Throwable th) {
            C4135os1.f11033a.set(Boolean.FALSE);
            throw th;
        }
    }
}
