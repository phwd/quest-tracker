package com.apple.dnssd;

public interface RegisterListener extends BaseListener {
    void serviceRegistered(DNSSDRegistration dNSSDRegistration, int i, String str, String str2, String str3);
}
