package fr.univ_amu.m1info.mars_rover;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import fr.univ_amu.m1info.mars_rover.input.MarsRoverInput;
import fr.univ_amu.m1info.mars_rover.output.MarsRoverOutput;
import fr.univ_amu.m1info.mars_rover.output.MarsRoverState;
import fr.univ_amu.m1info.mars_rover.simulator.Simulator;

import java.io.*;
import java.util.List;

public final class Main {

    // Small DTO to match the TP’s output formatting (e.g., "28 %")
    public record MarsRoverOutputYaml(String percentageExplored, List<MarsRoverState> finalRoverStates) {}

    public static void main(String[] args) {
        // args[0] (optional): input path; args[1] (optional): output path
        final String inPath  = args.length >= 1 ? args[0] : "/rectangular.yml";   // default: classpath resource
        final String outPath = args.length >= 2 ? args[1] : "output.yml";    // default: CWD/output.yml

        final ObjectMapper mapper = new ObjectMapper(
                new YAMLFactory()
                        .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                        .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
        );

        try {
            MarsRoverInput input;
            if (inPath.startsWith("/")) {
                // Read from classpath resource (e.g., /rectangular.yml in src/main/resources)
                try (InputStream is = Main.class.getResourceAsStream(inPath)) {
                    if (is == null) throw new FileNotFoundException("Classpath resource not found: " + inPath);
                    input = mapper.readValue(is, MarsRoverInput.class);
                }
            } else {
                // Read from file system
                try (InputStream is = new FileInputStream(inPath)) {
                    input = mapper.readValue(is, MarsRoverInput.class);
                }
            }

            // Run simulation
            MarsRoverOutput simOut = Simulator.run(input);

            // Format percentage as integer with " %" as required by TP (e.g., "28 %")
            int pctRounded = (int) Math.round(simOut.percentageExplored());
            String pctText = pctRounded + " %";
            MarsRoverOutputYaml yamlOut = new MarsRoverOutputYaml(pctText, simOut.finalRoverStates());

            // Write YAML to file system
            try (FileOutputStream fos = new FileOutputStream(outPath)) {
                ObjectWriter writer = mapper.writer();
                SequenceWriter sw = writer.writeValues(fos);
                sw.write(yamlOut);
            }

            System.out.println("Simulation done. Wrote: " + outPath);
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (UnsupportedOperationException e) {
            System.err.println("Unsupported feature: " + e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(3);
        }
    }
}
