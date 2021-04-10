package X;

import android.os.HandlerThread;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class GS {
    @Nullable
    public GH A00;
    @Nullable
    public GH A01;
    public final GI A02;
    @Nullable
    public final AbstractC0274Yy A03;
    public final GR A04;
    public final C0256Ye A05;

    public GS(HandlerThread handlerThread, AbstractC0274Yy yy, @Nullable GI gi, C0256Ye ye) {
        this.A04 = new GR(this, handlerThread.getLooper());
        this.A03 = yy;
        this.A02 = gi;
        this.A05 = ye;
    }
}
