package com.oculus.library.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.annotation.RequiresApi;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;

@RequiresApi(api = 21)
public class LibraryJobService extends JobService {
    private static final String TAG = "LibraryJobService";
    private InjectionContext _UL_mInjectionContext;

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    private static final void _UL_injectMe(Context context, LibraryJobService libraryJobService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), libraryJobService);
        } else {
            FbInjector.injectMe(LibraryJobService.class, libraryJobService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, LibraryJobService libraryJobService) {
        libraryJobService._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        BLog.i(TAG, "Library Update Job Started");
        ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).refetch(new ResultReceiver(new Handler()) {
            /* class com.oculus.library.job.LibraryJobService.AnonymousClass1 */

            public void onReceiveResult(int i, Bundle bundle) {
                LibraryJobService.this.jobFinished(jobParameters, i != 0);
            }
        });
        return true;
    }
}
