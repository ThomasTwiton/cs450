int button = 0;
int speakerPin = 1;
int soundSensor = 5;

int numTones = 10;
int tones[] = {261, 277, 294, 311, 330, 349, 370, 392, 415, 440};
//            mid C  C#   D    D#   E    F    F#   G    G#   A
int i = 0;

#define ROW_1 2
#define ROW_2 3
#define ROW_3 4
#define ROW_4 5
#define ROW_5 6
#define ROW_6 7
#define ROW_7 8
#define ROW_8 9

#define COL_1 10
#define COL_2 11
#define COL_3 12
#define COL_4 13
#define COL_5 A0
#define COL_6 A1
#define COL_7 A2
#define COL_8 A3

const byte rows[] = {
  ROW_1, ROW_2, ROW_3, ROW_4, ROW_5, ROW_6, ROW_7, ROW_8
};
const byte col[] = {
COL_1,COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8
};

byte letters[][8] {
  {B00000000,B11111100,B11111110,B00010011,B00010011,B11111110,B11111100,B00000000},
  {B00000000,B11111111,B10011001,B10011001,B10011001,B01100110,B00000000,B00000000},
  {B00111100,B01111110,B11100111,B11000011,B11000011,B11000011,B01100110,B01100110},
  {B00000000,B01111110,B01000010,B01000010,B01000010,B01100110,B00111100,B00000000},
  {B00000000,B00000000,B01111110,B00001010,B00001010,B00000010,B00000000,B00000000}
};

void setup()
{
//microphone sensor
pinMode(soundSensor, INPUT);

//button
pinMode(button, INPUT_PULLUP);

//LED 8x8 board
for (byte i = 2; i <= 13; i++)
      pinMode(i, OUTPUT);
pinMode(A0, OUTPUT);
pinMode(A1, OUTPUT);
pinMode(A2, OUTPUT);
pinMode(A3, OUTPUT);

Serial.begin(9600);
}

void loop()
{
//bressing the button will cause the tone to play
tone(speakerPin,tones[0]);

//if the mic hears the buzzer, change the letter
int SensorData = analogRead(soundSensor);
if(SensorData>23){
    Serial.print("I hear the buzzer");
    i++;
    if(i>4){
        i=0;
      }
  }

  //calls function to draw the letter
  drawScreen(letters[i]);
}

void  drawScreen(byte buffer2[])
{
 // Turn on each row in series
  for (byte i = 0; i < 8; i++)        // count next row
   {
      digitalWrite(rows[i], HIGH);    //initiate whole row
      for (byte a = 0; a < 8; a++)    // count next row
      {
        // if You set (~buffer2[i] >> a) then You will have positive
        digitalWrite(col[a], (buffer2[i] >> a) & 0x01); // initiate whole column

        delayMicroseconds(100);       // uncoment deley for diferent speed of display
        //delayMicroseconds(1000);
        //delay(10);
        //delay(100);

        digitalWrite(col[a], 1);      // reset whole column
      }
      digitalWrite(rows[i], LOW);     // reset whole row
      // otherwise last row will intersect with next row
  }
}
