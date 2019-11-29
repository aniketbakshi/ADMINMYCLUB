package com.example.adminmyclub.Beans;

public class NotificationBeans {
    String title;
    String subtitle;

    public NotificationBeans() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public NotificationBeans(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }
}
