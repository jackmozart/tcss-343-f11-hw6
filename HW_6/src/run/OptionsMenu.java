

package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * An options menu.
 * 
 * @author Steven Cozart
 * @version 5/2/10
 */
@SuppressWarnings("serial")
public class OptionsMenu extends JMenu
{
  
  // Class Fields
  
  
  // Instance fields
  
  /**
   * The check box for snapping to points.
   */
  private JCheckBoxMenuItem my_point_box;
  
  /**
   * The check box for snapping to edges.
   */
  private JCheckBoxMenuItem my_edge_box;
  
  
  // Constructor
  
  /**
   * Creates an options menu, with a grid option and thickness sub menu.
   * @param the_canvas A canvas to interact with.
   */
  public OptionsMenu()
  {
    super("Options");
    start();
  }
  
  /**
   * Actually start building the menu.
   */
  private void start()
  {
    setMnemonic('o');
    
    // Make Grid check box.
    final JMenuItem i = new JCheckBoxMenuItem("Grid");
    i.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event)
      {
    	  
      }
    });
    i.setMnemonic('g');
    i.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                                           InputEvent.CTRL_MASK, false));
    add(i);
    
    // Add thickness sub menu.
    add(createThicknessMenu());
    
    addSeparator();
    
  }
  
  
  
  
  /**
   * Creates the thickness menu for selecting different
   * brush widths.
   * 
   * @return A thickness menu.
   */
  private JMenu createThicknessMenu()
  {
    final ButtonGroup b = new ButtonGroup();
    final JMenu t = new JMenu("Thickness");
    t.setMnemonic('t');
    final JMenuItem[] item = new JMenuItem[2];
    
    for (int i = 0; i < 2; i++)
    {
      item[i] = createThickness(2);
      b.add(item[i]);
      t.add(item[i]);
    }
    item[1].setSelected(true);
    return t;
  }
  
  /**
   * Creates a thickness option, which changes the thickness of 
   * the currently selected tool.
   * 
   * @param the_thickness The width draw tools will use, and also the name of
   * the button.
   * @return a JRadioButton
   */
  private JRadioButtonMenuItem createThickness(final int the_thickness)
  {
    final String name = String.valueOf(the_thickness);
    final JRadioButtonMenuItem r = new JRadioButtonMenuItem(name);
    r.setMnemonic(name.toCharArray()[0]);
    r.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {

      }
    });
    return r;
  }

  

}
