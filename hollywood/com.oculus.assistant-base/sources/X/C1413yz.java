package X;

import android.util.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/* renamed from: X.yz  reason: case insensitive filesystem */
public final class C1413yz extends AbstractC0447Yr {
    public HashMap A00 = new HashMap();
    public Queue A01 = new PriorityBlockingQueue();
    public AbstractC0447Yr A02;

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final synchronized void close() {
        AbstractC0447Yr yr = this.A02;
        if (yr != null) {
            Log.d("AudioSourceManager", AnonymousClass08.A04("Closing ", yr.getSourceName()));
            this.A02.close();
            this.A02 = null;
        }
    }

    @Override // X.AbstractC0447Yr
    public final synchronized void open() {
        String str;
        if (this.A02 != null) {
            try {
                close();
            } catch (IOException e) {
                C0139Dd.A0M("AudioSourceManager", "Failed to close active audio source.", e);
            }
        }
        Log.d("AudioSourceManager", "Opening audio sources...");
        for (C0448Ys ys : this.A01) {
            String sourceName = ys.A00.getSourceName();
            if (ys.A00.isSourceAvailable()) {
                str = "available";
            } else {
                str = "not available";
            }
            Log.d("AudioSourceManager", AnonymousClass08.A07("  ", sourceName, " [", str, "]"));
            if (this.A02 == null && ys.A00.isSourceAvailable()) {
                AbstractC0447Yr yr = ys.A00;
                this.A02 = yr;
                Log.d("AudioSourceManager", AnonymousClass08.A04("Listening to ", yr.getSourceName()));
                ys.A00.open();
            }
        }
        if (this.A02 == null) {
            throw new IOException("Could not open any audio sources.");
        }
    }

    @Override // X.AbstractC0447Yr
    public final short[] readPcmData() {
        boolean z = false;
        short[] sArr = new short[0];
        synchronized (this) {
            AbstractC0447Yr yr = this.A02;
            if (yr != null) {
                if (yr.isSourceAvailable()) {
                    sArr = this.A02.readPcmData();
                } else {
                    z = true;
                }
            }
        }
        if (z) {
            close();
            open();
        }
        return sArr;
    }

    public final void A00(AbstractC0447Yr yr) {
        Object remove;
        if (yr != null && (remove = this.A00.remove(yr)) != null) {
            this.A01.remove(remove);
        }
    }

    public final void A01(AbstractC0447Yr yr, int i) {
        if (!this.A00.containsKey(yr)) {
            C0448Ys ys = new C0448Ys(this, yr, i);
            this.A01.add(ys);
            this.A00.put(yr, ys);
        }
    }

    @Override // X.AbstractC0447Yr
    public final int getSampleRate() {
        AbstractC0447Yr yr = this.A02;
        if (yr == null) {
            if (this.A01.size() > 0) {
                yr = ((C0448Ys) this.A01.peek()).A00;
            } else {
                throw new IllegalStateException("No audio source setup.");
            }
        }
        return yr.getSampleRate();
    }

    @Override // X.AbstractC0447Yr
    public final String getSourceName() {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (C0448Ys ys : this.A01) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(ys.A00.getSourceName());
            if (!z && ys.A00.isSourceAvailable()) {
                z = true;
                sb.append(" [A]");
            }
        }
        sb.insert(0, "Audio SourceManager [");
        sb.append("]");
        return sb.toString();
    }

    @Override // X.AbstractC0447Yr
    public final boolean isAudioDataPcm() {
        AbstractC0447Yr yr = this.A02;
        if (yr == null) {
            return true;
        }
        return yr.isAudioDataPcm();
    }

    @Override // X.AbstractC0447Yr
    public final boolean isSourceAvailable() {
        for (C0448Ys ys : this.A01) {
            if (ys.A00.isSourceAvailable()) {
                return true;
            }
        }
        return false;
    }

    @Override // X.AbstractC0447Yr, java.io.InputStream
    public final int read() {
        byte[] bArr = new byte[0];
        read(bArr, 0, 1);
        return bArr[0];
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        int i3;
        boolean z;
        synchronized (this) {
            AbstractC0447Yr yr = this.A02;
            i3 = 0;
            if (yr != null) {
                if (yr.isSourceAvailable()) {
                    i3 = this.A02.read(bArr, i, i2);
                } else {
                    z = true;
                }
            }
            z = false;
        }
        if (z) {
            close();
            open();
        }
        return i3;
    }
}
