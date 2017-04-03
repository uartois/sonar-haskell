package fr.univartois.sonarhs.measures;

import static fr.univartois.sonarhs.measures.ExampleMetrics.FILENAME_SIZE;
import static fr.univartois.sonarhs.measures.ExampleMetrics.FILENAME_SIZE_RATING;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

public class ComputeSizeRating implements MeasureComputer {

	private static final int THRESHOLD = 20;
	private static final int RATING_A = 1;
	private static final int RATING_B = 2;
	
	@Override
	public void compute(MeasureComputerContext context) {
	    Measure size = context.getMeasure(FILENAME_SIZE.key());
	    if (size != null) {
	      // rating values are currently implemented as integers in API
	      int rating = RATING_A;
	      if (size.getIntValue() > THRESHOLD) {
	        rating = RATING_B;
	      }
	      context.addMeasure(FILENAME_SIZE_RATING.key(), rating);
	}
	}

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
	    return def.newDefinitionBuilder()
	    	      .setInputMetrics(FILENAME_SIZE.key())
	    	      .setOutputMetrics(FILENAME_SIZE_RATING.key())
	    	.build();
	}

}
