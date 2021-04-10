package com.oculus.panelapp.keyboardv2.assistant;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.Preconditions;
import com.oculus.panelapp.keyboardv2.R;

public class TranscriptionFeedbackView extends LinearLayout {
    private static String TAG = "TranscriptionFeedbackView";
    private LinearLayout mConfirmationMessage;
    private TextView mConfirmationText;
    private Handler mHandler;
    private Runnable mHideRunnable = new Runnable() {
        /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$TranscriptionFeedbackView$LTGSQvQES_hIxpjmMe_pSCMu2c */

        public final void run() {
            TranscriptionFeedbackView.this.lambda$new$0$TranscriptionFeedbackView();
        }
    };
    private ImageButton mNegativeFeedback;
    private ImageButton mNegativeFeedbackResult;
    private ImageButton mPositiveFeedback;
    private ImageButton mPositiveFeedbackResult;
    private LinearLayout mRequestMessage;
    private boolean mScheduled;
    private KeyboardTranscription mTranscription;

    public /* synthetic */ void lambda$new$0$TranscriptionFeedbackView() {
        setVisibility(8);
    }

    public TranscriptionFeedbackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "TranscriptionFeedbackView created");
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void initialize() {
        this.mRequestMessage = (LinearLayout) findViewById(R.id.keyboard_transcription_feedback_request);
        this.mConfirmationMessage = (LinearLayout) findViewById(R.id.keyboard_transcription_feedback_confirmation);
        this.mPositiveFeedback = (ImageButton) findViewById(R.id.keyboard_transcription_feedback_positive);
        this.mNegativeFeedback = (ImageButton) findViewById(R.id.keyboard_transcription_feedback_negative);
        this.mPositiveFeedbackResult = (ImageButton) findViewById(R.id.keyboard_transcription_feedback_positive_after);
        this.mNegativeFeedbackResult = (ImageButton) findViewById(R.id.keyboard_transcription_feedback_negative_after);
        this.mConfirmationText = (TextView) findViewById(R.id.keyboard_transcription_confirmation_text);
        this.mPositiveFeedback.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$TranscriptionFeedbackView$9boj0HVAAuBCv_tE0lS5kuYxR9c */

            public final void onClick(View view) {
                TranscriptionFeedbackView.this.lambda$initialize$1$TranscriptionFeedbackView(view);
            }
        });
        this.mNegativeFeedback.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$TranscriptionFeedbackView$6GyIdHMCnnSd4E8zlQIs5pfqJ8E */

            public final void onClick(View view) {
                TranscriptionFeedbackView.this.lambda$initialize$2$TranscriptionFeedbackView(view);
            }
        });
        this.mPositiveFeedback.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$TranscriptionFeedbackView$In9POQtXE9bcDZ5UX9yuleUjBRk */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return TranscriptionFeedbackView.this.lambda$initialize$3$TranscriptionFeedbackView(view, motionEvent);
            }
        });
        this.mNegativeFeedback.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$TranscriptionFeedbackView$B8ciqwcpm58ALMXPHROWzIaWQ0 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return TranscriptionFeedbackView.this.lambda$initialize$4$TranscriptionFeedbackView(view, motionEvent);
            }
        });
        setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$TranscriptionFeedbackView$9GFdErBcS_llAVUaXQSqFiGjBE */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return TranscriptionFeedbackView.this.lambda$initialize$5$TranscriptionFeedbackView(view, motionEvent);
            }
        });
        String string = getContext().getString(R.string.transcription_feedback_thanks);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new StyleSpan(1), 0, string.length(), 33);
        this.mConfirmationText.setText(spannableStringBuilder);
        setVisibility(8);
        this.mRequestMessage.setVisibility(0);
        this.mConfirmationMessage.setVisibility(8);
    }

    public /* synthetic */ void lambda$initialize$1$TranscriptionFeedbackView(View view) {
        this.mTranscription.submitFeedback("positive");
        this.mRequestMessage.setVisibility(8);
        this.mConfirmationMessage.setVisibility(0);
        this.mNegativeFeedbackResult.setVisibility(8);
        this.mPositiveFeedbackResult.setVisibility(0);
        scheduleHide();
    }

    public /* synthetic */ void lambda$initialize$2$TranscriptionFeedbackView(View view) {
        this.mTranscription.submitFeedback("negative");
        this.mRequestMessage.setVisibility(8);
        this.mConfirmationMessage.setVisibility(0);
        this.mNegativeFeedbackResult.setVisibility(0);
        this.mPositiveFeedbackResult.setVisibility(8);
        scheduleHide();
    }

    public /* synthetic */ boolean lambda$initialize$3$TranscriptionFeedbackView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 9) {
            return true;
        }
        this.mTranscription.hoverFeedback();
        return true;
    }

    public /* synthetic */ boolean lambda$initialize$4$TranscriptionFeedbackView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 9) {
            return true;
        }
        this.mTranscription.hoverFeedback();
        return true;
    }

    public /* synthetic */ boolean lambda$initialize$5$TranscriptionFeedbackView(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 9) {
            stopHide();
            return false;
        } else if (action != 10) {
            return false;
        } else {
            scheduleHide();
            return false;
        }
    }

    private void stopHide() {
        this.mScheduled = false;
        this.mHandler.removeCallbacks(this.mHideRunnable);
    }

    private void scheduleHide() {
        if (!this.mScheduled) {
            this.mScheduled = true;
            this.mHandler.postDelayed(this.mHideRunnable, 3000);
        }
    }

    public void show(KeyboardTranscription keyboardTranscription) {
        Preconditions.checkNotNull(keyboardTranscription);
        this.mTranscription = keyboardTranscription;
        resetViews();
        setVisibility(0);
        scheduleHide();
    }

    private void resetViews() {
        this.mRequestMessage.setVisibility(0);
        this.mConfirmationMessage.setVisibility(8);
        this.mNegativeFeedbackResult.setVisibility(0);
        this.mPositiveFeedbackResult.setVisibility(0);
    }
}
