package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class EditMenu extends JMenu {
	  
	  
	  // Constructor
	  
	  /**
	   * Creates an edit menu, with undo and redo options.
	   * @param the_canvas A canvas to interact with.
	   */
	  public EditMenu()
	  {
	    super("Show");
	    start();
	  }
	  
	  /**
	   * Actually builds the menu.
	   */
	  private void start()
	  {
	    setMnemonic('s');
	    
	    // Undo Button
	    JMenuItem i = new JMenuItem("Vertices");
	    i.setMnemonic('v');
	    i.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
	                                            InputEvent.CTRL_MASK, true));
	    i.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(final ActionEvent the_event)
	      {
	    	  
	      }
	    });
	    add(i);
	    
	    // Edge Button
	    i = new JMenuItem("Edges");
	    i.setMnemonic('e');
	    i.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
	                                            InputEvent.CTRL_MASK, true));
	    i.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(final ActionEvent the_event)
	      {
	    	  
	      }
	    });
	    add(i);
	  }
	  
}
