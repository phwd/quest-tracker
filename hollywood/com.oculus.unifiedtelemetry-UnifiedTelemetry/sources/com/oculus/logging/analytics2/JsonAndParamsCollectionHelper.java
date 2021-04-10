package com.oculus.logging.analytics2;

import X.AbstractC0222Wi;
import X.AbstractC0224Wl;
import X.AbstractC0471q3;
import X.AnonymousClass07;
import X.AnonymousClass2z;
import X.AnonymousClass31;
import X.AnonymousClass3x;
import X.AnonymousClass8M;
import X.Bk;
import X.C0100Ib;
import X.C0109Kf;
import X.C0219We;
import X.C0223Wj;
import X.C0231Wv;
import X.C0417hQ;
import X.C0444kS;
import X.C0445kT;
import X.Cs;
import X.EnumC0225Wm;
import X.EnumC0467pu;
import X.EnumC0470q2;
import X.Mu;
import X.ON;
import X.X0;
import X.k2;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.StringReader;
import java.lang.ref.SoftReference;
import javax.annotation.Nullable;

public class JsonAndParamsCollectionHelper {
    public static final String TAG = "JsonAndParamsCollectionHelper";

    @Nullable
    public static AnonymousClass2z A00(String str, C0219We we) {
        k2 k2Var;
        String[] strArr;
        C0445kT[] kTVarArr;
        int i;
        int i2;
        int i3;
        Object obj;
        EnumC0470q2 q2Var;
        boolean z;
        C0231Wv wv;
        String str2;
        try {
            X0 x0 = we._jsonFactory;
            StringReader stringReader = new StringReader(str);
            ThreadLocal<SoftReference<k2>> threadLocal = X0.A02;
            SoftReference<k2> softReference = threadLocal.get();
            if (softReference == null) {
                k2Var = null;
            } else {
                k2Var = softReference.get();
            }
            if (k2Var == null) {
                k2Var = new k2();
                threadLocal.set(new SoftReference<>(k2Var));
            }
            Bk bk = new Bk(k2Var, stringReader);
            int i4 = x0._parserFeatures;
            AbstractC0471q3 q3Var = x0._objectCodec;
            C0444kS kSVar = x0.A00;
            EnumC0467pu puVar = EnumC0467pu.CANONICALIZE_FIELD_NAMES;
            int i5 = x0._factoryFeatures;
            boolean z2 = false;
            if ((puVar.getMask() & i5) != 0) {
                z2 = true;
            }
            boolean z3 = false;
            if ((EnumC0467pu.INTERN_FIELD_NAMES.getMask() & i5) != 0) {
                z3 = true;
            }
            synchronized (kSVar) {
                strArr = kSVar.A07;
                kTVarArr = kSVar.A06;
                i = kSVar.A02;
                i2 = kSVar.A08;
                i3 = kSVar.A01;
            }
            AnonymousClass3x r11 = new AnonymousClass3x(bk, i4, stringReader, q3Var, new C0444kS(kSVar, z2, z3, strArr, kTVarArr, i, i2, i3));
            AbstractC0224Wl wl = C0219We.A00;
            try {
                EnumC0470q2 A0Z = r11.A0Z();
                if (A0Z == null && (A0Z = r11.A0a()) == null) {
                    throw C0223Wj.A00(r11, "No content to map due to end-of-input");
                }
                if (A0Z == EnumC0470q2.VALUE_NULL) {
                    obj = C0219We.A00(we, we._deserializationContext.A0M(we._deserializationConfig, r11, null), wl).A08();
                } else if (A0Z == EnumC0470q2.END_ARRAY || A0Z == (q2Var = EnumC0470q2.END_OBJECT)) {
                    obj = null;
                } else {
                    AnonymousClass8M r3 = we._deserializationConfig;
                    Cs A0M = we._deserializationContext.A0M(r3, r11, null);
                    JsonDeserializer A00 = C0219We.A00(we, A0M, wl);
                    String str3 = r3._rootName;
                    if (str3 != null) {
                        z = false;
                        if (str3.length() > 0) {
                            z = true;
                        }
                    } else {
                        z = r3.A06(EnumC0225Wm.UNWRAP_ROOT_VALUE);
                    }
                    if (z) {
                        if (str3 == null) {
                            C0100Ib ib = we._rootNames;
                            Class<?> cls = wl._class;
                            synchronized (ib) {
                                ON on = new ON(cls);
                                C0109Kf<ON, C0231Wv> kf = ib._rootNames;
                                if (kf == null) {
                                    ib._rootNames = new C0109Kf<>(20, 200);
                                } else {
                                    wv = kf.get(on);
                                    if (wv != null) {
                                    }
                                }
                                C0417hQ A06 = r3.A01().A06(r3.A02(r3.A03(cls)).A05());
                                if (A06 != null) {
                                    str2 = A06._simpleName;
                                    boolean z4 = false;
                                    if (str2.length() > 0) {
                                        z4 = true;
                                    }
                                    if (z4) {
                                        wv = new C0231Wv(str2);
                                        ib._rootNames.put(on, wv);
                                    }
                                }
                                str2 = cls.getSimpleName();
                                wv = new C0231Wv(str2);
                                ib._rootNames.put(on, wv);
                            }
                            str3 = wv._value;
                        }
                        EnumC0470q2 A0Z2 = r11.A0Z();
                        if (A0Z2 != EnumC0470q2.START_OBJECT) {
                            StringBuilder sb = new StringBuilder("Current token not START_OBJECT (needed to unwrap root name '");
                            sb.append(str3);
                            sb.append("'), but ");
                            sb.append(A0Z2);
                            throw C0223Wj.A00(r11, sb.toString());
                        } else if (r11.A0a() == EnumC0470q2.FIELD_NAME) {
                            String A0c = r11.A0c();
                            if (str3.equals(A0c)) {
                                r11.A0a();
                                obj = A00.A09(r11, A0M);
                                if (r11.A0a() != q2Var) {
                                    StringBuilder sb2 = new StringBuilder("Current token not END_OBJECT (to match wrapper object with root name '");
                                    sb2.append(str3);
                                    sb2.append("'), but ");
                                    sb2.append(r11.A0Z());
                                    throw C0223Wj.A00(r11, sb2.toString());
                                }
                            } else {
                                StringBuilder sb3 = new StringBuilder("Root name '");
                                sb3.append(A0c);
                                sb3.append("' does not match expected ('");
                                sb3.append(str3);
                                sb3.append("') for type ");
                                sb3.append(wl);
                                throw C0223Wj.A00(r11, sb3.toString());
                            }
                        } else {
                            StringBuilder sb4 = new StringBuilder("Current token not FIELD_NAME (to contain expected root name '");
                            sb4.append(str3);
                            sb4.append("'), but ");
                            sb4.append(r11.A0Z());
                            throw C0223Wj.A00(r11, sb4.toString());
                        }
                    } else {
                        obj = A00.A09(r11, A0M);
                    }
                }
                r11.A0f();
                AbstractC0222Wi wi = (AbstractC0222Wi) obj;
                if (wi == null) {
                    wi = AnonymousClass31.A00;
                }
                boolean z5 = false;
                if (wi.A04() == AnonymousClass07.A06) {
                    z5 = true;
                }
                if (z5) {
                    return (AnonymousClass2z) wi;
                }
                Mu.A00(TAG, "Error adding json extras: json is not an object");
                return null;
            } finally {
                try {
                    r11.close();
                } catch (IOException unused) {
                }
            }
        } catch (IOException e) {
            Mu.A08(TAG, e, "Error adding json extras");
            return null;
        }
    }
}
