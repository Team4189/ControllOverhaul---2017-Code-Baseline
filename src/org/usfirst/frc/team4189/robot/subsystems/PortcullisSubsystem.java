package org.usfirst.frc.team4189.robot.subsystems;

import org.usfirst.frc.team4189.robot.RobotMap;
import org.usfirst.frc.team4189.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PortcullisSubsystem extends Subsystem {
   public static Talon chevalMotor = new Talon(RobotMap.chevalPort);
   DigitalInput chanA = new DigitalInput(RobotMap.encA2Port);
   DigitalInput chanB = new DigitalInput(RobotMap.encB2Port);
   public Encoder enc2 = new Encoder(chanA, chanB);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

   public double encGet() {
   	return enc2.getDistance();
   	} 
   
   public void setSpeed(double x) {
	chevalMotor.set(x);
    }
    
    public void initDefaultCommand() {
	//setDefaultCommand(new DriveWithJoysticks());
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}

