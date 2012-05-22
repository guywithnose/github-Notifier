package githubnotifier;

import javax.swing.SwingUtilities;

/**
 * The Class Main.
 */
public class Main implements Runnable
{

  /** The window. */
  public static MainWindow window = new MainWindow("github Notifier");

  /* (non-Javadoc)
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run()
  {
    window.pack();
    window.setSize(200,100);
    window.setVisible(true);
  }

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args)
  {
    try{
      SwingUtilities.invokeAndWait(new Main());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

}
