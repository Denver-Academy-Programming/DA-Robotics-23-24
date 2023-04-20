package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TelementryCounter extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait until the user presses the run button
        waitForStart();

        // Continously runs until the driver pressed the stop button
        // Simply adds up a count variable which gets displayed in the telemetry window
        int count = 0;
        while (opModeIsActive()) {
            String countString = String.format("Running: %d", count);
            telemetry.addData("Status", countString);
            telemetry.update();
            count += 1;
        }
    }
}
