// привет

int t1 = 14;
int t2 = 12;
void setup() {
  pinMode (t1, OUTPUT);
  pinMode (t2, OUTPUT);
}

void loop() {
  digitalWrite (t2, HIGH);
  digitalWrite (t1, LOW);
  delay (200);
  digitalWrite (t1, HIGH);
  digitalWrite (t2, LOW);
  delay (200);
  
  
  

}
