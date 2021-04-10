package com.oculus.appmanager.info;

public class ApkUpdateInfoContract {

    /* access modifiers changed from: private */
    public interface IntegerBasedEnum {
        int asInt();
    }

    public enum UpdateType implements IntegerBasedEnum {
        DIRECT_APK(1179648),
        DIRECT_OBB(1114112),
        DIRECT_ASSET(1310720),
        STORE_FULL_APK(9568256),
        STORE_PATCH_APK(10616832),
        STORE_FULL_OBB(9502720),
        STORE_PATCH_OBB(10551296),
        STORE_FULL_ASSET(9699328),
        STORE_PATCH_ASSET(10747904),
        UNINSTALL(4194304);
        
        private final int mCode;

        private UpdateType(int i) {
            this.mCode = i;
        }

        @Override // com.oculus.appmanager.info.ApkUpdateInfoContract.IntegerBasedEnum
        public int asInt() {
            return this.mCode;
        }

        public static UpdateType fromInt(int i) {
            return (UpdateType) ApkUpdateInfoContract.fromEnumInt(UpdateType.class, i);
        }
    }

    /* access modifiers changed from: private */
    public static <T extends Enum & IntegerBasedEnum> T fromEnumInt(Class<T> cls, int i) {
        T[] enumConstants = cls.getEnumConstants();
        for (T t : enumConstants) {
            if (t.asInt() == i) {
                return t;
            }
        }
        throw new IllegalArgumentException(cls.getSimpleName() + " int : " + i);
    }
}
