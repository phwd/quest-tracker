package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.LinkedHashMap;

/* renamed from: X.0HE  reason: invalid class name */
public abstract class AnonymousClass0HE extends AbstractC04020gg implements Serializable {
    public static final long serialVersionUID = 1;
    public transient LinkedHashMap<C04740jS, C05570lU> A00;

    @Override // X.AbstractC04020gg
    public final JsonDeserializer<Object> A06(AbstractC05680lg r3, Object obj) throws C03990gZ {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (!(obj instanceof JsonDeserializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonDeserializer.None.class || cls == C05340ku.class)) {
                        if (JsonDeserializer.class.isAssignableFrom(cls)) {
                            obj = C06330mu.A02(cls, this._config.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS));
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
            if (jsonDeserializer instanceof AbstractC05470lE) {
                ((AbstractC05470lE) jsonDeserializer).A8N(this);
            }
            return jsonDeserializer;
        }
        return null;
    }

    @Override // X.AbstractC04020gg
    public final AbstractC05240kb A0A(AbstractC05680lg r3, Object obj) throws C03990gZ {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (!(obj instanceof AbstractC05240kb)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == AbstractC03970gW.class || cls == C05340ku.class)) {
                        if (AbstractC05240kb.class.isAssignableFrom(cls)) {
                            obj = C06330mu.A02(cls, this._config.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS));
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
            AbstractC05240kb r4 = (AbstractC05240kb) obj;
            if (r4 instanceof AbstractC05470lE) {
                ((AbstractC05470lE) r4).A8N(this);
            }
            return r4;
        }
        return null;
    }

    @Override // X.AbstractC04020gg
    public final C05570lU A0B(Object obj, AbstractC04750jT<?> r5) {
        C04740jS A002 = r5.A00(obj);
        LinkedHashMap<C04740jS, C05570lU> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            this.A00 = new LinkedHashMap<>();
        } else {
            C05570lU r0 = linkedHashMap.get(A002);
            if (r0 != null) {
                return r0;
            }
        }
        C05570lU r02 = new C05570lU(obj);
        this.A00.put(A002, r02);
        return r02;
    }

    public AnonymousClass0HE(AbstractC05460lB r1) {
        super(r1);
    }
}
