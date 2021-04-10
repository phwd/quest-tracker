package org.chromium.services.media_session;

import android.os.SystemClock;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaPosition {

    /* renamed from: a  reason: collision with root package name */
    public Long f11010a;
    public Long b;
    public Float c;
    public Long d;

    public MediaPosition(long j, long j2, float f, long j3) {
        this.f11010a = Long.valueOf(j);
        this.b = Long.valueOf(j2);
        this.c = Float.valueOf(f);
        this.d = Long.valueOf(j3);
    }

    public static MediaPosition create(long j, long j2, float f, long j3) {
        return new MediaPosition(j, j2, f, j3 - (System.currentTimeMillis() - SystemClock.elapsedRealtime()));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaPosition)) {
            return false;
        }
        MediaPosition mediaPosition = (MediaPosition) obj;
        return this.f11010a.longValue() == mediaPosition.f11010a.longValue() && this.b.longValue() == mediaPosition.b.longValue() && this.c.floatValue() == mediaPosition.c.floatValue() && this.d.longValue() == mediaPosition.d.longValue();
    }

    public int hashCode() {
        int hashCode = this.b.hashCode();
        int hashCode2 = this.c.hashCode();
        return this.d.hashCode() + ((hashCode2 + ((hashCode + (this.f11010a.hashCode() * 31)) * 31)) * 31);
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("duration=");
        i.append(this.f11010a);
        i.append(", position=");
        i.append(this.b);
        i.append(", rate=");
        i.append(this.c);
        i.append(", updated=");
        i.append(this.d);
        return i.toString();
    }
}
