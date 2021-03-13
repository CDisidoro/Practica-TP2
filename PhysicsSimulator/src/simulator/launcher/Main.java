package simulator.launcher;

import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;
import simulator.control.Controller;
import simulator.control.StateComparator;
import simulator.factories.BasicBodyBuilder;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.EpsilonEqualStateBuilder;
import simulator.factories.Factory;
import simulator.factories.MassEqualStateBuilder;
import simulator.factories.MassLosingBodyBuilder;
import simulator.factories.MovingTowardsFixedPointBuilder;
import simulator.factories.NewtonUniversalGravitationBuilder;
import simulator.factories.NoForceBuilder;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.PhysicsSimulator;

public class Main {

	// Valores por defecto para algunos parametros
	private final static Double _dtimeDefaultValue = 2500.0;
	private final static String _forceLawsDefaultValue = "nlug";
	private final static String _stateComparatorDefaultValue = "epseq";
	private final static Integer _stepsDefaultValue = 150;

	// Algunos atributos para almacenar valores correspondientes a los parametros de la linea de comandos
	private static Double _dtime = null;
	private static String _inFile = null;
	private static String _outFile = null;
	private static String _eoFile = null;
	private static JSONObject _forceLawsInfo = null;
	private static JSONObject _stateComparatorInfo = null;
	private static Integer _steps = null;

	// Factorias
	private static Factory<Body> _bodyFactory;
	private static Factory<ForceLaws> _forceLawsFactory;
	private static Factory<StateComparator> _stateComparatorFactory;

