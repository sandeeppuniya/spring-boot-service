package com.example.dao;

import com.example.service.FileData;
import com.example.service.FileMetadata;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Tests the FileStoreDaoImpl class.
 */
public class FileStoreDaoImplTest {

    /**
     * FileStoreDaoImpl
     */
    private FileStoreDaoImpl fileStoreDaoImpl;

    /**
     * Used for logging
     */
    private static final Logger LOG = Logger.getLogger(FileStoreDaoImplTest.class);

    /**
     * Test directory to store test files.
     */
    public static final String TEST_STORE_DIRECTORY = "/USERS/SANDEEPPUNIYA/DESKTOP/FILE STORE";

    /**
     * Byte array used to test input file data.
     */
    byte[] fileBytes;

    /**
     * Initialize test data
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        fileStoreDaoImpl = new FileStoreDaoImpl(TEST_STORE_DIRECTORY);
        fileBytes = new byte[]{72, 12, 56};
    }

    /**
     * Tear down test data.
     */
    @After
    public void tearDown() {
        File directory = new File(TEST_STORE_DIRECTORY);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (!file.delete()) {
                LOG.info("Failed to delete : " + file.getName());
            }
        }
    }

    @Test
    public void testInsert() throws IOException {
        String fileName = "TestFile";
        FileData file = new FileData(fileBytes, fileName);
        fileStoreDaoImpl.insert(file);
        List<FileMetadata> output = fileStoreDaoImpl.searchByFileName(fileName);
        Assert.assertEquals("File is not inserted into the file store.", file.getFileName(), output.get(0).getFileName());
    }

    @Test
    public void testSearchByFileName() throws IOException {
        String fileName = "TestFile";
        FileData file = new FileData(fileBytes, fileName);
        fileStoreDaoImpl.insert(file);
        List<FileMetadata> output = fileStoreDaoImpl.searchByFileName(fileName);
        Assert.assertEquals("File name is not same.", file.getFileName(), output.get(0).getFileName());
    }

    @Test
    public void testSearchByFileNameFileNotFound() {
        String fileId = "test";
        List<FileMetadata> output = fileStoreDaoImpl.searchByFileName(fileId);
        Assert.assertEquals("File should not have been found in the file store.", 0, output.size());
    }

    @Test
    public void testSearchByFileId() throws IOException {
        String fileId = String.valueOf(RandomUtils.nextInt());
        FileData file = new FileData(fileBytes, "TestFile");
        file.setFileId(fileId);
        fileStoreDaoImpl.insert(file);
        List<FileMetadata> output = fileStoreDaoImpl.searchByFileId(fileId);
        Assert.assertEquals("File name is not same.", file.getFileId(), output.get(0).getFileId());
    }

    @Test
    public void testSearchByFileIdFileNotFound() {
        String fileId = String.valueOf(RandomUtils.nextInt());
        List<FileMetadata> output = fileStoreDaoImpl.searchByFileId(fileId);
        Assert.assertEquals("File should not have been found in the file store.", 0, output.size());
    }

    @Test
    public void testSearchFileDataById() throws IOException {
        FileData file = new FileData(fileBytes, "TestFile");
        fileStoreDaoImpl.insert(file);
        List<FileData> output = fileStoreDaoImpl.searchFileDataById(file.getFileId());
        Assert.assertEquals("File name is not same.", file.getFileName(), output.get(0).getFileName());
    }

    @Test
    public void testSearchFileDataByIdFileNotFound() {
        String fileId = String.valueOf(RandomUtils.nextInt());
        List<FileData> output = fileStoreDaoImpl.searchFileDataById(fileId);
        Assert.assertEquals("File should not have been found in the file store.", 0, output.size());
    }
}
