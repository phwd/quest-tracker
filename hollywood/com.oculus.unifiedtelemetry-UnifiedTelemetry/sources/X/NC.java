package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class NC implements Serializable {
    public static final long serialVersionUID = 1;
    public final NT _factory;

    public static final IllegalArgumentException A01(NQ nq, String str) {
        String str2 = nq.A02;
        return new IllegalArgumentException(AnonymousClass06.A08("Failed to parse type '", str2, "' (remaining: '", str2.substring(nq.A00), "'): ", str));
    }

    public NC(NT nt) {
        this._factory = nt;
    }

    public static final AbstractC0224Wl A00(NC nc, NQ nq) throws IllegalArgumentException {
        String str;
        AbstractC0224Wl r1;
        if (nq.hasMoreTokens()) {
            String nextToken = nq.nextToken();
            try {
                Class<?> A01 = Mv.A01(nextToken);
                if (nq.hasMoreTokens()) {
                    String nextToken2 = nq.nextToken();
                    if ("<".equals(nextToken2)) {
                        NT nt = nc._factory;
                        ArrayList arrayList = new ArrayList();
                        while (true) {
                            if (!nq.hasMoreTokens()) {
                                break;
                            }
                            arrayList.add(A00(nc, nq));
                            if (!nq.hasMoreTokens()) {
                                break;
                            }
                            String nextToken3 = nq.nextToken();
                            if (!">".equals(nextToken3)) {
                                if (!",".equals(nextToken3)) {
                                    str = AnonymousClass06.A05("Unexpected token '", nextToken3, "', expected ',' or '>')");
                                    break;
                                }
                            } else if (A01.isArray()) {
                                return AnonymousClass78.A00(nt.A09(A01.getComponentType(), null));
                            } else {
                                if (!A01.isEnum()) {
                                    if (Map.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() <= 0) {
                                            return NT.A01(nt, A01);
                                        }
                                        AbstractC0224Wl wl = (AbstractC0224Wl) arrayList.get(0);
                                        if (arrayList.size() >= 2) {
                                            r1 = (AbstractC0224Wl) arrayList.get(1);
                                        } else {
                                            r1 = new AnonymousClass73(Object.class);
                                        }
                                        return AnonymousClass2V.A00(A01, wl, r1);
                                    } else if (Collection.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() >= 1) {
                                            return AnonymousClass2W.A00(A01, (AbstractC0224Wl) arrayList.get(0));
                                        }
                                        return NT.A00(nt, A01);
                                    } else if (arrayList.size() != 0) {
                                        return NT.A02(A01, (AbstractC0224Wl[]) arrayList.toArray(new AbstractC0224Wl[arrayList.size()]));
                                    }
                                }
                                return new AnonymousClass73(A01);
                            }
                        }
                    } else {
                        nq.A01 = nextToken2;
                        nq.A00 -= nextToken2.length();
                    }
                }
                return nc._factory.A08(A01);
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw e;
                }
                str = AnonymousClass06.A06("Can not locate class '", nextToken, "', problem: ", e.getMessage());
            }
        }
        str = "Unexpected end-of-string";
        throw A01(nq, str);
    }
}
