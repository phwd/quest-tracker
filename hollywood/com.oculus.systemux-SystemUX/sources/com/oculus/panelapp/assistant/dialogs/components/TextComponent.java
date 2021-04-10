package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.assistant.R;
import org.json.JSONException;
import org.json.JSONObject;

public class TextComponent extends BaseComponent {
    private final AttributeSet mAttrs;
    private final int mDefStyleAttr;
    private OCTextView mTextView;

    public TextComponent(@NonNull Context context) {
        super(context);
        this.mDefStyleAttr = 0;
        this.mAttrs = null;
    }

    public TextComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDefStyleAttr = 0;
        this.mAttrs = attributeSet;
    }

    public TextComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefStyleAttr = i;
        this.mAttrs = attributeSet;
    }

    private OCTextView createTextView(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.mAttrs, R.styleable.TextComponent, this.mDefStyleAttr, 0);
        if (i == 0) {
            i = obtainStyledAttributes.getResourceId(R.styleable.TextComponent_text_style, R.style.Body1);
        }
        this.mTextView = new OCTextView(getContext(), null, i);
        this.mTextView.setText(obtainStyledAttributes.getText(R.styleable.TextComponent_text));
        obtainStyledAttributes.recycle();
        return this.mTextView;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mTextView = createTextView(context, 0);
        return this.mTextView;
    }

    public void setTextStyle(@StyleRes int i) {
        this.mTextView = new OCTextView(getContext(), null, this.mDefStyleAttr, i);
        setView(this.mTextView);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        String str;
        if (jSONObject.has(AssistantComponentContract.Components.TextComponent.STYLE)) {
            String string = jSONObject.getString(AssistantComponentContract.Components.TextComponent.STYLE);
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != 93911760) {
                if (hashCode != 110371416) {
                    switch (hashCode) {
                        case 3273:
                            if (string.equals(AssistantComponentContract.Components.TextComponent.Styles.H1)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 3274:
                            if (string.equals(AssistantComponentContract.Components.TextComponent.Styles.H2)) {
                                c = 2;
                                break;
                            }
                            break;
                        case 3275:
                            if (string.equals(AssistantComponentContract.Components.TextComponent.Styles.H3)) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                } else if (string.equals("title")) {
                    c = 0;
                }
            } else if (string.equals(AssistantComponentContract.Components.TextComponent.Styles.BODY2)) {
                c = 4;
            }
            if (c == 0) {
                setTextStyle(R.style.Title);
            } else if (c == 1) {
                setTextStyle(R.style.H1);
            } else if (c == 2) {
                setTextStyle(R.style.H2);
            } else if (c == 3) {
                setTextStyle(R.style.H3);
            } else if (c != 4) {
                setTextStyle(R.style.Body1);
            } else {
                setTextStyle(R.style.Body2);
            }
        }
        super.onApplyJson(jSONObject);
        if (jSONObject.has("text")) {
            str = jSONObject.getString("text");
        } else {
            str = jSONObject.has(AssistantComponentContract.Components.TextComponent.VALUE) ? jSONObject.getString(AssistantComponentContract.Components.TextComponent.VALUE) : "";
        }
        setText(str);
        this.mTextView.setGravity(parseGravity(jSONObject));
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null) {
            this.mTextView.setText("");
            return;
        }
        this.mTextView.setText(Html.fromHtml(charSequence.toString().replaceAll("\\n", "<br>"), 0));
    }

    public void setText(Spannable spannable) {
        this.mTextView.setText(spannable);
    }

    public void setTypeface(Typeface typeface, int i) {
        this.mTextView.setTypeface(typeface, i);
    }
}
