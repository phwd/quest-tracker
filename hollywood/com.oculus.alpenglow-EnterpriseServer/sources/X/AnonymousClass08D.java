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

/* renamed from: X.08D  reason: invalid class name */
public final class AnonymousClass08D {
    public static final AnonymousClass0Fe<?> A0C = new AnonymousClass0Fe<>(Object.class);
    public final AnonymousClass087 A00;
    public final Excluder A01;
    public final List<AnonymousClass0C3> A02;
    public final List<AnonymousClass0C3> A03;
    public final List<AnonymousClass0C3> A04;
    public final Map<Type, InstanceCreator<?>> A05;
    public final boolean A06;
    public final boolean A07;
    public final AnonymousClass0Cp A08;
    public final JsonAdapterAnnotationTypeAdapterFactory A09;
    public final ThreadLocal<Map<AnonymousClass0Fe<?>, AnonymousClass0XW<?>>> A0A;
    public final Map<AnonymousClass0Fe<?>, AnonymousClass0Bd<?>> A0B;

    private final AnonymousClass0GL A00(Writer writer) throws IOException {
        String str;
        AnonymousClass0GL r2 = new AnonymousClass0GL(writer);
        if (this.A06) {
            if ("  ".length() == 0) {
                r2.A01 = null;
                str = ":";
            } else {
                r2.A01 = "  ";
                str = ": ";
            }
            r2.A02 = str;
        }
        r2.A05 = false;
        return r2;
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/0Fo;Ljava/lang/reflect/Type;)TT; */
    public static final Object A01(AnonymousClass08D r4, AnonymousClass0Fo r5, Type type) throws AnonymousClass0XU, AnonymousClass0XQ {
        Object obj;
        boolean z = r5.A08;
        boolean z2 = true;
        r5.A08 = true;
        try {
            r5.A0D();
            z2 = false;
            obj = r4.A07(new AnonymousClass0Fe(type)).A02(r5);
        } catch (EOFException e) {
            if (z2) {
                obj = null;
            } else {
                throw new AnonymousClass0XQ(e);
            }
        } catch (IllegalStateException e2) {
            throw new AnonymousClass0XQ(e2);
        } catch (IOException e3) {
            throw new AnonymousClass0XQ(e3);
        } catch (AssertionError e4) {
            throw new AssertionError(AnonymousClass006.A05("AssertionError (GSON 2.8.5): ", e4.getMessage()), e4);
        } catch (Throwable th) {
            r5.A08 = z;
            throw th;
        }
        r5.A08 = z;
        return obj;
    }

    public static final void A03(AnonymousClass08D r7, AnonymousClass0AU r8, Appendable appendable) throws AnonymousClass0XU {
        Writer r9;
        try {
            if (appendable instanceof Writer) {
                r9 = (Writer) appendable;
            } else {
                r9 = new AnonymousClass0E9(appendable);
            }
            AnonymousClass0GL A002 = r7.A00(r9);
            boolean z = A002.A04;
            A002.A04 = true;
            boolean z2 = A002.A03;
            A002.A03 = r7.A07;
            boolean z3 = A002.A05;
            A002.A05 = false;
            try {
                C01220Fb.A0H.A03(A002, r8);
                A002.A04 = z;
                A002.A03 = z2;
                A002.A05 = z3;
            } catch (IOException e) {
                throw new AnonymousClass0XU(e);
            } catch (AssertionError e2) {
                throw new AssertionError(AnonymousClass006.A05("AssertionError (GSON 2.8.5): ", e2.getMessage()), e2);
            } catch (Throwable th) {
                A002.A04 = z;
                A002.A03 = z2;
                A002.A05 = z3;
                throw th;
            }
        } catch (IOException e3) {
            throw new AnonymousClass0XU(e3);
        }
    }

