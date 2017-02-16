package com.automation.tools;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

public class ThreadLocalCookieStore implements CookieStore {

    private final static ThreadLocal<CookieStore> ms_cookieJars = new ThreadLocal<CookieStore>() {
        @Override
        protected synchronized CookieStore initialValue() {
            return (new CookieManager()).getCookieStore(); /*InMemoryCookieStore*/
        }
    };


    public void add(URI uri, HttpCookie cookie) {
        ms_cookieJars.get().add(uri, cookie);
    }

    public List<HttpCookie> get(URI uri) {
        return ms_cookieJars.get().get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return null;
    }

    @Override
    public List<URI> getURIs() {
        return null;
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return false;
    }

    @Override
    public boolean removeAll() {
        return false;
    }

}