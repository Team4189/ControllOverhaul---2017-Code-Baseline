package org.usfirst.frc.team4189.robot.subsystems;

import java.util.Arrays;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;
import org.usfirst.frc.team4189.robot.RobotMap;
import org.usfirst.frc.team4189.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	Talon rightMotor = new Talon(RobotMap.rightMotorPort);
	Talon leftMotor = new Talon(RobotMap.leftMotorPort);
	Timer timer = new Timer();
	
	// public static Pixy pixy = new Pixy();

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void setSpeed(double x, double y) {
		leftMotor.set(x * -1);
		rightMotor.set(y);
	}

	public double convert() {
		// conversion factor: 4.9mV/1cm
		// this is our best guess so far
		return OI.rangeFinder1.getVoltage() * 83.961;
		// return 1;
	}
	
//	public double convert2() {
//		// conversion factor: 4.9mV/1cm
//		// this is our best guess so far
//		return OI.rangeFinder2.getVoltage() * 83.961;
//		// return 1;
//	}

	public double gyroConvert() {
		if (OI.gyro.getAngle() < 360 && OI.gyro.getAngle() > -360) {
			return OI.gyro.getAngle();

		}
		OI.gyro.reset();

		return OI.gyro.getAngle();

	}
	

	public void dashData() {

		// SmartDashboard.putNumber("DII Get Value",
		// SmartDashboard.getNumber("Distance In Inches"));

		// System.out.println(OI.gyro.getAngle());

		SmartDashboard.putNumber("Distance In Inches", convert());
		//SmartDashboard.putNumber("Distance in Inches, Rear" , Robot.chassis.convert2());
		// SmartDashboard.putNumber("Gyro Acceleration" , OI.gyro.getRate());
		SmartDashboard.putNumber("Gyro Angle", gyroConvert());
		SmartDashboard.putNumber("Pixy X", OI.pixyX.getVoltage());
		SmartDashboard.putNumber("Pixy Y", OI.pixyY.getVoltage());
		
		/**
		 * We aren't doing any logic with these numbers, currently, just
		 * printing to the dashboard the block inside the try/catch block seems
		 * like it would print a more stable read-out to the dashboard, but it's
		 * possible that this commented code would be more responsive when it
		 * comes to logic:
		 * 
		 * 
		 * //PixyPacket pkt = pixy.readPacket(1); get rid of this line
		 * SmartDashboard.putNumber("Object x: ", pixy.readPacket(1).X);
		 * SmartDashboard.putNumber("Object y: ", pixy.readPacket(1).Y);
		 * SmartDashboard.putNumber("Object Height: ",
		 * pixy.readPacket(1).Height); SmartDashboard.putNumber("Object Width: "
		 * , pixy.readPacket(1).Width);
		 */

		/*
		 * try { PixyPacket pkt = pixy.readPacket(1); if(pkt != null){
		 * SmartDashboard.putNumber("Object x: ", pkt.X);
		 * SmartDashboard.putNumber("Object y: ", pkt.Y);
		 * SmartDashboard.putNumber("Object Height: ", pkt.Height);
		 * SmartDashboard.putNumber("Object Width: ", pkt.Width); } } catch
		 * (PixyException e) { // TODO Auto-generated catch block
		 * //e.printStackTrace(); System.out.println(e.getMessage());
		 * 
		 * //test }
		 */
	}

	public double compareRange() {
		double minRange = 0.0;
		double[] SwerveVs = new double[90];
		for (int i = 0; i < 90; i++) {
			Robot.chassis.setSpeed(-0.25, 0.25);
			SwerveVs[i] = Robot.chassis.convert();
			Robot.chassis.setSpeed(0, 0);
		}

		Arrays.sort(SwerveVs);
		minRange = SwerveVs[0];
		Robot.chassis.setSpeed(0, 0);
		return minRange;

	}

	public void findMinRange() {

		double minRange = compareRange();
		while (Robot.chassis.convert() != minRange) {
			Robot.chassis.setSpeed(0.25, -0.25);
		}
		Robot.chassis.setSpeed(0, 0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
