package com.capgemini.app;
import java.io.File;
import java.io.IOException;

import com.capgemini.CamelApplication;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes = {Application.class,CamelApplication.class
})
class ApplicationTests extends CamelTestSupport {

	private static final String C_OUTBOX = "C:/outbox";
    private static final String C_INBOX = "C:/inbox";
    private static final String TEST_TXT = "test.txt";
    private static final String TEST2_XML = "test2.xml";

    static ConfigurableApplicationContext context;


    @BeforeClass
    static public void  setup(){
        SpringApplication springApplication = new SpringApplicationBuilder()           
                .sources(CamelApplication.class)
                .build();
        context = springApplication.run();
    }


    @BeforeEach
    void createFolders(){
        File toBeCreatedInBoxFolder = new File(C_INBOX);
        File toBeCreatedOutBoxFolder = new File(C_OUTBOX);
        toBeCreatedInBoxFolder.mkdir();
        toBeCreatedOutBoxFolder.mkdir();
    }

    @AfterEach
    void deleteFolders(){
        deleteDir(new File(C_INBOX));
        deleteDir(new File(C_OUTBOX));
    }

    public static boolean deleteDir(File dir){
        File[] files = dir.listFiles();
        if(files != null){
            for(File file : files){
                if(file.isDirectory()){
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }
        return dir.delete();
    }

	@Test
    public void checkFileExistsInOutputDirectory() throws InterruptedException, IOException
    {
        File file = new File(C_INBOX,TEST_TXT);
        File file2 = new File(C_INBOX,TEST2_XML);

        file.createNewFile();
        file2.createNewFile();

        Thread.sleep(5000);

        File outbox = new File(C_OUTBOX);

        assertTrue(outbox.isDirectory());
        assertEquals(2,outbox.listFiles().length);
        assertEquals(TEST_TXT, file.getName());
        assertEquals(TEST2_XML, file2.getName());

    }

}
