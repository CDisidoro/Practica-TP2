package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.SimulatorObserver;
/**
 * Visualizador de los cuerpos dentro del simulador Fisico
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 *
 */
@SuppressWarnings("serial")
public class Viewer extends JComponent implements SimulatorObserver{
	private static final int _WIDHT = 800;
	private static final int _HEIGHT = 600;
	private Color azul = Color.cyan;
	private Color rojo = Color.red;
	private Color negro = Color.black;
	private Color verde = Color.green;
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _showVectors;
	/**
	 * Constructor del visualizador de cuerpos
	 * @param ctrl Controlador del simulador, para agregar el viewer como un Observador
	 */
	Viewer(Controller ctrl){
		initGUI();
		ctrl.addObserver(this);
	}
	
	/**
	 * Inicializacion de la interfaz grafica del Visualizador
	 */
	private void initGUI() {
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(negro, 2),
						"Viewer",
						TitledBorder.LEFT,
						TitledBorder.TOP));
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		_showVectors = true;
		this.setPreferredSize(new Dimension(_WIDHT,_HEIGHT));
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
	/**
	 * Pinta los componentes del Viewer (Texto de ayuda, punto central, y cuerpos con sus vectores
	 * @param g Elemento grafico que se encarga del dibujado
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		_centerX = getWidth() /2;
		_centerY = getHeight()/2;
		//Dibujar una cruz en el centro
		gr.setColor(rojo);
		gr.drawLine(_centerX-10, _centerY, _centerX+10, _centerY);
		gr.drawLine(_centerX, _centerY-10, _centerX, _centerY+10);
		//Dibuja los bodies (Con vectores si showVectors es true)
		Iterator<Body> iterBod = _bodies.iterator();
		Body bod;
		while(iterBod.hasNext()) {
			bod = iterBod.next();
			Vector2D vector = bod.getPosition(), velocidad = bod.getVelocity().direction().scale(20), fuerza = bod.getForce().direction().scale(20);
			int posX = _centerX + (int) (vector.getX()/_scale) + 5;
			int posY = _centerY - (int) (vector.getY()/_scale) + 5;
			//Dibuja el circulo
			gr.setColor(azul);
			gr.fillOval(_centerX + (int) (vector.getX()/_scale), _centerY - (int) (vector.getY()/_scale), 10, 10);
			//Dibuja el nombre del cuerpo
			gr.setColor(negro);
			gr.drawString(bod.getId(),
					_centerX + (int) (vector.getX()/_scale) + 15,
					_centerY - (int) (vector.getY()/_scale) + 15);
			if(_showVectors) {
				//Dibujar los vectores velocidad (verde) y fuerza (rojo)
				//Dibuja la fuerza
				drawLineWithArrow(gr,
								posX,
								posY,
								posX + (int) fuerza.getX(),
								posY + (int) fuerza.getY(),
								3,
								6,
								rojo,
								rojo);
				//Dibuja la velocidad
				drawLineWithArrow(gr,
								posX,
								posY,
								posX + (int) velocidad.getX(),
								posY - (int) velocidad.getY(),
								3,
								6,
								verde,
								verde);
			}
		}
		//Dibuja help (Si showHelp es true)
		gr.setColor(rojo);
		if(_showHelp) {
			gr.drawString("h: toggle help, v: toggle vectors, +: zoom-in, -:zoom-out, =:fit \n Scaling ratio: "+_scale, 10, 30);
		}
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
