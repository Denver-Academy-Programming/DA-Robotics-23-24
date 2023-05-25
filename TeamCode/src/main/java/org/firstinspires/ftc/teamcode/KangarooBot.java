package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class KangarooBot extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Setup the variables for bot.
        // Both motors the bot has attached to the Control Hub.
        DcMotor motorLeft;
        DcMotor motorRight;

        // Setup variables for both motors.
        double motorLeftPower;
        double motorRightPower;

        // Initialize and setup the modules connected to the Control Hub.
        // Also deliberately sets the right motor in reverse because its
        // physically placed in reverse to the left motor.
        try {
            motorRight = hardwareMap.get(DcMotor.class, "motorRight");
            motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
            motorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (IllegalArgumentException e) {
            // Prettyfy error for non-tech peoples
            throw new RuntimeException(e);
        }

        telemetry.addData("Status", "Initialized\nWaiting to Start...");
        telemetry.update();

        // Wait until the user presses the run button.
        waitForStart();

        // Checks for the input of gamepad1s left and right thumb sticks
        // then applies that input to which motor it corresponds to.
        while (opModeIsActive()) {
            motorRightPower = gamepad1.right_stick_y;
            motorLeftPower = gamepad1.left_stick_y;

            // Motor power is halved so its not so hard to control
            motorRight.setPower(motorRightPower / 2);
            motorLeft.setPower(motorLeftPower / 2);

            telemetry.addData("Right Motor Power", motorRight.getPower());
            telemetry.addData("Left Motor Power", motorLeft.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();

            // Pressing B on the controller will stop the TeleOP script.
            if (gamepad1.b) {
                requestOpModeStop();
            }
        }
    }
}
