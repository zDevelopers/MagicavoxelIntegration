package me.cassayre.florian.magicavoxelintegration.api.core;

import me.cassayre.florian.magicavoxelintegration.api.utils.VoxelUtils;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A Magicavoxel data storage chunk.
 * A chunk is composed of three parts :
 * <p>- the header which stores the length of the content and the length of the children content</p>
 * <p>- the content</p>
 * <p>- the children chunks</p>
 */
public abstract class VoxelChunk
{
    /**
     * This chunk id.
     */
    private final String MAGIC_ID;

    /**
     * Create a new instance of a chunk with the specified id.
     * @param id
     */
    public VoxelChunk(String id)
    {
        MAGIC_ID = id;
    }

    /**
     * Writes the entire chunk in the output stream.
     * @param out the output stream
     * @throws IOException
     */
    public final void writeChunk(DataOutputStream out) throws IOException
    {
        // Magic ID value. May be MAIN, SIZE, XYZI or RGBA.
        out.writeBytes(MAGIC_ID);

        // Size of the chunks.
        out.writeInt(VoxelUtils.reverse(getChunkContentSize()));
        out.writeInt(VoxelUtils.reverse(getChildrenChunksContentSize()));

        // Content of the chunks.
        writeChunkContent(out);
    }

    /**
     * Returns the size of the chunk's content.
     * @return size of content
     */
    public abstract int getChunkContentSize();

    /**
     * Returns the size of the chunk's children content.
     * @return size of children content
     */
    public abstract int getChildrenChunksContentSize();

    /**
     * Writes the content of the chunk.
     * @param out the output stream
     * @throws IOException
     */
    protected abstract void writeChunkContent(DataOutputStream out) throws IOException;
}
