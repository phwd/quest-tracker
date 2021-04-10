package com.facebook.common.util;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.facebook.common.string.StringUtil;
import com.facebook.fbui.textlayoutbuilder.util.LayoutMeasureUtil;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TextSizeUtil {

    public static class TextMeasuredAttributes {
        public int lineCount;
        public int textHeight;

        public TextMeasuredAttributes(int i, int i2) {
            this.textHeight = i;
            this.lineCount = i2;
        }
    }

    public static TextMeasuredAttributes measureText(String str, int i, float f, TextPaint textPaint, Layout.Alignment alignment) {
        if (StringUtil.isEmptyOrNull(str)) {
            return new TextMeasuredAttributes(0, 0);
        }
        StaticLayout staticLayout = new StaticLayout(str, textPaint, i, alignment, f, 0.0f, true);
        return new TextMeasuredAttributes(LayoutMeasureUtil.getHeight(staticLayout), staticLayout.getLineCount());
    }
}
