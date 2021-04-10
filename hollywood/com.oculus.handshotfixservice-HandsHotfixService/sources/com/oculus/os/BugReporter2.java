package com.oculus.os;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import oculus.internal.Constants;
import oculus.internal.FileUtils;
import oculus.internal.Gatekeeper;
import oculus.internal.audio.IVrAudioDataDumpManager;
import oculus.internal.audio.IVrAudioDataDumpManagerClient;

public class BugReporter2 {
    private static final String AUDIO_DATA_DUMP_MANAGER_SERVICE_NAME = "VrAudioDataDumpManager";
    private static final String AUTHORITY = "com.oculus.bugreporter.provider";
    private static final String EXTRAMEDIA = "extraMedia";
    private static final String PASTAUDIODATA = "pastAudioData";
    private static final String SCREENSHOT = "screenshot";
    private static final String TAG = "BugReporter2";
    private IBinder mAudioDataDumpManagerBinder = null;
    private VrAudioDataDumpManagerClient mAudioDataDumpManagerClient = new VrAudioDataDumpManagerClient();
    private IVrAudioDataDumpManager mAudioDataDumpManagerService = null;
    private final String mBugId;
    private Context mContext;
    private final ThreadPoolExecutor mExecutor = new ScheduledThreadPoolExecutor(1);
    private boolean mIsPastAudioDataExpected = false;
    private boolean mIsScreenshotExpected = false;

    private BugReporter2(Context context, String bugId) {
        this.mContext = context;
        this.mBugId = bugId;
    }

    public static BugReporter2 createBugReport(Context context) {
        Uri report = context.getContentResolver().insert(Uri.parse("content://com.oculus.bugreporter.provider/report"), new ContentValues());
        if (report != null) {
            return new BugReporter2(context, report.getLastPathSegment());
        }
        Log.e(TAG, "Failed to create bug report");
        return null;
    }

    public static BugReporter2 createForTesting(Context context, String bugId) {
        return new BugReporter2(context, bugId);
    }

    public static boolean isBugReporter2Enabled() {
        return new Gatekeeper(10).isEnabled();
    }

