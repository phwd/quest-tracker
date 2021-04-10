package X;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.models.ModelLoader;
import com.facebook.models.ModelLoaderCallbacks;
import com.facebook.models.ModelMetadata;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;

/* renamed from: X.oI  reason: case insensitive filesystem */
public final class C0895oI implements ModelLoaderCallbacks {
    public final /* synthetic */ ModelLoader A00;
    public final /* synthetic */ SettableFuture A01;

    public C0895oI(ModelLoader modelLoader, SettableFuture settableFuture) {
        this.A00 = modelLoader;
        this.A01 = settableFuture;
    }

    @Override // com.facebook.models.ModelLoaderCallbacks
    public final void onError(String str) {
        Object[] objArr = {str};
        if (C0139Dd.A01.A3Y(5)) {
            C0139Dd.A0D(ModelLoader.class.getSimpleName(), StringFormatUtil.formatStrLocaleSafe("Failed to resolve ModelMetadata: %s", objArr));
        }
        this.A01.setException(new IOException(str));
    }

    @Override // com.facebook.models.ModelLoaderCallbacks
    public final void onResult(ModelMetadata modelMetadata) {
        this.A01.set(modelMetadata);
    }
}
