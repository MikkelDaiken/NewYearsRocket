/* autogenerated by Processing revision 1283 on 2023-01-04 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import processing.sound.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class raket3 extends PApplet {


SoundFile start;
SoundFile raket1;
SoundFile raket2;


//deklaration af raket batterier
Raket1 r1;
Raket2 r2;
Raket3 r3;

 public void setup() {
  /* size commented out by preprocessor */;
  start = new SoundFile(this, "start.mp3");
  raket1 = new SoundFile(this, "multiRaket.mp3");
  raket2 = new SoundFile(this, "raket1.mp3");

  // hvis ikke er height ikke initialiseret
  r1 = new Raket1();
  r2 = new Raket2();
  r3 = new Raket3();
}



 public void draw() {
  background(205);

    
  r1.flyvRaket();
  r2.flyvRaket();
  r3.flyvRaket();
}
// lyde: https://www.epidemicsound.com/sound-effects/fireworks/ //<>// //<>// //<>// //<>// //<>// //<>//
class Raket {
  PVector location = new PVector(0, height);
  PVector velocity;

  int dia=5;

  // farver RGB og opauqe værdi alfa
  int r=PApplet.parseInt(random(256));
  int g=PApplet.parseInt(random(256));
  int b=PApplet.parseInt(random(256));
  int alfa=255;

  int gram = PApplet.parseInt(random(100, 400));
  int lunte= PApplet.parseInt(random(1, 10));
  float angle=PApplet.parseInt(random(1, 7));
  boolean soundPayed = false;
  boolean lift= true; // bruges til bang lyd


  //construktor
  Raket() {
    velocity = new PVector(angle, -3);
  }



   public void visRaket() {
    fill(0);
    //noStroke();
    circle(location.x, location.y, dia);
    stroke(2);
  }

 //<>//


   public void playFireSound() {
    if (location.y==height) {
      start.play();
    }
  }

   public void playExplodingSound() {
    if (!soundPayed && !lift) {
      raket2.play();
      soundPayed = true;
    }
  }

   public void moveRaket() {

    location.add(velocity);
    if (location.y < gram) { // hvis  jeg har nået toppen og ikke er færdig
      velocity.x=1;
      velocity.y=-0.1f;
      lift=false;
    }
  }


  //er raketten færdig??
   public boolean done() {
    if (location.y<gram ) {
      return true;
    }
    return false;
  }


   public void display() {
    println(location);
    println(velocity);

    println( dia);

    // farver RGB og opauqe værdi alfa
    println( r);
    println( g);
    println( b);
    println(alfa);

    println(gram);
    println(lunte);
    println(angle);
    println(soundPayed);
    println(lift);
  }
}
/*************************************/

class Raket1 extends Raket {
   public void eksploderRaket() {
    noStroke();
    if (dia < 500) {
      fill(r, g, b, alfa);
      dia++;
      circle(location.x, location.y, dia);
      // gør eksplosion gennemsigtig
      alfa--;
    }
    stroke(2);
  }

   public void flyvRaket() {
    playFireSound();
    moveRaket();
    if (done()) {
      eksploderRaket();
    } else {
      visRaket();
    }
    playExplodingSound();
  }
}
/*************************************/

class Raket2 extends Raket {

   public void eksploderRaket() {

    pushMatrix();
    translate(location.x, location.y);
    stroke(r, b, g, alfa);

    for (int i=0; i<360; i++) {
      fill(r, g, b, alfa);
      rect(0, 0, 0, 1*i);
      rotate(1);
    }
    popMatrix();
    // gør eksplosion gennemsigtig
    alfa--;
  }
   public void flyvRaket() {
    playFireSound();
    moveRaket();
    if (done()) {
      eksploderRaket();
    } else {
      visRaket();
    }
    playExplodingSound();
  }
}

/*************************************/

class Raket3 extends Raket {



   public void eksploderRaket() {

    pushMatrix();
    translate(location.x, location.y);
    stroke(r, b, g, alfa);

    for (int i=0; i<100; i++) {
      fill(r, g, b, alfa);
      rect(0, 0, 0, 1*i);
      rotate(1);
    }
    popMatrix();
    // gør eksplosion gennemsigtig
    alfa--;
  }

   public void flyvRaket() {
    playFireSound();
    moveRaket();
    if (done()) {
      eksploderRaket();
    } else {
      visRaket();
    }
    playExplodingSound();
  }
}


  public void settings() { size(1500, 800); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "raket3" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}