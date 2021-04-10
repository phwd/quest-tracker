package com.oculus.assistant.entry;

import X.AbstractC0105Aj;
import X.AbstractC0106Ak;
import X.AbstractC0388Vb;
import X.AnonymousClass08;
import X.C0112Aq;
import X.C0139Dd;
import X.C0387Va;
import X.C1242vw;
import X.C1243vx;
import X.C1244vy;
import X.C1245vz;
import X.C1246w0;
import X.C1254wA;
import X.C1255wB;
import X.C1256wC;
import X.C1257wD;
import X.C1258wE;
import X.C1260wG;
import X.W8;
import X.W9;
import X.WA;
import X.w1;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import com.facebook.debug.tracer.Tracer;
import com.oculus.assistant.api.IOculusAssistantService;
import com.oculus.assistant.api.IOculusAssistantSubscriber;
import com.oculus.assistant.api.IOculusAssistantSubscriber$Stub$Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class AssistantEntryManager extends Binder implements IOculusAssistantService {
    public C0112Aq A00;
    public w1 A01;
    public W8 A02;
    public W9 A03;
    public WA A04;
    public boolean A05;
    public final AbstractC0105Aj A06;
    public final AbstractC0105Aj A07;
    public final AbstractC0105Aj A08;
    public final AbstractC0105Aj A09;
    public final List A0A;
    public final Map A0B;
    public final Map A0C;

    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IOculusAssistantSubscriber iOculusAssistantSubscriber$Stub$Proxy;
        Bundle bundle;
        if (i != 1) {
            if (i == 2) {
                parcel.enforceInterface("com.oculus.assistant.api.IOculusAssistantService");
                A5K(parcel.readString(), parcel.readString());
            } else if (i == 3) {
                parcel.enforceInterface("com.oculus.assistant.api.IOculusAssistantService");
                String readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                A4X(readString, bundle);
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.oculus.assistant.api.IOculusAssistantService");
                return true;
            }
            parcel2.writeNoException();
            return true;
        }
        parcel.enforceInterface("com.oculus.assistant.api.IOculusAssistantService");
        String readString2 = parcel.readString();
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            iOculusAssistantSubscriber$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.oculus.assistant.api.IOculusAssistantSubscriber");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantSubscriber)) {
                iOculusAssistantSubscriber$Stub$Proxy = new IOculusAssistantSubscriber$Stub$Proxy(readStrongBinder);
            } else {
                iOculusAssistantSubscriber$Stub$Proxy = (IOculusAssistantSubscriber) queryLocalInterface;
            }
        }
        String A5D = A5D(readString2, iOculusAssistantSubscriber$Stub$Proxy);
        parcel2.writeNoException();
        parcel2.writeString(A5D);
        return true;
    }

    @Override // com.oculus.assistant.api.IOculusAssistantService
    public final void A4X(String str, Bundle bundle) {
        String obj;
        Log.d("AssistantEntryManager", AnonymousClass08.A04("Received message: ", str));
        WA wa = this.A04;
        if (!wa.A00.containsKey(str)) {
            Log.e("MessageFactory", AnonymousClass08.A04("Invalid message received: ", str));
        } else {
            try {
                AbstractC0106Ak ak = (AbstractC0106Ak) ((AbstractC0388Vb) ((Class) wa.A00.get(str)).newInstance()).A2C(bundle);
                if (ak != null) {
                    if (this.A05) {
                        this.A00.A01(ak);
                        return;
                    } else {
                        this.A0A.add(ak);
                        return;
                    }
                }
            } catch (Exception e) {
                Log.e("MessageFactory", AnonymousClass08.A04("Failed unmarshaling: ", str), e);
            }
        }
        if (bundle == null) {
            obj = "null";
        } else {
            obj = bundle.toString();
        }
        C0139Dd.A09("AssistantEntryManager", AnonymousClass08.A06("Unable to generate message using messageType:", str, " bundle:", obj));
    }

    @Override // com.oculus.assistant.api.IOculusAssistantService
    public final String A5D(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber) {
        C0139Dd.A0H("AssistantEntryManager", "Subscribing to %s subscriber: %s", str, iOculusAssistantSubscriber);
        if (iOculusAssistantSubscriber == null) {
            return null;
        }
        String obj = UUID.randomUUID().toString();
        C0387Va va = new C0387Va(str, iOculusAssistantSubscriber, obj);
        this.A0B.put(obj, va);
        if (this.A05) {
            this.A00.A01(new C1255wB(va));
            return obj;
        }
        this.A0C.put(obj, va);
        return obj;
    }

    @Override // com.oculus.assistant.api.IOculusAssistantService
    public final void A5K(String str, String str2) {
        C0139Dd.A0H("AssistantEntryManager", "Unsubscribing: %s  type: %s", str2, str);
        Map map = this.A0B;
        if (!map.containsKey(str2)) {
            C0139Dd.A0A("AssistantEntryManager", AnonymousClass08.A06("Subscriber has already unsubscribed. Got unsubscribe message for unknown key: ", str2, " messageType: ", str));
            return;
        }
        if (this.A05) {
            this.A00.A01(new C1260wG((C0387Va) map.get(str2)));
        } else {
            this.A0C.remove(str2);
        }
        map.remove(str2);
    }

    public AssistantEntryManager() {
        attachInterface(this, "com.oculus.assistant.api.IOculusAssistantService");
    }

    public AssistantEntryManager(Context context) {
        this();
        this.A0B = Collections.synchronizedMap(new HashMap());
        this.A03 = null;
        this.A0C = Collections.synchronizedMap(new HashMap());
        this.A0A = Collections.synchronizedList(new ArrayList());
        this.A05 = false;
        this.A02 = new C1242vw(this);
        Tracer.A01("AssistantEntryManager");
        try {
            this.A00 = C0112Aq.A00();
            this.A04 = new WA();
            C1243vx vxVar = new C1243vx(this);
            this.A08 = vxVar;
            this.A09 = new C1244vy(this);
            this.A07 = new C1245vz(this);
            this.A06 = new C1246w0(this);
            this.A00.A02(C1257wD.class, vxVar);
            this.A00.A02(C1258wE.class, this.A09);
            this.A00.A02(C1256wC.class, this.A07);
            this.A00.A02(C1254wA.class, this.A06);
            W9 w9 = this.A03;
            if (w9 == null) {
                w9 = new W9(context, this.A02);
                this.A03 = w9;
            }
            w9.A01();
        } finally {
            Tracer.A00();
        }
    }
}
