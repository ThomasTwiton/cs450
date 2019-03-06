#include <Stepper.h>
#include <Servo.h>

int servoPin = 9;
int buttonPin = 2;
bool change = false;

Servo servo;

int angle = 0;   // servo position in degrees

int in1Pin = 7;
int in2Pin = 6;
int in3Pin = 5;
int in4Pin = 4;

Stepper motor(768, in1Pin, in2Pin, in3Pin, in4Pin);

void setup()
{
servo.attach(servoPin);

pinMode(in1Pin, OUTPUT);
pinMode(in2Pin, OUTPUT);
pinMode(in3Pin, OUTPUT);
pinMode(in4Pin, OUTPUT);

pinMode(buttonPin, INPUT_PULLUP);

motor.setSpeed(20);
}

void loop()
{

if(change){
   angle = angle + 1;
   servo.write(angle);
   delay(20);
   if(angle>=180){
     angle = 0;
     }
 } else {
   motor.step(20);
   }

   if (digitalRead(buttonPin) == LOW)
  {
    change = true;
  }
  else if (digitalRead(buttonPin) == HIGH)
  {
    change = false;
  }

   
 /* couldn't get microphone sensor to work because it kept picking up on the noise the servo motor made
 /* int SensorData = analogRead(soundSensor);
 Serial.print(SensorData);
   Serial.print("\n");
 if(SensorData>68){     
     change = not change;
     Serial.print(change);
   } */
}
