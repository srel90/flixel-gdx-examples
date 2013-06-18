package org.flixel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

//import org.example.ExampleActivity;

//import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class FlxDesktop
{
	public FlxDesktop(final FlxGame game)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// -------------------------------------------------------------
				// Display mode selection
				// -------------------------------------------------------------

				String[] modes =
				{ "portrait", "landscape" };
				String modeResult = (String) JOptionPane.showInputDialog(null, "Select the display mode",
						"Initialization", JOptionPane.PLAIN_MESSAGE, null, modes, "portrait");

				// -------------------------------------------------------------
				// Resolution selection
				// -------------------------------------------------------------

				String[] resolutions =
					{ 
						"HVGA (320x480)", 
						"WVGA800 (480x800)", 
						"WVGA854 (480x854)",
						"SVGA (600x800)",
						"WSVGA (600x1024)",
						"WXGA (800x1280)" 
					};
				String resolutionResult = (String) JOptionPane.showInputDialog(null, "Select your display",
						"Initialization", JOptionPane.PLAIN_MESSAGE, null, resolutions, "HVGA (320x480)");

				// -------------------------------------------------------------
				// App launch
				// -------------------------------------------------------------

				boolean isPortrait = modeResult.equals("portrait");

				if(resolutionResult != null && resolutionResult.length() > 0)
				{
					Matcher m = Pattern.compile("(\\d+)x(\\d+)").matcher(resolutionResult);
					m.find();
					int w = Integer.parseInt(m.group(isPortrait ? 1 : 2));
					int h = Integer.parseInt(m.group(isPortrait ? 2 : 1));
					launch(game, w, h);
				}
			}
		});
	}

	public static void launch(FlxGame game, int width, int height)
	{
		new LwjglApplication(game, "", width, height, false);
	}
}
