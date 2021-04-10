package com.oculus.panelapp.anytimeui.dialogs.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.panelapp.anytimeui.R;

public class SystemDialogStandardLayout extends SystemDialog {
    private static final String TAG = "SystemDialogStandardLayout";
    private Button mAcceptButton;
    private String mAcceptResult;
    private String mBackResult;
    private TextView mCalloutTextView;
    private Button mCancelButton;
    private String mCancelResult;
    private TextView mContentTextView;
    private View mCustomView;
    private Button mGearButton;
    private String mGearResult;
    private Button mMiddleButton;
    private String mMiddleResult;

    public static abstract class OnButtonClickedListener {
        public abstract void onAccept();

        public abstract void onCancel();

        public void onGearClicked() {
        }

        public void onMiddle() {
        }
    }

    public SystemDialogStandardLayout(Context context) {
        this(context, null);
    }

    public SystemDialogStandardLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SystemDialogStandardLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    public void setOnCloseListener(final OnButtonClickedListener onButtonClickedListener) {
        if (onButtonClickedListener != null) {
            this.mCancelButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.AnonymousClass1 */

                public void onClick(View view) {
                    onButtonClickedListener.onCancel();
                }
            });
            this.mMiddleButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.AnonymousClass2 */

                public void onClick(View view) {
                    onButtonClickedListener.onMiddle();
                }
            });
            this.mAcceptButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.AnonymousClass3 */

                public void onClick(View view) {
                    onButtonClickedListener.onAccept();
                }
            });
            this.mGearButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.dialogs.views.SystemDialogStandardLayout.AnonymousClass4 */

                public void onClick(View view) {
                    onButtonClickedListener.onGearClicked();
                }
            });
            return;
        }
        this.mCancelButton.setOnClickListener(null);
        this.mMiddleButton.setOnClickListener(null);
        this.mAcceptButton.setOnClickListener(null);
        this.mGearButton.setOnClickListener(null);
    }

    public void setAcceptText(@Nullable CharSequence charSequence) {
        updateTextView(this.mAcceptButton, charSequence);
    }

    public void setMiddleText(@Nullable CharSequence charSequence) {
        updateTextView(this.mMiddleButton, charSequence);
    }

    public void setCancelText(@Nullable CharSequence charSequence) {
        updateTextView(this.mCancelButton, charSequence);
    }

    public void setContentText(@Nullable CharSequence charSequence) {
        if (this.mContentTextView == null) {
            this.mContentTextView = (TextView) findViewById(R.id.system_dialog_standard_layout_content);
        }
        updateTextView(this.mContentTextView, charSequence);
    }

    public void setContentGravity(int i) {
        if (this.mContentTextView == null) {
            this.mContentTextView = (TextView) findViewById(R.id.system_dialog_standard_layout_content);
        }
        this.mContentTextView.setGravity(i);
    }

    public void setCalloutText(@Nullable CharSequence charSequence) {
        if (this.mCalloutTextView == null) {
            this.mCalloutTextView = (TextView) findViewById(R.id.system_dialog_standard_layout_callout);
        }
        updateTextView(this.mCalloutTextView, charSequence);
    }

    public void setCalloutGravity(int i) {
        if (this.mCalloutTextView == null) {
            this.mCalloutTextView = (TextView) findViewById(R.id.system_dialog_standard_layout_callout);
        }
        this.mCalloutTextView.setGravity(i);
    }

    private void updateTextView(TextView textView, @Nullable CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
            textView.setText("");
            return;
        }
        textView.setVisibility(0);
        textView.setText(charSequence);
    }

    @Nullable
    public String getCancelResult() {
        return this.mCancelResult;
    }

    @Nullable
    public String getMiddleResult() {
        return this.mMiddleResult;
    }

    @Nullable
    public String getAcceptResult() {
        return this.mAcceptResult;
    }

    @Nullable
    public String getBackResult() {
        return this.mBackResult;
    }

    @Nullable
    public String getGearResult() {
        return this.mGearResult;
    }

    @Nullable
    public View getCustomView() {
        return this.mCustomView;
    }

    private void init(@Nullable AttributeSet attributeSet, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        layoutInflater.inflate(R.layout.system_dialog_standard_layout_dialog, (ViewGroup) this, true);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SystemDialogStandardLayout, i, 0);
        ((TextView) findViewById(R.id.system_dialog_standard_layout_title)).setText(obtainStyledAttributes.getText(R.styleable.SystemDialogStandardLayout_titleText));
        setContentText(obtainStyledAttributes.getString(R.styleable.SystemDialogStandardLayout_contentText));
        setCalloutText(obtainStyledAttributes.getString(R.styleable.SystemDialogStandardLayout_calloutText));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.SystemDialogStandardLayout_customLayout, 0);
        if (resourceId != 0) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.system_dialog_standard_custom_layout);
            this.mCustomView = layoutInflater.inflate(resourceId, linearLayout);
            linearLayout.setVisibility(0);
        }
        this.mCancelButton = (Button) findViewById(R.id.system_dialog_standard_layout_button_cancel);
        this.mCancelResult = obtainStyledAttributes.getString(R.styleable.SystemDialogStandardLayout_cancelResult);
        setCancelText(obtainStyledAttributes.getText(R.styleable.SystemDialogStandardLayout_cancelText));
        this.mMiddleButton = (Button) findViewById(R.id.system_dialog_standard_layout_button_middle);
        this.mMiddleResult = obtainStyledAttributes.getString(R.styleable.SystemDialogStandardLayout_middleResult);
        setMiddleText(obtainStyledAttributes.getText(R.styleable.SystemDialogStandardLayout_middleText));
        this.mAcceptButton = (Button) findViewById(R.id.system_dialog_standard_layout_button_accept);
        this.mAcceptResult = obtainStyledAttributes.getString(R.styleable.SystemDialogStandardLayout_acceptResult);
        setAcceptText(obtainStyledAttributes.getText(R.styleable.SystemDialogStandardLayout_acceptText));
        this.mBackResult = obtainStyledAttributes.getString(R.styleable.SystemDialogStandardLayout_backResult);
        this.mGearButton = (Button) findViewById(R.id.system_dialog_standard_layout_gear_btn);
        this.mGearResult = obtainStyledAttributes.getString(R.styleable.SystemDialogStandardLayout_gearResult);
        this.mGearButton.setVisibility(TextUtils.isEmpty(getGearResult()) ? 8 : 0);
        if (this.mCancelButton.getVisibility() == 8 && this.mMiddleButton.getVisibility() == 8 && this.mAcceptButton.getVisibility() == 8) {
            findViewById(R.id.system_dialog_button_bar_container).setVisibility(8);
        }
        parseTags(obtainStyledAttributes.getResourceId(R.styleable.SystemDialogStandardLayout_tags, 0));
        obtainStyledAttributes.recycle();
    }

    private void parseTags(int i) {
        if (i != 0) {
            TextView textView = (TextView) findViewById(R.id.system_dialog_standard_layout_title);
            String[] stringArray = getResources().getStringArray(i);
            for (String str : stringArray) {
                char c = 65535;
                switch (str.hashCode()) {
                    case -226892067:
                        if (str.equals("[Padding=Small]")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -126954059:
                        if (str.equals("[Title=85%]")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 536475859:
                        if (str.equals("[TitleLeftBottomMargin=48,24]")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 602019470:
                        if (str.equals("[ContentTextGravity=Center]")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 954335988:
                        if (str.equals("[HideMiddleButtonOnLoad]")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1675639054:
                        if (str.equals("[TitleBottomMargin=6]")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1746904054:
                        if (str.equals("[ButtonBarMargin=0]")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        textView.setTextSize(textView.getTextSize() * 0.85f);
                        break;
                    case 1:
                        ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, 0, 0, 6);
                        break;
                    case 2:
                        ((LinearLayout.LayoutParams) ((LinearLayout) findViewById(R.id.system_dialog_button_bar_container)).getLayoutParams()).setMargins(0, 0, 0, 0);
                        break;
                    case 3:
                        setContentGravity(17);
                        break;
                    case 4:
                        Resources resources = getResources();
                        setPadding((int) resources.getDimension(R.dimen.system_dialog_standard_layout_padding_small_top), (int) resources.getDimension(R.dimen.system_dialog_standard_layout_padding_small_left), (int) resources.getDimension(R.dimen.system_dialog_standard_layout_padding_small_right), (int) resources.getDimension(R.dimen.system_dialog_standard_layout_padding_small_bottom));
                        break;
                    case 5:
                        this.mMiddleButton.setVisibility(8);
                        break;
                    case 6:
                        ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(48, 0, 0, 24);
                        break;
                    default:
                        Log.e(TAG, "Unrecognized tag: " + str);
                        break;
                }
            }
        }
    }
}
