package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.vrshell.panelservice.PanelService;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* renamed from: vw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC5338vw0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public WeakReference f11506a;

    public HandlerC5338vw0(PanelService panelService) {
        this.f11506a = new WeakReference(panelService);
    }

    public void handleMessage(Message message) {
        Surface surface;
        int i = message.what;
        if (i == 2) {
            Bundle data = message.getData();
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) data.getParcelable("panelServicePFD");
            Surface surface2 = (Surface) data.getParcelable("panelSurface");
            Bundle bundle = (Bundle) data.getParcelable("panelBundle");
            if (bundle.containsKey("_oc_shell_panel_app_wait_for_debugger")) {
                Debug.waitForDebugger();
            }
            new HashMap();
            HashMap hashMap = new HashMap();
            for (String str : data.keySet()) {
                if (str.startsWith("panelSurface:")) {
                    hashMap.put(str.substring(13), (Surface) data.getParcelable(str));
                }
            }
            if (hashMap.size() == 0 && (surface = (Surface) data.getParcelable("panelSurfaceAux")) != null) {
                hashMap.put("aux", surface);
            }
            PanelService panelService = (PanelService) this.f11506a.get();
            if (!bundle.containsKey("_oc_analytics_session_id") || !bundle.containsKey("_oc_shell_version_code") || !bundle.containsKey("_oc_shell_version_name")) {
                throw new IllegalArgumentException("Missing 1 or more required environment keys required for versioning and telemetry.");
            }
            panelService.nativeInitializePanelInstance(panelService.a(surface2, hashMap, bundle), parcelFileDescriptor.detachFd());
        } else if (i != 3) {
            super.handleMessage(message);
        } else {
            message.getData().getBinder("panelToken");
            PanelService panelService2 = (PanelService) this.f11506a.get();
            if (panelService2 != null) {
                panelService2.b();
            } else {
                Log.e("CppPanelService", "Service weak reference lost! Unable to handle activity token.");
            }
        }
    }

    public boolean sendMessageAtTime(Message message, long j) {
        PanelService panelService = (PanelService) this.f11506a.get();
        String c = panelService != null ? panelService.c(Binder.getCallingUid()) : "Service weak reference lost!";
        if (c == null) {
            return super.sendMessageAtTime(message, j);
        }
        Log.e("CppPanelService", "Error processing MSG_CREATE_PANEL_APP:  " + c);
        try {
            ((ParcelFileDescriptor) message.getData().getParcelable("panelServicePFD")).close();
            return false;
        } catch (Exception e) {
            Log.e("CppPanelService", "Error closing ParcelFileDescriptor after verification failure:  ", e);
            return false;
        }
    }
}
