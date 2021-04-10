package com.oculus.horizon.service;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Initializer;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsManager;
import com.oculus.security.basecomponent.OculusPublicService;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class ODHInterfaceService extends OculusPublicService {
    public static final String GET_OCULUS_USER_ID = "get_oculus_user_id";
    public static final String KEY_OCULUS_USER_ID = "OCULUS_USER_ID";
    public static final String MESSAGE_TYPE_KEY = "message_type";
    public static final String NULL_VALUE = "NULL";
    public static final String TAG = "ODHInterfaceService";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, ODHInterfaceService oDHInterfaceService) {
        oDHInterfaceService._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
    }

    @Override // X.AnonymousClass1U9
    public int doStartCommand(Intent intent, int i, int i2) {
        String stringExtra;
        if (intent != null && intent.getExtras() != null && intent.hasExtra("message_type") && (stringExtra = intent.getStringExtra("message_type")) != null && stringExtra.hashCode() == -1126786270 && stringExtra.equals(GET_OCULUS_USER_ID)) {
            getOculusUserId();
        }
        return 1;
    }

    private String formatValue(String str, String str2) {
        return AnonymousClass006.A07(str, ": ", str2);
    }

    private Map<String, String> getAllValues() {
        HashMap hashMap = new HashMap();
        hashMap.put(KEY_OCULUS_USER_ID, getOculusUserId());
        return hashMap;
    }

    @Nullable
    private String getOculusUserIdFromCredentialManager() {
        Credentials credentials = ((CredentialsManager) AnonymousClass0J2.A03(0, 199, this._UL_mInjectionContext)).getCredentials();
        if (credentials == null) {
            return null;
        }
        return credentials.mUserId;
    }

    public static final void _UL_injectMe(Context context, ODHInterfaceService oDHInterfaceService) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), oDHInterfaceService);
    }

    private String getOculusUserId() {
        String oculusUserIdFromCredentialManager = getOculusUserIdFromCredentialManager();
        if (oculusUserIdFromCredentialManager == null) {
            return NULL_VALUE;
        }
        return oculusUserIdFromCredentialManager;
    }

    @Override // X.AnonymousClass1U9, com.oculus.security.basecomponent.OculusPublicService
    @Initializer
    public void doCreate() {
        super.doCreate();
        _UL_injectMe((Context) this, this);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (Map.Entry<String, String> entry : getAllValues().entrySet()) {
            printWriter.println(AnonymousClass006.A07(entry.getKey(), ": ", entry.getValue()));
        }
    }
}
