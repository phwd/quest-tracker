package com.oculus.ocui;

import android.content.Context;
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
import com.oculus.common.ocui.R;

public class OCLink extends OCTextView {
    private OCEventHandler mEventHandler;
    private OCLinkHandler mLinkHandler;
    private View.OnClickListener mOnClickListener;

    public interface OCLinkHandler {
        void open(String str, String str2);
    }

    public OCLink(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCLink);
        initializeLink(obtainStyledAttributes.getString(R.styleable.OCLink_uri));
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
            /* class com.oculus.ocui.$$Lambda$OCLink$HoqxAgZKXWEsp8k5UFlIFYYeBNw */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCLink.this.lambda$initializeLink$8$OCLink(this.f$1, this.f$2, view, motionEvent);
            }
        });
        setText(embedLink(charSequence, str, false));
        setHighlightColor(0);
    }

    public /* synthetic */ boolean lambda$initializeLink$8$OCLink(String str, String str2, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            OCEventHandler oCEventHandler = this.mEventHandler;
            if (oCEventHandler != null) {
                oCEventHandler.onButtonEnter();
            }
            setText(embedLink(str, str2, true));
        } else if (actionMasked == 10) {
            setText(embedLink(str, str2, false));
        }
        return true;
    }

    private SpannableStringBuilder embedLink(String str, final String str2, boolean z) {
        int indexOf = str.indexOf("<b>");
        String replace = str.replace("<b>", "");
        int indexOf2 = replace.indexOf("</b>");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(replace.replace("</b>", ""));
        spannableStringBuilder.setSpan(new StyleSpan(1), indexOf, indexOf2, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(z ? R.color.text_link_on_enter : R.color.text_link_on_exit, null)), indexOf, indexOf2, 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            /* class com.oculus.ocui.OCLink.AnonymousClass1 */

            public void onClick(@NonNull View view) {
                if (OCLink.this.mEventHandler != null) {
                    OCLink.this.mEventHandler.onButtonClick();
                }
                if (OCLink.this.mLinkHandler != null) {
                    OCLink.this.mLinkHandler.open("systemux://browser", String.format("ovrweb://webtask?uri=%s", Uri.encode(str2)));
                }
                if (OCLink.this.mOnClickListener != null) {
                    OCLink.this.mOnClickListener.onClick(view);
                }
            }

            public void updateDrawState(@NonNull TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }
        }, indexOf, indexOf2, 33);
        return spannableStringBuilder;
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
