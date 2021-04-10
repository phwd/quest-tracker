package X;

import com.google.gson.InstanceCreator;
import com.google.gson.internal.Excluder;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.0yb  reason: invalid class name and case insensitive filesystem */
public final class C08790yb {
    public AbstractC08770yZ A00 = EnumC03370cm.IDENTITY;
    public EnumC08850yk A01 = EnumC08850yk.DEFAULT;
    public Excluder A02 = Excluder.A02;
    public final List<AbstractC08860ym> A03 = new ArrayList();
    public final List<AbstractC08860ym> A04 = new ArrayList();
    public final Map<Type, InstanceCreator<?>> A05 = new HashMap();

    public final C08780ya A00() {
        List<AbstractC08860ym> list = this.A03;
        int size = list.size();
        List<AbstractC08860ym> list2 = this.A04;
        ArrayList arrayList = new ArrayList(size + list2.size() + 3);
        arrayList.addAll(list);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(list2);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        return new C08780ya(this.A02, this.A00, this.A05, this.A01, list, list2, arrayList);
    }
}
