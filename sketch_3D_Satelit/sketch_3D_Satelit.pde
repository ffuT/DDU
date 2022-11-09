PImage earth;
PShape globe;
float r=200;
float angle=1;
float x,y,z;

//satelite
float Slon,Slat,Salt;

JSONObject json;

//V5J7QG-UD5MRG-GXP2FS-4YAY
//https://api.n2yo.com/rest/v1/satellite/positions/25544/41.702/-76.014/0/2/&apiKey=V5J7QG-UD5MRG-GXP2FS-4YAY

void setup(){
  
  try{
    json = new JSONObject();
    json = loadJSONObject("https://api.n2yo.com/rest/v1/satellite/positions/25544/41.702/-76.014/0/2/&apiKey=V5J7QG-UD5MRG-GXP2FS-4YAY");
    JSONArray pos;
    pos = new JSONArray();
    pos = json.getJSONArray("positions");
    
    JSONObject pos1 = pos.getJSONObject(1);
    
    Slon = pos1.getFloat("satlongitude");
    Slat = pos1.getFloat("satlatitude");
    Salt = pos1.getFloat("sataltitude");

} catch(Exception e){
    println("Cant get JSON data -> https://api.n2yo.com/rest/v1/satellite/positions/25544/41.702/-76.014/0/2/&apiKey=V5J7QG-UD5MRG-GXP2FS-4YAY");
  }
  
  
  
  println(Slon + " " + Slat + " " + Salt);
  
  size(800,800,P3D);
  earth = loadImage("C:\\Users\\hille\\Desktop\\sketch_3D_Satelit\\data\\earth.jpg");
  noStroke();
  fill(10,10,200);
  globe = createShape(SPHERE,r);
  //jeg kan ikke få den til at vise texturen, pde crasher hver gang...
  //jeg har derfor tilføjet forksellige byer istedet
  //bare lad som om der er textures :)
  //globe.setTexture(earth);
}

void draw(){
  background(33);
  
  textSize(15);
  //satelit
  drawcity("SpaceStation",255,255,255,10,10);
  drawcity("Copenhagen",255,0,0,10,25);
  drawcity("Berlin",255,255,0,10,40);
  drawcity("Stockholm",0,255,0,10,55);
  drawcity("London",0,0,255,10,70);
  drawcity("Batman",0,255,255,10,85);
  drawcity("Rome",255,0,255,10,100);
  drawcity("New York",10,150,150,10,115);
  drawcity("Edmonton",200,50,50,10,130);    
  drawcity("South Africa",50,50,50,10,145);    
  drawcity("Rabat",50,50,200,10,160);    
  


  translate(400,400);
  rotateX(PI/2);
  rotateX(PI-radians(21));
  rotateZ(angle);
  angle +=0.005;
  
  lights();
  noStroke();
  shape(globe);
  
  drawSat();
 
  
  //københavn
  drawpos(12.568337,55.676098,255,0,0);
  //berlin
  drawpos(18.063240,59.334591,0,255,0);
  //stockholm
  drawpos(13.404954,52.520008,255,255,0);
  //london
  drawpos(-0.1275,51.50722,0,0,255);
  //Batman
  drawpos(41.1322,37.88738,0,255,255);
  //Rome
  drawpos(12.51133,41.89193,255,0,255);
  //New York
  drawpos(-74.00597,40.71427,10,150,150);
  //Edmonton
  drawpos( -113.46871,53.55014,200,50,50);
  //southafrica
  drawpos(22.937506,-30.559482,50,50,50);
  //Rabat
  drawpos(-6.83255,34.01325,50,50,200);
 
}

void drawcity(String city,int R,int G,int B,int x,int y){
  fill(R,G,B);
  circle(x,y,5);
  text(city,x+10,y+5);
}

void drawpos(float lon, float lat,int R,int G,int B){
  float theta = radians(lat)+PI/2;
  float phi = radians(lon)+PI;
  x = r * sin(theta)*cos(phi);
  y = r * sin(theta)*sin(phi);
  z = r * cos(theta);
  
  pushMatrix();
  translate(x,y,z);
  fill(R,G,B);
  sphere(2);
  popMatrix();
}

void drawSat(){
  //31,5 fordi det er JordenRadius/200 ≈ 31,5 aka målestoksforhold
  float Sr = r + Salt/31.5;
  float theta = radians(Slat)+PI/2;
  float phi = radians(Slon)+PI;
  x = Sr * sin(theta)*cos(phi);
  y = Sr * sin(theta)*sin(phi);
  z = Sr * cos(theta);
  
  pushMatrix();
  translate(x,y,z);
  fill(255);
  sphere(5);
  popMatrix();
  
}
