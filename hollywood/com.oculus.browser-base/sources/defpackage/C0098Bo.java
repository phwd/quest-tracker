package defpackage;

import J.N;
import java.io.IOException;
import java.util.Objects;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: Bo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0098Bo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0220Do f7757a;

    public C0098Bo(C0220Do r1) {
        this.f7757a = r1;
    }

    public void a(C5653xo xoVar) {
        C0220Do r14 = this.f7757a;
        C5653xo xoVar2 = r14.g;
        int i = xoVar2.r;
        C0768Mo mo = (C0768Mo) r14.b;
        Objects.requireNonNull(mo);
        int i2 = xoVar2.r;
        if (i2 > 0) {
            ChildProcessLauncherHelperImpl.f.put(Integer.valueOf(i2), mo.f8502a);
            C1317Vo vo = mo.f8502a.k;
            if (vo != null) {
                if (vo.H == -1 || vo.I.size() < vo.H) {
                    vo.I.add(new C1134So(xoVar2, false, 1, false, 1));
                    vo.c(vo.I.size() - 1);
                    ComponentCallbacks2C5632xh xhVar = mo.f8502a.l;
                    if (xhVar != null) {
                        xhVar.c();
                    }
                } else {
                    StringBuilder i3 = AbstractC2531fV.i("mRankings.size:");
                    i3.append(vo.I.size());
                    i3.append(" mMaxSize:");
                    i3.append(vo.H);
                    throw new RuntimeException(i3.toString());
                }
            }
        }
        long j = mo.f8502a.r;
        if (j != 0) {
            N.MXR$KaDS(j, xoVar2.r);
        }
        mo.f8502a.r = 0;
        try {
            for (FileDescriptorInfo fileDescriptorInfo : r14.d) {
                fileDescriptorInfo.G.close();
            }
        } catch (IOException e) {
            AbstractC1220Ua0.f("ChildProcLauncher", "Failed to close FD.", e);
        }
    }
}
