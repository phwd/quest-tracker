package X;

import com.facebook.proxygen.HTTPClient;
import com.facebook.proxygen.HTTPThread;

public final class EZ {
    public final HTTPClient A00;
    public final AbstractC0119Bm A01;
    public final C0155Eb A02;
    public final HTTPThread A03;
    public final AbstractC0463a6 A04;

    public EZ(C0155Eb eb, AbstractC0119Bm bm, AbstractC0463a6 a6Var, HTTPClient.Builder builder) {
        this.A02 = eb;
        this.A01 = bm;
        this.A04 = a6Var;
        KJ.A05("liger", 0);
        HTTPThread hTTPThread = new HTTPThread();
        Thread thread = new Thread(new EY(hTTPThread), "Liger-EventBase");
        thread.setPriority(7);
        thread.start();
        hTTPThread.waitForInitialization();
        this.A03 = hTTPThread;
        builder.mEventBase = hTTPThread.getEventBase();
        builder.mProxyFallbackEnabled = true;
        builder.mEnableTransportCallbacks = true;
        HTTPClient build = builder.build();
        this.A00 = build;
        build.init();
    }
}
