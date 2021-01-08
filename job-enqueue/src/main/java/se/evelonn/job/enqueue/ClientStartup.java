package se.evelonn.job.enqueue;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.StorageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class ClientStartup {

	private final static Logger LOG = LoggerFactory.getLogger(ClientStartup.class);

	@Inject
	StorageProvider storageProvider;

	public void onStartUp(@Observes StartupEvent event) {
		LOG.info("Setting up job scheduler in client");
		BackgroundJob.setJobScheduler(new JobScheduler(storageProvider));
	}
}