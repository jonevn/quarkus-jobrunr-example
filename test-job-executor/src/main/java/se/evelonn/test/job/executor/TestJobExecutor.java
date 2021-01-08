package se.evelonn.test.job.executor;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.arc.Unremovable;
import se.evelonn.job.api.jobdata.TestJobData;
import se.evelonn.job.api.jobtypes.TestJob;

@ApplicationScoped
@Unremovable
public class TestJobExecutor implements TestJob {

	private final static Logger LOG = LoggerFactory.getLogger(TestJobExecutor.class);

	@Override
	public void execute(TestJobData jobdata) {
		LOG.info("Executing testjob with data: " + jobdata);
	}

}
