package com.example.service;

import com.example.dao.FileStoreDao;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.After;
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

public class FileServiceImplTest {

    private static final Logger LOG = Logger.getLogger(FileServiceImplTest.class);

    @Mock
    private FileStoreDao fileStoreDao;

    @Mock
    private FileData file;

    @Mock
    private ApplicationContext applicationContext;

    private FileServiceImpl fileServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        fileServiceImpl = new FileServiceImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindFile() throws IOException {
        String fileName = "testFindFile";
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        Mockito.when(applicationContext.getBean("fileStoreDaoImpl")).thenReturn(fileStoreDao);
        Mockito.when(fileStoreDao.searchByFileName(Matchers.any(String.class))).thenReturn(fileMetadatas);
        Assert.assertEquals(fileServiceImpl.findFile(fileName, applicationContext), fileMetadatas);
    }

    @Test
    public void testFindFileById() throws IOException {
        String fileId = String.valueOf(RandomUtils.nextInt());
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        Mockito.when(applicationContext.getBean("fileStoreDaoImpl")).thenReturn(fileStoreDao);
        Mockito.when(fileStoreDao.searchByFileName(Matchers.any(String.class))).thenReturn(fileMetadatas);
        Assert.assertEquals(fileServiceImpl.findFileById(fileId, applicationContext), fileMetadatas);
    }

    @Test
    public void testFindFileData() throws IOException {
        String fileId = String.valueOf(RandomUtils.nextInt());
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        Mockito.when(applicationContext.getBean("fileStoreDaoImpl")).thenReturn(fileStoreDao);
        Mockito.when(fileStoreDao.searchByFileName(Matchers.any(String.class))).thenReturn(fileMetadatas);
        Assert.assertEquals(fileServiceImpl.findFileData(fileId, applicationContext), fileMetadatas);
    }
}
