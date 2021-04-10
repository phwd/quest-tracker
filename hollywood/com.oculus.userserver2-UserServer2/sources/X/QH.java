package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum QH {
    DATA_DIRECTORY,
    CACHE_DIRECTORY,
    FILES_DIRECTORY,
    ABSOLUTE_PATH_OVERRIDE,
    EXTERNAL_CACHE_DIRECTORY,
    EXTERNAL_FILES_DIRECTORY,
    EXTERNAL_MEDIA_DIRECTORY,
    DATABASE_DIRECTORY
}
