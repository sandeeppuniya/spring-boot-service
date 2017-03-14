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
     * Search a stored file in 'file store' by it's file id.
     *
     * @param fileId File id
     * @return List of found FileMetadata objects. Returns empty list if no files found.
     */
    List<FileMetadata> searchByFileId(String fileId);

    /**
     * Search a stored file in 'file store' by it's file id.
     *
     * @param fileId File id
     * @return List of found FileData objects. Returns empty list if no files found.
     */
    List<FileData> searchFileDataById(String fileId);

    /**
     * Finds all the files added in the file store since the last run of the Scheduler Service.
     *
     * @param currentTime    Current time when the job is being run.
     * @param repeatInterval (in seconds) Frequency of the job run by scheduler service. For current implementation, repeatInterval is set to 1 hour(3600 seconds).
     * @return List of file data if new files were added since last run of the scheduled job. Otherwise, returns empty list.
     */
    List<FileData> getNewAddedFile(long currentTime, int repeatInterval);
}
