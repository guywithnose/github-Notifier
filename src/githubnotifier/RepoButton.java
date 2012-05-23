/*
 * File:         RepoButton.java
 * Author:       Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * The Class RepoButton.
 *
 * @author Dude
 */
public class RepoButton extends JButton
{

  /**
   * The Class LoadRepoEventsAction.
   */
  class LoadRepoEventsAction implements ActionListener
  {
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
      Main.window.showRepoEvents(e.getActionCommand());
    }
  }
  
  /**
   * Instantiates a new repo button.
   *
   * @param name the name
   */
  public RepoButton(String name)
  {
    super(name);
    addActionListener(new LoadRepoEventsAction());
  }
  
}
