package se.evelonn.job.executor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jobrunr.dashboard.JobRunrDashboardWebServer;
import org.jobrunr.server.BackgroundJobServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class JobExecutorAppllcationLifecycle {

	private static final Logger LOG = LoggerFactory.getLogger(JobExecutorAppllcationLifecycle.class);

	@Inject
	private BackgroundJobServer server;

	@Inject
	private JobRunrDashboardWebServer webserver;

	void onStart(@Observes StartupEvent event) {
		LOG.info("Starting BackgroundJobServer and DashboardWebServer");
		server.start();
		webserver.start();
	}

	void onStop(@Observes ShutdownEvent event) {
		server.stop();
		webserver.stop();
	}
}
