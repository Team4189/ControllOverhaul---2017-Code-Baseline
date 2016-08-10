package Autonomous;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForAccel extends Command {
	double time;
	Timer timer;
	double lastTime = 0;
	double dt = 0;
	double velocity = 0; // define the initial velocity of the object
	float position = 0;
	BuiltInAccelerometer accel;

    public DriveForAccel() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    	accel = new BuiltInAccelerometer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time = timer.get();  // time in seconds from the beginning
        dt = time - lastTime;  //time of last loop
        lastTime = time;

    	velocity += Math.abs(((accel.getX())*9.81)/3.28) * dt;
        position += 0.5 * velocity * dt;
        
//        SmartDashboard.putNumber("Distance Traveled", position);
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
