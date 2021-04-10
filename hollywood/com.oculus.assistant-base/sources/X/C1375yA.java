package X;

import android.os.SystemClock;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.oculus.os.VoiceAssistantManager;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.yA  reason: case insensitive filesystem */
public final class C1375yA extends AbstractC0447Yr {
    public long A00;
    public Y5 A01;
    public Thread A02;
    public boolean A03;
    public final LinkedBlockingDeque A04;
    public final AtomicBoolean A05;

    @Override // X.AbstractC0447Yr
    public final String getSourceName() {
        return "AssistantNativeAudioSource";
    }

    @Override // X.AbstractC0447Yr
    public final boolean isAudioDataPcm() {
        return false;
    }

    @Override // X.AbstractC0447Yr
    public final boolean isSourceAvailable() {
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
        AtomicBoolean atomicBoolean = this.A05;
        if (atomicBoolean.get()) {
            atomicBoolean.set(false);
            Thread thread = this.A02;
            if (thread != null) {
                thread.interrupt();
                this.A02 = null;
            }
            this.A04.clear();
            if (YN.A02()) {
                this.A01.A01();
            }
            C0139Dd.A09("AudioInputNativeSource", "Closing Native Audio Source");
            super.close();
        }
    }

    @Override // X.AbstractC0447Yr
    public final void open() {
        AtomicBoolean atomicBoolean = this.A05;
        if (!atomicBoolean.get()) {
            atomicBoolean.set(true);
            this.A00 = SystemClock.elapsedRealtime();
            this.A03 = true;
            Thread thread = this.A02;
            if (thread != null) {
                thread.interrupt();
                this.A02 = null;
            }
            Thread thread2 = new Thread(new RunnableC0429Xg(this));
            this.A02 = thread2;
            thread2.setName("Oculus Audio Thread");
            this.A02.start();
        }
    }

    @Override // X.AbstractC0447Yr
    public final short[] readPcmData() {
        throw new IOException("Not implemented");
    }

    @Override // X.AbstractC0447Yr
    public final int getSampleRate() {
        return 16000;
    }

    public C1375yA() {
        this.A00 = 0;
        this.A05 = new AtomicBoolean();
        this.A04 = new LinkedBlockingDeque((int) ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS);
        this.A01 = new Y5();
    }

    public C1375yA(VoiceAssistantManager voiceAssistantManager) {
        this.A00 = 0;
        this.A05 = new AtomicBoolean();
        this.A04 = new LinkedBlockingDeque((int) ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS);
        this.A01 = new Y5(voiceAssistantManager);
    }

    @Override // X.AbstractC0447Yr, java.io.InputStream
    public final int read() {
        if (this.A05.get()) {
            return 0;
        }
        throw new IOException("Audio Source is not open.");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        LinkedBlockingDeque linkedBlockingDeque;
        int capacity;
        if (this.A05.get()) {
            C0139Dd.A09("AudioInputNativeSource", "Read mic data as bytes");
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            while (true) {
                linkedBlockingDeque = this.A04;
                if (linkedBlockingDeque.isEmpty() && elapsedRealtime2 - elapsedRealtime < 1000) {
                    elapsedRealtime2 = SystemClock.elapsedRealtime();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            if (!linkedBlockingDeque.isEmpty()) {
                if (this.A03) {
                    YP A002 = YP.A00();
                    C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logFirstByteSent");
                    YO yo = A002.A01;
                    if (yo != null) {
                        yo.A01.markerPoint(yo.A00(), "audio_firstbyte_sent");
                    }
                    C00799i.A00.logServiceEvent("first_native_service_read");
                    this.A03 = false;
                }
                this.A00 = SystemClock.elapsedRealtime();
                ByteBuffer byteBuffer = (ByteBuffer) linkedBlockingDeque.peek();
                if (byteBuffer == null || (capacity = byteBuffer.capacity() - byteBuffer.position()) <= 0) {
                    return 0;
                }
                int min = Math.min(capacity, i2);
                byteBuffer.get(bArr, 0, min);
                if (byteBuffer.capacity() == byteBuffer.position()) {
                    C0139Dd.A09("AudioInputNativeSource", "Removing current buffer");
                    linkedBlockingDeque.remove();
                }
                return min;
            }
            throw new IOException("Can't read data from NativeService");
        }
        throw new IOException("Audio Source is not open.");
    }
}
