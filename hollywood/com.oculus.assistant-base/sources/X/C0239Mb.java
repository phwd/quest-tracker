package X;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.os.Handler;
import com.facebook.proxygen.HTTPRequestHandler;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.Mb  reason: case insensitive filesystem */
public final class C0239Mb {
    public boolean A00 = false;
    public final AudioAttributes A01;
    public final AudioFormat A02;
    public final Handler A03;
    public final AbstractC0238Ma A04;
    public final Thread A05;
    public final LinkedBlockingQueue A06 = new LinkedBlockingQueue();
    public final AtomicInteger A07 = new AtomicInteger(0);
    public volatile boolean A08 = false;

    public C0239Mb(Handler handler, int i, AbstractC0238Ma ma, AudioAttributes audioAttributes) {
        AudioFormat build = new AudioFormat.Builder().setChannelMask(4).setEncoding(2).setSampleRate(i).build();
        this.A03 = handler;
        this.A04 = ma;
        this.A05 = new Thread(new MY(this), "AudioTrackPlayer");
        this.A01 = audioAttributes;
        this.A02 = build;
    }

    public final void A00(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (this.A08) {
            return;
        }
        if (this.A00) {
            int addAndGet = this.A07.addAndGet(i2);
            if (addAndGet > 3145728) {
                IllegalStateException illegalStateException = new IllegalStateException(AnonymousClass08.A00("Pending audio buffer too big at ", addAndGet));
                this.A08 = true;
                this.A05.interrupt();
                C0139Dd.A0S("AudioTrackPlayer", illegalStateException, "Error while playing TTS");
                this.A03.post(new MU(this, illegalStateException));
                return;
            }
            MX mx = new MX();
            mx.A01 = true;
            synchronized (MR.class) {
                Iterator it = MR.A00.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        bArr2 = new byte[Math.max((int) HTTPRequestHandler.SMALL_REQUESTS_BODY_BUFFER_SIZE, i2)];
                        break;
                    }
                    bArr2 = (byte[]) it.next();
                    if (bArr2.length >= i2) {
                        it.remove();
                        break;
                    }
                }
            }
            mx.A02 = bArr2;
            mx.A00 = i2;
            System.arraycopy(bArr, i, bArr2, 0, i2);
            this.A06.add(mx);
            return;
        }
        throw new IllegalStateException("You must call 'start' first");
    }
}
