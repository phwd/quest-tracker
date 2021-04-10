package com.oculus.horizon.mediaupload;

import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import com.oculus.horizon.mediaupload.MC;

public class MediaChangeObserverService extends Service {
    public static final String TAG = "MediaChangeObserverService";
    public AnonymousClass0QC _UL_mInjectionContext;
    public ContentObserver mMediaObserver;

    public final int onStartCommand(Intent intent, int i, int i2) {
        if (!((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_mobile_save_to_fb_gaming_profile_horizon.enabled)) {
            stopSelf();
            return 2;
        }
        this.mMediaObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.horizon.mediaupload.MediaChangeObserverService.AnonymousClass1 */

            public final void onChange(boolean z) {
                MediaUploaderSyncService.A00(MediaChangeObserverService.this);
            }
        };
        ContentResolver contentResolver = getContentResolver();
        contentResolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.mMediaObserver);
        contentResolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.mMediaObserver);
        return 1;
    }

    public final void onCreate() {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, AnonymousClass0J2.get(this));
    }

    public final void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(this.mMediaObserver);
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        return null;
    }
}
