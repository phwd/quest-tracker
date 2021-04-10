package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* renamed from: X.0Ju  reason: invalid class name */
public abstract class AnonymousClass0Ju extends AnonymousClass0a8 implements Serializable {
    public static final long serialVersionUID = 1;
    public transient ArrayList<AnonymousClass0lR<?>> A00;
    public transient IdentityHashMap<Object, C06980oV> A01;

    @Override // X.AnonymousClass0a8
    public final JsonSerializer<Object> A09(AbstractC06640nb r3, Object obj) throws AnonymousClass0aG {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (!(obj instanceof JsonSerializer)) {
                if (obj instanceof Class) {
                    Class cls = (Class) obj;
                    if (!(cls == JsonSerializer.None.class || cls == C06410mq.class)) {
                        if (JsonSerializer.class.isAssignableFrom(cls)) {
                            obj = C07130om.A02(cls, this._config.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS));
                        } else {
                            sb = new StringBuilder("AnnotationIntrospector returned Class ");
                            sb.append(cls.getName());
                            str = "; expected Class<JsonSerializer>";
                        }
                    }
                } else {
                    sb = new StringBuilder("AnnotationIntrospector returned serializer definition of type ");
                    sb.append(obj.getClass().getName());
                    str = "; expected type JsonSerializer or Class<JsonSerializer> instead";
                }
                sb.append(str);
                throw new IllegalStateException(sb.toString());
            }
            JsonSerializer<Object> jsonSerializer = (JsonSerializer) obj;
            if (jsonSerializer instanceof AbstractC06870oI) {
                ((AbstractC06870oI) jsonSerializer).A7U(this);
            }
            return jsonSerializer;
        }
        return null;
    }

    public abstract AnonymousClass0Ju A0G(AnonymousClass0FM v, AbstractC06900oL v2);

    public final void A0H(AbstractC02640aV r7, Object obj) throws IOException, C02650aW {
        JsonSerializer<Object> jsonSerializer;
        boolean z = false;
        if (obj == null) {
            jsonSerializer = this._nullValueSerializer;
        } else {
            Class<?> cls = obj.getClass();
            jsonSerializer = A0A(cls, null);
            AnonymousClass0FM r2 = this._config;
            String str = r2._rootName;
            if (str == null) {
                z = r2.A06(AnonymousClass0a9.WRAP_ROOT_VALUE);
                if (z) {
                    r7.A0F();
                    r7.A0N(this._rootNames.A00(cls, this._config));
                }
            } else if (str.length() != 0) {
                r7.A0F();
                r7.A0P(str);
                z = true;
            }
        }
        try {
            jsonSerializer.A0D(obj, r7, this);
            if (z) {
                r7.A0C();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = AnonymousClass006.A07("[no message for ", e2.getClass().getName(), "]");
            }
            throw new AnonymousClass0aG(message, e2);
        }
    }

    @Override // X.AnonymousClass0a8
    public final C06980oV A0C(Object obj, AnonymousClass0lR<?> r7) {
        IdentityHashMap<Object, C06980oV> identityHashMap = this.A01;
        if (identityHashMap == null) {
            this.A01 = new IdentityHashMap<>();
        } else {
            C06980oV r0 = identityHashMap.get(obj);
            if (r0 != null) {
                return r0;
            }
        }
        ArrayList<AnonymousClass0lR<?>> arrayList = this.A00;
        if (arrayList != null) {
            int i = 0;
            int size = arrayList.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                AnonymousClass0lR<?> r1 = arrayList.get(i);
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
        C06980oV r12 = new C06980oV(r7);
        this.A01.put(obj, r12);
        return r12;
    }

    public AnonymousClass0Ju() {
    }

    public AnonymousClass0Ju(AnonymousClass0a8 r1, AnonymousClass0FM r2, AbstractC06900oL r3) {
        super(r1, r2, r3);
    }
}
