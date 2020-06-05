package com.nurvadli.swagger.api.doc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerApiDocApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
    }

    /**
     * this method write JSON from this
     *
     * @url: http://localhost:8080/v2/api-docs
     * to file
     * @path: /resources/swagger/spec/json
     */
    @Test
    public void testHome() {
        String swagger = this.testRestTemplate.getForObject("/v2/api-docs", String.class);
        this.writeFile("spec.json", swagger);
    }

    private void writeFile(String fileName, String content) {
        File directori = new File("swagger");
        if (!directori.exists()) {
            try {
                directori.mkdir();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter("swagger/" + fileName);
            bw = new BufferedWriter(fw);
            bw.write(content);
            ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bw) {
                    bw.close();
                }
                if (null != fw) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
