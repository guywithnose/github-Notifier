/*
 * File:         NotificationWindow.java
 * Author:       Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The Class MainWindow.
 */
public class NotificationWindow extends JFrame
{

  /**
   * Instantiates a new main window.
   *
   * @param comment the comment
   */
  public NotificationWindow(String comment)
  {
    super("New Comment");
    add(new JLabel(comment));
    pack();
    setSize(400,90);
    setVisible(true);
  }

}
