package android.icu.text;

import sun.security.util.DerValue;

class CharsetRecog_UTF8 extends CharsetRecognizer {
    CharsetRecog_UTF8() {
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.CharsetRecognizer
    public String getName() {
        return "UTF-8";
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.CharsetRecognizer
    public CharsetMatch match(CharsetDetector det) {
        int trailBytes;
        boolean hasBOM = false;
        int numValid = 0;
        int numInvalid = 0;
        byte[] input = det.fRawInput;
        if (det.fRawLength >= 3 && (input[0] & 255) == 239 && (input[1] & 255) == 187 && (input[2] & 255) == 191) {
            hasBOM = true;
        }
        int i = 0;
        while (i < det.fRawLength) {
            byte b = input[i];
            if ((b & 128) != 0) {
                if ((b & 224) == 192) {
                    trailBytes = 1;
                } else if ((b & 240) == 224) {
                    trailBytes = 2;
                } else if ((b & 248) == 240) {
                    trailBytes = 3;
                } else {
                    numInvalid++;
                }
                while (true) {
                    i++;
                    if (i < det.fRawLength) {
                        if ((input[i] & DerValue.TAG_PRIVATE) == 128) {
                            trailBytes--;
                            if (trailBytes == 0) {
                                numValid++;
                                break;
                            }
                        } else {
                            numInvalid++;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            i++;
        }
        int confidence = 0;
        if (hasBOM && numInvalid == 0) {
            confidence = 100;
        } else if (hasBOM && numValid > numInvalid * 10) {
            confidence = 80;
        } else if (numValid > 3 && numInvalid == 0) {
            confidence = 100;
        } else if (numValid > 0 && numInvalid == 0) {
            confidence = 80;
        } else if (numValid == 0 && numInvalid == 0) {
            confidence = 15;
        } else if (numValid > numInvalid * 10) {
            confidence = 25;
        }
        if (confidence == 0) {
            return null;
        }
        return new CharsetMatch(det, this, confidence);
    }
}
