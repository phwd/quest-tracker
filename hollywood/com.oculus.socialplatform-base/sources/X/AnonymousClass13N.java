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
import java.io.Reader;
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

/* renamed from: X.13N  reason: invalid class name */
public final class AnonymousClass13N {
    public static final AnonymousClass14H<?> A0B = new AnonymousClass14H<>(Object.class);
    public final AnonymousClass13M A00;
    public final Excluder A01;
    public final List<AnonymousClass13Z> A02;
    public final List<AnonymousClass13Z> A03;
    public final List<AnonymousClass13Z> A04;
    public final Map<Type, InstanceCreator<?>> A05;
    public final boolean A06;
    public final AnonymousClass13k A07;
    public final JsonAdapterAnnotationTypeAdapterFactory A08;
    public final ThreadLocal<Map<AnonymousClass14H<?>, AnonymousClass0eX<?>>> A09;
    public final Map<AnonymousClass14H<?>, AnonymousClass13Y<?>> A0A;

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/14I;Ljava/lang/reflect/Type;)TT; */
    public static final Object A00(AnonymousClass13N r4, AnonymousClass14I r5, Type type) throws AnonymousClass0eV, AnonymousClass0eR {
        Object obj;
        boolean z = r5.A07;
        boolean z2 = true;
        r5.A07 = true;
        try {
            r5.A0G();
            z2 = false;
            obj = r4.A03(new AnonymousClass14H(type)).A02(r5);
        } catch (EOFException e) {
            if (z2) {
                obj = null;
            } else {
                throw new AnonymousClass0eR(e);
            }
        } catch (IllegalStateException e2) {
            throw new AnonymousClass0eR(e2);
        } catch (IOException e3) {
            throw new AnonymousClass0eR(e3);
        } catch (AssertionError e4) {
            throw new AssertionError(AnonymousClass006.A07("AssertionError (GSON 2.8.5): ", e4.getMessage()), e4);
        } catch (Throwable th) {
            r5.A07 = z;
            throw th;
        }
        r5.A07 = z;
        return obj;
    }

    public final <T> AnonymousClass13Y<T> A02(AnonymousClass13Z r4, AnonymousClass14H<T> r5) {
        List<AnonymousClass13Z> list = this.A04;
        if (!list.contains(r4)) {
            r4 = this.A08;
        }
        boolean z = false;
        for (AnonymousClass13Z r0 : list) {
            if (z) {
                AnonymousClass13Y<T> A2M = r0.A2M(this, r5);
                if (A2M != null) {
                    return A2M;
                }
            } else if (r0 == r4) {
                z = true;
            }
        }
        StringBuilder sb = new StringBuilder("GSON cannot serialize ");
        sb.append(r5);
        throw new IllegalArgumentException(sb.toString());
    }

    public final <T> AnonymousClass13Y<T> A03(AnonymousClass14H<T> r9) {
        Map<AnonymousClass14H<?>, AnonymousClass13Y<?>> map = this.A0A;
        AnonymousClass0eX<?> r0 = map.get(r9);
        if (r0 == null) {
            ThreadLocal<Map<AnonymousClass14H<?>, AnonymousClass0eX<?>>> threadLocal = this.A09;
            Map<AnonymousClass14H<?>, AnonymousClass0eX<?>> map2 = threadLocal.get();
            boolean z = false;
            if (map2 == null) {
                map2 = new HashMap<>();
                threadLocal.set(map2);
                z = true;
            }
            r0 = map2.get(r9);
            if (r0 == null) {
                try {
                    AnonymousClass0eX<?> r5 = new AnonymousClass0eX<>();
                    map2.put(r9, r5);
                    for (AnonymousClass13Z r02 : this.A04) {
                        AnonymousClass13Y<T> A2M = r02.A2M(this, r9);
                        if (A2M != null) {
                            if (r5.A00 == null) {
                                r5.A00 = A2M;
                                map.put(r9, A2M);
                                return A2M;
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

    public final <T> T A04(Reader reader, Type type) throws AnonymousClass0eV, AnonymousClass0eR {
        AnonymousClass14I r1 = new AnonymousClass14I(reader);
        r1.A07 = false;
        T t = (T) A00(this, r1, type);
        if (t != null) {
            try {
                if (r1.A0G() != AnonymousClass007.A0A) {
                    throw new AnonymousClass0eV("JSON document was not fully consumed.");
                }
            } catch (AnonymousClass14M e) {
                throw new AnonymousClass0eR(e);
            } catch (IOException e2) {
                throw new AnonymousClass0eV(e2);
            }
        }
        return t;
    }

    public final <T> T A05(String str, Class<T> cls) throws AnonymousClass0eR {
        Object A042;
        if (str == null) {
            A042 = null;
        } else {
            A042 = A04(new StringReader(str), cls);
        }
        Map<Class<?>, Class<?>> map = AnonymousClass144.A00;
        if (cls != null) {
            Class<?> cls2 = map.get(cls);
            if (cls2 != null) {
                cls = cls2;
            }
            return cls.cast(A042);
        }
        throw null;
    }

    public final String A06(Object obj) {
        StringWriter stringWriter;
        if (obj == null) {
            AnonymousClass0eU r2 = AnonymousClass0eU.A00;
            stringWriter = new StringWriter();
            Writer writer = stringWriter;
            try {
                if (!(stringWriter instanceof Writer)) {
                    writer = new AnonymousClass146(stringWriter);
                }
                AnonymousClass14L r5 = new AnonymousClass14L(writer);
                r5.A04 = false;
                boolean z = r5.A03;
                r5.A03 = true;
                boolean z2 = r5.A02;
                r5.A02 = this.A06;
                r5.A04 = false;
                try {
                    AnonymousClass14E.A0H.A03(r5, r2);
                    r5.A03 = z;
                    r5.A02 = z2;
                    r5.A04 = false;
                } catch (IOException e) {
                    throw new AnonymousClass0eV(e);
                } catch (AssertionError e2) {
                    throw new AssertionError(AnonymousClass006.A07("AssertionError (GSON 2.8.5): ", e2.getMessage()), e2);
                } catch (Throwable th) {
                    r5.A03 = z;
                    r5.A02 = z2;
                    r5.A04 = false;
                    throw th;
                }
            } catch (IOException e3) {
                throw new AnonymousClass0eV(e3);
            }
        } else {
            Class<?> cls = obj.getClass();
            stringWriter = new StringWriter();
            Writer writer2 = stringWriter;
            try {
                if (!(stringWriter instanceof Writer)) {
                    writer2 = new AnonymousClass146(stringWriter);
                }
                AnonymousClass14L r52 = new AnonymousClass14L(writer2);
                r52.A04 = false;
                AnonymousClass13Y A032 = A03(new AnonymousClass14H(cls));
                boolean z3 = r52.A03;
                r52.A03 = true;
                boolean z4 = r52.A02;
                r52.A02 = this.A06;
                boolean z5 = r52.A04;
                r52.A04 = false;
                try {
                    A032.A03(r52, obj);
                    r52.A03 = z3;
                    r52.A02 = z4;
                    r52.A04 = z5;
                } catch (IOException e4) {
                    throw new AnonymousClass0eV(e4);
                } catch (AssertionError e5) {
                    throw new AssertionError(AnonymousClass006.A07("AssertionError (GSON 2.8.5): ", e5.getMessage()), e5);
                } catch (Throwable th2) {
                    r52.A03 = z3;
                    r52.A02 = z4;
                    r52.A04 = z5;
                    throw th2;
                }
            } catch (IOException e6) {
                throw new AnonymousClass0eV(e6);
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

    public AnonymousClass13N() {
        this(Excluder.A02, EnumC01450ed.IDENTITY, Collections.emptyMap(), AnonymousClass13X.DEFAULT, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/gson/internal/Excluder;LX/13M;Ljava/util/Map<Ljava/lang/reflect/Type;Lcom/google/gson/InstanceCreator<*>;>;ZZZZZZZLX/13X;Ljava/lang/String;IILjava/util/List<LX/13Z;>;Ljava/util/List<LX/13Z;>;Ljava/util/List<LX/13Z;>;)V */
    public AnonymousClass13N(Excluder excluder, AnonymousClass13M r8, Map map, AnonymousClass13X r10, List list, List list2, List list3) {
        AnonymousClass13Y r4;
        this.A09 = new ThreadLocal<>();
        this.A0A = new ConcurrentHashMap();
        this.A01 = excluder;
        this.A00 = r8;
        this.A05 = map;
        this.A07 = new AnonymousClass13k(map);
        this.A06 = true;
        this.A02 = list;
        this.A03 = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(AnonymousClass14E.A0f);
        arrayList.add(C01390dr.A01);
        arrayList.add(excluder);
        arrayList.addAll(list3);
        arrayList.add(AnonymousClass14E.A0l);
        arrayList.add(AnonymousClass14E.A0e);
        arrayList.add(AnonymousClass14E.A0W);
        arrayList.add(AnonymousClass14E.A0X);
        arrayList.add(AnonymousClass14E.A0i);
        if (r10 == AnonymousClass13X.DEFAULT) {
            r4 = AnonymousClass14E.A0J;
        } else {
            r4 = new AnonymousClass0ea();
        }
        arrayList.add(new TypeAdapters$33(Long.TYPE, Long.class, r4));
        arrayList.add(new TypeAdapters$33(Double.TYPE, Double.class, new C01440ec(this)));
        arrayList.add(new TypeAdapters$33(Float.TYPE, Float.class, new C01430eb(this)));
        arrayList.add(AnonymousClass14E.A0h);
        arrayList.add(AnonymousClass14E.A0U);
        arrayList.add(AnonymousClass14E.A0S);
        arrayList.add(new TypeAdapters$32(AtomicLong.class, new AnonymousClass0eO(new AnonymousClass0eZ(r4))));
        arrayList.add(new TypeAdapters$32(AtomicLongArray.class, new AnonymousClass0eO(new AnonymousClass0eY(r4))));
        arrayList.add(AnonymousClass14E.A0T);
        arrayList.add(AnonymousClass14E.A0Z);
        arrayList.add(AnonymousClass14E.A0k);
        arrayList.add(AnonymousClass14E.A0j);
        arrayList.add(new TypeAdapters$32(BigDecimal.class, AnonymousClass14E.A03));
        arrayList.add(new TypeAdapters$32(BigInteger.class, AnonymousClass14E.A04));
        arrayList.add(AnonymousClass14E.A0o);
        arrayList.add(AnonymousClass14E.A0n);
        arrayList.add(AnonymousClass14E.A0p);
        arrayList.add(AnonymousClass14E.A0b);
        arrayList.add(AnonymousClass14E.A0g);
        arrayList.add(AnonymousClass14E.A0d);
        arrayList.add(AnonymousClass14E.A0V);
        arrayList.add(C01420dy.A01);
        arrayList.add(AnonymousClass14E.A0Y);
        arrayList.add(C01350dk.A01);
        arrayList.add(C01360dm.A01);
        arrayList.add(AnonymousClass14E.A0m);
        arrayList.add(AnonymousClass0e2.A02);
        arrayList.add(AnonymousClass14E.A0a);
        AnonymousClass13k r2 = this.A07;
        arrayList.add(new CollectionTypeAdapterFactory(r2));
        arrayList.add(new MapTypeAdapterFactory(r2));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(r2);
        this.A08 = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(AnonymousClass14E.A0c);
        arrayList.add(new ReflectiveTypeAdapterFactory(r2, r8, excluder, jsonAdapterAnnotationTypeAdapterFactory));
        this.A04 = Collections.unmodifiableList(arrayList);
    }
}
