package com.example.service;

import java.io.Serializable;
import java.util.UUID;

/**
 * FileMetadata contains the meta data of a file uploaded by the client.
 */
public class FileMetadata implements Serializable {

    private static final long serialVersionUID = 100000000000L;

    /**
     * A uniquely identifiable file id.
     */
    protected String fileId;

    /**
     * Name of the input file.
     */
    protected String fileName;

    /**
     * Represents the time in milliseconds when the file is uploaded to the API.
     */
    protected long creationTime;

    /**
     * Default constructor
     */
    public FileMetadata() {
        super();
    }

    /**
     * Constructor with input file name.
     *
     * @param fileName Input file name.
     */
    public FileMetadata(String fileName) {
        this.fileId = UUID.randomUUID().toString();
        this.fileName = fileName;
    }

    /**
     * Getter for file id.
     *
     * @return File id which is a uniquely identifiable number.
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Setter for file id.
     *
     * @param fileId File id.
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * Getter for file name.
     *
     * @return File name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setter for file name.
     *
     * @param fileName File name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter for creation date
     *
     * @return creationTime Creation date
     */
    public long getCreationTime() {
        return creationTime;
    }

    /**
     * Setter for creation date
     *
     * @param creationTime Input creation date
     */
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
