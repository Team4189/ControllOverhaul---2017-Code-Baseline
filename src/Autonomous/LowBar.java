package Autonomous;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LowBar extends Command {

	Timer timer;

	public LowBar() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		OI.gyro.reset();
		timer = new Timer();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() < 4) {
			System.out.println("less than four");
			if (Robot.chassis.gyroConvert() > 5) {
				Robot.chassis.setSpeed(-.7, -.4);
			} else {
				Robot.chassis.setSpeed(-.5, -.5);
			}
			if (Robot.chassis.gyroConvert() < -5) {
				Robot.chassis.setSpeed(-.4, -.7);
			} else {
				Robot.chassis.setSpeed(-.50, -.50);
			}
		} else if(timer.get() > 4 && timer.get() < 8){
			System.out.println("greater than four");
			if (Robot.chassis.gyroConvert() > 5) {
				Robot.chassis.setSpeed(.7, .4);
			} else {
				Robot.chassis.setSpeed(.50, .50);
			}
			if (Robot.chassis.gyroConvert() < -5) {
				Robot.chassis.setSpeed(.4, .7);
			} else {
				Robot.chassis.setSpeed(.50, .50);
			}
		}else{ 
			System.out.println("Done");
			this.cancel();
		}
	SmartDashboard.putNumber("Autonomous Timer", timer.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
