package X;

import android.media.AudioAttributes;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.common.base.Absent;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

public final class l0 implements FU {
    public g1 A00;
    public long A01;
    public C0862kz A02;
    public boolean A03 = false;
    public final Handler A04;
    public final C0861ky A05;
    public final VQ A06;
    public final List A07 = new ArrayList();

    public final void A02() {
        C0139Dd.A09("StreamingTtsPlayer", "Finishing");
        if (!A00()) {
            this.A04.post(new FG(this));
        } else if (!this.A03) {
            this.A07.add(null);
        } else {
            VQ vq = this.A06;
            if (!vq.A02) {
                C0239Mb mb = vq.A01;
                MX mx = new MX();
                mx.A03 = true;
                mb.A06.add(mx);
            }
        }
    }

    public final void A03() {
        C0139Dd.A09("StreamingTtsPlayer", "Stopping");
        if (!A00()) {
            this.A04.post(new FM(this));
            return;
        }
        VQ vq = this.A06;
        vq.A02 = true;
        C0239Mb mb = vq.A01;
        if (mb != null) {
            mb.A08 = true;
            mb.A05.interrupt();
        }
    }

    public final void A05(C1004qX qXVar) {
        C0139Dd.A09("StreamingTtsPlayer", "Starting");
        if (!A00()) {
            this.A04.post(new FJ(this, qXVar));
            return;
        }
        VQ vq = this.A06;
        if (!vq.A03) {
            vq.A03 = true;
            if (!vq.A02) {
                Matcher matcher = VQ.A07.matcher(qXVar.mMimeType);
                if (matcher.find()) {
                    C0239Mb mb = new C0239Mb(vq.A05, Integer.parseInt(matcher.group(1)), vq.A06, vq.A04);
                    vq.A01 = mb;
                    mb.A00 = true;
                    mb.A05.start();
                    return;
                }
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(AnonymousClass08.A04("Unable to parse sampling rate from mime type :: ", qXVar.mMimeType));
                vq.A02 = true;
                C0239Mb mb2 = vq.A01;
                if (mb2 != null) {
                    mb2.A08 = true;
                    mb2.A05.interrupt();
                }
                C0139Dd.A0S("Pcm16TtsListener", illegalArgumentException, "Error while playing TTS");
                vq.A05.post(new RunnableC0240Mc(vq, illegalArgumentException));
                return;
            }
            return;
        }
        throw new IllegalStateException("Already started, don't reuse this class!");
    }

    public l0(Handler handler, AudioAttributes audioAttributes) {
        C0861ky kyVar = new C0861ky(this);
        this.A05 = kyVar;
        this.A04 = handler;
        VQ vq = new VQ(handler, audioAttributes);
        this.A06 = vq;
        vq.A00 = kyVar;
    }

    private boolean A00() {
        if (Looper.myLooper() == this.A04.getLooper()) {
            return true;
        }
        return false;
    }

    public final void A01() {
        if (!A00()) {
            this.A04.post(new FI(this));
        } else if (this.A01 == 0) {
            this.A01 = SystemClock.uptimeMillis();
            if (this.A00 != null) {
                C00949y.A00.A03(Absent.INSTANCE);
            }
        }
    }

    public final void A04(g1 g1Var) {
        if (!A00()) {
            this.A04.post(new FL(this, g1Var));
            return;
        }
        this.A00 = g1Var;
        if (g1Var != null) {
            if (this.A01 != 0) {
                C00949y.A00.A03(Absent.INSTANCE);
            }
            if (!this.A07.isEmpty()) {
                this.A00.A00(this);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06(byte[] r8, int r9, int r10) {
        /*
        // Method dump skipped, instructions count: 123
        */
        throw new UnsupportedOperationException("Method not decompiled: X.l0.A06(byte[], int, int):void");
    }

    @Override // X.FU
    public final void A57() {
        if (!A00()) {
            this.A04.post(new FK(this));
            return;
        }
        List list = this.A07;
        if (list.isEmpty()) {
            C0139Dd.A0A("StreamingTtsPlayer", "Not prepared!");
        } else if (this.A03) {
            C0139Dd.A0D("StreamingTtsPlayer", "Already speaking!");
        } else {
            C0139Dd.A09("StreamingTtsPlayer", "Speak");
            this.A03 = true;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ByteBuffer byteBuffer = (ByteBuffer) it.next();
                if (byteBuffer == null) {
                    VQ vq = this.A06;
                    if (!vq.A02) {
                        C0239Mb mb = vq.A01;
                        MX mx = new MX();
                        mx.A03 = true;
                        mb.A06.add(mx);
                    }
                } else {
                    byteBuffer.flip();
                    VQ vq2 = this.A06;
                    byte[] array = byteBuffer.array();
                    int position = byteBuffer.position();
                    int remaining = byteBuffer.remaining();
                    if (!vq2.A02) {
                        vq2.A01.A00(array, position, remaining);
                    }
                }
            }
            list.clear();
        }
    }
}
