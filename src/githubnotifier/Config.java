package githubnotifier;

import org.json.JSONException;
import org.json.JSONObject;

import data.FileReader;
import data.FileWriter;

/**
 * The Class Config.
 */
public class Config extends JSONObject
{
  
  /** The file name. */
  private String fileName;
  
  private boolean initialized = false;

  /**
   * Instantiates a new config.
   * 
   * @throws JSONException
   *           the jSON exception
   */
  public Config() throws JSONException
  {
    this("config");
  }
  
  /**
   * Instantiates a new config.
   * 
   * @param fileNameOverride
   *          the file name override
   * @throws JSONException
   *           the jSON exception
   */
  public Config(String fileNameOverride) throws JSONException
  {
    super(FileReader.getFileContents(fileNameOverride));
    fileName = fileNameOverride;
    initialized = true;
  }
  
  /**
   * Put.
   * 
   * @param name
   *          the name
   * @param value
   *          the value
   * @return the jSON object
   */
  @Override
  public JSONObject put(String name, Object value)
  {
    try
    {
      super.put(name, value);
      if(initialized)
        FileWriter.putFileContents(fileName, toString(2));
      return this;
    } catch (JSONException e)
    {
      e.printStackTrace();
      return null;
    }
  }

}
