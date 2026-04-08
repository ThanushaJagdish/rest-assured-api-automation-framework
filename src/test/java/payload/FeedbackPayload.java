package payload;

import pojo.FeedbackPojo;

public class FeedbackPayload {
	
	public static FeedbackPojo validFeedback() {
	    return new FeedbackPojo("John Smith", "john@test.com", "Test Subject", "Test Message");
	}

	public static FeedbackPojo emptyFeedback() {
	    return new FeedbackPojo("", "", "", "");
	}
}
