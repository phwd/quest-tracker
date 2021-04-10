package com.facebook.proxygen;

public class NativeHandleImpl implements NativeHandle {
    public long mNativeHandle;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
        if (r7 == 0) goto L_0x000d;
     */
    @Override // com.facebook.proxygen.NativeHandle
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNativeHandle(long r7) {
        /*
            r6 = this;
            long r4 = r6.mNativeHandle
            r2 = 0
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x000d
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            r0 = 0
            if (r1 != 0) goto L_0x000e
        L_0x000d:
            r0 = 1
        L_0x000e:
            com.facebook.proxygen.utils.Preconditions.checkState(r0)
            r6.mNativeHandle = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.proxygen.NativeHandleImpl.setNativeHandle(long):void");
    }

    @Override // com.facebook.proxygen.NativeHandle
    public long getNativeHandle() {
        return this.mNativeHandle;
    }
}
