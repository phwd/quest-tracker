package org.chromium.device.sensors;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PlatformSensorProvider {

    /* renamed from: a  reason: collision with root package name */
    public SensorManager f10959a;
    public HandlerThread b;
    public Handler c;
    public final Set d = new HashSet();

    public PlatformSensorProvider(Context context) {
        this.f10959a = (SensorManager) context.getSystemService("sensor");
    }

    public static PlatformSensorProvider create() {
        return new PlatformSensorProvider(ContextUtils.getApplicationContext());
    }

    public PlatformSensor createSensor(int i) {
        if (this.f10959a == null) {
            return null;
        }
        if (i == 0) {
            return PlatformSensor.a(5, 1, this);
        }
        if (i == 8) {
            return PlatformSensor.a(11, 4, this);
        }
        if (i == 10) {
            return PlatformSensor.a(15, 4, this);
        }
        if (i == 2) {
            return PlatformSensor.a(1, 3, this);
        }
        if (i == 3) {
            return PlatformSensor.a(10, 3, this);
        }
        if (i == 4) {
            return PlatformSensor.a(4, 3, this);
        }
        if (i != 5) {
            return null;
        }
        return PlatformSensor.a(2, 3, this);
    }

    public boolean hasSensorType(int i) {
        SensorManager sensorManager = this.f10959a;
        if (sensorManager == null) {
            return false;
        }
        int i2 = 5;
        if (i != 0) {
            if (i == 8) {
                i2 = 11;
            } else if (i == 10) {
                i2 = 15;
            } else if (i == 2) {
                i2 = 1;
            } else if (i == 3) {
                i2 = 10;
            } else if (i == 4) {
                i2 = 4;
            } else if (i != 5) {
                return false;
            } else {
                i2 = 2;
            }
        }
        return !sensorManager.getSensorList(i2).isEmpty();
    }

    public void setSensorManagerToNullForTesting() {
        this.f10959a = null;
    }
}
