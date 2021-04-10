package com.oculus.android.exoplayer2;

import android.os.Handler;
import com.oculus.android.exoplayer2.audio.AudioRendererEventListener;
import com.oculus.android.exoplayer2.metadata.MetadataOutput;
import com.oculus.android.exoplayer2.text.TextOutput;
import com.oculus.android.exoplayer2.video.VideoRendererEventListener;

public interface RenderersFactory {
    Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput);
}
