package com.xk.ui.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.xk.uiLib.ICallback;
import com.xk.utils.SWTTools;

public class CutScreen implements ICallback{
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	protected Shell shell;
	private Display display;
	public Image img;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CutScreen window = new CutScreen();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Rectangle rect = display.getBounds();
		shell = new Shell(SWT.FILL);
		shell.setBounds(0, 0, rect.width, rect.height);
		shell.setText("SWT Application");
		SWTTools.topWindow(shell);
		Rectangle di = display.getBounds();  
        Image temps = new Image(display, di.width, di.height);  
        GC gc = new GC(display);  
        gc.copyArea(temps, 0, 0);  
        gc.dispose();  
        ScreenCanvas ts = new ScreenCanvas(shell, temps, this);
		ts.setBounds(rect);
//		SWTTools.enableTrag(ts);
	}

	@Override
	public Object callback(Object obj) {
		img = (Image) obj;
		if(null != img) {
			Clipboard board = new Clipboard(null);
			board.setContents(new ImageData[]{img.getImageData()}, new Transfer[]{ImageTransfer.getInstance()});
			board.dispose();
		}
		shell.dispose();
		return img;
	}

}
