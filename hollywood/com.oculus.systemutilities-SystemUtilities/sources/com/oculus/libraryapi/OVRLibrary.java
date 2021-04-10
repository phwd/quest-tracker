package com.oculus.libraryapi;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.library.model.App;
import com.oculus.library.model.InstallerCallback;
import com.oculus.library.model.InstallerCallbackReceiver;
import com.oculus.library.utils.app.AppConverter;
import com.oculus.library.utils.app.AssetConverter;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class OVRLibrary {
    private final AppConverter mAppConverter;
    private final AssetConverter mAssetConverter;
    protected final Context mContext;
    private final HashMap<Object, ArrayList<Object>> mDownloadListeners = new HashMap<>();
    private final HashMap<OnChangeListener, ArrayList<LibraryContentObserver>> mEntitlementListeners = new HashMap<>();
    private final HashMap<Object, ArrayList<Object>> mInstallListeners = new HashMap<>();

    public interface OnChangeListener {
        void onChange(String str);
    }

    /* access modifiers changed from: private */
    public static class LibraryContentObserver extends ContentObserver {
        final Handler handler;
        final OnChangeListener listener;
        final Uri uri;

        public LibraryContentObserver(Uri uri2, OnChangeListener listener2, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = listener2;
            this.handler = handler2;
        }

        public void onChange(boolean selfChange, Uri uri2) {
            this.listener.onChange(OCMSLibraryContract.getPackageFromAppsUri(uri2));
        }
    }

    public OVRLibrary(Context context) {
        this.mContext = context.getApplicationContext();
        this.mAppConverter = new AppConverter(this.mContext.getPackageManager());
        this.mAssetConverter = new AssetConverter();
    }

    public void refetch(ResultReceiver resultReceiver) {
        Bundle extras = new Bundle();
        extras.putParcelable("result_receiver", prepareResultReceiverForIPC(resultReceiver));
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "refetch", (String) null, extras);
    }

    public App getApp(String packageName) {
        Uri uri = OCMSLibraryContract.uriForPackage(packageName);
        Cursor cursor = this.mContext.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            Log.w("OVRLibrary", "null cursor received for query " + uri.toString());
            return null;
        }
        try {
            App[] apps = this.mAppConverter.extractAllFromCursor(cursor);
            if (apps.length != 1) {
                Log.w("OVRLibrary", "unexpected number of results in cursor");
                return null;
            }
            App app = apps[0];
            cursor.close();
            return app;
        } finally {
            cursor.close();
        }
    }

    public App[] getAllApps() {
        Cursor cursor = this.mContext.getContentResolver().query(OCMSLibraryContract.uriForAllPackages(), null, null, null, null);
        try {
            return this.mAppConverter.extractAllFromCursor(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public int save(App.Editor appEditor) {
        if (appEditor == null) {
            Log.w("OVRLibrary", "attempting to save null app");
            return 0;
        }
        ContentValues contentValues = this.mAppConverter.toContentValues(appEditor);
        this.mAppConverter.addContentValuePackingVersion(contentValues, 2);
        return this.mContext.getContentResolver().update(OCMSLibraryContract.uriForPackage(appEditor.packageName), contentValues, null, null);
    }

    public void downloadAndInstall(String packageName, RequestOrigin origin, InstallerCallback callback) {
        Bundle extras = new Bundle();
        if (callback != null) {
            extras.putParcelable("result_receiver", prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        if (origin != null) {
            extras.putString("extra_request_origin", origin.name());
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "install_package", packageName, extras);
    }

    public void uninstall(String packageName, RequestOrigin origin, InstallerCallback callback) {
        if (getPackageInfo(packageName) != null) {
            Bundle extras = new Bundle();
            if (callback != null) {
                extras.putParcelable("result_receiver", prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
            }
            if (origin != null) {
                extras.putString("extra_request_origin", origin.name());
            }
            this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "uninstall_package", packageName, extras);
        } else if (callback != null) {
            callback.onInstallerResult(InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.NOT_INSTALLED));
        }
    }

    public void cancelDownload(String packageName, InstallerCallback callback) {
        Bundle extras = new Bundle();
        if (callback != null) {
            extras.putParcelable("result_receiver", prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "cancel_download_package", packageName, extras);
    }

    private PackageInfo getPackageInfo(String packageName) {
        try {
            return this.mContext.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public void registerAppsChangeListener(OnChangeListener listener) {
        registerObserverForAppChangeUri(OCMSLibraryContract.uriForAllPackages(), listener);
    }

    private synchronized void registerObserverForAppChangeUri(Uri uri, OnChangeListener listener) {
        ArrayList<LibraryContentObserver> contentObservers = this.mEntitlementListeners.get(listener);
        if (contentObservers == null) {
            contentObservers = new ArrayList<>();
            this.mEntitlementListeners.put(listener, contentObservers);
        }
        Iterator<LibraryContentObserver> it = contentObservers.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().uri.equals(uri)) {
                    break;
                }
            } else {
                LibraryContentObserver contentObserver = new LibraryContentObserver(uri, listener, new Handler());
                this.mContext.getContentResolver().registerContentObserver(uri, true, contentObserver);
                contentObservers.add(contentObserver);
                break;
            }
        }
    }

    private ResultReceiver prepareResultReceiverForIPC(ResultReceiver resultReceiver) {
        if (resultReceiver == null) {
            return null;
        }
        Parcel out = Parcel.obtain();
        resultReceiver.writeToParcel(out, 0);
        out.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(out);
        out.recycle();
        return resultReceiver2;
    }
}
