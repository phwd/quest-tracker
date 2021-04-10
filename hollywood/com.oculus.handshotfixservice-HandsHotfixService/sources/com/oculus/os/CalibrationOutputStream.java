package com.oculus.os;

import android.os.ServiceManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import oculus.internal.ICalibrationService;

public class CalibrationOutputStream extends OutputStream {
    private static final String CALIBRATION_SERVICE = "HMDCalibration";
    private static final boolean DEBUG = false;
    private static final String TAG = "CalibrationOutputStream";
    private ICalibrationService mService;
    private ByteArrayOutputStream mStream;
    private String mType;

    public CalibrationOutputStream(String type) {
        this(null, type);
    }

    @VisibleForTesting
    public CalibrationOutputStream(ICalibrationService service, String type) {
        this.mService = service;
        this.mType = type;
        this.mStream = new ByteArrayOutputStream();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (this.mService == null) {
                this.mService = ICalibrationService.Stub.asInterface(ServiceManager.getService(CALIBRATION_SERVICE));
            }
            this.mService.writeCalibrationData(this.mType, this.mStream.toByteArray());
        } catch (Exception e) {
            Log.e(TAG, "Failed to close output stream for calibration file: " + this.mType, e);
            throw new IOException(e);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        this.mStream.write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.mStream.write(b, off, len);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.mStream.write(b);
    }
}
