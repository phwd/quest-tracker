package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: X.0mo  reason: invalid class name and case insensitive filesystem */
public final class C06270mo implements Serializable {
    public static final long serialVersionUID = 1;
    public final C06240ml _factory;

    public static final IllegalArgumentException A01(C06260mn r5, String str) {
        String str2 = r5.A02;
        return new IllegalArgumentException(AnonymousClass006.A0A("Failed to parse type '", str2, "' (remaining: '", str2.substring(r5.A00), "'): ", str));
    }

    public C06270mo(C06240ml r1) {
        this._factory = r1;
    }

    public static final AbstractC04000gb A00(C06270mo r6, C06260mn r7) throws IllegalArgumentException {
        String str;
        AbstractC04000gb r1;
        if (r7.hasMoreTokens()) {
            String nextToken = r7.nextToken();
            try {
                Class<?> A01 = C06330mu.A01(nextToken);
                if (r7.hasMoreTokens()) {
                    String nextToken2 = r7.nextToken();
                    if ("<".equals(nextToken2)) {
                        C06240ml r3 = r6._factory;
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
                                return AnonymousClass076.A00(r3.A09(A01.getComponentType(), null));
                            } else {
                                if (!A01.isEnum()) {
                                    if (Map.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() <= 0) {
                                            return C06240ml.A01(r3, A01);
                                        }
                                        AbstractC04000gb r32 = (AbstractC04000gb) arrayList.get(0);
                                        if (arrayList.size() >= 2) {
                                            r1 = (AbstractC04000gb) arrayList.get(1);
                                        } else {
                                            r1 = new AnonymousClass071(Object.class);
                                        }
                                        return AnonymousClass02Y.A00(A01, r32, r1);
                                    } else if (Collection.class.isAssignableFrom(A01)) {
                                        if (arrayList.size() >= 1) {
                                            return AnonymousClass02Z.A00(A01, (AbstractC04000gb) arrayList.get(0));
                                        }
                                        return C06240ml.A00(r3, A01);
                                    } else if (arrayList.size() != 0) {
                                        return C06240ml.A02(A01, (AbstractC04000gb[]) arrayList.toArray(new AbstractC04000gb[arrayList.size()]));
                                    }
                                }
                                return new AnonymousClass071(A01);
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
