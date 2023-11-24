package com.miwth.allure_spa.model;

public class TimeSlot {
    private String time;
    private String status;

    public TimeSlot(String time, String status) {
        this.time = time;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (getTime() != null ? !getTime().equals(timeSlot.getTime()) : timeSlot.getTime() != null)
            return false;
        return getStatus() != null ? getStatus().equals(timeSlot.getStatus()) : timeSlot.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getTime() != null ? getTime().hashCode() : 0;
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    public static class Builder {
        private String time;
        private String status;

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public TimeSlot build() {
            return new TimeSlot(time, status);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static TimeSlot of(String time, String status) {
        return new TimeSlot(time, status);
    }

    public static TimeSlot of(String time) {
        return new TimeSlot(time, "Available");
    }
}
