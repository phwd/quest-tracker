package org.chromium.components.viz.service.frame_sinks;

import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExternalBeginFrameSourceAndroid {

    /* renamed from: a  reason: collision with root package name */
    public final long f10903a;
    public boolean b;
    public final C4135os1 c;
    public final ZM d;

    public ExternalBeginFrameSourceAndroid(long j, float f) {
        ZM zm = new ZM(this);
        this.d = zm;
        this.f10903a = j;
        ContextUtils.getApplicationContext();
        this.c = new C4135os1(zm, f);
    }

    public final void setEnabled(boolean z) {
        if (this.b != z) {
            this.b = z;
            if (z) {
                this.c.a();
            }
        }
    }

    public final void updateRefreshRate(float f) {
        this.c.b(f);
    }
}
