/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.Properties; 
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class sendMail
{
    public static void sendMail(String recepient , String mdp) throws Exception {
         String myAccountEmail = "projetjava2021@gmail.com";
       
        String password = "azerty2021";
        System.out.println("Preparing to send email");
         System.out.println("hello2");
        Properties properties = new Properties();
//SMTP GMAIL CONFIG
    Properties props = new Properties();  
    props.setProperty("mail.transport.protocol", "smtp");     
    props.setProperty("mail.host", "smtp.gmail.com");  
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.port", "465");  
    props.put("mail.debug", "true");  
    props.put("mail.smtp.socketFactory.port", "465");  
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
    props.put("mail.smtp.socketFactory.fallback", "false");  
//Connection addresse mail de l'application
    Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {
       protected PasswordAuthentication getPasswordAuthentication() {  
       return new PasswordAuthentication(myAccountEmail,password);  
   }  
   });  
   
        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient,mdp);
        System.out.println("hello2");
        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String password) {
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Recuperation de votre mot de passe");
            String htmlCode = "<h1> Bonjour </h1> <br/> <h2><b>Votre mot de passe est :"+password+" </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
