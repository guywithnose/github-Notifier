/*
 * File: MainWindow.java Author: Robert Bittle <guywithnose@gmail.com>
 */
package githubnotifier;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.json.JSONException;

/**
 * The Class MainWindow.
 */
public class MainWindow extends JFrame
{

  private JLabel loading;

  private JButton link;

  Config config;

  JTextField codeText;

  /**
   * The Class closeListener.
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

  class updateCode implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      if("OK".equals(e.getActionCommand()))
        config.put("code", codeText.getText());
      try
      {
        loadEvents();
      } catch (JSONException e1)
      {
        e1.printStackTrace();
      }
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
    loading = new JLabel("Loading...");
    add(loading);
    try
    {
      config = new Config();
      if (config.has("code"))
      {
        loadEvents();
      } else
      {
        loading.setVisible(false);
        link = new JButton("Click here to Authorize.");
        link.setActionCommand("https://github.com/login/oauth/authorize?scope=user&client_id="
            + config.getString("client_id"));
        link.addActionListener(new OpenUrlAction());
        add(link);
        codeText = new JTextField("Enter code");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new updateCode());
        add(codeText);
        add(okButton);
      }
    } catch (JSONException e)
    {
      e.printStackTrace();
    }
  }

  void loadEvents() throws JSONException
  {
    String code = config.getString("code");
    ArrayList<String> event = getEvents();
    for(Component comp : getComponents())
    {
      remove(comp);
    }
  }

  private ArrayList<String> getEvents()
  {
    return null;
  }

}
