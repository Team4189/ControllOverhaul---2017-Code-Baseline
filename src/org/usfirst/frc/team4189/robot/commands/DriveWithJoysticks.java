package org.usfirst.frc.team4189.robot.commands;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;
import org.usfirst.frc.team4189.robot.subsystems.PortcullisSubsystem;
import org.usfirst.frc.team4189.robot.subsystems.ScissorLifter;
import org.usfirst.frc.team4189.robot.subsystems.Shooter;

import Autonomous.DriveForAccel;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoysticks extends Command {

	double time;
	Timer timer;
	double lastTime = 0;
	double dt = 0;
	double velocity = 0; // define the initial velocity of the object
	float position = 0;
	BuiltInAccelerometer accel;

	public DriveWithJoysticks() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Drive With Joysticks has Initialized");

		DriveForAccel dfa = new DriveForAccel();
		timer = new Timer();
		timer.start();
		accel = new BuiltInAccelerometer();

	}

	protected double getAccel() {
		double read = accel.getY();
		if (Math.abs(read) < .025)
			return 0;
		else
			return read;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
    	
    	if (OI.safety.get() == true && OI.reverse.get() == true){
    		Robot.chassis.setSpeed(OI.rightStick.getY()*-1 , OI.leftStick.getY()*-1);
    	} else{
    		Robot.chassis.setSpeed(OI.leftStick.getY() , OI.rightStick.getY());
    	}
    	
    	if (OI.portSaftey.get() == true){
    		PortcullisSubsystem.chevalMotor.set(OI.accStick.getY());
    	} else {
    		PortcullisSubsystem.chevalMotor.set(0);
    	}
    	
    	SmartDashboard.putNumber("Left Stick", OI.leftStick.getY());
    	SmartDashboard.putNumber("Right Stick", OI.rightStick.getY());
    	
    	if(OI.safety.get() == true){
    		Robot.shooter.changeAngle(OI.accStick.getY());
    	}
    	
    	if(OI.safety.get() == true){
    		if (OI.resetEnc.get() == true){
    			Robot.shooter.enc3.reset();
    		}
    	
    	if (OI.safety.get() == true && OI.resetPortEnc.get() == true){
    			Robot.cheval.enc2.reset();
    		}
    	
    		
//    	if(OI.safety.get() == true && OI.portUp.get() == true){
//    		Robot.cheval.setSpeed(.25);
//    	}else if(OI.safety.get() == true && OI.portDown.get() == true){
//    		Robot.cheval.setSpeed(-.25);
//    	}else{
//    		Robot.cheval.setSpeed(0);
//    	}
    	
    	
    	double getAccelVar = getAccel();
    	time = timer.get();  // time in seconds from the beginning
        dt = time - lastTime;  //time of last loop
        lastTime = time;

    	//velocity += Math.abs(((getAccelVar)*9.81)/3.28) * dt;
        position += 0.5 * Math.abs(accel.getY()) * (dt * dt);
        
        
       // System.out.println("Raw Serial Data" + Robot.pixy.pixy.readString());
//        SmartDashboard.putNumber("Distance Traveled", position);
//        SmartDashboard.putNumber("Accelerometer", accel.getX());
//        SmartDashboard.putNumber("Corrected Accel", getAccelVar);
    	}
    	}

	void setLifter() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// Robot.chassis.setSpeed(0, 0);
		return false;

	}

	// Called once after isFinished returns true
	protected void end() {
		// Robot.chassis.setSpeed(0, 0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		// Robot.chassis.setSpeed(0, 0);
	}
}
