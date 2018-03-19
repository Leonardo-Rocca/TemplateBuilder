package modelo;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

import plantilla.GenerarArchivoWord;

//import plantilla.GeneraArchivoWord;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *Creado por Leonardo Rocca
 */
public class ViewTransformador {

	private static final String VERSION = "3.1";
	private JFrame frame;
	private JTextPane textPane = new JTextPane();
	JLabel lblTextoCopiado = new JLabel("Texto copiado");

	//VENCIMIENTO DEL PROGRAMA
	private static void vencimiento() {
	//	Date fecha = new Date();
		Calendar c = Calendar.getInstance();
		String m = String.valueOf(c.get(Calendar.MONTH));
		
	//	System.out.println(m +" / "+ String.valueOf(c.get(Calendar.DATE)));
	//	if(fecha.toString().substring(3, 8).equals(" Mar ") && 	Integer.parseInt(fecha.toString().substring(8, 10))>29){
		if(c.get(Calendar.MONTH)>10 ){// && c.get(Calendar.DATE)>29 ){
			String msg = "La versi�n caduc�.";
			msg = "Error.";
			JOptionPane.showMessageDialog(new JPanel(), msg, "Error", JOptionPane.ERROR_MESSAGE);		  
			System.exit(0); 		//final date
		}
	}
	
	public static void main(String[] args) {
		
		vencimiento();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTransformador window = new ViewTransformador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ViewTransformador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	//	frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 621, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 84, 501, 309);
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
				convert(new FilterController());
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
		btnClean.setIcon(new ImageIcon(ViewTransformador.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		btnClean.setBounds(75, 417, 48, 40);
		frame.getContentPane().add(btnClean);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 619, 71);
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
		btnSalir.setBounds(504, 37, 85, 23);
		panel.add(btnSalir);
		
		JLabel lblVersin = new JLabel("Versi\u00F3n ");
		lblVersin.setText("Versión 3.1");
		lblVersin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersin.setForeground(Color.WHITE);
		lblVersin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVersin.setBounds(10, 30, 111, 34);
		panel.add(lblVersin);
		
		frame.getContentPane().add(btnConvertir);	
		
		JLabel lblNewLabel = new JLabel("\u00A9 2017 - Leo Rocca");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(40, 477, 192, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btno = new JButton("\u00ACO");
		btno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addO();
			}
		});
		btno.setBounds(477, 417, 55, 40);
		frame.getContentPane().add(btno);
		
		JButton btnPlantilla = new JButton("");
		btnPlantilla.setIcon(new ImageIcon(ViewTransformador.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		btnPlantilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				irAPlantilla();
			}
		});
	//	btnPlantilla.setIcon(new ImageIcon(ViewTransformador.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Indent-Black.png")));
		btnPlantilla.setBounds(389, 417, 76, 40);
		frame.getContentPane().add(btnPlantilla);
	}

	protected void irAPlantilla() {
		new ViewTemplateText().main(null);
	}

	protected void addO() {
		convert(new FiltroO());
	}

	protected void exit() {
		System.exit(0);
	}

	protected void clean() {
		textPane.setText("");
		lblTextoCopiado.setVisible(false);
	}

	protected void convert(Filtro filtro) {

	//	Filtro f = new filterPlantilla(); 
		String text = textPane.getText();
		textPane.setText(convertService(filtro, text));	
	/*	try {
			new GenerarArchivoWord().replaceTextFound("", 0, "leo", "texto",(filterPlantilla) f);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
*/
		textPane.selectAll();
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		lblTextoCopiado.setVisible(true);
	}

	public String convertService(Filtro filtro, String text) {
		if(filtro==null)filtro=new FilterController();
		return LeeFichero.readText(text, filtro);
	}	
	public String convertService(String text) {
		return convertService(new FilterController(),text); 
	}
	
	
}
