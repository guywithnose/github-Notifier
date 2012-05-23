/*
 * File:         Config.java
 * Author:       Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import org.json.JSONException;
import org.json.JSONObject;

import data.FileReader;
import data.FileWriter;

/**
 * The Class Config.
 */
public class Config
{
  
  private static JSONObject config;
  
  /**
   * Instantiates a new config.
   *
   * @throws JSONException the jSON exception
   */
  private static void initialize() throws JSONException
  {
    if(config == null)
      config = new JSONObject(FileReader.getFileContents("config"));
  }
  
  /**
   * Put.
   * 
   * @param key
   *          the name
   * @param value
   *          the value
   */
  public static void put(String key, Object value)
  {
    try
    {
      initialize();
      config.put(key, value);
      FileWriter.putFileContents("config", config.toString(2));
    } catch (JSONException e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * Get String.
   *
   * @param key the name
   * @return the string
   */
  public static String get(String key)
  {
    try
    {
      initialize();
      return config.getString(key);
    } catch (JSONException e)
    {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * Checks for.
   *
   * @param key the key
   * @return true, if successful
   */
  public static boolean has(String key)
  {
    try
    {
      initialize();
    } catch (JSONException e)
    {
      e.printStackTrace();
    }
    return config.has(key);
  }
  
}
