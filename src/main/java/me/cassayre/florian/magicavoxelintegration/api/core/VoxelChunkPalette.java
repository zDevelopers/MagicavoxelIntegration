package me.cassayre.florian.magicavoxelintegration.api.core;

import java.io.DataOutputStream;
import java.io.IOException;

public class VoxelChunkPalette extends VoxelChunk
{
    private final static String CHUNK_NAME = "RGBA";

    private final static int PALETTE_SIZE = 256;

    private final int[] palette = new int[PALETTE_SIZE];

    public VoxelChunkPalette()
    {
        super(CHUNK_NAME);
    }

    public void setColorIndex(int index, int r, int g, int b, int a)
    {
        if(index == 0)
            throw new IllegalArgumentException("Color index cannot be null (reserved for empty voxel)");

        if(index < 0 || index >= PALETTE_SIZE)
            throw new IllegalArgumentException("Color index out of bounds");

        if(r < 0 || r >= 256 || g < 0 || g >= 256 || b < 0 || b >= 256 || a < 0 || a >= 256)
            throw new IllegalArgumentException("Color value out of bounds");

        int color = r * 16 << 2 + g * 16 << 1 + b * 16 + a;

        palette[index] = color;
    }

    public boolean isEmpty()
    {
        for(int i = 0; i < PALETTE_SIZE; i++)
        {
            if(palette[i] != 0)
                return false;
        }
        return true;
    }

    @Override
    public int getChunkContentSize()
    {
        return 4 * 256;
    }

    @Override
    public int getChildrenChunksContentSize()
    {
        return 0;
    }

    @Override
    public void writeChunkContent(DataOutputStream out) throws IOException
    {
        for(int i = 0; i < PALETTE_SIZE; i++)
        {
            out.writeInt(palette[i]);
        }
    }
}
