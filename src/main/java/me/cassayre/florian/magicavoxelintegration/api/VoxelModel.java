package me.cassayre.florian.magicavoxelintegration.api;

import me.cassayre.florian.magicavoxelintegration.api.core.VoxelChunkMain;
import me.cassayre.florian.magicavoxelintegration.api.utils.VoxelUtils;

import java.io.*;

/**
 * Highest level class representing a Magicavoxel model.
 */
public class VoxelModel
{
    private final static String MAGIC_FORMAT = "VOX ";
    private final static int VERSION_NUMBER = 150;

    private final VoxelChunkMain main;

    /**
     * Creates a new model instance with the specified size. Note that the size is not editable.
     * @param x size X
     * @param y size Y
     * @param z size Z
     */
    public VoxelModel(int x, int y, int z)
    {
        main = new VoxelChunkMain(x, y, z);
    }

    /**
     * Sets a voxel color index. Setting a {@code colorIndex} value to 0 will remove the voxel.
     * @param x coordinate x
     * @param y coordinate y
     * @param z coordinate z
     * @param colorIndex index of the color
     */
    public void setVoxel(int x, int y, int z, int colorIndex)
    {
        main.setVoxel(x, y, z, colorIndex);
    }

    /**
     * Sets the color to the specified colorIndex. {@code colorIndex} cannot be equal to 0.
     * @param colorIndex the id of the color
     * @param r red
     * @param g green
     * @param b blue
     * @param a transparency
     */
    public void setColorIndex(int colorIndex, int r, int g, int b, int a)
    {
        main.setColorIndex(colorIndex, r, g, b, a);
    }

    /**
     * Writes the model in a output stream.
     * @param outputStream the output stream
     * @throws IOException
     */
    public void serialize(OutputStream outputStream) throws IOException
    {
        DataOutputStream out = new DataOutputStream(outputStream);

        out.write(MAGIC_FORMAT.getBytes());
        out.writeInt(VoxelUtils.reverse(VERSION_NUMBER));

        main.writeChunk(out);
    }

    /**
     * Save the model to the specified file.
     * @param file the file
     * @throws IOException
     */
    public void save(File file) throws IOException
    {
        OutputStream out = new FileOutputStream(file);

        serialize(out);

        out.close();
    }
}
