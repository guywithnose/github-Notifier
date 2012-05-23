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

/**
 * The Class MainWindow.
 */
public class MainWindow extends JFrame
{

  /** The authentication panel. */
  private JPanel authenticationPanel;

  /** The repo panel. */
  private JPanel repoPanel;

  /** The comment panel. */
  private JPanel commentPanel;

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
    repaint();
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
    add(commentPanel = new CommentPanel(repoName));
    commentPanel.revalidate();
    repaint();
  }

}
