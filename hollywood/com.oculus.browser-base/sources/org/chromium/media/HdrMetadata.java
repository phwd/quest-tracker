package org.chromium.media;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HdrMetadata {

    /* renamed from: a  reason: collision with root package name */
    public long f10973a;
    public final Object b = new Object();

    public HdrMetadata(long j) {
        this.f10973a = j;
    }

    public static HdrMetadata create(long j) {
        return new HdrMetadata(j);
    }

    public final void close() {
        synchronized (this.b) {
            this.f10973a = 0;
        }
    }
}
