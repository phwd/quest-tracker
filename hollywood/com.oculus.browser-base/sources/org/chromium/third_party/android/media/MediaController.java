package org.chromium.third_party.android.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaController extends FrameLayout {
    public static final /* synthetic */ int F = 0;
    public Context G;
    public ViewGroup H;
    public SeekBar I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f11014J;
    public TextView K;
    public boolean L;
    public boolean M;
    public StringBuilder N;
    public Formatter O;
    public ImageButton P;
    public ImageButton Q;
    public ImageButton R;
    public ImageButton S;
    public ImageButton T;
    public View.OnClickListener U = new View$OnClickListenerC0132Cd0(this);
    public SeekBar.OnSeekBarChangeListener V = new C0193Dd0(this);
    public View.OnClickListener W = new View$OnClickListenerC0254Ed0(this);
    public View.OnClickListener a0 = new View$OnClickListenerC0315Fd0(this);

    public MediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G = context;
        this.M = true;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.f39240_resource_name_obfuscated_RES_2131624233, (ViewGroup) this, true);
        ImageButton imageButton = (ImageButton) findViewById(R.id.pause);
        this.P = imageButton;
        if (imageButton != null) {
            imageButton.requestFocus();
            this.P.setOnClickListener(this.U);
        }
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.ffwd);
        this.Q = imageButton2;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(this.a0);
            this.Q.setVisibility(this.M ? 0 : 8);
        }
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.rew);
        this.R = imageButton3;
        if (imageButton3 != null) {
            imageButton3.setOnClickListener(this.W);
            this.R.setVisibility(this.M ? 0 : 8);
        }
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.next);
        this.S = imageButton4;
        if (imageButton4 != null) {
            imageButton4.setVisibility(8);
        }
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.prev);
        this.T = imageButton5;
        if (imageButton5 != null) {
            imageButton5.setVisibility(8);
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.mediacontroller_progress_container);
        this.H = viewGroup;
        if (viewGroup != null) {
            SeekBar seekBar = (SeekBar) viewGroup.findViewById(R.id.mediacontroller_progress_bar);
            this.I = seekBar;
            if (seekBar != null) {
                seekBar.setOnSeekBarChangeListener(this.V);
                this.I.setMax(1000);
            }
        }
        this.f11014J = (TextView) findViewById(R.id.time);
        this.K = (TextView) findViewById(R.id.time_current);
        this.N = new StringBuilder();
        this.O = new Formatter(this.N, Locale.getDefault());
        ImageButton imageButton6 = this.S;
        if (imageButton6 != null) {
            imageButton6.setOnClickListener(null);
            this.S.setEnabled(false);
        }
        ImageButton imageButton7 = this.T;
        if (imageButton7 != null) {
            imageButton7.setOnClickListener(null);
            this.T.setEnabled(false);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(MediaController.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MediaController.class.getName());
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
    }
}
