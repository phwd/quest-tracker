package X;

import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedOutput;

/* renamed from: X.0UJ  reason: invalid class name */
public final class AnonymousClass0UJ implements Client {
    public static final byte[] A01 = new byte[0];
    public final AnonymousClass0N1 A00;

    @Override // retrofit.client.Client
    public final Response execute(Request request) throws IOException {
        AbstractC08320wM r2;
        Deque<AnonymousClass0Mz> deque;
        AnonymousClass0UK r11;
        AnonymousClass0N1 r6 = this.A00;
        String str = request.method;
        if ((TigonRequest.POST.equals(str) || "PUT".equals(str) || "PATCH".equals(str) || "PROPPATCH".equals(str) || "REPORT".equals(str)) && request.body == null) {
            byte[] bArr = A01;
            int length = bArr.length;
            long j = (long) length;
            long j2 = (long) 0;
            if ((j2 | j) < 0 || j2 > j || j - j2 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            r2 = new AnonymousClass0My(length, bArr);
        } else {
            TypedOutput typedOutput = request.body;
            if (typedOutput == null) {
                r2 = null;
            } else {
                r2 = new AnonymousClass0UL(C08370wR.A00(typedOutput.mimeType()), typedOutput);
            }
        }
        C08340wO r7 = new C08340wO();
        r7.A01(request.url);
        r7.A03(request.method, r2);
        List<Header> list = request.headers;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Header header = list.get(i);
            String str2 = header.value;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = header.name;
            C08420wY r0 = r7.A03;
            C08420wY.A00(str3, str2);
            r0.A02(str3, str2);
        }
        AnonymousClass0Mz A002 = r6.A00(r7.A00());
        synchronized (A002) {
            if (!A002.A00) {
                A002.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        A002.A03.A00 = C07790vM.A00.A01("response.body().close()");
        try {
            C08450wb r3 = A002.A01.A0J;
            synchronized (r3) {
                deque = r3.A03;
                deque.add(A002);
            }
            C08220wC A012 = A002.A01();
            if (A012 != null) {
                C08450wb.A01(r3, deque, A002, false);
                String obj = A012.A07.A03.toString();
                int i2 = A012.A01;
                String str4 = A012.A04;
                String[] strArr = A012.A06.A00;
                int length2 = strArr.length >> 1;
                ArrayList arrayList = new ArrayList(length2);
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i3 << 1;
                    arrayList.add(new Header(strArr[i4], strArr[i4 + 1]));
                }
                AbstractC08210wB r5 = A012.A0B;
                if (r5.A00() == 0) {
                    r11 = null;
                } else {
                    r11 = new AnonymousClass0UK(r5);
                }
                return new Response(obj, i2, str4, arrayList, r11);
            }
            throw new IOException("Canceled");
        } catch (Throwable th) {
            C08450wb r22 = A002.A01.A0J;
            C08450wb.A01(r22, r22.A03, A002, false);
            throw th;
        }
    }

    public AnonymousClass0UJ() {
        this(new AnonymousClass0N1());
    }

    public AnonymousClass0UJ(AnonymousClass0N1 r3) {
        if (r3 != null) {
            this.A00 = r3;
            return;
        }
        throw new NullPointerException("client == null");
    }
}
