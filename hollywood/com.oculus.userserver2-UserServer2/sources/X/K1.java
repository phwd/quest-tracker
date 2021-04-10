package X;

import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedOutput;

public final class K1 implements Client {
    public static final byte[] A01 = new byte[0];
    public final AbstractC0054Ej A00;

    @Override // retrofit.client.Client
    public final Response execute(Request request) throws IOException {
        XM k3;
        K2 k2;
        AbstractC0054Ej ej = this.A00;
        String str = request.method;
        if ((TigonRequest.POST.equals(str) || "PUT".equals(str) || "PATCH".equals(str) || "PROPPATCH".equals(str) || "REPORT".equals(str)) && request.body == null) {
            byte[] bArr = A01;
            int length = bArr.length;
            long j = (long) length;
            long j2 = (long) 0;
            if ((j2 | j) < 0 || j2 > j || j - j2 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            k3 = new C0051Eb(length, bArr);
        } else {
            TypedOutput typedOutput = request.body;
            if (typedOutput == null) {
                k3 = null;
            } else {
                k3 = new K3(XR.A00(typedOutput.mimeType()), typedOutput);
            }
        }
        XO xo = new XO();
        xo.A01(request.url);
        xo.A03(request.method, k3);
        List<Header> list = request.headers;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Header header = list.get(i);
            String str2 = header.value;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = header.name;
            XX xx = xo.A03;
            XX.A00(str3, str2);
            xx.A02(str3, str2);
        }
        XK A002 = new C0052Ec(ej, xo.A00(), false).A00();
        String obj = A002.A07.A03.toString();
        int i2 = A002.A01;
        String str4 = A002.A04;
        String[] strArr = A002.A06.A00;
        int length2 = strArr.length >> 1;
        ArrayList arrayList = new ArrayList(length2);
        for (int i3 = 0; i3 < length2; i3++) {
            int i4 = i3 << 1;
            arrayList.add(new Header(strArr[i4], strArr[i4 + 1]));
        }
        XJ xj = A002.A0B;
        if (xj.A00() == 0) {
            k2 = null;
        } else {
            k2 = new K2(xj);
        }
        return new Response(obj, i2, str4, arrayList, k2);
    }

    public K1() {
        this(new AbstractC0054Ej());
    }

    public K1(AbstractC0054Ej ej) {
        if (ej != null) {
            this.A00 = ej;
            return;
        }
        throw new NullPointerException("client == null");
    }
}
