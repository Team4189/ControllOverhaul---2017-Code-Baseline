package org.usfirst.frc.team4189.robot.commands;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;
import org.usfirst.frc.team4189.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterCommand extends Command {
	// private State currentState;
	int state;
	Timer timer = new Timer();
	boolean yAlligned;
	boolean xAlligned;

	public ShooterCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// public enum State {
	// SCOOPING_POS, TRAVELING_POS, SHOOTING_POS
	// }

	// Called just before this Command runs the first time
	protected void initialize() {
		state = 2;
		yAlligned = false;
		xAlligned = false;
	}

	double v() {
		return (.2 * Math.abs(OI.pixyX.getVoltage() - 2.3) + .15);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putNumber("Shooter Enc Val", Robot.shooter.encGet());
		SmartDashboard.putBoolean("Is alligning", OI.autoAllign.get());
		SmartDashboard.putBoolean("X Alligned", xAlligned);
		SmartDashboard.putBoolean("Y Alligned", yAlligned);

		if (OI.shooterUp.get()) {
			state = 3;
		} else if (OI.shooterDown.get()) {
			state = 1;
		} else if (OI.shooterParked.get()) {
			state = 2;
		}

		switch (state) {
		case 2:

			if (Robot.shooter.encGet() > -10 && OI.safety.get() == false) {
				Shooter.shooterAngleMotor.set(-.5);
			} else if (Robot.shooter.encGet() < 10 && OI.safety.get() == false) {
				Shooter.shooterAngleMotor.set(.5);
			}

			if (Robot.shooter.encGet() > -10 && Robot.shooter.encGet() < 10 && OI.safety.get() == false) {
				Shooter.shooterAngleMotor.set(0);
			}

			if (OI.shooterShoot.get() == true) {
				Shooter.shooterOperation.set(1);
			} else {
				Shooter.shooterOperation.set(0);
			}

//			if (timer.get() != 0 && OI.scoopPulse.get() == true) {
//				timer.start();
//			}

			if (timer.get() < .2 && timer.get() != 0) {
				Robot.shooter.shooterOperation.set(-1);
			} else if (timer.get() > .2) {
				Shooter.shooterOperation.set(0);
			}

			if (OI.autoAllign.get() == true) {

				if (OI.pixyX.getVoltage() < 2.2) {
					Robot.chassis.setSpeed(v(), -v());
				} else if (OI.pixyX.getVoltage() > 2.4) {
					Robot.chassis.setSpeed(-v(), v());
				} else {
					Robot.chassis.setSpeed(0, 0);
					xAlligned = true;
				}
			}
			
			if (OI.autoAllignY.get() == true) {

				if (OI.pixyY.getVoltage() < 3.1) {
					Robot.chassis.setSpeed(-v(), -v());
				} else if (OI.pixyY.getVoltage() > 3.3) {
					Robot.chassis.setSpeed(v(), v());
				} else {
					Robot.chassis.setSpeed(0, 0);
					yAlligned = true;
				}
			}
			
			

			break;

		case 1:
			if (Robot.shooter.encGet() > -670 && OI.safety.get() == false) {
				Shooter.shooterAngleMotor.set(-.5);
			} else if (Robot.shooter.encGet() < -680 && OI.safety.get() == false) {
				Shooter.shooterAngleMotor.set(.5);
			} else {
				Shooter.shooterAngleMotor.set(0);
			}
			Shooter.shooterOperation.set(-.5);

			break;

		case 3:
			if (Robot.shooter.encGet() < 940 && OI.safety.get() == false) {
				Shooter.shooterAngleMotor.set(.5);
			} else if (Robot.shooter.encGet() > 960 && OI.safety.get() == false) {
				Shooter.shooterAngleMotor.set(-.5);
			} else {
				Shooter.shooterAngleMotor.set(0);
			}

			if (OI.shooterShoot.get() == true) {
				Shooter.shooterOperation.set(1);
			} else {
				Shooter.shooterOperation.set(0);
			}

			break;

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