    public void storeScreenshot(final Bitmap screenshot) {
        if (screenshot != null) {
            this.mIsScreenshotExpected = true;
        }
        this.mExecutor.execute(new Runnable() {
            /* class com.oculus.os.BugReporter2.AnonymousClass1 */

            public void run() {
                ParcelFileDescriptor fd = null;
                try {
                    ContentResolver contentResolver = BugReporter2.this.mContext.getContentResolver();
                    fd = contentResolver.openFileDescriptor(Uri.parse("content://com.oculus.bugreporter.provider/report/screenshot/id/" + BugReporter2.this.mBugId), "rw");
                    OutputStream os = new ParcelFileDescriptor.AutoCloseOutputStream(fd);
                    if (screenshot != null) {
                        screenshot.compress(Bitmap.CompressFormat.PNG, 80, os);
                    } else {
                        Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888).compress(Bitmap.CompressFormat.PNG, 80, os);
                    }
                } catch (Exception e) {
                    Log.e(BugReporter2.TAG, "Couldn't store screenshot", e);
                } catch (Throwable th) {
                    FileUtils.closeQuietly((ParcelFileDescriptor) null);
                    throw th;
                }
                FileUtils.closeQuietly(fd);
                ContentValues values = new ContentValues();
                values.put(BugReporter2.SCREENSHOT, (Boolean) true);
                ContentResolver contentResolver2 = BugReporter2.this.mContext.getContentResolver();
                contentResolver2.update(Uri.parse("content://com.oculus.bugreporter.provider/report/id/" + BugReporter2.this.mBugId), values, "", new String[0]);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized IVrAudioDataDumpManager getAudioDataDumpManager() {
        if (this.mAudioDataDumpManagerService == null || this.mAudioDataDumpManagerBinder == null || !this.mAudioDataDumpManagerBinder.pingBinder() || !this.mAudioDataDumpManagerBinder.isBinderAlive()) {
            this.mAudioDataDumpManagerBinder = ServiceManager.checkService(AUDIO_DATA_DUMP_MANAGER_SERVICE_NAME);
            if (this.mAudioDataDumpManagerBinder == null) {
                Log.e(TAG, "Failed to get binder for IVrAudioDataDumpManager");
                this.mAudioDataDumpManagerService = null;
            }
            this.mAudioDataDumpManagerService = IVrAudioDataDumpManager.Stub.asInterface(this.mAudioDataDumpManagerBinder);
        }
        return this.mAudioDataDumpManagerService;
    }

    public void maybeStorePastAudioData() {
        this.mIsPastAudioDataExpected = true;
        IVrAudioDataDumpManager manager = getAudioDataDumpManager();
        if (manager != null) {
            try {
                manager.registerClient(this.mAudioDataDumpManagerClient);
                manager.zipAudioDataDumpFiles();
            } catch (RemoteException e) {
                Log.e(TAG, "Failed binder with VrAudioDataDumpManager", e);
                this.mIsPastAudioDataExpected = false;
            }
        } else {
            this.mIsPastAudioDataExpected = false;
        }
    }

    public void storeExtraFile(final File file) {
        this.mExecutor.execute(new Runnable() {
            /* class com.oculus.os.BugReporter2.AnonymousClass2 */

            public void run() {
                ParcelFileDescriptor fd = null;
                try {
                    ContentResolver contentResolver = BugReporter2.this.mContext.getContentResolver();
                    fd = contentResolver.openFileDescriptor(Uri.parse("content://com.oculus.bugreporter.provider/report/extraMedia/name/" + file.getName() + "/id/" + BugReporter2.this.mBugId), "rw");
                    FileOutputStream os = new ParcelFileDescriptor.AutoCloseOutputStream(fd);
                    FileInputStream is = new FileInputStream(file);
                    is.getChannel().transferTo(0, file.length(), os.getChannel());
                    is.close();
                } catch (Exception e) {
                    Log.e(BugReporter2.TAG, "Couldn't store extra file", e);
                } catch (Throwable th) {
                    FileUtils.closeQuietly(fd);
                    throw th;
                }
                FileUtils.closeQuietly(fd);
                ContentValues values = new ContentValues();
                values.put(BugReporter2.EXTRAMEDIA, file.getName());
                ContentResolver contentResolver2 = BugReporter2.this.mContext.getContentResolver();
                contentResolver2.update(Uri.parse("content://com.oculus.bugreporter.provider/report/id/" + BugReporter2.this.mBugId), values, "", new String[0]);
            }
        });
    }

    public Intent getLaunchIntent(String source, String sourceApp) {
        String params = "bug_id=" + this.mBugId;
        if (source != null) {
            params = params + "&source=" + source;
        }
        if (sourceApp != null) {
            params = params + "&source_app=" + sourceApp;
        }
        if (this.mIsScreenshotExpected) {
            params = params + "&is_screenshot_expected=1";
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(Constants.SHELL_PACKAGE, "com.oculus.vrshell.MainActivity"));
        intent.putExtra("uri", "com.oculus.bugreporter/com.oculus.bugreporter.MainActivity?" + params);
        intent.putExtra("is_past_audio_data_expected", this.mIsPastAudioDataExpected);
        intent.setData(Uri.parse("apk://com.oculus.vrshell.desktop"));
        return intent;
    }

    /* access modifiers changed from: private */
    public final class VrAudioDataDumpManagerClient extends IVrAudioDataDumpManagerClient.Stub {
        private static final String ZIPPED_NAME = "/data/diag_logs/audio_data_dump.zip";

        private VrAudioDataDumpManagerClient() {
        }

        @Override // oculus.internal.audio.IVrAudioDataDumpManagerClient
        public void onZippingComplete() {
            BugReporter2.this.mExecutor.execute(new Runnable() {
                /* class com.oculus.os.BugReporter2.VrAudioDataDumpManagerClient.AnonymousClass1 */

                public void run() {
                    ParcelFileDescriptor fd = null;
                    try {
                        ContentResolver contentResolver = BugReporter2.this.mContext.getContentResolver();
                        fd = contentResolver.openFileDescriptor(Uri.parse("content://com.oculus.bugreporter.provider/report/pastAudioData/id/" + BugReporter2.this.mBugId), "rw");
                        FileOutputStream os = new ParcelFileDescriptor.AutoCloseOutputStream(fd);
                        File zippedFile = new File(VrAudioDataDumpManagerClient.ZIPPED_NAME);
                        FileInputStream is = new FileInputStream(zippedFile);
                        is.getChannel().transferTo(0, zippedFile.length(), os.getChannel());
                        is.close();
                    } catch (Exception e) {
                        Log.e(BugReporter2.TAG, "Couldn't store past audio data", e);
                    } catch (Throwable th) {
                        FileUtils.closeQuietly(fd);
                        throw th;
                    }
                    FileUtils.closeQuietly(fd);
                    IVrAudioDataDumpManager manager = BugReporter2.this.getAudioDataDumpManager();
                    if (manager != null) {
                        try {
                            manager.unregisterClient(BugReporter2.this.mAudioDataDumpManagerClient);
                        } catch (RemoteException e2) {
                            Log.e(BugReporter2.TAG, "Failed to unregister with VrAudioDataDumpManager", e2);
                        }
                    }
                    ContentValues values = new ContentValues();
                    values.put(BugReporter2.PASTAUDIODATA, (Boolean) true);
                    ContentResolver contentResolver2 = BugReporter2.this.mContext.getContentResolver();
                    contentResolver2.update(Uri.parse("content://com.oculus.bugreporter.provider/report/id/" + BugReporter2.this.mBugId), values, "", new String[0]);
                }
            });
        }
    }
}
