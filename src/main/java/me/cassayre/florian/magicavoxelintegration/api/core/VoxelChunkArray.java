package me.cassayre.florian.magicavoxelintegration.api.core;

import me.cassayre.florian.magicavoxelintegration.api.utils.VoxelUtils;

import java.io.DataOutputStream;
import java.io.IOException;

public class VoxelChunkArray extends VoxelChunk
{
    private final static String CHUNK_NAME = "XYZI";

    private final byte[][][] array;
    private final int sizeX, sizeY, sizeZ;

    public VoxelChunkArray(int x, int y, int z)
    {
        super(CHUNK_NAME);

        array = new byte[x][y][z];

        sizeX = x;
        sizeY = y;
        sizeZ = z;
    }

    @Override
    public int getChunkContentSize()
    {
        return 4 + 4 * getTotalVoxels();
    }

    @Override
    public int getChildrenChunksContentSize()
    {
        return 0;
    }

    @Override
    public void writeChunkContent(DataOutputStream out) throws IOException
    {
        // Total size of the array.
        out.writeInt(VoxelUtils.reverse(getTotalVoxels()));

        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                for(int z = 0; z < sizeZ; z++)
                {
                    int colorIndex = array[x][y][z];

                    if(colorIndex == 0)
                        continue;

                    out.writeByte(x);
                    out.writeByte(y);
                    out.writeByte(z);

                    out.writeByte(colorIndex);
                }
            }
        }
    }

    private int getTotalVoxels()
    {
        int total = 0;

        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                for(int z = 0; z < sizeZ; z++)
                {
                    if(array[x][y][z] != 0)
                        total++;
                }
            }
        }

        return total;
    }

    public void setVoxel(int x, int y, int z, int colorIndex)
    {
        if(x < 0 || y < 0 || z < 0 || x >= sizeX || y >= sizeY || z >= sizeZ)
            throw new IllegalArgumentException("Coordinate out of bound");

        if(colorIndex < 0 || colorIndex >= VoxelChunkPalette.PALETTE_SIZE)
            throw new IllegalArgumentException("Color index out of bound");

        array[x][y][z] = (byte) colorIndex;
    }
}
