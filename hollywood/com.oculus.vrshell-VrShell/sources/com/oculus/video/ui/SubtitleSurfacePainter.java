package com.oculus.video.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.view.Surface;
import androidx.core.view.ViewCompat;
import com.oculus.android.exoplayer2.text.CaptionStyleCompat;
import com.oculus.android.exoplayer2.text.Cue;
import java.util.List;

public class SubtitleSurfacePainter {
    public static final float DEFAULT_BOTTOM_PADDING_FRACTION = 0.08f;
    public static final float DEFAULT_TEXT_SIZE_FRACTION = 0.0533f;
    private List<Cue> cues;
    private float fontScale = 1.0f;
    private final SubtitlePainter painter;
    private CaptionStyleCompat style = new CaptionStyleCompat(-1, 0, 0, 1, ViewCompat.MEASURED_STATE_MASK, null);

    public SubtitleSurfacePainter(Context context) {
        this.painter = new SubtitlePainter(context);
    }

    public void setCues(List<Cue> list, Surface surface) {
        if (this.cues != list) {
            this.cues = list;
            draw(surface);
        }
    }

    public void setFontScale(float f, Surface surface) {
        if (this.fontScale != f) {
            this.fontScale = f;
            draw(surface);
        }
    }

    public void setStyle(CaptionStyleCompat captionStyleCompat, Surface surface) {
        if (this.style != captionStyleCompat) {
            this.style = captionStyleCompat;
            draw(surface);
        }
    }

    private void draw(Surface surface) {
        try {
            Canvas lockCanvas = surface.lockCanvas(null);
            lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            int width = lockCanvas.getWidth();
            int height = lockCanvas.getHeight();
            if (this.cues != null) {
                for (Cue cue : this.cues) {
                    this.painter.draw(cue, true, true, this.style, this.fontScale * ((float) height) * 0.0533f, 0.08f, lockCanvas, 0, 0, width, height);
                    height = height;
                }
            }
            surface.unlockCanvasAndPost(lockCanvas);
        } catch (Exception unused) {
        }
    }
}
