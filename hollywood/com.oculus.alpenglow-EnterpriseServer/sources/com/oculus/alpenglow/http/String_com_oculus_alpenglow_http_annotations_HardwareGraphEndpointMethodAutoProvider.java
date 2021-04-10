package com.oculus.alpenglow.http;

import X.AnonymousClass0Li;
import android.os.SystemProperties;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_alpenglow_http_annotations_HardwareGraphEndpointMethodAutoProvider extends AnonymousClass0Li<String> {
    public final Object get() {
        return SystemProperties.get(HttpModule.HARDWARE_GRAPH_ENDPOINT_SYSPROP, Constants.HARDWARE_GRAPH_ENDPOINT);
    }
}
