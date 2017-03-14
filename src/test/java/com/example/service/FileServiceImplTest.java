package com.example.service;

import com.example.dao.FileStoreDao;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FileServiceImplTest {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(FileServiceImplTest.class);

    /**
     * Mock for FileStoreDao
     */
    @Mock
    private FileStoreDao fileStoreDaoMock;

    /**
     * Mock for FileData
     */
    @Mock
    private FileData fileMock;

    /**
     * Mock for ApplicationContext
     */
    @Mock
    private ApplicationContext applicationContextMock;

    /**
     * FileServiceImpl
     */
    private FileServiceImpl fileServiceImpl;

    /**
     * /**
     * Initialize test dependencies.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        fileServiceImpl = new FileServiceImpl();
    }

    /**
     * Tests save() method.
     *
     * @throws IOException
     */
    @Test
    public void testSave() throws IOException {
        when(applicationContextMock.getBean("fileStoreDaoImpl")).thenReturn(fileStoreDaoMock);
        fileServiceImpl.save(fileMock, applicationContextMock);
        verify(fileStoreDaoMock, Mockito.times(1)).insert(Matchers.any());
    }

    /**
     * Tests findFile.
     *
     * @throws IOException IOException
     */
    @Test
    public void testFindFile() throws IOException {
        String fileName = "testFindFile";
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        when(applicationContextMock.getBean("fileStoreDaoImpl")).thenReturn(fileStoreDaoMock);
        when(fileStoreDaoMock.searchByFileName(Matchers.any(String.class))).thenReturn(fileMetadatas);
        Assert.assertEquals(fileServiceImpl.findFile(fileName, applicationContextMock), fileMetadatas);
    }

    /**
     * Tests findFileById.
     *
     * @throws IOException IOException
     */
    @Test
    public void testFindFileById() throws IOException {
        String fileId = String.valueOf(RandomUtils.nextInt());
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        when(applicationContextMock.getBean("fileStoreDaoImpl")).thenReturn(fileStoreDaoMock);
        when(fileStoreDaoMock.searchByFileName(Matchers.any(String.class))).thenReturn(fileMetadatas);
        Assert.assertEquals(fileServiceImpl.findFileById(fileId, applicationContextMock), fileMetadatas);
    }

    /**
     * Tests findFileData.
     *
     * @throws IOException IOException
     */
    @Test
    public void testFindFileData() throws IOException {
        String fileId = String.valueOf(RandomUtils.nextInt());
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        when(applicationContextMock.getBean("fileStoreDaoImpl")).thenReturn(fileStoreDaoMock);
        when(fileStoreDaoMock.searchByFileName(Matchers.any(String.class))).thenReturn(fileMetadatas);
        Assert.assertEquals(fileServiceImpl.findFileData(fileId, applicationContextMock), fileMetadatas);
    }
}
