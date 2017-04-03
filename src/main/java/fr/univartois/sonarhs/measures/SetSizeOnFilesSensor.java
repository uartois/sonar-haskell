package fr.univartois.sonarhs.measures;

import static fr.univartois.sonarhs.measures.ExampleMetrics.FILENAME_SIZE;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;

public class SetSizeOnFilesSensor implements Sensor {

	@Override
	public void describe(SensorDescriptor descriptor) {
		 descriptor.name("Compute size of file names");
	}

	@Override
	public void execute(SensorContext context) {
	    FileSystem fs = context.fileSystem();
	    Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
	    for (InputFile file : files) {
	      context.<Integer>newMeasure()
	        .forMetric(FILENAME_SIZE)
	        .on(file)
	        .withValue(file.file().getName().length())
	        .save();
	    }
	}

}
