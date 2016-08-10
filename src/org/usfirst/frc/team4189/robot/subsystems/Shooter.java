package org.usfirst.frc.team4189.robot.subsystems;

import org.usfirst.frc.team4189.robot.RobotMap;
import org.usfirst.frc.team4189.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static Talon shooterAngleMotor = new Talon(RobotMap.shooterAnglePort);
    public static Talon shooterOperation = new Talon(RobotMap.shooterPort);
    public static Talon introBallMotor = new Talon(RobotMap.introBallPort);
//	public DigitalInput shooterLimitSW1 = new DigitalInput(RobotMap.shooterLimitSW1);    
//	public DigitalInput shooterLimitSW2 = new DigitalInput(RobotMap.shooterLimitSW2);
	DigitalInput chanA = new DigitalInput(RobotMap.encA3Port);
    DigitalInput chanB = new DigitalInput(RobotMap.encB3Port);
    public Encoder enc3 = new Encoder(chanA, chanB);
    
    
    
    public double encGet() {
	return enc3.getDistance();
	}

    
    public void changeAngle(double x) {
	shooterAngleMotor.set(x);
    }
    
    public void fireShooter(double x) {
	shooterOperation.set(x);
    }
    
    public void introBall(double x) {
	introBallMotor.set(x);
    }
    
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
	
    }
}