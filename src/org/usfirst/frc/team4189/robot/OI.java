package org.usfirst.frc.team4189.robot;

import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import org.usfirst.frc.team4189.robot.commands.IntroBall;
import org.usfirst.frc.team4189.robot.commands.ResetGyro;
import org.usfirst.frc.team4189.robot.commands.SetScissorLifter;
import org.usfirst.frc.team4189.robot.commands.SquareUp;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	// Joysticks
	public static Joystick leftStick = new Joystick(RobotMap.leftStick);
	public static Joystick rightStick = new Joystick(RobotMap.rightStick);
	public static Joystick accStick = new Joystick(RobotMap.accStick);
	// comment

	// Analog Inputs
	public static AnalogInput rangeFinder1 = new AnalogInput(0);
	public static AnalogGyro gyro = new AnalogGyro(1);
	public static AnalogInput pixyX = new AnalogInput(2);
	public static AnalogInput pixyY = new AnalogInput(3);
	

	// Buttons
	public static Button reverse = new JoystickButton(OI.rightStick, 1);
	public static Button resetGyro = new JoystickButton(OI.rightStick, 2);
	public static Button resetEnc = new JoystickButton(OI.rightStick, 11);
	public static Button resetPortEnc = new JoystickButton(OI.rightStick, 10);

	public static Button shooterUp = new JoystickButton(OI.accStick, 7);
	public static Button shooterDown = new JoystickButton(OI.accStick, 9);
	public static Button shooterShoot = new JoystickButton(OI.accStick, 1);
	public static Button shooterParked = new JoystickButton(OI.accStick, 8);
	public static Button introBall = new JoystickButton(OI.accStick, 2);
//	public static Button portDown = new JoystickButton(OI.accStick, 12);
//	public static Button portUp = new JoystickButton(OI.accStick, 10);
//	public static Button portParked = new JoystickButton(OI.accStick, 11);
	public static Button lifterUp = new JoystickButton(OI.accStick, 5);
	public static Button lifterDown = new JoystickButton(OI.accStick, 3);
	public static Button portSaftey = new JoystickButton(OI.accStick, 11);
	//public static Button scoopPulse = new JoystickButton(OI.accStick, 4);
	public static Button autoAllign = new JoystickButton(OI.accStick, 6);
	public static Button autoAllignY = new JoystickButton(OI.accStick, 4);
	
	public static Button winchUp = new JoystickButton(OI.leftStick, 3);
	public static Button winchDown = new JoystickButton(OI.leftStick, 2);
	//public static Button squareUp = new JoystickButton(OI.leftStick, 3);
	public static Button safety = new JoystickButton(OI.leftStick, 1);
	


	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	// button.whenPressed(new ExampleCommand());
	// button.whileHeld(new ExampleCommand());
	// button.whenReleased(new ExampleCommand());

	public OI() {
		introBall.whenPressed(new IntroBall());
	}
}
