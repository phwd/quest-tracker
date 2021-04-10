package X;

import com.facebook.common.json.ArrayListDeserializer;
import com.facebook.common.json.ImmutableListDeserializer;
import com.facebook.common.json.ImmutableMapDeserializer;
import com.facebook.common.json.LinkedHashMapDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Nullable;

/* renamed from: X.0Vd  reason: invalid class name and case insensitive filesystem */
public final class C01210Vd extends C02130i6 {
    public static C01210Vd A00;
    public boolean mHumanReadableFormatEnabled;
    public final AnonymousClass0J1 mJsonLogger;

    public C01210Vd(@Nullable AnonymousClass0iU r8, @Nullable AnonymousClass0J1 r9) {
        super(r8, null, null);
        AnonymousClass0HU r5;
        AnonymousClass0HM r3;
        AnonymousClass0HU r1;
        AnonymousClass0HM r0;
        this.mJsonLogger = r9;
        AnonymousClass0IM r12 = new AnonymousClass0IM();
        if (r12.A00() == null) {
            throw new IllegalArgumentException("Module without defined name");
        } else if (r12.version() != null) {
            r12.A01(new C02140i7(this, this));
            AnonymousClass0o1 r6 = AnonymousClass0o1.ALL;
            AnonymousClass0nR r32 = AnonymousClass0nR.NONE;
            AnonymousClass0HU r2 = this._deserializationConfig;
            C04140pQ r13 = r2._base;
            C04140pQ A002 = r13.A00(r6, r32);
            if (r13 == A002) {
                r5 = r2;
            } else {
                r5 = new AnonymousClass0HU(r2, A002);
            }
            this._deserializationConfig = r5;
            AnonymousClass0HM r22 = this._serializationConfig;
            C04140pQ r14 = r22._base;
            C04140pQ A003 = r14.A00(r6, r32);
            if (r14 == A003) {
                r3 = r22;
            } else {
                r3 = new AnonymousClass0HM(r22, A003);
            }
            this._serializationConfig = r3;
            EnumC02200iG r02 = EnumC02200iG.FAIL_ON_UNKNOWN_PROPERTIES;
            int i = r5._deserFeatures;
            int mask = (r02.getMask() ^ -1) & i;
            if (mask == i) {
                r1 = r5;
            } else {
                r1 = new AnonymousClass0HU(r5, r5._mapperFeatures, mask);
            }
            this._deserializationConfig = r1;
            EnumC03560nf r15 = EnumC03560nf.NON_NULL;
            if (r3._serializationInclusion == r15) {
                r0 = r3;
            } else {
                r0 = new AnonymousClass0HM(r3, r15);
            }
            this._serializationConfig = r0;
            this.mHumanReadableFormatEnabled = false;
        } else {
            throw new IllegalArgumentException("Module without defined version");
        }
    }

    static {
        C01220Ve r4 = new C01220Ve();
        C04140pQ r3 = new C04140pQ(r4, C02130i6.A02, C02130i6.A03, null, AnonymousClass0r9.A02, null, C04940ra.A05, Locale.getDefault(), TimeZone.getTimeZone("GMT"), AnonymousClass0o3.A01);
        try {
            Field declaredField = C02130i6.class.getDeclaredField("DEFAULT_INTROSPECTOR");
            declaredField.setAccessible(true);
            declaredField.set(null, r4);
            Field declaredField2 = C02130i6.class.getDeclaredField("DEFAULT_BASE");
            declaredField2.setAccessible(true);
            declaredField2.set(null, r3);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    @Override // X.C02130i6
    public final AnonymousClass0Oe A05(AnonymousClass0HM r7) {
        return new AnonymousClass0IH(this._serializerProvider, r7, this._serializerFactory, this.mJsonLogger, this.mHumanReadableFormatEnabled);
    }

    @Override // X.C02130i6
    public final JsonDeserializer<Object> A03(AbstractC02210iH r2, AbstractC02190iF r3) throws C02180iD {
        return A07(r2, r3);
    }

    public final <T> JsonDeserializer<T> A07(AbstractC02210iH r4, AbstractC02190iF r5) throws C02180iD {
        Class<?> cls;
        JsonDeserializer<T> A002;
        if (!r5.A0H() && (A002 = C00790Iy.A00(r5._class)) != null) {
            return A002;
        }
        Class<?> cls2 = r5._class;
        if (cls2 == List.class || cls2 == ArrayList.class) {
            return new ArrayListDeserializer(r5);
        }
        if (cls2 == ImmutableList.class) {
            return new ImmutableListDeserializer(r5);
        }
        AbstractC02190iF A06 = r5.A06(0);
        if (A06 != null && ((cls = A06._class) == String.class || Enum.class.isAssignableFrom(cls))) {
            if (cls2 == Map.class || cls2 == HashMap.class || cls2 == LinkedHashMap.class) {
                return new LinkedHashMapDeserializer(r5);
            }
            if (cls2 == ImmutableMap.class) {
                return new ImmutableMapDeserializer(r5);
            }
        }
        JsonDeserializer<T> jsonDeserializer = (JsonDeserializer<T>) super.A03(r4, r5);
        if (this.mJsonLogger == null) {
            return jsonDeserializer;
        }
        r5.toString();
        return jsonDeserializer;
    }

    public final <T> JsonDeserializer<T> A08(AbstractC02210iH r3, Class<T> cls) throws C02180iD {
        JsonDeserializer<T> A002 = C00790Iy.A00(cls);
        if (A002 == null) {
            A002 = (JsonDeserializer<T>) super.A03(r3, this._typeFactory.A09(cls, null));
            if (this.mJsonLogger != null) {
                cls.toString();
            }
        }
        return A002;
    }
}
