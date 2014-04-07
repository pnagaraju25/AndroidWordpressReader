package info.srihawong.libraries;

/**
 * Created by godsid on 4/5/14.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class RssItem implements Comparable<RssItem>, Parcelable {

    private RssFeed feed;
    private String title;
    private String link;
    private Date pubDate;
    private String description;
    private String content;

    public RssItem() {

    }

    public RssItem(Parcel source) {

        Bundle data = source.readBundle();
        title = data.getString("title");
        link = data.getString("link");
        pubDate = (Date) data.getSerializable("pubDate");
        description = data.getString("description");
        content = data.getString("content");
        feed = data.getParcelable("feed");

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        Bundle data = new Bundle();
        data.putString("title", title);
        data.putString("link", link);
        data.putSerializable("pubDate", pubDate);
        data.putString("description", description);
        data.putString("content", content);
        data.putParcelable("feed", feed);
        dest.writeBundle(data);
    }

    public static final Creator<RssItem> CREATOR = new Creator<RssItem>() {
        public RssItem createFromParcel(Parcel data) {
            return new RssItem(data);
        }
        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public RssFeed getFeed() {
        return feed;
    }

    public void setFeed(RssFeed feed) {
        this.feed = feed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setPubDate(String pubDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            this.pubDate = dateFormat.parse(pubDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(RssItem another) {
        if(getPubDate() != null && another.getPubDate() != null) {
            return getPubDate().compareTo(another.getPubDate());
        } else {
            return 0;
        }
    }

}
