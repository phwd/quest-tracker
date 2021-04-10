package X;

import android.media.MediaFormat;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.service_media.FileCapture;
import java.io.File;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1X9  reason: invalid class name */
public final class AnonymousClass1X9 {
    public int A00 = 0;
    @Nullable
    public MediaFormat A01 = null;
    @Nullable
    public MediaFormat A02 = null;
    @Nullable
    public File A03;
    public final int A04 = 17000000;
    public final long A05;
    public final boolean A06;

    public AnonymousClass1X9(File file) {
        this.A03 = file;
        this.A05 = FileCapture.DEFAULT_INSTANT_REPLAY_BITRATE;
        this.A06 = true;
    }
}
