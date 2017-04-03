#include<SoftwareSerial.h>
SoftwareSerial bluetooth(0, 1);
int pinX[8] = {14, 15, 16, 17, 18, 19, 3, 2}; //+
int pinY[8]= {12, 11, 10, 9, 8, 7, 6, 5}; //-

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
  for( int i=0; i<8; i++){
    pinMode (pinX[i], OUTPUT);
    pinMode (pinY[i], OUTPUT);  

    digitalWrite (pinX[i], HIGH);
    digitalWrite (pinY[i], LOW);
  }
  
  for(int j = 0; j < 8; j++){
  for( int i=0; i<8; i++){
    drawPoint(i, j, true);
    delay(1000);
    drawPoint(i, j, false);
    delay(100);
  }
  delay(1000);
  }
}

void drawPoint(int x, int y, bool power){
  if( power == true ){
    digitalWrite (pinX[x+1], LOW);
    digitalWrite (pinY[y], HIGH);  
  }
  else{
    digitalWrite (pinX[x+1], HIGH);
    digitalWrite (pinY[y], LOW);
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
  
