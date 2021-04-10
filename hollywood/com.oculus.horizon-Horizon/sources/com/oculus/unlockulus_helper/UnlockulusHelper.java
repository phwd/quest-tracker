package com.oculus.unlockulus_helper;

import com.oculus.appmanager.signature.VRPackageVerifier;

public class UnlockulusHelper {
    public static final String PACKAGE_NAME = "com.oculus.unlockulus";
    public static final String SYSTEM_PROP_SIGN_TYPE = "ro.build.fingerprint";
    public static final String TAG = "UnlockulusHelper";
    public static final String TYPE_RELEASEKEY = "release-keys";
    public static final VRPackageVerifier sVerifier = new VRPackageVerifier();

    public static class Result {
        public final boolean developerVRSigned;
        public final boolean firstPartyCertValid;
        public final boolean isInstalled;
        public final boolean userdev;

        public final String toString() {
            return String.format("Result[isInstalled: %b, firstPartyCertValid: %b, developerVRSigned: %b, userdev: %b]", Boolean.valueOf(this.isInstalled), Boolean.valueOf(this.firstPartyCertValid), Boolean.valueOf(this.developerVRSigned), Boolean.valueOf(this.userdev));
        }

        public Result(boolean z, boolean z2, boolean z3, boolean z4) {
            this.isInstalled = z;
            this.firstPartyCertValid = z2;
            this.developerVRSigned = z3;
            this.userdev = z4;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A00(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r5 = "com.oculus.unlockulus"
            java.lang.String r9 = "ro.build.fingerprint"
            r8 = 0
            java.lang.Class<?> r7 = com.oculus.util.system.SystemProps.CLASS     // Catch:{ Exception -> 0x002d }
            java.lang.String r6 = "get"
            r4 = 2
            java.lang.Class[] r1 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x002d }
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            r3 = 0
            r1[r3] = r0     // Catch:{ Exception -> 0x002d }
            r2 = 1
            r1[r2] = r0     // Catch:{ Exception -> 0x002d }
            java.lang.reflect.Method r1 = r7.getMethod(r6, r1)     // Catch:{ Exception -> 0x002d }
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x002d }
            r0[r3] = r9     // Catch:{ Exception -> 0x002d }
            r0[r2] = r8     // Catch:{ Exception -> 0x002d }
            java.lang.Object r1 = r1.invoke(r8, r0)     // Catch:{ Exception -> 0x002d }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x002d }
            if (r1 == 0) goto L_0x002d
            java.lang.String r0 = "release-keys"
            boolean r4 = r1.endsWith(r0)
            goto L_0x002e
        L_0x002d:
            r4 = 0
        L_0x002e:
            r3 = 1
            r4 = r4 ^ r3
            boolean r2 = com.oculus.signature.SignatureHelper.A00(r11, r5)     // Catch:{ NameNotFoundException -> 0x0044 }
            com.oculus.appmanager.signature.VRPackageVerifier r1 = com.oculus.unlockulus_helper.UnlockulusHelper.sVerifier     // Catch:{ NameNotFoundException -> 0x0044 }
            android.content.pm.PackageManager r0 = r11.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0044 }
            boolean r0 = r1.A00(r0, r5)     // Catch:{ NameNotFoundException -> 0x0044 }
            com.oculus.unlockulus_helper.UnlockulusHelper$Result r1 = new com.oculus.unlockulus_helper.UnlockulusHelper$Result     // Catch:{ NameNotFoundException -> 0x0044 }
            r1.<init>(r3, r2, r0, r4)     // Catch:{ NameNotFoundException -> 0x0044 }
            goto L_0x004a
        L_0x0044:
            r0 = 0
            com.oculus.unlockulus_helper.UnlockulusHelper$Result r1 = new com.oculus.unlockulus_helper.UnlockulusHelper$Result
            r1.<init>(r0, r0, r0, r4)
        L_0x004a:
            boolean r0 = r1.userdev
            if (r0 != 0) goto L_0x005a
            boolean r0 = r1.isInstalled
            if (r0 == 0) goto L_0x005c
            boolean r0 = r1.firstPartyCertValid
            if (r0 == 0) goto L_0x005c
            boolean r0 = r1.developerVRSigned
            if (r0 == 0) goto L_0x005c
        L_0x005a:
            r0 = 1
            return r0
        L_0x005c:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unlockulus_helper.UnlockulusHelper.A00(android.content.Context):boolean");
    }
}
