package com.oculus.util.constants;

public class MicrophoneMuteState {
    public static final int MUTED = 1;
    public static final int UNKNOWN = 0;
    public static final int UNMUTED = 2;

    public static final int validate(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        throw new IllegalArgumentException("Unrecognized mute state");
    }

    public static final String toString(int i) {
        if (i == 0) {
            return "UNKNOWN";
        }
        if (i == 1) {
            return "MUTED";
        }
        if (i == 2) {
            return "UNMUTED";
        }
        throw new IllegalArgumentException("Unrecognized mute state");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0046 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int valueOf(java.lang.String r5) throws java.lang.IllegalArgumentException {
        /*
            int r0 = r5.hashCode()
            r1 = 73726283(0x464f94b, float:2.6915741E-36)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x002b
            r1 = 433141802(0x19d1382a, float:2.1632778E-23)
            if (r0 == r1) goto L_0x0021
            r1 = 435201618(0x19f0a652, float:2.4882595E-23)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "UNMUTED"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0035
            r5 = 2
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "UNKNOWN"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0035
            r5 = 0
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "MUTED"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0035
            r5 = 1
            goto L_0x0036
        L_0x0035:
            r5 = -1
        L_0x0036:
            if (r5 == 0) goto L_0x0046
            if (r5 == r4) goto L_0x0045
            if (r5 != r3) goto L_0x003d
            return r3
        L_0x003d:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Unrecognized mute state"
            r5.<init>(r0)
            throw r5
        L_0x0045:
            return r4
        L_0x0046:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.util.constants.MicrophoneMuteState.valueOf(java.lang.String):int");
    }
}
