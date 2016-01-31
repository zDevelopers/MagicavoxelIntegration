package me.cassayre.florian.magicavoxelintegration.api.utils;

import java.nio.ByteBuffer;

public final class VoxelUtils
{
    // Util class
    private VoxelUtils()
    {
    }

    /**
     * Converts a big endian integer to little endian and vice versa.
     * @param n the integer
     * @return the converted integer
     */
    public static int reverse(int n)
    {
        byte[] bytes = ByteBuffer.allocate(4).putInt(n).array();
        byte[] b = new byte[bytes.length];

        for(int i = 0; i < bytes.length; i++)
        {
            b[bytes.length - 1 - i] = bytes[i];
        }

        return ByteBuffer.wrap(b).getInt();
    }
}
