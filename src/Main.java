import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

public class Main {

	static int theta;
	
	static float[] colorSamples = new float[3];
	static float[] touchSample = new float[3];
	public static void main(String[] args) throws InterruptedException{
		
		EV3 ev3brick =(EV3) BrickFinder.getLocal();
		TextLCD text = ev3brick.getTextLCD();
		EV3ColorSensor Color = new EV3ColorSensor(SensorPort.S1);
		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S2);
		
		touch.getTouchMode();
		Color.getColorIDMode();  //tells the sensor to return a color  (NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN) which is a value (0,1,2,3,4,4,5,6)
		text.drawString("Robot Running!",0,0);
		
		/*while(!Button.ENTER.isDown()){   //Apparently infinite while loops break things, so loop until enter is pressed
			
			Color.fetchSample(colorSamples, 0);   //Take a sample from color sensor and write to Colorsamples array
			touch.fetchSample(touchSample, 0);
			Delay.msDelay(2);   //This may or may not be needed, but was in the tutorial I read
			text.drawString("Color sensor value is:  " + colorSamples[0], 0, 3);  //Print color value for debugging
			


			if(colorSamples[0] == 1){
				text.drawString("Color sensor reads BLACK!", 1,4);
				stopMotors();
				
			}
			
			else if(touchSample[0] == 1){
				stopMotors();
				setSpeed(1000);
				flipper();
				moveBackward();
			}
			
			else
				setSpeed(100);		*/	
			
			
			/*setSpeed(100);
			moveForward();
			Delay.msDelay(2000);
			moveBackward();
			moveForward();
			stopMotors();
			setSpeed(1000);
			flipper();
			
			stopMotors();*/
		
			/*setSpeed(1000);
			turnLeft();
			stopMotors();*/
			setSpeed(100);
		while(!Button.ENTER.isDown()){
			
			Color.fetchSample(colorSamples, 0);   //Take a sample from color sensor and write to Colorsamples array
			touch.fetchSample(touchSample, 0);
			Delay.msDelay(2);   //This may or may not be needed, but was in the tutorial I read
			
			if(Color.getColorID() == 1) {
				stopMotors();
			}
			
			else if(touchSample[0] == 0) {
				if(!Motor.A.isMoving() || !Motor.B.isMoving())
					moveForward();
				
				else 
					continue;
			}
			
		
			else {
				stopMotors();
				flipper();
				moveBackward();
				turnLeft();
				moveForward();
			}
		}
			
			
	       
			
	        //DO SOME STUFF
		//}

	}

	
	public static void setSpeed(int speed){ //Set motor speeds, we should only set full speed once at the start of the code?
		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
		
		
	}
	public static void moveForward(){ //pass in a time in ms for the robot to drive forwards
		Motor.A.forward();
		Motor.B.forward();
	}
	public static void moveBackward(){ //pass in a time in ms for the robot to drive backwards
		Motor.A.backward();
		Motor.B.backward();
		Delay.msDelay(2000);
	}
	public static void flipper() {
		Motor.C.forward();
		Delay.msDelay(1000);
		Motor.C.backward();
		Delay.msDelay(1000);
		Motor.C.stop();
	}
	public static void stopMotors() {
		Motor.A.stop();
		Motor.B.stop();
	}
	
	public static void turnLeft() {
		Motor.B.forward();
		Delay.msDelay(2000);
		Motor.B.stop();
	}
	
	public static void turnRight() {
		Motor.A.forward();
		Delay.msDelay(2000);
		Motor.A.stop();
	}
}
