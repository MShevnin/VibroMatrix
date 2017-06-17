#include<SoftwareSerial.h>
#include <Wire.h>  
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE); 
#define DATA_PIN 13
#define LATCH_PIN 12
#define CLOCK_PIN 11
SoftwareSerial bluetooth(9, 10);

byte data[4] = {0, 0, 0, 0};

byte number0[15][2] = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {2, 4}, {1, 4}, {0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}};
byte number1[8][2] = {{0, 2}, {1, 1}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 4}};
byte number2[15][2] = {{0, 0}, {1, 0},{2, 0},{3, 0},{3, 1},{3, 2},{2, 2},{1, 2},{0, 2},{0, 3},{0, 4},{1, 4},{2, 4},{3, 4},{3, 4}};
byte number3[19][2] = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 1}, {3, 2}, {2, 2}, {1, 2}, {0, 2}, {0, 2}, {1, 2}, {2, 2}, {3, 2}, {3, 3}, {3, 4}, {2, 4}, {1, 4}, {0, 4}, {0, 4}};
byte number4[14][2] = {{0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}, {3, 2}, {3, 1}, {3, 0}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 4} };
byte number5[17][2] = {{0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}, {3,2 }, {3, 3}, {3, 4}, {2, 4}, {1, 4}, {0, 4}, {0, 4}, {0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 0}};
byte number6[17][2] = {{3, 0}, {2, 0}, {1, 0}, {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 4}, {2, 4}, {3, 4}, {3, 3}, {3, 2}, {2, 2}, {1, 2}, {0, 2}, {0, 2}};
byte number7[12][2] = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 4},  {1, 2}, {2, 2}, {3, 2}};
byte number8[21][2] = {{3, 0}, {2, 0}, {1, 0}, {0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}, {2, 3}, {3, 3}, {3, 4}, {2, 4}, {1, 4}, {0, 4}, {0, 3}, {0, 2}, {1, 2}, {2, 2}, {3, 2}, {3, 1}, {3, 0}};
byte number9[16][2] = {{3, 2}, {2, 2}, {1, 2}, {0, 2}, {0, 1}, {0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {2, 4}, {1, 4}, {0, 4}};

const byte PointMode = 0;
const byte SledMode = 1;
const byte FullSymbolMode = 2;
const int analog = A0;
byte mode = SledMode; 
int sensorValue = 0;        
int sc = 0;
int sc1=0;
void setup() {
  bluetooth.begin(9600);
  pinMode (DATA_PIN, OUTPUT);
  pinMode (LATCH_PIN, OUTPUT);
  pinMode (CLOCK_PIN, OUTPUT);
  Serial.begin(9600);
  lcd.begin(16,2); 
  lcd.home();
  Clear();
  
}

void Clear()
{
  digitalWrite (LATCH_PIN, LOW);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, 0);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, 0);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, 0);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, 0);
  digitalWrite(LATCH_PIN, HIGH);
  
  ClearData();
}

void ClearData()
{
  for(int i = 0; i < 4; i++)
    data[i] = 0;
}

void PrintSled(byte n[][2], byte count)
{
  for(int i = 0; i < count; i++){
    if(i > 0)
      AddPointXY(3-n[i-1][0], n[i-1][1]);
    AddPointXY(3-n[i][0], n[i][1]);
    delay(50);
    
    PrintXY(3-n[i][0], n[i][1]);
    delay(100);
    
    ClearData();
  }  
  Clear();
}

void PrintFull(byte n[][2], byte count)
{
  for(int i = 0; i < count; i++){
    AddPointXY(3-n[i][0], n[i][1]);
    delay(10);    
  } 
  delay(3000); 
  Clear();
}

