package X;

import android.os.SystemClock;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.yB  reason: case insensitive filesystem */
public final class C1376yB extends AbstractC0447Yr {
    public long A00 = 0;
    public Thread A01;
    public boolean A02;
    public final Y9 A03 = new Y9(BX.A00());
    public final LinkedBlockingDeque A04 = new LinkedBlockingDeque((int) ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS);
    public final AtomicBoolean A05 = new AtomicBoolean();
    public final AtomicBoolean A06 = new AtomicBoolean();

    @Override // X.AbstractC0447Yr
    public final String getSourceName() {
        return "OvrAudioSource";
    }

    @Override // X.AbstractC0447Yr
    public final boolean isAudioDataPcm() {
        return true;
    }

    @Override // X.AbstractC0447Yr
    public final boolean isSourceAvailable() {
        return true;
    }

    private void A00() {
        Thread thread = this.A01;
        if (thread != null) {
            thread.interrupt();
            this.A01 = null;
        }
        this.A05.set(false);
        Thread thread2 = new Thread(new RunnableC0430Xh(this));
        this.A01 = thread2;
        thread2.setName("Oculus Audio Thread");
        this.A01.start();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
        AtomicBoolean atomicBoolean = this.A06;
        if (atomicBoolean.get()) {
            atomicBoolean.set(false);
            Thread thread = this.A01;
            if (thread != null) {
                thread.interrupt();
                this.A01 = null;
            }
            this.A04.clear();
            this.A03.A01();
            super.close();
        }
    }

    @Override // X.AbstractC0447Yr
    public final void open() {
        AtomicBoolean atomicBoolean = this.A06;
        if (!atomicBoolean.get()) {
            atomicBoolean.set(true);
            this.A00 = SystemClock.elapsedRealtime();
            this.A03.A00();
            this.A02 = true;
            A00();
        }
    }

    @Override // X.AbstractC0447Yr
    public final short[] readPcmData() {
        LinkedBlockingDeque linkedBlockingDeque;
        String str;
        AtomicBoolean atomicBoolean = this.A06;
        if (atomicBoolean.get()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            while (true) {
                linkedBlockingDeque = this.A04;
                if (!linkedBlockingDeque.isEmpty() || elapsedRealtime2 - elapsedRealtime >= 1000) {
                    boolean z = false;
                } else {
                    elapsedRealtime2 = SystemClock.elapsedRealtime();
                    try {
                        Thread thread = this.A01;
                        if (thread == null || !thread.isAlive() || this.A05.get()) {
                            A00();
                        }
                        Y9 y9 = this.A03;
                        if (!(!y9.A03.get()) || elapsedRealtime2 - elapsedRealtime > 300) {
                            y9.A01();
                            y9.A00();
                        }
                        Thread.sleep(15);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            boolean z2 = false;
            try {
                if (!linkedBlockingDeque.isEmpty()) {
                    if (this.A02) {
                        YP A002 = YP.A00();
                        C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logFirstByteSent");
                        YO yo = A002.A01;
                        if (yo != null) {
                            yo.A01.markerPoint(yo.A00(), "audio_firstbyte_sent");
                        }
                        C00799i.A00.logServiceEvent("first_ovrservice_read");
                        this.A02 = false;
                    }
                    this.A00 = SystemClock.elapsedRealtime();
                    return (short[]) linkedBlockingDeque.take();
                }
            } catch (InterruptedException unused2) {
                z2 = true;
            }
            StringBuilder sb = new StringBuilder("Can't read data from OVRService. Read Once: ");
            String str2 = "true";
            String str3 = "false";
            if (!this.A02) {
                str3 = str2;
            }
            sb.append(str3);
            sb.append(" Interrupted: ");
            String str4 = "false";
            if (z2) {
                str4 = str2;
            }
            sb.append(str4);
            sb.append(" Open: ");
            String str5 = "false";
            if (atomicBoolean.get()) {
                str5 = str2;
            }
            sb.append(str5);
            sb.append(" Buffers: ");
            sb.append(linkedBlockingDeque.size());
            sb.append(" Thread: ");
            Thread thread2 = this.A01;
            if (thread2 != null) {
                StringBuilder sb2 = new StringBuilder("okay ");
                sb2.append(thread2.getState());
                str = sb2.toString();
            } else {
                str = "null";
            }
            sb.append(str);
            sb.append(" ThreadExit: ");
            if (!this.A05.get()) {
                str2 = "false";
            }
            sb.append(str2);
            sb.append(" OVRService: ");
            sb.append(this.A03.toString());
            throw new IOException(sb.toString());
        }
        throw new IOException("Audio Source is not open.");
    }

    @Override // X.AbstractC0447Yr
    public final int getSampleRate() {
        return 48000;
    }

    @Override // X.AbstractC0447Yr, java.io.InputStream
    public final int read() {
        if (this.A06.get()) {
            return 0;
        }
        throw new IOException("Audio Source is not open.");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        throw new IOException("Not implemented");
    }
}
