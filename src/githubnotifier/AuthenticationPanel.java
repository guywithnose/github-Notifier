/*
 * File: AuthenticationPanel.java Author: Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.json.JSONObject;

import data.JavaCurl;

/**
 * The Class RepoButton.
 * 
 * @author Dude
 */
public class AuthenticationPanel extends JPanel
{

  /** The link. */
  private JButton link;

  /** The code text. */
  JTextField codeText;

  /**
   * The Class updateCode.
   */
  class updateCode implements ActionListener
  {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if ("OK".equals(e.getActionCommand()))
      {
        if (loadAccessToken(codeText.getText()))
        {
          Main.window.loadRepos();
        }
      }
    }
  }

  /**
   * Instantiates a new url button.
   * 
   */
  public AuthenticationPanel()
  {
    super();
    link = new UrlButton("Click here to Authorize.",
        "https://github.com/login/oauth/authorize?scope=user,repo&client_id="
            + Config.get("client_id"));
    add(link);
    codeText = new JTextField("Enter code");
    JButton okButton = new JButton("OK");
    okButton.addActionListener(new updateCode());
    add(codeText);
    add(okButton);
  }

  /**
   * Load access token.
   * 
   * @param code
   *          the code
   * @return true, if successful
   */
  static boolean loadAccessToken(String code)
  {
    try
    {
      HashMap<String, String> params = new HashMap<String, String>();
      params.put("client_id", Config.get("client_id"));
      params.put("client_secret", Config.get("client_secret"));
      params.put("code", code);
      String eventData = JavaCurl.getUrl(
          "https://github.com/login/oauth/access_token", "POST", params);
      String[] tokenData = eventData.split("[=&]");
      if (tokenData.length > 1)
      {
        Config.put("accessToken", tokenData[1]);
        String userData = JavaCurl
            .getUrl("https://api.github.com/user?access_token="
                + Config.get("accessToken"));
        JSONObject user = new JSONObject(userData);
        Config.put("userName", user.getString("login"));
        return true;
      }
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }

}
