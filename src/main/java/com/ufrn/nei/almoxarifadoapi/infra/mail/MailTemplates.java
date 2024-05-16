package com.ufrn.nei.almoxarifadoapi.infra.mail;

import com.ufrn.nei.almoxarifadoapi.utils.RefactorDate;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;

import java.sql.Timestamp;

@Component
public class MailTemplates {
  @Autowired
  private SimpleMailMessage simpleMailMessage;

  @Autowired
  private MimeMessage mimeMessage;

  public SimpleMailMessage buildMailMessageUserCreated(String userEmail, String userName) {
    String subject = "Sua conta foi criada com sucesso!";
    String text = String.format(
        """
            Olá %s,

            Seja bem-vindo ao nosso sistema de solicitação de itens do almoxarifado! Estamos muito felizes em tê-lo conosco.

            Com este sistema, você terá acesso a uma variedade de itens disponíveis em nosso almoxarifado, tornando mais fácil e conveniente solicitar o que você precisa para suas atividades.

            Aqui estão algumas coisas que você pode fazer com a sua nova conta:
            - Explorar nosso catálogo de itens disponíveis.
            - Criar solicitações para itens que você precisa.
            - Acompanhar o status das suas solicitações.
            - Receber notificações sobre o progresso das suas solicitações.

            Estamos sempre trabalhando para melhorar nossa plataforma e proporcionar uma experiência mais eficiente para todos os nossos usuários.

            Se precisar de ajuda ou tiver alguma dúvida, não hesite em entrar em contato conosco. Estamos aqui para ajudar!

            Mais uma vez, obrigado por se juntar a nós. Esperamos que você aproveite ao máximo o nosso sistema de solicitação de itens do almoxarifado.

            Atenciosamente,
            Equipe do NEI""",
        userName);

    simpleMailMessage.setTo(userEmail);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(text);

    return simpleMailMessage;
  }

  public SimpleMailMessage buildMailMessageRequestCreated(String userEmail, String userName,
      String itemName, Timestamp date, Long itemQuantity) {
    String formatDate = RefactorDate.refactorTimestamp(date);

    String subject = "Sua Solicitação foi criada com sucesso!";
    String text = String.format("""
        Olá %s,

        Sua solicitação do item '%s' foi realizada com sucesso!

        Detalhes:
        - Item: %s
        - Quantidade: %d
        - Hora da Solicitação: %s

        Obrigado por utilizar nosso sistema.""",
        userName, itemName, itemName, itemQuantity, formatDate);

    simpleMailMessage.setTo(userEmail);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(text);

    return simpleMailMessage;
  }

  public SimpleMailMessage buildMailMessageRequestAccepted(String userEmail, String userName,
      String itemName, Timestamp date, Long itemQuantity) {
    String formatDate = RefactorDate.refactorTimestamp(date);

    String subject = "Sua solicitação foi aceita!";
    String text = String.format("""
        Olá %s,

        Sua solicitação do item '%s' foi aceita!

        Detalhes:
        - Item: %s
        - Quantidade: %d
        - Hora da Aceitação: %s

        Obrigado por utilizar nosso sistema.""",
        userName, itemName, itemName, itemQuantity, formatDate);

    simpleMailMessage.setTo(userEmail);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(text);

    return simpleMailMessage;
  }

  public SimpleMailMessage buildMailMessageRequestDenied(String userEmail, String userName,
      String itemName, Timestamp date, Long itemQuantity) {
    String formatDate = RefactorDate.refactorTimestamp(date);

    String subject = "Sua solicitação foi recusada.";
    String text = String.format("""
        Olá %s,

        Sua solicitação para o item '%s' foi recusada.

        Detalhes:
        - Item: %s
        - Quantidade: %d
        - Hora da Recusa: %s

        Por favor, entre em contato conosco para mais informações.

        Atenciosamente,
        Equipe do Almoxarifado""",
        userName, itemName, itemName, itemQuantity, formatDate);

    simpleMailMessage.setTo(userEmail);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(text);

    return simpleMailMessage;
  }

  public SimpleMailMessage buildMailMessageRequestCanceled(String userEmail, String userName,
      String itemName, Timestamp date, Long itemQuantity) {
    String formatDate = RefactorDate.refactorTimestamp(date);

    String subject = "Confirmação de cancelamento.";
    String text = String.format("""
        Olá %s,

        Sua solicitação para o item '%s' foi cancelada com sucesso.

        Detalhes:
        - Item: %s
        - Quantidade: %d
        - Hora do Cancelamento: %s

        Equipe do Almoxarifado.""",
        userName, itemName, itemName, itemQuantity, formatDate);

    simpleMailMessage.setTo(userEmail);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(text);

    return simpleMailMessage;
  }

  public MimeMessage buildMailMessageForgotPassword(String userEmail, String token)
      throws MessagingException {
    String subject = "Redefinição de senha no NEI Almoxarifado";

    mimeMessage.setRecipients(Message.RecipientType.TO, userEmail);
    mimeMessage.setSubject(subject);
    mimeMessage.setContent(
        "<h3> Código de redefinição de senha </h3> <br> <span> Seu código para a redefinição de senha é <strong>"
            + token + "</strong>. Se você não fez essa solicitação, pode ignorar esse e-mail com segurança.",
        "text/html; charset=UTF-8");

    return mimeMessage;
  }
}
