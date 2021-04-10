package com.oculus.os;

import android.os.ServiceManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import oculus.internal.ICalibrationService;

public class CalibrationInputStream extends InputStream {
    private static final String CALIBRATION_SERVICE = "HMDCalibration";
    private static final boolean DEBUG = false;
    private static final String TAG = "CalibrationInputStream";
    private final long mSize;
    private final ByteArrayInputStream mStream;

    public CalibrationInputStream(String type) throws IOException {
        this(null, type);
    }

    @VisibleForTesting
    public CalibrationInputStream(ICalibrationService service, String type) throws IOException {
        byte[] data = getCalibrationData(service, type);
        if (data != null) {
            this.mSize = (long) data.length;
            this.mStream = new ByteArrayInputStream(data);
            return;
        }
        Log.e(TAG, "Failed to create input stream for calibration file " + type);
        throw new IOException();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        this.mStream.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.mStream.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return this.mStream.read(b);
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        return this.mStream.read(b, off, len);
    }

    public long getSize() {
        return this.mSize;
    }

    private static byte[] getCalibrationData(ICalibrationService service, String type) {
        if (service == null) {
            try {
                service = ICalibrationService.Stub.asInterface(ServiceManager.getService(CALIBRATION_SERVICE));
            } catch (Exception e) {
                Log.e(TAG, "loadCalibrationFile: encountered exception", e);
                return null;
            }
        }
        return service.loadCalibrationFile(type);
    }
}
