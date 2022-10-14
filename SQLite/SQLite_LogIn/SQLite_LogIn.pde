import de.bezier.data.sql.*;
/*
  program to take data from SQLite
  And
  Login and change Data
  u cant logout tho
*/

SQLite db;

void setup(){
  size(400,400);
  db = new SQLite(this,"LOGIN_database.sqlite");
  if(db.connect()){
    println("connected");    
  }
}

//program data
int screennumbr = 5;
String mail;
String password;
boolean loggedin=false;
String update="";

//for adding account
String newMail;
String newPassword;
String newName;

//user data:
int UserID;
String User_Name="";
int User_Age;
String User_Gender;

//display what is typed
String ScreenText ="";
String keyP="y";

//draw userinterface
void draw(){
  //sets background color
  fill(33,33,33);
  rect(-1,-1,402,402);
  
  //chanhge color and size of text
  fill(255,255,255);
  textSize(32);
  
  //switches which screen to be shown a lil confusing i know, it is what it is
  switch(screennumbr){
    case 0:
    //login (mail)
      text(ScreenText,10,395);
      text("type ur mail: ",10,360);
        break;
    case 1:
    //login (password)
      text(ScreenText,10,395);
      text("type ur password: ",10,360);
      text(mail,10,330);
        break;
    case 3:
    //logged in aka account
      text(ScreenText,10,395);
      text("hello "+ User_Name,10,32);
      text("age: "+ User_Age,10,62);
      text("sex: "+ User_Gender,10,92);
      text("to delete account type delete",10,300);
      text("to update data, type",10,330);
      text("update and what to update",10,360);
      //other profile data
        break;
    case 5:
    //make ned account
      if(mailexist){
        text("this mail is already in use",10,330);
        text("type login to login",10,360);
      }
      if(!mailexist){
        text("type new to make an account",10,330);
        text("or type login to login",10,360);
      }
      
      text(ScreenText,10,390);
        break;
    //add user:
    case 6:
    //new mail
      text("type stop to cancel",10,330);
      text("type your mail",10,360);
      text(ScreenText,10,390);
        break;
    case 7:
    //new password
      text("type stop to cancel",10,330);
      text("type your Password",10,360);
      text(ScreenText,10,390);
        break;
    case 8:
    //new users name
      text("type stop to cancel",10,330);
      text("type your name",10,360);
      text(ScreenText,10,390);
        break;
    case 9:
    //account made login
      text("Account created,",10,330);
      text("type login to login",10,360);
      text(ScreenText,10,390);      
        break;
    case 10:
    //update profile data
      text(ScreenText,10,390);
      text("updating "+ update,10,330);
  }
}

//faster way is to not compare to each mail, but just query for this mail
//updates users mail if it exists
void updateMail(String in){
  mail = in;
  db.query("SELECT UserID, Email FROM LogIn;");
  while(db.next()){
    if(db.getString("Email").equals(mail)){
      screennumbr = 1;
      return;
    }
  }
    println("mail not found in system");
    //screennumbr 5 -> "register or login" screen
    mailexist=false;
    screennumbr = 5;
}

//confirm password -> porfile page
void login(String in){
  password = in;
  db.query("SELECT Password FROM login WHERE Email IS \""+ mail + "\";");
  if(db.getString("Password").equals(password)){
    db.query("SELECT * From Login WHERE Email IS \"" +mail+ "\";");
    
    //set profile values:
    UserID = db.getInt("UserID");
    User_Name = db.getString("User_Name");
    User_Age = db.getInt("Age");
    
    
    //db.getBoolean dosnt work so i use string
    String gender = db.getString("Gender");
    boolean Gender;    
    if(gender==null){
      User_Gender = "null";
    } else{
      if(gender.equals("true")){
        Gender = true;
      } else {Gender = false;}
        if(Gender){User_Gender = "male";} else {User_Gender = "female";} 
    }
    loggedin = true;
    
    println("logged in as " + User_Name);
    //switch to profile
    screennumbr = 3;
  }
}
//for displaying right screen
boolean mailexist=false;

//doesnt verify if mail is real
//checks if mail is already in use
void checkmail(String input){
  db.query("SELECT * From login WHERE Email IS \"" +input+ "\";");
  if(db.next()){
    println("mail in use");
    mailexist = true;
    screennumbr=5;
  } else {
    println("adding account");
    newMail = inputstr;
    screennumbr = 7;
  }
}

