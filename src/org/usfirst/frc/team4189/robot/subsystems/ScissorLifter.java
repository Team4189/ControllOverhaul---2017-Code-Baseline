package org.usfirst.frc.team4189.robot.subsystems;

import org.usfirst.frc.team4189.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScissorLifter extends Subsystem {

    public static Talon winchMotor = new Talon(RobotMap.winchPort);
    public static Talon scissorMotor = new Talon(RobotMap.scissorPort);
    
    DigitalInput chanA = new DigitalInput(RobotMap.encA1Port);
    DigitalInput chanB = new DigitalInput(RobotMap.encB1Port);
    //DigitalInput chanA = new DigitalInput(RobotMap.encA3Port);
    //DigitalInput chanB = new DigitalInput(RobotMap.encB3Port);
    Encoder winchEnc = new Encoder(chanA, chanB);

    public void setWinch(double x){
	winchMotor.set(x);
    }
    public void setScissor(double x){
	scissorMotor.set(x);

    }

    public double encGet() {
	return winchEnc.getDistance();
    }


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}

