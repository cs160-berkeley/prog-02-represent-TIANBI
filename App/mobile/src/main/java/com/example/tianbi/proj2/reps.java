package com.example.tianbi.proj2;

/**
 * Created by TIANBI on 3/1/16.
 */
public class reps {
    private int id;
    private String rep_fullname;
    private String rep_party;
    private String rep_email;
    private String rep_website;
    private String rep_lastTweet;
    private int rep_photo;

    public reps(int id, String rep_fullname, String rep_party, String rep_email, String rep_website, String rep_lastTweet, int rep_photo) {
        this.id = id;
        this.rep_fullname = rep_fullname;
        this.rep_party = rep_party;
        this.rep_email = rep_email;
        this.rep_website = rep_website;
        this.rep_lastTweet = rep_lastTweet;
        this.rep_photo = rep_photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRep_fullname() {
        return rep_fullname;
    }

    public void setRep_fullname(String rep_fullname) {
        this.rep_fullname = rep_fullname;
    }

    public String getRep_party() {
        return rep_party;
    }

    public void setRep_party(String rep_party) {
        this.rep_party = rep_party;
    }

    public String getRep_email() {
        return rep_email;
    }

    public void setRep_email(String rep_email) {
        this.rep_email = rep_email;
    }

    public String getRep_website() {
        return rep_website;
    }

    public void setRep_website(String rep_website) {
        this.rep_website = rep_website;
    }

    public String getRep_lastTweet() {
        return rep_lastTweet;
    }

    public void setRep_lastTweet(String rep_lastTweet) {
        this.rep_lastTweet = rep_lastTweet;
    }

    public int getRep_photo() {
        return rep_photo;
    }

    public void setRep_photo(int rep_photo) {
        this.rep_photo = rep_photo;
    }
}
