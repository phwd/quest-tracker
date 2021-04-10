package X;

import com.google.gson.InstanceCreator;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
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

/* renamed from: X.hr  reason: case insensitive filesystem */
public final class C0246hr {
    public static final h6<?> A0B = new h6<>(Object.class);
    public final AbstractC0247hs A00;
    public final C0086Lh A01;
    public final List<AbstractC0237hg> A02;
    public final List<AbstractC0237hg> A03;
    public final List<AbstractC0237hg> A04;
    public final Map<Type, InstanceCreator<?>> A05;
    public final boolean A06;
    public final C0232hV A07;
    public final LU A08;
    public final ThreadLocal<Map<h6<?>, M6<?>>> A09;
    public final Map<h6<?>, hh<?>> A0A;

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/h5;Ljava/lang/reflect/Type;)TT; */
    public static final Object A00(C0246hr hrVar, h5 h5Var, Type type) throws M4, C0102Ly {
        Object obj;
        boolean z = h5Var.A07;
        boolean z2 = true;
        h5Var.A07 = true;
        try {
            h5Var.A0G();
            z2 = false;
            obj = hrVar.A03(new h6(type)).A02(h5Var);
        } catch (EOFException e) {
            if (z2) {
                obj = null;
            } else {
                throw new C0102Ly(e);
            }
        } catch (IllegalStateException e2) {
            throw new C0102Ly(e2);
        } catch (IOException e3) {
            throw new C0102Ly(e3);
        } catch (AssertionError e4) {
            throw new AssertionError(AnonymousClass06.A03("AssertionError (GSON 2.8.5): ", e4.getMessage()), e4);
        } catch (Throwable th) {
            h5Var.A07 = z;
            throw th;
        }
        h5Var.A07 = z;
        return obj;
    }

    public final <T> hh<T> A02(AbstractC0237hg hgVar, h6<T> h6Var) {
        List<AbstractC0237hg> list = this.A04;
        if (!list.contains(hgVar)) {
            hgVar = this.A08;
        }
        boolean z = false;
        for (AbstractC0237hg hgVar2 : list) {
            if (z) {
                hh<T> A1F = hgVar2.A1F(this, h6Var);
                if (A1F != null) {
                    return A1F;
                }
            } else if (hgVar2 == hgVar) {
                z = true;
            }
        }
        StringBuilder sb = new StringBuilder("GSON cannot serialize ");
        sb.append(h6Var);
        throw new IllegalArgumentException(sb.toString());
    }

    public final <T> hh<T> A03(h6<T> h6Var) {
        Map<h6<?>, hh<?>> map = this.A0A;
        M6<?> m6 = map.get(h6Var);
        if (m6 == null) {
            ThreadLocal<Map<h6<?>, M6<?>>> threadLocal = this.A09;
            Map<h6<?>, M6<?>> map2 = threadLocal.get();
            boolean z = false;
            if (map2 == null) {
                map2 = new HashMap<>();
                threadLocal.set(map2);
                z = true;
            }
            m6 = map2.get(h6Var);
            if (m6 == null) {
                try {
                    M6<?> m62 = new M6<>();
                    map2.put(h6Var, m62);
                    for (AbstractC0237hg hgVar : this.A04) {
                        hh<T> A1F = hgVar.A1F(this, h6Var);
                        if (A1F != null) {
                            if (m62.A00 == null) {
                                m62.A00 = A1F;
                                map.put(h6Var, A1F);
                                return A1F;
                            }
                            throw new AssertionError();
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("GSON (2.8.5) cannot handle ");
                    sb.append(h6Var);
                    throw new IllegalArgumentException(sb.toString());
                } finally {
                    map2.remove(h6Var);
                    if (z) {
                        threadLocal.remove();
                    }
                }
            }
        }
        return m6;
    }

    public final <T> T A04(Reader reader, Type type) throws M4, C0102Ly {
        h5 h5Var = new h5(reader);
        h5Var.A07 = false;
        T t = (T) A00(this, h5Var, type);
        if (t != null) {
            try {
                if (h5Var.A0G() != AnonymousClass07.A09) {
                    throw new M4("JSON document was not fully consumed.");
                }
            } catch (h2 e) {
                throw new C0102Ly(e);
            } catch (IOException e2) {
                throw new M4(e2);
            }
        }
        return t;
    }

    public final String A05(Object obj) {
        Throwable th;
        StringWriter stringWriter;
        h3 h3Var;
        boolean z;
        boolean z2;
        if (obj == null) {
            M3 m3 = M3.A00;
            stringWriter = new StringWriter();
            Writer writer = stringWriter;
            try {
                if (!(stringWriter instanceof Writer)) {
                    writer = new hI(stringWriter);
                }
                h3Var = new h3(writer);
                h3Var.A04 = false;
                z = h3Var.A03;
                h3Var.A03 = true;
                z2 = h3Var.A02;
                h3Var.A02 = this.A06;
                h3Var.A04 = false;
            } catch (IOException e) {
                throw new M4(e);
            }
            try {
                hA.A0H.A03(h3Var, m3);
                h3Var.A03 = z;
                h3Var.A02 = z2;
                h3Var.A04 = false;
            } catch (IOException e2) {
                throw new M4(e2);
            } catch (AssertionError e3) {
                throw new AssertionError(AnonymousClass06.A03("AssertionError (GSON 2.8.5): ", e3.getMessage()), e3);
            } catch (Throwable th2) {
                th = th2;
                h3Var.A03 = z;
                h3Var.A02 = z2;
                h3Var.A04 = false;
                throw th;
            }
        } else {
            Class<?> cls = obj.getClass();
            stringWriter = new StringWriter();
            Writer writer2 = stringWriter;
            if (!(stringWriter instanceof Writer)) {
                writer2 = new hI(stringWriter);
            }
            h3 h3Var2 = new h3(writer2);
            h3Var2.A04 = false;
            hh A032 = A03(new h6(cls));
            boolean z3 = h3Var2.A03;
            h3Var2.A03 = true;
            boolean z4 = h3Var2.A02;
            h3Var2.A02 = this.A06;
            boolean z5 = h3Var2.A04;
            h3Var2.A04 = false;
            try {
                A032.A03(h3Var2, obj);
                h3Var2.A03 = z3;
                h3Var2.A02 = z4;
                h3Var2.A04 = z5;
            } catch (IOException e4) {
                throw new M4(e4);
            } catch (AssertionError e5) {
                throw new AssertionError(AnonymousClass06.A03("AssertionError (GSON 2.8.5): ", e5.getMessage()), e5);
            } catch (Throwable th3) {
                th = th3;
                h3Var2.A03 = z3;
                h3Var2.A02 = z4;
                h3Var2.A04 = z5;
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

    public C0246hr() {
        this(C0086Lh.A02, MC.IDENTITY, Collections.emptyMap(), EnumC0238hi.DEFAULT, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/Lh;LX/hs;Ljava/util/Map<Ljava/lang/reflect/Type;Lcom/google/gson/InstanceCreator<*>;>;ZZZZZZZLX/hi;Ljava/lang/String;IILjava/util/List<LX/hg;>;Ljava/util/List<LX/hg;>;Ljava/util/List<LX/hg;>;)V */
    public C0246hr(C0086Lh lh, AbstractC0247hs hsVar, Map map, EnumC0238hi hiVar, List list, List list2, List list3) {
        hh m9;
        this.A09 = new ThreadLocal<>();
        this.A0A = new ConcurrentHashMap();
        this.A01 = lh;
        this.A00 = hsVar;
        this.A05 = map;
        this.A07 = new C0232hV(map);
        this.A06 = true;
        this.A02 = list;
        this.A03 = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(hA.A0f);
        arrayList.add(LO.A01);
        arrayList.add(lh);
        arrayList.addAll(list3);
        arrayList.add(hA.A0l);
        arrayList.add(hA.A0e);
        arrayList.add(hA.A0W);
        arrayList.add(hA.A0X);
        arrayList.add(hA.A0i);
        if (hiVar == EnumC0238hi.DEFAULT) {
            m9 = hA.A0J;
        } else {
            m9 = new M9();
        }
        arrayList.add(new C0063Kc(Long.TYPE, Long.class, m9));
        arrayList.add(new C0063Kc(Double.TYPE, Double.class, new MB(this)));
        arrayList.add(new C0063Kc(Float.TYPE, Float.class, new MA(this)));
        arrayList.add(hA.A0h);
        arrayList.add(hA.A0U);
        arrayList.add(hA.A0S);
        arrayList.add(new C0064Kd(AtomicLong.class, new C0099Lv(new M8(m9))));
        arrayList.add(new C0064Kd(AtomicLongArray.class, new C0099Lv(new M7(m9))));
        arrayList.add(hA.A0T);
        arrayList.add(hA.A0Z);
        arrayList.add(hA.A0k);
        arrayList.add(hA.A0j);
        arrayList.add(new C0064Kd(BigDecimal.class, hA.A03));
        arrayList.add(new C0064Kd(BigInteger.class, hA.A04));
        arrayList.add(hA.A0o);
        arrayList.add(hA.A0n);
        arrayList.add(hA.A0p);
        arrayList.add(hA.A0b);
        arrayList.add(hA.A0g);
        arrayList.add(hA.A0d);
        arrayList.add(hA.A0V);
        arrayList.add(LV.A01);
        arrayList.add(hA.A0Y);
        arrayList.add(LH.A01);
        arrayList.add(LJ.A01);
        arrayList.add(hA.A0m);
        arrayList.add(LZ.A02);
        arrayList.add(hA.A0a);
        C0232hV hVVar = this.A07;
        arrayList.add(new LX(hVVar));
        arrayList.add(new LQ(hVVar));
        LU lu = new LU(hVVar);
        this.A08 = lu;
        arrayList.add(lu);
        arrayList.add(hA.A0c);
        arrayList.add(new LL(hVVar, hsVar, lh, lu));
        this.A04 = Collections.unmodifiableList(arrayList);
    }
}
