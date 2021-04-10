package java.net;

/* access modifiers changed from: package-private */
/* compiled from: URL */
public final class UrlDeserializedState {
    private final String authority;
    private final String file;
    private final int hashCode;
    private final String host;
    private final int port;
    private final String protocol;
    private final String ref;

    public UrlDeserializedState(String protocol2, String host2, int port2, String authority2, String file2, String ref2, int hashCode2) {
        this.protocol = protocol2;
        this.host = host2;
        this.port = port2;
        this.authority = authority2;
        this.file = file2;
        this.ref = ref2;
        this.hashCode = hashCode2;
    }

    /* access modifiers changed from: package-private */
    public String getProtocol() {
        return this.protocol;
    }

    /* access modifiers changed from: package-private */
    public String getHost() {
        return this.host;
    }

    /* access modifiers changed from: package-private */
    public String getAuthority() {
        return this.authority;
    }

    /* access modifiers changed from: package-private */
    public int getPort() {
        return this.port;
    }

    /* access modifiers changed from: package-private */
    public String getFile() {
        return this.file;
    }

    /* access modifiers changed from: package-private */
    public String getRef() {
        return this.ref;
    }

    /* access modifiers changed from: package-private */
    public int getHashCode() {
        return this.hashCode;
    }

    /* access modifiers changed from: package-private */
    public String reconstituteUrlString() {
        int len = this.protocol.length() + 1;
        String str = this.authority;
        if (str != null && str.length() > 0) {
            len += this.authority.length() + 2;
        }
        String str2 = this.file;
        if (str2 != null) {
            len += str2.length();
        }
        String str3 = this.ref;
        if (str3 != null) {
            len += str3.length() + 1;
        }
        StringBuilder result = new StringBuilder(len);
        result.append(this.protocol);
        result.append(":");
        String str4 = this.authority;
        if (str4 != null && str4.length() > 0) {
            result.append("//");
            result.append(this.authority);
        }
        String str5 = this.file;
        if (str5 != null) {
            result.append(str5);
        }
        if (this.ref != null) {
            result.append("#");
            result.append(this.ref);
        }
        return result.toString();
    }
}
