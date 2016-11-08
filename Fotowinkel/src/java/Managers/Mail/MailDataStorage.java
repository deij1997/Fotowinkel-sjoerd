/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers.Mail;

import javax.mail.PasswordAuthentication;

/**
 *
 * @author Rowan
 */
public class MailDataStorage
{
    private final static String SENDER_PASSWORD = "Em72@Gmai111";
    public final static String SENDER_MAIL = "s21mplumbum@gmail.com";
    public final static String SENDER_NAME = "Fotowinkel Sjoerd";

    public final static String EMAIL_BODY = "<div style=\"background:#292c2f; width:100%; height:100%; \">\n"
                                            + "<div id=\"headerwrapper\" style=\"width:100%; max-width:960px; margin:auto;\">\n"
                                            + "<div id=\"header\" style=\"margin:25px;\">\n"
                                            + "<!-- Header logo-->\n"
                                            + "		<h1 style=\" font: normal 28px Cookie, Arial, Helvetica, sans-serif; line-height: 40px; margin: 0; color:white\"><a style=\"color: #ffffff; text-decoration: none;\" href=\"#\">Fotowinkel<span style=\"color: #5383d3;\">Sjoerd</span></a></h1>\n"
                                            + "\n"
                                            + "</div>\n"
                                            + "</div>\n"
                                            + "<!-- Content-->\n"
                                            + "<div id=\"contentwrapperwrapper\" style=\"background:white;width:100%; \">\n"
                                            + "<div id=\"contentwrapper\" style=\"max-width:960px; margin:auto; \">\n"
                                            + "<div id=\"content\" style=\"padding:25px;\">\n"
                                            + "%s\n"
                                            + "</div>\n"
                                            + "</div>\n"
                                            + "</div>\n"
                                            + "<!-- Footer-->\n"
                                            + "<div id=\"footerwrapper\" style=\"width:100%; font: bold 16px sans-serif; max-width:960px; margin:auto; \">\n"
                                            + "<div id=\"footer\" style=\"margin:25px; \">\n"
                                            + "			<!--left footer-->\n"
                                            + "			<div style=\"display: inline-block; vertical-align: top; width: 49%; min-width:250px;min-height:200px;\" >\n"
                                            + "				<!-- Adress-->\n"
                                            + "				<div >\n"
                                            + "					<i style=\"background-color:  #33383b; color: #ffffff; font-size: 25px; width: 38px; height: 38px; border-radius: 50%; text-align: center; line-height: 42px; vertical-align: middle;\" ></i>\n"
                                            + "					<p style=\"display: inline-block; color: #ffffff; vertical-align: middle; margin:0;\"><span style=\"display:block; font-weight: normal; font-size:14px; line-height:2;\">Rachelsmolen 1</span> Eindhoven, The Netherlands</p>\n"
                                            + "				</div>\n"
                                            + "				<!-- telefoonnummer-->\n"
                                            + "				<div>\n"
                                            + "					<i style=\"background-color:  #33383b; color: #ffffff; font-size: 25px; width: 38px; height: 38px; border-radius: 50%; text-align: center; line-height: 42px; vertical-align: middle;\"></i>\n"
                                            + "					<p style=\"display: inline-block; color: #ffffff; vertical-align: middle; margin:0;\">+31 0088 508 0000</p>\n"
                                            + "				</div>\n"
                                            + "				<!-- Mail-->\n"
                                            + "\n"
                                            + "				<div>\n"
                                            + "					<i style=\"background-color:  #33383b; color: #ffffff; font-size: 25px; width: 38px; height: 38px; border-radius: 50%; text-align: center; line-height: 42px;  vertical-align: middle; font-size: 17px; line-height: 38px;\" ></i>\n"
                                            + "					<p style=\"display: inline-block; color: #ffffff; vertical-align: middle; margin:0;\"><a style=\"color:  #5383d3; text-decoration: none;;\" href=\"mailto:support@company.com\">tu.hoang@student.fontys.nl</a></p>\n"
                                            + "				</div>\n"
                                            + "			</div>\n"
                                            + "			<!--right footer-->\n"
                                            + "\n"
                                            + "			<div style=\"display: inline-block; vertical-align: top; width: 49%; width:250px; min-height:200px; float:right\" >\n"
                                            + "\n"
                                            + "				<p style=\"line-height: 20px; color:  #92999f; font-size: 13px; font-weight: normal; margin: 0;padding-left:30px;\" >\n"
                                            + "					<span style=\"display: block; color:  #ffffff; font-size: 14px; font-weight: bold; margin-bottom: 20px;\">About the company</span>\n"
                                            + "					Fotowinkel Sjoerd is build by 5 of the best developers of the world. h4r4mb3 4l1f3\n"
                                            + "				</p>\n"
                                            + "\n"
                                            + "			</div>\n"
                                            + "</div>\n"
                                            + "\n"
                                            + "</div>\n"
                                            + "<style>\n"
                                            + "html, body{\n"
                                            + " padding: 0px;\n"
                                            + " margin: 0px;\n"
                                            + "}\n"
                                            + "</style>";

    public static PasswordAuthentication getAuth()
    {
        return new PasswordAuthentication(SENDER_MAIL, SENDER_PASSWORD);
    }
}
