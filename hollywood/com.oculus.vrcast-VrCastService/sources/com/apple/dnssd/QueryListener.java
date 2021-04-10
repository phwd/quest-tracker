package com.apple.dnssd;

public interface QueryListener extends BaseListener {
    void queryAnswered(DNSSDService dNSSDService, int i, int i2, String str, int i3, int i4, byte[] bArr, int i5);
}
