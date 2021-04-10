package X;

import com.facebook.annotations.DoNotInline;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.common.build.BuildConfig;
import com.oculus.util.network.NetworkUtils;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0HJ  reason: invalid class name */
public final class AnonymousClass0HJ {
    public static final String[] A00 = {"aura", "browser", "videoplayer", "adnw", "quicksilver", NetworkUtils.WIFI, "papaya", "remotecodec"};

    @DoNotInline
    public static final boolean A02() {
        return false;
    }

    @DoNotInline
    public static final int A00() {
        return BuildConfig.VERSION_CODE;
    }

    @DoNotInline
    public static final int A01() {
        return BuildConfig.BUILD_ID;
    }
}
