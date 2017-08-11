package com.ruderbytes.tvsi;

/**
 * Created by muhammad on 10/07/17.
 */

public class JSONResponse {
    private String status;
    private Channel[] items;

    public Channel[] getItems() {
        return items;
    }
    public String getStatus() {
        return status;
    }
}