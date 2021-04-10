package com.apple.dnssd;

public interface DNSSDRegistration extends DNSSDService {
    DNSRecord addRecord(int i, int i2, byte[] bArr, int i3) throws DNSSDException;

    DNSRecord getTXTRecord() throws DNSSDException;
}
