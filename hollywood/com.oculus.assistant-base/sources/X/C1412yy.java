package X;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/* renamed from: X.yy  reason: case insensitive filesystem */
public final class C1412yy extends AbstractC0447Yr {
    public long A00;
    public long A01;
    public AbstractC0447Yr A02;
    public DataInputStream A03;
    public FileInputStream A04;
    public InputStream A05;
    public String A06;
    public boolean A07 = false;
    public final C0450Yv A08 = new C0450Yv();
    public final Map A09 = new HashMap();
    public final ConcurrentLinkedQueue A0A = new ConcurrentLinkedQueue();
    public final LinkedBlockingDeque A0B = new LinkedBlockingDeque();

    public static File A00(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(BX.A00().getFilesDir());
        sb.append(File.separator);
        sb.append("E2E");
        return new File(new File(sb.toString()), str);
    }

    private void A01() {
        if (this.A07) {
            try {
                C0139Dd.A0F("AudioInputLogSource", "closeLog: Done reading %s", this.A06);
                this.A05.close();
            } catch (IOException e) {
                C0139Dd.A0L("AudioInputLogSource", "checkClose: ", e);
            }
        }
        if (this.A05 != null) {
            try {
                this.A04.close();
            } catch (IOException e2) {
                C0139Dd.A0L("AudioInputLogSource", "checkClose: ", e2);
            }
        }
        this.A05 = null;
        this.A06 = null;
        this.A01 = 0;
        this.A00 = 0;
        this.A07 = false;
    }

    private void A02() {
        String str;
        if (this.A00 == this.A01) {
            A01();
        }
        if (this.A00 >= this.A01) {
            ConcurrentLinkedQueue concurrentLinkedQueue = this.A0A;
            if (!concurrentLinkedQueue.isEmpty() && this.A0B.isEmpty()) {
                String str2 = (String) concurrentLinkedQueue.remove();
                if (this.A00 == this.A01) {
                    A01();
                }
                try {
                    File A002 = A00(AnonymousClass08.A04(str2, ".pcm"));
                    File A003 = A00(AnonymousClass08.A04(str2, ".index"));
                    boolean exists = A002.exists();
                    if (!exists) {
                        A002 = new File(C0451Yx.A00(), AnonymousClass08.A04(str2, ".pcm"));
                        A003 = new File(C0451Yx.A00(), AnonymousClass08.A04(str2, ".index"));
                    }
                    this.A05 = new BufferedInputStream(new FileInputStream(A002));
                    FileInputStream fileInputStream = new FileInputStream(A003);
                    this.A04 = fileInputStream;
                    this.A03 = new DataInputStream(new BufferedInputStream(fileInputStream));
                    this.A01 = A002.length();
                    this.A00 = 0;
                    this.A06 = str2;
                    this.A07 = true;
                    if (exists) {
                        str = "end to end file";
                    } else {
                        str = "logs";
                    }
                    C0139Dd.A0H("AudioInputLogSource", "Opening %s from %s for playback.", str2, str);
                } catch (FileNotFoundException e) {
                    C0139Dd.A0L("AudioInputLogSource", "Log file is no longer available.", e);
                    InputStream inputStream = this.A05;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            this.A05 = null;
                        } catch (IOException e2) {
                            C0139Dd.A0L("AudioInputLogSource", "openFile: ", e2);
                        }
                    }
                    FileInputStream fileInputStream2 = this.A04;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                            this.A04 = null;
                        } catch (IOException e3) {
                            C0139Dd.A0L("AudioInputLogSource", "openFile: ", e3);
                        }
                    }
                }
            }
        }
    }

    @Override // X.AbstractC0447Yr
    public final int getSampleRate() {
        if (this.A07) {
            return 48000;
        }
        return this.A02.getSampleRate();
    }

    @Override // X.AbstractC0447Yr
    public final String getSourceName() {
        AbstractC0447Yr yr = this.A02;
        if (yr != null) {
            return AnonymousClass08.A04(yr.getSourceName(), " [Logged]");
        }
        return "Audio Input Logger";
    }

    @Override // X.AbstractC0447Yr
    public final boolean isAudioDataPcm() {
        AbstractC0447Yr yr = this.A02;
        if (yr != null) {
            return yr.isAudioDataPcm();
        }
        return true;
    }

    @Override // X.AbstractC0447Yr
    public final boolean isSourceAvailable() {
        if (this.A05 != null) {
            return true;
        }
        AbstractC0447Yr yr = this.A02;
        if (yr == null || !yr.isSourceAvailable()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC0447Yr
    public final void open() {
        AbstractC0447Yr yr = this.A02;
        if (yr != null) {
            C0139Dd.A0F("AudioInputLogSource", "opening real source %s", yr.getSourceName());
            this.A02.open();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
        super.close();
        A01();
        AbstractC0447Yr yr = this.A02;
        if (yr != null) {
            yr.close();
        }
        this.A09.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        if (r1 == false) goto L_0x006b;
     */
    @Override // X.AbstractC0447Yr
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final short[] readPcmData() {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1412yy.readPcmData():short[]");
    }

    public C1412yy() {
    }

    public C1412yy(AbstractC0447Yr yr) {
        this.A02 = yr;
    }

    @Override // X.AbstractC0447Yr, java.io.InputStream
    public final int read() {
        if (this.A05 != null) {
            return 0;
        }
        return this.A02.read();
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        int i3;
        int capacity;
        ByteBuffer byteBuffer;
        if (isAudioDataPcm()) {
            A02();
            LinkedBlockingDeque linkedBlockingDeque = this.A0B;
            if (linkedBlockingDeque.size() > 0 && ((Buffer) linkedBlockingDeque.peek()).capacity() == 0) {
                linkedBlockingDeque.remove();
            }
            if (linkedBlockingDeque.size() == 0) {
                short[] readPcmData = readPcmData();
                int length = readPcmData.length;
                int i4 = length << 1;
                Map map = this.A09;
                Integer valueOf = Integer.valueOf(i4);
                if (!map.containsKey(valueOf) || (byteBuffer = (ByteBuffer) map.remove(valueOf)) == null) {
                    byteBuffer = ByteBuffer.allocate(i4);
                }
                for (short s : readPcmData) {
                    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                    byteBuffer.putShort(s);
                }
                byteBuffer.position(0);
                linkedBlockingDeque.offer(byteBuffer);
            }
            ByteBuffer byteBuffer2 = (ByteBuffer) linkedBlockingDeque.peek();
            if (byteBuffer2 == null || (capacity = byteBuffer2.capacity() - byteBuffer2.position()) <= 0) {
                i3 = 0;
            } else {
                i3 = Math.min(capacity, i2);
                byteBuffer2.get(bArr, 0, i3);
                if (byteBuffer2.capacity() == byteBuffer2.position()) {
                    Buffer buffer = (Buffer) linkedBlockingDeque.remove();
                    buffer.clear();
                    buffer.position(0);
                    Map map2 = this.A09;
                    if (!map2.containsKey(Integer.valueOf(buffer.capacity()))) {
                        map2.put(Integer.valueOf(buffer.capacity()), buffer);
                    }
                }
            }
        } else {
            i3 = this.A02.read(bArr, i, i2);
        }
        log("readByteData", bArr);
        return i3;
    }
}
