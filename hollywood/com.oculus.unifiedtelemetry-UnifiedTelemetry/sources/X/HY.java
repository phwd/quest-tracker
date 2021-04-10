package X;

import com.google.gson.InstanceCreator;
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

public final class HY {
    public static final lQ<?> A0B = new lQ<>(Object.class);
    public final HX A00;
    public final Tj A01;
    public final List<AbstractC0132Os> A02;
    public final List<AbstractC0132Os> A03;
    public final List<AbstractC0132Os> A04;
    public final Map<Type, InstanceCreator<?>> A05;
    public final boolean A06;
    public final TW A07;
    public final TP A08;
    public final ThreadLocal<Map<lQ<?>, U6<?>>> A09;
    public final Map<lQ<?>, AbstractC0131Ob<?>> A0A;

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/lk;Ljava/lang/reflect/Type;)TT; */
    public static final Object A00(HY hy, lk lkVar, Type type) throws U4, U0 {
        Object obj;
        boolean z = lkVar.A07;
        boolean z2 = true;
        lkVar.A07 = true;
        try {
            lkVar.A0G();
            z2 = false;
            obj = hy.A04(new lQ(type)).A02(lkVar);
        } catch (EOFException e) {
            if (z2) {
                obj = null;
            } else {
                throw new U0(e);
            }
        } catch (IllegalStateException e2) {
            throw new U0(e2);
        } catch (IOException e3) {
            throw new U0(e3);
        } catch (AssertionError e4) {
            throw new AssertionError(AnonymousClass06.A04("AssertionError (GSON 2.8.5): ", e4.getMessage()), e4);
        } catch (Throwable th) {
            lkVar.A07 = z;
            throw th;
        }
        lkVar.A07 = z;
        return obj;
    }

    public static final void A02(HY hy, Object obj, Type type, mm mmVar) throws U4 {
        AbstractC0131Ob A042 = hy.A04(new lQ(type));
        boolean z = mmVar.A03;
        mmVar.A03 = true;
        boolean z2 = mmVar.A02;
        mmVar.A02 = hy.A06;
        boolean z3 = mmVar.A04;
        mmVar.A04 = false;
        try {
            A042.A03(mmVar, obj);
            mmVar.A03 = z;
            mmVar.A02 = z2;
            mmVar.A04 = z3;
        } catch (IOException e) {
            throw new U4(e);
        } catch (AssertionError e2) {
            throw new AssertionError(AnonymousClass06.A04("AssertionError (GSON 2.8.5): ", e2.getMessage()), e2);
        } catch (Throwable th) {
            mmVar.A03 = z;
            mmVar.A02 = z2;
            mmVar.A04 = z3;
            throw th;
        }
    }

    public final <T> AbstractC0131Ob<T> A03(AbstractC0132Os os, lQ<T> lQVar) {
        List<AbstractC0132Os> list = this.A04;
        if (!list.contains(os)) {
            os = this.A08;
        }
        boolean z = false;
        for (AbstractC0132Os os2 : list) {
            if (z) {
                AbstractC0131Ob<T> A1e = os2.A1e(this, lQVar);
                if (A1e != null) {
                    return A1e;
                }
            } else if (os2 == os) {
                z = true;
            }
        }
        StringBuilder sb = new StringBuilder("GSON cannot serialize ");
        sb.append(lQVar);
        throw new IllegalArgumentException(sb.toString());
    }

    public final <T> AbstractC0131Ob<T> A04(lQ<T> lQVar) {
        Map<lQ<?>, AbstractC0131Ob<?>> map = this.A0A;
        U6<?> u6 = map.get(lQVar);
        if (u6 == null) {
            ThreadLocal<Map<lQ<?>, U6<?>>> threadLocal = this.A09;
            Map<lQ<?>, U6<?>> map2 = threadLocal.get();
            boolean z = false;
            if (map2 == null) {
                map2 = new HashMap<>();
                threadLocal.set(map2);
                z = true;
            }
            u6 = map2.get(lQVar);
            if (u6 == null) {
                try {
                    U6<?> u62 = new U6<>();
                    map2.put(lQVar, u62);
                    for (AbstractC0132Os os : this.A04) {
                        AbstractC0131Ob<T> A1e = os.A1e(this, lQVar);
                        if (A1e != null) {
                            if (u62.A00 == null) {
                                u62.A00 = A1e;
                                map.put(lQVar, A1e);
                                return A1e;
                            }
                            throw new AssertionError();
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("GSON (2.8.5) cannot handle ");
                    sb.append(lQVar);
                    throw new IllegalArgumentException(sb.toString());
                } finally {
                    map2.remove(lQVar);
                    if (z) {
                        threadLocal.remove();
                    }
                }
            }
        }
        return u6;
    }

    public final <T> T A05(Reader reader, Type type) throws U4, U0 {
        lk lkVar = new lk(reader);
        lkVar.A07 = false;
        T t = (T) A00(this, lkVar, type);
        if (t != null) {
            try {
                if (lkVar.A0G() != AnonymousClass07.A09) {
                    throw new U4("JSON document was not fully consumed.");
                }
            } catch (pz e) {
                throw new U0(e);
            } catch (IOException e2) {
                throw new U4(e2);
            }
        }
        return t;
    }

    public final <T> T A06(String str, Class<T> cls) throws U0 {
        Object A052;
        if (str == null) {
            A052 = null;
        } else {
            A052 = A05(new StringReader(str), cls);
        }
        Map<Class<?>, Class<?>> map = VG.A00;
        if (cls != null) {
            Class<?> cls2 = map.get(cls);
            if (cls2 != null) {
                cls = cls2;
            }
            return cls.cast(A052);
        }
        throw null;
    }

    public final String A07(Object obj) {
        StringWriter stringWriter;
        if (obj == null) {
            U3 u3 = U3.A00;
            stringWriter = new StringWriter();
            Writer writer = stringWriter;
            try {
                if (!(stringWriter instanceof Writer)) {
                    writer = new VJ(stringWriter);
                }
                mm mmVar = new mm(writer);
                mmVar.A04 = false;
                boolean z = mmVar.A03;
                mmVar.A03 = true;
                boolean z2 = mmVar.A02;
                mmVar.A02 = this.A06;
                mmVar.A04 = false;
                try {
                    C0433hz.A0H.A03(mmVar, u3);
                    mmVar.A03 = z;
                    mmVar.A02 = z2;
                    mmVar.A04 = false;
                } catch (IOException e) {
                    throw new U4(e);
                } catch (AssertionError e2) {
                    throw new AssertionError(AnonymousClass06.A04("AssertionError (GSON 2.8.5): ", e2.getMessage()), e2);
                } catch (Throwable th) {
                    mmVar.A03 = z;
                    mmVar.A02 = z2;
                    mmVar.A04 = false;
                    throw th;
                }
            } catch (IOException e3) {
                throw new U4(e3);
            }
        } else {
            Class<?> cls = obj.getClass();
            stringWriter = new StringWriter();
            Writer writer2 = stringWriter;
            if (!(stringWriter instanceof Writer)) {
                writer2 = new VJ(stringWriter);
            }
            mm mmVar2 = new mm(writer2);
            mmVar2.A04 = false;
            A02(this, obj, cls, mmVar2);
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

    public HY() {
        this(Tj.A02, UC.IDENTITY, Collections.emptyMap(), OS.DEFAULT, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/Tj;LX/HX;Ljava/util/Map<Ljava/lang/reflect/Type;Lcom/google/gson/InstanceCreator<*>;>;ZZZZZZZLX/OS;Ljava/lang/String;IILjava/util/List<LX/Os;>;Ljava/util/List<LX/Os;>;Ljava/util/List<LX/Os;>;)V */
    public HY(Tj tj, HX hx, Map map, OS os, List list, List list2, List list3) {
        AbstractC0131Ob u9;
        this.A09 = new ThreadLocal<>();
        this.A0A = new ConcurrentHashMap();
        this.A01 = tj;
        this.A00 = hx;
        this.A05 = map;
        this.A07 = new TW(map);
        this.A06 = true;
        this.A02 = list;
        this.A03 = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(C0433hz.A0f);
        arrayList.add(T9.A01);
        arrayList.add(tj);
        arrayList.addAll(list3);
        arrayList.add(C0433hz.A0l);
        arrayList.add(C0433hz.A0e);
        arrayList.add(C0433hz.A0W);
        arrayList.add(C0433hz.A0X);
        arrayList.add(C0433hz.A0i);
        if (os == OS.DEFAULT) {
            u9 = C0433hz.A0J;
        } else {
            u9 = new U9();
        }
        arrayList.add(new SW(Long.TYPE, Long.class, u9));
        arrayList.add(new SW(Double.TYPE, Double.class, new UB(this)));
        arrayList.add(new SW(Float.TYPE, Float.class, new UA(this)));
        arrayList.add(C0433hz.A0h);
        arrayList.add(C0433hz.A0U);
        arrayList.add(C0433hz.A0S);
        arrayList.add(new SX(AtomicLong.class, new Tx(new U8(u9))));
        arrayList.add(new SX(AtomicLongArray.class, new Tx(new U7(u9))));
        arrayList.add(C0433hz.A0T);
        arrayList.add(C0433hz.A0Z);
        arrayList.add(C0433hz.A0k);
        arrayList.add(C0433hz.A0j);
        arrayList.add(new SX(BigDecimal.class, C0433hz.A03));
        arrayList.add(new SX(BigInteger.class, C0433hz.A04));
        arrayList.add(C0433hz.A0o);
        arrayList.add(C0433hz.A0n);
        arrayList.add(C0433hz.A0p);
        arrayList.add(C0433hz.A0b);
        arrayList.add(C0433hz.A0g);
        arrayList.add(C0433hz.A0d);
        arrayList.add(C0433hz.A0V);
        arrayList.add(TR.A01);
        arrayList.add(C0433hz.A0Y);
        arrayList.add(T1.A01);
        arrayList.add(T3.A01);
        arrayList.add(C0433hz.A0m);
        arrayList.add(C0165Tb.A02);
        arrayList.add(C0433hz.A0a);
        TW tw = this.A07;
        arrayList.add(new TV(tw));
        arrayList.add(new TB(tw));
        TP tp = new TP(tw);
        this.A08 = tp;
        arrayList.add(tp);
        arrayList.add(C0433hz.A0c);
        arrayList.add(new T5(tw, hx, tj, tp));
        this.A04 = Collections.unmodifiableList(arrayList);
    }
}
