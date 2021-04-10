package X;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;

/* renamed from: X.1dV  reason: invalid class name and case insensitive filesystem */
public final class C09391dV extends PlatformServiceClient {
    @Override // com.facebook.internal.PlatformServiceClient
    public final void populateRequestBundle(Bundle bundle) {
    }

    public C09391dV(Context context, String str) {
        super(context, 65536, NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REPLY, NativeProtocol.PROTOCOL_VERSION_20121101, str);
    }
}
