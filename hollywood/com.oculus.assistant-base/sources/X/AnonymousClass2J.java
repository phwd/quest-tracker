package X;

import java.util.ArrayList;
import java.util.List;

/* renamed from: X.2J  reason: invalid class name */
public final class AnonymousClass2J extends VD {
    public int A00 = 1;
    public final AbstractC1014qi[] A01;

    public AnonymousClass2J(AbstractC1014qi[] qiVarArr) {
        super(qiVarArr[0]);
        this.A01 = qiVarArr;
    }

    public static AnonymousClass2J A00(AbstractC1014qi qiVar, AbstractC1014qi qiVar2) {
        AbstractC1014qi[] qiVarArr;
        boolean z = qiVar instanceof AnonymousClass2J;
        if (z || (qiVar2 instanceof AnonymousClass2J)) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                ((AnonymousClass2J) qiVar).A01(arrayList);
            } else {
                arrayList.add(qiVar);
            }
            if (qiVar2 instanceof AnonymousClass2J) {
                ((AnonymousClass2J) qiVar2).A01(arrayList);
            } else {
                arrayList.add(qiVar2);
            }
            qiVarArr = (AbstractC1014qi[]) arrayList.toArray(new AbstractC1014qi[arrayList.size()]);
        } else {
            qiVarArr = new AbstractC1014qi[]{qiVar, qiVar2};
        }
        return new AnonymousClass2J(qiVarArr);
    }

    private final void A01(List list) {
        AbstractC1014qi[] qiVarArr = this.A01;
        int length = qiVarArr.length;
        for (int i = this.A00 - 1; i < length; i++) {
            AbstractC1014qi qiVar = qiVarArr[i];
            if (qiVar instanceof AnonymousClass2J) {
                ((AnonymousClass2J) qiVar).A01(list);
            } else {
                list.add(qiVar);
            }
        }
    }

    @Override // X.AbstractC1014qi, java.io.Closeable, X.VD, java.lang.AutoCloseable
    public final void close() {
        while (true) {
            ((VD) this).A00.close();
            int i = this.A00;
            AbstractC1014qi[] qiVarArr = this.A01;
            if (i < qiVarArr.length) {
                this.A00 = i + 1;
                ((VD) this).A00 = qiVarArr[i];
            } else {
                return;
            }
        }
    }
}
