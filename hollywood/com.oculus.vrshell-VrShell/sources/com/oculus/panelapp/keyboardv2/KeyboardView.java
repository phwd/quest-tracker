package com.oculus.panelapp.keyboardv2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.oculus.panelapp.keyboardv2.Keyboard;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class KeyboardView extends View {
    private static final int DEBOUNCE_TIME = 70;
    private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private static final int MSG_LONGPRESS = 2;
    private static final int MSG_REPEAT = 1;
    private static final int NOT_A_KEY = -1;
    private static final int REPEAT_INTERVAL = 50;
    private static final int REPEAT_START_DELAY = 400;
    public static final String TAG = "KeyboardView";
    private boolean mAbortKey;
    private Bitmap mBuffer;
    private Canvas mCanvas;
    private Rect mClipRegion;
    private int mCurrentHoverKeyIndex;
    private int mCurrentKey;
    private int mCurrentKeyIndex;
    private long mCurrentKeyTime;
    private Rect mDirtyRect;
    private boolean mDownActionWasValid;
    private long mDownTime;
    private boolean mDrawPending;
    Handler mHandler;
    private Drawable mKeyBackground;
    private int[] mKeyIndices;
    private Keyboard mKeyboard;
    private OnKeyboardActionListener mKeyboardActionListener;
    private boolean mKeyboardChanged;
    private int mLastCodeX;
    private int mLastCodeY;
    private int mLastKey;
    private long mLastKeyTime;
    private long mLastMoveTime;
    private int mLastX;
    private int mLastY;
    private final Rect mPaddingRect;
    private Paint mPaint;
    private int mPopupLayout;
    private int mRepeatKeyIndex;
    private int mShadowColor;
    private float mShadowRadius;

    public interface OnKeyboardActionListener {
        void onHoverEnter(Keyboard.Key key);

        void onHoverExit();

        void onKey(int i, int[] iArr);

        boolean onLongPress(Keyboard.Key key, boolean z);

        void onPress(String str, boolean z, Keyboard.Key key);

        void onRelease(String str, boolean z, Keyboard.Key key);
    }

    public KeyboardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.keyboardViewStyle);
    }

    public KeyboardView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public KeyboardView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mClipRegion = new Rect(0, 0, 0, 0);
        this.mDirtyRect = new Rect();
        this.mPaddingRect = new Rect();
        this.mCurrentKeyIndex = -1;
        this.mCurrentKey = -1;
        this.mCurrentHoverKeyIndex = -1;
        this.mKeyIndices = new int[12];
        this.mRepeatKeyIndex = -1;
        this.mDownActionWasValid = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.KeyboardView, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.KeyboardView_keyBackground) {
                this.mKeyBackground = obtainStyledAttributes.getDrawable(index);
            } else if (index == R.styleable.KeyboardView_popupLayout) {
                this.mPopupLayout = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.KeyboardView_shadowColor) {
                this.mShadowColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.KeyboardView_shadowRadius) {
                this.mShadowRadius = obtainStyledAttributes.getFloat(index, 0.0f);
            }
        }
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setAlpha(255);
        setEnabled(true);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mHandler == null) {
            this.mHandler = new Handler() {
                /* class com.oculus.panelapp.keyboardv2.KeyboardView.AnonymousClass1 */

                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i != 1) {
                        if (i == 2) {
                            KeyboardView.this.openPopupIfRequired((MotionEvent) message.obj);
                        }
                    } else if (KeyboardView.this.repeatKey()) {
                        sendMessageDelayed(Message.obtain(this, 1), 50);
                    }
                }
            };
        }
    }

    public void setOnKeyboardActionListener(OnKeyboardActionListener onKeyboardActionListener) {
        this.mKeyboardActionListener = onKeyboardActionListener;
    }

    /* access modifiers changed from: protected */
    public OnKeyboardActionListener getOnKeyboardActionListener() {
        return this.mKeyboardActionListener;
    }

    public void setKeyboard(Keyboard keyboard) {
        if (this.mKeyboard != null) {
            showKey(-1);
        }
        removeMessages();
        this.mKeyboard = keyboard;
        requestLayout();
        this.mKeyboardChanged = true;
        invalidateAllKeys();
        this.mAbortKey = true;
    }

    public Keyboard getKeyboard() {
        return this.mKeyboard;
    }

    public int getRowCount() {
        Keyboard keyboard = this.mKeyboard;
        if (keyboard == null) {
            return 0;
        }
        return keyboard.getRows().size();
    }

    public int getColumnCount() {
        Keyboard keyboard = this.mKeyboard;
        int i = 0;
        if (keyboard == null) {
            return 0;
        }
        Iterator<Keyboard.Row> it = keyboard.getRows().iterator();
        while (it.hasNext()) {
            Keyboard.Row next = it.next();
            if (next.getKeys().size() > i) {
                i = next.getKeys().size();
            }
        }
        return i;
    }

    public boolean setShiftState(ShiftState shiftState) {
        Keyboard keyboard = this.mKeyboard;
        if (keyboard == null || !keyboard.setShifted(shiftState)) {
            return false;
        }
        this.mKeyboardChanged = true;
        invalidateAllKeys();
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.keyboardv2.KeyboardView$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$keyboardv2$ShiftState = new int[ShiftState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.panelapp.keyboardv2.ShiftState[] r0 = com.oculus.panelapp.keyboardv2.ShiftState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.keyboardv2.KeyboardView.AnonymousClass2.$SwitchMap$com$oculus$panelapp$keyboardv2$ShiftState = r0
                int[] r0 = com.oculus.panelapp.keyboardv2.KeyboardView.AnonymousClass2.$SwitchMap$com$oculus$panelapp$keyboardv2$ShiftState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.keyboardv2.ShiftState r1 = com.oculus.panelapp.keyboardv2.ShiftState.OFF     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.keyboardv2.KeyboardView.AnonymousClass2.$SwitchMap$com$oculus$panelapp$keyboardv2$ShiftState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.keyboardv2.ShiftState r1 = com.oculus.panelapp.keyboardv2.ShiftState.ON     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.keyboardv2.KeyboardView.AnonymousClass2.$SwitchMap$com$oculus$panelapp$keyboardv2$ShiftState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.keyboardv2.ShiftState r1 = com.oculus.panelapp.keyboardv2.ShiftState.CAPS_LOCK     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.keyboardv2.KeyboardView.AnonymousClass2.<clinit>():void");
        }
    }

    public boolean toggleShiftState() {
        if (this.mKeyboard == null) {
            return false;
        }
        int i = AnonymousClass2.$SwitchMap$com$oculus$panelapp$keyboardv2$ShiftState[this.mKeyboard.getShiftState().ordinal()];
        if (i == 1) {
            return setShiftState(ShiftState.ON);
        }
        if (i == 2) {
            return setShiftState(ShiftState.CAPS_LOCK);
        }
        if (i != 3) {
            return false;
        }
        return setShiftState(ShiftState.OFF);
    }

    public boolean getShifted() {
        Keyboard keyboard = this.mKeyboard;
        if (keyboard != null) {
            return keyboard.isShifted();
        }
        return false;
    }

    public void onMeasure(int i, int i2) {
        Keyboard keyboard = this.mKeyboard;
        if (keyboard == null) {
            setMeasuredDimension(getPaddingLeft() + getPaddingRight(), getPaddingTop() + getPaddingBottom());
        } else {
            setMeasuredDimension(keyboard.getMinWidth() + getPaddingLeft() + getPaddingRight(), this.mKeyboard.getHeight() + getPaddingTop() + getPaddingBottom());
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mBuffer = null;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Keyboard keyboard = this.mKeyboard;
        if (keyboard != null && keyboard.isLayoutInvalid()) {
            invalidateAllKeys();
        }
        if (this.mDrawPending || this.mBuffer == null || this.mKeyboardChanged) {
            onBufferDraw();
        }
        canvas.drawBitmap(this.mBuffer, 0.0f, 0.0f, (Paint) null);
    }

    private void reflow() {
        List<Keyboard.Key> keys = this.mKeyboard.getKeys();
        int size = keys.size();
        for (int i = 0; i < size; i++) {
            Keyboard.Key key = keys.get(i);
            String label = getLabel(key);
            String subLabel = getSubLabel(key);
            Drawable icon = getIcon(key);
            if (key.mWidthMode == Keyboard.KeyDimensionMode.FIT_CONTENT) {
                key.mWidthMode = Keyboard.KeyDimensionMode.FIXED;
                this.mPaint.setTypeface(key.font);
                if (label != null) {
                    this.mPaint.setTextSize((float) key.keyTextSize);
                    key.width = (int) (this.mPaint.measureText(label) + (this.mPaint.measureText(" ") * 2.0f));
                }
                if (subLabel != null) {
                    this.mPaint.setTextSize((float) key.subKeyTextSize);
                    key.width = Math.max(key.width, (int) (this.mPaint.measureText(subLabel) + (this.mPaint.measureText(" ") * 2.0f)));
                }
                if (icon != null) {
                    key.width = Math.max(key.width, (int) (((float) icon.getIntrinsicWidth()) * 2.0f));
                }
            }
            if (key.mXMode == Keyboard.KeyPositionMode.FLOW) {
                key.mXMode = Keyboard.KeyPositionMode.FIXED;
                if (i == 0) {
                    key.x = getPaddingLeft() + key.gap;
                } else {
                    Keyboard.Key key2 = keys.get(i - 1);
                    key.x = key2.x + key2.width + key.gap;
                }
            }
        }
    }

    public String getLabel(Keyboard.Key key) {
        CharSequence charSequence = this.mKeyboard.isShifted() ? key.shiftLabel : key.label;
        if (charSequence == null) {
            charSequence = key.label;
        }
        if (charSequence == null) {
            return null;
        }
        return charSequence.toString();
    }

    public String getSubLabel(Keyboard.Key key) {
        if (key.subLabel == null) {
            return null;
        }
        return key.subLabel.toString();
    }

    public Drawable getIcon(Keyboard.Key key) {
        Drawable drawable = this.mKeyboard.isShifted() ? key.shiftIcon : key.icon;
        return drawable == null ? key.icon : drawable;
    }

    private void onBufferDraw() {
        int i = 0;
        if (this.mBuffer == null || this.mKeyboardChanged) {
            Bitmap bitmap = this.mBuffer;
            if (bitmap == null || (this.mKeyboardChanged && !(bitmap.getWidth() == getWidth() && this.mBuffer.getHeight() == getHeight()))) {
                this.mBuffer = Bitmap.createBitmap(Math.max(1, getWidth()), Math.max(1, getHeight()), Bitmap.Config.ARGB_8888);
                this.mCanvas = new Canvas(this.mBuffer);
            }
            invalidateAllKeys();
            this.mKeyboardChanged = false;
        }
        if (this.mKeyboard != null) {
            reflow();
            this.mCanvas.save();
            Paint paint = this.mPaint;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            this.mPaddingRect.setEmpty();
            this.mCanvas.clipRect(this.mDirtyRect);
            this.mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            List<Keyboard.Key> keys = this.mKeyboard.getKeys();
            int size = keys.size();
            int i2 = 0;
            while (i2 < size) {
                Keyboard.Key key = keys.get(i2);
                Rect bounds = key.getBounds();
                bounds.offset(paddingLeft, paddingTop);
                if (!bounds.isEmpty() && this.mDirtyRect.contains(bounds)) {
                    this.mCanvas.save();
                    Optional<DynamicKeyArea> optional = key.mDynamicArea;
                    if (optional.isPresent()) {
                        this.mCanvas.clipRect(optional.get().getBounds());
                    } else {
                        this.mCanvas.clipRect(this.mDirtyRect);
                    }
                    int[] currentDrawableState = key.getCurrentDrawableState(getKeyboard().getKeyToHighlight());
                    Drawable drawable = key.keyBackground == null ? this.mKeyBackground : key.keyBackground;
                    drawable.getPadding(this.mPaddingRect);
                    int colorForState = key.keyTextColor.getColorForState(currentDrawableState, key.keyTextColor.getDefaultColor());
                    paint.setColor(colorForState);
                    drawable.setState(currentDrawableState);
                    Rect bounds2 = drawable.getBounds();
                    if (!(key.width == bounds2.right && key.height == bounds2.bottom)) {
                        drawable.setBounds(i, i, key.width, key.height);
                    }
                    this.mCanvas.translate((float) (key.x + paddingLeft), (float) (key.y + paddingTop));
                    drawable.draw(this.mCanvas);
                    String label = getLabel(key);
                    String subLabel = getSubLabel(key);
                    Drawable icon = getIcon(key);
                    if (label != null) {
                        paint.setTypeface(key.font);
                        paint.setShadowLayer(this.mShadowRadius, 0.0f, 0.0f, this.mShadowColor);
                        if (subLabel != null) {
                            float f = (float) (((((key.height - this.mPaddingRect.bottom) - this.mPaddingRect.top) - key.keyTextSize) - key.subKeyTextSize) / 3);
                            paint.setTextSize((float) key.subKeyTextSize);
                            float descent = (((float) (key.height - this.mPaddingRect.bottom)) - f) - paint.descent();
                            this.mCanvas.drawText(subLabel, (float) ((((key.width - this.mPaddingRect.left) - this.mPaddingRect.right) / 2) + this.mPaddingRect.left), descent, paint);
                            paint.setTextSize((float) key.keyTextSize);
                            this.mCanvas.drawText(label, (float) ((((key.width - this.mPaddingRect.left) - this.mPaddingRect.right) / 2) + this.mPaddingRect.left), (descent - ((float) key.subKeyTextSize)) - f, paint);
                        } else {
                            paint.setTextSize((float) key.keyTextSize);
                            this.mCanvas.drawText(label, (float) ((((key.width - this.mPaddingRect.left) - this.mPaddingRect.right) / 2) + this.mPaddingRect.left), ((float) (((key.height - this.mPaddingRect.top) - this.mPaddingRect.bottom) / 2)) + ((paint.getTextSize() - paint.descent()) / 2.0f) + ((float) this.mPaddingRect.top), paint);
                        }
                        paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                    } else if (icon != null) {
                        icon.setTint(colorForState);
                        drawIcon(key, icon, this.mPaddingRect);
                    }
                    this.mCanvas.translate((float) ((-key.x) - paddingLeft), (float) ((-key.y) - paddingTop));
                    this.mCanvas.restore();
                }
                i2++;
                i = 0;
            }
            this.mCanvas.restore();
            this.mDrawPending = false;
            this.mDirtyRect.setEmpty();
            invalidate();
        }
    }

    private int getKeyIndices(int i, int i2, int[] iArr) {
        List<Keyboard.Key> keys = this.mKeyboard.getKeys();
        int[] nearestKeys = this.mKeyboard.getNearestKeys(i, i2);
        int length = nearestKeys.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (keys.get(nearestKeys[i3]).isInside(i, i2)) {
                return nearestKeys[i3];
            }
        }
        return -1;
    }

    private void drawIcon(Keyboard.Key key, Drawable drawable, Rect rect) {
        if (drawable instanceof VectorDrawable) {
            drawVectorIcon(key, (VectorDrawable) drawable, rect);
            return;
        }
        int intrinsicWidth = ((((key.width - rect.left) - rect.right) - drawable.getIntrinsicWidth()) / 2) + rect.left;
        int intrinsicHeight = ((((key.height - rect.top) - rect.bottom) - drawable.getIntrinsicHeight()) / 2) + rect.top;
        this.mCanvas.translate((float) intrinsicWidth, (float) intrinsicHeight);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(this.mCanvas);
        this.mCanvas.translate((float) (-intrinsicWidth), (float) (-intrinsicHeight));
    }

    private void drawVectorIcon(Keyboard.Key key, VectorDrawable vectorDrawable, Rect rect) {
        int intrinsicWidth = vectorDrawable.getIntrinsicWidth();
        int intrinsicHeight = vectorDrawable.getIntrinsicHeight();
        int i = key.keyTextSize;
        int i2 = (int) (((float) intrinsicWidth) * (((float) i) / ((float) intrinsicHeight)));
        int i3 = (int) ((((float) (((key.width - rect.left) - rect.right) - i2)) / 2.0f) + ((float) rect.left));
        int i4 = (int) ((((float) (((key.height - rect.top) - rect.bottom) - i)) / 2.0f) + ((float) rect.top));
        this.mCanvas.translate((float) i3, (float) i4);
        vectorDrawable.setBounds(0, 0, i2, i);
        vectorDrawable.draw(this.mCanvas);
        this.mCanvas.translate((float) (-i3), (float) (-i4));
    }

    private void detectAndSendKey(int i, int i2, int i3) {
        List<Keyboard.Key> keys = this.mKeyboard.getKeys();
        if (i != -1 && i < keys.size()) {
            Keyboard.Key key = keys.get(i);
            if (key.text != null || key.codes.get(0).intValue() == KeyCode.LAYOUT.value || key.codes.get(0).intValue() == KeyCode.SHIFT.value || key.codes.get(0).intValue() == KeyCode.DISMISS.value || key.codes.get(0).intValue() == KeyCode.ACTION_KEY.value || key.codes.get(0).intValue() == KeyCode.RETURN_TO_CURRENT_LANGUAGE.value || key.codes.get(0).intValue() == KeyCode.JP_CONVERSION.value) {
                this.mKeyboardActionListener.onRelease(this.mKeyboard.getIme(), this.mKeyboard.isShifted(), key);
            }
        }
    }

    private void showKey(int i) {
        int i2;
        int i3 = this.mCurrentKeyIndex;
        this.mCurrentKeyIndex = i;
        List<Keyboard.Key> keys = this.mKeyboard.getKeys();
        if (i3 != this.mCurrentKeyIndex) {
            if (i3 != -1 && keys.size() > i3) {
                keys.get(i3).onReleased();
                invalidateKey(i3);
            }
            if (this.mCurrentKeyIndex != -1 && keys.size() > (i2 = this.mCurrentKeyIndex)) {
                Keyboard.Key key = keys.get(i2);
                if (!isEmptyTypeaheadSuggestion(key)) {
                    key.onPressed();
                    invalidateKey(this.mCurrentKeyIndex);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void removePressedState() {
        if (this.mCurrentKey != -1) {
            this.mKeyboard.getKeys().get(this.mCurrentKey).onReleased();
            invalidateKey(this.mCurrentKey);
        }
    }

    public void invalidateAllKeys() {
        this.mDirtyRect.union(0, 0, getWidth(), getHeight());
        this.mDrawPending = true;
    }

    public void invalidateKey(int i) {
        List<Keyboard.Key> keys = this.mKeyboard.getKeys();
        if (keys != null && i >= 0 && i < keys.size()) {
            invalidateKey(keys.get(i));
        }
    }

    public void invalidateKey(Keyboard.Key key) {
        Optional<DynamicKeyArea> optional = key.mDynamicArea;
        if (optional.isPresent()) {
            Rect bounds = optional.get().getBounds();
            bounds.offset(getPaddingLeft(), getPaddingTop());
            this.mDirtyRect.union(bounds);
        } else {
            Rect bounds2 = key.getBounds();
            bounds2.offset(getPaddingLeft(), getPaddingTop());
            this.mDirtyRect.union(bounds2);
        }
        onBufferDraw();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean openPopupIfRequired(MotionEvent motionEvent) {
        if (this.mPopupLayout == 0) {
            return false;
        }
        List<Keyboard.Key> keys = this.mKeyboard.getKeys();
        int i = this.mCurrentKey;
        if (i < 0 || i >= keys.size()) {
            return false;
        }
        return this.mKeyboardActionListener.onLongPress(keys.get(this.mCurrentKey), this.mKeyboard.isShifted());
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i;
        int i2;
        int x = ((int) motionEvent.getX()) - getPaddingLeft();
        int y = ((int) motionEvent.getY()) - getPaddingTop();
        int i3 = this.mCurrentHoverKeyIndex;
        this.mCurrentHoverKeyIndex = getKeyIndices(x, y, null);
        List<Keyboard.Key> keys = this.mKeyboard.getKeys();
        if (motionEvent.getAction() == 9) {
            if (this.mCurrentHoverKeyIndex != -1 && keys.size() > (i2 = this.mCurrentHoverKeyIndex)) {
                Keyboard.Key key = keys.get(i2);
                if (isEmptyTypeaheadSuggestion(key)) {
                    return true;
                }
                key.onHoverEnter();
                invalidateKey(this.mCurrentHoverKeyIndex);
                this.mKeyboardActionListener.onHoverEnter(key);
            }
        } else if (motionEvent.getAction() == 10) {
            if (i3 != -1 && keys.size() > i3) {
                Keyboard.Key key2 = keys.get(i3);
                if (isEmptyTypeaheadSuggestion(key2)) {
                    return true;
                }
                key2.onHoverExit();
                invalidateKey(i3);
                this.mKeyboardActionListener.onHoverExit();
            }
            this.mCurrentHoverKeyIndex = -1;
        } else if (motionEvent.getAction() == 7) {
            if (this.mCurrentHoverKeyIndex != -1 && keys.size() > (i = this.mCurrentHoverKeyIndex)) {
                Keyboard.Key key3 = keys.get(i);
                if (isEmptyTypeaheadSuggestion(key3)) {
                    return true;
                }
                invalidateKey(this.mCurrentHoverKeyIndex);
                if (i3 != this.mCurrentHoverKeyIndex) {
                    key3.onHoverEnter();
                    this.mKeyboardActionListener.onHoverEnter(key3);
                }
            }
            if (!(i3 == this.mCurrentHoverKeyIndex || i3 == -1 || keys.size() <= i3)) {
                Keyboard.Key key4 = keys.get(i3);
                if (isEmptyTypeaheadSuggestion(key4)) {
                    return true;
                }
                key4.onHoverExit();
                invalidateKey(i3);
            }
        }
        return true;
    }

    private boolean isEmptyTypeaheadSuggestion(Keyboard.Key key) {
        return KeyCode.WORD_PREDICTION.value == key.codes.get(0).intValue() && TextUtils.isEmpty(key.text);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
        // Method dump skipped, instructions count: 391
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.keyboardv2.KeyboardView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean repeatKey() {
        this.mKeyboardActionListener.onPress(this.mKeyboard.getIme(), this.mKeyboard.isShifted(), this.mKeyboard.getKeys().get(this.mRepeatKeyIndex));
        return true;
    }

    public void closing() {
        removeMessages();
        this.mBuffer = null;
        this.mCanvas = null;
    }

    private void removeMessages() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
            this.mHandler.removeMessages(2);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        closing();
    }
}
