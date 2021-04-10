package com.fasterxml.jackson.databind;

import X.AbstractC1012qg;
import X.AbstractC1031r2;
import X.AnonymousClass2H;
import X.AnonymousClass7v;
import X.C0247Ng;
import X.C0253Nr;
import X.C0254Ns;
import X.C1010qe;
import X.C1011qf;
import X.C1016qk;
import X.C1028qz;
import X.CF;
import X.JD;
import X.N3;
import X.NR;
import X.NX;
import X.NY;
import X.OB;
import X.QC;
import X.UK;
import X.VG;
import X.VH;
import com.facebook.auth.viewercontext.ViewerContext;
import com.facebook.common.json.FbSerializerProvider$1;
import com.facebook.common.json.FbSerializerProvider$2;
import com.facebook.common.json.FbSerializerProvider$3;
import com.facebook.common.json.FbSerializerProvider$4;
import com.facebook.common.json.FbSerializerProvider$5;
import com.facebook.common.json.FbSerializerProvider$6;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.guava.ser.MultimapSerializer;
import com.google.common.base.Preconditions;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

public abstract class JsonSerializer {

    public abstract class None extends JsonSerializer {
    }

    public final JsonSerializer A07(QC qc) {
        if (!(this instanceof UnwrappingBeanSerializer)) {
            if (this instanceof BeanAsArraySerializer) {
                return ((BeanAsArraySerializer) this).A00.A07(qc);
            }
            if (!(this instanceof BeanSerializer)) {
                return this;
            }
        }
        return new UnwrappingBeanSerializer((BeanSerializerBase) this, qc);
    }

