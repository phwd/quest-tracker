package com.oculus.modules;

import X.AnonymousClass006;
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
    public static final String DIALOG_ACTION_KEY = "action";
    public static final String DIALOG_ID_KEY = "dialogId";
    public static final String DIALOG_PARAMETERS_KEY = "parameters";
    public static final String DIALOG_SELECTED_LIST_ITEM_IDS_KEY = "listSelection";
    public static final String DIALOG_TYPE_COMMON_VALUE = "common";
    public static final String DIALOG_TYPE_CUSTOM_VALUE = "custom";
    public static final String DIALOG_TYPE_KEY = "type";
    public static final String TAG = "DialogsModuleImpl";
    public final Context mContext;
    public boolean mIsIPCListenerRegistered = false;

    private void registerIPCListener() {
        if (!this.mIsIPCListenerRegistered) {
            this.mIsIPCListenerRegistered = true;
            ShellIPC.registerListener("systemDialogResult", new ShellIPC.Listener() {
                /* class com.oculus.modules.DialogsModuleImpl.AnonymousClass1 */

                @Override // com.oculus.panellib.ShellIPC.Listener
                public boolean listen(String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        DialogsModule.DialogResult dialogResult = new DialogsModule.DialogResult();
                        dialogResult.action = jSONObject.getString("action");
                        dialogResult.dialogId = jSONObject.getString("dialogId");
                        JSONArray optJSONArray = jSONObject.optJSONArray("listSelection");
                        ArrayList arrayList = new ArrayList();
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                arrayList.add(optJSONArray.getString(i));
                            }
                        }
                        dialogResult.listSelection = arrayList;
                        DialogsModuleImpl.this.emitOnDialogResult(dialogResult);
                        return false;
                    } catch (Exception e) {
                        Log.e(DialogsModuleImpl.TAG, AnonymousClass006.A07("Unable to parse dialog result command: ", str), e);
                        return false;
                    }
                }
            });
        }
    }

    public DialogsModuleImpl(Context context) {
        this.mContext = context;
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    @Override // com.oculus.modules.codegen.DialogsModule
    public void hideDialogImpl(String str, Resolver<Void> resolver) {
        registerIPCListener();
        ShellIPC.dialogHide();
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.DialogsModule
    public void showCommonDialogImpl(DialogsModule.CommonDialogID commonDialogID, JSONObject jSONObject, Resolver<Void> resolver) {
        registerIPCListener();
        String obj = commonDialogID.toString();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("dialogId", obj);
            jSONObject2.put("type", "common");
            jSONObject2.put("parameters", jSONObject);
            ShellIPC.dialogShow(jSONObject2);
            resolver.resolve(null);
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DialogsModule
    public void showDialogImpl(DialogsModule.DialogConfig dialogConfig, Resolver<Void> resolver) {
        registerIPCListener();
        try {
            JSONObject convertToJSONObject = dialogConfig.convertToJSONObject();
            convertToJSONObject.put("type", "custom");
            ShellIPC.dialogShow(convertToJSONObject);
            resolver.resolve(null);
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }
}
