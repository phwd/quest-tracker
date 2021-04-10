package org.chromium.content.browser;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RenderWidgetHostViewImpl implements UL0 {

    /* renamed from: a  reason: collision with root package name */
    public long f10916a;
    public Throwable b;

    public RenderWidgetHostViewImpl(long j) {
        this.f10916a = j;
    }

    public static RenderWidgetHostViewImpl create(long j) {
        return new RenderWidgetHostViewImpl(j);
    }

    public boolean a() {
        return this.f10916a == 0;
    }

    public final void clearNativePtr() {
        this.f10916a = 0;
        this.b = new RuntimeException("clearNativePtr");
    }
}
