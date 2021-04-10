package X;

import com.facebook.acra.LogCatCollector;
import com.facebook.proxygen.HTTPTransportCallback;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class E3 {
    public static final Charset A01 = Charset.forName(LogCatCollector.UTF_8_ENCODING);
    public ByteBuffer A00;

    public E3() {
        ByteBuffer allocate = ByteBuffer.allocate(HTTPTransportCallback.BODY_BYTES_RECEIVED);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        this.A00 = allocate;
    }
}
