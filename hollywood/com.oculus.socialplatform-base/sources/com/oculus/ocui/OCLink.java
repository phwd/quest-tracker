package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import com.oculus.socialplatform.R;

public class OCLink extends OCTextView {
    public OCEventHandler mEventHandler;
    public OCLinkHandler mLinkHandler;
    public View.OnClickListener mOnClickListener;

    public interface OCLinkHandler {
        void open(String str, String str2);
    }

    private SpannableStringBuilder embedLink(String str, final String str2, boolean z) {
        int indexOf = str.indexOf("<b>");
        String replace = str.replace("<b>", "");
        int indexOf2 = replace.indexOf("</b>");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(replace.replace("</b>", ""));
        spannableStringBuilder.setSpan(new StyleSpan(1), indexOf, indexOf2, 33);
        Resources resources = getResources();
        int i = R.color.text_link_on_exit;
        if (z) {
            i = R.color.text_link_on_enter;
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(resources.getColor(i, null)), indexOf, indexOf2, 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            /* class com.oculus.ocui.OCLink.AnonymousClass1 */

            public void updateDrawState(@NonNull TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

            public void onClick(@NonNull View view) {
                OCEventHandler oCEventHandler = OCLink.this.mEventHandler;
                if (oCEventHandler != null) {
                    oCEventHandler.onButtonClick();
                }
                OCLinkHandler oCLinkHandler = OCLink.this.mLinkHandler;
                if (oCLinkHandler != null) {
                    oCLinkHandler.open("systemux://browser", String.format("ovrweb://webtask?uri=%s", Uri.encode(str2)));
                }
                View.OnClickListener onClickListener = OCLink.this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        }, indexOf, indexOf2, 33);
        return spannableStringBuilder;
    }

    public OCLink(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.oculus.common.ocui.R.styleable.OCLink);
        initializeLink(obtainStyledAttributes.getString(0));
        obtainStyledAttributes.recycle();
    }

    private void initializeLink(String str) {
        String charSequence = getText().toString();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(charSequence)) {
            setOnHoverListener(null);
            return;
        }
        setMovementMethod(LinkMovementMethod.getInstance());
        setOnHoverListener(new View.OnHoverListener(charSequence, str) {
            /* class com.oculus.ocui.$$Lambda$OCLink$xInqFYIm_LB3a75FdeDxEOTQCiA2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCLink.this.lambda$initializeLink$0$OCLink(this.f$1, this.f$2, view, motionEvent);
            }
        });
        setText(embedLink(charSequence, str, false));
        setHighlightColor(0);
    }

    public /* synthetic */ boolean lambda$initializeLink$0$OCLink(String str, String str2, View view, MotionEvent motionEvent) {
        SpannableStringBuilder embedLink;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 9) {
            if (actionMasked == 10) {
                embedLink = embedLink(str, str2, false);
            }
            return true;
        }
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonEnter();
        }
        embedLink = embedLink(str, str2, true);
        setText(embedLink);
        return true;
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setLinkHandler(OCLinkHandler oCLinkHandler) {
        this.mLinkHandler = oCLinkHandler;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @BindingAdapter({"app:uri"})
    public static void setUri(OCLink oCLink, String str) {
        oCLink.initializeLink(str);
    }
}
