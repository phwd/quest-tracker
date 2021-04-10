package sun.security.provider.certpath;

import java.security.cert.X509Certificate;

public class BuildStep {
    public static final int BACK = 2;
    public static final int FAIL = 4;
    public static final int FOLLOW = 3;
    public static final int POSSIBLE = 1;
    public static final int SUCCEED = 5;
    private X509Certificate cert;
    private int result;
    private Throwable throwable;
    private Vertex vertex;

    public BuildStep(Vertex vtx, int res) {
        this.vertex = vtx;
        Vertex vertex2 = this.vertex;
        if (vertex2 != null) {
            this.cert = vertex2.getCertificate();
            this.throwable = this.vertex.getThrowable();
        }
        this.result = res;
    }

    public Vertex getVertex() {
        return this.vertex;
    }

    public X509Certificate getCertificate() {
        return this.cert;
    }

    public String getIssuerName() {
        return getIssuerName(null);
    }

    public String getIssuerName(String defaultName) {
        X509Certificate x509Certificate = this.cert;
        if (x509Certificate == null) {
            return defaultName;
        }
        return x509Certificate.getIssuerX500Principal().toString();
    }

    public String getSubjectName() {
        return getSubjectName(null);
    }

    public String getSubjectName(String defaultName) {
        X509Certificate x509Certificate = this.cert;
        if (x509Certificate == null) {
            return defaultName;
        }
        return x509Certificate.getSubjectX500Principal().toString();
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public int getResult() {
        return this.result;
    }

    public String resultToString(int res) {
        if (res == 1) {
            return "Certificate to be tried.\n";
        }
        if (res == 2) {
            return "Certificate backed out since path does not satisfy build requirements.\n";
        }
        if (res == 3) {
            return "Certificate satisfies conditions.\n";
        }
        if (res == 4) {
            return "Certificate backed out since path does not satisfy conditions.\n";
        }
        if (res != 5) {
            return "Internal error: Invalid step result value.\n";
        }
        return "Certificate satisfies conditions.\n";
    }

    public String toString() {
        int i = this.result;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return "Internal Error: Invalid step result\n";
                        }
                    }
                }
            }
            String out = resultToString(this.result);
            return out + this.vertex.throwableToString();
        }
        return resultToString(this.result);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        if (r1 != 5) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String verboseToString() {
        /*
            r3 = this;
            int r0 = r3.getResult()
            java.lang.String r0 = r3.resultToString(r0)
            int r1 = r3.result
            r2 = 1
            if (r1 == r2) goto L_0x0046
            r2 = 2
            if (r1 == r2) goto L_0x0030
            r2 = 3
            if (r1 == r2) goto L_0x001a
            r2 = 4
            if (r1 == r2) goto L_0x0030
            r2 = 5
            if (r1 == r2) goto L_0x001a
            goto L_0x0047
        L_0x001a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            sun.security.provider.certpath.Vertex r2 = r3.vertex
            java.lang.String r2 = r2.moreToString()
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            goto L_0x0047
        L_0x0030:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            sun.security.provider.certpath.Vertex r2 = r3.vertex
            java.lang.String r2 = r2.throwableToString()
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            goto L_0x0047
        L_0x0046:
        L_0x0047:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = "Certificate contains:\n"
            r1.append(r2)
            sun.security.provider.certpath.Vertex r2 = r3.vertex
            java.lang.String r2 = r2.certToString()
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.BuildStep.verboseToString():java.lang.String");
    }

    public String fullToString() {
        return resultToString(getResult()) + this.vertex.toString();
    }
}
