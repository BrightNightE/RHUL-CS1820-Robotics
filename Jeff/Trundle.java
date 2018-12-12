import lejos.robotics.subsumption.Behavior;

public class Trundle implements Behavior {

	public static boolean moving = true;

	@Override
	public boolean takeControl() {
		moving = true;
		return true;
	}

	@Override
	public void action() {
		// System.out.println("Forward?");
		Faces.HAPPY.getFaces();
		Driver.power.forward();
		while (moving);
	}

	@Override
	public void suppress() {
		moving = false;
	}

}
