package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.auto.programs.ExampleAuto;
import frc.robot.commands.drivetrain.ArcadeDriveCmd;
import frc.robot.subsystems.ExampleSys;
import frc.robot.subsystems.SwerveSys;


public class RobotContainer {
    // Initialize subsystems.
    private final SwerveSys swerveSys = new SwerveSys();
    private final ExampleSys exampleSys = new ExampleSys();

    // Initialize joysticks.
    private final Joystick leftJoystick = new Joystick(0);
    private final Joystick rightJoystick = new Joystick(1);

    // Initialize auto selector.
    SendableChooser<Command> autoSelector = new SendableChooser<Command>();

    private final PWMSparkMax hook = new PWMSparkMax(0);
    private final PWMSparkMax intake = new PWMSparkMax(1);
    private final PWMSparkMax shooterHold = new PWMSparkMax(2);
    private final PWMSparkMax shooterGo = new PWMSparkMax(3);

    public RobotContainer() {
        SmartDashboard.putData("auto selector", autoSelector);

        // Add programs to auto selector.
        autoSelector.setDefaultOption("Do Nothing", null);
        autoSelector.addOption("Example Auto", new ExampleAuto(swerveSys, exampleSys));

        configDriverBindings();
    }

    public void configDriverBindings() {

        System.out.println("BEFORE SWERVESYS");
        swerveSys.setDefaultCommand(new ArcadeDriveCmd(
            () -> MathUtil.applyDeadband(leftJoystick.getY(), ControllerConstants.joystickDeadband),
            () -> MathUtil.applyDeadband(leftJoystick.getX(), ControllerConstants.joystickDeadband),
            () -> MathUtil.applyDeadband(rightJoystick.getX(), ControllerConstants.joystickDeadband),
            true,
            true,
            swerveSys
        )
        );
        System.out.println("AFTER SWERVESYS");
   
        if(rightJoystick.getTriggerPressed()) {
            Commands.runOnce(() -> swerveSys.resetHeading());
        }
       
        //HOOK Mechanism
        if(rightJoystick.getRawButton(5)) {
            System.out.println("RIGHT5");
        }
        if (leftJoystick.getRawButton(5)) {
            System.out.println("Hook");
          hook.set(1);
        } else if (leftJoystick.getRawButton(4)){
          hook.set(-1);
        }else{
          hook.set(0);
        }

      //INTAKE/SHOOTERHOLD Mechanism
        if (leftJoystick.getRawButton(3)){
            intake.set(1);
            shooterHold.set(1);
        } else if (leftJoystick.getRawButton(2)){
            intake.set(-1);
            shooterHold.set(-1);

      //SHOOTERGO Mechanism
        }else if (leftJoystick.getTrigger()){
            shooterGo.set(1);
        }

      
        //driverController.axisGreaterThan(XboxController.Axis.kLeftTrigger.value, ControllerConstants.triggerPressedThreshhold)
        //    .whileTrue(Commands.runOnce(() -> swerveSys.lock()));

        /* Orignal code for xBox controller object
        swerveSys.setDefaultCommand(new ArcadeDriveCmd(
            () -> MathUtil.applyDeadband(driverController.getLeftY(), ControllerConstants.joystickDeadband),
            () -> MathUtil.applyDeadband(driverController.getLeftX(), ControllerConstants.joystickDeadband),
            () -> MathUtil.applyDeadband(driverController.getRightX(), ControllerConstants.joystickDeadband),
            true,
            true,
            swerveSys
        ));
        
        
        // If you're more comfortable with it, you still can use the other way (i.e. new ResetHeadingCmd(swerveSys)).
        // Otherwise I would delete those simple commands just to keep things clean.

        // Start is the "three lines" button. Back is the "windows" button.
        driverController.start().onTrue(Commands.runOnce(() -> swerveSys.resetHeading()));

        driverController.axisGreaterThan(XboxController.Axis.kLeftTrigger.value, ControllerConstants.triggerPressedThreshhold)
            .whileTrue(Commands.runOnce(() -> swerveSys.lock()));
            */
    }

    public Command getAutonomousCommand() {
        return autoSelector.getSelected();
    }

    // For uniformity, any information sent to Shuffleboard/SmartDashboard should go here.
    public void updateInterface() {
        SmartDashboard.putNumber("heading degrees", swerveSys.getHeading().getDegrees());
        SmartDashboard.putNumber("speed m/s", swerveSys.getAverageDriveVelocityMetersPerSec());

        SmartDashboard.putNumber("FL angle degrees", swerveSys.getModuleStates()[0].angle.getDegrees());
        SmartDashboard.putNumber("FR angle degrees", swerveSys.getModuleStates()[1].angle.getDegrees());
        SmartDashboard.putNumber("BL angle degrees", swerveSys.getModuleStates()[2].angle.getDegrees());
        SmartDashboard.putNumber("BR angle degrees", swerveSys.getModuleStates()[3].angle.getDegrees());

        SmartDashboard.putNumber("FL raw CANCoder degrees", swerveSys.getCanCoderAngles()[0].getDegrees());
        SmartDashboard.putNumber("FR raw CANCoder degrees", swerveSys.getCanCoderAngles()[1].getDegrees());
        SmartDashboard.putNumber("BL raw CANCoder degrees", swerveSys.getCanCoderAngles()[2].getDegrees());
        SmartDashboard.putNumber("BR raw CANCoder degrees", swerveSys.getCanCoderAngles()[3].getDegrees());

        SmartDashboard.putNumber("FL offset CANCoder degrees", swerveSys.getCanCoderAngles()[0].getDegrees() - DriveConstants.frontLeftModOffset.getDegrees());
        SmartDashboard.putNumber("FR offset CANCoder degrees", swerveSys.getCanCoderAngles()[1].getDegrees() - DriveConstants.frontRightModOffset.getDegrees());
        SmartDashboard.putNumber("BL offset CANCoder degrees", swerveSys.getCanCoderAngles()[2].getDegrees() - DriveConstants.backLeftModOffset.getDegrees());
        SmartDashboard.putNumber("BR offset CANCoder degrees", swerveSys.getCanCoderAngles()[3].getDegrees() - DriveConstants.backRightModOffset.getDegrees());
    }
}
