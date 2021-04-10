package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.DialogsModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.ShellIPC;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogsModuleImpl extends DialogsModule {
    private static final String DIALOG_ACTION_KEY = "action";
    private static final String DIALOG_ID_KEY = "dialogId";
    private static final String DIALOG_PARAMETERS_KEY = "parameters";
    private static final String DIALOG_SELECTED_LIST_ITEM_IDS_KEY = "listSelection";
    private static final String DIALOG_TYPE_COMMON_VALUE = "common";
    private static final String DIALOG_TYPE_CUSTOM_VALUE = "custom";
    private static final String DIALOG_TYPE_KEY = "type";
    private static final String TAG = "DialogsModuleImpl";
    private final Context mContext;
    private boolean mIsIPCListenerRegistered = false;

    public DialogsModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.DialogsModule
    public final void showCommonDialogImpl(DialogsModule.CommonDialogID commonDialogID, JSONObject jSONObject, Resolver<Void> resolver) {
        registerIPCListener();
        String str = TAG;
        Log.d(str, "showCommonDialogImpl(" + commonDialogID.toString() + ")");
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DIALOG_ID_KEY, commonDialogID.toString());
            jSONObject2.put(DIALOG_TYPE_KEY, DIALOG_TYPE_COMMON_VALUE);
            jSONObject2.put(DIALOG_PARAMETERS_KEY, jSONObject);
            ShellIPC.dialogShow(jSONObject2);
            resolver.resolve(null);
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DialogsModule
    public final void showDialogImpl(DialogsModule.DialogConfig dialogConfig, Resolver<Void> resolver) {
        registerIPCListener();
        Log.d(TAG, "showDialogImpl");
        try {
            JSONObject convertToJSONObject = dialogConfig.convertToJSONObject();
            convertToJSONObject.put(DIALOG_TYPE_KEY, DIALOG_TYPE_CUSTOM_VALUE);
            ShellIPC.dialogShow(convertToJSONObject);
            resolver.resolve(null);
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DialogsModule
    public final void hideDialogImpl(String str, Resolver<Void> resolver) {
        registerIPCListener();
        String str2 = TAG;
        Log.d(str2, "hideDialogImpl(" + str + ")");
        ShellIPC.dialogHide();
        resolver.resolve(null);
    }

    private void registerIPCListener() {
        if (!this.mIsIPCListenerRegistered) {
            this.mIsIPCListenerRegistered = true;
            ShellIPC.registerListener("systemDialogResult", new ShellIPC.Listener() {
                /* class com.oculus.modules.DialogsModuleImpl.AnonymousClass1 */

                public boolean listen(String str) {
                    String str2 = DialogsModuleImpl.TAG;
                    Log.d(str2, "listener(" + str + ")");
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        DialogsModule.DialogResult dialogResult = new DialogsModule.DialogResult();
                        dialogResult.action = jSONObject.getString(DialogsModuleImpl.DIALOG_ACTION_KEY);
                        dialogResult.dialogId = jSONObject.getString(DialogsModuleImpl.DIALOG_ID_KEY);
                        JSONArray optJSONArray = jSONObject.optJSONArray(DialogsModuleImpl.DIALOG_SELECTED_LIST_ITEM_IDS_KEY);
                        ArrayList arrayList = new ArrayList();
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                arrayList.add(optJSONArray.getString(i));
                            }
                        }
                        dialogResult.listSelection = arrayList;
                        DialogsModuleImpl.this.emitOnDialogResult(dialogResult);
                    } catch (Exception e) {
                        String str3 = DialogsModuleImpl.TAG;
                        Log.e(str3, "Unable to parse dialog result command: " + str, e);
                    }
                    return false;
                }
            });
        }
    }
}
