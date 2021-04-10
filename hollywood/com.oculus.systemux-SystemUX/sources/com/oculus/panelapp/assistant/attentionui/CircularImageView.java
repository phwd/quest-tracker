package com.oculus.panelapp.assistant.attentionui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

public class CircularImageView extends ImageView {
    private Bitmap mBitmap;
    private Paint mMaskPaint;

    public CircularImageView(Context context) {
        super(context);
        init();
    }

    public CircularImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CircularImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mMaskPaint = new Paint(1);
        this.mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        setOutlineProvider(new ViewOutlineProvider() {
            /* class com.oculus.panelapp.assistant.attentionui.CircularImageView.AnonymousClass1 */

            public void getOutline(View view, Outline outline) {
                Rect rect = new Rect();
                view.getDrawingRect(rect);
                outline.setOval(rect);
            }
        });
        setClipToOutline(true);
    }
}
