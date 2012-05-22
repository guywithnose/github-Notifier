/*
 * File:         FileWriter.java
 * Author:       Robert Bittle <guywithnose@gmail.com>
 */
package data;

import java.io.FileOutputStream;
import java.io.File;

/**
 * The Class FileWriter.
 */
public class FileWriter
{


    /**
     * Put file contents.
     * 
     * @param fileName
     *          the file name
     * @param data
     *          the data
     * @return true, if successful
     */
    public static boolean putFileContents(String fileName, String data)
    {
      return putFileContents(new File(fileName), data);
    }
  
    /**
     * Put file contents.
     *
     * @param file the file
     * @param data the data
     * @return the string
     */
    public static boolean putFileContents(File file, String data)
    {
        try
        {
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

}
