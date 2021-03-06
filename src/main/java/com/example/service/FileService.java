package com.example.service;

import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * FileService to service the save and find functionality for a given file.
 */
public interface FileService {

    /**
     * Method to save a given file.
     *
     * @param file               Input file
     * @param applicationContext Application object
     * @throws IOException Throws IOException when fails to save the file.
     */
    void save(FileData file, ApplicationContext applicationContext) throws IOException;

    /**
     * Finds a given file on the basis of the input file name.
     *
     * @param fileName           Input file name
     * @param applicationContext Application Context
     * @return List of matching files in the file store.
     */
    List<FileMetadata> findFile(String fileName, ApplicationContext applicationContext);

    /**
     * Finds a given file on the basis of the input file id.
     *
     * @param fileId             Input file id
     * @param applicationContext Application Context
     * @return List of matching files in the file store.
     */
    List<FileMetadata> findFileById(String fileId, ApplicationContext applicationContext);

    /**
     * Finds a file using file id and returns the file data.
     *
     * @param fileId             Input file id
     * @param applicationContext Application Context
     * @return List of matching file data in the file store.
     */
    List<FileData> findFileData(String fileId, ApplicationContext applicationContext);

}
