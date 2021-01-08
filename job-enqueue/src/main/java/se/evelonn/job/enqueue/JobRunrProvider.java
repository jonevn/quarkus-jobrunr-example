package se.evelonn.job.enqueue;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.jobrunr.jobs.mappers.JobMapper;
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
