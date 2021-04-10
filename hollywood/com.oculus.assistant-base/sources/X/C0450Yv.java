package X;

import android.util.Log;
import com.facebook.assistant.oacr.OacrConstants;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: X.Yv  reason: case insensitive filesystem */
public final class C0450Yv {
    public Yt A00;
    public DataOutputStream A01;
    public DataOutputStream A02;
    public FileOutputStream A03;
    public FileOutputStream A04;
    public String A05 = OacrConstants.AUTO_SPEECH_DOMAIN;
    public final C0098Ac A06 = new C0098Ac("MicDataLogger");
    public final AbstractC0449Yu A07 = new C1414z1(this);
    public final Object A08 = new Object();

    public static void A00(C0450Yv yv) {
        synchronized (yv.A08) {
            try {
                DataOutputStream dataOutputStream = yv.A01;
                if (dataOutputStream != null) {
                    dataOutputStream.flush();
                }
                DataOutputStream dataOutputStream2 = yv.A02;
                if (dataOutputStream2 != null) {
                    dataOutputStream2.writeInt(0);
                    yv.A02.flush();
                }
                FileOutputStream fileOutputStream = yv.A03;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (yv.A04 != null) {
                    yv.A03.close();
                }
                Log.d("MicDataLogger", AnonymousClass08.A04("Finished writing ", yv.A05));
            } catch (IOException e) {
                Log.e("MicDataLogger", e.getMessage(), e);
            }
            yv.A01 = null;
            yv.A03 = null;
            yv.A04 = null;
            yv.A02 = null;
        }
    }
}
