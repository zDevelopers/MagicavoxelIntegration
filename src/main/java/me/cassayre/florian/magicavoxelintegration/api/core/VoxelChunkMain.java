package me.cassayre.florian.magicavoxelintegration.api.core;

import java.io.DataOutputStream;
import java.io.IOException;

public class VoxelChunkMain extends VoxelChunk
{
    private final static String CHUNK_NAME = "MAIN";

    private final static int MAX_SIZE = 128;

    private VoxelChunkSize size;
    private VoxelChunkArray array;

    private VoxelChunkPalette palette;

    public VoxelChunkMain(int x, int y, int z)
    {
        super(CHUNK_NAME);

        if(x > MAX_SIZE || y > MAX_SIZE || z > MAX_SIZE)
            throw new IllegalArgumentException("Coordinate cannot exceed " + MAX_SIZE);

        size = new VoxelChunkSize(x, y, z);
        array = new VoxelChunkArray(x, y, z);

        palette = new VoxelChunkPalette();
    }

    public void setVoxel(int x, int y, int z, int colorIndex)
    {
        array.setVoxel(x, y, z, colorIndex);
    }

    public void setColorIndex(int index, int r, int g, int b, int a)
    {
        palette.setColorIndex(index, r, g, b, a);
    }

    @Override
    public int getChunkContentSize()
    {
        return 0;
    }

    @Override
    public int getChildrenChunksContentSize()
    {
        return size.getChunkContentSize() + array.getChunkContentSize() + 4 * 3 * 2 + (palette.isEmpty() ? 0 : palette.getChunkContentSize() + 4 * 3);
    }

    @Override
    public void writeChunkContent(DataOutputStream out) throws IOException
    {
        size.writeChunk(out);
        array.writeChunk(out);

        if(!palette.isEmpty())
            palette.writeChunk(out);
    }
}
