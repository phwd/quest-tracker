package X;

import android.os.Build;
import android.os.Trace;
import com.facebook.analytics2.logger.EventBatchListener;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.logging.ExtraKeys;
import com.oculus.logging.analytics2.DebugEventBaseParameterHandler;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class GG<T> {
    @Nullable
    public G0 A00;
    @Nullable
    public G1 A01;
    @Nullable
    public GG<T>.Batch A02;
    public final int A03;
    public final int A04;
    public final Fx A05;
    public final ByteBuffer A06;
    public final Set<EventBatchListener> A07 = new HashSet();
    public final char[] A08;
    public final MG A09;

    public abstract GG<T>.Batch A04(@Nullable String str, @Nullable GG<T>.Batch batch) throws IOException;

    @Nullable
    public abstract T A05();

    public GG(int i, int i2, Fx fx, MG mg) {
        if (i <= i2) {
            this.A04 = i;
            this.A03 = i2;
            this.A08 = new char[1024];
            this.A06 = ByteBuffer.allocate(2048);
            this.A05 = fx;
            this.A09 = mg;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" > ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    public final void A01() {
        GG<T>.Batch batch = this.A02;
        if (batch == null) {
            return;
        }
        if (this.A01 != null) {
            try {
                batch.A03.close();
                batch.A01.A03();
            } catch (IOException unused) {
            }
            this.A02 = null;
            return;
        }
        throw new IllegalStateException("mBatchSessionMetadataHelper is null");
    }

    public final void A02(G0 g0) {
        this.A00 = g0;
        this.A01 = new G1(this.A09, g0);
        GG<T>.Batch batch = this.A02;
        if (batch != null) {
            try {
                batch.A03.close();
                batch.A01.A03();
            } catch (IOException unused) {
            }
            this.A02 = null;
        }
    }

    public final void A03(MF mf) throws IOException {
        Writer writer;
        Throwable th;
        int incrementAndGet;
        C0260Yj yj;
        if (this.A01 != null) {
            GG<T>.Batch batch = this.A02;
            if (batch == null || !batch.A01.A0B(this)) {
                A01();
                G0 g0 = this.A00;
                String str = null;
                if (!(g0 == null || (yj = g0.A00) == null)) {
                    str = yj.userId;
                }
                GG<T>.Batch A042 = A04(str, this.A02);
                this.A02 = A042;
                try {
                    G2 g2 = A042.A02;
                    Fx fx = this.A05;
                    G2.A01(g2);
                    G2.A00(g2);
                    Writer writer2 = g2.A03;
                    YE A022 = fx.A00.A02();
                    Trace.beginSection("writeFixedData");
                    try {
                        YE.A00(A022, DebugEventBaseParameterHandler.ParamKeys.TIME, Long.valueOf(System.currentTimeMillis()));
                        SS ss = fx.A01;
                        YE.A00(A022, "app_id", ss.A2H());
                        YE.A00(A022, "app_ver", ss.A2I());
                        YE.A00(A022, "build_num", Integer.valueOf(ss.A2J()));
                        YE.A00(A022, "device", Build.MODEL);
                        YE.A00(A022, ExtraKeys.OS_VERSION, Build.VERSION.RELEASE);
                        YE.A00(A022, "device_id", fx.A02.A2F());
                        YD.A00().A06(writer2, A022);
                        A022.A02();
                        Trace.endSection();
                        G2 g22 = this.A02.A02;
                        G1 g1 = this.A01;
                        G2.A01(g22);
                        G2.A00(g22);
                        Writer writer3 = g22.A03;
                        MG mg = g1.A01;
                        YE A023 = mg.A02();
                        Trace.beginSection("writeNewSessionData");
                        try {
                            G0 g02 = g1.A00;
                            YE.A00(A023, "session_id", g02.A01);
                            synchronized (g02) {
                                incrementAndGet = g02.A02.incrementAndGet();
                            }
                            YE.A00(A023, "seq", Integer.valueOf(incrementAndGet));
                            C0260Yj yj2 = g02.A00;
                            if (yj2 == null) {
                                YE.A00(A023, "uid", null);
                            } else {
                                yj2.A01(A023, mg);
                            }
                            YD.A00().A06(writer3, A023);
                            A023.A02();
                            Trace.endSection();
                        } catch (Throwable th2) {
                            th = th2;
                            A023.A02();
                            Trace.endSection();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        A022.A02();
                        Trace.endSection();
                        throw th;
                    }
                } catch (IOException e) {
                    this.A02.A01.A08(this);
                    A01();
                    throw e;
                }
            }
            GG<T>.Batch batch2 = this.A02;
            try {
                G2 g23 = batch2.A02;
                G2.A01(g23);
                if (!g23.A02) {
                    G2.A01(g23);
                    G2.A00(g23);
                    writer = g23.A03;
                    writer.write("\"data\":[");
                    g23.A02 = true;
                } else {
                    writer = g23.A03;
                    writer.write(44);
                }
                if (writer != null) {
                    YD yd = mf.A02;
                    if (yd != null) {
                        yd.A05(writer, mf);
                        batch2.A03.flush();
                        batch2.A00++;
                        return;
                    }
                    throw new AssertionError("No encoder set, please call setEncoder() first!");
                }
                throw new AssertionError("Writer is null!");
            } finally {
                batch2.A01.A08(this);
            }
        } else {
            throw new IllegalStateException("mBatchSessionMetadataHelper is null");
        }
    }
}
