package integrify.inventory.infrastructure.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(EmailServiceEnum emailServiceEnum) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(new InternetAddress("lamngo606@gmail.com"));
        helper.setTo("lam.ngo@integrify.io");
        if(emailServiceEnum.equals(EmailServiceEnum.LOWSTOCK)){
            helper.setSubject("Product Low Stock Notification");

            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Low Stock Notification</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            color: #333;\n" +
                    "        }\n" +
                    "        p {\n" +
                    "            color: #555;\n" +
                    "        }\n" +
                    "        .low-stock {\n" +
                    "            background-color: #f8d7da;\n" +
                    "            padding: 10px;\n" +
                    "            border-radius: 5px;\n" +
                    "            color: #721c24;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>Product Low Stock Notification</h1>\n" +
                    "    <p>Dear Customer,</p>\n" +
                    "    <p>We would like to inform you that the quantity of a product you ordered is running low.</p>\n" +
                    "    <p>Product: <strong>Product Name</strong></p>\n" +
                    "    <p>Current Stock: <strong>5</strong></p>\n" +
                    "    <p>Please consider placing a new order soon to ensure availability.</p>\n" +
                    "    <p>If you have any questions or need further assistance, feel free to contact us.</p>\n" +
                    "    <p>Thank you for your continued support!</p>\n" +
                    "    <p>Sincerely,</p>\n" +
                    "    <p>GentStyle</p>\n" +
                    "\n" +
                    "    <!-- Low Stock Message -->\n" +
                    "    <div class=\"low-stock\">\n" +
                    "        <p><strong>Low Stock Alert:</strong> Hurry, only a few items left!</p>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";
            
            helper.setText(htmlContent, true);
        } else if (emailServiceEnum.equals(EmailServiceEnum.UPDATEORDERSTATUS)) {
            helper.setSubject("Order Shipped");

            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Order Shipped</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            color: #333;\n" +
                    "        }\n" +
                    "        p {\n" +
                    "            color: #555;\n" +
                    "        }\n" +
                    "        .shipped {\n" +
                    "            background-color: #d4edda;\n" +
                    "            padding: 10px;\n" +
                    "            border-radius: 5px;\n" +
                    "            color: #155724;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>Order Shipped</h1>\n" +
                    "    <p>Dear Customer,</p>\n" +
                    "    <p>We are pleased to inform you that your order has been shipped.</p>\n" +
                    "    <p>Order Details:</p>\n" +
                    "    <ul>\n" +
                    "        <li>Order ID: <strong>#123456</strong></li>\n" +
                    "        <li>Shipping Method: <strong>Express Shipping</strong></li>\n" +
                    "        <li>Tracking Number: <strong>ABC123XYZ</strong></li>\n" +
                    "    </ul>\n" +
                    "    <p>You can track your shipment using the provided tracking number.</p>\n" +
                    "    <p>If you have any questions or need further assistance, feel free to contact us.</p>\n" +
                    "    <p>Thank you for shopping with us!</p>\n" +
                    "    <p>Sincerely,</p>\n" +
                    "    <p>Inventory</p>\n" +
                    "\n" +
                    "    <!-- Shipped Order Message -->\n" +
                    "    <div class=\"shipped\">\n" +
                    "        <p><strong>Shipment Update:</strong> Your order has been shipped.</p>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";

            helper.setText(htmlContent, true);
        }


        mailSender.send(message);
    }
}