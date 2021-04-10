package org.chromium.components.browser_ui.photo_picker;

import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PickerVideoPlayer extends FrameLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnSystemUiVisibilityChangeListener {
    public static final /* synthetic */ int F = 0;
    public View G;
    public Context H;
    public final ImageView I;

    /* renamed from: J  reason: collision with root package name */
    public final TextView f10816J;
    public final VideoView K;
    public MediaPlayer L;
    public final View M;
    public boolean N;
    public final View O;
    public final View P;
    public final ImageView Q;
    public final ImageView R;
    public boolean S = true;
    public final ImageView T;
    public boolean U;
    public boolean V;
    public final TextView W;
    public final LinearLayout a0;
    public final SeekBar b0;
    public boolean c0;
    public boolean d0;
    public int e0;
    public C3898nV f0;

    public PickerVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H = context;
        LayoutInflater.from(context).inflate(R.layout.f42240_resource_name_obfuscated_RES_2131624533, this);
        ImageView imageView = (ImageView) findViewById(R.id.back_button);
        this.I = imageView;
        this.f10816J = (TextView) findViewById(R.id.video_file_name);
        this.K = (VideoView) findViewById(R.id.video_player);
        View findViewById = findViewById(R.id.video_overlay_container);
        this.M = findViewById;
        this.O = findViewById(R.id.video_controls);
        this.P = findViewById(R.id.video_controls_gradient);
        ImageView imageView2 = (ImageView) findViewById(R.id.video_player_play_button);
        this.Q = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R.id.mute);
        this.R = imageView3;
        imageView3.setImageResource(R.drawable.f33030_resource_name_obfuscated_RES_2131231343);
        ImageView imageView4 = (ImageView) findViewById(R.id.fullscreen);
        this.T = imageView4;
        this.W = (TextView) findViewById(R.id.remaining_time);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        this.b0 = seekBar;
        this.a0 = (LinearLayout) findViewById(R.id.fast_forward_message);
        imageView.setOnClickListener(this);
        findViewById.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        this.f0 = new C3898nV(context, new VC0(this, null));
        findViewById.setOnTouchListener(new PC0(this));
    }

    public boolean a() {
        if (getVisibility() != 0) {
            return false;
        }
        setVisibility(8);
        f();
        this.K.setMediaController(null);
        this.R.setImageResource(R.drawable.f33030_resource_name_obfuscated_RES_2131231343);
        return true;
    }

    public final void b(int i) {
        if (i != 0) {
            this.O.animate().cancel();
            this.P.animate().cancel();
            this.Q.animate().cancel();
            int i2 = 2500;
            int i3 = 0;
            long j = (long) (i != 3 ? 2500 : 0);
            this.P.animate().alpha(0.0f).setStartDelay(j).setDuration((long) 1000);
            ViewPropertyAnimator startDelay = this.O.animate().alpha(0.0f).setStartDelay(j);
            long j2 = (long) 750;
            startDelay.setDuration(j2).setListener(new RC0(this));
            if (i != 3) {
                if (i == 1) {
                    i2 = 250;
                }
                i3 = i2;
            }
            this.Q.animate().alpha(0.0f).setStartDelay((long) i3).setDuration(j2).setListener(new SC0(this));
        }
    }

    public final /* synthetic */ void c() {
        h();
        this.M.setVisibility(0);
    }

    public final void d(boolean z, int i) {
        this.O.animate().cancel();
        this.P.animate().cancel();
        this.Q.animate().cancel();
        if (this.K.isPlaying()) {
            this.d0 = true;
            PostTask.b(Zo1.f9374a, new NC0(this), 250);
        }
        this.N = true;
        if (!z) {
            this.O.setAlpha(1.0f);
            this.P.setAlpha(1.0f);
            this.Q.setAlpha(1.0f);
            this.R.setClickable(true);
            this.T.setClickable(true);
            this.Q.setClickable(true);
            b(i);
            return;
        }
        long j = (long) 250;
        this.P.animate().alpha(1.0f).setStartDelay(0).setDuration(j);
        this.O.animate().alpha(1.0f).setStartDelay(0).setDuration((long) 500).setListener(new TC0(this, i));
        this.Q.animate().alpha(1.0f).setStartDelay(0).setDuration(j).setListener(new UC0(this));
    }

    public final void e() {
        this.L.start();
        this.Q.setImageResource(R.drawable.f32370_resource_name_obfuscated_RES_2131231277);
        this.Q.setContentDescription(this.H.getResources().getString(R.string.f45700_resource_name_obfuscated_RES_2131951887));
        d(false, 1);
    }

    public final void f() {
        this.d0 = false;
        this.L.pause();
        g();
        d(false, 0);
    }

    public final void g() {
        this.Q.setImageResource(R.drawable.f32540_resource_name_obfuscated_RES_2131231294);
        this.Q.setContentDescription(this.H.getResources().getString(R.string.f45720_resource_name_obfuscated_RES_2131951889));
    }

    public final void h() {
        this.O.setLayoutParams(new FrameLayout.LayoutParams(this.K.getMeasuredWidth(), this.K.getMeasuredHeight()));
    }

    public final void i() {
        try {
            String m = C4368qD.m(Long.valueOf((long) this.K.getCurrentPosition()));
            String m2 = C4368qD.m(Long.valueOf((long) this.K.getDuration()));
            int i = 0;
            this.W.setText(this.H.getResources().getString(R.string.f58980_resource_name_obfuscated_RES_2131953215, m, m2));
            this.W.setContentDescription(this.H.getResources().getString(R.string.f45730_resource_name_obfuscated_RES_2131951890, m, m2));
            if (this.K.getDuration() != 0) {
                i = (this.K.getCurrentPosition() * 100) / this.K.getDuration();
            }
            this.b0.setProgress(i);
            if (this.K.isPlaying() && this.d0) {
                this.d0 = true;
                PostTask.b(Zo1.f9374a, new NC0(this), 250);
            }
        } catch (IllegalStateException unused) {
        }
    }

    public final void j(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.L.seekTo((long) i, 3);
        } else {
            this.K.seekTo(i);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.video_player_play_button) {
            if (this.K.isPlaying()) {
                f();
            } else {
                e();
            }
        } else if (id == R.id.back_button) {
            a();
        } else if (id == R.id.mute) {
            boolean z = !this.S;
            this.S = z;
            if (z) {
                this.L.setVolume(1.0f, 1.0f);
                this.R.setImageResource(R.drawable.f33030_resource_name_obfuscated_RES_2131231343);
                this.R.setContentDescription(this.H.getResources().getString(R.string.f45570_resource_name_obfuscated_RES_2131951874));
                return;
            }
            this.L.setVolume(0.0f, 0.0f);
            this.R.setImageResource(R.drawable.f33020_resource_name_obfuscated_RES_2131231342);
            this.R.setContentDescription(this.H.getResources().getString(R.string.f46350_resource_name_obfuscated_RES_2131951952));
        } else if (id == R.id.fullscreen) {
            this.V = true;
            if (!this.U) {
                this.G.setOnSystemUiVisibilityChangeListener(this);
                int systemUiVisibility = this.G.getSystemUiVisibility();
                this.e0 = systemUiVisibility;
                this.G.setSystemUiVisibility(systemUiVisibility | 2048 | 4 | 2 | 1024 | 1);
                return;
            }
            this.G.setSystemUiVisibility(this.e0);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.O.getVisibility() != 8) {
            ThreadUtils.d(new KC0(this));
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            j(Math.round((((float) i) / 100.0f) * ((float) this.K.getDuration())));
            i();
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        d(false, 0);
        if (this.K.isPlaying()) {
            f();
            this.c0 = true;
        }
        this.a0.setVisibility(0);
        this.Q.setVisibility(8);
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        b(this.c0 ? 1 : 2);
        if (this.c0) {
            e();
            this.c0 = false;
        }
        this.a0.setVisibility(8);
        this.Q.setVisibility(0);
    }

    public void onSystemUiVisibilityChange(int i) {
        if ((i & 4) == 0) {
            this.G.setOnSystemUiVisibilityChangeListener(null);
            this.T.setImageResource(R.drawable.f30300_resource_name_obfuscated_RES_2131231070);
            this.T.setContentDescription(this.H.getResources().getString(R.string.f45490_resource_name_obfuscated_RES_2131951866));
            this.U = false;
            if (!this.V) {
                getHandler().post(new MC0(this));
                return;
            }
        } else {
            this.T.setImageResource(R.drawable.f30290_resource_name_obfuscated_RES_2131231069);
            this.T.setContentDescription(this.H.getResources().getString(R.string.f45410_resource_name_obfuscated_RES_2131951858));
            this.U = true;
        }
        h();
        this.V = false;
    }
}
