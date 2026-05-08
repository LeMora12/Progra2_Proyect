package programLibraries;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneAboutIt;
	private JPanel contentPaneInstruction;
	private JTable tableClient;
	private JTable tableArgument;
	private JFrame aboutIt;
	private JFrame instruction;
	private JButton btnSaveClient;
	private DefaultTableModel clientModel;
	private DefaultTableModel argumentModel;
	private String fileName = "DATA.clca";
	private DataModel model;
	private TableRowSorter<DefaultTableModel> rowFilterClient;
	private TableRowSorter<DefaultTableModel> rowFilterArgument;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	
	public Program() {
		setResizable(false);

		model = deserializable();
		
		setTitle("Mini Proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 487);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchive = new JMenu("Archivo");
		mnArchive.setForeground(new Color(51, 51, 51));
		menuBar.add(mnArchive);
		
		JMenuItem mntmLoad = new JMenuItem("Cargar Datos");
		mntmLoad.addActionListener(new ActionListener() {
			
			/*
        	 *Permite cargar el archivo .clca que hayamos creado haciendo que se vuelvan a poner las tablas creadas
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 01/05/24
        	 *@since 02/05/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				model = deserializable();
				clientModel = model.getClient();
				argumentModel = model.getClient();
				tableClient.setModel(clientModel);
				tableArgument.setModel(argumentModel);
				rowFilterClient.setModel(clientModel);
				rowFilterArgument.setModel(argumentModel);
				tableClient.setRowSorter(rowFilterClient);
				tableArgument.setRowSorter(rowFilterArgument);
			}
		});
		mnArchive.add(mntmLoad);
		
		JMenuItem mntmLogOut = new JMenuItem("Salir");
		mntmLogOut.addActionListener(new ActionListener() {
			
			/*
        	 *Permite cerrar el programa completamente
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.0.1
        	 *@date 28/03/24
        	 *@since 28/03/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame exit = new JFrame("Salir");
				if(JOptionPane.showConfirmDialog(exit, "¿Deseas cerrar el programa?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
			}
			
		});
		
		JMenuItem mntmDelete = new JMenuItem("Borrar Datos");
		mntmDelete.addActionListener(new ActionListener() {
			
			/*
        	 *Permite eliminar los datos que tiene el .clca creado para que sea como un "nuevo" archivo
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 01/05/24
        	 *@since 02/05/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File(fileName);
				file.delete();
			}
		});
		mnArchive.add(mntmDelete);
		mnArchive.add(mntmLogOut);
		
		JMenu mnHelp = new JMenu("Ayuda");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstructions = new JMenuItem("Instrucciones");
		mntmInstructions.setFont(new Font("Dialog", Font.BOLD, 12));
		mnHelp.add(mntmInstructions);
		contentPaneInstruction = new JPanel();
		contentPaneInstruction.setBorder(new EmptyBorder(5, 5, 5, 5));
		instruction = new JFrame();
		mntmInstructions.addActionListener(new ActionListener() {
			
			/*
        	 *Lanza una nueva ventana explicando como usar el programa.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.3
        	 *@date 27/04/24
        	 *@since 02/05/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!instruction.isVisible()) {
					instruction.setResizable(false);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					instruction.setBounds(100, 100, 500, 422);
					
					contentPaneInstruction = new JPanel();
					contentPaneInstruction.setLayout(new BoxLayout(contentPaneInstruction, BoxLayout.Y_AXIS));
					contentPaneInstruction.setBorder(new EmptyBorder(5,5,5,5));
					
					JLabel lblInstruction_1 = new JLabel("<html><u> INSTRUCCIONES  </u></html>");
					lblInstruction_1.setFont(new Font("Dialog", Font.BOLD, 30));
					lblInstruction_1.setFont(new Font("Dialog", 1, 30));
					lblInstruction_1.setBounds(10, 10, 10, 10);
					
					JLabel lblInstruction = new JLabel("<html> Para poder utilizar este programa primero debemos de rellenar todos los datos solicitados, despues de ello le dara un click en <u> Guardar</u> y asi sera sucesivamente las veces que quiera agregar una nueva tabla y para que no este borrando a cada rato lo que habia escrito anteriormente despues de guardar presione el boton nuevo para que se limpie la lista de datos. <br><br> Si quiere modificar una tabla seleccionara la tabla que deseara modificar y el boton de <u>Guardar</> se modificara a un boton llamado <u>Modificar</u> y asi podra modificar su tabla. <br><br>Si quiere eliminar la tabla solo seleccione la tabla que desea eliminar y le da click al boton <u>Eliminar</u> y asi sera para la ventana de descripcion de cliente. <br><br> Si desea borrar los datos de la tabla ira a la parte de archivos e ira a darle <u>Borrar Datos</u> para que cuando vuelva a ejecutar su programa no tenga ningun dato guardado y si quiere cargar los datos guardados solo presione <u>Cargar Datos</u>.  </html>");
					lblInstruction.setFont(new Font("Dialog", 1, 14));
					lblInstruction.setForeground(new Color(0, 128, 128));
					lblInstruction.setBounds(10, 10, 10, 10);
					
					contentPaneInstruction.add(lblInstruction_1);
					contentPaneInstruction.add(lblInstruction);
					contentPaneInstruction.setBackground(Color.LIGHT_GRAY);
					instruction.setContentPane(contentPaneInstruction);
					instruction.setVisible(true);
				}
			}
			
		});
		
		
		JMenuItem mntmAboutIt = new JMenuItem("Acerca De");
		mntmAboutIt.setFont(new Font("Dialog", Font.BOLD, 12));
		mnHelp.add(mntmAboutIt);
		contentPaneAboutIt = new JPanel();
		contentPaneAboutIt.setBorder(new EmptyBorder(5, 5, 5, 5));
		aboutIt = new JFrame();
		mntmAboutIt.addActionListener(new ActionListener() {
			
			/*
        	 *Lanza una nueva ventana con los detalles del creador del programa, docente, clase, fecha, etc.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.0.1
        	 *@date 27/04/24
        	 *@since 27/04/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
					if(!aboutIt.isVisible()) {
						aboutIt.setResizable(false);
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						aboutIt.setBounds(100, 100, 500, 420);
						
						contentPaneAboutIt = new JPanel();
						contentPaneAboutIt.setLayout(new BoxLayout(contentPaneAboutIt, BoxLayout.Y_AXIS));
						contentPaneAboutIt.setBorder(new EmptyBorder(5,5,5,5));
						
						JLabel lblAboutIt = new JLabel("<html><u> P-II-CLIENT  </u></html>");
						lblAboutIt.setFont(new Font("Dialog", Font.BOLD, 30));
						lblAboutIt.setFont(new Font("Dialog", 1, 30));
						lblAboutIt.setBounds(10, 10, 10, 10);
						JLabel lblAboutIt_1 = new JLabel("<html><u> MANAGMENT™  </u></html>");
						lblAboutIt_1.setFont(new Font("Dialog", 1, 30));
						lblAboutIt_1.setBounds(5, 5, 5, 5);
						JLabel lblAboutIt_2 = new JLabel("<html><br><u> Hecho por Cristhian Leandro Cárdenas Aguilar </u></br></html>");
						lblAboutIt_2.setFont(new Font("Dialog", 3, 14));
						lblAboutIt_2.setForeground(new Color(0, 128, 128));
						lblAboutIt_2.setBounds(5, 5, 5, 5);
						JLabel lblAbouIt_3 = new JLabel("Numero de Cuenta: 20231002983");
						lblAbouIt_3.setFont(new Font("Dialog", 3, 14));
						lblAbouIt_3.setForeground(new Color(0, 128, 128));
						lblAbouIt_3.setBounds(5, 5, 5, 5);
						JLabel lblAbouIt_4 = new JLabel("Correo Institucional: clcardenas@unah.hn");
						lblAbouIt_4.setFont(new Font("Dialog", 3, 14));
						lblAbouIt_4.setForeground(new Color(0, 128, 128));
						lblAbouIt_4.setBounds(5, 5, 5, 5);
						JLabel lblAbouIt_5 = new JLabel("Asignatura: Programacion II (IS-210)");
						lblAbouIt_5.setFont(new Font("Dialog", 3, 14));
						lblAbouIt_5.setForeground(new Color(0, 128, 128));
						lblAbouIt_5.setBounds(5, 5, 5, 5);
						JLabel lblAbouIt_6 = new JLabel("Fecha: 3 de Mayo del 2024");
						lblAbouIt_6.setFont(new Font("Dialog", 3, 14));
						lblAbouIt_6.setForeground(new Color(0, 128, 128));
						lblAbouIt_6.setBounds(5, 5, 5, 5);
						JLabel lblAbouIt_7 = new JLabel("Docente: Ingenierio José Manuel Inestroza Murillo");
						lblAbouIt_7.setFont(new Font("Dialog", 3, 14));
						lblAbouIt_7.setForeground(new Color(0, 128, 128));
						lblAbouIt_7.setBounds(5, 5, 5, 5);
						JLabel lblAbouIt_8 = new JLabel("<html><br> Este programa agrega tablas que el usuario implementa poniendo datos en nombre, teléfono, etc. y al cerrarse el programa guardara las tablas ingresadas en el disco del computador y cuando se ejecute devuelta el programa se miraran las tablas hechas por el usuario con la capacidad de modificar y eliminar las tablas que el usuario seleccione. </br></html>");
						lblAbouIt_8.setFont(new Font("Dialog", 3, 12));
						lblAbouIt_8.setBounds(5, 5, 5, 5);
						JLabel lblAbouIt_9 = new JLabel("<html><br> La funcionalidad principal del programa esta en el clase Program donde en otras clases vamos a estar manejando flujos de datos como el serializable de las tablas y los validadores necesarios que inclusivemente tendremos validadores en el programa principal para no tener tantos datos en otras clases para que sea mas facil para el programador entender el codigo. </b></html>");
						lblAbouIt_9.setFont(new Font("Dialog", 3, 12));
						lblAbouIt_9.setBounds(5, 5, 5, 5);
						
						
						contentPaneAboutIt.setBackground(Color.LIGHT_GRAY);
						aboutIt.setContentPane(contentPaneAboutIt);
						contentPaneAboutIt.add(lblAboutIt);
						contentPaneAboutIt.add(lblAboutIt_1);
						contentPaneAboutIt.add(lblAboutIt_2);
						contentPaneAboutIt.add(lblAbouIt_3);
						contentPaneAboutIt.add(lblAbouIt_4);
						contentPaneAboutIt.add(lblAbouIt_5);
						contentPaneAboutIt.add(lblAbouIt_6);
						contentPaneAboutIt.add(lblAbouIt_7);
						contentPaneAboutIt.add(lblAbouIt_8);
						contentPaneAboutIt.add(lblAbouIt_9);
						aboutIt.setVisible(true);
					}	
			}
			
		});
			

		setContentPane(contentPaneAboutIt);
		contentPaneAboutIt.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 12, 741, 429);
		contentPaneAboutIt.add(tabbedPane);
		
		JPanel Client = new JPanel();
		tabbedPane.addTab("Cliente", null, Client, null);
		Client.setLayout(null);
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setBounds(45, 23, 55, 15);
		Client.add(lblName);
		
		JLabel lblPhone = new JLabel("Teléfono");
		lblPhone.setBounds(45, 53, 67, 15);
		Client.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Correo");
		lblEmail.setBounds(45, 83, 55, 15);
		Client.add(lblEmail);
		
		JLabel lblObs = new JLabel("Observación");
		lblObs.setBounds(45, 126, 107, 15);
		Client.add(lblObs);
		
		JTextArea txtName = new JTextArea();
		txtName.addKeyListener(new KeyAdapter() {
			
			/*
        	 *Restringe el uso de caracteres solo permitiendo escribir letras.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.0.1
        	 *@date 27/04/24
        	 *@since 27/04/24   	
        	 */
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE)) {
					e.consume();
				}
				
			}
		});
		txtName.setBounds(173, 23, 515, 20);
		Client.add(txtName);
		
		JTextArea txtPhone = new JTextArea();
		txtPhone.addKeyListener(new KeyAdapter() {
			
			/*
        	 *Restringe el uso de caracteres solo permitiendo ingresar numeros y hasta un maximo de 8 digitos.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.0.1
        	 *@date 27/04/24
        	 *@since 27/04/24   	
        	 */
			
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean number = key >=48 && key <=57;
				
				if(!number) {
					e.consume();
				}
				if(txtPhone.getText().trim().length()==8) {
					e.consume();
				}
				
			}
		});
		txtPhone.setBounds(173, 53, 515, 20);
		Client.add(txtPhone);
		
		JTextArea txtEmail = new JTextArea();
		txtEmail.setBounds(173, 83, 515, 20);
		Client.add(txtEmail);
		
		JTextArea txtObs = new JTextArea();
		txtObs.setWrapStyleWord(true);
		txtObs.setLineWrap(true);
		txtObs.setBounds(173, 113, 515, 46);
		Client.add(txtObs);
		
		JScrollPane scrollObs = new JScrollPane(txtObs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollObs.setBounds(173, 113, 515, 46);
		Client.add(scrollObs);
		
		JButton btnNewClient = new JButton("Nuevo");
		btnNewClient.addActionListener(new ActionListener() {
			
			/*
        	 *Borra lo que tiene en los textos para que el usuario pueda ingresar nuevos datos.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.0.1
        	 *@date 26/04/24
        	 *@since 26/04/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
                txtPhone.setText("");
                txtEmail.setText("");
                txtObs.setText("");
			}
		});
		btnNewClient.setToolTipText("Limpiar las cajas de texto para agregar un nuevo elemento.");
		btnNewClient.setBounds(45, 171, 139, 25);
		Client.add(btnNewClient);
		
		
		btnSaveClient = new JButton("Guardar");
		btnSaveClient.setToolTipText("Guarda las tablas hechas.");
		btnSaveClient.addActionListener(new ActionListener() {
			
			/*
        	 *Verifica si tiene una tabla seleccionada para editar y tambien verifica si tiene todos los contenidos de los textos
        	 *agregados con su validador de correo para que solo admita correos legitimosn y cada vez que se guarda una nueva tabla
        	 *se guarda en el .clca creado.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 26/04/24
        	 *@since 01/05/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableClient.getModel();
				int selectedRow = tableClient.getSelectedRow();
				String name = txtName.getText();
	            String phone = txtPhone.getText();
	            String email = txtEmail.getText();
	            String obs = txtObs.getText();
				if (selectedRow != -1) {
					model.setValueAt(name, selectedRow, 0);
					model.setValueAt(phone, selectedRow, 1);
					model.setValueAt(email, selectedRow, 2); 
					model.setValueAt(obs, selectedRow, 3);
		            tableClient.clearSelection();
		            if (isValidEmail(email)) {} 
		        } else {
		            if (!name.isEmpty() && !phone.isEmpty() && isValidEmail(email) && !obs.isEmpty()) {
		            	model.addRow(new Object[]{name, phone, email, obs});
		            } else {
		                JOptionPane.showMessageDialog(null, "Rellene todos los espacios solicitados correctamente para continuar.", "Advertencia", JOptionPane.PLAIN_MESSAGE);
		                return;
		            }
		        }
				serializable();
		    }
		});
		btnSaveClient.setBounds(196, 171, 240, 25);
		Client.add(btnSaveClient);
		
		JButton btnDeleteCLient = new JButton("Eliminar");
		btnDeleteCLient.setEnabled(false);
		btnDeleteCLient.setToolTipText("Elimina las tablas seleccionadas");
		btnDeleteCLient.addActionListener(new ActionListener() {
			
			/*
        	 *Elimina la tabla seleccionada y lo guarda en el .dat creado para ir haciendo actualizaciones reales de las tablas.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 26/04/24
        	 *@since 01/05/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableClient.getModel();
				model.removeRow(tableClient.getSelectedRow());
				serializable();
			}
		});
		btnDeleteCLient.setBounds(448, 171, 240, 25);
		Client.add(btnDeleteCLient);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 235, 712, 155);
		Client.add(scrollPane);
		
		
		tableClient = new JTable();
		tableClient.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			/*
        	 *Mira si tiene una fila seleccionada y modfica lo que tiene en las tabla seleccionada y lo guarda en el .clca por cada modificacion hecha.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 26/04/24
        	 *@since 1/05/24   	
        	 */
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableClient.getModel();
				if (!e.getValueIsAdjusting()) {
					if (tableClient.getSelectedRow() != -1) {
						btnDeleteCLient.setEnabled(true);
						btnSaveClient.setText("Modificar");
						int selectedRow = tableClient.getSelectedRow();
						txtName.setText(model.getValueAt(selectedRow, 0).toString());
						txtPhone.setText(model.getValueAt(selectedRow, 1).toString());
						txtEmail.setText(model.getValueAt(selectedRow, 2).toString());
						txtObs.setText(model.getValueAt(selectedRow, 3).toString());
					}else {
						btnSaveClient.setText("Guardar");
						btnDeleteCLient.setEnabled(false);
					}
				}
				serializable();
			}
		});
		clientModel = model.getClient();
		rowFilterClient = new TableRowSorter<>(clientModel);
		tableClient.setModel(clientModel);
		tableClient.getColumnModel().getColumn(0).setPreferredWidth(135);
		tableClient.getColumnModel().getColumn(1).setPreferredWidth(163);
		tableClient.getColumnModel().getColumn(2).setPreferredWidth(137);
		tableClient.getColumnModel().getColumn(3).setPreferredWidth(180);
		scrollPane.setViewportView(tableClient);
		tableClient.setRowSorter(rowFilterClient);
	
		JTextArea txtLook = new JTextArea();
		txtLook.addKeyListener(new KeyAdapter() {
			
			/*
        	 *Filtra las filas segun el texto que ingresemos para encontrar la tabla seleccionada.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 01/05/24
        	 *@since 02/05/24   	
        	 */
			
			@Override
			public void keyTyped(KeyEvent e) {
				RowFilter<DefaultTableModel, Integer> rFilter = null;
				try {
					
					rFilter = RowFilter.regexFilter(txtLook.getText());
					
				}catch(java.util.regex.PatternSyntaxException e1){
					return;
				}
				rowFilterClient.setRowFilter(rFilter);
			}
		});
		txtLook.setToolTipText("Busqueda de tablas");
		txtLook.setBounds(512, 208, 212, 15);
		Client.add(txtLook);
		
		JLabel lblNewLabel = new JLabel("Buscar");
		lblNewLabel.setBounds(458, 208, 70, 15);
		Client.add(lblNewLabel);
		
		JPanel User = new JPanel();
		tabbedPane.addTab("Descripcion del Cliente", null, User, null);
		User.setLayout(null);
		
		JLabel lblArgumentPartI = new JLabel("Agregue una nueva nota\n");
		lblArgumentPartI.setBounds(31, 24, 181, 15);
		User.add(lblArgumentPartI);
		
		JLabel lblArgumentPartII = new JLabel("escribiendo un texto y ");
		lblArgumentPartII.setBounds(31, 38, 181, 15);
		User.add(lblArgumentPartII);
		
		JLabel lblArgumentPartIII = new JLabel("presionando el boton de");
		lblArgumentPartIII.setBounds(31, 51, 181, 15);
		User.add(lblArgumentPartIII);
		
		JLabel lblArgumentPartIV = new JLabel("agregar.");
		lblArgumentPartIV.setBounds(31, 64, 181, 15);
		User.add(lblArgumentPartIV);
		
		JTextArea txtArgument = new JTextArea();
		txtArgument.setWrapStyleWord(true);
		txtArgument.setLineWrap(true);
		txtArgument.setBounds(-26, 24, 451, 81);
		User.add(txtArgument);
		
		JScrollPane scroll = new JScrollPane(txtArgument, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(244, 12, 451, 81);
		User.add(scroll);
		
		JButton btnNewArgument = new JButton("Nuevo");
		btnNewArgument.setToolTipText("Limpiar las cajas de texto para agregar un nuevo elemento.");
		btnNewArgument.addActionListener(new ActionListener() {
			
			/*
        	 *Borra lo que tiene en el texto para que el usuario pueda ingresar nuevos datos.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.0.1
        	 *@date 26/04/24
        	 *@since 26/04/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtArgument.setText("");
			}
		});
		btnNewArgument.setBounds(31, 109, 117, 25);
		User.add(btnNewArgument);
		

		
		JButton btnAddArgument = new JButton("Agregar Nota");
		btnAddArgument.addActionListener(new ActionListener() {
			
			/*
        	 *Verifica si tiene una tabla seleccionada para editar y tambien verifica si tiene todos los contenidos de los textos
        	 *agregados con su validador de correo para que solo admita correos legitimosn y cada vez que se guarda una nueva tabla
        	 *se guarda en el .clca creado.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 26/04/24
        	 *@since 01/05/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableArgument.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) tableArgument.getModel();
				String note = txtArgument.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String date = sdf.format(new Date());
				if (selectedRow != -1) {
					model.setValueAt(date, selectedRow, 0);
					model.setValueAt(txtArgument.getText(), selectedRow, 1);
					tableArgument.clearSelection();
				}else {
					if (!note.isEmpty()) {
						model.addRow(new Object[]{date, note});
					}else {
	            		JOptionPane.showMessageDialog(null, "Rellene el espacio solicitado para continuar.", "Advertencia", JOptionPane.PLAIN_MESSAGE);
	            	}
				}
				serializable();
				
			}
		});
		btnAddArgument.setToolTipText("Agrega una nueva nota en la tabla.");
		btnAddArgument.setBounds(243, 109, 220, 25);
		User.add(btnAddArgument);
		
		JButton btnDeleteArgument = new JButton("Eliminar");
		btnDeleteArgument.setEnabled(false);
		btnDeleteArgument.addActionListener(new ActionListener() {
			
			/*
        	 *Elimina la tabla seleccionada y lo guarda en el .dat creado para ir haciendo actualizaciones reales de las tablas.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 26/04/24
        	 *@since 01/05/24   	
        	 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableArgument.getModel();
				model.removeRow(tableArgument.getSelectedRow());
				serializable();
			}
		});
		btnDeleteArgument.setToolTipText("Elimina la tabla seleccionada.");
		btnDeleteArgument.setBounds(475, 109, 220, 25);
		User.add(btnDeleteArgument);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 168, 712, 222);
		User.add(scrollPane_1);
		
		tableArgument = new JTable();
		tableArgument.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			/*
        	 *Mira si tiene una fila seleccionada y modfica lo que tiene en las tabla seleccionada y lo guarda en el .clca por cada modificacion hecha.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 26/04/24
        	 *@since 1/05/24   	
        	 */
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					DefaultTableModel model = (DefaultTableModel) tableArgument.getModel();
					if (tableArgument.getSelectedRow() != -1) {
						btnDeleteArgument.setEnabled(true);
						btnAddArgument.setText("Modificar");
						int selectedRow = tableArgument.getSelectedRow();
						txtArgument.setText(model.getValueAt(selectedRow, 1).toString());
					}else {
						btnAddArgument.setText("Agregar Nota");
						btnDeleteArgument.setEnabled(false);
					}
				}
				serializable();
			}
		});
		argumentModel = model.getArgument();
		rowFilterArgument = new TableRowSorter<>(argumentModel);
		tableArgument.setModel(argumentModel);
		tableArgument.getColumnModel().getColumn(0).setPreferredWidth(205);
		tableArgument.getColumnModel().getColumn(1).setPreferredWidth(421);
		scrollPane_1.setViewportView(tableArgument);
		tableArgument.setRowSorter(rowFilterArgument);
		
		JTextArea txtsearch = new JTextArea();
		txtsearch.addKeyListener(new KeyAdapter() {
			
			/*
        	 *Filtra las filas segun el texto que ingresemos para encontrar la tabla seleccionada.
        	 * 
        	 *@author clcardenas@unah.hn
        	 *@version 0.1.1
        	 *@date 01/05/24
        	 *@since 02/05/24   	
        	 */
			
			@Override
			public void keyTyped(KeyEvent e) {
				RowFilter<DefaultTableModel, Integer> rFilter = null;
				try {
					
					rFilter = RowFilter.regexFilter(txtsearch.getText());
					
				}catch(java.util.regex.PatternSyntaxException e2){
					return;
				}
				rowFilterArgument.setRowFilter(rFilter);
			}
		});
		txtsearch.setToolTipText("Busqueda de tablas");
		txtsearch.setBounds(512, 141, 212, 15);
		User.add(txtsearch);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar");
		lblNewLabel_1.setBounds(458, 141, 70, 15);
		User.add(lblNewLabel_1);
	}
	
	
	/*
	 *Valida si en el txtEmail tiene los parametros que un correo tiene.
	 * 
	 *@author clcardenas@unah.hn
	 *@version 0.0.1
	 *@date 01/05/24
	 *@since 01/05/24   	
	 */
	
	private boolean isValidEmail(String email) {
        return Validator.isEmail(email);
    }
	
	
	/*
	 *Gaurda los datos en el .clca.
	 * 
	 *@author jose.inestroza@unah.edu.hn
	 *@version 0.0.1
	 *@date 01/05/24
	 *@since 01/05/24   	
	 */
	
	private void serializable() {
		DataModel model = new DataModel();
		model.setClient(clientModel);
		model.setArgument(argumentModel);
		
		try {
			
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(model);
			oos.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 *Carga los datos del .clca.
	 * 
	 *@author jose.inestroza@unah.edu.hn
	 *@version 0.0.1
	 *@date 01/05/24
	 *@since 01/05/24   	
	 */
	
	private DataModel deserializable() {

		try {
			
			FileInputStream fis = new FileInputStream(fileName);
			try (ObjectInputStream ois = new ObjectInputStream(fis)) {
				DataModel newDataModel = (DataModel)ois.readObject();
					
				return newDataModel;
			}
		}catch(IOException | ClassNotFoundException e) {
			return new DataModel();
		}
		
	}
}


