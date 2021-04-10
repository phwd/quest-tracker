package com.facebook.tigon.javaservice;

import com.facebook.tigon.iface.TigonRequest;

public interface JavaBackedTigonService {
    void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr);
}
