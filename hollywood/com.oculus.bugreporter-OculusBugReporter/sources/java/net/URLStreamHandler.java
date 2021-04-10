package java.net;

import java.io.IOException;
import java.util.Objects;

public abstract class URLStreamHandler {
    /* access modifiers changed from: protected */
    public abstract URLConnection openConnection(URL url) throws IOException;

    /* access modifiers changed from: protected */
    public URLConnection openConnection(URL u, Proxy p) throws IOException {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0246  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x02b3  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x02c2 A[LOOP:1: B:120:0x02b9->B:122:0x02c2, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0366  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0372  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02dd A[EDGE_INSN: B:162:0x02dd->B:123:0x02dd ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0323 A[EDGE_INSN: B:165:0x0323->B:135:0x0323 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0347 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0242  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseURL(java.net.URL r27, java.lang.String r28, int r29, int r30) {
        /*
        // Method dump skipped, instructions count: 930
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.URLStreamHandler.parseURL(java.net.URL, java.lang.String, int, int):void");
    }

    /* access modifiers changed from: protected */
    public int getDefaultPort() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public boolean equals(URL u1, URL u2) {
        return Objects.equals(u1.getRef(), u2.getRef()) && Objects.equals(u1.getQuery(), u2.getQuery()) && sameFile(u1, u2);
    }

    /* access modifiers changed from: protected */
    public int hashCode(URL u) {
        return Objects.hash(u.getRef(), u.getQuery(), u.getProtocol(), u.getFile(), u.getHost(), Integer.valueOf(u.getPort()));
    }

    /* access modifiers changed from: protected */
    public boolean sameFile(URL u1, URL u2) {
        if (u1.getProtocol() != u2.getProtocol() && (u1.getProtocol() == null || !u1.getProtocol().equalsIgnoreCase(u2.getProtocol()))) {
            return false;
        }
        if (u1.getFile() != u2.getFile() && (u1.getFile() == null || !u1.getFile().equals(u2.getFile()))) {
            return false;
        }
        if ((u1.getPort() != -1 ? u1.getPort() : u1.handler.getDefaultPort()) == (u2.getPort() != -1 ? u2.getPort() : u2.handler.getDefaultPort()) && hostsEqual(u1, u2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0028, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0026 A[ExcHandler: SecurityException (e java.lang.SecurityException), Splitter:B:11:0x0019] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.net.InetAddress getHostAddress(java.net.URL r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.net.InetAddress r0 = r4.hostAddress     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0009
            java.net.InetAddress r0 = r4.hostAddress     // Catch:{ all -> 0x002e }
            monitor-exit(r3)
            return r0
        L_0x0009:
            java.lang.String r0 = r4.getHost()
            r1 = 0
            if (r0 == 0) goto L_0x002c
            java.lang.String r2 = ""
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0019
            goto L_0x002c
        L_0x0019:
            java.net.InetAddress r2 = java.net.InetAddress.getByName(r0)     // Catch:{ UnknownHostException -> 0x0029, SecurityException -> 0x0026 }
            r4.hostAddress = r2     // Catch:{ UnknownHostException -> 0x0024, SecurityException -> 0x0026 }
            java.net.InetAddress r1 = r4.hostAddress
            monitor-exit(r3)
            return r1
        L_0x0024:
            r2 = move-exception
            goto L_0x002a
        L_0x0026:
            r2 = move-exception
            monitor-exit(r3)
            return r1
        L_0x0029:
            r2 = move-exception
        L_0x002a:
            monitor-exit(r3)
            return r1
        L_0x002c:
            monitor-exit(r3)
            return r1
        L_0x002e:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.URLStreamHandler.getHostAddress(java.net.URL):java.net.InetAddress");
    }

    /* access modifiers changed from: protected */
    public boolean hostsEqual(URL u1, URL u2) {
        if (u1.getHost() == null || u2.getHost() == null) {
            return u1.getHost() == null && u2.getHost() == null;
        }
        return u1.getHost().equalsIgnoreCase(u2.getHost());
    }

    /* access modifiers changed from: protected */
    public String toExternalForm(URL u) {
        int len = u.getProtocol().length() + 1;
        if (u.getAuthority() != null && u.getAuthority().length() > 0) {
            len += u.getAuthority().length() + 2;
        }
        if (u.getPath() != null) {
            len += u.getPath().length();
        }
        if (u.getQuery() != null) {
            len += u.getQuery().length() + 1;
        }
        if (u.getRef() != null) {
            len += u.getRef().length() + 1;
        }
        StringBuilder result = new StringBuilder(len);
        result.append(u.getProtocol());
        result.append(":");
        if (u.getAuthority() != null) {
            result.append("//");
            result.append(u.getAuthority());
        }
        String fileAndQuery = u.getFile();
        if (fileAndQuery != null) {
            result.append(fileAndQuery);
        }
        if (u.getRef() != null) {
            result.append("#");
            result.append(u.getRef());
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public void setURL(URL u, String protocol, String host, int port, String authority, String userInfo, String path, String query, String ref) {
        if (this == u.handler) {
            u.set(u.getProtocol(), host, port, authority, userInfo, path, query, ref);
            return;
        }
        throw new SecurityException("handler for url different from this handler");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void setURL(URL u, String protocol, String host, int port, String file, String ref) {
        String userInfo;
        String authority;
        String host2;
        String query;
        String path;
        String authority2;
        if (host == null || host.length() == 0) {
            host2 = host;
            authority = null;
            userInfo = null;
        } else {
            if (port == -1) {
                authority2 = host;
            } else {
                authority2 = host + ":" + port;
            }
            int at = host.lastIndexOf(64);
            if (at != -1) {
                String userInfo2 = host.substring(0, at);
                host2 = host.substring(at + 1);
                authority = authority2;
                userInfo = userInfo2;
            } else {
                host2 = host;
                authority = authority2;
                userInfo = null;
            }
        }
        if (file != null) {
            int q = file.lastIndexOf(63);
            if (q != -1) {
                String query2 = file.substring(q + 1);
                path = file.substring(0, q);
                query = query2;
            } else {
                path = file;
                query = null;
            }
        } else {
            path = null;
            query = null;
        }
        setURL(u, protocol, host2, port, authority, userInfo, path, query, ref);
    }
}
