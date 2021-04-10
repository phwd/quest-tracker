package org.chromium.content.browser;

import J.N;
import android.media.AudioManager;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioFocusDelegateOculus implements AudioManager.OnAudioFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public EnumC2886hb f10907a = EnumC2886hb.NONE;
    public boolean b;
    public Map c = new HashMap();

    public AudioFocusDelegateOculus(AbstractC2715gb gbVar) {
    }

    public static AudioFocusDelegateOculus getInstance() {
        return AbstractC3057ib.f10147a;
    }

    public final boolean a() {
        return ((AudioManager) ContextUtils.getApplicationContext().getSystemService("audio")).requestAudioFocus(this, 3, this.f10907a.f10083J) == 1;
    }

    public void abandonAudioFocus(long j) {
        EnumC2886hb hbVar = EnumC2886hb.NONE;
        if (this.c.containsKey(Long.valueOf(j))) {
            EnumC2886hb hbVar2 = (EnumC2886hb) this.c.remove(Long.valueOf(j));
            if (this.c.isEmpty()) {
                ((AudioManager) ContextUtils.getApplicationContext().getSystemService("audio")).abandonAudioFocus(this);
                this.f10907a = hbVar;
                return;
            }
            EnumC2886hb hbVar3 = hbVar;
            for (Map.Entry entry : this.c.entrySet()) {
                if (hbVar3 == hbVar) {
                    hbVar3 = (EnumC2886hb) entry.getValue();
                }
                if (entry.getValue() == EnumC2886hb.EXCLUSIVE) {
                    hbVar3 = (EnumC2886hb) entry.getValue();
                }
            }
            if (hbVar3 != this.f10907a) {
                this.f10907a = hbVar3;
                a();
            }
        }
    }

    public boolean isFocusTransient() {
        return this.f10907a == EnumC2886hb.TRANSIENT;
    }

    public void onAudioFocusChange(int i) {
        if (!this.c.isEmpty()) {
            if (i == -3) {
                this.b = true;
                for (Long l : this.c.keySet()) {
                    long longValue = l.longValue();
                    N.MhoNY7uq(longValue, this);
                    N.MMxu6yiv(longValue, this);
                }
            } else if (i == -2) {
                for (Long l2 : this.c.keySet()) {
                    N.MPID$Al7(l2.longValue(), this);
                }
            } else if (i == -1) {
                for (Long l3 : this.c.keySet()) {
                    N.MPID$Al7(l3.longValue(), this);
                }
                this.c.clear();
                ((AudioManager) ContextUtils.getApplicationContext().getSystemService("audio")).abandonAudioFocus(this);
                this.f10907a = EnumC2886hb.NONE;
            } else if (i != 1) {
                AbstractC1220Ua0.f("MediaSession", "onAudioFocusChange called with unexpected value %d", Integer.valueOf(i));
            } else if (this.b) {
                for (Long l4 : this.c.keySet()) {
                    N.MoAsTWsj(l4.longValue(), this);
                }
                this.b = false;
            } else {
                for (Long l5 : this.c.keySet()) {
                    N.MbBPfVsi(l5.longValue(), this);
                }
            }
        }
    }

    public boolean requestAudioFocus(long j, boolean z) {
        EnumC2886hb hbVar = EnumC2886hb.EXCLUSIVE;
        EnumC2886hb hbVar2 = z ? EnumC2886hb.TRANSIENT : hbVar;
        this.c.put(Long.valueOf(j), hbVar2);
        EnumC2886hb hbVar3 = this.f10907a;
        if (hbVar2 == hbVar3) {
            return true;
        }
        if (hbVar3 != EnumC2886hb.NONE && hbVar2 != hbVar) {
            return true;
        }
        this.f10907a = hbVar2;
        return a();
    }

    public void tearDown(long j) {
        abandonAudioFocus(j);
    }
}
