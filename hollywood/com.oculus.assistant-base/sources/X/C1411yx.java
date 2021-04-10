package X;

import android.media.AudioAttributes;
import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.regex.Matcher;

/* renamed from: X.yx  reason: case insensitive filesystem */
public final class C1411yx implements AbstractC0528bR {
    public final /* synthetic */ AbstractC0444Yo A00;
    public final /* synthetic */ C0446Yq A01;

    public C1411yx(C0446Yq yq, AbstractC0444Yo yo) {
        this.A01 = yq;
        this.A00 = yo;
    }

    @Override // X.AbstractC0528bR
    public final void onFailure(C1146tm tmVar, IOException iOException) {
        C0514bB.A02(tmVar, "call");
        C0514bB.A02(iOException, "e");
        C0139Dd.A0L("ServerTextToSpeech", "Network error during TTS request", iOException);
        AbstractC0444Yo yo = this.A00;
        if (yo != null) {
            yo.A45(iOException);
        }
    }

    @Override // X.AbstractC0528bR
    public final void onResponse(C1146tm tmVar, C0554br brVar) {
        int i;
        C0239Mb mb;
        Integer A03;
        if (brVar != null) {
            try {
                int i2 = brVar.A02;
                if (i2 < 200 || i2 >= 300) {
                    String format = String.format("Unexpected response code %d", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
                    C0514bB.A01(format, "java.lang.String.format(this, *args)");
                    throw new IllegalArgumentException(format);
                }
                String A002 = brVar.A00(HttpRequestMultipart.CONTENT_TYPE);
                if (A002 != null) {
                    Matcher matcher = C0446Yq.A06.matcher(A002);
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        if (group == null || (A03 = C00080r.A03(group)) == null) {
                            i = 0;
                        } else {
                            i = A03.intValue();
                        }
                        C0446Yq yq = this.A01;
                        boolean z = yq.A03;
                        if (z) {
                            AbstractC0444Yo yo = this.A00;
                            C0239Mb mb2 = yq.A00;
                            if (mb2 != null) {
                                mb2.A08 = true;
                                mb2.A05.interrupt();
                            }
                            C0239Mb mb3 = new C0239Mb(yq.A02, i, new C1410yw(yo), new AudioAttributes.Builder().setContentType(1).setUsage(11).build());
                            yq.A00 = mb3;
                            mb3.A00 = true;
                            mb3.A05.start();
                        }
                        AbstractC0444Yo yo2 = this.A00;
                        if (yo2 != null) {
                            yo2.A4M();
                        }
                        InputStream A3K = brVar.A0B.A01().A3K();
                        byte[] bArr = new byte[512];
                        while (true) {
                            int read = A3K.read(bArr);
                            if (read != -1) {
                                if (z) {
                                    C0239Mb mb4 = yq.A00;
                                    if (mb4 != null) {
                                        mb4.A00(bArr, 0, read);
                                    } else {
                                        return;
                                    }
                                }
                                if (yo2 != null) {
                                    byte[] copyOf = Arrays.copyOf(bArr, read);
                                    C0514bB.A01(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                                    yo2.A3s(copyOf);
                                }
                            } else if (z && (mb = yq.A00) != null) {
                                MX mx = new MX();
                                mx.A03 = true;
                                mb.A06.add(mx);
                                return;
                            } else {
                                return;
                            }
                        }
                    } else {
                        String format2 = String.format("Unable to parse sampling rate from: %s", Arrays.copyOf(new Object[]{A002}, 1));
                        C0514bB.A01(format2, "java.lang.String.format(this, *args)");
                        throw new IllegalArgumentException(format2);
                    }
                }
            } catch (Exception e) {
                C0139Dd.A0L("ServerTextToSpeech", "Error during TTS: ", e);
                AbstractC0444Yo yo3 = this.A00;
                if (yo3 != null) {
                    yo3.A45(e);
                }
                this.A01.A00();
                return;
            }
        }
        throw new IllegalArgumentException("No content type returned");
    }
}
