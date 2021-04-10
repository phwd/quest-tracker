package X;

/* renamed from: X.1QR  reason: invalid class name */
public final class AnonymousClass1QR implements AnonymousClass1EW {
    public final AnonymousClass1QS A00;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r0.isConnected() == false) goto L_0x000f;
     */
    @Override // X.AnonymousClass1EW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean AAM(java.util.Map<java.lang.String, java.lang.String> r7) {
        /*
            r6 = this;
            X.1QS r1 = r6.A00
            android.net.NetworkInfo r0 = r1.A01()
            if (r0 == 0) goto L_0x000f
            boolean r0 = r0.isConnected()
            r5 = 1
            if (r0 != 0) goto L_0x0010
        L_0x000f:
            r5 = 0
        L_0x0010:
            if (r5 != 0) goto L_0x0021
            android.net.NetworkInfo r4 = r1.A01()
            if (r7 == 0) goto L_0x0021
            java.lang.String r3 = "MqttNetworkManagerMonitor"
            if (r4 != 0) goto L_0x0022
            java.lang.String r0 = "no_info"
        L_0x001e:
            r7.put(r3, r0)
        L_0x0021:
            return r5
        L_0x0022:
            int r0 = r4.getType()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            int r0 = r4.getSubtype()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            android.net.NetworkInfo$State r0 = r4.getState()
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r1, r0}
            java.lang.String r1 = "%s_%s_%s"
            r0 = 0
            java.lang.String r0 = java.lang.String.format(r0, r1, r2)
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1QR.AAM(java.util.Map):boolean");
    }

    public AnonymousClass1QR(AnonymousClass1QS r1) {
        this.A00 = r1;
    }
}
