Database opgave DDU
jeg har valgt opgave 2, og jeg laver den individuelt.

login side ide.png viser min grund ide, til hvordan userinterface skal være.
Burndownchart.xlsx viser mit burnddownchart
database og pde program lægger i SQLite_Login folderen


TASKBOARD/TODO: (done = færdigt, nope = ikke færdigt)
(tids estimat og mere præcise tasks i burndownchart.xlsx)

få data fra database - done 

ændre data i database - done

lav userinteface - done
	login / opret (login side ide.png)
	draw(skriv mail) ved klik på login, efter klik enter ->
	draw(skriv kode) ved klik enter check login -> ind til profil elr prøv igen

tager brugerinput - done
	detect hvilken knab der bliver klikket
		evt skriv java program til at skrive den lange liste -> void keyPressed(){if(key == 'x'){keyP = "x";}
	boolean login = true -> login blabla..
	boolean opret = true -> input navn, mail, osv...

ændre data/slet profil - done
	profil side, med knapper til at skifte data og slet profil

lav endu en tabel i SQLite - nope
	mere information - done

ISSUES:
	BACKSPACE fjerner alt: - nope
		fix så den kun fjerner sidste char