void PrintN0()
{
  for(int i = 0; i < 15; i++){
    PrintXY(3-number0[i][0], number0[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN1()
{
  for(int i = 0; i < 8; i++){
    PrintXY(3-number1[i][0], number1[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN2()
{
  for(int i = 0; i < 15; i++){
    PrintXY(3-number2[i][0], number2[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN3()
{
  for(int i = 0; i < 19; i++){
    PrintXY(3-number3[i][0], number3[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN4()
{
  for(int i = 0; i < 14; i++){
    PrintXY(3-number4[i][0], number4[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN5()
{
  for(int i = 0; i < 17; i++){
    PrintXY(3-number5[i][0], number5[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN6()
{
  for(int i = 0; i < 17; i++){
    PrintXY(3-number6[i][0], number6[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN7()
{
  for(int i = 0; i < 12; i++){
    PrintXY(3-number7[i][0], number7[i][1]);
    delay(sc);
  }  
  Clear();
}

void PrintN8()
{
  for(int i = 0; i < 21; i++){
    PrintXY(3-number8[i][0], number8[i][1]);
    delay(sc);
  }  
  Clear();
}
void PrintN9()
{
  for(int i = 0; i < 16; i++){
    PrintXY(3-number9[i][0], number9[i][1]);
    delay(sc);
  }  
  Clear();
}
void scor()
{
  sensorValue = analogRead(analog);
  sc1 = map(sensorValue, 0, 1000, 50, 250);
  if((sc1>=50)&&(sc<=250)){
  sc=sc1;
  lcd.setCursor(0,0); 
  lcd.print(sc);
  delay(100);
 }
}
//void PrintPoint(int x1, int y1, int t){
  //bluetooth.read(x1);
 // bluetooth.read(y1);
 // bluetooth.read(t);
  
//}

void PrintXY(int x, int y)
{
  byte n0 = 0;
  byte n1 = 0;
  byte n2 = 0;
  byte n3 = 0;
  byte v = 0;
  switch (y) {
    case 0:
      v = 0b01000000;
      break;
    case 1:
      v = 0b00100000;
      break;
    case 2:
      v = 0b00010000;
      break;
    case 3:
      v = 0b00001000;
      break;
    case 4:
      v = 0b00000100;
      break;
  }
  switch (x) {
    case 0:
      n0 = v;
      break;
    case 1:
      n1 = v;
      break;
    case 2:
      n2 = v;
      break;
    case 3:
      n3 = v;
      break;
  }

  digitalWrite (LATCH_PIN, LOW);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, n3);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, n2);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, n1);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, n0);
  digitalWrite(LATCH_PIN, HIGH); 
}

void AddPointXY(int x, int y)
{
  byte i = 0;
  byte v = 0;
  switch (y) {
    case 0:
      v = 0b01000000;
      break;
    case 1:
      v = 0b00100000;
      break;
    case 2:
      v = 0b00010000;
      break;
    case 3:
      v = 0b00001000;
      break;
    case 4:
      v = 0b00000100;
      break;
  }

  data[x] = data[x] | v;
  
  digitalWrite (LATCH_PIN, LOW);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, data[3]);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, data[2]);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, data[1]);
  shiftOut(DATA_PIN, CLOCK_PIN, LSBFIRST, data[0]);
  digitalWrite(LATCH_PIN, HIGH); 
}


void loop() {
  scor();
  if (bluetooth.available() > 0) { // put your main code here, to run repeatedly:
    char digit = bluetooth.read();
      
    switch (digit) {
      case '0' :
        if(mode == SledMode)
          PrintSled(number0, 15);
        else if (mode = FullSymbolMode)
          PrintFull(number0, 15);
        else
          PrintN0();
        break;
      case '1' :
        if(mode == SledMode)
          PrintSled(number1, 8);
        else if (mode = FullSymbolMode)
          PrintFull(number1, 8);
        else
          PrintN1();
        break; 
      case '2' :
        if(mode == SledMode)
          PrintSled(number2, 15);
        else if (mode = FullSymbolMode)
          PrintFull(number2, 15);
        else
          PrintN2();
        break;
      case '3' :
        if(mode == SledMode)
          PrintSled(number3, 19);
        else if (mode = FullSymbolMode)
          PrintFull(number3, 19);
        else
          PrintN3();
        break;
      case '4' :
        if(mode == SledMode)
          PrintSled(number4, 14);
        else if (mode = FullSymbolMode)
          PrintFull(number4, 14);
        else
          PrintN4();
        break;
      case '5' :
        if(mode == SledMode)
          PrintSled(number5, 17);
        else if (mode = FullSymbolMode)
          PrintFull(number5, 17);
        else
          PrintN5();
        break;
      case '6' :
        if(mode == SledMode)
          PrintSled(number6, 17);
        else if (mode = FullSymbolMode)
          PrintFull(number6, 17);
        else
          PrintN6();
        break;
      case '7' :
        if(mode == SledMode)
          PrintSled(number7, 12);
        else if (mode = FullSymbolMode)
          PrintFull(number7, 12);
        else
          PrintN7();
        break;
      case '8' :
        if(mode == SledMode)
          PrintSled(number8, 21);
        else if (mode = FullSymbolMode)
          PrintFull(number8, 21);
        else
          PrintN8();
        break;
      case '9' :
        if(mode == SledMode)
          PrintSled(number9, 16);
        else if (mode = FullSymbolMode)
          PrintFull(number9, 16);
        else PrintN9();
        break;
      case 'e':
      case 'E' :
        mode = PointMode;
        break;
      case 's':
      case 'S' :
        mode = SledMode;
        break;
      case 'f':
      case 'F' :
        mode = FullSymbolMode;
        break;      
    }
  }
}
