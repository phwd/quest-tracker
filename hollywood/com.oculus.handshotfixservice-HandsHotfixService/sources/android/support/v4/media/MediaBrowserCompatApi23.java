package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/* access modifiers changed from: package-private */
@RequiresApi(23)
public class MediaBrowserCompatApi23 {

    /* access modifiers changed from: package-private */
    public interface ItemCallback {
        void onError(@NonNull String str);

        void onItemLoaded(Parcel parcel);
    }

    public static Object createItemCallback(ItemCallback callback) {
        return new ItemCallbackProxy(callback);
    }

    public static void getItem(Object browserObj, String mediaId, Object itemCallbackObj) {
        ((MediaBrowser) browserObj).getItem(mediaId, (MediaBrowser.ItemCallback) itemCallbackObj);
    }

    /* access modifiers changed from: package-private */
    public static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser.ItemCallback {
        protected final T mItemCallback;

        public ItemCallbackProxy(T callback) {
            this.mItemCallback = callback;
        }

        public void onItemLoaded(MediaBrowser.MediaItem item) {
            if (item == null) {
                this.mItemCallback.onItemLoaded(null);
                return;
            }
            Parcel parcel = Parcel.obtain();
            item.writeToParcel(parcel, 0);
            this.mItemCallback.onItemLoaded(parcel);
        }

        public void onError(@NonNull String itemId) {
            this.mItemCallback.onError(itemId);
        }
    }

    private MediaBrowserCompatApi23() {
    }
}
