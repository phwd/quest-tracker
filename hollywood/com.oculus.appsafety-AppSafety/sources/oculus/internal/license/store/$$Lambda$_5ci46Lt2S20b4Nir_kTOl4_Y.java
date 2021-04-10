package oculus.internal.license.store;

import java.security.cert.Certificate;
import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$_5-ci46Lt2S20b4Nir_kTOl4_-Y  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$_5ci46Lt2S20b4Nir_kTOl4_Y implements Function {
    public static final /* synthetic */ $$Lambda$_5ci46Lt2S20b4Nir_kTOl4_Y INSTANCE = new $$Lambda$_5ci46Lt2S20b4Nir_kTOl4_Y();

    private /* synthetic */ $$Lambda$_5ci46Lt2S20b4Nir_kTOl4_Y() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Certificate) obj).getPublicKey();
    }
}
