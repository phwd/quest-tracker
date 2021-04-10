package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: X.Pz  reason: case insensitive filesystem */
public final class C0303Pz implements Serializable {
    public static final long serialVersionUID = 1;
    public final C0300Pw _factory;

    public static final IllegalArgumentException A01(C0302Py py, String str) {
        String str2 = py.A02;
        return new IllegalArgumentException(AnonymousClass08.A08("Failed to parse type '", str2, "' (remaining: '", str2.substring(py.A00), "'): ", str));
    }

    public C0303Pz(C0300Pw pw) {
        this._factory = pw;
    }

    public static final AbstractC1024qt A00(C0303Pz pz, C0302Py py) {
        AbstractC1024qt fFVar;
        if (py.hasMoreTokens()) {
            String nextToken = py.nextToken();
            try {
                Class A01 = Q5.A01(nextToken);
                if (py.hasMoreTokens()) {
                    String nextToken2 = py.nextToken();
                    if ("<".equals(nextToken2)) {
                        C0300Pw pw = pz._factory;
                        ArrayList arrayList = new ArrayList();
                        while (py.hasMoreTokens()) {
                            arrayList.add(A00(pz, py));
                            if (!py.hasMoreTokens()) {
                                break;
                            }
                            String nextToken3 = py.nextToken();
                            if (">".equals(nextToken3)) {
                                if (A01.isArray()) {
                                    return C0683fI.A00(pw.A09(A01.getComponentType(), null));
                                }
                                if (!A01.isEnum()) {
                                    if (Map.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() <= 0) {
                                            return C0300Pw.A01(pw, A01);
                                        }
                                        AbstractC1024qt qtVar = (AbstractC1024qt) arrayList.get(0);
                                        if (arrayList.size() >= 2) {
                                            fFVar = (AbstractC1024qt) arrayList.get(1);
                                        } else {
                                            fFVar = new fF(Object.class);
                                        }
                                        return C00313p.A00(A01, qtVar, fFVar);
                                    } else if (Collection.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() >= 1) {
                                            return C00323q.A00(A01, (AbstractC1024qt) arrayList.get(0));
                                        }
                                        return C0300Pw.A00(pw, A01);
                                    } else if (arrayList.size() != 0) {
                                        return C0300Pw.A02(A01, (AbstractC1024qt[]) arrayList.toArray(new AbstractC1024qt[arrayList.size()]));
                                    }
                                }
                                return new fF(A01);
                            } else if (!",".equals(nextToken3)) {
                                throw A01(py, AnonymousClass08.A05("Unexpected token '", nextToken3, "', expected ',' or '>')"));
                            }
                        }
                        throw A01(py, "Unexpected end-of-string");
                    }
                    py.A01 = nextToken2;
                    py.A00 -= nextToken2.length();
                }
                return pz._factory.A08(A01);
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw e;
                }
                throw A01(py, AnonymousClass08.A06("Can not locate class '", nextToken, "', problem: ", e.getMessage()));
            }
        } else {
            throw A01(py, "Unexpected end-of-string");
        }
    }
}
