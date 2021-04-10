package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.assistant.api.IOculusAssistantService;
import com.oculus.assistant.api.IOculusAssistantService$Stub$Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VY implements ServiceConnection {
    public IOculusAssistantService A00 = null;
    public final Map A01 = Collections.synchronizedMap(new HashMap());

    public final void onServiceDisconnected(ComponentName componentName) {
        this.A00 = null;
    }

    public final void A00(String str, Bundle bundle) {
        IOculusAssistantService iOculusAssistantService = this.A00;
        if (iOculusAssistantService != null) {
            try {
                iOculusAssistantService.A4X(str, bundle);
            } catch (RemoteException e) {
                Log.e("OAConnection", "Failed to post message to Assistant: ", e);
            }
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IOculusAssistantService iOculusAssistantService$Stub$Proxy;
        String str;
        if (iBinder == null) {
            iOculusAssistantService$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oculus.assistant.api.IOculusAssistantService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantService)) {
                iOculusAssistantService$Stub$Proxy = new IOculusAssistantService$Stub$Proxy(iBinder);
            } else {
                iOculusAssistantService$Stub$Proxy = (IOculusAssistantService) queryLocalInterface;
            }
        }
        this.A00 = iOculusAssistantService$Stub$Proxy;
        ArrayList arrayList = new ArrayList();
        Map map = this.A01;
        for (Map.Entry entry : map.entrySet()) {
            try {
                str = this.A00.A5D(((C0387Va) entry.getValue()).A01, ((C0387Va) entry.getValue()).A00);
            } catch (RemoteException e) {
                Log.e("OAConnection", "Failed to subscribe to Assistant: ", e);
                str = null;
            }
            ((C0387Va) entry.getValue()).A02 = str;
            arrayList.add(entry.getValue());
        }
        map.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0387Va va = (C0387Va) it.next();
            map.put(va.A02, va);
        }
        if (this instanceof C1419zB) {
            A00("StartTypeaheadSessionMessage", null);
        }
    }
}
