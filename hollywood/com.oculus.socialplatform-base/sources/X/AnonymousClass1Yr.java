package X;

import com.facebook.msys.mci.Analytics;
import com.facebook.msys.mci.Crypto;
import com.facebook.msys.mci.MediaTranscoder;
import com.facebook.msys.mci.Settings;
import com.facebook.msys.mci.UUID;
import com.facebook.msys.util.Provider;
import javax.annotation.Nullable;

/* renamed from: X.1Yr  reason: invalid class name */
public class AnonymousClass1Yr {
    @Nullable
    public Provider<Analytics> A00;
    @Nullable
    public Provider<MediaTranscoder> A01;
    public final Provider<Crypto> A02;
    public final Provider<Settings> A03;
    public final Provider<UUID> A04;

    public AnonymousClass1Yr(Provider<Crypto> provider, Provider<Settings> provider2, Provider<UUID> provider3) {
        this.A02 = provider;
        this.A03 = provider2;
        this.A04 = provider3;
    }
}
