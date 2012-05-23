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

  /**
   * Instantiates a new url button.
   * 
   */
  public CommentPanel(String repoName) {
    super();
    setLayout(new GridLayout(9, 2));
    getComments(repoName);
  }

  /**
   * Get Repos.
   * 
   * @return the repos
   */
  private void getComments(String repoName) {
    try {
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
          add(new JLabel(body));
          add(new JLabel(event.getJSONObject("payload")
              .getJSONObject("comment").getString("updated_at")));
          numLabels++;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
