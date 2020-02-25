import smtplib
import ssl
import build_teams


#consts
OMRI_EMAIL = "omri162004@gmail.com"
OFIR_EMAIL = "ofirs2003@gmail.com"
ROY_EMAIL = "roy17.rs@gmail.com"
SERVER_EMAIL = "volleyballmotzkin@gmail.com"
MAIL_PASSWORD = "moshemoshe5"
MAIL_PORT = 465

def team_to_str(team):
    mes = ""
    team =build_teams.from_dict_to_sorted_list(team)
    for i in range(0,len(team)):
        mes += str(i+1) + "." + team[i][0] + "\n"
    return mes

def send_email(mail_server,email,teams):
    mes = "Hello sir the teams for today:\n" + team_to_str(teams[0]) +"\n"+ team_to_str(teams[1])
    msg = """From: Volleyball Motzkin<%s>
To: %s <%s>
Subject: %s\n
%s"""%(SERVER_EMAIL,email.split('@')[0],email,"Your Teams for today!","hi galant, how are you?")
    mail_server.sendmail("mosmospyt@gmail.com",email, msg)
    print("mail was sent to %s"%(email))


def manage_mail(team1,team2):
    context = ssl.create_default_context()

    mail_server = smtplib.SMTP_SSL("smtp.gmail.com", MAIL_PORT, context=context)
    mail_server.login(SERVER_EMAIL,MAIL_PASSWORD) #TODO: hide password
    send_email(mail_server,ROY_EMAIL,(team1,team2))

    mail_server.quit()


