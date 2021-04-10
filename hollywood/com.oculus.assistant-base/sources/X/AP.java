package X;

import com.facebook.proxygen.HTTPTransportCallback;
import java.util.concurrent.ExecutorService;

public interface AP {
    public static final ExecutorService A00;

    static {
        AR ar = new AR();
        ar.A00 = 2;
        ar.A04 = 2;
        ar.A02 = HTTPTransportCallback.BODY_BYTES_RECEIVED;
        ar.A03 = 30000;
        ar.A01 = 10;
        ar.A05 = "Background #";
        A00 = ar.A00();
    }
}
