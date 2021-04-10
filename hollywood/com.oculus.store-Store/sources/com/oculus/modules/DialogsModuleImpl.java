package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.DialogsModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.ShellIPC;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogsModuleImpl extends DialogsModule {
    private static final String DIALOG_ACTION_KEY = "action";
    private static final String DIALOG_ID_KEY = "dialogId";
    private static final String DIALOG_PARAMETERS_KEY = "parameters";
    private static final String DIALOG_TYPE_COMMON_VALUE = "common";
    private static final String DIALOG_TYPE_KEY = "type";
    private static final String TAG = DialogsModuleImpl.class.getSimpleName();
    private final Context mContext;
    private boolean mIsIPCListenerRegistered = false;

    public DialogsModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DialogsModule
    public void showCommonDialogImpl(DialogsModule.CommonDialogID commonDialogId, JSONObject config, Resolver<Void> resolver) {
        registerIPCListener();
        Log.d(TAG, "showCommonDialogImpl(" + commonDialogId.toString() + ")");
        try {
            JSONObject commonDialogConfig = new JSONObject();
            commonDialogConfig.put(DIALOG_ID_KEY, commonDialogId.toString());
            commonDialogConfig.put(DIALOG_TYPE_KEY, DIALOG_TYPE_COMMON_VALUE);
            commonDialogConfig.put(DIALOG_PARAMETERS_KEY, config);
            ShellIPC.dialogShow(commonDialogConfig);
            resolver.resolve(null);
        } catch (JSONException jsonException) {
            resolver.reject(jsonException);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DialogsModule
    public void showDialogImpl(DialogsModule.DialogConfig config, Resolver<Void> resolver) {
        registerIPCListener();
        Log.d(TAG, "showDialogImpl");
        ShellIPC.dialogShow(config.convertToJSONObject());
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DialogsModule
    public void hideDialogImpl(String dialogId, Resolver<Void> resolver) {
        registerIPCListener();
        Log.d(TAG, "hideDialogImpl(" + dialogId + ")");
        ShellIPC.dialogHide();
        resolver.resolve(null);
    }

    private void registerIPCListener() {
        if (!this.mIsIPCListenerRegistered) {
            this.mIsIPCListenerRegistered = true;
            ShellIPC.registerListener("systemDialogResult", new ShellIPC.Listener() {
                /* class com.oculus.modules.DialogsModuleImpl.AnonymousClass1 */

                @Override // com.oculus.panellib.ShellIPC.Listener
                public boolean listen(String command) {
                    Log.d(DialogsModuleImpl.TAG, "listener(" + command + ")");
                    try {
                        JSONObject dialogResultJson = new JSONObject(command);
                        DialogsModule.DialogResult dialogResult = new DialogsModule.DialogResult();
                        dialogResult.action = dialogResultJson.getString(DialogsModuleImpl.DIALOG_ACTION_KEY);
                        dialogResult.dialogId = dialogResultJson.getString(DialogsModuleImpl.DIALOG_ID_KEY);
                        DialogsModuleImpl.this.emitOnDialogResult(dialogResult);
                        return false;
                    } catch (Exception e) {
                        Log.e(DialogsModuleImpl.TAG, "Unable to parse dialog result command: " + command, e);
                        return false;
                    }
                }
            });
        }
    }
}
