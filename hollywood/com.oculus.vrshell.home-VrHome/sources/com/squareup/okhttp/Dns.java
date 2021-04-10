package com.squareup.okhttp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public interface Dns {
    public static final Dns SYSTEM = new Dns() {
        /* class com.squareup.okhttp.Dns.AnonymousClass1 */

        @Override // com.squareup.okhttp.Dns
        public List<InetAddress> lookup(String hostname) throws UnknownHostException {
            if (hostname != null) {
                return Arrays.asList(InetAddress.getAllByName(hostname));
            }
            throw new UnknownHostException("hostname == null");
        }
    };

    List<InetAddress> lookup(String str) throws UnknownHostException;
}
