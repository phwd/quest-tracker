package X;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.8j  reason: invalid class name and case insensitive filesystem */
public final class C00598j {
    public RunnableC00578h A00;
    public FileOutputStream A01;
    public String A02;
    public DataOutputStream A03;
    public final AbstractC00588i A04;
    public final File A05;
    public final Object A06 = new Object();
    public final String A07;
    public final List A08;

    public C00598j() {
        StringBuilder sb = new StringBuilder();
        sb.append(BX.A00().getFilesDir());
        sb.append(File.separator);
        sb.append("LastAudioLog");
        this.A05 = new File(sb.toString());
        this.A08 = new ArrayList();
        this.A04 = new C0745gV(this);
        this.A07 = "LastTranscription.pcm";
    }

    public static void A00(C00598j r4) {
        synchronized (r4.A06) {
            try {
                DataOutputStream dataOutputStream = r4.A03;
                if (dataOutputStream != null) {
                    dataOutputStream.flush();
                }
                FileOutputStream fileOutputStream = r4.A01;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                C0139Dd.A0F("LastAudioLogger", "Finished writing %s", r4.A07);
            } catch (IOException e) {
                C0139Dd.A0L("LastAudioLogger", "Failed closing file.", e);
            }
            r4.A03 = null;
            r4.A01 = null;
        }
    }
}
