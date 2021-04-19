package simulator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class Viewer extends JComponent implements SimulatorObserver{
	private static final int _WIDHT = 1000;
	private static final int _HEIGHT = 1000;
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _showVectors;
	Viewer(Controller ctrl){
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		//TODO Sumar border con title
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		_showVectors = true;
		//TODO Fijar tamaño con _HEIGHT y _WIDTH
		//Creacion del Listener de teclas
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					repaint();
					break;
				case '+':
					_scale = Math.max(1000.0, _scale/1.1);
					repaint();
					break;
				case '=':
					autoScale();
					repaint();
					break;
				case 'h':
					_showHelp = !_showHelp;
					repaint();
					break;
				case 'v':
					_showVectors = !_showVectors;
					repaint();
					break;
				default:
				}
			}
		});
		//Creacion del Listener del Raton
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		repaint();
	}
	//METODOS PROPORCIONADOS
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		_centerX = getWidth() /2;
		_centerY = getHeight()/2;
		//TODO Dibujar la cruz en el centro, dibujar los bodies (con vectores si showVectors es true) y la ayuda (si showHelp es true)
	}
	/**
	 * Realiza un auto escalado del viewer
	 */
	private void autoScale() {
		double max = 1.0;
		for(Body b : _bodies) {
			Vector2D p = b.getPosition();
			max = Math.max(max, Math.abs(p.getX()));
			max = Math.max(max, Math.abs(p.getY()));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(), (double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	/**
	 * Dibuja una linea con una flecha en los puntos especificos
	 * @param g Motor grafico
	 * @param x1 Origen de la flecha en X
	 * @param y1 Origen de la flecha en Y
	 * @param x2 Destino de la flecha en X
	 * @param y2 Destino de la flecha en Y
	 * @param w Ancho
	 * @param h Altura
	 * @param lineColor Color de la linea
	 * @param arrowColor Color de la flecha
	 */
	private void drawLineWithArrow(Graphics g, int x1, int y1, int x2, int y2, int w, int h, Color lineColor, Color arrowColor) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - w, xn = xm, ym = h, yn = -h, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;
		
		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;
		
		int[] xpoints = {x2, (int) xm, (int) xn};
		int[] ypoints = {y2, (int) ym, (int) yn};
		
		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);
	}
	//METODOS DEL OBSERVADOR
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(bodies);
		autoScale();
		repaint();
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(bodies);
		autoScale();
		repaint();
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		update(bodies);
		autoScale();
		repaint();
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		update(bodies);
		repaint();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
	}
	//METODOS PERSONALES
	/**
	 * Actualiza la lista de cuerpos del viewer
	 * @param bod Lista nueva de cuerpos
	 */
	private void update(List<Body> bod) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bod;
			}
		});
	}
}
