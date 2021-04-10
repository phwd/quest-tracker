package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: X.0og  reason: invalid class name and case insensitive filesystem */
public final class C07070og implements Serializable {
    public static final long serialVersionUID = 1;
    public final C07040od _factory;

    public static final IllegalArgumentException A01(C07060of r5, String str) {
        String str2 = r5.A02;
        return new IllegalArgumentException(AnonymousClass006.A0A("Failed to parse type '", str2, "' (remaining: '", str2.substring(r5.A00), "'): ", str));
    }

    public C07070og(C07040od r1) {
        this._factory = r1;
    }

    public static final AnonymousClass0aI A00(C07070og r6, C07060of r7) throws IllegalArgumentException {
        String str;
        AnonymousClass0aI r1;
        if (r7.hasMoreTokens()) {
            String nextToken = r7.nextToken();
            try {
                Class<?> A01 = C07130om.A01(nextToken);
                if (r7.hasMoreTokens()) {
                    String nextToken2 = r7.nextToken();
                    if ("<".equals(nextToken2)) {
                        C07040od r3 = r6._factory;
                        ArrayList arrayList = new ArrayList();
                        while (true) {
                            if (!r7.hasMoreTokens()) {
                                break;
                            }
                            arrayList.add(A00(r6, r7));
                            if (!r7.hasMoreTokens()) {
                                break;
                            }
                            String nextToken3 = r7.nextToken();
                            if (!">".equals(nextToken3)) {
                                if (!",".equals(nextToken3)) {
                                    str = AnonymousClass006.A07("Unexpected token '", nextToken3, "', expected ',' or '>')");
                                    break;
                                }
                            } else if (A01.isArray()) {
                                return AnonymousClass0CC.A00(r3.A09(A01.getComponentType(), null));
                            } else {
                                if (!A01.isEnum()) {
                                    if (Map.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() <= 0) {
                                            return C07040od.A01(r3, A01);
                                        }
                                        AnonymousClass0aI r32 = (AnonymousClass0aI) arrayList.get(0);
                                        if (arrayList.size() >= 2) {
                                            r1 = (AnonymousClass0aI) arrayList.get(1);
                                        } else {
                                            r1 = new AnonymousClass0C9(Object.class);
                                        }
                                        return C006506c.A00(A01, r32, r1);
                                    } else if (Collection.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() >= 1) {
                                            return C006606d.A00(A01, (AnonymousClass0aI) arrayList.get(0));
                                        }
                                        return C07040od.A00(r3, A01);
                                    } else if (arrayList.size() != 0) {
                                        return C07040od.A02(A01, (AnonymousClass0aI[]) arrayList.toArray(new AnonymousClass0aI[arrayList.size()]));
                                    }
                                }
                                return new AnonymousClass0C9(A01);
                            }
                        }
                    } else {
                        r7.A01 = nextToken2;
                        r7.A00 -= nextToken2.length();
                    }
                }
                return r6._factory.A08(A01);
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw e;
                }
                str = AnonymousClass006.A08("Can not locate class '", nextToken, "', problem: ", e.getMessage());
            }
        }
        str = "Unexpected end-of-string";
        throw A01(r7, str);
    }
}
