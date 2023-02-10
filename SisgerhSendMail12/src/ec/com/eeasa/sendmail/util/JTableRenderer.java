package ec.com.eeasa.sendmail.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JTableRenderer extends JLabel implements TableCellRenderer{

	private static final long serialVersionUID = 1L;
	Font f = new Font( "Helvetica",Font.PLAIN,10 );
	Color colGeneralSel = new Color(117, 204, 169);
	Color colGeneral = new Color(225, 244, 238);
	
	public JTableRenderer() {
	  setOpaque(true);
	 }
	
	public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean isSelected, boolean hasFocus,
	      int row, int column) {
		  setHorizontalAlignment(CENTER);
		  if (isSelected) {
		   setBackground(colGeneralSel); 
		  } else {
		   setBackground(colGeneral);
		  }
		  try {
		   setFont(f);
		   setText(valor.toString());
		  } catch (NullPointerException npe) {
		   System.out.println(valor.toString());
		   setText("0"); 
		  }
		  return this;
	 }
}
