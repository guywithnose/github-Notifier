/*
 * File:         UrlButton.java
 * Author:       Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JButton;

/**
 * The Class RepoButton.
 *
 * @author Dude
 */
public class UrlButton extends JButton
{
  
  class OpenUrlAction implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      open(e.getActionCommand());
    }

    private void open(String uri)
    {
      if (Desktop.isDesktopSupported())
      {
        try
        {
          Desktop.getDesktop().browse(new URI(uri));
        } catch (Exception e)
        {
          e.printStackTrace();
        }
      } else
      {
        System.out.println("Cannot open browser...");
        System.out.println("Please go to " + uri);
      }
    }
  }
  
  /**
   * Instantiates a new url button.
   *
   * @param text the text
   * @param link the link
   */
  public UrlButton(String text, String link)
  {
    super(text);
    setActionCommand(link);
    addActionListener(new OpenUrlAction());
  }
  
}
