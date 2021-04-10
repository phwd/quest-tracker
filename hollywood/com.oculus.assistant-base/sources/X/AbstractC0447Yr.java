package X;

import android.util.Log;
import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.assistant.oacr.OacrConstants;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: X.Yr  reason: case insensitive filesystem */
public abstract class AbstractC0447Yr extends InputStream {
    public static final int LOG_RATE_LIMIT = 10000;
    public static final int LOG_VOLUME_LEVEL_TRIGGER = 10;
    public static final int PCM_BUFFER_SIZE = 1024;
    public static final String TAG = "AudioSource";
    public long mLastLog;
    public byte[] mPcmBuffer = new byte[2048];

    private boolean isWithinLevelTrigger(short s) {
        return s > 10 || s < -10;
    }

    public abstract int getSampleRate();

    public abstract String getSourceName();

    public abstract boolean isAudioDataPcm();

    public abstract boolean isSourceAvailable();

    public abstract void open();

    public short[] readPcmData() {
        byte[] bArr = this.mPcmBuffer;
        int read = (int) (((float) read(bArr, 0, bArr.length)) / 2.0f);
        short[] sArr = new short[read];
        for (int i = 0; i < read; i++) {
            ByteBuffer wrap = ByteBuffer.wrap(this.mPcmBuffer, i << 1, 2);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            sArr[i] = wrap.getShort();
        }
        return sArr;
    }

    private boolean checkRateLimit() {
        if (System.currentTimeMillis() - this.mLastLog > StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS) {
            return true;
        }
        return false;
    }

    public void log(String str, byte[] bArr) {
        int length;
        if (!C0139Dd.A01.A3Y(2)) {
            return;
        }
        if (checkRateLimit() || (bArr.length > 1 && isWithinLevelTrigger((short) bArr[0]))) {
            this.mLastLog = System.currentTimeMillis();
            String A05 = AnonymousClass08.A05(getSourceName(), ": ", str);
            String str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
            int i = 0;
            do {
                int i2 = i << 1;
                length = bArr.length;
                if (i2 >= length) {
                    break;
                }
                ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 2);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                str2 = AnonymousClass08.A04(str2, String.format("%6s", AnonymousClass08.A00(OacrConstants.AUTO_SPEECH_DOMAIN, wrap.getShort())));
                i++;
            } while (i < 5);
            StringBuilder sb = new StringBuilder();
            sb.append(A05);
            sb.append("[");
            sb.append(length);
            sb.append(" bytes]:\t");
            sb.append(str2);
            Log.v(TAG, sb.toString());
        }
    }

    public void log(String str, short[] sArr) {
        int length;
        if (!C0139Dd.A01.A3Y(2)) {
            return;
        }
        if (checkRateLimit() || (sArr.length > 1 && isWithinLevelTrigger(sArr[0]))) {
            this.mLastLog = System.currentTimeMillis();
            String A05 = AnonymousClass08.A05(getSourceName(), ": ", str);
            String str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
            int i = 0;
            do {
                length = sArr.length;
                if (i >= length) {
                    break;
                }
                str2 = AnonymousClass08.A04(str2, String.format("%6s", AnonymousClass08.A00(OacrConstants.AUTO_SPEECH_DOMAIN, sArr[i])));
                i++;
            } while (i < 5);
            StringBuilder sb = new StringBuilder();
            sb.append(A05);
            sb.append("[");
            sb.append(length << 1);
            sb.append(" bytes]:\t");
            sb.append(str2);
            Log.v(TAG, sb.toString());
        }
    }

    @Override // java.io.InputStream
    public int read() {
        return read(new byte[0], 0, 1);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }
}
