package pruebas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PruebaLista extends JFrame{
//DefaultListCellRenderer
		
	
		public PruebaLista() {
			setSize(200,500);
			DefaultListModel<String> modeloLista = new DefaultListModel<String>();
			JList<String> lista = new JList<String>(modeloLista);
			
			for (int i = 0; i < 20; i++) {
				modeloLista.add(i, "Opcion " + i);
			}
			
			getContentPane().add(new JScrollPane(lista));
			
			
			lista.setCellRenderer(new DefaultListCellRenderer() {
				private static final long serialVersionUID = 1L;
				
				JPanel pnl;
				JLabel lbl;
				
				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {
					// TODO Auto-generated method stub
					
					pnl = new JPanel();
					pnl.setSize(200,50);
					if (isSelected) {
	                    pnl.setBackground(new Color(122, 199, 218));
	                } else {
	                    pnl.setBackground(new Color(202, 232, 232));
	                    pnl.setForeground(list.getForeground());
	                }
					lbl = new JLabel(value.toString());
					pnl.add(lbl);
					return pnl;
				}
				
				
			});
			
			lista.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseExited(MouseEvent e) {
	                lista.clearSelection();
	            }
	        });
			
			
			lista.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                int index = lista.locationToIndex(e.getPoint());
	                if (index != -1) {
	                    lista.setSelectedIndex(index);
	                }
	            }
	        });
			
			setVisible(true);
		}
		
		public static void main(String[] args) {
			new PruebaLista();
		}
		
		
}
