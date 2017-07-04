package modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import plantilla.GenerarArchivoWord;

import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

public class ViewTemplateText {

	private JFrame frame;
	private JTextPane textPane = new JTextPane();
	JLabel lblTextoCopiado = new JLabel("Texto copiado");
	private JTextField txtSeparador;
	private filterPlantilla f = new filterPlantilla(); 
	private JLabel lblVariablesAgregadas;
	private JLabel lbEj;
	private JCheckBox chkRepetidos;
	private JTextPane textPaneVars;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTemplateText window = new ViewTemplateText();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewTemplateText() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 621, 540);

		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 136, 328, 257);
		frame.getContentPane().add(scrollPane);
		textPane.setBackground(new Color(234, 255, 255));
		scrollPane.setViewportView(textPane);
		
		textPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				 SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			                textPane.selectAll();		            	
			                }
			        });
			    }
			});
		
		
		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.setFont(new Font("Verdana", Font.BOLD, 11));
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convert();
			}
		});
		btnConvertir.setBounds(225, 409, 154, 48);

		lblTextoCopiado.setBackground(Color.BLACK);
		lblTextoCopiado.setForeground(Color.BLACK);
		
		lblTextoCopiado.setVisible(false);
		lblTextoCopiado.setBounds(249, 477, 154, 14);
		frame.getContentPane().add(lblTextoCopiado);
		
		JLabel lblEditorDeTemplates = new JLabel("Editor de Templates");
		lblEditorDeTemplates.setForeground(new Color(255, 255, 255));
		lblEditorDeTemplates.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorDeTemplates.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEditorDeTemplates.setBounds(105, 21, 394, 34);
		frame.getContentPane().add(lblEditorDeTemplates);
		
		JButton btnClean = new JButton("");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		btnClean.setIcon(new ImageIcon(ViewTransformador.class.getResource("/com/sun/javafx/scene/web/skin/FontBackgroundColor_16x16_JFX.png")));
		btnClean.setBounds(23, 417, 48, 40);
		frame.getContentPane().add(btnClean);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 605, 71);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(0, 102, 153));
		btnSalir.setBounds(504, 37, 60, 23);
		panel.add(btnSalir);
		
		JLabel lblVersin = new JLabel("Versi\u00F3n 3.0");
		lblVersin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersin.setForeground(Color.WHITE);
		lblVersin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVersin.setBounds(10, 30, 111, 34);
		panel.add(lblVersin);
		
		frame.getContentPane().add(btnConvertir);	
		
		JLabel lblNewLabel = new JLabel("\u00A9 2017 - Leo Rocca");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(40, 477, 129, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAddVars = new JButton("Agregar variables");
		btnAddVars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addVars();
			}
		});
		btnAddVars.setBounds(415, 417, 138, 40);
		btnAddVars.setVisible(false);
		frame.getContentPane().add(btnAddVars);
		
		txtSeparador = new JTextField();
		txtSeparador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lbEj.setText("Ej: 18"+txtSeparador.getText()+"Primer Nombre");
				}
		});
		txtSeparador.setText(".");
		txtSeparador.setBounds(138, 82, 86, 20);
		frame.getContentPane().add(txtSeparador);
		txtSeparador.setColumns(10);
		
		JLabel lblSeparador = new JLabel("Separador");
		lblSeparador.setBounds(10, 85, 92, 14);
		frame.getContentPane().add(lblSeparador);
		
		 lbEj = new JLabel("Ej: 18"+txtSeparador.getText()+"Primer Nombre");
		lbEj.setBounds(257, 85, 146, 17);
		frame.getContentPane().add(lbEj);
		
		 lblVariablesAgregadas = new JLabel("Variables");
		lblVariablesAgregadas.setBounds(71, 111, 153, 14);
		frame.getContentPane().add(lblVariablesAgregadas);
		
		chkRepetidos = new JCheckBox("Eliminar repetidos");
		chkRepetidos.setBounds(91, 434, 120, 23);
		frame.getContentPane().add(chkRepetidos);
		
		JLabel lblTemplate = new JLabel("Template");
		lblTemplate.setBounds(364, 111, 92, 14);
		frame.getContentPane().add(lblTemplate);
		
		JScrollPane scrollPaneVars = new JScrollPane();
		scrollPaneVars.setBounds(23, 136, 204, 257);
		frame.getContentPane().add(scrollPaneVars);
		
		 textPaneVars = new JTextPane();
		 scrollPaneVars.setViewportView(textPaneVars);
		 textPaneVars.setBackground(new Color(234, 255, 255));
		
	//	lblVariablesAgregadas.setVisible(false);
	}

	protected void convert() {	
		addVars();
	//	lblVariablesAgregadas.setVisible(false);
		f.eliminarRepetidos(chkRepetidos.isSelected());
		textPane.setText(remplazarTexto(remplazarTexto(textPane.getText())));

		textPane.selectAll();
		StringSelection stringSelection = new StringSelection(textPane.getText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		lblTextoCopiado.setVisible(true);
	}

	private String remplazarTexto(String text) {
		return new GenerarArchivoWord().replaceTextFoundFromText(text, txtSeparador.getText(), f);
	}

	protected void addVars() {
		f = new filterPlantilla(); 
		textPaneVars.setText(LeeFichero.readText(textPaneVars.getText(), f));	
	//	lblVariablesAgregadas.setVisible(true);
	}

	protected void clean() {
		textPane.setText("");
		lblTextoCopiado.setVisible(false);
	}

	protected void exit() {
		System.exit(0);
	}
}
