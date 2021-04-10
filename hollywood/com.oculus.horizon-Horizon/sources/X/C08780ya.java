package X;

import com.google.gson.InstanceCreator;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters$32;
import com.google.gson.internal.bind.TypeAdapters$33;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* renamed from: X.0ya  reason: invalid class name and case insensitive filesystem */
public final class C08780ya {
    public static final C09110zQ<?> A0B = new C09110zQ<>(Object.class);
    public final AbstractC08770yZ A00;
    public final Excluder A01;
    public final List<AbstractC08860ym> A02;
    public final List<AbstractC08860ym> A03;
    public final List<AbstractC08860ym> A04;
    public final Map<Type, InstanceCreator<?>> A05;
    public final boolean A06;
    public final C08920yx A07;
    public final JsonAdapterAnnotationTypeAdapterFactory A08;
    public final ThreadLocal<Map<C09110zQ<?>, C03290cd<?>>> A09;
    public final Map<C09110zQ<?>, AnonymousClass0yl<?>> A0A;

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/0zR;Ljava/lang/reflect/Type;)TT; */
    public static final Object A00(C08780ya r4, C09120zR r5, Type type) throws AnonymousClass0c9, C03080c5 {
        Object obj;
        boolean z = r5.A07;
        boolean z2 = true;
        r5.A07 = true;
        try {
            r5.A0G();
            z2 = false;
            obj = r4.A04(new C09110zQ(type)).A02(r5);
        } catch (EOFException e) {
            if (z2) {
                obj = null;
            } else {
                throw new C03080c5(e);
            }
        } catch (IllegalStateException e2) {
            throw new C03080c5(e2);
        } catch (IOException e3) {
            throw new C03080c5(e3);
        } catch (AssertionError e4) {
            throw new AssertionError(AnonymousClass006.A05("AssertionError (GSON 2.8.5): ", e4.getMessage()), e4);
        } catch (Throwable th) {
            r5.A07 = z;
            throw th;
        }
        r5.A07 = z;
        return obj;
    }

    public static void A02(Object obj, C09120zR r1) {
        if (obj != null) {
            try {
                if (r1.A0G() != AnonymousClass007.A0J) {
                    throw new AnonymousClass0c9("JSON document was not fully consumed.");
                }
            } catch (C09140zV e) {
                throw new C03080c5(e);
            } catch (IOException e2) {
                throw new AnonymousClass0c9(e2);
            }
        }
    }

    public final <T> AnonymousClass0yl<T> A03(AbstractC08860ym r4, C09110zQ<T> r5) {
        List<AbstractC08860ym> list = this.A04;
        if (!list.contains(r4)) {
            r4 = this.A08;
        }
        boolean z = false;
        for (AbstractC08860ym r0 : list) {
            if (z) {
                AnonymousClass0yl<T> A1x = r0.A1x(this, r5);
                if (A1x != null) {
                    return A1x;
                }
            } else if (r0 == r4) {
                z = true;
            }
        }
        StringBuilder sb = new StringBuilder("GSON cannot serialize ");
        sb.append(r5);
        throw new IllegalArgumentException(sb.toString());
    }

    public final <T> AnonymousClass0yl<T> A04(C09110zQ<T> r9) {
        Map<C09110zQ<?>, AnonymousClass0yl<?>> map = this.A0A;
        C03290cd<?> r0 = map.get(r9);
        if (r0 == null) {
            ThreadLocal<Map<C09110zQ<?>, C03290cd<?>>> threadLocal = this.A09;
            Map<C09110zQ<?>, C03290cd<?>> map2 = threadLocal.get();
            boolean z = false;
            if (map2 == null) {
                map2 = new HashMap<>();
                threadLocal.set(map2);
                z = true;
            }
            r0 = map2.get(r9);
            if (r0 == null) {
                try {
                    C03290cd<?> r5 = new C03290cd<>();
                    map2.put(r9, r5);
                    for (AbstractC08860ym r02 : this.A04) {
                        AnonymousClass0yl<T> A1x = r02.A1x(this, r9);
                        if (A1x != null) {
                            if (r5.A00 == null) {
                                r5.A00 = A1x;
                                map.put(r9, A1x);
                                return A1x;
                            }
                            throw new AssertionError();
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("GSON (2.8.5) cannot handle ");
                    sb.append(r9);
                    throw new IllegalArgumentException(sb.toString());
                } finally {
                    map2.remove(r9);
                    if (z) {
                        threadLocal.remove();
                    }
                }
            }
        }
        return r0;
    }

    public final <T> T A05(String str, Class<T> cls) throws C03080c5 {
        Object A002;
        if (str == null) {
            A002 = null;
        } else {
            C09120zR r1 = new C09120zR(new StringReader(str));
            r1.A07 = false;
            A002 = A00(this, r1, cls);
            A02(A002, r1);
        }
        Map<Class<?>, Class<?>> map = C09020zD.A00;
        if (cls != null) {
            Class<?> cls2 = map.get(cls);
            if (cls2 != null) {
                cls = cls2;
            }
            return cls.cast(A002);
        }
        throw null;
    }

    public final String A06(Object obj) {
        Throwable th;
        StringWriter stringWriter;
        C09130zU r5;
        boolean z;
        boolean z2;
        if (obj == null) {
            AnonymousClass0c8 r2 = AnonymousClass0c8.A00;
            stringWriter = new StringWriter();
            Writer writer = stringWriter;
            try {
                if (!(stringWriter instanceof Writer)) {
                    writer = new C09040zF(stringWriter);
                }
                r5 = new C09130zU(writer);
                r5.A04 = false;
                z = r5.A03;
                r5.A03 = true;
                z2 = r5.A02;
                r5.A02 = this.A06;
                r5.A04 = false;
            } catch (IOException e) {
                throw new AnonymousClass0c9(e);
            }
            try {
                C09080zN.A0H.A03(r5, r2);
                r5.A03 = z;
                r5.A02 = z2;
                r5.A04 = false;
            } catch (IOException e2) {
                throw new AnonymousClass0c9(e2);
            } catch (AssertionError e3) {
                throw new AssertionError(AnonymousClass006.A05("AssertionError (GSON 2.8.5): ", e3.getMessage()), e3);
            } catch (Throwable th2) {
                th = th2;
                r5.A03 = z;
                r5.A02 = z2;
                r5.A04 = false;
                throw th;
            }
        } else {
            Class<?> cls = obj.getClass();
            stringWriter = new StringWriter();
            Writer writer2 = stringWriter;
            if (!(stringWriter instanceof Writer)) {
                writer2 = new C09040zF(stringWriter);
            }
            C09130zU r52 = new C09130zU(writer2);
            r52.A04 = false;
            AnonymousClass0yl A042 = A04(new C09110zQ(cls));
            boolean z3 = r52.A03;
            r52.A03 = true;
            boolean z4 = r52.A02;
            r52.A02 = this.A06;
            boolean z5 = r52.A04;
            r52.A04 = false;
            try {
                A042.A03(r52, obj);
                r52.A03 = z3;
                r52.A02 = z4;
                r52.A04 = z5;
            } catch (IOException e4) {
                throw new AnonymousClass0c9(e4);
            } catch (AssertionError e5) {
                throw new AssertionError(AnonymousClass006.A05("AssertionError (GSON 2.8.5): ", e5.getMessage()), e5);
            } catch (Throwable th3) {
                th = th3;
                r52.A03 = z3;
                r52.A02 = z4;
                r52.A04 = z5;
                throw th;
            }
        }
        return stringWriter.toString();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{serializeNulls:");
        sb.append(false);
        sb.append(",factories:");
        sb.append(this.A04);
        sb.append(",instanceCreators:");
        sb.append(this.A07);
        sb.append("}");
        return sb.toString();
    }

    public static void A01(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            StringBuilder sb = new StringBuilder();
            sb.append(d);
            sb.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public C08780ya() {
        this(Excluder.A02, EnumC03370cm.IDENTITY, Collections.emptyMap(), EnumC08850yk.DEFAULT, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/gson/internal/Excluder;LX/0yZ;Ljava/util/Map<Ljava/lang/reflect/Type;Lcom/google/gson/InstanceCreator<*>;>;ZZZZZZZLX/0yk;Ljava/lang/String;IILjava/util/List<LX/0ym;>;Ljava/util/List<LX/0ym;>;Ljava/util/List<LX/0ym;>;)V */
    public C08780ya(Excluder excluder, AbstractC08770yZ r8, Map map, EnumC08850yk r10, List list, List list2, List list3) {
        AnonymousClass0yl r4;
        this.A09 = new ThreadLocal<>();
        this.A0A = new ConcurrentHashMap();
        this.A01 = excluder;
        this.A00 = r8;
        this.A05 = map;
        this.A07 = new C08920yx(map);
        this.A06 = true;
        this.A02 = list;
        this.A03 = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(C09080zN.A0f);
        arrayList.add(AnonymousClass0Wm.A01);
        arrayList.add(excluder);
        arrayList.addAll(list3);
        arrayList.add(C09080zN.A0l);
        arrayList.add(C09080zN.A0e);
        arrayList.add(C09080zN.A0W);
        arrayList.add(C09080zN.A0X);
        arrayList.add(C09080zN.A0i);
        if (r10 == EnumC08850yk.DEFAULT) {
            r4 = C09080zN.A0J;
        } else {
            r4 = new C03340cj();
        }
        arrayList.add(new TypeAdapters$33(Long.TYPE, Long.class, r4));
        arrayList.add(new TypeAdapters$33(Double.TYPE, Double.class, new C03360cl(this)));
        arrayList.add(new TypeAdapters$33(Float.TYPE, Float.class, new C03350ck(this)));
        arrayList.add(C09080zN.A0h);
        arrayList.add(C09080zN.A0U);
        arrayList.add(C09080zN.A0S);
        arrayList.add(new TypeAdapters$32(AtomicLong.class, new AnonymousClass0c2(new C03320ch(r4))));
        arrayList.add(new TypeAdapters$32(AtomicLongArray.class, new AnonymousClass0c2(new C03300ce(r4))));
        arrayList.add(C09080zN.A0T);
        arrayList.add(C09080zN.A0Z);
        arrayList.add(C09080zN.A0k);
        arrayList.add(C09080zN.A0j);
        arrayList.add(new TypeAdapters$32(BigDecimal.class, C09080zN.A03));
        arrayList.add(new TypeAdapters$32(BigInteger.class, C09080zN.A04));
        arrayList.add(C09080zN.A0o);
        arrayList.add(C09080zN.A0n);
        arrayList.add(C09080zN.A0p);
        arrayList.add(C09080zN.A0b);
        arrayList.add(C09080zN.A0g);
        arrayList.add(C09080zN.A0d);
        arrayList.add(C09080zN.A0V);
        arrayList.add(AnonymousClass0X6.A01);
        arrayList.add(C09080zN.A0Y);
        arrayList.add(AnonymousClass0W5.A01);
        arrayList.add(AnonymousClass0WF.A01);
        arrayList.add(C09080zN.A0m);
        arrayList.add(C01960Yg.A02);
        arrayList.add(C09080zN.A0a);
        C08920yx r2 = this.A07;
        arrayList.add(new CollectionTypeAdapterFactory(r2));
        arrayList.add(new MapTypeAdapterFactory(r2));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(r2);
        this.A08 = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(C09080zN.A0c);
        arrayList.add(new ReflectiveTypeAdapterFactory(r2, r8, excluder, jsonAdapterAnnotationTypeAdapterFactory));
        this.A04 = Collections.unmodifiableList(arrayList);
    }
}
