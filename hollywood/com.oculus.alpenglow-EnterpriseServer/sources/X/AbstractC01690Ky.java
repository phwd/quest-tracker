package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.LinkedHashMap;

/* renamed from: X.0Ky  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01690Ky extends AbstractC02570aK implements Serializable {
    public static final long serialVersionUID = 1;
    public transient LinkedHashMap<C05800lQ, C06590nP> A00;

    @Override // X.AbstractC02570aK
    public final JsonDeserializer<Object> A0A(AbstractC06640nb r3, Object obj) throws AnonymousClass0aG {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (!(obj instanceof JsonDeserializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonDeserializer.None.class || cls == C06410mq.class)) {
                        if (JsonDeserializer.class.isAssignableFrom(cls)) {
                            obj = C07130om.A02(cls, this._config.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            sb = new StringBuilder("AnnotationIntrospector returned Class ");
                            sb.append(cls.getName());
                            str = "; expected Class<JsonDeserializer>";
                        }
                    }
                } else {
                    sb = new StringBuilder("AnnotationIntrospector returned deserializer definition of type ");
                    sb.append(obj.getClass().getName());
                    str = "; expected type JsonDeserializer or Class<JsonDeserializer> instead";
                }
                sb.append(str);
                throw new IllegalStateException(sb.toString());
            }
            JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) obj;
            if (jsonDeserializer instanceof AbstractC06550n9) {
                ((AbstractC06550n9) jsonDeserializer).A7T(this);
            }
            return jsonDeserializer;
        }
        return null;
    }

    @Override // X.AbstractC02570aK
    public final AnonymousClass0mY A0H(AbstractC06640nb r3, Object obj) throws AnonymousClass0aG {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (!(obj instanceof AnonymousClass0mY)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == AbstractC02550aD.class || cls == C06410mq.class)) {
                        if (AnonymousClass0mY.class.isAssignableFrom(cls)) {
                            obj = C07130om.A02(cls, this._config.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            sb = new StringBuilder("AnnotationIntrospector returned Class ");
                            sb.append(cls.getName());
                            str = "; expected Class<KeyDeserializer>";
                        }
                    }
                } else {
                    sb = new StringBuilder("AnnotationIntrospector returned key deserializer definition of type ");
                    sb.append(obj.getClass().getName());
                    str = "; expected type KeyDeserializer or Class<KeyDeserializer> instead";
                }
                sb.append(str);
                throw new IllegalStateException(sb.toString());
            }
            AnonymousClass0mY r4 = (AnonymousClass0mY) obj;
            if (r4 instanceof AbstractC06550n9) {
                ((AbstractC06550n9) r4).A7T(this);
            }
            return r4;
        }
        return null;
    }

    public abstract AbstractC01690Ky A0P(C01260Fu v, AnonymousClass0aT v2, AbstractC06280mT v3);

    @Override // X.AbstractC02570aK
    public final C06590nP A0I(Object obj, AnonymousClass0lR<?> r5) {
        C05800lQ A002 = r5.A00(obj);
        LinkedHashMap<C05800lQ, C06590nP> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            this.A00 = new LinkedHashMap<>();
        } else {
            C06590nP r0 = linkedHashMap.get(A002);
            if (r0 != null) {
                return r0;
            }
        }
        C06590nP r02 = new C06590nP(obj);
        this.A00.put(A002, r02);
        return r02;
    }

    public AbstractC01690Ky(AbstractC01690Ky r1, C01260Fu r2, AnonymousClass0aT r3, AbstractC06280mT r4) {
        super(r1, r2, r3, r4);
    }

    public AbstractC01690Ky(AbstractC06540n6 r1) {
        super(r1);
    }
}
