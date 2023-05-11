package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class MotorButton extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize and setup the modules connected to the Control Hub
        DcMotor testDCMotor;
        DigitalChannel touchSensor;

        try {
            testDCMotor = hardwareMap.get(DcMotor.class, "motorTest");
            touchSensor = hardwareMap.get(DigitalChannel.class, "digitalTouch");
            touchSensor.setMode(DigitalChannel.Mode.INPUT);
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
        telemetry.addData("Status", "Running");
        telemetry.update();
        while (opModeIsActive()) {
            if (touchSensor.getState()) {
                testDCMotor.setPower(10);
                telemetry.addData("Status", "Motor On");
            } else {
                testDCMotor.setPower(0);
                telemetry.addData("Status", "Motor Off");
            }
            telemetry.update();
        }
    }
}
