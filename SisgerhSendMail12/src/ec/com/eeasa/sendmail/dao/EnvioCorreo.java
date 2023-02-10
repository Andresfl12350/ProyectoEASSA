package ec.com.eeasa.sendmail.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import ec.com.eeasa.sendmail.util.ConnectionManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import javax.mail.util.ByteArrayDataSource;

public class EnvioCorreo {

	String inAsunto=null;
	String inNombreArchivo=null;
	String inNomina=null;
	
	public EnvioCorreo(){};
	
	public EnvioCorreo(String inAsunto, String inNombreArchivo, String inNomina) {
		super();
		this.inAsunto = inAsunto;
		this.inNombreArchivo = inNombreArchivo;
		this.inNomina = inNomina;
	}

	public String enviarRol(String inRolIni,String inRolFin,String inCorreoPersonal) {
	    String respuesta="";
		
	    // La dirección de la cuenta de envío (from)
	    String envia = "sisgerh@eeasa.com.ec";
	    
	    // La dirección de envío (to)
	    String recibe = inCorreoPersonal;
	    
	    // El servidor (host). En este caso usamos localhost
	    //String host = "zimbra.eeasa.com.ec";
	    String host = "172.16.1.21";

	    // Obtenemos las propiedades del sistema
	    Properties propiedades = System.getProperties();

	    // Configuramos el servidor de correo
	    propiedades.setProperty("mail.smtp.host", host);	    
	    propiedades.put("mail.smtp.host", host);
	    //propiedades.put("mail.smtp.user", from);
	    //propiedades.put("mail.smtp.password", pass);
	    propiedades.put("mail.smtp.port", "25");
	    

	    // Obtenemos la sesión por defecto
	    Session sesion = Session.getDefaultInstance(propiedades);

	    try{
	      // Creamos un objeto mensaje tipo MimeMessage por defecto.
	      MimeMessage mensaje = new MimeMessage(sesion);

	      // Asignamos el “de o from” al header del correo.
	      mensaje.setFrom(new InternetAddress(envia));

	      // Asignamos el “para o to” al header del correo.
	      mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(recibe));
	      mensaje.addRecipient(Message.RecipientType.CC, new InternetAddress(envia));

	      // Asignamos el asunto
	      mensaje.setSubject(this.inAsunto);

		      // Creamos un cuerpo del correo con ayuda de la clase BodyPart
		      BodyPart cuerpoMensaje = new MimeBodyPart();
	
		      // Asignamos el texto del correo
		      cuerpoMensaje.setText("Saludos Cordiales\n Sirvase revisar la informacion de su rol de pagos adjunto al presente correo.");
	
		      // Creamos un multipart al correo
		      Multipart multipart = new MimeMultipart();
	
		      // Agregamos el texto al cuerpo del correo multiparte
		      multipart.addBodyPart(cuerpoMensaje);
	
		      // Ahora el proceso para adjuntar el archivo
		      cuerpoMensaje = new MimeBodyPart();
		      		      
		      DataSource fuente = new ByteArrayDataSource(generarPDF(inRolIni,inRolFin),"application/octet-stream");
		      
		      cuerpoMensaje.setDataHandler(new DataHandler(fuente));
		      cuerpoMensaje.setFileName(inNombreArchivo);
		      		      
		      multipart.addBodyPart(cuerpoMensaje);

	      // Asignamos al mensaje todas las partes que creamos anteriormente
	      mensaje.setContent(multipart);
	      
	      // Enviamos el correo
	      Transport.send(mensaje);
	      respuesta = "Mensaje enviado";
	    } catch (MessagingException e) {	      
	      respuesta = e.getMessage();
	      e.printStackTrace();
	    }
	    
	    return respuesta;
	  }
	  
	  
	  public byte[] generarPDF(String inRolIni, String inRolFin){
		  byte[] archivoPDF = null;
		  try {		
				Connection connection = ConnectionManager.getConnection();
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				// agrega parametros				
				parameters.put("SUBREPORT_DIR", "");
				parameters.put("P_DNMPN_CODIGO", this.inNomina);
				parameters.put("P_DMPER_NUMERO_ROL_INICIO", inRolIni);
				parameters.put("P_DMPER_NUMERO_ROL_FIN", inRolFin);
				
				InputStream inputStream = EnvioCorreo.class.getClassLoader().getResourceAsStream("rptListadoRolIndividual.jrxml");				
				JasperReport report = JasperCompileManager.compileReport(inputStream);				
				JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);

				//exporta el reporte a pdf
				//JasperExportManager.exportReportToPdfFile(print, ArchivoUtil.getURLServidor()+"C:\\rptListadoRolIndividual.pdf");
				
				archivoPDF = JasperExportManager.exportReportToPdf(print);	    	
		    	
		    	ConnectionManager.closeConecction();
			} catch (Exception e) {
				e.printStackTrace();			
				System.out.println("Existe un error al generar el PDF. "+e.getMessage());
				ConnectionManager.closeConecction();
			} 
		  	
		  	return archivoPDF;
		}
	}