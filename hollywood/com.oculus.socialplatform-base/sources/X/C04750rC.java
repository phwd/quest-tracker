package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: X.0rC  reason: invalid class name and case insensitive filesystem */
public final class C04750rC implements Serializable {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0r9 _factory;

    public static final IllegalArgumentException A01(C04740rB r5, String str) {
        String str2 = r5.A02;
        return new IllegalArgumentException(AnonymousClass006.A0D("Failed to parse type '", str2, "' (remaining: '", str2.substring(r5.A00), "'): ", str));
    }

    public C04750rC(AnonymousClass0r9 r1) {
        this._factory = r1;
    }

    public static final AbstractC02190iF A00(C04750rC r6, C04740rB r7) throws IllegalArgumentException {
        AbstractC02190iF r1;
        if (r7.hasMoreTokens()) {
            String nextToken = r7.nextToken();
            try {
                Class<?> A01 = C04810rI.A01(nextToken);
                if (r7.hasMoreTokens()) {
                    String nextToken2 = r7.nextToken();
                    if ("<".equals(nextToken2)) {
                        AnonymousClass0r9 r3 = r6._factory;
                        ArrayList arrayList = new ArrayList();
                        while (r7.hasMoreTokens()) {
                            arrayList.add(A00(r6, r7));
                            if (!r7.hasMoreTokens()) {
                                break;
                            }
                            String nextToken3 = r7.nextToken();
                            if (">".equals(nextToken3)) {
                                if (A01.isArray()) {
                                    return AnonymousClass0CA.A00(r3.A09(A01.getComponentType(), null));
                                }
                                if (!A01.isEnum()) {
                                    if (Map.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() <= 0) {
                                            return AnonymousClass0r9.A01(r3, A01);
                                        }
                                        AbstractC02190iF r32 = (AbstractC02190iF) arrayList.get(0);
                                        if (arrayList.size() >= 2) {
                                            r1 = (AbstractC02190iF) arrayList.get(1);
                                        } else {
                                            r1 = new AnonymousClass0C7(Object.class);
                                        }
                                        return AnonymousClass03D.A00(A01, r32, r1);
                                    } else if (Collection.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() >= 1) {
                                            return AnonymousClass03E.A00(A01, (AbstractC02190iF) arrayList.get(0));
                                        }
                                        return AnonymousClass0r9.A00(r3, A01);
                                    } else if (arrayList.size() != 0) {
                                        return AnonymousClass0r9.A02(A01, (AbstractC02190iF[]) arrayList.toArray(new AbstractC02190iF[arrayList.size()]));
                                    }
                                }
                                return new AnonymousClass0C7(A01);
                            } else if (!",".equals(nextToken3)) {
                                throw A01(r7, AnonymousClass006.A09("Unexpected token '", nextToken3, "', expected ',' or '>')"));
                            }
                        }
                        throw A01(r7, "Unexpected end-of-string");
                    }
                    r7.A01 = nextToken2;
                    r7.A00 -= nextToken2.length();
                }
                return r6._factory.A08(A01);
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw e;
                }
                throw A01(r7, AnonymousClass006.A0B("Can not locate class '", nextToken, "', problem: ", e.getMessage()));
            }
        } else {
            throw A01(r7, "Unexpected end-of-string");
        }
    }
}
