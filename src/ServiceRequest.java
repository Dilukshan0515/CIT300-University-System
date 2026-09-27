public class ServiceRequest {

    private int requestId;
    private String studentId;
    private String requestType;

    public ServiceRequest(
            int requestId,
            String studentId,
            String requestType) {

        this.requestId = requestId;
        this.studentId = studentId;
        this.requestType = requestType;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {

        return "Request ID: " + requestId
                + " | Student ID: " + studentId
                + " | Service: " + requestType;
    }
}
