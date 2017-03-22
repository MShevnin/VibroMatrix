#include<SoftwareSerial.h>
SoftwareSerial bluetooth(0, 1);
int pinX[8] = {14, 15, 16, 17, 18, 19, 3, 2}; //+
int pinY[8]= {12, 11, 10, 9, 8, 7, 6, 5}; //-
void setup() {
  bluetooth.begin(9600);  
  for( int i=0; i<8; i++){
    pinMode (pinX[i], OUTPUT);
    pinMode (pinY[i], OUTPUT);  
  }
  drawPoint(7, 7, true);
  delay(1000);
  drawPoint(7, 7, false);
  delay(1000);

} 
void drawPoint(int x, int y, bool power){
  if( power == true ){
    digitalWrite (pinX[x], LOW);
    digitalWrite (pinY[y], HIGH);  
  }
  else{
    digitalWrite (pinX[x], HIGH);
    digitalWrite (pinY[y], LOW);
  }
}

void loop() {

  if (bluetooth.available() > 0) { 
    drawPoint(7, 7, true);
    delay(1000);
    drawPoint(7, 7, false);
    delay(1000);

    byte x = bluetooth.read();
    byte y = bluetooth.read();
    byte t = bluetooth.read();
    drawPoint(x, y, true);
    delay(10*t);
    drawPoint(x, y, false);
    delay(10*t);
 }

}
  
