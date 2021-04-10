package X;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* renamed from: X.0r3  reason: invalid class name and case insensitive filesystem */
public class C07080r3 implements AnonymousClass0D4<Void, List<TResult>> {
    public final /* synthetic */ Collection A00;

    public C07080r3(Collection collection) {
        this.A00 = collection;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
    @Override // X.AnonymousClass0D4
    public final Object then(AnonymousClass0DC<Void> r4) throws Exception {
        Collection<AnonymousClass0DC> collection = this.A00;
        if (collection.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass0DC r0 : collection) {
            arrayList.add(r0.A0G());
        }
        return arrayList;
    }
}
