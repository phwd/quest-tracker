package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.LinkedHashMap;

public abstract class Cs extends AbstractC0226Wn implements Serializable {
    public static final long serialVersionUID = 1;
    public transient LinkedHashMap<po, C0200Vg> A00;

    @Override // X.AbstractC0226Wn
    public final JsonDeserializer<Object> A07(VV vv, Object obj) throws C0223Wj {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (!(obj instanceof JsonDeserializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonDeserializer.None.class || cls == dY.class)) {
                        if (JsonDeserializer.class.isAssignableFrom(cls)) {
                            obj = Mv.A02(cls, this._config.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS));
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
            if (jsonDeserializer instanceof AbstractC0264Yo) {
                ((AbstractC0264Yo) jsonDeserializer).A4n(this);
            }
            return jsonDeserializer;
        }
        return null;
    }

    @Override // X.AbstractC0226Wn
    public final AbstractC0420hV A0E(VV vv, Object obj) throws C0223Wj {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (!(obj instanceof AbstractC0420hV)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == AbstractC0221Wg.class || cls == dY.class)) {
                        if (AbstractC0420hV.class.isAssignableFrom(cls)) {
                            obj = Mv.A02(cls, this._config.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS));
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
            AbstractC0420hV hVVar = (AbstractC0420hV) obj;
            if (hVVar instanceof AbstractC0264Yo) {
                ((AbstractC0264Yo) hVVar).A4n(this);
            }
            return hVVar;
        }
        return null;
    }

    public abstract Cs A0M(AnonymousClass8M v, AbstractC0232Ww ww, AbstractC0435iB iBVar);

    @Override // X.AbstractC0226Wn
    public final C0200Vg A0F(Object obj, pp<?> ppVar) {
        po A002 = ppVar.A00(obj);
        LinkedHashMap<po, C0200Vg> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            this.A00 = new LinkedHashMap<>();
        } else {
            C0200Vg vg = linkedHashMap.get(A002);
            if (vg != null) {
                return vg;
            }
        }
        C0200Vg vg2 = new C0200Vg(obj);
        this.A00.put(A002, vg2);
        return vg2;
    }

    public Cs(Cs cs, AnonymousClass8M r2, AbstractC0232Ww ww, AbstractC0435iB iBVar) {
        super(cs, r2, ww, iBVar);
    }

    public Cs(ZD zd) {
        super(zd);
    }
}
