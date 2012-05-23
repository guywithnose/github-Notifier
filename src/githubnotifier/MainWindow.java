/*
 * File: MainWindow.java Author: Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.json.JSONArray;

import data.JavaCurl;

/**
 * The Class MainWindow.
 */
public class MainWindow extends JFrame
{

  /** The authentication panel. */
  public JPanel authenticationPanel;

  /** The repo panel. */
  public JPanel repoPanel;

  /**
   * The Class closeListener.
   * 
   * @see closeEvent
   */
  private class closeListener extends WindowAdapter
  {

    /**
     * Instantiates a new close listener.
     */
    public closeListener()
    {
      // Do Nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
     */
    @SuppressWarnings("unused")
    @Override
    public void windowClosing(WindowEvent we)
    {
      System.exit(0);
    }

  }

  /**
   * Instantiates a new main window.
   * 
   * @param title
   *          the title
   */
  public MainWindow(String title)
  {
    super(title);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    addWindowListener(new closeListener());
    setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    add(authenticationPanel = new AuthenticationPanel());
    if (Config.has("accessToken"))
    {
      loadRepos();
    }
  }

  /**
   * Load repos.
   */
  void loadRepos()
  {
    remove(authenticationPanel);
    add(repoPanel = new RepoPanel());
    repoPanel.revalidate();
  }

  /**
   * Show repo events.
   * 
   * @param repoName
   *          the repo name
   */
  public void showRepoEvents(String repoName)
  {
    remove(repoPanel);
    try
    {
      JSONArray eventData = new JSONArray(
          JavaCurl.getUrl("https://api.github.com/repos/"
              + Config.get("userName") + "/" + repoName
              + "/events?access_token=" + Config.get("accessToken")));
      System.out.println(eventData.toString(4));
      repaint();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

}
