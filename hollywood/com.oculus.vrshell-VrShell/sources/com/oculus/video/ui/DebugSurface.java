package com.oculus.video.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.StyleSpan;
import android.view.Surface;
import java.util.Map;

public class DebugSurface {
    private static final int TEXT_PADDING = 10;
    private static final float TEXT_SIZE_PX = 20.0f;
    private final Surface surface;
    private final TextPaint textPaint = new TextPaint();

    public DebugSurface(Surface surface2) {
        this.surface = surface2;
        this.textPaint.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        this.textPaint.setTextSize(TEXT_SIZE_PX);
        this.textPaint.setColor(-1);
        this.textPaint.setStyle(Paint.Style.FILL);
    }

    public void setContent(Map<String, String> map, boolean z) {
        try {
            Canvas lockCanvas = this.surface.lockCanvas(null);
            if (z) {
                lockCanvas.drawColor(2144128204, PorterDuff.Mode.SRC);
            } else {
                lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            }
            if (map != null) {
                int save = lockCanvas.save();
                lockCanvas.translate(10.0f, 10.0f);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (!(entry == null || entry.getKey() == null)) {
                        if (entry.getValue() != null) {
                            spannableStringBuilder.append(entry.getKey() + ": ", new StyleSpan(1), 33).append((CharSequence) entry.getValue());
                            spannableStringBuilder.append('\n');
                        }
                    }
                }
                new StaticLayout(spannableStringBuilder, this.textPaint, lockCanvas.getWidth() - 20, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).draw(lockCanvas);
                lockCanvas.restoreToCount(save);
            }
            this.surface.unlockCanvasAndPost(lockCanvas);
        } catch (Exception unused) {
        }
    }
}
