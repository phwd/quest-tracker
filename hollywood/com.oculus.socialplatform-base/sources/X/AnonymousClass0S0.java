package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.LinkedHashMap;

/* renamed from: X.0S0  reason: invalid class name */
public abstract class AnonymousClass0S0 extends AbstractC02210iH implements Serializable {
    public static final long serialVersionUID = 1;
    public transient LinkedHashMap<C03590ny, C04430py> A00;

    @Override // X.AbstractC02210iH
    public final JsonDeserializer<Object> A0A(AnonymousClass0qA r4, Object obj) throws C02180iD {
        if (obj != null) {
            if (!(obj instanceof JsonDeserializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonDeserializer.None.class || cls == C04130pP.class)) {
                        if (JsonDeserializer.class.isAssignableFrom(cls)) {
                            obj = C04810rI.A02(cls, this._config.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<JsonDeserializer>"));
                        }
                    }
                } else {
                    throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned deserializer definition of type ", obj.getClass().getName(), "; expected type JsonDeserializer or Class<JsonDeserializer> instead"));
                }
            }
            JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) obj;
            if (jsonDeserializer instanceof AbstractC04280pi) {
                ((AbstractC04280pi) jsonDeserializer).A9N(this);
            }
            return jsonDeserializer;
        }
        return null;
    }

    @Override // X.AbstractC02210iH
    public final AnonymousClass0p6 A0I(AnonymousClass0qA r4, Object obj) throws C02180iD {
        if (obj != null) {
            if (!(obj instanceof AnonymousClass0p6)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == AnonymousClass0iA.class || cls == C04130pP.class)) {
                        if (AnonymousClass0p6.class.isAssignableFrom(cls)) {
                            obj = C04810rI.A02(cls, this._config.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<KeyDeserializer>"));
                        }
                    }
                } else {
                    throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned key deserializer definition of type ", obj.getClass().getName(), "; expected type KeyDeserializer or Class<KeyDeserializer> instead"));
                }
            }
            AnonymousClass0p6 r5 = (AnonymousClass0p6) obj;
            if (r5 instanceof AbstractC04280pi) {
                ((AbstractC04280pi) r5).A9N(this);
            }
            return r5;
        }
        return null;
    }

    public abstract AnonymousClass0S0 A0Q(AbstractC04250pf v);

    @Override // X.AbstractC02210iH
    public final C04430py A0J(Object obj, AbstractC03600nz<?> r5) {
        C03590ny A002 = r5.A00(obj);
        LinkedHashMap<C03590ny, C04430py> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            this.A00 = new LinkedHashMap<>();
        } else {
            C04430py r0 = linkedHashMap.get(A002);
            if (r0 != null) {
                return r0;
            }
        }
        C04430py r02 = new C04430py(obj);
        this.A00.put(A002, r02);
        return r02;
    }

    public AnonymousClass0S0(AbstractC04250pf r1) {
        super(r1);
    }

    public AnonymousClass0S0(AnonymousClass0S0 r1, AbstractC04250pf r2) {
        super(r1, r2);
    }
}
