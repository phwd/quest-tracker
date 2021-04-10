package com.apple.dnssd;

/* compiled from: DNSSD */
class AppleService implements DNSSDService, Runnable {
    protected BaseListener fListener;
    protected long fNativeContext = 0;

    /* access modifiers changed from: protected */
    public native int BlockForData();

    /* access modifiers changed from: protected */
    public native synchronized void HaltOperation();

    /* access modifiers changed from: protected */
    public native int ProcessResults();

    public AppleService(BaseListener baseListener) {
        this.fListener = baseListener;
    }

    @Override // com.apple.dnssd.DNSSDService
    public void stop() {
        HaltOperation();
    }

    /* access modifiers changed from: protected */
    public void ThrowOnErr(int i) throws DNSSDException {
        if (i != 0) {
            throw new AppleDNSSDException(i);
        }
    }

    public void run() {
        while (true) {
            int BlockForData = BlockForData();
            synchronized (this) {
                if (this.fNativeContext != 0) {
                    if (BlockForData != 0) {
                        int ProcessResults = ProcessResults();
                        if (this.fNativeContext != 0) {
                            if (ProcessResults != 0) {
                                this.fListener.operationFailed(this, ProcessResults);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }
}
