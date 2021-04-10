package java.lang;

/* access modifiers changed from: package-private */
public final class ProcessImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private ProcessImpl() {
    }

    private static byte[] toCString(String s) {
        if (s == null) {
            return null;
        }
        byte[] bytes = s.getBytes();
        byte[] result = new byte[(bytes.length + 1)];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        result[result.length - 1] = 0;
        return result;
    }

    /* JADX INFO: Multiple debug info for r4v2 byte[]: [D('i' int), D('argBlock' byte[])] */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0153 A[SYNTHETIC, Splitter:B:76:0x0153] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x016f A[SYNTHETIC, Splitter:B:92:0x016f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Process start(java.lang.String[] r21, java.util.Map<java.lang.String, java.lang.String> r22, java.lang.String r23, java.lang.ProcessBuilder.Redirect[] r24, boolean r25) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 386
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.ProcessImpl.start(java.lang.String[], java.util.Map, java.lang.String, java.lang.ProcessBuilder$Redirect[], boolean):java.lang.Process");
    }
}