	//Inicializador de los componentes de la simulacion (Comparadores de Estado, Factorias de Leyes y Cuerpos)
	private static void init() {
		// Inicializacion de Factorias de Cuerpos
		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		bodyBuilders.add(new BasicBodyBuilder());
		bodyBuilders.add(new MassLosingBodyBuilder());
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilders);
		// Inicializacion de Factorias de Leyes de Fuerza
		ArrayList<Builder<ForceLaws>> lawsBuilders = new ArrayList<>();
		lawsBuilders.add(new NewtonUniversalGravitationBuilder());
		lawsBuilders.add(new MovingTowardsFixedPointBuilder());
		lawsBuilders.add(new NoForceBuilder());
		_forceLawsFactory = new BuilderBasedFactory<ForceLaws>(lawsBuilders);
		// Inicializacion de Factorias de Comparadores de Estados
		ArrayList<Builder<StateComparator>> stateBuilders = new ArrayList<>();
		stateBuilders.add(new MassEqualStateBuilder());
		stateBuilders.add(new EpsilonEqualStateBuilder());
		_stateComparatorFactory = new BuilderBasedFactory<StateComparator>(stateBuilders);
	}

	//Parseo de los argumentos de la linea de comandos
	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);

			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseStepsOption(line); //s
			parseOutFileOption(line); //o
			parseExpOutFileOption(line); //eo
			parseDeltaTimeOption(line);
			parseForceLawsOption(line);
			parseStateComparatorOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	//Construye el Menu de Opciones del Simulador
	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		// Ayuda
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message.").build());

		// Archivo de Entrada
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Bodies JSON input file.").build());
		
		// Salida Esperada
		cmdLineOptions.addOption(Option.builder("eo").longOpt("expected-output").hasArg()
				.desc("The expected output file. If not provided no comparison is applied")
				.build());
		
		// Archivo de Salida
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg()
				.desc("Output file, where output is written. Default value: the standard output").build());
		
		// Pasos
		cmdLineOptions.addOption(Option.builder("s").longOpt("steps").hasArg()
				.desc("An integer = number of steps. Default: " + _stepsDefaultValue).build());
		
		// Tiempo Delta
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());

		// Leyes de Fuerza
		cmdLineOptions.addOption(Option.builder("fl").longOpt("force-laws").hasArg()
				.desc("Force laws to be used in the simulator. Possible values: "
						+ factoryPossibleValues(_forceLawsFactory) + ". Default value: '" + _forceLawsDefaultValue
						+ "'.")
				.build());

		// Comparador de Leyes de Gravedad
		cmdLineOptions.addOption(Option.builder("cmp").longOpt("comparator").hasArg()
				.desc("State comparator to be used when comparing states. Possible values: "
						+ factoryPossibleValues(_stateComparatorFactory) + ". Default value: '"
						+ _stateComparatorDefaultValue + "'.")
				.build());

		return cmdLineOptions;
	}
	
	//Detecta los posibles valores para una Factoria
	public static String factoryPossibleValues(Factory<?> factory) {
		//Si la factoria es nula, retorna un mensaje diciendo que No hay posibles valores para la Factoria
		if (factory == null)
			return "No values found (the factory is null)";

		String s = "";
		//Para cada JSONObject dentro del getInfo de factory, se concatenara el tipo y descripcion de dato posible de la factoria
		for (JSONObject fe : factory.getInfo()) {
			if (s.length() > 0) {
				s = s + ", ";
			}
			s = s + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
		}

		s = s + ". You can provide the 'data' json attaching :{...} to the tag, but without spaces.";
		return s;
	}

	//Parseo de la Opcion de Ayuda
	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	//Parseo de la Opcion de Archivo de Entrada que lanza un ParseException si no se especifica un archivo de entrada para el modo batch
	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null) {
			throw new ParseException("In batch mode an input file of bodies is required");
		}
	}

	//Parseo de la Opcion de Archivo de Salida que lanza un ParseException si no se especifica un archivo de salida
	//¿Es necesario lanzar una excepcion si no recibe un archivo de salida? Como condicionar un parametro opcional? - TODO
	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
		if (_outFile == null) {
			throw new ParseException("In batch mode an output file is required");
		}
	}
	
	//Parseo de la Opcion de Archivo de Salida Esperada que lanza un ParseException si no se especifica un archivo de comparacion
	//¿Es necesario lanzar una expecion si no recibe un archivo de comparacion? Como condicionar un parametro opcional? - TODO
	private static void parseExpOutFileOption(CommandLine line) throws ParseException{
		_eoFile = line.getOptionValue("eo");
		if(_eoFile == null) {
			throw new ParseException("In batch mode an expected output file is required");
		}
	}
	
	//Parseo de la Opcion del Tiempo Delta que lanza un ParseException si la informacion del tiempo delta es invalida
	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}

	//Parseo de la Opcion de Pasos que lanza un ParseException si la informacion de pasos es invalida
	//¿Es necesario lanzar una expecion si no recibe un archivo de comparacion? Como condicionar un parametro opcional? - TODO
	private static void parseStepsOption(CommandLine line) throws ParseException{
		String steps = line.getOptionValue("s", _stepsDefaultValue.toString());
		try {
			_steps = Integer.parseInt(steps);
			assert (_steps > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid steps value: " + steps);
		}
	}
	
	//Parsea el tipo de objeto recibido en el String con el formato (tipo:dato) o (tipo) en un JSONObject
	private static JSONObject parseWRTFactory(String v, Factory<?> factory) {

		// the value of v is either a tag for the type, or a tag:data where data is a
		// JSON structure corresponding to the data of that type. We split this
		// information
		// into variables 'type' and 'data'
		//
		//Busca el separador entre el tipo y el dato en el string v
		int i = v.indexOf(":");
		String type = null;
		String data = null;
		//Si se ha encontrado el separador en el string, creara dos substring type y data separados por la posicion del separador
		if (i != -1) {
			type = v.substring(0, i);
			data = v.substring(i + 1);
		} else { //En caso opuesto el tipo sera igual al string v
			type = v;
			data = "{}";
		}

		// Comprueba si el tipo es soportado por la Factoria
		boolean found = false;
		for (JSONObject fe : factory.getInfo()) {
			if (type.equals(fe.getString("type"))) {
				found = true;
				break;
			}
		}

		// Si se encuentra una Factoria para el objeto, creara un JSONObject para este
		JSONObject jo = null;
		if (found) {
			jo = new JSONObject();
			jo.put("type", type);
			jo.put("data", new JSONObject(data));
		}
		return jo;

	}

	//Parseo de la Opcion de Ley de Fuerza, que lanza un ParseException si la informacion de la ley es invalida
	private static void parseForceLawsOption(CommandLine line) throws ParseException {
		String fl = line.getOptionValue("fl", _forceLawsDefaultValue);
		_forceLawsInfo = parseWRTFactory(fl, _forceLawsFactory);
		if (_forceLawsInfo == null) {
			throw new ParseException("Invalid force laws: " + fl);
		}
	}

	//Parseo de la Opcion de Comparador de Estados que lanza un ParseException si la informacion del comparador es invalida
	private static void parseStateComparatorOption(CommandLine line) throws ParseException {
		String scmp = line.getOptionValue("cmp", _stateComparatorDefaultValue);
		_stateComparatorInfo = parseWRTFactory(scmp, _stateComparatorFactory);
		if (_stateComparatorInfo == null) {
			throw new ParseException("Invalid state comparator: " + scmp);
		}
	}

	//Inicio del modo Batch - TODO
	private static void startBatchMode() throws Exception {
		PhysicsSimulator simulador = new PhysicsSimulator(_forceLawsFactory.createInstance(_forceLawsInfo), _dtime); //Crea el simulador
		//TODO crear los ficheros de E/S
		_stateComparatorFactory.createInstance(_stateComparatorInfo); //Crea el Comparador de estados
		Controller controlador = new Controller(simulador, _bodyFactory);
		controlador.localBodies(null); //TODO Pasar al controlador una lista de cuerpos
		controlador.run(_steps, null, null, null); //TODO Pasar al controlador la Entrada, Salida, Salida Esperada y Comparador de Estado
	}

	//Funcion de inicio del simulador que llama al parseo de los argumentos pasados e inicia el modo Batch
	private static void start(String[] args) throws Exception {
		parseArgs(args);
		startBatchMode();
	}

	//Funcion principal de ejecución que llama a init y a start
	public static void main(String[] args) {
		try {
			init();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
