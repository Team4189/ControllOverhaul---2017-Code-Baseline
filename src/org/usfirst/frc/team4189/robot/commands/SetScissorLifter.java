package org.usfirst.frc.team4189.robot.commands;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;
import org.usfirst.frc.team4189.robot.subsystems.ScissorLifter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetScissorLifter extends Command {

	public SetScissorLifter() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (OI.lifterUp.get() == true || OI.lifterDown.get() == true) {
			if (OI.lifterUp.get() == true) {
				Robot.scissorLifter.setScissor(-1);
			}
			if (OI.lifterDown.get() == true) {
				Robot.scissorLifter.setScissor(1);
			}
		} else{
			Robot.scissorLifter.setScissor(0);
		}
		
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
