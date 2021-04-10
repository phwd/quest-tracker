package defpackage;

import com.oculus.os.Version;

/* renamed from: xv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5674xv {
    public static int a(int i, int i2) {
        if (i == 1) {
            switch (i2) {
                case 1:
                    return 10;
                case 4:
                    return 11;
                case Version.VERSION_8:
                    return 12;
                case Version.VERSION_16:
                    return 13;
                case Version.VERSION_32:
                    return 20;
                case 64:
                    return 21;
                case 128:
                    return 22;
                case 256:
                    return 30;
                case 512:
                    return 31;
                case 1024:
                    return 32;
                case 2048:
                    return 40;
                case 4096:
                    return 41;
                case 8192:
                    return 42;
                case 16384:
                    return 50;
                case 32768:
                    return 51;
                case 65536:
                    return 52;
                default:
                    throw new C5504wv(null);
            }
        } else if (i != 6) {
            if (i != 7) {
                if (i != 8) {
                    throw new C5504wv(null);
                } else if (i2 == 1 || i2 == 2) {
                    return 30;
                } else {
                    switch (i2) {
                        case 4:
                        case Version.VERSION_8:
                            return 60;
                        case Version.VERSION_16:
                        case Version.VERSION_32:
                            return 63;
                        case 64:
                        case 128:
                            return 90;
                        case 256:
                        case 512:
                            return 93;
                        case 1024:
                        case 2048:
                            return 120;
                        case 4096:
                        case 8192:
                            return 123;
                        case 16384:
                        case 32768:
                            return 150;
                        case 65536:
                        case 131072:
                            return 153;
                        case 262144:
                        case 524288:
                            return 156;
                        case 1048576:
                        case 2097152:
                            return 180;
                        case 4194304:
                        case 8388608:
                            return 183;
                        case 16777216:
                        case 33554432:
                            return 186;
                        default:
                            throw new C5504wv(null);
                    }
                }
            } else if (i2 == 1) {
                return 10;
            } else {
                if (i2 == 2) {
                    return 11;
                }
                switch (i2) {
                    case 4:
                        return 20;
                    case Version.VERSION_8:
                        return 21;
                    case Version.VERSION_16:
                        return 30;
                    case Version.VERSION_32:
                        return 31;
                    case 64:
                        return 40;
                    case 128:
                        return 41;
                    case 256:
                        return 50;
                    case 512:
                        return 51;
                    case 1024:
                        return 52;
                    case 2048:
                        return 60;
                    case 4096:
                        return 61;
                    case 8192:
                        return 62;
                    default:
                        throw new C5504wv(null);
                }
            }
        } else if (i2 == 1) {
            return 0;
        } else {
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            throw new C5504wv(null);
        }
    }

    public static int b(int i, int i2) {
        if (i != 1) {
            if (i != 6) {
                if (i != 7) {
                    if (i != 8) {
                        throw new C5504wv(null);
                    } else if (i2 == 1) {
                        return 16;
                    } else {
                        if (i2 == 2 || i2 == 4096) {
                            return 17;
                        }
                        throw new C5504wv(null);
                    }
                } else if (i2 == 1) {
                    return 12;
                } else {
                    if (i2 == 2) {
                        return 13;
                    }
                    if (i2 == 4) {
                        return 14;
                    }
                    if (i2 == 8) {
                        return 15;
                    }
                    if (i2 == 4096) {
                        return 14;
                    }
                    if (i2 == 8192) {
                        return 15;
                    }
                    throw new C5504wv(null);
                }
            } else if (i2 == 1) {
                return 11;
            } else {
                throw new C5504wv(null);
            }
        } else if (i2 == 1) {
            return 0;
        } else {
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            if (i2 == 16) {
                return 4;
            }
            if (i2 == 32) {
                return 5;
            }
            if (i2 == 64) {
                return 6;
            }
            throw new C5504wv(null);
        }
    }
}