//new account
void addUser(){
  db.query("INSERT INTO login(User_Name,Email,Password) VALUES (\""+newName+"\",\""+newMail+"\",\""+newPassword+"\");");
}
void updateEmail(String input){
  db.query("UPDATE Login SET Email = \""+input+"\" WHERE UserID = \""+UserID+"\";");
}

void updateUserName(String input){
  db.query("UPDATE Login SET User_Name = \""+input+"\" WHERE UserID = \""+UserID+"\";");
}

void updatePassword(String input){
  db.query("UPDATE Login SET Password = \""+input+"\" WHERE UserID = \""+UserID+"\";");
}

void updateGender(String input){
  String sex = input.toLowerCase();
  if(sex.equals("male")||sex.equals("man")){
    db.query("UPDATE Login SET Gender = \"true\" WHERE User_Name = \""+User_Name+"\";");
  } else {
    db.query("UPDATE Login SET Gender = \"false\" WHERE User_Name = \""+User_Name+"\";");
  }
}

//updates age based on inpit
void updateAge(String input){
  int age;
  try{
  age = Integer.parseInt(input);
  }catch(Exception e){
    return;
  }
  db.query("UPDATE Login SET Age = \""+ age + "\" WHERE User_Name = \""+User_Name+"\";");
}

//decide what to update based on input
void Update(int n){
  switch(n){
    case 1:
      update = "age";
      break;
    case 2:
      update = "Gender";
      break;
    case 3:
      update = "Email";
      break;  
    case 4:
      update = "User_Name";
      break;
    case 5:
      update = "Password";
      break;  
  }
}

//updates all profile data after information update
void updateData(){
  db.query("SELECT * FROM Login Where UserID =\"" + UserID + "\" ;)");
  User_Name = db.getString("User_Name");
  User_Age = db.getInt("Age");
  //databse cant returnd boolean so it has to be string...
  String gender = db.getString("Gender");
  boolean Gender;
    if(gender==null){
      User_Gender = "null";
    } else{
      if(gender.equals("true")){
        Gender = true;
      } else {Gender = false;}
        if(Gender){User_Gender = "male";} else {User_Gender = "female";} 
    }
  loggedin = true;
}


