package run;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;

/**
 * 
 * @author Steven Cozart
 * @author CSS Mentors (this was created by draging and droping the mentor version of UWT tcss 305 power paint)
 */
public class DikstraGui extends JFrame {
	  /**
	   * The initial width.
	   */
	  public static final int INITIAL_WIDTH = 400;
	  
	  /**
	   * The initial height.
	   */
	  public static final int INITIAL_HEIGHT = 300;
	  
	  
	  /**
	   * The menu containing tools.
	   */
	  private final JMenu my_tool_menu = new JMenu("Tools");
	  
	  /**
	   * The button group for the tool bar buttons.
	   */
	  private final ButtonGroup my_tool_bar_group = new ButtonGroup();
	  
	  
	  
	  // Constructor
	  
	  /**
	   * Creates a PowerPaint instance.  To create and display actual GUI, start()
	   * must be called.
	   */
	  public DikstraGui()
	  {
	    super("AAA killer");
	  }
	  
	  /**
	   * Creates and displays the GUI.
	   */
	  public void start()
	  {
	    
	    // add menu bar
	    final JMenuBar menu_bar = new JMenuBar();
	    menu_bar.add(createFileMenu());
	    menu_bar.add(new EditMenu());
	    menu_bar.add(new OptionsMenu());
	    my_tool_menu.setMnemonic('t');
	    menu_bar.add(my_tool_menu);
	    menu_bar.add(createHelpMenu());
	    setJMenuBar(menu_bar);
	    
	    // add canvas
	    add(new JPanel(), BorderLayout.CENTER);
	    
	    // make tools
	    addTools();
	    
	    // show
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack();
	    setVisible(true);
	  }
	  
	  
	  /**
	   * Creates the file menu, containing clear and quit.
	   * 
	   * @return a file menu.
	   */
	  private JMenu createFileMenu()
	  {
	    final JMenu r = new JMenu("File");
	    r.setMnemonic('f');
	    JMenuItem i = new JMenuItem("Clear");
	    i.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(final ActionEvent the_event)
	      {

	      }
	    });
	    i.setMnemonic(KeyEvent.VK_C);
	    r.add(i);
	    r.addSeparator();
	    i = new JMenuItem("Quit");
	    i.setMnemonic('q');
	    i.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(final ActionEvent the_event)
	      {
	        DikstraGui.this.dispose();
	      }
	    });
	    r.add(i);
	    return r;
	  }
	    
	  /**
	   * Makes a help menu, with information about PowerPaint.
	   * @return a help menu.
	   */
	  private JMenu createHelpMenu()
	  {
	    final JMenu r = new JMenu("Help");
	    r.setMnemonic('h');
	    final JMenuItem item = new JMenuItem("About");
	    item.setMnemonic('a');
	    item.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(final ActionEvent the_event)
	      {
	        JOptionPane.showMessageDialog(DikstraGui.this,
	                                      "TCSS 343 Dikstra AAA \n" +
	                                      "Programmer: Steven Cozart \n" +
	                                      "Instructor: Donald Chinn \n" +
	                                      "UW Tacoma, Autum Quarter 2011");
	      }
	    });
	    r.add(item);
	    return r;
	  }
	  
	  /**
	   * Adds the color option and all tools to both my_tool_menu and 
	   * my_tool_bar.
	   */
	  private void addTools()
	  {
	    
	    // Make color button
	    final JButton color_button = new JButton();
	    final Action a = new AbstractAction("Color")
	    {
	      public void actionPerformed(final ActionEvent the_event)
	      {

	      }
	    };
	    a.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
	    a.putValue(Action.SHORT_DESCRIPTION, "Choose a stroke color.");
	    color_button.setAction(a);
	    my_tool_menu.add(new JMenuItem(a));
	    
	    
	    // Make rest of tools.
	  }
	  
	  /**
	   * Creates an action for a Tool.
	   * 
	   * @param the_tool An instance of Tool.
	   * @param the_name The name of the tool.
	   * @param the_key The mnemonic key.
	   * @param the_description A short description of the tool.
	   * @return the action.
	   */
	  private Action createTool( final String the_name,
	                         final int the_key, 
	                         final String the_description)
	  {
	    final Action a = new AbstractAction(the_name)
	    {
	      public void actionPerformed(final ActionEvent the_event)
	      {

	      }
	    };
	    a.putValue(Action.MNEMONIC_KEY, the_key);
	    a.putValue(Action.SHORT_DESCRIPTION, the_description);
	    a.putValue(Action.SELECTED_KEY, true);
	    final JToggleButton toggle = new JToggleButton(a);
	    final JRadioButtonMenuItem radio = new JRadioButtonMenuItem(a);
	    my_tool_menu.add(radio);
	    my_tool_bar_group.add(toggle);
	    return a;
	  }
}
