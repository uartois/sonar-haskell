package fr.univartois.sonarhs.measures;


import static fr.univartois.sonarhs.measures.ExampleMetrics.FILENAME_SIZE;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

public class ComputeSizeAverage implements MeasureComputer {

	@Override
	public void compute(MeasureComputerContext context) {
	    if (context.getComponent().getType() != Component.Type.FILE) {
	        int sum = 0;
	        int count = 0;
	        for (Measure child : context.getChildrenMeasures(FILENAME_SIZE.key())) {
	          sum += child.getIntValue();
	          count++;
	        }
	        int average = count == 0 ? 0 : sum / count;
	        context.addMeasure(FILENAME_SIZE.key(), average);
	    }
	}

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
	    return def.newDefinitionBuilder()
	    	      .setOutputMetrics(FILENAME_SIZE.key())
	    	.build();
	}

}
