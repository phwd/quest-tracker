package defpackage;

import android.content.Intent;
import android.util.Pair;
import java.io.File;
import org.chromium.base.task.PostTask;

/* renamed from: z00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5861z00 extends XY0 {
    @Override // defpackage.XY0
    public void a(Intent intent) {
        PostTask.e(Zo1.f9374a, new RunnableC5351w00());
        File[] listFiles = AbstractC1102Sb1.a().listFiles();
        boolean z = true;
        if (listFiles != null) {
            boolean z2 = true;
            for (File file : listFiles) {
                Pair c = AbstractC1224Ub1.c(file.getName());
                if (c != null && ((Boolean) c.second).booleanValue()) {
                    z2 &= file.delete();
                }
            }
            z = z2;
        }
        if (z) {
            C3070if1 if1 = Zo1.f9374a;
            PostTask.e(if1, new RunnableC5521x00());
            PostTask.e(if1, new RunnableC5691y00(this));
        }
    }
}
