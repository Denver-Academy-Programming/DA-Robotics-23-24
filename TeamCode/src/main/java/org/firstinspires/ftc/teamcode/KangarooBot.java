package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class KangarooBot extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Setup variables for bot
        // Both motors the bot has attached to the Control Hub
        DcMotor motorLeft;
        DcMotor motorRight;

        // Setup controller variables
        double motorLeftPower;
        double motorRightPower;

        // Initialize and setup the modules connected to the Control Hub
        try {
            motorRight = hardwareMap.get(DcMotor.class, "motorRight");
            motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        } catch (IllegalArgumentException e) {
            // Prettyfy error for non-tech peoples
            throw new RuntimeException(e);
        }

        telemetry.addData("Status", "Initialized\nWaiting to Start...");
        telemetry.update();

        // Wait until the user presses the run button
        waitForStart();

        // Checks for the input of gamepad1s left and right thumb sticks then
        // applies that input to which motor it corresponds to
        while (opModeIsActive()) {
            motorLeftPower = gamepad1.left_stick_y;
            motorRightPower = -this.gamepad1.right_stick_y;

            motorRight.setPower(motorRightPower);
            motorLeft.setPower(motorLeftPower);

            telemetry.addData("Right Motor Power", motorRight.getPower());
            telemetry.addData("Left Motor Power", motorLeft.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();

            if (gamepad1.b) {
                requestOpModeStop();
            }
        }
    }
}
