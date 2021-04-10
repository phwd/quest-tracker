package com.oculus.companion.server.casting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import com.oculus.companion.server.Protocol$CastDevice;
import com.oculus.security.CallerInfoHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import oculus.internal.BlockingResultReceiver;

public class CastController {
    private static final long DISCOVER_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(5);
    private static final ComponentName OVR_MEDIA_SERVICE_COMPONENT = new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService");
    private static final long START_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(10);
    private static final long STATUS_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(3);
    private final Context mContext;
    private final Handler mHandler;

    public CastController(Context context) {
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
    }

    public List<Protocol$CastDevice> getAdvertisingDevices() {
        Pair result = new BlockingResultReceiver(this.mContext, this.mHandler, createOvrMediaServiceIntent("com.oculus.horizon.VRCAST_DISCOVER_ACTION"), "result_receiver").getResult(DISCOVER_TIMEOUT_MILLIS);
        if (result == null || ((Integer) result.first).intValue() != 0) {
            return null;
        }
        return extractDevicesList((Bundle) result.second);
    }

    public int startCasting(String str) {
        Intent createOvrMediaServiceIntent = createOvrMediaServiceIntent("com.oculus.horizon.VRCAST_START_CASTING_ACTION");
        BlockingResultReceiver blockingResultReceiver = new BlockingResultReceiver(this.mContext, this.mHandler, createOvrMediaServiceIntent, "result_receiver");
        createOvrMediaServiceIntent.putExtra("deviceid", str);
        Pair result = blockingResultReceiver.getResult(START_TIMEOUT_MILLIS);
        return (result == null || ((Integer) result.first).intValue() != 0) ? 1 : 0;
    }

    public void stopCasting() {
        this.mContext.startService(createOvrMediaServiceIntent("com.oculus.horizon.VRCAST_STOP_CASTING_ACTION"));
    }

    public Protocol$CastDevice getCastingStatus() {
        Pair result = new BlockingResultReceiver(this.mContext, this.mHandler, createOvrMediaServiceIntent("com.oculus.horizon.VRCAST_CASTING_STATUS_ACTION"), "result_receiver").getResult(STATUS_TIMEOUT_MILLIS);
        if (result == null || ((Integer) result.first).intValue() != 0) {
            return null;
        }
        return extractDevice((Bundle) result.second, Protocol$CastDevice.Status.INVALID);
    }

    private Intent createOvrMediaServiceIntent(String str) {
        Intent intent = new Intent();
        intent.setComponent(OVR_MEDIA_SERVICE_COMPONENT);
        intent.putExtra("message_type", str);
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, "com.oculus.companion.server");
        return intent;
    }

    private List<Protocol$CastDevice> extractDevicesList(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList("device_list");
        if (parcelableArrayList == null) {
            return arrayList;
        }
        for (Bundle bundle2 : parcelableArrayList) {
            Protocol$CastDevice extractDevice = extractDevice(bundle2, Protocol$CastDevice.Status.FOUND);
            if (extractDevice != null) {
                arrayList.add(extractDevice);
            }
        }
        return arrayList;
    }

    private Protocol$CastDevice extractDevice(Bundle bundle, Protocol$CastDevice.Status status) {
        String string = bundle.getString("device_name");
        String string2 = bundle.getString("deviceid");
        if (bundle.containsKey("device_status")) {
            status = Protocol$CastDevice.Status.valueOf(bundle.getInt("device_status", status.getNumber()));
        }
        if (string == null || string2 == null) {
            return null;
        }
        Protocol$CastDevice.Builder newBuilder = Protocol$CastDevice.newBuilder();
        newBuilder.setName(string);
        newBuilder.setId(string2);
        newBuilder.setStatus(status);
        newBuilder.setType(Protocol$CastDevice.Type.CHROMECAST);
        return (Protocol$CastDevice) newBuilder.build();
    }
}
