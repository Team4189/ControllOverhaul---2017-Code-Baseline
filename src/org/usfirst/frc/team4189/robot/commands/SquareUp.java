package org.usfirst.frc.team4189.robot.commands;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SquareUp extends Command {

	boolean resetPlease = false;
	double range = 0;
	double angle = 0;
   
	public SquareUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    public void compareRange()
    {
    	Robot.chassis.setSpeed(-0.33, 0.36);
    	double minRange, minRangeAngle;
    	TreeMap<Double, Double> gyroDistance = new TreeMap<>();
        //double [] SwerveVs = new double[49500]; //robot seems to be checking approx 16,500 per second
        for(int i = 0; i < 4950; i++)
        {
        	gyroDistance.put(Robot.chassis.gyroConvert(), Robot.chassis.convert());
            //SwerveVs[i] = Robot.chassis.convert();
//            SmartDashboard.putNumber("Distance In Inches", Robot.chassis.convert());
//            SmartDashboard.putNumber("Angle at distance", Robot.chassis.gyroConvert());
        }
        Robot.chassis.setSpeed(0,0);
        minRange = gyroDistance.firstEntry().getValue();
        minRangeAngle = gyroDistance.firstEntry().getKey();
        //TODO::: This needs fixing
        for (Map.Entry<Double, Double> entry : gyroDistance.entrySet())
        {
        	if (minRange >= entry.getValue())
        	{
        		minRangeAngle = entry.getKey();
        		minRange = entry.getValue();
        	}
        }
        //Arrays.sort(SwerveVs);
        //minRange = SwerveVs[0];
        this.range = minRange;
        this.angle = minRangeAngle;

    }
    public void findMinRange()
    {
        compareRange();
//        SmartDashboard.putNumber("Minimum Range", this.range);
//        SmartDashboard.putNumber("Angle at distance", this.angle);
        
        
        while(Robot.chassis.gyroConvert() < angle - 4.0 || Robot.chassis.gyroConvert() > angle + .5)
        {
        	if (Robot.chassis.gyroConvert() > angle)
            {
            	Robot.chassis.setSpeed(-0.35, 0.37);
//            	SmartDashboard.putNumber("Gyro Angle", Robot.chassis.gyroConvert());
//            	SmartDashboard.putNumber("Distance In Inches", Robot.chassis.convert());
            }
            else
            {
            	Robot.chassis.setSpeed(-.35,  .37);
//            	SmartDashboard.putNumber("Gyro Angle", Robot.chassis.gyroConvert());
//            	SmartDashboard.putNumber("Distance In Inches", Robot.chassis.convert());
            }
        }
        
        Robot.chassis.setSpeed(0,0);
        
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	findMinRange();
    	resetPlease = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return resetPlease;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
