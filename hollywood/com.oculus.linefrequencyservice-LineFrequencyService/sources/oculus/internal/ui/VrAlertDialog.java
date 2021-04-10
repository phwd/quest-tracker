package oculus.internal.ui;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.android.internal.app.AlertController;
import java.lang.reflect.Field;
import oculus.internal.ui.ClearActivityUtils;

public class VrAlertDialog extends AlertDialog {
    private static final String TAG = "VrAlertDialog";
    private BroadcastReceiver broadcastReceiver;
    private ClearActivityUtils.ForcePauseUtil mForcePauseUtil;
    private int mPlanBVisibility;
    private PowerManager mPowerManager;
    private VrUiInterface mVrLifecycle;
    private VrWindowAttachedStateListener mWindowAttachedListener;

    public VrAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mForcePauseUtil = new ClearActivityUtils.ForcePauseUtil();
        this.mPlanBVisibility = 0;
        this.broadcastReceiver = new BroadcastReceiver() {
            /* class oculus.internal.ui.VrAlertDialog.AnonymousClass2 */

            /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
            /* JADX WARNING: Removed duplicated region for block: B:14:0x0038  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onReceive(android.content.Context r5, android.content.Intent r6) {
                /*
                    r4 = this;
                    java.lang.String r0 = r6.getAction()
                    int r1 = r0.hashCode()
                    r2 = -2128145023(0xffffffff81271581, float:-3.0688484E-38)
                    r3 = 1
                    if (r1 == r2) goto L_0x001e
                    r2 = -1454123155(0xffffffffa953d76d, float:-4.7038264E-14)
                    if (r1 == r2) goto L_0x0014
                L_0x0013:
                    goto L_0x0028
                L_0x0014:
                    java.lang.String r1 = "android.intent.action.SCREEN_ON"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x0013
                    r0 = 0
                    goto L_0x0029
                L_0x001e:
                    java.lang.String r1 = "android.intent.action.SCREEN_OFF"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x0013
                    r0 = r3
                    goto L_0x0029
                L_0x0028:
                    r0 = -1
                L_0x0029:
                    if (r0 == 0) goto L_0x0038
                    if (r0 == r3) goto L_0x002e
                    goto L_0x0042
                L_0x002e:
                    oculus.internal.ui.VrAlertDialog r0 = oculus.internal.ui.VrAlertDialog.this
                    oculus.internal.ui.VrUiInterface r0 = oculus.internal.ui.VrAlertDialog.access$100(r0)
                    r0.onPause()
                    goto L_0x0042
                L_0x0038:
                    oculus.internal.ui.VrAlertDialog r0 = oculus.internal.ui.VrAlertDialog.this
                    oculus.internal.ui.VrUiInterface r0 = oculus.internal.ui.VrAlertDialog.access$100(r0)
                    r0.onResume()
                L_0x0042:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: oculus.internal.ui.VrAlertDialog.AnonymousClass2.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
        setIconAttribute(16843605);
        setForcePauseBackgroundActivity(true);
    }

    public VrAlertDialog(Context context) {
        this(context, 16974916);
        setForcePauseBackgroundActivity(true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState, new VrUiWrapper());
    }

    /* access modifiers changed from: package-private */
    public void onCreate(Bundle savedInstanceState, VrUiInterface wrapper) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_ON");
        filter.addAction("android.intent.action.SCREEN_OFF");
        getContext().registerReceiver(this.broadcastReceiver, filter);
        this.mVrLifecycle = wrapper;
        this.mVrLifecycle.onCreate(getContext(), getWindow());
        Context context = getContext();
        getContext();
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mWindowAttachedListener = new VrWindowAttachedStateListener(getWindow().getDecorView(), new Runnable() {
            /* class oculus.internal.ui.VrAlertDialog.AnonymousClass1 */

            public void run() {
                if (VrAlertDialog.this.mPowerManager.isScreenOn()) {
                    VrAlertDialog.this.mVrLifecycle.onResume();
                }
            }
        });
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.mForcePauseUtil.onStart(getContext());
        this.mWindowAttachedListener.start();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void stopVR() {
        this.mWindowAttachedListener.stop();
        if (this.mWindowAttachedListener.wasWindowAttached()) {
            this.mVrLifecycle.onPause();
        }
        getContext().unregisterReceiver(this.broadcastReceiver);
        this.mVrLifecycle.onDestroy();
        this.mForcePauseUtil.onStop(getContext());
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        stopVR();
        super.onStop();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mVrLifecycle.onWindowFocusChanged(hasFocus);
    }

    public void setPlanBEnabled(boolean enablePlanB) {
        if (!isShowing()) {
            boolean guardianAware = getContext().getPackageManager().hasSystemFeature("oculus.software.guardian");
            if (!enablePlanB) {
                this.mPlanBVisibility = 8;
            } else if (guardianAware) {
                this.mPlanBVisibility = 4;
            } else {
                this.mPlanBVisibility = 0;
            }
        } else {
            throw new RuntimeException("Attempting to set Plan B message while dialog is showing");
        }
    }

    public void setForcePauseBackgroundActivity(boolean shouldPauseActivity) {
        this.mForcePauseUtil.setForcePauseBackgroundActivity(shouldPauseActivity);
    }

    public static class Builder {
        private final AlertController.AlertParams P;

        public Builder(Context context) {
            this.P = new AlertController.AlertParams(context);
        }

        public Builder(Context context, int themeResId) {
            this(new ContextThemeWrapper(context, themeResId));
            VrBase.scaleDensity(context);
        }

        public Context getContext() {
            return this.P.mContext;
        }

        public Builder setTitle(int titleId) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mTitle = alertParams.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.P.mTitle = title;
            return this;
        }

        public Builder setCustomTitle(View customTitleView) {
            this.P.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(int messageId) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mMessage = alertParams.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.P.mMessage = message;
            return this;
        }

        public Builder setIcon(int iconId) {
            this.P.mIconId = iconId;
            return this;
        }

        public Builder setIcon(Drawable icon) {
            this.P.mIcon = icon;
            return this;
        }

        public Builder setIconAttribute(int attrId) {
            TypedValue out = new TypedValue();
            this.P.mContext.getTheme().resolveAttribute(attrId, out, true);
            this.P.mIconId = out.resourceId;
            return this;
        }

        public Builder setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(textId);
            this.P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mPositiveButtonText = text;
            alertParams.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(int textId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(textId);
            this.P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNegativeButtonText = text;
            alertParams.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(int textId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNeutralButtonText = alertParams.mContext.getText(textId);
            this.P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNeutralButtonText = text;
            alertParams.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(int itemsId, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(itemsId);
            this.P.mOnClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = items;
            alertParams.mOnClickListener = listener;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mAdapter = adapter;
            alertParams.mOnClickListener = listener;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mCursor = cursor;
            alertParams.mLabelColumn = labelColumn;
            alertParams.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(itemsId);
            AlertController.AlertParams alertParams2 = this.P;
            alertParams2.mOnCheckboxClickListener = listener;
            alertParams2.mCheckedItems = checkedItems;
            alertParams2.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = items;
            alertParams.mOnCheckboxClickListener = listener;
            alertParams.mCheckedItems = checkedItems;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mCursor = cursor;
            alertParams.mOnCheckboxClickListener = listener;
            alertParams.mIsCheckedColumn = isCheckedColumn;
            alertParams.mLabelColumn = labelColumn;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int itemsId, int checkedItem, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(itemsId);
            AlertController.AlertParams alertParams2 = this.P;
            alertParams2.mOnClickListener = listener;
            alertParams2.mCheckedItem = checkedItem;
            alertParams2.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mCursor = cursor;
            alertParams.mOnClickListener = listener;
            alertParams.mCheckedItem = checkedItem;
            alertParams.mLabelColumn = labelColumn;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = items;
            alertParams.mOnClickListener = listener;
            alertParams.mCheckedItem = checkedItem;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mAdapter = adapter;
            alertParams.mOnClickListener = listener;
            alertParams.mCheckedItem = checkedItem;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            this.P.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(int layoutResId) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mView = null;
            alertParams.mViewLayoutResId = layoutResId;
            alertParams.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpacingSpecified = false;
            return this;
        }

        @Deprecated
        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpacingSpecified = true;
            alertParams.mViewSpacingLeft = viewSpacingLeft;
            alertParams.mViewSpacingTop = viewSpacingTop;
            alertParams.mViewSpacingRight = viewSpacingRight;
            alertParams.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            this.P.mForceInverseBackground = useInverseBackground;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            this.P.mRecycleOnMeasure = enabled;
            return this;
        }

        public VrAlertDialog create() {
            VrAlertDialog dialog = new VrAlertDialog(this.P.mContext);
            AlertController mAlert = null;
            try {
                Field mAlertField = AlertDialog.class.getDeclaredField("mAlert");
                mAlertField.setAccessible(true);
                mAlert = (AlertController) mAlertField.get(dialog);
            } catch (IllegalAccessException | NoSuchFieldException e) {
            }
            this.P.apply(mAlert);
            dialog.setCancelable(this.P.mCancelable);
            if (this.P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.P.mOnCancelListener);
            dialog.setOnDismissListener(this.P.mOnDismissListener);
            if (this.P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.P.mOnKeyListener);
            }
            return dialog;
        }

        public VrAlertDialog show() {
            VrAlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
