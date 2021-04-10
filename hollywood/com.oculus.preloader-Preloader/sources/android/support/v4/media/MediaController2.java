package android.support.v4.media;

import android.annotation.TargetApi;

@TargetApi(19)
public class MediaController2 implements AutoCloseable {

    public static abstract class ControllerCallback {
        public abstract void onDisconnected(MediaController2 mediaController2);
    }

    interface SupportLibraryImpl extends AutoCloseable {
    }
}