    public static final void A04(AnonymousClass08D r7, Object obj, Type type, Appendable appendable) throws AnonymousClass0XU {
        Writer r10;
        try {
            if (appendable instanceof Writer) {
                r10 = (Writer) appendable;
            } else {
                r10 = new AnonymousClass0E9(appendable);
            }
            AnonymousClass0GL A002 = r7.A00(r10);
            AnonymousClass0Bd A072 = r7.A07(new AnonymousClass0Fe(type));
            boolean z = A002.A04;
            A002.A04 = true;
            boolean z2 = A002.A03;
            A002.A03 = r7.A07;
            boolean z3 = A002.A05;
            A002.A05 = false;
            try {
                A072.A03(A002, obj);
                A002.A04 = z;
                A002.A03 = z2;
                A002.A05 = z3;
            } catch (IOException e) {
                throw new AnonymousClass0XU(e);
            } catch (AssertionError e2) {
                throw new AssertionError(AnonymousClass006.A05("AssertionError (GSON 2.8.5): ", e2.getMessage()), e2);
            } catch (Throwable th) {
                A002.A04 = z;
                A002.A03 = z2;
                A002.A05 = z3;
                throw th;
            }
        } catch (IOException e3) {
            throw new AnonymousClass0XU(e3);
        }
    }

    public static void A05(Object obj, AnonymousClass0Fo r1) {
        if (obj != null) {
            try {
                if (r1.A0D() != AnonymousClass007.A0J) {
                    throw new AnonymousClass0XU("JSON document was not fully consumed.");
                }
            } catch (AnonymousClass0GS e) {
                throw new AnonymousClass0XQ(e);
            } catch (IOException e2) {
                throw new AnonymousClass0XU(e2);
            }
        }
    }

    public final <T> AnonymousClass0Bd<T> A06(AnonymousClass0C3 r4, AnonymousClass0Fe<T> r5) {
        List<AnonymousClass0C3> list = this.A04;
        if (!list.contains(r4)) {
            r4 = this.A09;
        }
        boolean z = false;
        for (AnonymousClass0C3 r0 : list) {
            if (z) {
                AnonymousClass0Bd<T> A1v = r0.A1v(this, r5);
                if (A1v != null) {
                    return A1v;
                }
            } else if (r0 == r4) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + r5);
    }

