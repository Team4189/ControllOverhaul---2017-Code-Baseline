package Autonomous;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RockWall extends Command {
	Timer timer = new Timer();
	

	public RockWall() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		OI.gyro.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.chassis.gyroConvert() > 5){
    		Robot.chassis.setSpeed(-.5 , -.8);
    	}
    	else{
    		Robot.chassis.setSpeed(-.6, -.6);
    	}
    	if(Robot.chassis.gyroConvert() < -5){
    		Robot.chassis.setSpeed(-.8 , -.5);
    	}
    	else{
    		Robot.chassis.setSpeed(-.6, -.6);
    	}
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(timer.get() >= 4){
			return true;
		}
		else{
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		this.cancel();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
