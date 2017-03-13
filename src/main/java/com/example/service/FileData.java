package com.example.service;

/**
 * File data object.
 */
public class FileData extends FileMetadata {

    /**
     * A byte array of input file data.
     */
    private byte[] fileData;

    /**
     * File path representing the path to store a file in the 'file store'
     */
    private String filePath;

    /**
     * @param fileData Byte array of file data
     * @param fileName Name of the file
     */
    public FileData(byte[] fileData, String fileName) {
        super(fileName);
        this.fileData = fileData;
    }

    /**
     * Getter for file data.
     *
     * @return Byte array of file data.
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * Setter for file data array.
     *
     * @param fileData Byte array data
     */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    /**
     * Getter for filePath
     *
     * @return filePath Path of input file.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter for an input file path.
     *
     * @param filePath Path of input file.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
