package domain;

/**
 * Created by admin on 01.02.2017.
 */
public class ServResponse {
    private boolean IsSuccess;
    private String comment;

    public ServResponse() {
        IsSuccess = true;
        comment = "";
    }

    public boolean getSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
