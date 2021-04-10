package com.facebook.proxygen;

import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;

public class LigerResponse extends BasicHttpResponse {
    public LigerResponse(ProtocolVersion protocolVersion, int i, String str) {
        super(protocolVersion, i, str);
    }
}
