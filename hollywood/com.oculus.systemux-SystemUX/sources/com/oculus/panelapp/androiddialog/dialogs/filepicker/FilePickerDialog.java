package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.secure.fileprovider.FileUtil;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.localfilemanager.FileModel;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.FilePickerDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FilePickerDialog extends ConstraintLayout implements Dialog {
    private static final String CLOSE_ACTION = "close";
    private static final String FILES_SELECTED_ACTION = "filesSelected";
    private static final String TAG = LoggingUtil.tag(FilePickerDialog.class);
    final Context mContext;
    AndroidDialogPanelApp mPanelApp;
    private final ThumbnailLoader mThumbnailLoader;

    public FilePickerDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mThumbnailLoader = new ThumbnailLoader(context);
        Log.i(TAG, "Constructed FilePickerDialog.");
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public DialogDefinitionCustom getDialogDefinition() {
        return new DialogDefinitionCustom(CommonSystemDialog.FILE_PICKER.getDialogId(), this.mContext.getResources().getString(R.string.file_picker_title), this.mContext.getResources().getString(R.string.file_picker_preview_empty)).setPrimaryButton(new DialogButtonText(FILES_SELECTED_ACTION, this.mContext.getResources().getString(R.string.file_picker_accept_button))).setSecondaryButton(new DialogButtonText("close", this.mContext.getResources().getString(R.string.file_picker_cancel_button)));
    }

    @Nullable
    @VisibleForTesting
    static JSONObject getResult(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", FILES_SELECTED_ACTION);
            JSONArray jSONArray = new JSONArray();
            if (FileUtil.isFileOnSdCard(str)) {
                jSONArray.put(str);
                jSONObject.put(DialogResult.DIALOG_SELECTED_LIST_ITEM_IDS_KEY, jSONArray);
                return jSONObject;
            }
            Log.w(TAG, "file is not in the sdcard");
            return null;
        } catch (IOException e) {
            Log.e(TAG, "Unable to verify if the file is on the sdcard", e);
            return null;
        } catch (JSONException e2) {
            Log.e(TAG, "Unable to get result for file picker, cannot format dialog result JSON.", e2);
            return null;
        }
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, FilePickerDialogBinding filePickerDialogBinding) {
        this.mPanelApp = androidDialogPanelApp;
        FilePicker.init(this.mContext, this.mThumbnailLoader, this.mPanelApp, filePickerDialogBinding, new FilePicker.OnFileSelected() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePickerDialog$jSZxT_Z725TkaAJBvtHzGaEYc6M */

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.OnFileSelected
            public final void run(FileModel.FileData fileData) {
                FilePickerDialog.this.lambda$initialize$18$FilePickerDialog(fileData);
            }
        }, new FilePicker.OnCancel() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePickerDialog$qX9zAYbdi5Rh_ezcOLGlzpe8_CM */

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.OnCancel
            public final void run() {
                FilePickerDialog.this.lambda$initialize$19$FilePickerDialog();
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$initialize$18$FilePickerDialog(com.oculus.localfilemanager.FileModel.FileData r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0026
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0012 }
            java.lang.String r1 = r5.path     // Catch:{ IOException -> 0x0012 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0012 }
            java.lang.String r0 = r0.getCanonicalPath()     // Catch:{ IOException -> 0x0012 }
            org.json.JSONObject r5 = getResult(r0)     // Catch:{ IOException -> 0x0012 }
            goto L_0x0027
        L_0x0012:
            r0 = move-exception
            java.lang.String r1 = com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerDialog.TAG
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r5 = r5.path
            r2[r3] = r5
            java.lang.String r5 = "Unable to canonicalize the file path %s"
            java.lang.String r5 = java.lang.String.format(r5, r2)
            android.util.Log.e(r1, r5, r0)
        L_0x0026:
            r5 = 0
        L_0x0027:
            if (r5 != 0) goto L_0x002f
            com.oculus.panelapp.androiddialog.AndroidDialogPanelApp r5 = r4.mPanelApp
            r5.closeDialog()
            goto L_0x0034
        L_0x002f:
            com.oculus.panelapp.androiddialog.AndroidDialogPanelApp r0 = r4.mPanelApp
            r0.sendResult(r5)
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerDialog.lambda$initialize$18$FilePickerDialog(com.oculus.localfilemanager.FileModel$FileData):void");
    }

    public /* synthetic */ void lambda$initialize$19$FilePickerDialog() {
        this.mPanelApp.closeDialog();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        this.mThumbnailLoader.destroy();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        this.mPanelApp.closeDialog();
        return true;
    }
}
