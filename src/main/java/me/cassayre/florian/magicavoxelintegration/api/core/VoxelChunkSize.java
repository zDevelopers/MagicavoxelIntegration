package me.cassayre.florian.magicavoxelintegration.api.core;

import me.cassayre.florian.magicavoxelintegration.api.utils.VoxelUtils;

import java.io.DataOutputStream;
import java.io.IOException;

public class VoxelChunkSize extends VoxelChunk
{
    private final static String CHUNK_NAME = "SIZE";

    private int sizeX, sizeY, sizeZ;

    public VoxelChunkSize(int x, int y, int z)
    {
        super(CHUNK_NAME);

        sizeX = x;
        sizeY = y;
        sizeZ = z;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public void setSizeX(int sizeX)
    {
        this.sizeX = sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public void setSizeY(int sizeY)
    {
        this.sizeY = sizeY;
    }

    public int getSizeZ()
    {
        return sizeZ;
    }

    public void setSizeZ(int sizeZ)
    {
        this.sizeZ = sizeZ;
    }

    @Override
    public int getChunkContentSize()
    {
        return 4 * 3;
    }

    @Override
    public int getChildrenChunksContentSize()
    {
        return 0;
    }

    @Override
    public void writeChunkContent(DataOutputStream out) throws IOException
    {
        out.writeInt(VoxelUtils.reverse(sizeX));
        out.writeInt(VoxelUtils.reverse(sizeY));
        out.writeInt(VoxelUtils.reverse(sizeZ));
    }
}
