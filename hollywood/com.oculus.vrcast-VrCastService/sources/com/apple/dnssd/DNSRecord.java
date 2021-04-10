package com.apple.dnssd;

public interface DNSRecord {
    void remove() throws DNSSDException;

    void update(int i, byte[] bArr, int i2) throws DNSSDException;
}
