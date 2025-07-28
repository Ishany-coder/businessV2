public class ClawGrabber {

    // Grab PIDs and Controllers
    private PID claw1PIDGrab = new PID(1, 1, 1);
    private PID claw2PIDGrab = new PID(1, 1, 1);
    private PID claw3PIDGrab = new PID(1, 1, 1);
    private PID claw4PIDGrab = new PID(1, 1, 1);

    private ServoController claw1Grab = new ServoController(claw1PIDGrab);
    private ServoController claw2Grab = new ServoController(claw2PIDGrab);
    private ServoController claw3Grab = new ServoController(claw3PIDGrab);
    private ServoController claw4Grab = new ServoController(claw4PIDGrab);

    // Joint 1
    private PID claw1PIDJoint1 = new PID(1, 1, 1);
    private PID claw2PIDJoint1 = new PID(1, 1, 1);
    private PID claw3PIDJoint1 = new PID(1, 1, 1);
    private PID claw4PIDJoint1 = new PID(1, 1, 1);

    private ServoController claw1Joint1 = new ServoController(claw1PIDJoint1);
    private ServoController claw2Joint1 = new ServoController(claw2PIDJoint1);
    private ServoController claw3Joint1 = new ServoController(claw3PIDJoint1);
    private ServoController claw4Joint1 = new ServoController(claw4PIDJoint1);

    // Joint 2
    private PID claw1PIDJoint2 = new PID(1, 1, 1);
    private PID claw2PIDJoint2 = new PID(1, 1, 1);
    private PID claw3PIDJoint2 = new PID(1, 1, 1);
    private PID claw4PIDJoint2 = new PID(1, 1, 1);

    private ServoController claw1Joint2 = new ServoController(claw1PIDJoint2);
    private ServoController claw2Joint2 = new ServoController(claw2PIDJoint2);
    private ServoController claw3Joint2 = new ServoController(claw3PIDJoint2);
    private ServoController claw4Joint2 = new ServoController(claw4PIDJoint2);

    // Joint 3
    private PID claw1PIDJoint3 = new PID(1, 1, 1);
    private PID claw2PIDJoint3 = new PID(1, 1, 1);
    private PID claw3PIDJoint3 = new PID(1, 1, 1);
    private PID claw4PIDJoint3 = new PID(1, 1, 1);

    private ServoController claw1Joint3 = new ServoController(claw1PIDJoint3);
    private ServoController claw2Joint3 = new ServoController(claw2PIDJoint3);
    private ServoController claw3Joint3 = new ServoController(claw3PIDJoint3);
    private ServoController claw4Joint3 = new ServoController(claw4PIDJoint3);

    // Fold angles (joint-level per claw)
    private static final double FOLD_ANGLE_CLAW1_JOINT1 = 45;
    private static final double FOLD_ANGLE_CLAW1_JOINT2 = 30;
    private static final double FOLD_ANGLE_CLAW1_JOINT3 = 15;
    private static final double FOLD_ANGLE_CLAW1_GRAB   = 60;

    private static final double FOLD_ANGLE_CLAW2_JOINT1 = 46;
    private static final double FOLD_ANGLE_CLAW2_JOINT2 = 31;
    private static final double FOLD_ANGLE_CLAW2_JOINT3 = 16;
    private static final double FOLD_ANGLE_CLAW2_GRAB   = 61;

    private static final double FOLD_ANGLE_CLAW3_JOINT1 = 47;
    private static final double FOLD_ANGLE_CLAW3_JOINT2 = 32;
    private static final double FOLD_ANGLE_CLAW3_JOINT3 = 17;
    private static final double FOLD_ANGLE_CLAW3_GRAB   = 62;

    private static final double FOLD_ANGLE_CLAW4_JOINT1 = 48;
    private static final double FOLD_ANGLE_CLAW4_JOINT2 = 33;
    private static final double FOLD_ANGLE_CLAW4_JOINT3 = 18;
    private static final double FOLD_ANGLE_CLAW4_GRAB   = 63;

    private void fold(boolean isStraight){
        if (isStraight) return;

        // Claw 1
        claw1Joint1.moveServo(FOLD_ANGLE_CLAW1_JOINT1);
        claw1Joint2.moveServo(FOLD_ANGLE_CLAW1_JOINT2);
        claw1Joint3.moveServo(FOLD_ANGLE_CLAW1_JOINT3);
        claw1Grab.moveServo(FOLD_ANGLE_CLAW1_GRAB);

        // Claw 2
        claw2Joint1.moveServo(FOLD_ANGLE_CLAW2_JOINT1);
        claw2Joint2.moveServo(FOLD_ANGLE_CLAW2_JOINT2);
        claw2Joint3.moveServo(FOLD_ANGLE_CLAW2_JOINT3);
        claw2Grab.moveServo(FOLD_ANGLE_CLAW2_GRAB);

        // Claw 3
        claw3Joint1.moveServo(FOLD_ANGLE_CLAW3_JOINT1);
        claw3Joint2.moveServo(FOLD_ANGLE_CLAW3_JOINT2);
        claw3Joint3.moveServo(FOLD_ANGLE_CLAW3_JOINT3);
        claw3Grab.moveServo(FOLD_ANGLE_CLAW3_GRAB);

        // Claw 4
        claw4Joint1.moveServo(FOLD_ANGLE_CLAW4_JOINT1);
        claw4Joint2.moveServo(FOLD_ANGLE_CLAW4_JOINT2);
        claw4Joint3.moveServo(FOLD_ANGLE_CLAW4_JOINT3);
        claw4Grab.moveServo(FOLD_ANGLE_CLAW4_GRAB);
    }
}