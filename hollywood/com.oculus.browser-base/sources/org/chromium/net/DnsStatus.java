package org.chromium.net;

import java.net.InetAddress;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DnsStatus {

    /* renamed from: a  reason: collision with root package name */
    public final List f11001a;
    public final boolean b;
    public final String c;

    public DnsStatus(List list, boolean z, String str) {
        this.f11001a = list;
        this.b = z;
        this.c = str == null ? "" : str;
    }

    public byte[][] getDnsServers() {
        byte[][] bArr = new byte[this.f11001a.size()][];
        for (int i = 0; i < this.f11001a.size(); i++) {
            bArr[i] = ((InetAddress) this.f11001a.get(i)).getAddress();
        }
        return bArr;
    }

    public boolean getPrivateDnsActive() {
        return this.b;
    }

    public String getPrivateDnsServerName() {
        return this.c;
    }
}
