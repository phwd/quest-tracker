package com.apple.dnssd;

public interface RegisterRecordListener extends BaseListener {
    void recordRegistered(DNSRecord dNSRecord, int i);
}
