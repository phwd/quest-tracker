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

/* renamed from: X.0Vp  reason: invalid class name */
public final class AnonymousClass0Vp implements Client {
    public static final byte[] A01 = new byte[0];
    public final AnonymousClass0Qs A00;

    @Override // retrofit.client.Client
    public final Response execute(Request request) throws IOException {
        AbstractC05690kc r2;
        Deque<AnonymousClass0Qd> deque;
        AnonymousClass0Vq r11;
        AnonymousClass0Qs r6 = this.A00;
        String str = request.method;
        if ((TigonRequest.POST.equals(str) || "PUT".equals(str) || "PATCH".equals(str) || "PROPPATCH".equals(str) || "REPORT".equals(str)) && request.body == null) {
            byte[] bArr = A01;
            int length = bArr.length;
            long j = (long) length;
            long j2 = (long) 0;
            if ((j2 | j) < 0 || j2 > j || j - j2 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            r2 = new AnonymousClass0Q0(length, bArr);
        } else {
            TypedOutput typedOutput = request.body;
            if (typedOutput == null) {
                r2 = null;
            } else {
                r2 = new AnonymousClass0Vr(C05820lT.A00(typedOutput.mimeType()), typedOutput);
            }
        }
        C05710kf r7 = new C05710kf();
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
            C06030lq r0 = r7.A03;
            C06030lq.A00(str3, str2);
            r0.A02(str3, str2);
        }
        AnonymousClass0Qd A002 = r6.A00(r7.A00());
        synchronized (A002) {
            if (!A002.A00) {
                A002.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        A002.A03.A00 = C04670hG.A00.A01("response.body().close()");
        try {
            C06110ly r3 = A002.A01.A0H;
            synchronized (r3) {
                deque = r3.A03;
                deque.add(A002);
            }
            C05660kD A012 = A002.A01();
            C06110ly.A02(r3, deque, A002, false);
            String r72 = A012.A07.A03.toString();
            int i2 = A012.A01;
            String str4 = A012.A04;
            String[] strArr = A012.A06.A00;
            int length2 = strArr.length >> 1;
            ArrayList arrayList = new ArrayList(length2);
            for (int i3 = 0; i3 < length2; i3++) {
                int i4 = i3 << 1;
                arrayList.add(new Header(strArr[i4], strArr[i4 + 1]));
            }
            AbstractC05650kC r5 = A012.A0B;
            if (r5.A00() == 0) {
                r11 = null;
            } else {
                r11 = new AnonymousClass0Vq(r5);
            }
            return new Response(r72, i2, str4, arrayList, r11);
        } catch (Throwable th) {
            C06110ly r22 = A002.A01.A0H;
            C06110ly.A02(r22, r22.A03, A002, false);
            throw th;
        }
    }

    public AnonymousClass0Vp() {
        this(new AnonymousClass0Qs());
    }

    public AnonymousClass0Vp(AnonymousClass0Qs r3) {
        if (r3 != null) {
            this.A00 = r3;
            return;
        }
        throw new NullPointerException("client == null");
    }
}
