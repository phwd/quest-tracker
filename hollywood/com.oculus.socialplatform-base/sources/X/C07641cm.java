package X;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: X.1cm  reason: invalid class name and case insensitive filesystem */
public final class C07641cm {
    public final AnonymousClass06o<List<Throwable>> A00;
    public final C07791d6 A01;
    public final C07741cw A02;
    public final C08641ey A03;
    public final C07891dd A04;
    public final C07931dh A05;
    public final AnonymousClass1dM A06 = new AnonymousClass1dM();
    public final AnonymousClass1cX A07 = new AnonymousClass1cX();
    public final C08721fA A08;
    public final C07771cz A09;

    @NonNull
    public final List<AbstractC08251eH> A00() {
        List<AbstractC08251eH> list;
        C07931dh r2 = this.A05;
        synchronized (r2) {
            list = r2.A00;
        }
        if (!list.isEmpty()) {
            return list;
        }
        throw new C07461cF();
    }

    @NonNull
    public final <Model> List<AbstractC07011bT<Model, ?>> A01(@NonNull Model model) {
        List<AbstractC07011bT<Model, ?>> list;
        ArrayList arrayList;
        C07741cw r2 = this.A02;
        Class<?> cls = model.getClass();
        synchronized (r2) {
            Map<Class<?>, C07841dW<?>> map = r2.A00.A00;
            C07841dW<?> r0 = map.get(cls);
            if (r0 == null) {
                list = null;
            } else {
                list = r0.A00;
            }
            if (list == null) {
                C07381c7 r5 = r2.A01;
                synchronized (r5) {
                    try {
                        arrayList = new ArrayList();
                        for (C07391c8<?, ?> r3 : r5.A02) {
                            Set<C07391c8<?, ?>> set = r5.A03;
                            if (!set.contains(r3) && r3.A02.isAssignableFrom(cls)) {
                                set.add(r3);
                                AbstractC07011bT<? extends Model, ? extends Data> A1o = r3.A00.A1o(r5);
                                AnonymousClass1S2.A00(A1o);
                                arrayList.add(A1o);
                                set.remove(r3);
                            }
                        }
                    } catch (Throwable th) {
                        r5.A03.clear();
                        throw th;
                    }
                }
                list = Collections.unmodifiableList(arrayList);
                if (map.put(cls, new C07841dW<>(list)) != null) {
                    StringBuilder sb = new StringBuilder("Already cached loaders for model: ");
                    sb.append(cls);
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
        if (!list.isEmpty()) {
            int size = list.size();
            List<AbstractC07011bT<Model, ?>> emptyList = Collections.emptyList();
            boolean z = true;
            for (int i = 0; i < size; i++) {
                AbstractC07011bT<Model, ?> r1 = list.get(i);
                if (r1.A5N(model)) {
                    if (z) {
                        emptyList = new ArrayList<>(size - i);
                        z = false;
                    }
                    emptyList.add(r1);
                }
            }
            if (!emptyList.isEmpty()) {
                return emptyList;
            }
            throw new C07421cB(model, list);
        }
        throw new C07421cB(model);
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/1ck<*>;)LX/1cm; */
    @NonNull
    public final void A02(@NonNull AbstractC07621ck r4) {
        C07791d6 r2 = this.A01;
        synchronized (r2) {
            r2.A00.put(r4.A3h(), r4);
        }
    }

    /* JADX WARN: Incorrect return type in method signature: <TResource:Ljava/lang/Object;>(Ljava/lang/Class<TTResource;>;LX/1dV<TTResource;>;)LX/1cm; */
    @NonNull
    public final void A03(@NonNull Class cls, @NonNull AnonymousClass1dV r5) {
        C07771cz r2 = this.A09;
        synchronized (r2) {
            r2.A00.add(new C07761cy<>(cls, r5));
        }
    }

    /* JADX WARN: Incorrect return type in method signature: <Model:Ljava/lang/Object;Data:Ljava/lang/Object;>(Ljava/lang/Class<TModel;>;Ljava/lang/Class<TData;>;LX/1c9<TModel;TData;>;)LX/1cm; */
    @NonNull
    public final void A04(@NonNull Class cls, @NonNull Class cls2, @NonNull AbstractC07401c9 r8) {
        C07741cw r4 = this.A02;
        synchronized (r4) {
            C07381c7 r3 = r4.A01;
            synchronized (r3) {
                C07391c8<?, ?> r2 = new C07391c8<>(cls, cls2, r8);
                List<C07391c8<?, ?>> list = r3.A02;
                list.add(list.size(), r2);
            }
            r4.A00.A00.clear();
        }
    }

    /* JADX WARN: Incorrect return type in method signature: <TResource:Ljava/lang/Object;Transcode:Ljava/lang/Object;>(Ljava/lang/Class<TTResource;>;Ljava/lang/Class<TTranscode;>;LX/1fP<TTResource;TTranscode;>;)LX/1cm; */
    @NonNull
    public final void A05(@NonNull Class cls, @NonNull Class cls2, @NonNull AbstractC08801fP r6) {
        C08641ey r2 = this.A03;
        synchronized (r2) {
            r2.A00.add(new C08631ex<>(cls, cls2, r6));
        }
    }

    /* JADX WARN: Incorrect return type in method signature: <Data:Ljava/lang/Object;TResource:Ljava/lang/Object;>(Ljava/lang/String;Ljava/lang/Class<TData;>;Ljava/lang/Class<TTResource;>;LX/1dN<TData;TTResource;>;)LX/1cm; */
    @NonNull
    public final void A06(@NonNull String str, @NonNull Class cls, @NonNull Class cls2, @NonNull AnonymousClass1dN r7) {
        C08721fA r2 = this.A08;
        synchronized (r2) {
            List<String> list = r2.A00;
            if (!list.contains(str)) {
                list.add(str);
            }
            Map<String, List<C08791fJ<?, ?>>> map = r2.A01;
            List<C08791fJ<?, ?>> list2 = map.get(str);
            if (list2 == null) {
                list2 = new ArrayList<>();
                map.put(str, list2);
            }
            list2.add(new C08791fJ<>(cls, cls2, r7));
        }
    }

    public C07641cm() {
        C08131e2 r1 = new C08131e2(new AnonymousClass0WB(20), new AnonymousClass1dY(), new C07851dX());
        this.A00 = r1;
        this.A02 = new C07741cw(r1);
        this.A04 = new C07891dd();
        this.A08 = new C08721fA();
        this.A09 = new C07771cz();
        this.A01 = new C07791d6();
        this.A03 = new C08641ey();
        this.A05 = new C07931dh();
        List asList = Arrays.asList("Gif", "Bitmap", "BitmapDrawable");
        ArrayList<String> arrayList = new ArrayList(asList.size());
        arrayList.addAll(asList);
        arrayList.add(0, "legacy_prepend_all");
        arrayList.add("legacy_append");
        C08721fA r4 = this.A08;
        synchronized (r4) {
            List<String> list = r4.A00;
            ArrayList arrayList2 = new ArrayList(list);
            list.clear();
            for (String str : arrayList) {
                list.add(str);
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (!arrayList.contains(str2)) {
                    list.add(str2);
                }
            }
        }
    }
}
