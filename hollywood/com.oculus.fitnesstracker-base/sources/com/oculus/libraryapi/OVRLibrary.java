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

    /* access modifiers changed from: package-private */
    public static class LibraryContentObserver extends ContentObserver {
        final Handler handler;
        final OnChangeListener listener;
        final Uri uri;

        public LibraryContentObserver(Uri uri2, OnChangeListener onChangeListener, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = onChangeListener;
            this.handler = handler2;
        }

        public final void onChange(boolean z, Uri uri2) {
            this.listener.onChange(OCMSLibraryContract.getPackageFromAppsUri(uri2));
        }
    }

    public OVRLibrary(Context context) {
        this.mContext = context.getApplicationContext();
        this.mAppConverter = new AppConverter(this.mContext.getPackageManager());
        this.mAssetConverter = new AssetConverter();
    }

    public final void refetch(ResultReceiver resultReceiver) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("result_receiver", prepareResultReceiverForIPC(resultReceiver));
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "refetch", (String) null, bundle);
    }

    public final App getApp(String str) {
        Uri uriForPackage = OCMSLibraryContract.uriForPackage(str);
        Cursor query = this.mContext.getContentResolver().query(uriForPackage, null, null, null, null);
        if (query == null) {
            Log.w("OVRLibrary", "null cursor received for query " + uriForPackage.toString());
            return null;
        }
        try {
            App[] extractAllFromCursor = this.mAppConverter.extractAllFromCursor(query);
            if (extractAllFromCursor.length != 1) {
                Log.w("OVRLibrary", "unexpected number of results in cursor");
                return null;
            }
            App app = extractAllFromCursor[0];
            query.close();
            return app;
        } finally {
            query.close();
        }
    }

    public final App[] getAllApps() {
        Cursor query = this.mContext.getContentResolver().query(OCMSLibraryContract.uriForAllPackages(), null, null, null, null);
        try {
            return this.mAppConverter.extractAllFromCursor(query);
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public final int save(App.Editor editor) {
        if (editor == null) {
            Log.w("OVRLibrary", "attempting to save null app");
            return 0;
        }
        ContentValues contentValues = this.mAppConverter.toContentValues(editor);
        this.mAppConverter.addContentValuePackingVersion(contentValues, 2);
        return this.mContext.getContentResolver().update(OCMSLibraryContract.uriForPackage(editor.packageName), contentValues, null, null);
    }

    public final void downloadAndInstall(String str, RequestOrigin requestOrigin, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        if (installerCallback != null) {
            bundle.putParcelable("result_receiver", prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
        }
        if (requestOrigin != null) {
            bundle.putString("extra_request_origin", requestOrigin.name());
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "install_package", str, bundle);
    }

    public final void uninstall(String str, RequestOrigin requestOrigin, InstallerCallback installerCallback) {
        if (getPackageInfo(str) != null) {
            Bundle bundle = new Bundle();
            if (installerCallback != null) {
                bundle.putParcelable("result_receiver", prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
            }
            if (requestOrigin != null) {
                bundle.putString("extra_request_origin", requestOrigin.name());
            }
            this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "uninstall_package", str, bundle);
        } else if (installerCallback != null) {
            installerCallback.onInstallerResult(InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.NOT_INSTALLED));
        }
    }

    public final void cancelDownload(String str, InstallerCallback installerCallback) {
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), "cancel_download_package", str, new Bundle());
    }

    private PackageInfo getPackageInfo(String str) {
        try {
            return this.mContext.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public synchronized void registerObserverForAppChangeUri(Uri uri, OnChangeListener onChangeListener) {
        ArrayList<LibraryContentObserver> arrayList = this.mEntitlementListeners.get(onChangeListener);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mEntitlementListeners.put(onChangeListener, arrayList);
        }
        Iterator<LibraryContentObserver> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().uri.equals(uri)) {
                return;
            }
        }
        LibraryContentObserver libraryContentObserver = new LibraryContentObserver(uri, onChangeListener, new Handler());
        this.mContext.getContentResolver().registerContentObserver(uri, true, libraryContentObserver);
        arrayList.add(libraryContentObserver);
    }

    private static ResultReceiver prepareResultReceiverForIPC(ResultReceiver resultReceiver) {
        if (resultReceiver == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }
}
