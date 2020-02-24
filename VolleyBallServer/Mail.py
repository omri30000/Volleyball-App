import smtplib
import ssl



#consts

OMRI_EMAIL = "omri162004@gmail.com"
OFIR_EMAIL = "omri162004@gmail.com"
SERVER_EMAIL = "mosmospyt@gmail.com"
MAIL_PORT = 465

def team_to_str(team):
    mes = ""
    for i in range(0,len(team)):
        mes += (str(i) +'.' + team[i][0])
    return mes

def send_email(mail_server,email,teams):
    mes = "Hello sir the teams for today:\n" + team_to_str(teams[0]) +"\n"+ team_to_str(teams[1])
    msg = """From: Prices Tracker <%s>
To: To Person <%s>
Subject: %s\n
%s."""%("mosmospyt@gmail.com",email,mes)
    mail_server.sendmail("mosmospyt@gmail.com",email, msg)
    print("mail was sent to %s"%(email))


def manage_mail(team1,team2):
    context = ssl.create_default_context()

    mail_server = smtplib.SMTP_SSL("smtp.gmail.com", MAIL_PORT, context=context)
    mail_server.login(SERVER_EMAIL,"Asd123874") #TODO: hide password
    send_email(mail_server,OMRI_EMAIL,(team1,team2))
    send_email(mail_server,OFIR_EMAIL,(team1,team2))

    mail_server.quit(mail_server)


