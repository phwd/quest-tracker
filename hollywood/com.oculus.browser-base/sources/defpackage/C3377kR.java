package defpackage;

import com.google.android.gms.cast.MediaInfo;
import java.util.Objects;
import org.chromium.components.media_router.FlingingControllerBridge;

/* renamed from: kR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3377kR implements AbstractC2352eR {

    /* renamed from: a  reason: collision with root package name */
    public final N21 f10277a = new N21();
    public final QL0 b;
    public final String c;
    public FlingingControllerBridge d;
    public boolean e;
    public boolean f;

    public C3377kR(QL0 ql0, String str) {
        this.b = ql0;
        this.c = str;
    }

    public final void a(AM0 am0) {
        if (!am0.b().x()) {
            AbstractC1220Ua0.a("FlingCtrlAdptr", "Error when sending command. Status code: %d", Integer.valueOf(am0.b().K));
        }
    }

    public void b(long j) {
        if (this.b.h()) {
            this.e = true;
            String str = this.c;
            MediaInfo mediaInfo = new MediaInfo(str, -1, null, null, -1, null, null, null, null, null, null, null, -1);
            if (str != null) {
                mediaInfo.H = "*/*";
                Objects.requireNonNull(mediaInfo);
                mediaInfo.G = 1;
                ML0 e2 = this.b.e();
                Objects.requireNonNull(e2);
                Boolean bool = Boolean.TRUE;
                if (Double.compare(1.0d, 2.0d) > 0 || Double.compare(1.0d, 0.5d) < 0) {
                    throw new IllegalArgumentException("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
                }
                C4092oe0 oe0 = new C4092oe0(mediaInfo, null, bool, j, 1.0d, null, null, null, null, null);
                SE0.e("Must be called from the main thread.");
                if (!e2.w()) {
                    ML0.s(17, null);
                } else {
                    e2.t(new KC1(e2, e2.g, oe0));
                }
            } else {
                throw new IllegalArgumentException("contentID cannot be null");
            }
        }
    }
}
