package org.usfirst.frc.team4189.robot.commands;

import org.usfirst.frc.team4189.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntroBall extends Command {
	Timer timer = new Timer();
	boolean finished;
    public IntroBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(timer.get() < .25){
    		Robot.shooter.introBall(.5);
    		//System.out.println(timer.get());
    	}
    	else if(timer.get() > .25 && timer.get() < .6){
    		Robot.shooter.introBall(-.5);
    		//System.out.println(timer.get());
    	}
    	else if(timer.get() > .6){
    		Robot.shooter.introBall(0);
    		//System.out.println(timer.get());
    		timer.stop();
    		this.cancel();
    		
    		
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