int Ecount;
String inputstr = "";
//update program/program loop
void keyPressed(){
    
     //this should have been a switch case aswell
  if(key == ENTER){
    //type quit or exit to close the program instantly
    //could have made one temp and put it outside function
    String tempquit = inputstr.toLowerCase();
    if(tempquit.equals("quit") || tempquit.equals("exit")){
      println("programm closed");
      System.exit(0);
  }
  
  //if on update what to value them
  if(screennumbr==10){
    switch(update){
      case "age":
        updateAge(inputstr);
        break;
      case "Gender":
        updateGender(inputstr);
        break;
      case "Email":
        updateEmail(inputstr);
        break;
      case "User_Name":
        updateUserName(inputstr);
        break;
      case "Password":
        updatePassword(inputstr);
        break;
      default:
         break;
  }
    update = "";
    updateData();
    screennumbr = 3;
    inputstr="";
    //else if on profile do this
  } else if(screennumbr== 3){
    String tempupdate = inputstr.toLowerCase();
    
    if(tempupdate.equals("delete")){
      db.query("DELETE FROM Login WHERE UserID = "+ UserID+";");
      loggedin= false;
      screennumbr = 5;
      
      inputstr="";
      ScreenText="";
      return;
    }
    //waht to update
    if(tempupdate.contains("update")){
      if(tempupdate.contains("age")){
        Update(1); 
      } else if(tempupdate.contains("gender")|| tempupdate.contains("sex")){
        Update(2);
      } else if(tempupdate.contains("email")|| tempupdate.contains("mail")){
        Update(3);
      }else if(tempupdate.contains("name")|| tempupdate.contains("username")){
        Update(4);
      }else if(tempupdate.contains("password")){
        Update(5);
      } 
      if(!update.equals("")){
      screennumbr = 10;
      }
      inputstr="";
    }   
    inputstr = "";
  }
    
    
    if(inputstr.equals("stop")){
      screennumbr=5;
      inputstr="";
    }
    
    //adds new name
    if(screennumbr == 8){
      newName = inputstr;
      screennumbr = 9;
      inputstr="";
      addUser();
  }
    //adds new passowrd
    if(screennumbr == 7){
      newPassword = inputstr;
      screennumbr = 8;
      inputstr= "";
    }
    //adds new email
    if(screennumbr == 6){
       checkmail(inputstr);
       inputstr="";
       ScreenText="";
       return;
    }
    
   //login after password
   if(screennumbr == 1){
     login(inputstr);
     inputstr = "";
     //Ecount aka enter count if u fail login 3 times back to start screen
     Ecount++;
       if(Ecount%3==0 && !loggedin){
         screennumbr = 5;
       }
     }
    
    //mail update 
    if(screennumbr == 0){
      //TODO - save mail switch to password
      updateMail(inputstr);
      //next screen change
      inputstr = "";
    }
    //what to do
    if(screennumbr == 5 || screennumbr ==9){
       String templogin = inputstr.toLowerCase();
       if(templogin.equals("login")){
       screennumbr =0;
       } else if(templogin.equals("new")){
       screennumbr =6;
       }
       inputstr = "";
     }
  }
  //deletes ur inputstr.. should only delete last cahr
  if(key == BACKSPACE){
    inputstr = "";
  }
  
  //key pressed
  keyP ="";
  //used a simple javaprogram to give this code, some i had to do manually, 
  //idk why processing cant just do keyP = key.value or some like that
  if(key == '0'){keyP = "0";}
  if(key == '1'){keyP = "1";}
  if(key == '2'){keyP = "2";}
  if(key == '3'){keyP = "3";}
  if(key == '4'){keyP = "4";}
  if(key == '5'){keyP = "5";}
  if(key == '6'){keyP = "6";}
  if(key == '7'){keyP = "7";}
  if(key == '8'){keyP = "8";}
  if(key == '9'){keyP = "9";}
  if(key == 32){keyP = " ";}
  if(key == 'a'){keyP = "a";}
  if(key == 'b'){keyP = "b";}
  if(key == 'c'){keyP = "c";}
  if(key == 'd'){keyP = "d";}
  if(key == 'e'){keyP = "e";}
  if(key == 'f'){keyP = "f";}
  if(key == 'g'){keyP = "g";}
  if(key == 'h'){keyP = "h";}
  if(key == 'i'){keyP = "i";}
  if(key == 'j'){keyP = "j";}
  if(key == 'k'){keyP = "k";}
  if(key == 'l'){keyP = "l";}
  if(key == 'm'){keyP = "m";}
  if(key == 'n'){keyP = "n";}
  if(key == 'o'){keyP = "o";}
  if(key == 'p'){keyP = "p";}
  if(key == 'q'){keyP = "q";}
  if(key == 'r'){keyP = "r";}
  if(key == 's'){keyP = "s";}
  if(key == 't'){keyP = "t";}
  if(key == 'u'){keyP = "u";}
  if(key == 'v'){keyP = "v";}
  if(key == 'w'){keyP = "w";}
  if(key == 'x'){keyP = "x";}
  if(key == 'y'){keyP = "y";}
  if(key == 'z'){keyP = "z";}
  if(key == '@'){keyP = "@";}
  if(key == 'A'){keyP = "A";}
  if(key == 'B'){keyP = "B";}
  if(key == 'C'){keyP = "C";}
  if(key == 'D'){keyP = "D";}
  if(key == 'E'){keyP = "E";}
  if(key == 'F'){keyP = "F";}
  if(key == 'G'){keyP = "G";}
  if(key == 'H'){keyP = "H";}
  if(key == 'I'){keyP = "I";}
  if(key == 'J'){keyP = "J";}
  if(key == 'K'){keyP = "K";}
  if(key == 'L'){keyP = "L";}
  if(key == 'M'){keyP = "M";}
  if(key == 'N'){keyP = "N";}
  if(key == 'O'){keyP = "O";}
  if(key == 'P'){keyP = "P";}
  if(key == 'Q'){keyP = "Q";}
  if(key == 'R'){keyP = "R";}
  if(key == 'S'){keyP = "S";}
  if(key == 'T'){keyP = "T";}
  if(key == 'U'){keyP = "U";}
  if(key == 'V'){keyP = "V";}
  if(key == 'W'){keyP = "W";}
  if(key == 'X'){keyP = "X";}
  if(key == 'Y'){keyP = "Y";}
  if(key == 'Z'){keyP = "Z";}
  if(key == '.'){keyP = ".";}
  if(key == 'æ'){keyP = "æ";}
  if(key == 'Æ'){keyP = "Æ";}
  if(key == 'ø'){keyP = "ø";}
  if(key == 'Ø'){keyP = "Ø";}
  if(key == 'å'){keyP = "å";}
  if(key == 'Å'){keyP = "Å";}

  //inputs as string
  inputstr += keyP;
  //inpust displayed on screen
  ScreenText = inputstr;
}
