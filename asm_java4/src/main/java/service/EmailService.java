package service;

import entily.User;
import jakarta.servlet.ServletContext;

public interface EmailService {
void sendEmail(ServletContext context,User recipient, String type);
}
