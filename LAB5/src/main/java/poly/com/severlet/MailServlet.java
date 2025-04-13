package poly.com.severlet;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class MailServlet
 */
@MultipartConfig
@WebServlet("/mail")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.protocols", "TLSv1.2");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", 587);
            p.put("mail.debug", "true");  
            Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("doantan868@gmail.com", "wbdq gmie sgfj dqcs");
                }
            });
            javax.mail.Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress("doantan868@gmail.com"));
            String email = request.getParameter("to");
            msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email));
            String subject = request.getParameter("subject");
            msg.setSubject(subject);
            String content = request.getParameter("body");
            
            MimeBodyPart contenpart = new MimeBodyPart();
            MimeBodyPart filepart = new MimeBodyPart();
            /// goi file dinh kem
            File dir = new File(request.getServletContext().getRealPath("/files"));
			if(!dir.exists()) {
				dir.mkdir();
				//System.out.print(request.getServletContext() + "anhtu");
			}
			/// ghi len server
			Part doc = request.getPart("photo_file");
    		File docFile = new File(dir, doc.getSubmittedFileName());
    		doc.write(docFile.getAbsolutePath());
			
    		/// lay duong dan file de goi email
          	Part photo = request.getPart("photo_file");
    		File photoFile = new File(dir, photo.getSubmittedFileName());
    		
    		
    		
    		
         	FileDataSource fds=new FileDataSource(photoFile);
    		filepart.setDataHandler(new DataHandler(fds));
    		filepart.setFileName(dir.getName());
         	///
            contenpart.setContent(content, "text/html; charset=utf-8");
           
            MimeMultipart multipart = new MimeMultipart();
            
            multipart.addBodyPart(contenpart);
            multipart.addBodyPart(filepart);
           
    		msg.setContent(multipart);
            Transport.send(msg);
	        request.setAttribute("message", "Send email success!");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("message", "Send email fail!");
		}
		request.getRequestDispatcher("/views/mail.jsp").forward(request, response);
	}

}
