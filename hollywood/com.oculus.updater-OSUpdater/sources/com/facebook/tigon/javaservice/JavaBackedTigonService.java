package com.facebook.tigon.javaservice;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;

@DoNotStrip
public interface JavaBackedTigonService {
    @DoNotStrip
    void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr) throws IOException;
}
