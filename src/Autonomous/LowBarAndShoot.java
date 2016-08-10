package Autonomous;

import org.usfirst.frc.team4189.robot.OI;
import org.usfirst.frc.team4189.robot.Robot;
import org.usfirst.frc.team4189.robot.commands.IntroBall;
import org.usfirst.frc.team4189.robot.commands.ShooterCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LowBarAndShoot extends Command {

	Timer timer;
	Timer timer2;
	boolean yAlligned;
	boolean xAlligned;

	public LowBarAndShoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		OI.gyro.reset();
		timer = new Timer();
		timer2 = new Timer();
		timer.reset();
		timer2.reset();
		timer.start();
		yAlligned = false;
		xAlligned = false;
	}

	double v() {
		return (.2 * Math.abs(OI.pixyX.getVoltage() - 2.3) + .15);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() < 5) {
			if (Robot.chassis.gyroConvert() > 5) {
				Robot.chassis.setSpeed(-.4, -.7);
			} else {
				Robot.chassis.setSpeed(-.50, -.50);
			}

			if (Robot.chassis.gyroConvert() < -5) {
				Robot.chassis.setSpeed(-.7, -.4);
			} else {
				Robot.chassis.setSpeed(-.50, -.50);
			}
		} else if (timer.get() > 5 && Robot.chassis.gyroConvert() < 45) {
			Robot.chassis.setSpeed(-.5, .5);
		} else if (xAlligned == false || yAlligned == false) {

			if (OI.pixyX.getVoltage() < 2.2) {
				Robot.chassis.setSpeed(v(), -v());
			} else if (OI.pixyX.getVoltage() > 2.4) {
				Robot.chassis.setSpeed(-v(), v());
			} else {
				Robot.chassis.setSpeed(0, 0);
				xAlligned = true;
			}

			if (OI.pixyY.getVoltage() < 3.1) {
				Robot.chassis.setSpeed(-v(), -v());
			} else if (OI.pixyY.getVoltage() > 3.3) {
				Robot.chassis.setSpeed(v(), v());
			} else {
				Robot.chassis.setSpeed(0, 0);
				yAlligned = true;
			}
		} else if (Robot.shooter.encGet() < 960) {
			Robot.shooter.shooterAngleMotor.set(.5);
		} else if (Robot.shooter.encGet() > 960) {
			Robot.shooter.changeAngle(0);
			Robot.shooter.shooterOperation.set(1);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (timer2.get() == 0) {
				timer2.start();
			}
			if (timer2.get() < 1) {
				Robot.shooter.introBall(.5);
				System.out.println(timer2.get());
			} else if (timer2.get() > 1 && timer2.get() < 2.5) {
				Robot.shooter.introBall(-.5);
				System.out.println(timer2.get());
			} else if (timer2.get() > 2.5) {
				Robot.shooter.introBall(0);
				System.out.println(timer2.get());
				timer2.stop();
				this.cancel();
			}
		}
		SmartDashboard.putNumber("Shooter Enc Val", Robot.shooter.encGet());
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
