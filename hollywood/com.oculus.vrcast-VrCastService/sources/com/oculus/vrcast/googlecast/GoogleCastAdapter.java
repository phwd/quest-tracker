package com.oculus.vrcast.googlecast;

import android.content.Context;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.VrCastAdapter;
import com.oculus.vrcast.telemetry.VrCastTelemetry;

public class GoogleCastAdapter implements VrCastAdapter {
    private final GoogleCastConnectionHelper mConnectionHelper;
    private final GoogleCastDiscoveryHelper mDiscoveryHelper;

    public GoogleCastAdapter(Context context, VrCastTelemetry vrCastTelemetry) {
        this.mDiscoveryHelper = new GoogleCastDiscoveryHelper(context, vrCastTelemetry);
        this.mConnectionHelper = new GoogleCastConnectionHelper(context, vrCastTelemetry);
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void startDiscovery() {
        this.mDiscoveryHelper.startDiscovery();
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void stopDiscovery() {
        this.mDiscoveryHelper.stopDiscovery();
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void connect(CastDevice castDevice) {
        this.mConnectionHelper.connect((GoogleCastDevice) castDevice);
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void disconnect(CastDevice castDevice) {
        this.mConnectionHelper.disconnect();
    }
}
