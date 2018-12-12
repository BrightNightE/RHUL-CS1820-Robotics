

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Backup implements Behavior {
	private boolean backupInterupt = false;

	@Override
	public boolean takeControl() {
		backupInterupt = false;
		Driver.FRONT_SP.fetchSample(Driver.FRONT_SAMPLES, 0);
		return (Driver.FRONT_SAMPLES[0] < MoveList.WALL_DISTANCE.getMove());
	}

	@Override
	public void action() {

		Faces.SAD.getFaces();
		Driver.power.stop();
		Delay.msDelay((long) MoveList.TIMEOUT.getMove());
		Driver.RIGHT_SP.fetchSample(Driver.RIGHT_SAMPLES, 0);
		Driver.LEFT_SP.fetchSample(Driver.LEFT_SAMPLES, 0);
		
		if (backupInterupt)
			return;

		if (Driver.RIGHT_SAMPLES[0] < Driver.LEFT_SAMPLES[0]) {
			Driver.power.rotate(MoveList.TURNING_ANGLE.getMove());
		} else {
			Driver.power.rotate(-MoveList.TURNING_ANGLE.getMove());
		}
		backupInterupt = false;

	}

	@Override
	public void suppress() {
		backupInterupt = true;
	}

}
