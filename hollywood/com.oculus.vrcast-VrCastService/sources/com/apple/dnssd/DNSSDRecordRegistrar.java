package com.apple.dnssd;

public interface DNSSDRecordRegistrar extends DNSSDService {
    DNSRecord registerRecord(int i, int i2, String str, int i3, int i4, byte[] bArr, int i5) throws DNSSDException;
}
