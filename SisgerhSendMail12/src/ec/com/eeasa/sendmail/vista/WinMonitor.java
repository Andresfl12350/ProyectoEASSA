package ec.com.eeasa.sendmail.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import ec.com.eeasa.sendmail.dao.ConsultaDao;
import ec.com.eeasa.sendmail.dao.EnvioCorreo;
import ec.com.eeasa.sendmail.util.ResultSetTableModel;
import ec.com.eeasa.sendmail.util.RowNumberTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WinMonitor extends JFrame {

	private static final long serialVersionUID = 1L;	
	private JPanel jtpEnvioMail = null;
	
	private JPanel pnlPersonal = null;	
	private JLabel lblMesAnio;
	private JTextField txtMesAnio = null;
	private JButton btnGenerar = null;
	private JPanel pnlEnvioMail = null;
	private JTabbedPane jtpPersonal = null;
	
	private JLabel lblRegimenLaboral;
	private JComboBox<String> cbxRegimenLab;
	private ArrayList<HashMap<String, String>> listRegmimeLab = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> listTipoNomina = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> listZona = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> listNomina = new ArrayList<HashMap<String, String>>();
	private ResultSetTableModel personalModel = new ResultSetTableModel();
		
	private JLabel lblTipoNom;
	private JLabel lblZona;
	private JComboBox<String> cbxTipoNomina;
	private JComboBox<String> cbxZona;
	private JButton btnEnviarEmail;
	
	private ConsultaDao objConsulta=null;
	private EnvioCorreo objEnvioCorreo=null;
	private JLabel lblNomina;
	private JComboBox<String> cbxNomina;
	private JButton btnBuscar;
	private JTable tbPersonal;
	private JScrollPane scrollPane;
	
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JPanel getjtpEnvioMail() {
		if (jtpEnvioMail == null) {
			jtpEnvioMail = new JPanel();
			jtpEnvioMail.setLayout(new BorderLayout(0, 0));
			jtpEnvioMail.add(getPnlEnvioMail());
		}
		return jtpEnvioMail;
	}

	/**
	 * This method initializes pnlTables	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlPersonal() {
		if (pnlPersonal == null) {
			pnlPersonal = new JPanel();
			GridBagLayout gbl_pnlPersonal = new GridBagLayout();
			gbl_pnlPersonal.rowWeights = new double[]{1.0};
			gbl_pnlPersonal.columnWeights = new double[]{1.0};
			pnlPersonal.setLayout(gbl_pnlPersonal);
			pnlPersonal.setEnabled(true);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			pnlPersonal.add(getScrollPane(), gbc_scrollPane);
		}
		return pnlPersonal;
	}

	/**
	 * This method initializes txtComprobanteGen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtMesAnio() {
		if (txtMesAnio == null) {
			txtMesAnio = new JTextField();
		}
		return txtMesAnio;
	}

	/**
	 * This method initializes btnGenerar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGenerar() {
		if (btnGenerar == null) {
			btnGenerar = new JButton();
			btnGenerar.setText("Buscar");
			btnGenerar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if(txtMesAnio.getText().length()>0){
							loadPersonal(Integer.parseInt(getCampoTipoNomina("DNMTR_ESTADO_PERSONA")),getCodZona(),getCodNomina());						
						}											
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnGenerar;
	}
	
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setText("Buscar");
			btnBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if(txtMesAnio.getText().length()>0){
							String[] parts = txtMesAnio.getText().split("/");
							Integer mes = Integer.parseInt(parts[0]);
							Integer anio = Integer.parseInt(parts[1]);
							
							loadNomina(getCodTipoNomina(), anio.toString(),mes.toString());
						}											
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnBuscar;
	}
	
	/**
	 * This method initializes pnlGenerar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlEnvioMail() {
		if (pnlEnvioMail == null) {
			GridBagConstraints gbc_jtpPersonal = new GridBagConstraints();
			gbc_jtpPersonal.fill = GridBagConstraints.BOTH;
			gbc_jtpPersonal.gridy = 5;
			gbc_jtpPersonal.weightx = 1.0;
			gbc_jtpPersonal.weighty = 1.0;
			gbc_jtpPersonal.gridwidth = 3;
			gbc_jtpPersonal.insets = new Insets(5, 5, 5, 0);
			gbc_jtpPersonal.gridx = 0;
			pnlEnvioMail = new JPanel();
			GridBagLayout gbl_pnlEnvioMail = new GridBagLayout();
			gbl_pnlEnvioMail.rowHeights = new int[] {33, 0, 0, 0, 0, 0, 32};
			gbl_pnlEnvioMail.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_pnlEnvioMail.columnWeights = new double[]{0.0, 0.0, 0.0};
			pnlEnvioMail.setLayout(gbl_pnlEnvioMail);
			GridBagConstraints gbc_lblRegimenLaboral = new GridBagConstraints();
			gbc_lblRegimenLaboral.anchor = GridBagConstraints.WEST;
			gbc_lblRegimenLaboral.insets = new Insets(0, 0, 5, 5);
			gbc_lblRegimenLaboral.gridx = 0;
			gbc_lblRegimenLaboral.gridy = 0;
			pnlEnvioMail.add(getLblRegimenLaboral(), gbc_lblRegimenLaboral);
			GridBagConstraints gbc_cbxRegimenLab = new GridBagConstraints();
			gbc_cbxRegimenLab.insets = new Insets(0, 0, 5, 5);
			gbc_cbxRegimenLab.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxRegimenLab.gridx = 1;
			gbc_cbxRegimenLab.gridy = 0;
			pnlEnvioMail.add(getCbxRegimenLab(), gbc_cbxRegimenLab);
			GridBagConstraints gbc_lblTipoNom = new GridBagConstraints();
			gbc_lblTipoNom.anchor = GridBagConstraints.WEST;
			gbc_lblTipoNom.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoNom.gridx = 0;
			gbc_lblTipoNom.gridy = 1;
			pnlEnvioMail.add(getLblTipoNom(), gbc_lblTipoNom);
			GridBagConstraints gbc_cbxTipoNomina = new GridBagConstraints();
			gbc_cbxTipoNomina.insets = new Insets(0, 0, 5, 5);
			gbc_cbxTipoNomina.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxTipoNomina.gridx = 1;
			gbc_cbxTipoNomina.gridy = 1;
			pnlEnvioMail.add(getCbxTipoNomina(), gbc_cbxTipoNomina);
			lblMesAnio = new JLabel();
			lblMesAnio.setText(" Mes/A\u00F1o");
			GridBagConstraints gbc_lblMesAnio = new GridBagConstraints();
			gbc_lblMesAnio.anchor = GridBagConstraints.WEST;
			gbc_lblMesAnio.gridx = 0;
			gbc_lblMesAnio.gridy = 2;
			gbc_lblMesAnio.insets = new Insets(5, 5, 5, 5);
			pnlEnvioMail.add(lblMesAnio, gbc_lblMesAnio);
			GridBagConstraints gbc_txtMesAnio = new GridBagConstraints();
			gbc_txtMesAnio.fill = GridBagConstraints.BOTH;
			gbc_txtMesAnio.gridwidth = 1;
			gbc_txtMesAnio.gridx = 1;
			gbc_txtMesAnio.gridy = 2;
			gbc_txtMesAnio.weightx = 1.0;
			gbc_txtMesAnio.insets = new Insets(5, 5, 5, 5);
			pnlEnvioMail.add(getTxtMesAnio(), gbc_txtMesAnio);
			GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
			gbc_btnBuscar.anchor = GridBagConstraints.WEST;
			gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
			gbc_btnBuscar.gridx = 2;
			gbc_btnBuscar.gridy = 2;
			pnlEnvioMail.add(getBtnBuscar(), gbc_btnBuscar);
			GridBagConstraints gbc_lblNomina = new GridBagConstraints();
			gbc_lblNomina.anchor = GridBagConstraints.WEST;
			gbc_lblNomina.insets = new Insets(0, 0, 5, 5);
			gbc_lblNomina.gridx = 0;
			gbc_lblNomina.gridy = 3;
			pnlEnvioMail.add(getLblNomina(), gbc_lblNomina);
			GridBagConstraints gbc_cbxNomina = new GridBagConstraints();
			gbc_cbxNomina.insets = new Insets(0, 0, 5, 5);
			gbc_cbxNomina.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxNomina.gridx = 1;
			gbc_cbxNomina.gridy = 3;
			pnlEnvioMail.add(getCbxNomina(), gbc_cbxNomina);
			GridBagConstraints gbc_lblZona = new GridBagConstraints();
			gbc_lblZona.anchor = GridBagConstraints.WEST;
			gbc_lblZona.insets = new Insets(0, 0, 5, 5);
			gbc_lblZona.gridx = 0;
			gbc_lblZona.gridy = 4;
			pnlEnvioMail.add(getLblZona(), gbc_lblZona);
			GridBagConstraints gbc_cbxZona = new GridBagConstraints();
			gbc_cbxZona.insets = new Insets(0, 0, 5, 5);
			gbc_cbxZona.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxZona.gridx = 1;
			gbc_cbxZona.gridy = 4;
			pnlEnvioMail.add(getCbxZona(), gbc_cbxZona);
			GridBagConstraints gbc_btnGenerar = new GridBagConstraints();
			gbc_btnGenerar.insets = new Insets(0, 0, 5, 0);
			gbc_btnGenerar.gridx = 2;
			gbc_btnGenerar.gridy = 4;
			pnlEnvioMail.add(getBtnGenerar(), gbc_btnGenerar);
			pnlEnvioMail.add(getJtpPersonal(), gbc_jtpPersonal);
			GridBagConstraints gbc_btnEnviarEmail = new GridBagConstraints();
			gbc_btnEnviarEmail.anchor = GridBagConstraints.NORTH;
			gbc_btnEnviarEmail.insets = new Insets(0, 0, 0, 5);
			gbc_btnEnviarEmail.gridx = 1;
			gbc_btnEnviarEmail.gridy = 6;
			pnlEnvioMail.add(getBtnEnviarEmail(), gbc_btnEnviarEmail);
		}
		return pnlEnvioMail;
	}

	/**
	 * This method initializes jtpComprobantesGenerar	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJtpPersonal() {
		if (jtpPersonal == null) {
			jtpPersonal = new JTabbedPane();
			jtpPersonal.setBorder(null);
			jtpPersonal.addTab("Personal", null, getPnlPersonal(), null);
		}
		return jtpPersonal;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new WinMonitor().setVisible(true);
			}
		});		
	}
	
	/**
	 * @param owner
	 */
	public WinMonitor() {
		super();
		initialize();
		objConsulta = new ConsultaDao();
		loadRegimenLaboral();
		loadZona();		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(548, 462);
		this.setContentPane(getjtpEnvioMail());
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Envio e-mail a personal");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		txtMesAnio.setText(String.valueOf(month)+"/"+String.valueOf(year));
	}
	
	private JLabel getLblRegimenLaboral() {
		if (lblRegimenLaboral == null) {
			lblRegimenLaboral = new JLabel("   Regimen Laboral");
		}
		return lblRegimenLaboral;
	}
	private JComboBox<String> getCbxRegimenLab() {
		if (cbxRegimenLab == null) {
			cbxRegimenLab = new JComboBox<String>();
			cbxRegimenLab.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						loadTipoNomina(getCodRegimen());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return cbxRegimenLab;
	}
		
	private JLabel getLblTipoNom() {
		if (lblTipoNom == null) {
			lblTipoNom = new JLabel("   Tipo Nomina");
		}
		return lblTipoNom;
	}
	private JLabel getLblZona() {
		if (lblZona == null) {
			lblZona = new JLabel("   Zona");
		}
		return lblZona;
	}
	private JComboBox<String> getCbxTipoNomina() {
		if (cbxTipoNomina == null) {
			cbxTipoNomina = new JComboBox<String>();
		}
		return cbxTipoNomina;
	}
	private JComboBox<String> getCbxZona() {
		if (cbxZona == null) {
			cbxZona = new JComboBox<String>();			
		}
		return cbxZona;
	}
	private JButton getBtnEnviarEmail() {
		if (btnEnviarEmail == null) {
			btnEnviarEmail = new JButton();
			btnEnviarEmail.setText("Enviar");
			btnEnviarEmail.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
										
					try {
						String respuesta="";
						objEnvioCorreo = new EnvioCorreo(getCampoNomina("DNMPN_NOMBRE_NOMINA"), "Rol_"+txtMesAnio.getText().replace("/", "_")+".pdf", getCodNomina());
						
						String outRolIni = null;
						String outCorreoPersonal = null;

				        int[] selectedRow = tbPersonal.getSelectedRows();

				        for (int i = 0; i < selectedRow.length; i++) {
				        	outRolIni = (String) tbPersonal.getValueAt(selectedRow[i], 1);
				        	outCorreoPersonal = (String) tbPersonal.getValueAt(selectedRow[i], 4);
				        	respuesta = objEnvioCorreo.enviarRol(outRolIni, outRolIni, outCorreoPersonal);
				        					        					        		
			        		tbPersonal.getModel().setValueAt(respuesta, selectedRow[i], 5);
							tbPersonal.repaint();				        	
				        }				        
																	
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnEnviarEmail;
	}
	private JLabel getLblNomina() {
		if (lblNomina == null) {
			lblNomina = new JLabel("   Nomina");
		}
		return lblNomina;
	}
	private JComboBox<String> getCbxNomina() {
		if (cbxNomina == null) {
			cbxNomina = new JComboBox<String>();
		}
		return cbxNomina;
	}
	
	private void loadRegimenLaboral(){		 
		try {
			listRegmimeLab = objConsulta.selectAllRegimenLab();
			for(int i=0; i<listRegmimeLab.size();i++){
				cbxRegimenLab.addItem(listRegmimeLab.get(i).get("DRLAB_DESCRIPCION"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCodRegimen(){
		String cod_regimen= "";		
		int pos = cbxRegimenLab.getSelectedIndex();		
		cod_regimen = listRegmimeLab.get(pos).get("DRLAB_CODIGO");		
		return cod_regimen.trim();
	}
	
	private void loadTipoNomina(String inCodigoRegimen){		 
		try {
			listTipoNomina.clear();
			cbxTipoNomina.removeAllItems();
			listTipoNomina = objConsulta.selectAllTipoNomina(inCodigoRegimen);
			for(int i=0; i<listTipoNomina.size();i++){
				cbxTipoNomina.addItem(listTipoNomina.get(i).get("DNMTR_NOMBRE"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCodTipoNomina(){
		String cod_tipoNomina= "";		
		int pos = cbxTipoNomina.getSelectedIndex();		
		cod_tipoNomina = listTipoNomina.get(pos).get("DNMTR_CODIGO");		
		return cod_tipoNomina.trim();
	}
	
	public String getCampoTipoNomina(String inCampo){
		String campotipoNomina= "";		
		int pos = cbxTipoNomina.getSelectedIndex();		
		campotipoNomina = listTipoNomina.get(pos).get(inCampo);		
		return campotipoNomina.trim();
	}
	
	private void loadZona(){		 
		try {
			listZona = objConsulta.selectAllZona();
			for(int i=0; i<listZona.size();i++){
				cbxZona.addItem(listZona.get(i).get("DMZON_NOMBRE"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCodZona(){
		String cod_zona= "";		
		int pos = cbxZona.getSelectedIndex();		
		cod_zona = listZona.get(pos).get("DMZON_CODIGO");		
		return cod_zona.trim();
	}
	
	private void loadNomina(String inTipoNomina, String inAnio, String inMes){		 
		try {
			listNomina.clear();
			cbxNomina.removeAllItems();
			listNomina = objConsulta.selectAllNomina(inTipoNomina, inAnio, inMes);
			for(int i=0; i<listNomina.size();i++){
				cbxNomina.addItem(listNomina.get(i).get("DNMPN_NOMBRE_NOMINA"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCodNomina(){
		String cod_nomina= "";
		int pos = cbxNomina.getSelectedIndex();
		cod_nomina = listNomina.get(pos).get("DNMPN_CODIGO");
		return cod_nomina.trim();
	}	
	
	public String getCampoNomina(String inCampo){
		String campoNomina= "";		
		int pos = cbxNomina.getSelectedIndex();		
		campoNomina = listNomina.get(pos).get(inCampo);		
		return campoNomina.trim();
	}
	
	private void loadPersonal(int inDnmtrEstadoPersona,String inZona, String inNomina){		 
		try {		
			objConsulta.selectByTipoNomina(inDnmtrEstadoPersona,inZona,inNomina,tbPersonal);		
			/*tbPersonal.setModel(personalModel);
			objConsulta.selectByTipoNomina(inDnmtrEstadoPersona,inZona,inNomina,personalModel);*/
						
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	private JTable getTbPersonal() {
		if (tbPersonal == null) {
			tbPersonal = new JTable();			
			
			tbPersonal.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
			{
			    @Override
			    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			    {
			        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			        
			        if(table.getValueAt(row, 5).toString().equalsIgnoreCase("Mensaje enviado") || table.getValueAt(row, 5).toString().equalsIgnoreCase("Pendiente")){
			        	if(isSelected){
			        		setBackground(table.getSelectionBackground());
			        		setForeground(table.getSelectionForeground());
			        	}else{
			        		setBackground(table.getBackground());
			        		setForeground(table.getForeground());
			        	}				        	           
			        }else{    
			        	setForeground(Color.black);        
			            setBackground(Color.red);
			        }     			        
			        return c;
			    }
			});
			JTable rowTable = new RowNumberTable(tbPersonal);
			scrollPane.setRowHeaderView(rowTable);
			scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,rowTable.getTableHeader());
		}
		return tbPersonal;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTbPersonal());
		}
		return scrollPane;
	}
} 
