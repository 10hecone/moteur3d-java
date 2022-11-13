import javax.swing.*;
import javax.vecmath.Vector3d;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		// run Frame
		Frame frame = new Frame();
		Object3D object3D = new Object3D(Primitive.Dypiramid, new Vector3d(200, 200, 0));
		frame.world.objects.add(object3D);
		// add 3 sliders to rotate the object
		JSlider sliderX = new JSlider(-180, 180, 0);
		sliderX.addChangeListener(e -> object3D.rotation.x = sliderX.getValue() );
		JSlider sliderY = new JSlider(-180, 180, 0);
		sliderY.addChangeListener(e -> object3D.rotation.y = sliderY.getValue() );
		sliderY.setOrientation(SwingConstants.VERTICAL);
		JSlider sliderZ = new JSlider(-180, 180, 0);
		sliderZ.addChangeListener(e -> object3D.rotation.z = sliderZ.getValue() );
		sliderZ.setOrientation(SwingConstants.VERTICAL);
		frame.add(sliderX, BorderLayout.SOUTH);
		frame.add(sliderY, BorderLayout.WEST);
		frame.add(sliderZ, BorderLayout.EAST);
	}
}
