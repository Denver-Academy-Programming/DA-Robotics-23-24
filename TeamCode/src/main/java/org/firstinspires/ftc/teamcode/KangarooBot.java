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

        // Setup variables for power control
        // 1 - Fast
        // 2 - Medium Speed
        // 3 - Slow
        int powerLevel;

        // Initialize and setup the modules connected to the Control Hub.
        // Also deliberately sets the right motor in reverse because its
        // physically placed in a reverse position to the left motor.
        try {
            motorRight = hardwareMap.get(DcMotor.class, "motorRight");
            motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
            motorRight.setDirection(DcMotorSimple.Direction.REVERSE);
            powerLevel = 1;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "An error occurred with the initialization of the script!\n" +
                    "Please check you have the right control scheme set on the\n" +
                    "Driver Hub so it knows which modules are connected.\n" +
                    "Full traceback:\n" + e
            );
        }

        // Display that the script/bot is ready and wait until the user presses the run button.
        telemetry.addData("Status:", "Initialized!\nWaiting to Start...");
        telemetry.update();
        waitForStart();

        // Checks for the input of gamepad1s left and right thumb sticks
        // then applies that input to which motor it corresponds to.
        while (opModeIsActive()) {
            motorRightPower = gamepad1.right_stick_y;
            motorLeftPower = gamepad1.left_stick_y;

            // Motor power is halved so its not so hard to control
            motorRight.setPower(motorRightPower / powerLevel);
            motorLeft.setPower(motorLeftPower / powerLevel);

            // Display/Update information to the telemetry screen of the Driver Station
            telemetry.addData("Power Level:", powerLevel);
            telemetry.addData("Right Motor Power:", motorRight.getPower());
            telemetry.addData("Left Motor Power:", motorLeft.getPower());
            telemetry.addData("Status:", "Running!");
            telemetry.addLine("Press B button to stop script...");
            telemetry.update();

            // Power control for the bot, makes sure that powerLevel says within a 1-3 range
            if (gamepad1.dpad_up & (powerLevel < 3)) {
                powerLevel -= 1;
            } else if (gamepad1.dpad_down & (powerLevel > 1)) {
                powerLevel += 1;
            }

            // Pressing B on the controller will stop the TeleOP script.
            if (gamepad1.b) {
                requestOpModeStop();
            }
        }
    }
}
