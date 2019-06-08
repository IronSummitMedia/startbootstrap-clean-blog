package tech.daniellas.streams;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public abstract class FastBenchmarkBase {

	@Test
	public void runBenchmarks() throws RunnerException {
		Options options = new OptionsBuilder()
		    // Add class with methods annotated with @Benchmark
		    .include(this.getClass().getSimpleName())
		    // Use only one fork, single benchmark will be executed at once
		    .forks(1)
		    // Measure number of operations
		    .mode(Mode.Throughput)
		    // Performed within one second
		    .timeUnit(TimeUnit.SECONDS)
		    // Run 3 warmup iteration
		    .warmupIterations(1)
		    // Every 5 seconds long
		    .warmupTime(new TimeValue(1, TimeUnit.SECONDS))
		    // Then run 10 measurement iterations
		    .measurementIterations(1)
		    // Every 5 seconds long
		    .measurementTime(new TimeValue(2, TimeUnit.SECONDS))
		    // Finally write results to file
		    .result(reportPath())
		    // In JSON format
		    .resultFormat(ResultFormatType.JSON)
		    .build();

		new Runner(options).run();
	}

	protected abstract String reportPath();

}
