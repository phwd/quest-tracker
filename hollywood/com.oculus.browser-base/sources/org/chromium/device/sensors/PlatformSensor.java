package org.chromium.device.sensors;

import J.N;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PlatformSensor implements SensorEventListener {

    /* renamed from: a  reason: collision with root package name */
    public long f10958a;
    public final Sensor b;
    public final int c;
    public final int d;
    public double e;
    public final PlatformSensorProvider f;

    public PlatformSensor(Sensor sensor, int i, PlatformSensorProvider platformSensorProvider) {
        this.d = i;
        this.f = platformSensorProvider;
        this.b = sensor;
        this.c = sensor.getMinDelay();
    }

    public static PlatformSensor a(int i, int i2, PlatformSensorProvider platformSensorProvider) {
        List<Sensor> sensorList = platformSensorProvider.f10959a.getSensorList(i);
        if (sensorList.isEmpty()) {
            return null;
        }
        return new PlatformSensor(sensorList.get(0), i2, platformSensorProvider);
    }

    public void b(double d2, double d3, double d4, double d5, double d6) {
        N.Mb4JvlL7(this.f10958a, this, d2, d3, d4, d5, d6);
    }

    public boolean checkSensorConfiguration(double d2) {
        return this.c <= ((int) ((1.0d / d2) * 1000000.0d));
    }

    public double getDefaultConfiguration() {
        return 5.0d;
    }

    public double getMaximumSupportedFrequency() {
        int i = this.c;
        return i == 0 ? getDefaultConfiguration() : 1.0d / (((double) i) * 1.0E-6d);
    }

    public int getReportingMode() {
        return this.b.getReportingMode() == 0 ? 1 : 0;
    }

    public void initPlatformSensorAndroid(long j) {
        this.f10958a = j;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        long j = this.f10958a;
        if (j == 0) {
            AbstractC1220Ua0.f("PlatformSensor", "Should not get sensor events after PlatformSensorAndroid is destroyed.", new Object[0]);
            return;
        }
        float[] fArr = sensorEvent.values;
        if (fArr.length < this.d) {
            N.MrHXg7he(j, this);
            stopSensor();
            return;
        }
        double d2 = ((double) sensorEvent.timestamp) * 1.0E-9d;
        int length = fArr.length;
        if (length == 1) {
            b(d2, (double) fArr[0], 0.0d, 0.0d, 0.0d);
        } else if (length == 2) {
            b(d2, (double) fArr[0], (double) fArr[1], 0.0d, 0.0d);
        } else if (length != 3) {
            b(d2, (double) fArr[0], (double) fArr[1], (double) fArr[2], (double) fArr[3]);
        } else {
            b(d2, (double) fArr[0], (double) fArr[1], (double) fArr[2], 0.0d);
        }
    }

    public void sensorDestroyed() {
        stopSensor();
        this.f10958a = 0;
    }

    public boolean startSensor(double d2) {
        double d3 = this.e;
        if (d3 == d2) {
            return true;
        }
        if (d3 != 0.0d) {
            this.f.f10959a.unregisterListener(this, this.b);
        }
        PlatformSensorProvider platformSensorProvider = this.f;
        synchronized (platformSensorProvider.d) {
            if (platformSensorProvider.d.isEmpty() && platformSensorProvider.b == null) {
                HandlerThread handlerThread = new HandlerThread("SensorsHandlerThread");
                platformSensorProvider.b = handlerThread;
                handlerThread.start();
                platformSensorProvider.c = new Handler(platformSensorProvider.b.getLooper());
            }
            platformSensorProvider.d.add(this);
        }
        boolean z = false;
        try {
            PlatformSensorProvider platformSensorProvider2 = this.f;
            z = platformSensorProvider2.f10959a.registerListener(this, this.b, (int) ((1.0d / d2) * 1000000.0d), platformSensorProvider2.c);
        } catch (RuntimeException e2) {
            AbstractC1220Ua0.f("PlatformSensor", "Failed to register sensor listener.", e2);
        }
        if (!z) {
            stopSensor();
            return z;
        }
        this.e = d2;
        return z;
    }

    public void stopSensor() {
        HandlerThread handlerThread;
        if (this.e != 0.0d) {
            this.f.f10959a.unregisterListener(this, this.b);
        }
        PlatformSensorProvider platformSensorProvider = this.f;
        synchronized (platformSensorProvider.d) {
            platformSensorProvider.d.remove(this);
            if (platformSensorProvider.d.isEmpty() && (handlerThread = platformSensorProvider.b) != null) {
                handlerThread.quitSafely();
                platformSensorProvider.b = null;
                platformSensorProvider.c = null;
            }
        }
        this.e = 0.0d;
    }
}
