package com.oculus.unifiedtelemetry.credentialsmanager;

import X.AbstractC0096Hu;
import X.AbstractC0385gk;
import X.AbstractC0386gl;
import X.Mu;
import X.QB;
import X.QC;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.multiuser.UserClassifier;

public class UnifiedTelemetryAuthAction implements AbstractC0385gk, QB {
    public static final String TAG = "UnifiedTelemetryAuthAction";
    public QC _UL_mInjectionContext;

    @Override // X.AbstractC0385gk
    public final void A3q(Context context, Intent intent, AbstractC0386gl glVar) {
        String action;
        QC qc = new QC(2, AbstractC0096Hu.get(context));
        this._UL_mInjectionContext = qc;
        if (((UserClassifier) AbstractC0096Hu.A03(1, 22, qc)).A01()) {
            UserHandle userHandle = (UserHandle) intent.getParcelableExtra(ServiceContract.EXTRA_USER);
            if (userHandle != null && ((UserClassifier) AbstractC0096Hu.A03(1, 22, this._UL_mInjectionContext)).A02(userHandle) && (action = intent.getAction()) != null) {
                int hashCode = action.hashCode();
                if (hashCode != 168677078) {
                    if (hashCode == 698177661 && action.equals(ServiceContract.BROADCAST_LOGIN)) {
                        UnifiedTelemetryCredentialsManager.A01((UnifiedTelemetryCredentialsManager) AbstractC0096Hu.A03(0, MinidumpReader.MODULE_FULL_SIZE, this._UL_mInjectionContext), true);
                        return;
                    }
                } else if (action.equals(ServiceContract.BROADCAST_LOGOUT)) {
                    UnifiedTelemetryCredentialsManager unifiedTelemetryCredentialsManager = (UnifiedTelemetryCredentialsManager) AbstractC0096Hu.A03(0, MinidumpReader.MODULE_FULL_SIZE, this._UL_mInjectionContext);
                    synchronized (unifiedTelemetryCredentialsManager) {
                        unifiedTelemetryCredentialsManager.mCredentials = new Credentials(null, null);
                        unifiedTelemetryCredentialsManager.mUnifiedTelemetryCredentialsPrefs.A03(null, null);
                    }
                    return;
                }
                Mu.A05(TAG, "Unsupported action: %s", action);
                return;
            }
            return;
        }
        throw new IllegalStateException("Not running as system user");
    }
}
