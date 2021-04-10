package X;

import com.oculus.aidl.OVRServiceInterface;
import java.io.Closeable;
import java.io.DataInputStream;
import java.util.Map;

public final class Ej implements Closeable {
    public boolean A00;
    public String[] A01;
    public final DataInputStream A02;
    public final Map A03;

    public static El A00(Ej ej) {
        El A002;
        El el;
        DataInputStream dataInputStream = ej.A02;
        byte readByte = dataInputStream.readByte();
        String str = null;
        switch (readByte) {
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                el = null;
                str = ej.A01[dataInputStream.readShort()];
                A002 = null;
                break;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                A002 = A00(ej);
                if (readByte != 13) {
                    el = null;
                    break;
                } else {
                    el = A00(ej);
                    break;
                }
            default:
                A002 = null;
                el = null;
                break;
        }
        return new El(readByte, str, A002, el);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A02.close();
    }

    public Ej(DataInputStream dataInputStream, Map map) {
        this.A02 = dataInputStream;
        this.A03 = map;
    }
}
