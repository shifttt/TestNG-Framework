package com.hrms.utils;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {


    /**
     * This method read any given property file
     * @param filePath
     */

    static Properties prop;

    public static Properties readProperties(String filePath){
        try {
            FileInputStream file = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(file);
            file.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }

    /**
     * This method retrieves single value based on the specifies key
     * @param key
     * @return
     */
    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }

    @Test
    public void test(){
        System.out.println(System.getProperty("user.dir"));
    }

}
