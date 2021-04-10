package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.Mu;
import X.QC;
import android.content.Context;
import android.media.AudioManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class AudioCollector implements ICollector {
    public static final String AUDIO_EVENT_NAME = "oculus_mobile_os_audio";
    public static final String EMPTY_REPLY = "empty-reply";
    public static final String ILLEGAL_REPLY = "illegal-reply";
    public static final String TAG = "AudioCollector";
    public static volatile AudioCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_AudioCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final AudioManager mAudioManager;
    public final SampledMetric mBTHeadphonesOn = new SampledMetric();
    public final SampledMetric mHeadphonesOn = new SampledMetric();
    public final SampledMetric mMusicStreamMuted = new SampledMetric();
    public final SampledMetric mMusicStreamVolumeIndex = new SampledMetric();
    public int mSampleCounter = 0;
    public final SampledString mSndDeviceIn = new SampledString();
    public final SampledString mSndDeviceOut = new SampledString();

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
    }

    public static String A00(String str) {
        try {
            int indexOf = str.indexOf(61);
            if (indexOf != -1) {
                int i = indexOf + 1;
                if (!str.substring(i).isEmpty()) {
                    return str.substring(i);
                }
                throw new StringIndexOutOfBoundsException();
            }
            throw new IllegalArgumentException("getParameters reply should contain '='");
        } catch (StringIndexOutOfBoundsException unused) {
            return EMPTY_REPLY;
        } catch (IllegalArgumentException e) {
            Mu.A03(TAG, e.getMessage(), e);
            return ILLEGAL_REPLY;
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
        if (this.mSampleCounter > 0) {
            Event event = new Event(AUDIO_EVENT_NAME);
            event.A04("audio_bluetooth_headphones_on", this.mBTHeadphonesOn);
            event.A04("audio_headphones_on", this.mHeadphonesOn);
            event.A04("audio_music_stream_volume", this.mMusicStreamVolumeIndex);
            event.A04("audio_music_stream_muted", this.mMusicStreamMuted);
            event.A02("nb_samples", this.mSampleCounter);
            event.A05("snd_device_in", this.mSndDeviceIn);
            event.A05("snd_device_out", this.mSndDeviceOut);
            ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event.mName, event.mContent);
            this.mBTHeadphonesOn.A00();
            this.mHeadphonesOn.A00();
            this.mMusicStreamVolumeIndex.A00();
            this.mMusicStreamMuted.A00();
            this.mSndDeviceIn.mStringFrequency.clear();
            this.mSndDeviceOut.mStringFrequency.clear();
            this.mSampleCounter = 0;
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            this.mBTHeadphonesOn.A01(audioManager.isBluetoothA2dpOn() ? 1 : 0);
            this.mHeadphonesOn.A01(this.mAudioManager.isWiredHeadsetOn() ? 1 : 0);
            this.mMusicStreamVolumeIndex.A01(this.mAudioManager.getStreamVolume(3));
            this.mMusicStreamMuted.A01(this.mAudioManager.isStreamMute(3) ? 1 : 0);
            this.mSndDeviceIn.A00(A00(this.mAudioManager.getParameters("snd_device_in")));
            this.mSndDeviceOut.A00(A00(this.mAudioManager.getParameters("snd_device_out")));
            this.mSampleCounter++;
        }
    }

    @Inject
    public AudioCollector(AbstractC0247Xu xu) {
        QC qc = new QC(2, xu);
        this._UL_mInjectionContext = qc;
        this.mAudioManager = (AudioManager) ((Context) AbstractC0096Hu.A03(1, 3, qc)).getSystemService("audio");
    }
}
