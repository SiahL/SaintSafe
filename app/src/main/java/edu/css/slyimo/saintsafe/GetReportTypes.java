package edu.css.slyimo.saintsafe;

import android.app.ListActivity;

/**
 * Created by siah on 4/28/15.
 */
public class GetReportTypes{
    private String reportTitle ="";
    private Integer icon;

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getreportTitle() {
        return reportTitle;
    }

    public void setIcon(Integer icon) {this.icon = icon;}

    public Integer getIcon() {return icon;}


}

