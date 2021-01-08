package se.evelonn.job.executor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.jobrunr.dashboard.JobRunrDashboardWebServer;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.server.BackgroundJobServer;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.jobrunr.utils.mapper.JsonMapper;
import org.jobrunr.utils.mapper.jsonb.JsonbJsonMapper;

@ApplicationScoped
public class JobRunrProvider {

	@Inject
	@io.quarkus.agroal.DataSource("jobs")
	private DataSource dataSource;

	@Produces
	@Singleton
	public BackgroundJobServer backgroundJobServer(StorageProvider storageProvider, JobActivator jobActivator) {
		return new BackgroundJobServer(storageProvider, jobActivator);
	}

	@Produces
	@Singleton
	public JobRunrDashboardWebServer dashboardWebServer(StorageProvider storageProvider, JsonMapper jsonMapper) {
		return new JobRunrDashboardWebServer(storageProvider, jsonMapper);
	}

	@Produces
	@Singleton
	public JobActivator jobActivator() {
		return new JobActivator() {

			@Override
			public <T> T activateJob(Class<T> aClass) {
				return CDI.current().select(aClass).get();
			}
		};
	}

	@Produces
	@Singleton
	public StorageProvider storageProvider(JobMapper jobMapper) {
		PostgresStorageProvider postgresStorageProvider = new PostgresStorageProvider(dataSource);
		postgresStorageProvider.setJobMapper(jobMapper);
		return postgresStorageProvider;
	}

	@Produces
	@Singleton
	public JobMapper jobMapper(JsonMapper jsonMapper) {
		return new JobMapper(jsonMapper);
	}

	@Produces
	@Singleton
	public JsonMapper jsonMapper() {
		return new JsonbJsonMapper();
	}
}
