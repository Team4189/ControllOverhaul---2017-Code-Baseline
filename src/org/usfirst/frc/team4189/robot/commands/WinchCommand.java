package org.usfirst.frc.team4189.robot.commands;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;
import org.usfirst.frc.team4189.robot.subsystems.ScissorLifter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4189.robot.subsystems.ScissorLifter;
/**
 *
 */
public class WinchCommand extends Command {

    public WinchCommand() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	if(OI.winchUp.get() == true || OI.winchDown.get() == true) {
	    if(OI.winchUp.get() == true)
	    {
		Robot.scissorLifter.setWinch(1);
	    }
	    else if(OI.winchDown.get() == true)
	    {
		Robot.scissorLifter.setWinch(-1);
	    }
	}
	else
	{
	    Robot.scissorLifter.setWinch(0);
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
