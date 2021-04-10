package com.oculus.ocms.library.receiver;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Initializer;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.receiver.BroadcastReceiverLike;
import com.facebook.ultralight.UL;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

public class PackageRemovedReceiver extends OculusSystemSecureBroadcastReceiver implements InjectableComponentWithoutContext {
    private static final String TAG = "PackageRemovedReceiver";
    private InjectionContext _UL_mInjectionContext;

    public PackageRemovedReceiver() {
        super(new String[0]);
    }

    private static final void _UL_injectMe(Context context, PackageRemovedReceiver packageRemovedReceiver) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe((InjectorLike) FbInjector.get(context), packageRemovedReceiver);
        } else {
            FbInjector.injectMe(PackageRemovedReceiver.class, (InjectableComponentWithoutContext) packageRemovedReceiver, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, PackageRemovedReceiver packageRemovedReceiver) {
        packageRemovedReceiver._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    @Initializer
    public void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike) {
        Uri data;
        _UL_injectMe(context, this);
        if (intent != null && TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_REMOVED") && !intent.getBooleanExtra("android.intent.extra.REPLACING", false) && (data = intent.getData()) != null) {
            String schemeSpecificPart = data.getSchemeSpecificPart();
            if (((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(schemeSpecificPart) != null) {
                new LibraryUpdateTask((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext), schemeSpecificPart).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(schemeSpecificPart.hashCode());
                }
            }
        }
    }

    private static class LibraryUpdateTask extends AsyncTask<Void, Void, Void> {
        private final OVRLibrary mOVRLibrary;
        private final String mPackageName;

        public LibraryUpdateTask(OVRLibrary oVRLibrary, String str) {
            this.mPackageName = str;
            this.mOVRLibrary = oVRLibrary;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            BLog.d(PackageRemovedReceiver.TAG, "Uninstalled: Clear on %s TrustedBinaryStatus", this.mPackageName);
            this.mOVRLibrary.save(new App.Editor(this.mPackageName).withTrustedBinaryStatus(""));
            return null;
        }
    }
}
