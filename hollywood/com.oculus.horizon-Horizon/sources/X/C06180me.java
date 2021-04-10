package X;

/* renamed from: X.0me  reason: invalid class name and case insensitive filesystem */
public final class C06180me implements AbstractC01900Xy {
    public final AnonymousClass0XJ A00;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r0.isConnected() == false) goto L_0x000f;
     */
    @Override // X.AbstractC01900Xy
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A8x(java.util.Map<java.lang.String, java.lang.String> r7) {
        /*
            r6 = this;
            X.0XJ r1 = r6.A00
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
            r0 = 3
            java.lang.Object[] r2 = new java.lang.Object[r0]
            r1 = 0
            int r0 = r4.getType()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2[r1] = r0
            r1 = 1
            int r0 = r4.getSubtype()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2[r1] = r0
            r1 = 2
            android.net.NetworkInfo$State r0 = r4.getState()
            r2[r1] = r0
            java.lang.String r1 = "%s_%s_%s"
            r0 = 0
            java.lang.String r0 = java.lang.String.format(r0, r1, r2)
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06180me.A8x(java.util.Map):boolean");
    }

    public C06180me(AnonymousClass0XJ r1) {
        this.A00 = r1;
    }
}
