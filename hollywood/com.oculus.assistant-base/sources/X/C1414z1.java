package X;

import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: X.z1  reason: case insensitive filesystem */
public final class C1414z1 implements AbstractC0449Yu {
    public final /* synthetic */ C0450Yv A00;

    public C1414z1(C0450Yv yv) {
        this.A00 = yv;
    }

    @Override // X.AbstractC0449Yu
    public final boolean A4u() {
        C0450Yv yv = this.A00;
        synchronized (yv.A08) {
            File file = new File(C0451Yx.A00(), AnonymousClass08.A04(yv.A05, ".pcm"));
            File file2 = new File(C0451Yx.A00(), AnonymousClass08.A04(yv.A05, ".index"));
            Log.d("MicDataLogger", AnonymousClass08.A04("Recording mic input to: ", file.getAbsolutePath()));
            try {
                if (!file.getParentFile().isDirectory()) {
                    file.getParentFile().mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                yv.A03 = fileOutputStream;
                yv.A01 = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                yv.A04 = fileOutputStream2;
                yv.A02 = new DataOutputStream(new BufferedOutputStream(fileOutputStream2));
                C0098Ac ac = yv.A06;
                ac.A00.post(new RunnableC0097Ab(ac, new C0795hj(ac, new z0(this))));
            } catch (IOException e) {
                Log.e("MicDataLogger", e.getMessage(), e);
                C0450Yv.A00(yv);
            }
        }
        return false;
    }
}
