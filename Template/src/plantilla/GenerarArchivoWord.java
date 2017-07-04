package plantilla;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.swing.JLabel;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import modelo.Filtro;
import modelo.filterPlantilla;

//---------------------Template----------------

public class GenerarArchivoWord {
	public String replaceTextFound(String path, int id, String nombre, String texto, filterPlantilla f) throws IOException {
	
		ArrayList<Component> components = f.getComponents();
		
		String realContextPath = path;
		/*String inputfilepath = realContextPath+"/models"+ "/PLANTILLA.docx";		String outputfilepath = realContextPath+"/docs/"+id+"_Expediente.docx";*/
		String relativeOutputfilepath = "/docs/"+id+"_Expediente.docx";

		String inputfilepath = realContextPath+ "PLANTILLA.docx";
		String outputfilepath = realContextPath+"_Expediente.docx";

		InputStream fs = new FileInputStream(inputfilepath);		
		XWPFDocument doc = new XWPFDocument(fs);		

	
		Component comp=new Component("Hora: ${proveedor_pp!}", 2); 
	//	components.add(comp);
		int quantityComponents = components.size();//quantityComponents=2;
		
		//Creamos el extractor pasandole el stream
		XWPFWordExtractor xwpf_we = new XWPFWordExtractor(doc); 
		String textoEntero = xwpf_we.getText(); 

		for(int i=0;i<quantityComponents;i++){
			comp = components.get(i);
			
			String regex = "\\[[0-9]{1,3}.\\s?"+comp.getLabel()+"\\s?\\]";

			textoEntero = textoEntero.replaceFirst(regex,  Matcher.quoteReplacement(comp.getValue() ));
		}
		System.out.println(textoEntero);
		 textoEntero.split("\n");
		XWPFRun r2 = doc.createParagraph().createRun();
		r2.setText(textoEntero);
		r2.setFontSize(12);
		r2.addCarriageReturn();
		
		for( int j=0;j<0;j++){
				comp = components.get(j);
		for (XWPFParagraph p : doc.getParagraphs()) {
		List<XWPFRun>  runs = p.getRuns();


		if (runs != null) {
		for (XWPFRun r : runs) {
		String text = r.getText(0);

		if (text != null){
		//	if (text.contains("$ID")) {
				
				String regex = "\\[[0-9]{1,2}."+comp.getLabel()+"\\]";
			//	texto="[5-"+comp.getLabel()+"]";
				System.out.println(text);
				text = text.replaceFirst(regex,  Matcher.quoteReplacement(comp.getValue() ));
				r.setText(text, 0);
		//	}
			
		}
		if (text != null){			
			if (text.contains("$ID")) {
				text = text.replace("$ID", String.valueOf(id));
				r.setText(text, 0);
			}if(text.contains("$NOMBRE")) {
				text = text.replace("$NOMBRE", nombre);
				r.setText(text, 0);
		}if(text.contains("$TEXTO")) {
		text = text.replace("$TEXTO", texto);
		r.setText(text, 0);
		}
		}
		}
		}
		 

		}		
		}
		FileOutputStream out = new FileOutputStream(outputfilepath);
		doc.write(out);
		out.close();
		return relativeOutputfilepath;
		}

	public String replaceTextFoundFromText(String text, String sep, filterPlantilla f) {

		ArrayList<Component> components = f.getComponents();
	
		Component comp=new Component("Hora: ${proveedor_pp!}", 2); 

			int quantityComponents = components.size();//quantityComponents=2;

			String textoEntero = text; 

			for(int i=0;i<quantityComponents;i++){
				comp = components.get(i);
				
				
			String label = comp.getLabel();
			String capitals = this.toCamelCase(label,false);
			String capitalsY = this.toCamelCase(label,true);
			String lower = Character.toLowerCase(label.charAt(0))+label.substring(1); 
		//	System.out.println(capitals);
			
				String regex = "\\[[0-9]{1,3}"+sep+"\\s?"+"("+capitals+"|"+label+"|"+lower+"|"+capitalsY+")"+"\\s?\\]";

				textoEntero = textoEntero.replaceFirst(regex,  Matcher.quoteReplacement(comp.getValue() ));
			}
			System.out.println(textoEntero);
		//	 textoEntero.split("\n");
			 
		return textoEntero;
	}
	
	static String toCamelCase(String texto,boolean flag){
	    if(texto.length()<2)return texto;
	    
		String[] parts = texto.split(" ");

		   final StringBuilder ret = new StringBuilder(texto.length());

		for (String word : parts) {
	    	
		if(word!=null && word.trim().length()>0){
			 if(word.length()<2&&flag){
				 ret.append(word.charAt(0));
			 }else{
				 ret.append(Character.toUpperCase(word.charAt(0)));
			 }
			ret.append(word.substring(1,word.length()));
			ret.append(" ");}
		
	        }		
	    return ret.substring(0,ret.length()-1).toString();
	}
	
}
