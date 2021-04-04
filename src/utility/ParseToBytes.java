package utility;

import java.nio.ByteBuffer;

public interface ParseToBytes {
    static byte[] longToBytes(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        return buffer.array();
    }

    static byte[] doubleToBytes(double value) {
        final byte[] heightInByteArrays = new byte[8];
        long doubleToLong = Double.doubleToLongBits(value);
        heightInByteArrays[0] = (byte)((doubleToLong >> 56) & 0xff);
        heightInByteArrays[1] = (byte)((doubleToLong >> 48) & 0xff);
        heightInByteArrays[2] = (byte)((doubleToLong >> 40) & 0xff);
        heightInByteArrays[3] = (byte)((doubleToLong >> 32) & 0xff);
        heightInByteArrays[4] = (byte)((doubleToLong >> 24) & 0xff);
        heightInByteArrays[5] = (byte)((doubleToLong >> 16) & 0xff);
        heightInByteArrays[6] = (byte)((doubleToLong >> 8) & 0xff);
        heightInByteArrays[7] = (byte)((doubleToLong >> 0) & 0xff);
        return heightInByteArrays;
    }

    static byte[] floatToBytes (float value) {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }
}
