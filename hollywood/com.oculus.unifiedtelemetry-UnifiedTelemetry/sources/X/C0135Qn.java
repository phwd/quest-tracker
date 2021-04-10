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

/* renamed from: X.Qn  reason: case insensitive filesystem */
public final class C0135Qn implements Client {
    public static final byte[] A01 = new byte[0];
    public final LD A00;

    @Override // retrofit.client.Client
    public final Response execute(Request request) throws IOException {
        AbstractC0361di qv;
        Qu qu;
        LD ld = this.A00;
        String str = request.method;
        if ((TigonRequest.POST.equals(str) || "PUT".equals(str) || "PATCH".equals(str) || "PROPPATCH".equals(str) || "REPORT".equals(str)) && request.body == null) {
            byte[] bArr = A01;
            int length = bArr.length;
            long j = (long) length;
            long j2 = (long) 0;
            if ((j2 | j) < 0 || j2 > j || j - j2 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            qv = new LA(length, bArr);
        } else {
            TypedOutput typedOutput = request.body;
            if (typedOutput == null) {
                qv = null;
            } else {
                qv = new C0136Qv(C0366dn.A00(typedOutput.mimeType()), typedOutput);
            }
        }
        C0363dk dkVar = new C0363dk();
        dkVar.A01(request.url);
        dkVar.A03(request.method, qv);
        List<Header> list = request.headers;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Header header = list.get(i);
            String str2 = header.value;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = header.name;
            C0370dt dtVar = dkVar.A03;
            C0370dt.A00(str3, str2);
            dtVar.A02(str3, str2);
        }
        C0359dg A02 = ld.A00(dkVar.A00()).A02();
        String obj = A02.A07.A03.toString();
        int i2 = A02.A01;
        String str4 = A02.A04;
        String[] strArr = A02.A06.A00;
        int length2 = strArr.length >> 1;
        ArrayList arrayList = new ArrayList(length2);
        for (int i3 = 0; i3 < length2; i3++) {
            int i4 = i3 << 1;
            arrayList.add(new Header(strArr[i4], strArr[i4 + 1]));
        }
        AbstractC0358df dfVar = A02.A0B;
        if (dfVar.A00() == 0) {
            qu = null;
        } else {
            qu = new Qu(dfVar);
        }
        return new Response(obj, i2, str4, arrayList, qu);
    }

    public C0135Qn() {
        this(new LD());
    }

    public C0135Qn(LD ld) {
        if (ld != null) {
            this.A00 = ld;
            return;
        }
        throw new NullPointerException("client == null");
    }
}
