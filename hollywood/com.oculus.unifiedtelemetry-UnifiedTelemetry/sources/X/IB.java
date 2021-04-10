package X;

import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@VisibleForTesting
public class IB implements Z9 {
    public final Fw A00;
    public final List<C00258p> A01;
    public volatile boolean A02;

    @Override // X.ST
    public final boolean A3G() {
        return true;
    }

    @Override // X.ST
    public final void A3R() {
        List<C00258p> list = this.A01;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).A3R();
        }
    }

    @Override // X.Z9
    public final void unlock() {
        List<C00258p> list = this.A01;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).unlock();
        }
        this.A02 = false;
    }

    public IB(List<C00258p> list, Fw fw) {
        if (!list.isEmpty()) {
            this.A01 = list;
            this.A00 = fw;
            return;
        }
        throw new IllegalArgumentException("payloads cannot be empty");
    }

    /* JADX INFO: finally extract failed */
    @Override // X.ST
    public final void A5v(Writer writer) throws IOException {
        String str;
        if (!A2z()) {
            List<C00258p> list = this.A01;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).A01();
            }
            this.A02 = true;
        }
        C0086Gr gr = new C0086Gr(writer);
        int i2 = gr.A00;
        if (i2 == 1) {
            gr.A00 = 2;
            Writer writer2 = gr.A01;
            writer2.write(123);
            writer2.write("\"batches\":[");
            List<C00258p> list2 = this.A01;
            int size2 = list2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                C00258p r2 = list2.get(i3);
                int i4 = gr.A00;
                if (i4 == 2) {
                    gr.A00 = 3;
                } else if (i4 == 3) {
                    writer2.write(44);
                } else {
                    str = AnonymousClass06.A01("state=", i4);
                }
                r2.A05(writer2, true);
            }
            Fw fw = this.A00;
            int i5 = gr.A00;
            if (i5 == 2 || i5 == 3) {
                writer2.write("],");
                YE A022 = fw.A01.A02();
                try {
                    Fv fv = fw.A00;
                    YE A023 = ((MF) A022).A01.A02();
                    A022.A0C("request_info", A023);
                    fv.A00(A023);
                    YD.A00().A06(writer2, A022);
                    A022.A02();
                    writer2.write(125);
                    return;
                } catch (Throwable th) {
                    A022.A02();
                    throw th;
                }
            } else {
                throw new IllegalStateException(AnonymousClass06.A01("state=", i5));
            }
        } else {
            StringBuilder sb = new StringBuilder("Expected state ");
            sb.append(1);
            sb.append("; got ");
            sb.append(i2);
            str = sb.toString();
        }
        throw new IllegalStateException(str);
    }

    @Override // X.Z9
    public final boolean A2z() {
        return this.A02;
    }
}
