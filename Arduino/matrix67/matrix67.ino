
#include<SoftwareSerial.h>
SoftwareSerial bluetooth(0, 1);
boolean isEmpty = true;
int x=0, y = 0;
int pinX[8] = {2, 3, 19, 18, 13, 12}; //+
int pinY[8] = {11, 10, 9, 8, 7, 6, 5}; //-

void readXY() {
  x = readX();
  y = readY();
  if ((x > 1020) || (y > 1020)) {
    isEmpty = true;
  }
  else {
    isEmpty = false;
    x = 880 - x;
    if (x < 0) {
      x = 0;
    }
    if (x > 750) {
      x = 750;
    }
    y = 990 - y ;
    if (y < 0) {
      y = 0;
    }
    if (y > 960) {
      y = 960;
    }
  }
}

int readY() // возвращает координату Y на сенсорном экране
{
  int yr = 0;
  pinMode(A0, INPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, INPUT);
  pinMode(A3, OUTPUT);
  digitalWrite(A1, LOW);  // подать низкий уровень на A1
  digitalWrite(A3, HIGH); // подать высокий уровень на A3
  delay(5);
  yr = analogRead(0);     // сохранить координату X
  return yr;
}
int readX() // возвращает координату X на сенсорном экране
{
  int xr = 0;
  pinMode(A0, OUTPUT);    // A0
  pinMode(A1, INPUT);     // A1
  pinMode(A2, OUTPUT);    // A2
  pinMode(A3, INPUT);     // A3
  digitalWrite(14, LOW);  // подать низкий уровень на A0
  digitalWrite(16, HIGH); // подать высокий уровень на A2
  delay(5);
  xr = analogRead(3);     // сохранить координату Y
  return xr;
}
void delay2(unsigned long ms)
{
  uint16_t start = (uint16_t)micros();

  while (ms > 0) {
    if (((uint16_t)micros() - start) >= 1000) {
      ms--;
      start += 1000;
    }
  }
}

void setup() {
  bluetooth.begin(9600);
  for ( int x = 0; x < 6; x++) {
    pinMode (pinX[x], OUTPUT);
    digitalWrite (pinX[x], HIGH);
  }

  for ( int y = 0; y < 7; y++) {
    pinMode (pinY[y], OUTPUT);
    digitalWrite (pinY[y], LOW);
  }

  for (int y = 0; y < 7; y++) {
    for ( int x = 0; x < 6; x++) {
      drawPoint(x, y, true);
      delay(100);
      drawPoint(x, y, false);
      delay(100);
    }
    delay(300);
  }
}

void drawPoint(int x, int y, bool power) {
  if ( power == true ) {
    if ((x >= 0) && (x < 6) && (y >= 0) && (y < 7)) {
      digitalWrite (pinX[x], LOW);
      digitalWrite (pinY[y], HIGH);
    }
  }
  else {
    if ((x >= 0) && (x < 6) && (y >= 0) && (y < 7)) {
      digitalWrite (pinX[x], HIGH);
      digitalWrite (pinY[y], LOW);
    }
  }
}

void loop() {
  readXY();
  if (isEmpty)
  {
    if (bluetooth.available() > 2) {
      //drawPoint(7, 7, true);
      //delay(1000);
      //drawPoint(7, 7, false);
      //delay(1000);

      byte xr = bluetooth.read();
      byte yr = bluetooth.read();
      byte tr = bluetooth.read();

      if (xr == 100) {
        delay(10 * tr);
      }
      else {
        drawPoint(xr, yr, true);
        delay(10 * tr);
        drawPoint(xr, yr, false);
        //delay(10*t);
      }
    }
  }
  else {
    long xp=0, yp=0, x1=0, y1=0;
    xp = x*5/750; 
    yp = y*6/960;
    
    x1 = x;
    y1 = y;
    x1 = x1*200.0/750.0;
    y1 = (960.0-y1)*250.0/960.0;
    
    drawPoint(xp, yp, true);
    
    String send = "p;"+ String(x1, DEC) + ";" + String(y1, DEC) + ";" + String(15, DEC)+";\r\n";
    bluetooth.write(send.c_str());
    bluetooth.flush();
    
    delay(150);
    drawPoint(xp, yp, false);
 
}
  //delay (200);

}

