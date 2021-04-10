package oculus.internal;

public final class HexString {
    public static byte[] decode(String encoded) {
        if (encoded.length() % 2 != 1) {
            byte[] data = new byte[(encoded.length() / 2)];
            for (int i = 0; i < encoded.length() / 2; i++) {
                int first = Character.digit(encoded.charAt(i * 2), 16);
                int second = Character.digit(encoded.charAt((i * 2) + 1), 16);
                if (first == -1 || second == -1) {
                    throw new IllegalArgumentException("invalid character");
                }
                data[i] = (byte) ((first << 4) + second);
            }
            return data;
        }
        throw new IllegalArgumentException("malformed input");
    }

    public static String encode(byte[] data) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            char first = Character.forDigit((data[i] >> 4) & 15, 16);
            char second = Character.forDigit(data[i] & 15, 16);
            hexStringBuffer.append(first);
            hexStringBuffer.append(second);
        }
        return hexStringBuffer.toString();
    }
}