    public final Class A08() {
        if (this instanceof StdSerializer) {
            return ((StdSerializer) this).A00;
        }
        if (!(this instanceof TypeWrappedSerializer)) {
            return null;
        }
        return Object.class;
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A09(java.lang.Object r7, X.AbstractC1012qg r8, X.AbstractC1031r2 r9, X.PU r10) {
        /*
        // Method dump skipped, instructions count: 734
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.JsonSerializer.A09(java.lang.Object, X.qg, X.r2, X.PU):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x005b A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0A(java.lang.Object r6) {
        /*
        // Method dump skipped, instructions count: 362
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.JsonSerializer.A0A(java.lang.Object):boolean");
    }

    public void A0B(Object obj, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        NY ny;
        NY ny2;
        String[] strArr;
        C0253Nr[] nrArr;
        int i;
        int i2;
        int i3;
        String A0p;
        C1016qk A02;
        if (this instanceof MultimapSerializer) {
            MultimapSerializer multimapSerializer = (MultimapSerializer) this;
            UK uk = (UK) obj;
            qgVar.A0C();
            if (!uk.isEmpty()) {
                MultimapSerializer.A00(multimapSerializer, uk, qgVar, r2Var);
            }
        } else if (this instanceof TypeWrappedSerializer) {
            TypeWrappedSerializer typeWrappedSerializer = (TypeWrappedSerializer) this;
            typeWrappedSerializer.A00.A09(obj, qgVar, r2Var, typeWrappedSerializer.A01);
            return;
        } else if (this instanceof FbSerializerProvider$6) {
            Map map = (Map) obj;
            qgVar.A0C();
            for (Object obj2 : map.keySet()) {
                if (obj2 instanceof String) {
                    qgVar.A0M((String) obj2);
                } else if (obj2 instanceof Enum) {
                    boolean z = qgVar instanceof JD;
                    if (!z) {
                        ny = ((VH) qgVar).A00;
                    } else {
                        ny = ((JD) qgVar).A00;
                    }
                    boolean z2 = ny instanceof C1028qz;
                    C1010qe qeVar = ((C1028qz) ny)._jsonFactory;
                    StringWriter stringWriter = new StringWriter();
                    AbstractC1012qg A01 = qeVar.A01(stringWriter);
                    if (!z) {
                        ny2 = ((VH) qgVar).A00;
                    } else {
                        ny2 = ((JD) qgVar).A00;
                    }
                    if (!(A01 instanceof JD)) {
                        ((VH) A01).A00 = ny2;
                    } else {
                        ((JD) A01).A00 = ny2;
                    }
                    A01.A0L(obj2);
                    A01.flush();
                    StringReader stringReader = new StringReader(stringWriter.toString());
                    C0247Ng ng = new C0247Ng(C1010qe.A00(), stringReader, true);
                    int i4 = qeVar._parserFeatures;
                    NY ny3 = qeVar._objectCodec;
                    C0254Ns ns = qeVar.A00;
                    NR nr = NR.CANONICALIZE_FIELD_NAMES;
                    int i5 = qeVar._factoryFeatures;
                    boolean z3 = false;
                    if ((nr.getMask() & i5) != 0) {
                        z3 = true;
                    }
                    boolean z4 = false;
                    if ((NR.INTERN_FIELD_NAMES.getMask() & i5) != 0) {
                        z4 = true;
                    }
                    synchronized (ns) {
                        strArr = ns.A07;
                        nrArr = ns.A06;
                        i = ns.A02;
                        i2 = ns.A08;
                        i3 = ns.A01;
                    }
                    AnonymousClass7v r15 = new AnonymousClass7v(ng, i4, stringReader, ny3, new C0254Ns(ns, z3, z4, strArr, nrArr, i, i2, i3));
                    if (((VG) r15).A00 == NX.FIELD_NAME) {
                        r15.A0I = false;
                        NX nx = r15.A0D;
                        r15.A0D = null;
                        ((VG) r15).A00 = nx;
                        if (nx == NX.VALUE_STRING) {
                            if (r15.A02) {
                                r15.A02 = false;
                                AnonymousClass7v.A0A(r15);
                            }
                            A0p = r15.A0O.A05();
                        } else {
                            if (nx == NX.START_ARRAY) {
                                A02 = r15.A0E.A01(r15.A09, r15.A08);
                            } else {
                                if (nx == NX.START_OBJECT) {
                                    A02 = r15.A0E.A02(r15.A09, r15.A08);
                                }
                                StringBuilder sb = new StringBuilder("Tried to use json as map key, but it is not a string: ");
                                sb.append(stringWriter);
                                throw new C1011qf(sb.toString());
                            }
                            r15.A0E = A02;
                            StringBuilder sb2 = new StringBuilder("Tried to use json as map key, but it is not a string: ");
                            sb2.append(stringWriter);
                            throw new C1011qf(sb2.toString());
                        }
                    } else if (r15.A0o() == NX.VALUE_STRING) {
                        A0p = r15.A0p();
                    } else {
                        StringBuilder sb22 = new StringBuilder("Tried to use json as map key, but it is not a string: ");
                        sb22.append(stringWriter);
                        throw new C1011qf(sb22.toString());
                    }
                    if (A0p != null && r15.A0o() == null) {
                        qgVar.A0M(A0p);
                    }
                    StringBuilder sb222 = new StringBuilder("Tried to use json as map key, but it is not a string: ");
                    sb222.append(stringWriter);
                    throw new C1011qf(sb222.toString());
                } else {
                    StringBuilder sb3 = new StringBuilder("Non-string, non-enum key (");
                    sb3.append(obj2.getClass());
                    sb3.append(") found in map.");
                    throw new C1011qf(sb3.toString());
                }
                qgVar.A0L(map.get(obj2));
            }
        } else if (this instanceof FbSerializerProvider$5) {
            qgVar.A0P(((Enum) obj).name());
            return;
        } else if (this instanceof FbSerializerProvider$4) {
            StaticGraphServiceFactory.getTreeSerializer();
            throw new NullPointerException("serializeTreeToByteBuffer");
        } else if (this instanceof FbSerializerProvider$3) {
            throw new NullPointerException("getTypeTag");
        } else if (this instanceof FbSerializerProvider$2) {
            ((OB) obj).A4x(qgVar, r2Var);
            return;
        } else if (!(this instanceof FbSerializerProvider$1)) {
            ViewerContext viewerContext = (ViewerContext) obj;
            Preconditions.checkNotNull(r2Var, "Must give a non null SerializerProvider");
            AnonymousClass2H r0 = r2Var._config;
            N3 n3 = N3.NON_NULL;
            N3 n32 = r0._serializationInclusion;
            if (n32 == null) {
                n32 = N3.ALWAYS;
            }
            if (n3.equals(n32)) {
                if (viewerContext == null) {
                    qgVar.A0A();
                }
                qgVar.A0C();
                String str = viewerContext.mUserId;
                if (str != null) {
                    qgVar.A0M("user_id");
                    qgVar.A0P(str);
                }
                String str2 = viewerContext.mAuthToken;
                if (str2 != null) {
                    qgVar.A0M("auth_token");
                    qgVar.A0P(str2);
                }
                String str3 = viewerContext.mSessionCookiesString;
                if (str3 != null) {
                    qgVar.A0M("session_cookies_string");
                    qgVar.A0P(str3);
                }
                boolean z5 = viewerContext.mIsPageContext;
                qgVar.A0M("is_page_context");
                qgVar.A0T(z5);
                boolean z6 = viewerContext.mIsDittoContext;
                qgVar.A0M("is_ditto_context");
                qgVar.A0T(z6);
                boolean z7 = viewerContext.mIsTimelineViewAsContext;
                qgVar.A0M("is_timeline_view_as_context");
                qgVar.A0T(z7);
                boolean z8 = viewerContext.mIsContextualProfileContext;
                qgVar.A0M("is_contextual_profile_context");
                qgVar.A0T(z8);
                boolean z9 = viewerContext.mIsRoomGuestContext;
                qgVar.A0M("is_room_guest_context");
                qgVar.A0T(z9);
                String str4 = viewerContext.mSessionSecret;
                if (str4 != null) {
                    qgVar.A0M("session_secret");
                    qgVar.A0P(str4);
                }
                String str5 = viewerContext.mSessionKey;
                if (str5 != null) {
                    qgVar.A0M("session_key");
                    qgVar.A0P(str5);
                }
                String str6 = viewerContext.mUsername;
                if (str6 != null) {
                    qgVar.A0M("username");
                    qgVar.A0P(str6);
                }
            } else {
                throw new IllegalArgumentException(String.format(Locale.US, "Currently, we only support serialization inclusion %s. You are using %s.", n3, n32));
            }
        } else {
            CF.A00(qgVar, r2Var, (Collection) obj);
            return;
        }
        qgVar.A09();
    }
}
