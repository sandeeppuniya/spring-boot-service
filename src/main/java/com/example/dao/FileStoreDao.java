package com.example.dao;

import com.example.service.FileData;
import com.example.service.FileMetadata;

import java.io.IOException;
import java.util.List;

/**
 * File store data access object to insert file data and search stored files by their names.
 */
public interface FileStoreDao {

    /**
     * Inserts a file into the 'file store'.
     *
     * @param file Input file
     */
    void insert(FileData file) throws IOException;

    /**
     * Search a stored file in 'file store' by it's file name.
     *
     * @param fileName File name
     * @return List of found FileMetadata objects. Returns empty list if no files found.
     */
    List<FileMetadata> searchByFileName(String fileName);

    /**
     * Search a stored file in 'file store' by it's file name.
     *
     * @param fileId File id
     * @return List of found FileMetadata objects. Returns empty list if no files found.
     */
    List<FileMetadata> searchByFileId(String fileId);
}
