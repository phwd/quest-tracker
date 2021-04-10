package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* renamed from: X.0Oe  reason: invalid class name */
public abstract class AnonymousClass0Oe extends AbstractC02120i3 implements Serializable {
    public static final long serialVersionUID = 1;
    public transient ArrayList<AbstractC03600nz<?>> A00;
    public transient IdentityHashMap<Object, AnonymousClass0r1> A01;

    @Override // X.AbstractC02120i3
    public final JsonSerializer<Object> A0A(AnonymousClass0qA r4, Object obj) throws C02180iD {
        if (obj != null) {
            if (!(obj instanceof JsonSerializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonSerializer.None.class || cls == C04130pP.class)) {
                        if (JsonSerializer.class.isAssignableFrom(cls)) {
                            obj = C04810rI.A02(cls, this._config.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<JsonSerializer>"));
                        }
                    }
                } else {
                    throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned serializer definition of type ", obj.getClass().getName(), "; expected type JsonSerializer or Class<JsonSerializer> instead"));
                }
            }
            JsonSerializer<Object> jsonSerializer = (JsonSerializer) obj;
            if (jsonSerializer instanceof AbstractC04620qo) {
                ((AbstractC04620qo) jsonSerializer).A9O(this);
            }
            return jsonSerializer;
        }
        return null;
    }

    public abstract AnonymousClass0Oe A0I(AnonymousClass0HM v, AbstractC04630qr v2);

    public final void A0J(AbstractC02300iS r10, Object obj) throws IOException, C02310iT {
        JsonSerializer<Object> jsonSerializer;
        C02270iP r1;
        String str;
        boolean z = false;
        if (obj == null) {
            jsonSerializer = this._nullValueSerializer;
        } else {
            Class<?> cls = obj.getClass();
            jsonSerializer = A0C(cls, true, null);
            AnonymousClass0HM r2 = this._config;
            String str2 = r2._rootName;
            if (str2 == null) {
                z = r2.A06(AnonymousClass0i4.WRAP_ROOT_VALUE);
                if (z) {
                    r10.A0I();
                    C04930rZ r3 = this._rootNames;
                    AnonymousClass0HM r7 = this._config;
                    synchronized (r3) {
                        C04720r6 r5 = new C04720r6(cls);
                        C04850rP<C04720r6, C02270iP> r0 = r3._rootNames;
                        if (r0 == null) {
                            r3._rootNames = new C04850rP<>(20, 200);
                        } else {
                            r1 = r0.get(r5);
                            if (r1 != null) {
                            }
                        }
                        C04070pB A07 = r7.A01().A07(r7.A02(r7.A03(cls)).A07());
                        if (A07 != null) {
                            str = A07._simpleName;
                            if (str.length() > 0) {
                                r1 = new C02270iP(str);
                                r3._rootNames.put(r5, r1);
                            }
                        }
                        str = cls.getSimpleName();
                        r1 = new C02270iP(str);
                        r3._rootNames.put(r5, r1);
                    }
                    r10.A0P(r1);
                }
            } else if (str2.length() != 0) {
                r10.A0I();
                r10.A0R(str2);
                z = true;
            }
        }
        try {
            jsonSerializer.serialize(obj, r10, this);
            if (z) {
                r10.A0F();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = AnonymousClass006.A09("[no message for ", e2.getClass().getName(), "]");
            }
            throw new C02180iD(message, e2);
        }
    }

    @Override // X.AbstractC02120i3
    public final AnonymousClass0r1 A0D(Object obj, AbstractC03600nz<?> r7) {
        IdentityHashMap<Object, AnonymousClass0r1> identityHashMap = this.A01;
        if (identityHashMap == null) {
            this.A01 = new IdentityHashMap<>();
        } else {
            AnonymousClass0r1 r0 = identityHashMap.get(obj);
            if (r0 != null) {
                return r0;
            }
        }
        ArrayList<AbstractC03600nz<?>> arrayList = this.A00;
        if (arrayList != null) {
            int i = 0;
            int size = arrayList.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                AbstractC03600nz<?> r1 = arrayList.get(i);
                if (r1.A04(r7)) {
                    r7 = r1;
                    break;
                }
                i++;
            }
        } else {
            arrayList = new ArrayList<>(8);
            this.A00 = arrayList;
        }
        arrayList.add(r7);
        AnonymousClass0r1 r12 = new AnonymousClass0r1(r7);
        this.A01.put(obj, r12);
        return r12;
    }

    public AnonymousClass0Oe() {
    }

    public AnonymousClass0Oe(AbstractC02120i3 r1, AnonymousClass0HM r2, AbstractC04630qr r3) {
        super(r1, r2, r3);
    }
}
