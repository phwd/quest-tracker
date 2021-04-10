package com.google.common.hash;

final class Hashing$ConcatenatedHashFunction extends AbstractCompositeHashFunction {
    private final int bits;

    public boolean equals(Object obj) {
        if (obj instanceof Hashing$ConcatenatedHashFunction) {
            Hashing$ConcatenatedHashFunction hashing$ConcatenatedHashFunction = (Hashing$ConcatenatedHashFunction) obj;
            if (this.bits == hashing$ConcatenatedHashFunction.bits && this.functions.length == hashing$ConcatenatedHashFunction.functions.length) {
                int i = 0;
                while (true) {
                    HashFunction[] hashFunctionArr = this.functions;
                    if (i >= hashFunctionArr.length) {
                        return true;
                    }
                    if (!hashFunctionArr[i].equals(hashing$ConcatenatedHashFunction.functions[i])) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.bits;
        for (HashFunction hashFunction : this.functions) {
            i ^= hashFunction.hashCode();
        }
        return i;
    }
}
