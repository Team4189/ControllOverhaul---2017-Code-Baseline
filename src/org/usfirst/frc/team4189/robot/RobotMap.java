package org.usfirst.frc.team4189.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//If editing, NAME COMPONENTS DESCRIPTIVELY
	
	//Joystick port numbers go here
	public static int leftStick = 0;
	public static int rightStick = 1;
	public static int accStick = 2;
	
	//PWM port numbers go here 
	public static int leftMotorPort = 0;
	public static int rightMotorPort = 1;
	public static int winchPort = 2;
	public static int scissorPort = 3;
	public static int chevalPort = 4;
	public static int shooterAnglePort = 5; 
	public static int shooterPort = 6;
	public static int introBallPort = 7;
	
	//DIO port numbers go here
	public static int encA1Port = 0;
	public static int encB1Port = 1;
	public static int encA2Port = 2;//Portcullis Angle
	public static int encB2Port = 3;//Portcullis Angle
	public static int encA3Port = 4;//Shooter Angle
	public static int encB3Port = 5;//Shooter Angle
	//public static int shooterLimitSW1 = 6;
	//public static int shooterLimitSW2 = 7;
	
	
	//AnalogInput port numbers go here
	public static int rngF1Port = 0;
	public static int rngF2Port = 1;
	public static int rngF3Port = 2;
	public static int gyroPort = 3;
	
	//Relay port numbers go here
	//public static int *NAME* = 0;
	//public static int *NAME* = 1;
	//public static int *NAME* = 2;
	//public static int *NAME* = 3;
	
			
	
	
	
}
