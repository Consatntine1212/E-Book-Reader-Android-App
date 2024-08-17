package com.unipi.conchro;

import java.util.ArrayList;

public class DataModal {
    private ArrayList<String> title,author,formaturi,language,subject;

    public DataModal() {
        // Default constructor for Firestore serialization
    }

    public DataModal(ArrayList<String> title, ArrayList<String> author, ArrayList<String> formaturi, ArrayList<String> language, ArrayList<String> subject) {
        this.title = title;
        this.author = author;
        this.formaturi = formaturi;
        this.language = language;
        this.subject = subject;
    }
////********************* - ***********************////
    public String getTitle() {
        if (title != null && !title.isEmpty()) {
            return title.get(0);
        } else {
            // handle case when title is null or empty
            // e.g., return a default value or throw an exception
            return "Default Title";
        }
    }
    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }
    ////********************* - ***********************////
    public String getAuthor() {
        if (author != null && !author.isEmpty()) {
            return author.get(0);
        } else {
            // handle case when title is null or empty
            // e.g., return a default value or throw an exception
            return "Default author";
        }
    }
    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }
    ////********************* - ***********************////
    public String getFormaturi() {
        if (formaturi != null && !formaturi.isEmpty()) {
            return formaturi.get(0);
        } else {
            // handle case when title is null or empty
            // e.g., return a default value or throw an exception
            return "Default formaturi";
        }
    }
    public void setFormaturi(ArrayList<String> formaturi) {
        this.formaturi = formaturi;
    }
    ////********************* - ***********************////
    public String getLanguage() {
        if (language != null && !language.isEmpty()) {
            return language.get(0);
        } else {
            // handle case when title is null or empty
            // e.g., return a default value or throw an exception
            return "Default language";
        }
    }
    public void setLanguage(ArrayList<String> language) {
        this.language = language;
    }
    ////********************* - ***********************////
    public String getSubject() {
        if (subject != null && !subject.isEmpty()) {
            return subject.get(0);
        } else {
            // handle case when title is null or empty
            // e.g., return a default value or throw an exception
            return "Default subject";
        }
    }
    public void setSubject(ArrayList<String> subject) {
        this.subject = subject;
    }
    public String getImgUrl() {
        if (formaturi != null && !formaturi.isEmpty()) {
            for (String url : formaturi) {
                if (url.contains("cover.medium")) {
                    return url;
                }
            }
            // handle case when "cover.medium" is not found in any of the URLs
            // e.g., return a default value or throw an exception
            return "Default formaturi";
        } else {
            // handle case when formaturi is null or empty
            // e.g., return a default value or throw an exception
            return "Default formaturi";
        }
    }
}
