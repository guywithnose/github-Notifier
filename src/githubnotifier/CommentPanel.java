/*
 * File: AuthenticationPanel.java Author: Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONObject;

import data.JavaCurl;

/**
 * The Class RepoButton.
 * 
 * @author Dude
 */
public class CommentPanel extends JPanel {

  private String repoName;
  
  private String mostRecentComment = null;
  
  /**
   * Instantiates a new url button.
   * 
   * @param RepoName
   *          the repo name
   */
  public CommentPanel(String RepoName) {
    super();
    repoName = RepoName;
    setLayout(new GridLayout(9, 2));
    getComments();
    Thread updater = new Thread(new Updater());
    updater.start();
  }

  /**
   * Get Repos.
   */
  void getComments() {
    try {
      String newMostRecentComment = null;
      JSONArray eventData = new JSONArray(
          JavaCurl.getUrl("https://api.github.com/repos/"
              + Config.get("userName") + "/" + repoName
              + "/events?access_token=" + Config.get("accessToken")));
      int numLabels = 0;
      for (int i = 0; i < eventData.length() && numLabels < 9; i++) {
        JSONObject event = eventData.getJSONObject(i);
        if (event.has("payload")
            && event.getJSONObject("payload").has("comment")) {
          String body = event.getJSONObject("payload").getJSONObject("comment")
              .getString("body");
          String date = event.getJSONObject("payload")
              .getJSONObject("comment").getString("updated_at");
          if(newMostRecentComment == null)
            newMostRecentComment = date;
          add(new JLabel(body));
          add(new JLabel(date));
          numLabels++;
        }
      }
      if(mostRecentComment == null)
        mostRecentComment = newMostRecentComment;
      if(newMostRecentComment != null && !newMostRecentComment.equals(mostRecentComment))
      {
        mostRecentComment = newMostRecentComment;
        Runtime.getRuntime().exec(new String[]{"bash","-c","zenity --info --text 'New github comment'"});
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private class Updater implements Runnable
  {
  
    public Updater()
    {
      // I'm thinking we should test this
    }

    @Override
    public void run()
    {
      synchronized (this)
      {
        while(true)
        {
          try
          {
            wait(5000);
          } catch (InterruptedException e)
          {
            e.printStackTrace();
          }
          getComments();
        }
      }
    }
    
  }

}
