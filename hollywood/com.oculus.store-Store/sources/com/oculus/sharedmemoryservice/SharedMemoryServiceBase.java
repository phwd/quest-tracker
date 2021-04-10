package com.oculus.sharedmemoryservice;

import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import com.facebook.debug.log.BLog;
import com.oculus.modules.SharedMemoryModuleImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class SharedMemoryServiceBase extends Service {
    private static final String MODULE_NAME = SharedMemoryServiceBase.class.getSimpleName();
    private static final int SHARED_MEMORY_INFO_ID = 1;
    private static final String TEMP_DIR_NAME = "/temp";
    Handler mMainHandler = new IncomingHandler(this);
    private Messenger mMessenger = new Messenger(this.mMainHandler);

    public IBinder onBind(Intent intent) {
        return this.mMessenger.getBinder();
    }

    static class IncomingHandler extends Handler {
        private final WeakReference<SharedMemoryServiceBase> service;

        IncomingHandler(SharedMemoryServiceBase cps) {
            this.service = new WeakReference<>(cps);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String fileName = (String) msg.getData().getCharSequence("fileName", "");
                    ParcelFileDescriptor pfd = (ParcelFileDescriptor) msg.obj;
                    int length = msg.arg1;
                    byte[] buffer = new byte[length];
                    try {
                        new ParcelFileDescriptor.AutoCloseInputStream(pfd).read(buffer);
                        String tempDirPath = this.service.get().getFilesDir() + SharedMemoryServiceBase.TEMP_DIR_NAME;
                        File tempDir = new File(tempDirPath);
                        if (tempDir.exists()) {
                            for (File tempFile : tempDir.listFiles()) {
                                tempFile.delete();
                            }
                        } else if (!tempDir.mkdir()) {
                            BLog.d(SharedMemoryServiceBase.MODULE_NAME, "Unable to create temp directory");
                            return;
                        }
                        String absolutePath = tempDirPath + "/" + fileName.substring(fileName.lastIndexOf("/") + 1);
                        try {
                            FileOutputStream output = new FileOutputStream(absolutePath);
                            output.write(buffer);
                            output.close();
                        } catch (IOException e) {
                            BLog.d(SharedMemoryServiceBase.MODULE_NAME, "Got IOException " + e);
                        }
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        BitmapFactory.decodeByteArray(buffer, 0, length, options);
                        SharedMemoryModuleImpl.setStaticFileInfo(absolutePath, options.outWidth, options.outHeight);
                        break;
                    } catch (IOException e2) {
                        BLog.d(SharedMemoryServiceBase.MODULE_NAME, "Got IOException" + e2);
                        return;
                    }
            }
            super.handleMessage(msg);
        }
    }
}
