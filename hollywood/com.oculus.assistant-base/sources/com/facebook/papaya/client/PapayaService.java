package com.facebook.papaya.client;

import X.AbstractC0370Ug;
import X.C0139Dd;
import X.C0905oX;
import X.C0906oY;
import X.EnumC0181Gs;
import X.Gv;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.papaya.IPapayaCallback;
import com.facebook.papaya.IPapayaLogSink;
import com.facebook.papaya.IPapayaService;
import com.facebook.papaya.client.executor.IExecutorFactory;
import com.facebook.papaya.client.platform.IDispatcher;
import com.facebook.papaya.client.platform.IPlatform;
import com.facebook.papaya.client.transport.ITransport;
import java.util.Map;

public class PapayaService extends Service {

    public final class BinderImpl extends Binder implements IPapayaService {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i == 2) {
                    parcel.enforceInterface("com.facebook.papaya.IPapayaService");
                    A1P(parcel.readString());
                } else if (i == 3) {
                    parcel.enforceInterface("com.facebook.papaya.IPapayaService");
                    PapayaJNI.nativeCancelAllExecutors();
                } else if (i == 4) {
                    parcel.enforceInterface("com.facebook.papaya.IPapayaService");
                    run();
                } else if (i == 5) {
                    parcel.enforceInterface("com.facebook.papaya.IPapayaService");
                    stop();
                } else if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    parcel2.writeString("com.facebook.papaya.IPapayaService");
                    return true;
                }
                parcel2.writeNoException();
                return true;
            }
            parcel.enforceInterface("com.facebook.papaya.IPapayaService");
            boolean A5C = A5C(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(A5C ? 1 : 0);
            return true;
        }

        @Override // com.facebook.papaya.IPapayaService
        public final boolean A5C(String str) {
            PapayaJNI.nativeSubmitExecutor(str);
            return true;
        }

        @Override // com.facebook.papaya.IPapayaService
        public final void run() {
            PapayaJNI.run();
        }

        @Override // com.facebook.papaya.IPapayaService
        public final void stop() {
            PapayaJNI.stop();
        }

        @Override // com.facebook.papaya.IPapayaService
        public final void A1P(String str) {
            PapayaJNI.nativeCancelExecutor(str);
        }

        public BinderImpl() {
            this(0);
        }

        public BinderImpl(int i) {
            attachInterface(this, "com.facebook.papaya.IPapayaService");
        }
    }

    public final IBinder onBind(Intent intent) {
        PapayaMetadataInternal papayaMetadataInternal;
        PapayaMetadata papayaMetadata;
        IDispatcher iDispatcher;
        IPlatform iPlatform;
        if (intent.getExtras() == null || (papayaMetadataInternal = (PapayaMetadataInternal) intent.getExtras().getParcelable("papaya_metadata")) == null || (papayaMetadata = papayaMetadataInternal.A02) == null || !papayaMetadata.A0D) {
            return null;
        }
        Class cls = papayaMetadata.A09;
        if (cls == null) {
            C0139Dd.A02(Gv.class, "Transport implementation not found!");
        } else {
            try {
                ITransport iTransport = (ITransport) cls.getConstructor(Context.class, Bundle.class).newInstance(this, papayaMetadata.A03);
                if (iTransport != null) {
                    Class cls2 = papayaMetadata.A07;
                    if (cls2 == null) {
                        iDispatcher = null;
                    } else {
                        try {
                            iDispatcher = (IDispatcher) cls2.newInstance();
                        } catch (Exception e) {
                            C0139Dd.A07(Gv.class, e, "Failed to instantiate Dispatcher implementation", new Object[0]);
                            iDispatcher = null;
                        }
                    }
                    String str = papayaMetadata.A0B;
                    ComponentName componentName = papayaMetadata.A01;
                    Class cls3 = papayaMetadata.A08;
                    if (cls3 == null) {
                        iPlatform = null;
                    } else {
                        try {
                            iPlatform = (IPlatform) cls3.newInstance();
                        } catch (Exception e2) {
                            C0139Dd.A07(Gv.class, e2, "Failed to instantiate Platform implementation", new Object[0]);
                            iPlatform = null;
                        }
                    }
                    PapayaJNI.initialize(str, this, componentName, iTransport, iPlatform, iDispatcher, null, papayaMetadata.A0C, papayaMetadata.A06, papayaMetadata.A0A);
                    AbstractC0370Ug A0E = papayaMetadata.A05.entrySet().iterator();
                    while (A0E.hasNext()) {
                        Map.Entry entry = (Map.Entry) A0E.next();
                        try {
                            String str2 = (String) entry.getKey();
                            PapayaJNI.nativeRegisterExecutor(str2, (IExecutorFactory) ((Class) entry.getValue()).getConstructor(Context.class, Bundle.class).newInstance(this, papayaMetadata.A02.getBundle(str2)));
                            if (C0139Dd.A01.A3Y(4)) {
                                C0139Dd.A03(Gv.class, StringFormatUtil.formatStrLocaleSafe("Registered executor factory: %s", str2));
                            }
                        } catch (Exception e3) {
                            C0139Dd.A07(Gv.class, e3, "Failed to instantiate ExecutorFactory implementation", new Object[0]);
                        }
                    }
                    IPapayaLogSink iPapayaLogSink = papayaMetadataInternal.A01;
                    EnumC0181Gs gs = papayaMetadata.A04;
                    if (iPapayaLogSink != null) {
                        PapayaJNI.addLogSink(gs, new C0905oX(iPapayaLogSink));
                    }
                    IPapayaCallback iPapayaCallback = papayaMetadataInternal.A00;
                    if (iPapayaCallback != null) {
                        PapayaJNI.nativeSetCallback(new C0906oY(iPapayaCallback));
                    }
                    C0139Dd.A01(Gv.class, "Initialized Papaya client runtime");
                    return new BinderImpl(0);
                }
            } catch (Exception e4) {
                C0139Dd.A07(Gv.class, e4, "Failed to instantiate Transport implementation", new Object[0]);
            }
        }
        C0139Dd.A02(Gv.class, "Failed to create Transport");
        return new BinderImpl(0);
    }
}
