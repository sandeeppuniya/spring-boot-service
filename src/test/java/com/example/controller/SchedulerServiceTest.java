package com.example.controller;

import com.example.dao.FileStoreDao;
import com.example.service.EmailService;
import com.example.service.FileData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for SchedulerService
 */
@ImportResource(locations = "application-context.xml")
public class SchedulerServiceTest {

    @Mock
    private EmailService emailServiceMock;

    @Mock
    private FileStoreDao fileStoreDaoImpl;

    @Mock
    private List<FileData> outputTest;

    private SchedulerService schedulerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        schedulerService = new SchedulerService();
        schedulerService.setEmailServiceImpl(emailServiceMock);
        schedulerService.setFileStoreDaoImpl(fileStoreDaoImpl);
        outputTest = new ArrayList<FileData>();
        FileData fileData = new FileData();
        outputTest.add(fileData);
    }

    @Test
    public void testRunJobWithoutMatchingFiles() {
        schedulerService.runJob();
        verify(fileStoreDaoImpl, times(1)).getNewAddedFile(anyLong(), anyInt());
        verify(emailServiceMock, times(0)).sendEmail(anyString());
    }

    @Test
    public void testRunJobWithMatchingFiles() {
        when(fileStoreDaoImpl.getNewAddedFile(anyLong(), anyInt())).thenReturn(outputTest);
        schedulerService.runJob();
        verify(fileStoreDaoImpl, times(1)).getNewAddedFile(anyLong(), anyInt());
        verify(emailServiceMock, times(1)).sendEmail(anyString());
    }
}
