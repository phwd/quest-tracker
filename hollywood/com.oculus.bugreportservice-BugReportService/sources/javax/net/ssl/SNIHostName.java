package javax.net.ssl;

import java.util.Locale;

public final class SNIHostName extends SNIServerName {
    private final String hostname;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SNIHostName(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "Server name value of host_name cannot be null"
            java.util.Objects.requireNonNull(r3, r0)
            java.lang.String r3 = (java.lang.String) r3
            r0 = 2
            java.net.IDN.toASCII(r3, r0)
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.US_ASCII
            byte[] r0 = r3.getBytes(r0)
            r1 = 0
            r2.<init>(r1, r0)
            r2.hostname = r3
            r2.checkHostName()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.net.ssl.SNIHostName.<init>(java.lang.String):void");
    }

    @Override // javax.net.ssl.SNIServerName
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SNIHostName) {
            return this.hostname.equalsIgnoreCase(((SNIHostName) obj).hostname);
        }
        return false;
    }

    @Override // javax.net.ssl.SNIServerName
    public int hashCode() {
        return 527 + this.hostname.toUpperCase(Locale.ENGLISH).hashCode();
    }

    @Override // javax.net.ssl.SNIServerName
    public String toString() {
        return "type=host_name (0), value=" + this.hostname;
    }

    private void checkHostName() {
        if (this.hostname.isEmpty()) {
            throw new IllegalArgumentException("Server name value of host_name cannot be empty");
        } else if (this.hostname.endsWith(".")) {
            throw new IllegalArgumentException("Server name value of host_name cannot have the trailing dot");
        }
    }
}