    public final <T> AnonymousClass0Bd<T> A07(AnonymousClass0Fe<T> r9) {
        Map<AnonymousClass0Fe<?>, AnonymousClass0Bd<?>> map = this.A0B;
        AnonymousClass0XW<?> r0 = map.get(r9);
        if (r0 == null) {
            ThreadLocal<Map<AnonymousClass0Fe<?>, AnonymousClass0XW<?>>> threadLocal = this.A0A;
            Map<AnonymousClass0Fe<?>, AnonymousClass0XW<?>> map2 = threadLocal.get();
            boolean z = false;
            if (map2 == null) {
                map2 = new HashMap<>();
                threadLocal.set(map2);
                z = true;
            }
            r0 = map2.get(r9);
            if (r0 == null) {
                try {
                    AnonymousClass0XW<?> r5 = new AnonymousClass0XW<>();
                    map2.put(r9, r5);
                    for (AnonymousClass0C3 r02 : this.A04) {
                        AnonymousClass0Bd<T> A1v = r02.A1v(this, r9);
                        if (A1v != null) {
                            if (r5.A00 == null) {
                                r5.A00 = A1v;
                                map.put(r9, A1v);
                                return A1v;
                            }
                            throw new AssertionError();
                        }
                    }
                    throw new IllegalArgumentException("GSON (2.8.5) cannot handle " + r9);
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

    public final String A08(Object obj) {
        StringWriter stringWriter;
        if (obj == null) {
            AnonymousClass0XT r0 = AnonymousClass0XT.A00;
            stringWriter = new StringWriter();
            A03(this, r0, stringWriter);
        } else {
            Class<?> cls = obj.getClass();
            stringWriter = new StringWriter();
            A04(this, obj, cls, stringWriter);
        }
        return stringWriter.toString();
    }

    public final String toString() {
        return "{serializeNulls:" + false + ",factories:" + this.A04 + ",instanceCreators:" + this.A08 + "}";
    }

    public static void A02(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public AnonymousClass08D() {
        this(Excluder.A02, EnumC02190Xc.IDENTITY, Collections.emptyMap(), false, AnonymousClass0BU.DEFAULT, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/gson/internal/Excluder;LX/087;Ljava/util/Map<Ljava/lang/reflect/Type;Lcom/google/gson/InstanceCreator<*>;>;ZZZZZZZLX/0BU;Ljava/lang/String;IILjava/util/List<LX/0C3;>;Ljava/util/List<LX/0C3;>;Ljava/util/List<LX/0C3;>;)V */
    public AnonymousClass08D(Excluder excluder, AnonymousClass087 r8, Map map, boolean z, AnonymousClass0BU r11, List list, List list2, List list3) {
        AnonymousClass0Bd r4;
        this.A0A = new ThreadLocal<>();
        this.A0B = new ConcurrentHashMap();
        this.A01 = excluder;
        this.A00 = r8;
        this.A05 = map;
        this.A08 = new AnonymousClass0Cp(map);
        this.A07 = true;
        this.A06 = z;
        this.A02 = list;
        this.A03 = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(C01220Fb.A0f);
        arrayList.add(C02140Wq.A01);
        arrayList.add(excluder);
        arrayList.addAll(list3);
        arrayList.add(C01220Fb.A0l);
        arrayList.add(C01220Fb.A0e);
        arrayList.add(C01220Fb.A0W);
        arrayList.add(C01220Fb.A0X);
        arrayList.add(C01220Fb.A0i);
        if (r11 == AnonymousClass0BU.DEFAULT) {
            r4 = C01220Fb.A0J;
        } else {
            r4 = new AnonymousClass0XZ();
        }
        arrayList.add(new TypeAdapters$33(Long.TYPE, Long.class, r4));
        arrayList.add(new TypeAdapters$33(Double.TYPE, Double.class, new C02180Xb(this)));
        arrayList.add(new TypeAdapters$33(Float.TYPE, Float.class, new C02170Xa(this)));
        arrayList.add(C01220Fb.A0h);
        arrayList.add(C01220Fb.A0U);
        arrayList.add(C01220Fb.A0S);
        arrayList.add(new TypeAdapters$32(AtomicLong.class, new AnonymousClass0XN(new AnonymousClass0XY(r4))));
        arrayList.add(new TypeAdapters$32(AtomicLongArray.class, new AnonymousClass0XN(new AnonymousClass0XX(r4))));
        arrayList.add(C01220Fb.A0T);
        arrayList.add(C01220Fb.A0Z);
        arrayList.add(C01220Fb.A0k);
        arrayList.add(C01220Fb.A0j);
        arrayList.add(new TypeAdapters$32(BigDecimal.class, C01220Fb.A03));
        arrayList.add(new TypeAdapters$32(BigInteger.class, C01220Fb.A04));
        arrayList.add(C01220Fb.A0o);
        arrayList.add(C01220Fb.A0n);
        arrayList.add(C01220Fb.A0p);
        arrayList.add(C01220Fb.A0b);
        arrayList.add(C01220Fb.A0g);
        arrayList.add(C01220Fb.A0d);
        arrayList.add(C01220Fb.A0V);
        arrayList.add(C02160Wx.A01);
        arrayList.add(C01220Fb.A0Y);
        arrayList.add(C02100Wj.A01);
        arrayList.add(C02110Wl.A01);
        arrayList.add(C01220Fb.A0m);
        arrayList.add(AnonymousClass0X1.A02);
        arrayList.add(C01220Fb.A0a);
        arrayList.add(new CollectionTypeAdapterFactory(this.A08));
        arrayList.add(new MapTypeAdapterFactory(this.A08));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.A08);
        this.A09 = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(C01220Fb.A0c);
        arrayList.add(new ReflectiveTypeAdapterFactory(this.A08, r8, excluder, this.A09));
        this.A04 = Collections.unmodifiableList(arrayList);
    }
}
