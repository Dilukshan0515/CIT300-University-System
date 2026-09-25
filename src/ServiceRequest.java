import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Data model representing a student service request in the university helpdesk system.
 */
public class ServiceRequest {
    private int requestId;
    private int studentId;
    private String requestType;
    private String description;
    private String priority; // HIGH, MEDIUM, LOW
    private String status;   // PENDING, IN_PROGRESS, COMPLETED
    private String timestamp;

    public ServiceRequest(int requestId, int studentId, String requestType, String description, String priority) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.requestType = requestType;
        this.description = description;
        this.priority = priority != null ? priority.toUpperCase() : "MEDIUM";
        this.status = "PENDING";
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public int getRequestId() {
        return requestId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("ServiceRequest[ReqID=%d, StudentID=%d, Type='%s', Priority='%s', Status='%s', Desc='%s', Time='%s']",
                requestId, studentId, requestType, priority, status, description, timestamp);
    }
}
