// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

import java.util.HashMap;

public class Environment {
    private HashMap<String, Double> env = new HashMap<String, Double>();

    // put the 'val' value and key 'var' in the environment
    public double put(String var, double val) {
        env.put(var, val);
        return val; }
    // get the value for key 'var' in the env
    public double get(int pos, String var) throws EvalException {
        if (env.containsKey(var)) {
            return env.get(var);
        }
        throw new EvalException(pos, var);
    }

}
