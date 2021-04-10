package X;

import com.facebook.acra.LogCatCollector;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.net.URLEncoder;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.sl  reason: case insensitive filesystem */
public final class C0512sl {
    public boolean A00 = false;
    public final KJ A01;

    public final void A00(String str, @Nullable String str2) throws IOException {
        if (this.A00) {
            this.A01.A5y("&");
        }
        KJ kj = this.A01;
        kj.A5y(URLEncoder.encode(str, LogCatCollector.UTF_8_ENCODING));
        kj.A5y("=");
        if (str2 != null) {
            kj.A5y(URLEncoder.encode(str2, LogCatCollector.UTF_8_ENCODING));
        }
        this.A00 = true;
    }

    public C0512sl(KJ kj) {
        this.A01 = kj;
    }
}
