package experimentation;

import java.io.FileReader;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
	public static void main(String[] args) {
		try {
            Gson gson = new Gson();
            List<Config> configs = gson.fromJson(new FileReader("experimentation/configurations.json"), new TypeToken<List<Config>>() {}.getType());
            for (Config config : configs) {
                String[] strat = new String[config.strategies.length];
                for (int i = 0; i < config.strategies.length; i++) {
                    strat[i] = config.strategies[i];
                }
                int[] depthVec = config.depths;
                int[] parm = config.params;
                int numIterations = config.numIterations;
                Map<Integer, List<Integer>> teams = config.teamMapping;
                Demo demo = new Demo(strat, parm, depthVec, teams,numIterations);
                demo.startDemo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	 static class Config {
	        String[] strategies;
	        int[] depths;
	        int[] params;
	        int numIterations;
	        Map<Integer, List<Integer>> teamMapping;
	    }

}
