package X;

import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class z5 implements AbstractC0449Yu {
    public final /* synthetic */ Yt A00;
    public final /* synthetic */ byte[] A01;
    public final /* synthetic */ short[] A02;

    public z5(Yt yt, short[] sArr, byte[] bArr) {
        this.A00 = yt;
        this.A02 = sArr;
        this.A01 = bArr;
    }

    @Override // X.AbstractC0449Yu
    public final boolean A4u() {
        DataOutputStream dataOutputStream;
        C0450Yv yv = this.A00.A02;
        synchronized (yv.A08) {
            try {
                short[] sArr = this.A02;
                int length = sArr.length;
                if (length > 0 && (dataOutputStream = yv.A02) != null) {
                    dataOutputStream.writeInt(length);
                    for (short s : sArr) {
                        byte[] bArr = this.A01;
                        ByteBuffer wrap = ByteBuffer.wrap(bArr, 0, 2);
                        wrap.order(ByteOrder.LITTLE_ENDIAN);
                        wrap.putShort(s);
                        yv.A01.write(bArr);
                    }
                }
            } catch (IOException e) {
                Log.e("MicDataLogger", e.getMessage(), e);
            }
        }
        return false;
    }
}
