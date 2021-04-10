package X;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.Arrays;

public final class z3 implements AbstractC0449Yu {
    public String A00;
    public final /* synthetic */ C0450Yv A01;

    public z3(C0450Yv yv, String str) {
        this.A01 = yv;
        this.A00 = str;
    }

    @Override // X.AbstractC0449Yu
    public final boolean A4u() {
        C0450Yv yv = this.A01;
        C0450Yv.A00(yv);
        if (!TextUtils.isEmpty(this.A00)) {
            File file = new File(C0451Yx.A00(), AnonymousClass08.A04(yv.A05, ".pcm"));
            File file2 = new File(C0451Yx.A00(), AnonymousClass08.A04(yv.A05, ".index"));
            File file3 = new File(file2.getParentFile(), AnonymousClass08.A04(this.A00, ".pcm"));
            File file4 = new File(file.getParentFile(), AnonymousClass08.A04(this.A00, ".index"));
            file.renameTo(file3);
            file2.renameTo(file4);
            Log.d("MicDataLogger", AnonymousClass08.A04("Recording renamed to ", file3.getName()));
            File[] listFiles = C0451Yx.A00().listFiles();
            Arrays.sort(listFiles, new Yw());
            int length = listFiles.length - 200;
            for (int i = 0; i < length; i++) {
                listFiles[i].delete();
                C0139Dd.A0F("MicLogFile", "Removing log file %s", listFiles[i].getName());
            }
        }
        TextUtils.isEmpty(this.A00);
        C0098Ac ac = yv.A06;
        ac.A00.post(new RunnableC0097Ab(ac, new C0795hj(ac, new C1415z2(this))));
        return true;
    }
}
