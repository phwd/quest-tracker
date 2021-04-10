package com.oculus.sharedmemoryservice;

import X.AnonymousClass006;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import com.oculus.modules.SharedMemoryModuleImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class SharedMemoryServiceBase extends Service {
    public static final String MODULE_NAME = "SharedMemoryServiceBase";
    public static final int SHARED_MEMORY_INFO_ID = 1;
    public static final String TEMP_DIR_NAME = "/temp";
    public Handler mMainHandler;
    public Messenger mMessenger;

    public static class IncomingHandler extends Handler {
        public final WeakReference<SharedMemoryServiceBase> service;

        public void handleMessage(Message message) {
            if (message.what == 1) {
                String str = (String) message.getData().getCharSequence("fileName", "");
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) message.obj;
                int i = message.arg1;
                byte[] bArr = new byte[i];
                try {
                    new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor).read(bArr);
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.service.get().getFilesDir());
                    sb.append(SharedMemoryServiceBase.TEMP_DIR_NAME);
                    String obj = sb.toString();
                    File file = new File(obj);
                    if (file.exists()) {
                        File[] listFiles = file.listFiles();
                        for (File file2 : listFiles) {
                            file2.delete();
                        }
                    } else if (!file.mkdir()) {
                        return;
                    }
                    String A09 = AnonymousClass006.A09(obj, "/", str.substring(str.lastIndexOf("/") + 1));
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(A09);
                        fileOutputStream.write(bArr);
                        fileOutputStream.close();
                    } catch (IOException unused) {
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    BitmapFactory.decodeByteArray(bArr, 0, i, options);
                    SharedMemoryModuleImpl.setStaticFileInfo(A09, options.outWidth, options.outHeight);
                } catch (IOException unused2) {
                    return;
                }
            }
            super.handleMessage(message);
        }

        public IncomingHandler(SharedMemoryServiceBase sharedMemoryServiceBase) {
            this.service = new WeakReference<>(sharedMemoryServiceBase);
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mMessenger.getBinder();
    }

    public SharedMemoryServiceBase() {
        IncomingHandler incomingHandler = new IncomingHandler(this);
        this.mMainHandler = incomingHandler;
        this.mMessenger = new Messenger(incomingHandler);
    }

    public static /* synthetic */ String access$000() {
        return MODULE_NAME;
    }
}
