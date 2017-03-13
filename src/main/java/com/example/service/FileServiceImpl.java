package com.example.service;

import com.example.dao.FileStoreDao;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.List;

/**
 * Implementation class for FileService.
 */
@EnableAutoConfiguration
@ComponentScan
public class FileServiceImpl implements FileService {

    /**
     * Data access object to insert, find or search files.
     */
    private FileStoreDao fileStoreDao;

    /**
     * Saves a given input file in the file system.
     *
     * @param file               Input file
     * @param applicationContext Application context object
     * @throws IOException Throws exception when fails to save the input file.
     */
    public void save(FileData file, ApplicationContext applicationContext) throws IOException {
        fileStoreDao = (FileStoreDao) applicationContext.getBean("fileStoreDaoImpl");
        fileStoreDao.insert(file);
    }

    /**
     * Finds a file using the name of the input file.
     *
     * @param fileName           Input file name
     * @param applicationContext Application context object
     * @return List of found files matching the input file name.
     */
    public List<FileMetadata> findFile(String fileName, ApplicationContext applicationContext) {
        fileStoreDao = (FileStoreDao) applicationContext.getBean("fileStoreDaoImpl");

        return fileStoreDao.searchByFileName(fileName);
    }
}
