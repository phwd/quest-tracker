package oculus.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.oculus.os.platform.internal.R;

public class ForgotPatternInstructionsView extends LinearLayout {
    public ForgotPatternInstructionsView(Context context) {
        this(context, null);
    }

    public ForgotPatternInstructionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(1);
        setGravity(1);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.forgot_pattern_instructions_view, (ViewGroup) this, true);
    }
}
