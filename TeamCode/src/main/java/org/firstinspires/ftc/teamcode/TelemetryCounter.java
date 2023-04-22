package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.Locale;

@TeleOp
public class TelemetryCounter extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait until the user presses the run button
        waitForStart();

        // Continuously runs until the driver pressed the stop button
        // Simply adds up a count variable which gets displayed in the telemetry window
        int count = 0;
        while (opModeIsActive()) {
            String countString = String.format(Locale.US, "Running: %d", count);
            telemetry.addData("Status", countString);
            telemetry.update();
            count += 1;
        }
    }
}
