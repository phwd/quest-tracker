package oculus.internal.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import oculus.internal.ui.ClearActivityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class VrReactDialog {
    private static final String TAG = "VrReactDialog";
    private final Context mContext;
    private final DialogConfiguration mDialogConfiguration = new DialogConfiguration();
    private final DialogImplementation mDialogImplementation;

    public VrReactDialog(Context context) {
        this.mContext = context;
        this.mDialogImplementation = new DialogImplementation(context, new ReactDialogEventHandler() {
            /* class oculus.internal.ui.VrReactDialog.AnonymousClass1 */

            @Override // oculus.internal.ui.ReactDialogEventHandler
            public void onPrimaryButtonClicked() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class oculus.internal.ui.VrReactDialog.AnonymousClass1.AnonymousClass1 */

                    public void run() {
                        VrReactDialog.this.dismiss();
                        if (VrReactDialog.this.mDialogConfiguration.mPrimaryOnClickListener != null) {
                            VrReactDialog.this.mDialogConfiguration.mPrimaryOnClickListener.onClick(VrReactDialog.this.mDialogImplementation, -1);
                        }
                    }
                });
            }

            @Override // oculus.internal.ui.ReactDialogEventHandler
            public void onSecondaryButtonClicked() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class oculus.internal.ui.VrReactDialog.AnonymousClass1.AnonymousClass2 */

                    public void run() {
                        VrReactDialog.this.dismiss();
                        if (VrReactDialog.this.mDialogConfiguration.mSecondaryOnClickListener != null) {
                            VrReactDialog.this.mDialogConfiguration.mSecondaryOnClickListener.onClick(VrReactDialog.this.mDialogImplementation, -2);
                        }
                    }
                });
            }

            @Override // oculus.internal.ui.ReactDialogEventHandler
            public void onTertiaryButtonClicked() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class oculus.internal.ui.VrReactDialog.AnonymousClass1.AnonymousClass3 */

                    public void run() {
                        VrReactDialog.this.cancel();
                        if (VrReactDialog.this.mDialogConfiguration.mTertiaryOnClickListener != null) {
                            VrReactDialog.this.mDialogConfiguration.mTertiaryOnClickListener.onClick(VrReactDialog.this.mDialogImplementation, -3);
                        }
                    }
                });
            }

            @Override // oculus.internal.ui.ReactDialogEventHandler
            public void onIconButtonClicked() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class oculus.internal.ui.VrReactDialog.AnonymousClass1.AnonymousClass4 */

                    public void run() {
                        VrReactDialog.this.cancel();
                        if (VrReactDialog.this.mDialogConfiguration.mIconButtonOnClickListener != null) {
                            VrReactDialog.this.mDialogConfiguration.mIconButtonOnClickListener.onClick(VrReactDialog.this.mDialogImplementation, -3);
                        }
                    }
                });
            }
        });
    }

    public void setForcePauseBackgroundActivity(boolean shouldPauseActivity) {
        this.mDialogImplementation.setForcePauseBackgroundActivity(shouldPauseActivity);
    }

    public Window getWindow() {
        return this.mDialogImplementation.getWindow();
    }

    public void show() {
        this.mDialogImplementation.show();
    }

    public void cancel() {
        this.mDialogImplementation.cancel();
    }

    public void dismiss() {
        this.mDialogImplementation.dismiss();
    }

    public void flushConfigurationUpdates() {
        this.mDialogImplementation.setConfiguration(this.mDialogConfiguration);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        this.mDialogImplementation.setOnDismissListener(listener);
    }

    private String getStringResource(Integer id) {
        if (id == null) {
            return null;
        }
        return this.mContext.getResources().getString(id.intValue());
    }

    public void setTitle(Integer titleId) {
        setTitle(getStringResource(titleId));
    }

    public void setTitle(String title) {
        this.mDialogConfiguration.mTitle = title;
    }

    public void setBody(Integer bodyId) {
        setBody(getStringResource(bodyId));
    }

    public void setBody(String body) {
        this.mDialogConfiguration.mBody = body;
    }

    public void setInformationBox(Integer informationBoxId) {
        setInformationBox(getStringResource(informationBoxId));
    }

    public void setInformationBox(String informationBox) {
        this.mDialogConfiguration.mInformationBox = informationBox;
    }

    public void setPrimaryButton(Integer buttonId, DialogInterface.OnClickListener onClickListener) {
        setPrimaryButton(getStringResource(buttonId), onClickListener);
    }

    public void setPrimaryButton(String button, DialogInterface.OnClickListener onClickListener) {
        if (button == null && onClickListener != null) {
            Log.e(TAG, "Configuring a primary OnClickListener without any text for the button.");
        }
        DialogConfiguration dialogConfiguration = this.mDialogConfiguration;
        dialogConfiguration.mPrimaryButton = button;
        dialogConfiguration.mPrimaryOnClickListener = onClickListener;
    }

    public void setSecondaryButton(Integer buttonId, DialogInterface.OnClickListener onClickListener) {
        setSecondaryButton(getStringResource(buttonId), onClickListener);
    }

    public void setSecondaryButton(String button, DialogInterface.OnClickListener onClickListener) {
        if (button == null && onClickListener != null) {
            Log.e(TAG, "Configuring a secondary OnClickListener without any text for the button.");
        }
        DialogConfiguration dialogConfiguration = this.mDialogConfiguration;
        dialogConfiguration.mSecondaryButton = button;
        dialogConfiguration.mSecondaryOnClickListener = onClickListener;
    }

    public void setTertiaryButton(Integer buttonId, DialogInterface.OnClickListener onClickListener) {
        setTertiaryButton(getStringResource(buttonId), onClickListener);
    }

    public void setTertiaryButton(String button, DialogInterface.OnClickListener onClickListener) {
        if (button == null && onClickListener != null) {
            Log.e(TAG, "Configuring a tertiary OnClickListener without any text for the button.");
        }
        DialogConfiguration dialogConfiguration = this.mDialogConfiguration;
        dialogConfiguration.mTertiaryButton = button;
        dialogConfiguration.mTertiaryOnClickListener = onClickListener;
    }

    public void setIconButton(Icons icon, DialogInterface.OnClickListener onClickListener) {
        if (icon == null && onClickListener != null) {
            Log.e(TAG, "Configuring an icon OnClickListener without any icon for the button.");
        }
        DialogConfiguration dialogConfiguration = this.mDialogConfiguration;
        dialogConfiguration.mIconButtonImage = icon;
        dialogConfiguration.mIconButtonOnClickListener = onClickListener;
    }

    public void setPrimaryStyledAsSecondary(boolean value) {
        this.mDialogConfiguration.mStylePrimaryAsSecondary = Boolean.valueOf(value);
    }

    public void setProgressIndicator(boolean visible, Float progress) {
        DialogConfiguration dialogConfiguration = this.mDialogConfiguration;
        dialogConfiguration.mProgressIndicatorVisible = visible;
        if (progress == null) {
            dialogConfiguration.mProgressIndicatorProgress = null;
        } else {
            dialogConfiguration.mProgressIndicatorProgress = progress;
        }
    }

    public enum Icons {
        INFO("res/drawable/oc_icon_info_1_24_fff.png");
        
        private String path;

        private Icons(String path2) {
            this.path = path2;
        }

        public static String getUrl(Icons icon) {
            if (icon == null) {
                return null;
            }
            return "apk://oculus.platform/" + icon.path;
        }
    }

    public static class DialogConfiguration {
        public String mBody = null;
        public Icons mIconButtonImage = null;
        public DialogInterface.OnClickListener mIconButtonOnClickListener = null;
        public String mInformationBox = null;
        public String mPrimaryButton = null;
        public DialogInterface.OnClickListener mPrimaryOnClickListener = null;
        public Float mProgressIndicatorProgress = null;
        public boolean mProgressIndicatorVisible = false;
        public String mSecondaryButton = null;
        public DialogInterface.OnClickListener mSecondaryOnClickListener = null;
        public Boolean mStylePrimaryAsSecondary = null;
        public String mTertiaryButton = null;
        public DialogInterface.OnClickListener mTertiaryOnClickListener = null;
        public String mTitle = null;

        public String toJSONParcel() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("title", this.mTitle);
                parcel.put("body", this.mBody);
                parcel.put("informationBox", this.mInformationBox);
                if (this.mInformationBox != null) {
                    parcel.put("informationBoxIconUrl", Icons.getUrl(Icons.INFO));
                }
                parcel.put("primaryButton", this.mPrimaryButton);
                parcel.put("secondaryButton", this.mSecondaryButton);
                parcel.put("tertiaryButton", this.mTertiaryButton);
                parcel.put("iconButtonImageSourceUrl", Icons.getUrl(this.mIconButtonImage));
                parcel.put("stylePrimaryAsSecondary", this.mStylePrimaryAsSecondary);
                parcel.put("progressIndicatorVisible", this.mProgressIndicatorVisible);
                parcel.put("progressIndicatorProgress", this.mProgressIndicatorProgress);
                if (this.mProgressIndicatorProgress != null) {
                    int progressPercent = Math.max(0, Math.min(100, Math.round(this.mProgressIndicatorProgress.floatValue() * 100.0f)));
                    parcel.put("progressIndicatorProgressText", Integer.toString(progressPercent) + "%");
                }
            } catch (JSONException e) {
                Log.e(VrReactDialog.TAG, "Failed to parcel dialog configuration.", e);
            }
            return parcel.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class DialogImplementation extends Dialog implements Window.Callback {
        private final Context mContext;
        private final ClearActivityUtils.ForcePauseUtil mForcePauseUtil = new ClearActivityUtils.ForcePauseUtil();
        private final ReactDialogEventHandler mReactDialogEventHandler;
        private final VrUiWrapper mVrLifecycle = new VrUiWrapper();
        private VrWindowAttachedStateListener mWindowAttachedListener;

        public DialogImplementation(Context context, ReactDialogEventHandler reactDialogEventHandler) {
            super(context);
            this.mContext = context;
            this.mReactDialogEventHandler = reactDialogEventHandler;
        }

        public void setForcePauseBackgroundActivity(boolean shouldPauseActivity) {
            this.mForcePauseUtil.setForcePauseBackgroundActivity(shouldPauseActivity);
        }

        public void setConfiguration(DialogConfiguration dialogConfiguration) {
            this.mVrLifecycle.setConfiguration(dialogConfiguration);
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.mVrLifecycle.useReactLibrary(this.mReactDialogEventHandler);
            this.mVrLifecycle.onCreate(getContext(), getWindow());
            this.mWindowAttachedListener = new VrWindowAttachedStateListener(getWindow().getDecorView(), new Runnable() {
                /* class oculus.internal.ui.VrReactDialog.DialogImplementation.AnonymousClass1 */

                public void run() {
                    DialogImplementation.this.mVrLifecycle.onResume();
                }
            });
        }

        public void onStart() {
            this.mForcePauseUtil.onStart(getContext());
            this.mWindowAttachedListener.start();
            super.onStart();
        }

        public void onStop() {
            this.mWindowAttachedListener.stop();
            if (this.mWindowAttachedListener.wasWindowAttached()) {
                this.mVrLifecycle.onPause();
            }
            this.mVrLifecycle.onDestroy();
            this.mForcePauseUtil.onStop(getContext());
            super.onStop();
        }

        public void hide() {
            this.mVrLifecycle.onPause();
        }

        public void onWindowFocusChanged(boolean hasFocus) {
            super.onWindowFocusChanged(hasFocus);
            this.mVrLifecycle.onWindowFocusChanged(hasFocus);
        }
    }
}
