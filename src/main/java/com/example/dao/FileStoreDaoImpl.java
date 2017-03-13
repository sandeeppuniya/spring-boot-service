package com.example.dao;

import com.example.service.FileData;
import com.example.service.FileMetadata;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Implementation class for file store data access object.
 */
@Component
public class FileStoreDaoImpl implements FileStoreDao {

    private static final Logger LOG = Logger.getLogger(FileStoreDaoImpl.class);

    /**
     * Store directory path that stores the input files.
     */
    public static final String STORE_DIRECTORY = "/USERS/SANDEEPPUNIYA/DESKTOP/FILE STORE";

    /**
     * Contains the selected path of directory to store file data. Default value is provided by STORE_DIRECTORY.
     */
    private String storeDirectoryPath;

    /**
     * Default constructor.
     */
    public FileStoreDaoImpl() {
        createDirectory(STORE_DIRECTORY);
        this.storeDirectoryPath = STORE_DIRECTORY;
    }

    /**
     * Constructor to set file store directory path to an input value.
     *
     * @param dirPath Input directory path for file store.
     */
    public FileStoreDaoImpl(String dirPath) {
        createDirectory(dirPath);
        this.storeDirectoryPath = dirPath;
    }

    /**
     * Inserts the content and metadata of an input file.
     *
     * @param file Input file
     */
    public void insert(FileData file) throws IOException {
        final String methodName = "insert() : Entry";
        LOG.info(methodName);
        saveFiledata(file);
    }

    /**
     * Search a given input file by the file name.
     *
     * @param fileName Input file name.
     * @return List of found FileMetadata objects. Otherwise, returns empty list.
     */
    public List<FileMetadata> searchByFileName(String fileName) {
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        File folder = new File(STORE_DIRECTORY + "/");
        File[] listOfFiles = folder.listFiles();
        InputStream inputStream = null;
        for (File file : listOfFiles) {
            if (!file.isHidden()) {
                File[] listOfFile = file.listFiles();
                for (File file1 : listOfFile) {
                    if (file1.getName().equals("metadata.properties")) {
                        Properties property = new Properties();
                        try {
                            inputStream = new FileInputStream(file1);
                            property.load(inputStream);
                            if (property.containsValue(fileName)) {
                                FileMetadata fileMetadata = new FileMetadata();
                                fileMetadata.setFileId(property.getProperty("fileId"));
                                fileMetadata.setFileName(property.getProperty("fileName"));
                                fileMetadatas.add(fileMetadata);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return fileMetadatas;
    }

    /**
     * Wrapper method to perform the actual load of file data in the file system. The steps involved are as follows:
     * 1. Create the given file directory with in STORE_DIRECTORY created when the FileStoreDaoImpl is initialized
     * during application deployment.
     * 2. Save the input file data
     * 3. Save the metadata of the input file.
     *
     * @param file Input file to be stored.
     */
    public void saveFiledata(FileData file) throws IOException {
        this.storeDirectoryPath = STORE_DIRECTORY;
        createFileDirectory(file);
        String filePath = file.getFilePath() + "/" + file.getFileName();
        fileLoad(file, filePath);
        saveFileMetadata(file);
    }

    /**
     * Saves the metadata of a given file in the file Store.
     *
     * @param file Input file
     */
    public void saveFileMetadata(FileData file) {
        String methodName = "saveFileMetadata() : ";
        System.out.println(methodName + "Entry");
        Properties properties = new Properties();
        properties.setProperty("fileId", file.getFileId());
        properties.setProperty("fileName", file.getFileName());
        if (file.getFilePath() != null) {
            File file1 = new File(new File(file.getFilePath()), "metadata.properties");

            OutputStream out = null;
            try {
                out = new FileOutputStream(file1);
                properties.store(out, "Meta data");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Created directory to store an input file within the main STORE_DIRECTORY.
     *
     * @param file Input file.
     */
    private void createFileDirectory(FileData file) {
        String filePath = createFilePath(file);
        file.setFilePath(filePath);
        createDirectory(filePath);
    }

    /**
     * Generic method to create directory for a given file store path.
     *
     * @param dirPath Input directory path.
     */
    public void createDirectory(String dirPath) {
        File file = new File(dirPath);
        file.mkdirs();
    }

    /**
     * Creates input file path from the STORE_DIRECTORY value.
     *
     * @param file Input file.
     * @return Output file path
     */
    private String createFilePath(FileData file) {
        String filePath = storeDirectoryPath + "/";
        StringBuilder sb = new StringBuilder(filePath);
        sb.append(file.getFileId());
        return sb.toString();
    }

    /**
     * Loads file data in the 'file store'
     *
     * @param file     Input file
     * @param filePath Path to load the file data.
     */
    private void fileLoad(FileData file, String filePath) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(file.getFileData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
        }
    }
}
