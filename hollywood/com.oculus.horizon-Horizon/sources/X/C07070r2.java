package X;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0r2  reason: invalid class name and case insensitive filesystem */
public class C07070r2 implements AnonymousClass0D4<Object, Void> {
    public final /* synthetic */ AnonymousClass0DD A00;
    public final /* synthetic */ Object A01;
    public final /* synthetic */ ArrayList A02;
    public final /* synthetic */ AtomicBoolean A03;
    public final /* synthetic */ AtomicInteger A04;

    public C07070r2(Object obj, ArrayList arrayList, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, AnonymousClass0DD r5) {
        this.A01 = obj;
        this.A02 = arrayList;
        this.A03 = atomicBoolean;
        this.A04 = atomicInteger;
        this.A00 = r5;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
    @Override // X.AnonymousClass0D4
    public final Void then(AnonymousClass0DC<Object> r6) throws Exception {
        if (r6.A0K()) {
            synchronized (this.A01) {
                this.A02.add(r6.A0F());
            }
        }
        if (r6.A0I()) {
            this.A03.set(true);
        }
        if (this.A04.decrementAndGet() == 0) {
            ArrayList arrayList = this.A02;
            if (arrayList.size() != 0) {
                if (arrayList.size() == 1) {
                    this.A00.A01((Exception) arrayList.get(0));
                } else {
                    this.A00.A01(new AnonymousClass0Cu(String.format("There were %d exceptions.", Integer.valueOf(arrayList.size())), arrayList));
                    return null;
                }
            } else if (this.A03.get()) {
                this.A00.A00();
                return null;
            } else {
                this.A00.A02(null);
                return null;
            }
        }
        return null;
    }
}
