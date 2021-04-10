package oculus.internal.license.store;

import com.oculus.license.License;
import java.nio.ByteBuffer;
import java.util.Collection;

public interface LicenseStoreObserver {
    default void onBeginInstall(Collection<ByteBuffer> collection) {
    }

    default void onInstalled(Collection<License> collection) {
    }

    default void onBeginDelete(Collection<Long> collection) {
    }

    default void onDeleted(Collection<Long> collection) {
    }

    default void onBeginRevoke(Collection<Long> collection) {
    }

    default void onRevoked(Collection<Long> collection) {
    }

    default void onBeginUnrevoke(Collection<Long> collection) {
    }

    default void onUnrevoked(Collection<Long> collection) {
    }

    default void onBeginChange() {
    }

    default void onChanged(boolean successful) {
    }
}
