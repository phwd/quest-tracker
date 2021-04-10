package com.oculus.horizon.mediaupload;

import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.C02600ao;
import X.C02800bY;
import X.C02930bl;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.oculus.horizon.mediaupload.MC;
import com.oculus.ipc.common.ParcelableCallbackReceiver;
import com.oculus.mediaupload.model.MediaUploaderCallback;
import com.oculus.mediaupload.model.MediaUploaderIntentValidator;
import com.oculus.mediaupload.model.MediaUploaderResult;

public class MediaUploaderSyncService extends JobService {
    public static final Class<?> TAG = MediaUploaderSyncService.class;
    public AnonymousClass0QC _UL_mInjectionContext;

    public static void A00(Context context) {
        Intent intent = new Intent(context, MediaUploaderService.class);
        ParcelableCallbackReceiver parcelableCallbackReceiver = new ParcelableCallbackReceiver(new MediaUploaderCallbackPrinter());
        Parcel obtain = Parcel.obtain();
        parcelableCallbackReceiver.mReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        obtain.recycle();
        intent.putExtra("result_receiver", (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain));
        intent.putExtra(MediaUploaderIntentValidator.REQUEST_TYPE_KEY, MediaUploaderIntentValidator.Type.SYNC_TO_FB.name());
        try {
            C02800bY.A02(intent, context, AnonymousClass006.A05(MediaUploaderSyncService.class.getSimpleName(), ":sendToMediaUploader()"));
            C02600ao.A00().A06().A00(intent, context);
        } catch (C02930bl e) {
            AnonymousClass0NO.A03(MediaUploaderSyncService.class, "Error sending oculus sync intent.", e);
        }
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_mobile_save_to_fb_gaming_profile_horizon.enabled)) {
            A00(this);
        }
        return false;
    }

    public final void onCreate() {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, AnonymousClass0J2.get(this));
        super.onCreate();
    }

    public static class MediaUploaderCallbackPrinter implements MediaUploaderCallback {
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // com.oculus.ipc.common.ParcelableCallbackReceiver.Callback
        public final void onResult(MediaUploaderResult mediaUploaderResult) {
            mediaUploaderResult.toString();
        }
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
