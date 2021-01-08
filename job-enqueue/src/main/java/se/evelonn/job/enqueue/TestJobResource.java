package se.evelonn.job.enqueue;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jobrunr.scheduling.BackgroundJob;

import se.evelonn.job.api.jobdata.TestJobData;
import se.evelonn.job.api.jobtypes.TestJob;

@Path("testjob")
public class TestJobResource {

	@POST
	public Response enqueueTestJob() {
		BackgroundJob.<TestJob> enqueue(job -> {
			TestJobData jobdata = new TestJobData();
			jobdata.setTitle("This is the title");
			jobdata.setMessage("This is the message");
			job.execute(jobdata);
		});
		return Response.ok().build();
	}
}
