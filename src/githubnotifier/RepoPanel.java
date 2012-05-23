/*
 * File: AuthenticationPanel.java Author: Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import java.util.ArrayList;
import javax.swing.JPanel;

import org.json.JSONArray;

import data.JavaCurl;

/**
 * The Class RepoButton.
 * 
 * @author Dude
 */
public class RepoPanel extends JPanel
{

  /**
   * Instantiates a new url button.
   * 
   */
  public RepoPanel()
  {
    super();
    for (String repoName : getRepos())
    {
      add(new RepoButton(repoName));
    }
  }

  /**
   * Get Repos.
   * 
   * @return the repos
   */
  private static ArrayList<String> getRepos()
  {
    ArrayList<String> repos = new ArrayList<String>();
    try
    {
      String eventData = JavaCurl
          .getUrl("https://api.github.com/user/repos?access_token="
              + Config.get("accessToken"));
      JSONArray reposData = new JSONArray(eventData);
      for (int i = 0; i < reposData.length(); i++)
      {
        repos.add(reposData.getJSONObject(i).getString("name"));
      }
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return repos;
  }

}
