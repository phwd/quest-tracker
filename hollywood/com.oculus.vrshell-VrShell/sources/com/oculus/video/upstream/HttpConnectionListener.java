package com.oculus.video.upstream;

import com.oculus.android.exoplayer2.upstream.TransferListener;

public interface HttpConnectionListener<S> extends TransferListener<S> {
    void onConnect(String str, String str2, int i, long j);
}
