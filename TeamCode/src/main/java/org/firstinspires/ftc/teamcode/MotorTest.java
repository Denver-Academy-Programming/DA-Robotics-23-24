package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MotorTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize and setup the modules connected to the Control Hub
        DcMotor testDCMotor;
        try {
            testDCMotor = hardwareMap.get(DcMotor.class, "motorTest");
        } catch (IllegalArgumentException e) {
            // Prettyfy error for non-tech peoples
            throw new RuntimeException(e);
        }
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait until the user presses the run button
        waitForStart();

        // Continuously runs until the driver pressed the stop button
        // Runs the connected DcMotor named "motorTest" at power 10 until stop
        while (opModeIsActive()) {
            testDCMotor.setPower(10);
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
