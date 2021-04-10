package oculus.internal.license.store;

import android.content.ContentValues;
import com.oculus.license.License;

@FunctionalInterface
public interface LicenseLoader {
    License load(ContentValues contentValues) throws Exception;
}
