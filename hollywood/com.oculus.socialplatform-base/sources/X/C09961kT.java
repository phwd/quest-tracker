package X;

import android.os.SystemClock;
import java.io.IOException;
import java.util.regex.Pattern;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* renamed from: X.1kT  reason: invalid class name and case insensitive filesystem */
public class C09961kT implements Callback {
    public final /* synthetic */ AnonymousClass1lI A00;
    public final /* synthetic */ AnonymousClass1kR A01;
    public final /* synthetic */ C09951kS A02;

    public C09961kT(AnonymousClass1kR r1, AnonymousClass1lI r2, C09951kS r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    @Override // okhttp3.Callback
    public final void onFailure(Call call, IOException iOException) {
        C09951kS r1 = this.A02;
        if (call.isCanceled()) {
            r1.A00();
        } else {
            r1.A02(iOException);
        }
    }

    @Override // okhttp3.Callback
    public final void onResponse(Call call, Response response) throws IOException {
        AnonymousClass0PH r2;
        AnonymousClass1lI r4 = this.A00;
        r4.A01 = SystemClock.elapsedRealtime();
        ResponseBody responseBody = response.body;
        if (responseBody == null) {
            StringBuilder sb = new StringBuilder("Response body null: ");
            sb.append(response);
            IOException iOException = new IOException(sb.toString());
            C09951kS r1 = this.A02;
            if (call.isCanceled()) {
                r1.A00();
            } else {
                r1.A02(iOException);
            }
        } else {
            try {
                if (!response.isSuccessful()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unexpected HTTP code ");
                    sb2.append(response);
                    IOException iOException2 = new IOException(sb2.toString());
                    C09951kS r12 = this.A02;
                    if (call.isCanceled()) {
                        r12.A00();
                    } else {
                        r12.A02(iOException2);
                    }
                } else {
                    String header = response.header("Content-Range");
                    if (header != null) {
                        Pattern pattern = AnonymousClass0PH.A02;
                        if (pattern == null) {
                            pattern = Pattern.compile("[-/ ]");
                            AnonymousClass0PH.A02 = pattern;
                        }
                        try {
                            String[] split = pattern.split(header);
                            boolean z = false;
                            if (split.length == 4) {
                                z = true;
                            }
                            C00740Ii.A01(Boolean.valueOf(z));
                            C00740Ii.A01(Boolean.valueOf(split[0].equals("bytes")));
                            int parseInt = Integer.parseInt(split[1]);
                            int parseInt2 = Integer.parseInt(split[2]);
                            int parseInt3 = Integer.parseInt(split[3]);
                            boolean z2 = false;
                            if (parseInt2 > parseInt) {
                                z2 = true;
                            }
                            C00740Ii.A01(Boolean.valueOf(z2));
                            boolean z3 = false;
                            if (parseInt3 > parseInt2) {
                                z3 = true;
                            }
                            C00740Ii.A01(Boolean.valueOf(z3));
                            if (parseInt2 < parseInt3 - 1) {
                                r2 = new AnonymousClass0PH(parseInt, parseInt2);
                            } else {
                                r2 = new AnonymousClass0PH(parseInt, Integer.MAX_VALUE);
                            }
                            if (!(r2.A00 == 0 && r2.A01 == Integer.MAX_VALUE)) {
                                ((AnonymousClass1lA) r4).A02 = r2;
                                ((AnonymousClass1lA) r4).A00 = 8;
                            }
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException(String.format(null, "Invalid Content-Range header value: \"%s\"", header), e);
                        }
                    }
                    long contentLength = responseBody.contentLength();
                    if (contentLength < 0) {
                        contentLength = 0;
                    }
                    this.A02.A01(responseBody.byteStream(), (int) contentLength);
                }
            } catch (Exception e2) {
                C09951kS r13 = this.A02;
                if (call.isCanceled()) {
                    r13.A00();
                } else {
                    r13.A02(e2);
                }
            } catch (Throwable th) {
                responseBody.close();
                throw th;
            }
            responseBody.close();
        }
    }
}
