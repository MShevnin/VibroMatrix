#include<SoftwareSerial.h>
SoftwareSerial bluetooth(0, 1);
int pinX[8] = {15, 16, 17, 18, 19, 3}; //+
int pinY[8]= {12, 11, 10, 9, 8, 7, 6}; //-

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
  for( int x=0; x<6; x++){
    pinMode (pinX[x], OUTPUT);
    digitalWrite (pinX[x], HIGH);
  }

  for( int y=0; y<7; y++){
    pinMode (pinY[y], OUTPUT);  
    digitalWrite (pinY[y], LOW);
  }
  
  for(int y = 0; y < 7; y++){
    for( int x=0; x<6; x++){
      drawPoint(x, y, true);
      delay(500);
      drawPoint(x, y, false);
      delay(100);
    }
    delay(1000);
  }
}

void drawPoint(int x, int y, bool power){
  if( power == true ){
    if((x >= 0)&&(x < 6)&&(y >= 0)&&(y < 7)){
      digitalWrite (pinX[x], LOW);
      digitalWrite (pinY[y], HIGH);  
    }
  }
  else{
    if((x >= 0)&&(x < 6)&&(y >= 0)&&(y < 7)){
      digitalWrite (pinX[x], HIGH);
      digitalWrite (pinY[y], LOW);
    }
  }
}

void loop() {

  if (bluetooth.available() > 2) { 
    //drawPoint(7, 7, true);
    //delay(1000);
    //drawPoint(7, 7, false);
    //delay(1000);

    byte x = bluetooth.read();
    byte y = bluetooth.read();
    byte t = bluetooth.read();
    
    if(x==100){
      delay(10*t);    
    }
    else{
      drawPoint(x, y, true);
      delay(10*t);
      drawPoint(x, y, false);
      //delay(10*t);
    }
 }

}
  
