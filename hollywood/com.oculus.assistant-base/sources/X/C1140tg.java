package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.util.List;

/* renamed from: X.tg  reason: case insensitive filesystem */
public final class C1140tg implements AbstractC0545bi {
    public final AbstractC0537ba A00;

    @Override // X.AbstractC0545bi
    public final C0554br A3L(C1138te teVar) {
        int i;
        long j;
        C0546bj bjVar;
        C0551bo boVar = teVar.A01;
        C0550bn bnVar = new C0550bn(boVar);
        AbstractC0552bp bpVar = boVar.A04;
        if (bpVar != null) {
            boolean z = bpVar instanceof C1145tl;
            if (!z && (bjVar = ((C1002qT) bpVar).A00) != null) {
                bnVar.A02(HttpRequestMultipart.CONTENT_TYPE, bjVar.toString());
            }
            if (z) {
                i = ((C1145tl) bpVar).A00;
                j = (long) i;
            } else if (!(bpVar instanceof C1002qT)) {
                j = -1;
            } else {
                i = ((C1002qT) bpVar).A01.length;
                j = (long) i;
            }
            if (j != -1) {
                bnVar.A02("Content-Length", Long.toString(j));
                bnVar.A03.A01("Transfer-Encoding");
            } else {
                bnVar.A02("Transfer-Encoding", "chunked");
                bnVar.A03.A01("Content-Length");
            }
        }
        C0542bf bfVar = boVar.A02;
        boolean z2 = false;
        if (bfVar.A00("Host") == null) {
            bnVar.A02("Host", C0561by.A04(boVar.A03, false));
        }
        if (bfVar.A00("Connection") == null) {
            bnVar.A02("Connection", "Keep-Alive");
        }
        if (bfVar.A00("Accept-Encoding") == null && bfVar.A00("Range") == null) {
            z2 = true;
            bnVar.A02("Accept-Encoding", "gzip");
        }
        AbstractC0537ba baVar = this.A00;
        C0544bh bhVar = boVar.A03;
        List A3h = baVar.A3h(bhVar);
        if (!A3h.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = A3h.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (i2 > 0) {
                    sb.append("; ");
                }
                C0536bZ bZVar = (C0536bZ) A3h.get(i2);
                sb.append(bZVar.A02);
                sb.append('=');
                sb.append(bZVar.A04);
            }
            bnVar.A02("Cookie", sb.toString());
        }
        if (bfVar.A00(HttpRequestMultipart.USER_AGENT) == null) {
            bnVar.A02(HttpRequestMultipart.USER_AGENT, "okhttp/3.6.0");
        }
        C0554br A002 = teVar.A00(bnVar.A00(), teVar.A03, teVar.A04, teVar.A02);
        C0542bf bfVar2 = A002.A06;
        C0574cD.A02(baVar, bhVar, bfVar2);
        C0553bq bqVar = new C0553bq(A002);
        bqVar.A07 = boVar;
        if (z2 && "gzip".equalsIgnoreCase(A002.A00("Content-Encoding")) && C0574cD.A03(A002)) {
            t2 t2Var = new t2(A002.A0B.A01());
            C0541be A01 = bfVar2.A01();
            A01.A01("Content-Encoding");
            A01.A01("Content-Length");
            C0542bf bfVar3 = new C0542bf(A01);
            bqVar.A05 = bfVar3.A01();
            bqVar.A0B = new C1137td(bfVar3, new C00222y(t2Var));
        }
        return bqVar.A00();
    }

    public C1140tg(AbstractC0537ba baVar) {
        this.A00 = baVar;
    }
}
